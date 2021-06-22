package in.nareshit.raghu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.exception.UomNotFoundException;
import in.nareshit.raghu.model.Uom;
import in.nareshit.raghu.repo.UomRepository;

@Service
public class UomServiceImpl implements IUomService {
	@Autowired
	private UomRepository repo;
	
	public String uomModelCounts(String uomModel) {
		Integer count = repo.countOfUomModel(uomModel);
		String str =""; 
		
		return str.isEmpty() ? "empty": "sdasd" ;
	}

	@Override
	public Integer saveUom(Uom uom) {
		 uom = repo.save(uom);
		 return uom.getUomId();
	}

	@Override
	public List<Uom> getAllUom() {
		List<Uom> list = repo.findAll();
		return list;
	}

	@Override
	public void deleteUom(Integer id) {
		if(repo.findById(id).isPresent()) {
			repo.deleteById(id);
		}
		else {
			throw new UomNotFoundException("Uom '"+id+"' not exist !");
		}
			
		
	}

	@Override
	public Uom editUom(Integer id) {
		Optional<Uom> list = repo.findById(id);
		if(list.isEmpty()) {
			throw new UomNotFoundException("Uom '"+id+"' not Exist !");
		}
		else {
			return list.get();
		}
		
	}
	
	public Integer updateUom(Uom uom){
		repo.save(uom);
		return uom.getUomId();
	}

	@Override
	public boolean uomModelCount(String uomModel) {
		Integer count = repo.countOfUomModel(uomModel);
		boolean isExist = count>0?true:false;
		return isExist;
	}

	@Override
	public boolean uomModelCountForEdit(String uomModel, Integer id) {
		Integer count = repo.countOfUomModelForEdit(uomModel, id);
		boolean isExist = count>0?true:false;
		
		return isExist;
	}
	
	
}
