package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dao.UserActionDAO;
import entity.ItemEntity;
import entity.VariantEntity;

@Controller
public class UserActionController {

	@Autowired
	private UserActionDAO userActionDAO;
	
	@RequestMapping(value = "/item/add", method = RequestMethod.POST)
	public ModelAndView addItem(@ModelAttribute ItemEntity item) {
		
		
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/item/update", method = RequestMethod.POST)
    public ModelAndView updateItem(@ModelAttribute ItemEntity item) {
		userActionDAO.updateItem(item);
		
        return new ModelAndView("redirect:/");
    }
	
	@RequestMapping(value = "/item/get", method = RequestMethod.GET)
    public ModelAndView getItem(@ModelAttribute ItemEntity item) {
		
		userActionDAO.getItem(item.getItemId());
		
        return new ModelAndView("redirect:/");
    }
	
	@RequestMapping(value = "/variant/add", method = RequestMethod.GET)
    public ModelAndView addVariant(@ModelAttribute VariantEntity variant) {
		
		userActionDAO.updateVariant(variant);
		
        return new ModelAndView("redirect:/");
    }
}
