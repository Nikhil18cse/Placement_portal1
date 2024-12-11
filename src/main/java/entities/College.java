package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "colleges")
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected int id;

	@Column(name = "collegedmin")
	private String collegedmin;

	@Column(name = "collegename")
	private String collegename;

	@Column(name = "location")
	private String location;

	public College() {
	}

	public College(String collegedmin, String collegename, String location) {

		this.collegedmin = collegedmin;
		this.collegename = collegename;
		this.location = location;

	}

	public College(int id, String collegedmin, String collegename, String location) {
		this.id = id;
		this.collegedmin = collegedmin;
		this.collegename = collegename;
		this.location = location;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCollegedmin() {
		return collegedmin;
	}

	public void setCollegedmin(String collegedmin) {
		this.collegedmin = collegedmin;
	}

	public String getCollegename() {
		return collegename;
	}

	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
