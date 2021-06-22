package in.nareshit.raghu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.nareshit.raghu.exception.UomNotFoundException;
import in.nareshit.raghu.model.Uom;
import in.nareshit.raghu.service.IUomService;

@Controller
@RequestMapping("/uom")
public class UomController {
	@Autowired
	IUomService service;
	
	public void commonFetchAll(Model model) {
		List<Uom> uom = service.getAllUom();
		model.addAttribute("list",uom);
	}
	@GetMapping("/register")
	public String showUomRegister() {
		return "UomRegister";
	}
	
	@PostMapping("/save")
	public String saveUomType(@ModelAttribute Uom uom, Model model) {
		Integer id = service.saveUom(uom);
		String msg = "Uom '"+id+"'is created !";
		model.addAttribute("message",msg);
		return "UomRegister";
	}
	
	@GetMapping("/all")
	public String getAllUom(Model model){
//		List<Uom> list = service.getAllUom();
//		model.addAttribute("list", list);
		commonFetchAll(model);
		return "UomData";
	}
	
	@GetMapping("/delete")
	public String deleteUom(@RequestParam Integer id, Model model) {
		try{
			service.deleteUom(id);
			String msg = "Uom Record '"+id+"' Deleted !";
			List<Uom> list = service.getAllUom();
			model.addAttribute("list", list);
			model.addAttribute("message", msg);
			return "UomData";
		}
		catch(UomNotFoundException e) {
			e.printStackTrace();
			List<Uom> list = service.getAllUom();
			model.addAttribute("list", list);
			model.addAttribute("message", e.getMessage());
			return "UomData";
		}
		
	}
	
	@GetMapping("/edit")
	public String editUom(@RequestParam Integer id, Model model) {
		String page ="";
		try {
			Uom uom = service.editUom(id);
			model.addAttribute("Uom", uom);
			page="UomEdit";
		}
		catch(UomNotFoundException e) {
			e.printStackTrace();
			model.addAttribute("message", e.getMessage());
			page="UomData";
		}
		return page;
	}
	
	@PostMapping("/update")
	public String updateUom(@ModelAttribute Uom uom, Model model) {
		Integer id = service.updateUom(uom);
		List<Uom> list=service.getAllUom();
		model.addAttribute("list", list);
		String msg = "Uom '"+id+"' updated successfully !";
		model.addAttribute("message",msg);
		return "UomData";
	}
	
	@GetMapping("/validate")
	@ResponseBody
	public String uomModelExist(@RequestParam String uomModel, 
								@RequestParam Integer id) {
		String msg="";
		if(id==0 && service.uomModelCount(uomModel))
			msg= uomModel + " already exist !";
		else if(id!=0 && service.uomModelCountForEdit(uomModel, id))
			msg = uomModel + " already exist !";
		
		return msg;
	}
}
