package in.nareshit.raghu.service;

import java.util.List;
import java.util.Optional;

import in.nareshit.raghu.model.OrderMethod;

public interface IOrderMethodService {
	
	public Integer saveOrderMethod(OrderMethod orderMethod);
	
	public List<OrderMethod> getAllOrderMethod();
	
	public void deleteOrderMethod(Integer id);
	
	public Optional<OrderMethod> editOrderMethod(Integer id);
	
	public Integer updateOrderMethod(OrderMethod orderMethod);
	
	public boolean isOrderMethodCodeIsExist(String orderCode);

	public boolean isOrderMethodCodeIsExistForEdit(Integer id, String orderCode);
}
