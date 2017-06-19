package com.jsxnh.kbms.handler;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jsxnh.kbms.dao.DomainDao;
import com.jsxnh.kbms.dao.ModuleDao;
import com.jsxnh.kbms.entities.Authority;
import com.jsxnh.kbms.entities.Domain;
import com.jsxnh.kbms.entities.Module;
import com.jsxnh.kbms.service.FindAuthorityService;

@Controller
public class DomainHandler {

	@Autowired
	private FindAuthorityService findAuthorityService;
	@Autowired
	private DomainDao domainDao;
	@Autowired
	private ModuleDao moduleDao;
	
	@RequestMapping(value="/kbms/showadddomain",method=RequestMethod.GET)
	public ModelAndView showAddauthority(HttpServletRequest request){
		ModelAndView model;
		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("添加领域")){
				model=new ModelAndView("adddomain");
				return model;
			}
		}
		model=new ModelAndView("error");
		return model;
	}
	
	@RequestMapping(value="/kbms/adddomain",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String addDomain(@RequestBody String str){
		JSONArray json=new JSONArray();
		JSONObject domainjson=new JSONObject(str);
		Domain domain=new Domain();
		domain.setDomain(domainjson.getString("domain"));
		domain.setDescription(domainjson.getString("description"));
		domain.setCreate_id(domainjson.getInt("user_id"));
		domain.setLast_time(new Date());
		domainDao.addDomain(domain);
		json.put("Y");
		return json.toString();
	}
	
	@RequestMapping(value="/kbms/showmodifydomain",method=RequestMethod.GET)
	public ModelAndView showModifyDomain(HttpServletRequest request){
		ModelAndView model;
		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("修改领域")){
				model=new ModelAndView("modifydomain");
				List<Domain> domains=domainDao.listAllDomain();
				JSONArray json=new JSONArray();
				for(Domain domain:domains){
					JSONObject item=new JSONObject();
					item.put("id", domain.getId());
					item.put("domain", domain.getDomain());
					item.put("description", domain.getDescription());
					json.put(item);
				}
				model.addObject("domains", json.toString());
				return model;
			}
		}
		model=new ModelAndView("error");
		return model;
	}
	
	@RequestMapping(value="/kbms/modifydomain",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String modifyDomain(@RequestBody String str){
		JSONArray json=new JSONArray();
		JSONObject domainjson=new JSONObject(str);
		Domain domain=domainDao.finddomainByid(domainjson.getInt("id"));
		if(!domain.getDescription().equals(domainjson.getString("description"))){
			domainDao.updateDescription(domainjson.getString("description"),domainjson.getInt("user_id"), domainjson.getInt("id"));
		}
		if(!domain.getDomain().equals(domainjson.getString("domain"))){
			domainDao.updateDomain(domainjson.getString("domain"), domainjson.getInt("user_id"), domainjson.getInt("id"));
		}		
		json.put("Y");
		return json.toString();
	}
	
	@RequestMapping(value="/kbms/findmodulebydomain",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String findModuleBydomain(@RequestParam("domain") String domain){
		JSONArray json=new JSONArray();
		Domain dm=domainDao.finddomainBydomain(domain);
		List<Module> modules=moduleDao.findModuleByDomain(dm.getId());
		for(Module module:modules){
			json.put(module.getModule());
		}
		return json.toString();
	}
	
	@RequestMapping(value="/finddomains",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String finddomains(){
		JSONArray json=new JSONArray();
		List<Domain> dms=domainDao.listAllDomain();
		for(Domain dm:dms){
			json.put(dm.getDomain());
		}
		return json.toString();
	}
	
	@RequestMapping(value="/finddomainsbyid",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String finddomainsByid(@RequestParam("id") Integer id){
		JSONObject json=new JSONObject();
		Domain domain=domainDao.finddomainByid(id);
		json.put("domain", domain.getDomain());
		json.put("description", domain.getDescription());
		return json.toString();
	}
}
