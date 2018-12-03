package dao;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import entity.ItemEntity;
import entity.MyJsonObject;
import entity.UserActionEntity;
import entity.VariantEntity;
import utility.HibernateUtil;

public class UserActionLogDAOImpl {
	@Autowired
	private UserActionDAOImpl itemDAOImpl;
	
	public void logItemUpdation(ItemEntity newItem) {
		Session session=null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			ItemEntity oldItem=itemDAOImpl.getItem(newItem.getItemId());
			JSONObject jsonOld= new JSONObject();
			JSONObject jsonNew= new JSONObject();
			
			if(!oldItem.getItemName().equals(newItem.getItemName())) {
				jsonOld.put("itemName",oldItem.getItemName());
				jsonNew.put("itemName", newItem.getItemName());
			}
			if(!oldItem.getCategory().equals(newItem.getCategory())) {
				jsonOld.put("category", oldItem.getCategory());
				jsonNew.put("category", newItem.getCategory());
			}
			if(!oldItem.getBrand().equals(newItem.getBrand())) {
				jsonOld.put("brand", oldItem.getCategory());
				jsonNew.put("brand", newItem.getBrand());
			}
			StringWriter out = new StringWriter();
			jsonOld.write(out);
			String jsonOldText = out.toString();
			jsonNew.write(out);
			String jsonNewText = out.toString();
			
		      
			UserActionEntity uae= new UserActionEntity();
			uae.setItemId(newItem.getItemId());
			uae.setVariantId(null);
			uae.setField("field");
			uae.setOldValue(jsonOldText);
			uae.setNewValue(jsonNewText);
			uae.setUserId("iny2593");
			uae.setRowUpdTs(new java.util.Date());
			
			session.save(uae);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
	}
	
	public MyJsonObject logVariantUpdation(VariantEntity newVariant) {
		Session session=null;
		MyJsonObject js=null;
		try {
			VariantEntity oldVariant=itemDAOImpl.getVariant(newVariant.getVariantId());
			
			JSONObject jsonOld= new JSONObject();
			JSONObject jsonNew= new JSONObject();
			
			if(!oldVariant.getVariantName().equals(newVariant.getVariantName())) {
				jsonOld.put("variantName",oldVariant.getVariantName());
				jsonNew.put("variantName", newVariant.getVariantName());
			}
			if(oldVariant.getSellingPrice()!=newVariant.getSellingPrice()) {
				jsonOld.put("sellingPrice", oldVariant.getSellingPrice());
				jsonNew.put("sellingPrice", newVariant.getSellingPrice());
			}
			if(oldVariant.getCostPrice()!=newVariant.getCostPrice()) {
				jsonOld.put("costPrice", oldVariant.getCostPrice());
				jsonNew.put("costPrice", newVariant.getCostPrice());
			}
			if(oldVariant.getQuantity()!=newVariant.getQuantity()) {
				jsonOld.put("quantity", oldVariant.getQuantity());
				jsonNew.put("quantity", newVariant.getQuantity());
			}
			
			if(!oldVariant.getProperties().equals(newVariant.getProperties())) {
				jsonOld.put("properties", oldVariant.getProperties());
				jsonNew.put("properties", newVariant.getProperties());
			}
			
			js= new MyJsonObject();
			
			StringWriter out = new StringWriter();
			jsonOld.write(out);
			String jsonOldText = out.toString();
			jsonNew.write(out);
			String jsonNewText = out.toString();
			
			js.setOldJson(jsonOldText);
			js.setNewJson(jsonNewText);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
		return js;
}
	public List<UserActionEntity> getUserActionbyTime(Date startTime, Date endTime) {
		Session session=null;
		List<UserActionEntity> userActionsList = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			
			Query query=session.createQuery("FROM UserActionEntity ua where ua.rowUpdTs>=:stime and ua.rowUpdTs<=:etime GROUP BY ua.userId,ua.rowUpdTs");
			query.setParameter("stime", startTime);
			query.setParameter("etime", endTime);
			
			userActionsList=query.list();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
		
		return userActionsList;
	}
	public List<UserActionEntity> getUserActionByTimeAndId(Date startTime, Date endTime, String userId) {
		Session session=null;
		List<UserActionEntity> userActionsList = null;
		
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			session.beginTransaction();
			Query query=session.createQuery("FROM UserActionEntity ua where ua.userId:=uid and ua.rowUpdTs>=:stime and ua.rowUpdTs<=:etime GROUP BY ua.userId,ua.rowUpdTs");
			query.setParameter("stime", startTime);
			query.setParameter("etime", endTime);
			query.setParameter("uid", userId);
			
			userActionsList=query.list();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(session != null) session.close();
		}
		
		return userActionsList;
	}
}