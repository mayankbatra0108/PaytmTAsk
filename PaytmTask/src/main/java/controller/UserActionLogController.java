package controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.UserActionLogDAOImpl;
import entity.UserActionEntity;

@Controller("/userActionLog")
public class UserActionLogController {
	
	@Autowired
	private UserActionLogDAOImpl userActionDAOImpl;
	
	@RequestMapping(value = "/showLogByTime", method = RequestMethod.GET)
	public ModelAndView getUserActionbyTime(Date d1, Date d2) {
		
		List<UserActionEntity> list=userActionDAOImpl.getUserActionbyTime(d1, d2);
		
		System.out.println(list);
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/showLogByTimeAndId", method = RequestMethod.GET)
	public ModelAndView getUserActionbyTimeAndId(Date d1, Date d2, String userId) {
		
		List<UserActionEntity> list=userActionDAOImpl.getUserActionByTimeAndId(d1, d2, userId);
		
		System.out.println(list);
		
		return new ModelAndView("redirect:/");
	}
	
}
