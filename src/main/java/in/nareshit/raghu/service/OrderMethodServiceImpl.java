package in.nareshit.raghu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.exception.OrderMethodNotFoundException;
import in.nareshit.raghu.model.OrderMethod;
import in.nareshit.raghu.repo.OrderMethodRepository;

@Service
public class OrderMethodServiceImpl implements IOrderMethodService {

	@Autowired
	private OrderMethodRepository repo;

	@Override
	public Integer saveOrderMethod(OrderMethod orderMethod) {
		OrderMethod om = repo.save(orderMethod);
		Integer id = om.getId();
		return id;
	}

	@Override
	public List<OrderMethod> getAllOrderMethod() {
		List<OrderMethod> list = repo.findAll();
		return list;
	}

	@Override
	public void deleteOrderMethod(Integer id) {
		if(repo.findById(id).isPresent()) {
			repo.deleteById(id);
		}
		else {
			throw new OrderMethodNotFoundException("Order Method '"+id+"' Not Exist");
		}
		
	}

	@Override
	public Optional<OrderMethod> editOrderMethod(Integer id) {
		if(repo.findById(id).empty() != null) {
			Optional<OrderMethod> om = repo.findById(id);
			return om;
		}
		else {
			throw new OrderMethodNotFoundException("Order Method'"+id+"'not exist");
		}
		
	}

	@Override
	public Integer updateOrderMethod(OrderMethod orderMethod) {
		repo.save(orderMethod);
//		Integer id = om.getId();
		return orderMethod.getId();
	}

	@Override
	public boolean isOrderMethodCodeIsExist(String orderCode) {
		Integer count = repo.getOrderMethodCodeCount(orderCode);
		boolean isExist = count>0?true:false;
		return isExist;
	}

	@Override
	public boolean isOrderMethodCodeIsExistForEdit(Integer id, String orderCode) {
		Integer count = repo.getOrderMethodCodeCountForEdit(id, orderCode);
		boolean isExist = count>0?true:false;
		return isExist;
	}
	
	
}
