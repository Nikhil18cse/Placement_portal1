package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="students")
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;
	
	@Column(name="name")    
	private String name;
	
	@Column(name="college")
	private String college;
	
	@Column(name="roll")
	private String roll;
	
	@Column(name="qualification")
	private String qualification;
	
	@Column(name="course")
	private String course;

	@Column(name="year")
	private String year;
	
	@Column(name="certificate")
	private String certificate;
	
	@Column(name="hallTicketNo")
	private String hallTicketNo;
	
	public Student() {
	}
	
	
	public Student(String name, String college, String roll, String qualification, String course, String year2,
			String certificate, String hallTicketNo) 
	{
		
		this.name = name;
		this.college = college;
		this.roll = roll;
		this.qualification = qualification;
		this.course = course;
		this.year = year2;
		this.certificate = certificate;
		this.hallTicketNo = hallTicketNo;
	}
	
	public Student(int id, String name, String college, String roll, String qualification, String course, String year,
			String certificate, String hallTicketNo) 
	{
		this.id = id;
		this.name = name;
		this.college = college;
		this.roll = roll;
		this.qualification = qualification;
		this.course = course;
		this.year = year;
		this.certificate = certificate;
		this.hallTicketNo = hallTicketNo;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCollege() {
		return college;
	}


	public void setCollege(String college) {
		this.college = college;
	}


	public String getRoll() {
		return roll;
	}


	public void setRoll(String roll) {
		this.roll = roll;
	}


	public String getQualification() {
		return qualification;
	}


	public void setQualification(String qualification) {
		this.qualification = qualification;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	public String getYear() {
		return year;
	}


	public void setYear(String year) {
		this.year = year;
	}


	public String getCertificate() {
		return certificate;
	}


	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}


	public String getHallTicketNo() {
		return hallTicketNo;
	}


	public void setHallTicketNo(String hallTicketNo) {
		this.hallTicketNo = hallTicketNo;
	}
	
	

	
}
