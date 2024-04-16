package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.City;

public interface CityRepo extends JpaRepository<City, Integer> {
	@Query(value="select * from city_master where state_id=:stateId",nativeQuery=true)
	public List<City> findByState_StateId(Integer stateId);
}
