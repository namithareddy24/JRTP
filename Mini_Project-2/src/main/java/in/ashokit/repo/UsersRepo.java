package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Users;

public interface UsersRepo extends JpaRepository<Users, Integer>{
	public Users findByEmail(String email);
	public Users findByEmailAndPwd(String email,String pwd);
	
}
