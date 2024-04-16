package in.ashokit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="enquiry")
public class Enquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eid;
	private String name;
	private Long phno;
	private String mode;
	
	private String course;
	private String status;
	@CreationTimestamp
	private LocalDate CreateDate;
	@UpdateTimestamp
	private LocalDate UpdateDate;
	
	@ManyToOne
	@JoinColumn(name="cid")
	private Counsellor counsellor;

	public Integer getEid() {
		return eid;
	}

	public void setEid(Integer eid) {
		this.eid = eid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getPhno() {
		return phno;
	}

	public void setPhno(Long phno) {
		this.phno = phno;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(LocalDate createDate) {
		CreateDate = createDate;
	}

	public LocalDate getUpdateDate() {
		return UpdateDate;
	}

	public void setUpdateDate(LocalDate updateDate) {
		UpdateDate = updateDate;
	}

	public Counsellor getCounsellor() {
		return counsellor;
	}

	public void setCounsellor(Counsellor counsellor) {
		this.counsellor = counsellor;
	}

	}
