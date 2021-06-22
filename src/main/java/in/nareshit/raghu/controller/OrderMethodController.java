package in.nareshit.raghu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.nareshit.raghu.exception.OrderMethodNotFoundException;
import in.nareshit.raghu.model.OrderMethod;
import in.nareshit.raghu.service.IOrderMethodService;

@Controller
@RequestMapping("/om")
public class OrderMethodController {
	
	@Autowired
	private IOrderMethodService service;
	
	@GetMapping("/register")
	public String showOrderMethod() {		
		return "OrderMethodRegister";
	}
	
	@PostMapping("/save")
	public String saveOrderMethod(@ModelAttribute OrderMethod orderMethod, Model model){
	Integer id = service.saveOrderMethod(orderMethod);
	String msg = "Order Method'"+id+"'created successfully..!";
	model.addAttribute("message", msg);
	return "OrderMethodRegister";
	}
	
	@GetMapping("/all")
	public String getAllOrderMethods(Model model) {
	List<OrderMethod> orderMethod = service.getAllOrderMethod();
	model.addAttribute("list", orderMethod);
	return "OrderMethodData";
}
	@GetMapping("/delete")
	public String deleteOrderMethod(@RequestParam Integer id, Model model) {
		String msg ="";
		try {
		service.deleteOrderMethod(id);
		 msg = "Order Method'"+id+"' Deleted Successfully !";
		List<OrderMethod> list = service.getAllOrderMethod();
		model.addAttribute("list", list);
		model.addAttribute("message", msg);
		}
		catch(OrderMethodNotFoundException e) {
			e.printStackTrace();
			List<OrderMethod> list = service.getAllOrderMethod();
			model.addAttribute("list", list);
			model.addAttribute("message", e.getMessage());
			
		}
		return "OrderMethodData";
	}
	
	@GetMapping("/edit")
	public String editOrderMethod(@RequestParam Integer id, Model model) {
		Optional<OrderMethod> orderMethod = service.editOrderMethod(id);
		model.addAttribute("OrderMethod", orderMethod);
		return "OrderMethodEdit";
	}
	
	@PostMapping("/update")
		public String updateOrderMethod(@ModelAttribute OrderMethod orderMethod, Model model) {
			Integer id = service.updateOrderMethod(orderMethod);
			String msg = "OrderMethod '"+id+"'updated successfully..!";
			model.addAttribute("message", msg);
//			List<OrderMethod> list = service.getAllOrderMethod();
//			model.addAttribute("list", list);
			return "redirect:all";
		}
	
	@GetMapping("/validate")
	@ResponseBody
	public String validateShipmentTypeCode(@RequestParam String orderCode, 
								@RequestParam Integer id) {
		String message="";
		if(id==0 && service.isOrderMethodCodeIsExist(orderCode)) {
			message = orderCode+",already exist !";
		}
		else if(id!=0 && service.isOrderMethodCodeIsExistForEdit(id, orderCode)){
			message = orderCode+", already exist !";
		}
		return message;
	}
	
}

