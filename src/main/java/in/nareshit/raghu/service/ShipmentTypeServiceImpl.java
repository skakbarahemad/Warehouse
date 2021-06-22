package in.nareshit.raghu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.exception.ShipmentTypeNotFoundException;
import in.nareshit.raghu.model.ShipmentType;
import in.nareshit.raghu.repo.ShipmentTypeRepository;

@Service
public class ShipmentTypeServiceImpl implements IShipmentTypeService {

	@Autowired
	private ShipmentTypeRepository repo;

	@Override
	public Integer saveShipmentType(ShipmentType st) {
		st = repo.save(st);
		return st.getId();
	}

	@Override
	public List<ShipmentType> getAllShipmentTypes() {
		List<ShipmentType> list = repo.findAll();
		return list;
	}

	@Override
	public void deleteShipmentType(Integer id) {
		repo.deleteById(id);

	}

	@Override
	public ShipmentType getShipmentType(Integer id) {
		Optional<ShipmentType> st = repo.findById(id);
		if (st.isEmpty()) {
			throw new ShipmentTypeNotFoundException("Shipment Type '" + id + "' not exist !");
		} else {
			return st.get();
		}
	}

	@Override
	public Integer updateShipmentType(ShipmentType st) {
		st = repo.save(st);
		return st.getId();
	}

	@Override
	public boolean isShipmentTypeCodeExist(String shipCode) {
		Integer count = repo.getShipmentTypeCodeCount(shipCode);
		boolean isExist = count > 0 ? true : false;
		return isExist;
	}

	@Override
	public boolean isShipmentTypeCodeExistForEdit(String shipCode, Integer id) {
		Integer count = repo.getShipmentTypeCodeCountForEdit(shipCode, id);
		boolean isExist = count > 0 ? true : false;
		return isExist;
	}

}
