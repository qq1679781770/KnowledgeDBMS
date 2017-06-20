package com.jsxnh.kbms.handler;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jsxnh.kbms.dao.CheckingDao;
import com.jsxnh.kbms.dao.UncheckDao;
import com.jsxnh.kbms.entities.Authority;
import com.jsxnh.kbms.entities.Checking;
import com.jsxnh.kbms.service.FindAuthorityService;
import com.jsxnh.kbms.service.PageService;

@Controller
public class UncheckHandler {

	@Autowired
	private PageService pageService;
	@Autowired
	private FindAuthorityService findAuthorityService;
	@Autowired
	private CheckingDao checkingDao;
	@Autowired
	private UncheckDao uncheckDao;
	
	@RequestMapping("/kbms/searchuncheck/{page}")
	public ModelAndView searchUnchek(@PathVariable("page") Integer page,HttpServletRequest request){
		ModelAndView model;
		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("评价资源")){
				model=new ModelAndView("checkresource");
				model.addObject("page", page);
				model.addObject("totalpage", pageService.findUncheckResources((Integer)request.getSession().getAttribute("user_id")));
				model.addObject("uncheckresources", pageService.findUncheckResources((Integer)request.getSession().getAttribute("user_id"), page));
				return model;
			}
		}
		model=new ModelAndView("error");
		return model;
	}
	
	@RequestMapping(value="/kbms/checkresource",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String checkResource(@RequestBody String str){
		JSONObject json=new JSONObject(str);
		Checking checking=new Checking();
		checking.setCheck_id(json.getInt("check_id"));
		checking.setScore(json.getInt("score"));
		checking.setTime(new Date());
		checking.setUser_id(json.getInt("user_id"));
		checkingDao.addChecking(checking);
		uncheckDao.updatecheck_time(json.getInt("check_id"));		
		return new JSONArray().toString();
	}
	
	
}
