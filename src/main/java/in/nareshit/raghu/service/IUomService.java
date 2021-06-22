package in.nareshit.raghu.service;

import java.util.List;
import java.util.Optional;

import in.nareshit.raghu.model.Uom;

public interface IUomService {
	
	public Integer saveUom(Uom uom);
	
	public List<Uom> getAllUom();
	
	public void deleteUom(Integer id);
	
	public Uom editUom(Integer id);
	
	public Integer updateUom(Uom uom);
	
	public boolean uomModelCount(String uomModel);
	
	public boolean uomModelCountForEdit(String uomModel, Integer id);
}
