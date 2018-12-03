package dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;

import entity.ItemEntity;
import entity.VariantEntity;
import utility.HibernateUtil;

public class UserActionDAOImpl implements UserActionDAO{
	
	@Autowired
	private UserActionLogDAOImpl userActionLogDAOImpl;

	public List<ItemEntity> getAllItems() {
		Session session=null;
		List<ItemEntity> itemsList = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query=session.createQuery("FROM ITEM");
			
			itemsList=query.list();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
		
		return itemsList;
	}

	public ItemEntity getItem(Integer ItemId) {
		Session session=null;
		ItemEntity item = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			item=(ItemEntity)session.get(ItemEntity.class,ItemId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
		
		return item;
	}

	public void addItem(ItemEntity item) {
		Session session=null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(item);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
		
	}

	public void deleteItem(Integer itemId) {
		Session session=null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			ItemEntity item = (ItemEntity) session.load(ItemEntity.class, itemId);
			Set<VariantEntity> set=item.getVariants();
			for(VariantEntity ve: set) {
				ve.setItem(null);
			}
			
			if (null != item) {
	            session.delete(item);
	        }
			session.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
		
	}

	public void updateItem(ItemEntity item) {
		Session session=null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			ItemEntity itemBean = (ItemEntity) session.load(ItemEntity.class, item.getItemId());
			
			itemBean.setItemName(item.getItemName());
			itemBean.setCategory(item.getCategory());
			itemBean.setBrand(item.getBrand());
			
			
			userActionLogDAOImpl.logItemUpdation(item);
			
			session.update(itemBean);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
		
		
	}

	public List<VariantEntity> getAllVariants() {
		// TODO Auto-generated method stub
		return null;
	}

	public VariantEntity getVariant(Integer variantId) {
		Session session=null;
		VariantEntity variant = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			variant=(VariantEntity)session.get(VariantEntity.class,variantId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
		
		return variant;	
	}

	public void addVariant(VariantEntity variant) {
		Session session=null;
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		try {
		VariantEntity variantBean= new VariantEntity();
		
		variantBean.setCostPrice(variant.getCostPrice());
		variantBean.setSellingPrice(variant.getSellingPrice());
		variantBean.setQuantity(variant.getQuantity());
		variantBean.setVariantName(variant.getVariantName());
		variantBean.setProperties(variant.getProperties());
		variantBean.setItem(variant.getItem());
		
		session.save(variantBean);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
	}

	public void updateVariant(VariantEntity variant) {
		Session session=null;
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		try {
		VariantEntity variantBean= (VariantEntity) session.load(VariantEntity.class, variant.getVariantId());
		variantBean.setSellingPrice(variantBean.getSellingPrice());
		variantBean.setCostPrice(variantBean.getCostPrice());
		variantBean.setQuantity(variantBean.getQuantity());
		variantBean.setVariantName(variantBean.getVariantName());
		variantBean.setProperties(variantBean.getProperties());
		
		userActionLogDAOImpl.logVariantUpdation(variant);
		
		session.update(variantBean);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
		
		
		session.getTransaction().commit();
	}

	public void deleteVariant(Integer variantId) {
		Session session=null;
		
		session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		String hql = "delete from VariantEntity where variantId= :vid";
		Query query = session.createQuery(hql);
		query.setInteger("variantId", variantId);
		query.executeUpdate();

		session.getTransaction().commit();
	}

	
	
}
