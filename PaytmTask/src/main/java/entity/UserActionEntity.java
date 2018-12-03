package entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="USER_ACTION")
public class UserActionEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7278968552842043491L;
	
	@Column(name="ITEM_ID")
	private Integer itemId;
	
	@Column(name="VARIANT_ID")
	private Integer variantId;
	
	@Column(name="FIELD")
	private String field;
	
	@Column(name="OLD_VALUE")
	private String oldValue;
	
	@Column(name="NEW_VALUE")
	private String newValue;
	
	@Column(name="ROW_UPD_ID")
	private String userId;
	
	@Column(name="ROW_UPD_TS")
	private Date rowUpdTs;
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getVariantId() {
		return variantId;
	}
	public void setVariantId(Integer variantId) {
		this.variantId = variantId;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getRowUpdTs() {
		return rowUpdTs;
	}
	public void setRowUpdTs(Date rowUpdTs) {
		this.rowUpdTs = rowUpdTs;
	}
	
}
