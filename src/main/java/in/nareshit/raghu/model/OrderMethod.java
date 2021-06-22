package in.nareshit.raghu.model;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="order_Method_tab")
public class OrderMethod {
	@Id
	@Column(name="om_id_col")
	@GeneratedValue(generator="om_gen")
	@SequenceGenerator(name = "om_gen",sequenceName ="om_seq_gen" )
	private Integer id;
	
	@Column(name="om_mode_col")
	private String orderMode;
	
	@Column(name="om_code_col")
	private String orderCode;
	
	@Column(name="om_type_col")
	private String orderType;
	
	@Column(name="om_desc_col")
	private String orderDesc;
	
	@ElementCollection
	@CollectionTable(name="om_actp_tab",
	                joinColumns = @JoinColumn(name="om_id_col"))
	@Column(name="om_actp_col")
	private Set<String> orderAcpt;

}
