package services;

import java.io.IOException;
import java.io.InputStream;

import javax.persistence.EntityTransaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.transaction.Transaction;

import org.hibernate.Session;

import entities.Certificate;
import hibernatecfg.HibernateUtil;
import repository.CertificateDao;

@WebServlet("/certificate")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,     // 10MB
    maxRequestSize = 1024 * 1024 * 50   // 50MB
)
public class CertificateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CertificateDao certificateDao;

    @Override
    public void init() {
        certificateDao = new CertificateDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("certificateinsert".equals(action)) {
                insertCertificate(request, response);
            } else if ("certificateupdate".equals(action)) {
                updateCertificate(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid Action");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void insertCertificate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String year = request.getParameter("year");
        String college = request.getParameter("college");
        Part filePart = request.getPart("file");

        if (filePart == null || filePart.getSize() <= 0) {
            request.setAttribute("error", "File upload is required.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Declare the session at the start of the method
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = null;
            try {
                transaction = (Transaction) session.beginTransaction();

                // Create certificate object
                Certificate certificate = new Certificate();
                certificate.setYear(year);
                certificate.setCollege(college);

                // Process file upload and set file data
                try (InputStream fileContent = filePart.getInputStream()) {
                    byte[] fileBytes = fileContent.readAllBytes();
                    certificate.setFileData(fileBytes);
                    certificate.setFileName(getFileName(filePart));
                }

                // Save certificate and commit transaction
                session.save(certificate);
                transaction.commit();

                // Redirect to success page
                request.setAttribute("successMessage", "Certificate added successfully!");
                request.getRequestDispatcher("success.jsp").forward(request, response);

            } catch (Exception e) {
                if (transaction != null && ((EntityTransaction) transaction).isActive()) {
                    transaction.rollback();
                }
                // Log the error to investigate further
                e.printStackTrace();
                request.setAttribute("error", "An error occurred while adding the certificate.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Handle any additional exceptions, like session-related ones
            e.printStackTrace();
            request.setAttribute("error", "Database connection error.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }


    private void updateCertificate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String year = request.getParameter("year");
        String college = request.getParameter("college");
        Part filePart = request.getPart("file");

        Certificate certificate = certificateDao.getCertificate(id);
        if (certificate == null) {
            request.setAttribute("error", "Certificate not found.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        certificate.setYear(year);
        certificate.setCollege(college);

        if (filePart != null && filePart.getSize() > 0) {
            try (InputStream fileContent = filePart.getInputStream()) {
                byte[] fileBytes = fileContent.readAllBytes();
                certificate.setFileData(fileBytes);
                certificate.setFileName(getFileName(filePart));
            }
        }

        certificateDao.updateCertificate(certificate);

        request.setAttribute("successMessage", "Certificate updated successfully!");
        request.getRequestDispatcher("success.jsp").forward(request, response);
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String content : contentDisposition.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return "unknown";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
