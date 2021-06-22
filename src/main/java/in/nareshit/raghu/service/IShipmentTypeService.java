package in.nareshit.raghu.service;

import java.util.List;

import in.nareshit.raghu.model.ShipmentType;

public interface IShipmentTypeService {
	
	public Integer saveShipmentType(ShipmentType st);
	
	public List<ShipmentType> getAllShipmentTypes();
	
	public void deleteShipmentType(Integer id);
	
	public ShipmentType getShipmentType(Integer id);
	
	public Integer updateShipmentType(ShipmentType st);
	
	public boolean isShipmentTypeCodeExist(String shipCode);
	public boolean isShipmentTypeCodeExistForEdit(String shipCode, Integer id);
	
	
}
