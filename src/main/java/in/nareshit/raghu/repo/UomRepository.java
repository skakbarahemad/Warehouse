package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.model.Uom;

public interface UomRepository extends JpaRepository<Uom, Integer>{

	@Query("Select count(uomModel) from Uom where uomModel=:uomModel")
	Integer countOfUomModel(String uomModel);
	
	@Query("Select count(uomModel) from Uom where uomModel=:uomModel and id!=:id")
	Integer countOfUomModelForEdit(String uomModel, Integer id);
}
