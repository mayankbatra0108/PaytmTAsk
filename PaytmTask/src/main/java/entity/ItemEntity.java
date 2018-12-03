package entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ITEM")
public class ItemEntity implements Serializable {
	
	private static final long serialVersionUID = 168824637936553704L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private int itemId;

	@Column(name="NAME")
	private String ItemName;
	
	@Column(name="BRAND")
	private String brand;
	
	@Column(name="CATEGORY")
	private String category;

	@Column(name="PRODUCTCODE")
	private String productCode;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="EMPLOYEE_ID")
	private Set<VariantEntity> variants;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return ItemName;
	}

	public void setItemName(String itemName) {
		ItemName = itemName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Set<VariantEntity> getVariants() {
		return variants;
	}

	public void setVariants(Set<VariantEntity> variants) {
		this.variants = variants;
	}
	
}
