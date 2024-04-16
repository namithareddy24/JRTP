package in.ashokit.dto;

public class Dashboard {
	
	private Long TotalEnquiries;
	private Long OpenEnquiries;
	private Long EnrolledEnquiries;
	private Long LostEnquiries;
	public Long getTotalEnquiries() {
		return TotalEnquiries;
	}
	public void setTotalEnquiries(Long totalEnquiries2) {
		TotalEnquiries = totalEnquiries2;
	}
	public Long getOpenEnquiries() {
		return OpenEnquiries;
	}
	public void setOpenEnquiries(Long openEnquiries) {
		OpenEnquiries = openEnquiries;
	}
	public Long getEnrolledEnquiries() {
		return EnrolledEnquiries;
	}
	public void setEnrolledEnquiries(Long enrolledEnquiries) {
		EnrolledEnquiries = enrolledEnquiries;
	}
	public Long getLostEnquiries() {
		return LostEnquiries;
	}
	public void setLostEnquiries(Long lostEnquiries) {
		LostEnquiries = lostEnquiries;
	}
	

}
