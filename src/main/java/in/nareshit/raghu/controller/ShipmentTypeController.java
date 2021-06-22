package in.nareshit.raghu.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.nareshit.raghu.exception.ShipmentTypeNotFoundException;
import in.nareshit.raghu.model.ShipmentType;
import in.nareshit.raghu.service.IShipmentTypeService;

@Controller
@RequestMapping("/st")
public class ShipmentTypeController  {
		
	private static final Logger log = LoggerFactory.getLogger(ShipmentTypeController.class);
		@Autowired
		private IShipmentTypeService service;
		
		public void commonFetchAll(Model model) {
			List<ShipmentType> st = service.getAllShipmentTypes();
			model.addAttribute("list", st);
		}
		@GetMapping("/register")
		public String showRegister() {
			return "ShipmentTypeRegister";
		}
		
		@PostMapping("/save")
		public String saveShipmentType(@ModelAttribute ShipmentType shipmentType,
				Model model) {
				log.info("Entered into save method");
				Integer id = service.saveShipmentType(shipmentType);		
				String msg = "Shipment Type'"+id+"'is created";
				model.addAttribute("message", msg);
				log.debug("Data Saved");
				log.info("Data saved");
				return "ShipmentTypeRegister";
		}
		
		@GetMapping("/all")
		public String fetchShipmentType(Model model) {
//			List<ShipmentType> list = service.getAllShipmentTypes();
//			model.addAttribute("list", list);
			commonFetchAll(model);
			return "ShipmentTypeData";
			
		}
		
		@GetMapping("/delete")
		public String deleteShipmentType(@RequestParam Integer id, Model model) {
			service.deleteShipmentType(id);
			String msg = "Shipment Type '"+id+"'Deleted !";
//			List<ShipmentType> list=service.getAllShipmentTypes();
			commonFetchAll(model);
			model.addAttribute("message", msg);
//			model.addAttribute("list", list);
		
			return "ShipmentTypeData";
		}
		
		@GetMapping("/edit")
		public String editShipmentType(@RequestParam Integer id, Model model) {
			String page = null;
			try {
				ShipmentType st = service.getShipmentType(id);
				model.addAttribute("ShipmentType", st);
				page = "ShipmentTypeEdit";
			}
			catch(ShipmentTypeNotFoundException e) {
				e.printStackTrace();
				page = "ShipmentTypeData";
				model.addAttribute("message", e.getMessage());
				commonFetchAll(model);
//				List<ShipmentType> list = service.getAllShipmentTypes();
//				model.addAttribute("list", list);
				
			}
			return page;
		}
		
		@PostMapping("/update")
		public String updateShipmentType(@ModelAttribute ShipmentType shipmentType, Model model) {
			Integer id = service.updateShipmentType(shipmentType);
			String msg ="Shipment Type'"+id+"' Updated Successfully !";
			model.addAttribute("message", msg);
			return "redirect:all";
		}
		
		@GetMapping("/validate")
		@ResponseBody
		public String validateShipmentTypeCode(@RequestParam String shipCode, 
									@RequestParam Integer id) {
			String message="";
			if(id==0 && service.isShipmentTypeCodeExist(shipCode)) {
				message = shipCode+",already exist !";
			}
			else if(id!=0 && service.isShipmentTypeCodeExistForEdit(shipCode, id)) {
				message = shipCode+", already exist !";
			}
			return message;
		}
}
