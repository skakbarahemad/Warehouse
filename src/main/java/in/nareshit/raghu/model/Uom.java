package in.nareshit.raghu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="Uom_tab")

public class Uom {
	@Id
	@Column(name="uom_id_col")
	@GeneratedValue
	private Integer uomId;
	@Column(name="uom_type_col")
	private String uomType;
	@Column(name="uom_model_col")
	private String uomModel;
	@Column(name="uom_desc_col")
	private String uomDesc;
	

}
