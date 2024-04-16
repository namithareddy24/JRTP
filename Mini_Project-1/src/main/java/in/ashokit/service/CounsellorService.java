package in.ashokit.service;

import in.ashokit.entity.Counsellor;

public interface CounsellorService{
	
	public boolean saveCounsellor(Counsellor counsellor);
	
	public Counsellor getCounsellor(String email,String pwd);

}
