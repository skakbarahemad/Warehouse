package in.nareshit.raghu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.nareshit.raghu.model.OrderMethod;

public interface OrderMethodRepository extends JpaRepository<OrderMethod, Integer> {
	
	@Query("SELECT COUNT(orderCode) FROM OrderMethod WHERE orderCode=:orderCode")
	Integer getOrderMethodCodeCount(String orderCode);

	@Query("SELECT COUNT(orderCode) FROM OrderMethod WHERE orderCode=:orderCode AND id!=:id")
	Integer getOrderMethodCodeCountForEdit(Integer id, String orderCode);
}
