package in.ashokit.entity;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "counsellor")
public class Counsellor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cid;
	private String name;
	private String email;
	private String pwd;
	private Long phno;
	@CreationTimestamp
	private LocalDate CreateDate;
	@UpdateTimestamp
	private LocalDate UpdateDate;

	@OneToMany(mappedBy = "counsellor", cascade = CascadeType.ALL)
	private List<Enquiry> enquiry;

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Long getPhno() {
		return phno;
	}

	public void setPhno(Long phno) {
		this.phno = phno;
	}

	public List<Enquiry> getEnquiry() {
		return enquiry;
	}

	public void setEnquiry(List<Enquiry> enquiry) {
		this.enquiry = enquiry;
	}

}
