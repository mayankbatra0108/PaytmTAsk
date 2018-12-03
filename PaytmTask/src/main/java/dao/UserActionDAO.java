package dao;

import java.util.List;

import entity.ItemEntity;
import entity.VariantEntity;

public interface UserActionDAO {
	
	public List<ItemEntity> getAllItems();
	
	public ItemEntity getItem(Integer ItemId);
	
	public void addItem(ItemEntity item);
	
	public void deleteItem(Integer itemId);
	
	public void updateItem(ItemEntity item);
	
	public List<VariantEntity> getAllVariants();
	
	public VariantEntity getVariant(Integer variantId);
	
	public void addVariant(VariantEntity variant);
	
	public void updateVariant(VariantEntity variant);
	
	public void deleteVariant(Integer variantId);
	
}
