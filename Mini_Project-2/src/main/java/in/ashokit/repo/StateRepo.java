package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.State;

public interface StateRepo extends JpaRepository<State, Integer> {
	@Query(value="select * from state_master where country_id=:countryId",nativeQuery=true)
	public List<State> findByCountry_CountryId(Integer countryId);
}
