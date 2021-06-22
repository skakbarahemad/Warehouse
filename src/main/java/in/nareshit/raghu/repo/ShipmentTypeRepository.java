package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.model.ShipmentType;

public interface ShipmentTypeRepository extends JpaRepository<ShipmentType, Integer> {
	
	@Query("Select count(shipCode) from ShipmentType where shipCode=:shipCode")
	Integer getShipmentTypeCodeCount(String shipCode);
	
	@Query("Select count(shipCode) from ShipmentType where shipCode=:shipCode and id!=:id")
	Integer getShipmentTypeCodeCountForEdit(String shipCode, Integer id);
}
