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
public class ModuleHandler {

	@Autowired
	private FindAuthorityService findAuthorityService;
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private DomainDao domainDao;
	
	@RequestMapping(value="/kbms/showaddmodule",method=RequestMethod.GET)
	public ModelAndView showAddauthority(HttpServletRequest request){
		ModelAndView model;
		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("添加模块")){
				model=new ModelAndView("addmodule");
				JSONArray json=new JSONArray();
				List<Domain> domains=domainDao.listAllDomain();
				for(Domain domain:domains){
					json.put(domain.getDomain());
				}
				model.addObject("domains", json.toString());
				return model;
			}
		}
		model=new ModelAndView("error");
		return model;
	}
	
	@RequestMapping(value="/kbms/addmodule",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String addDomain(@RequestBody String str){
		JSONArray json=new JSONArray();
		JSONObject modulejson=new JSONObject(str);
		Module module=new Module();
		module.setDescription(modulejson.getString("description"));
		module.setCreate_id(modulejson.getInt("user_id"));
		module.setModule(modulejson.getString("module"));
		module.setLast_time(new Date());
		module.setDomain_id(domainDao.finddomainBydomain(modulejson.getString("domain")).getId());
		moduleDao.addModule(module);
		json.put("Y");
		return json.toString();
	}
	
	@RequestMapping(value="/kbms/showmodifymodule",method=RequestMethod.GET)
	public ModelAndView showModifyDomain(HttpServletRequest request){
		ModelAndView model;
		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("修改模块")){
				model=new ModelAndView("modifymodule");
				JSONArray json=new JSONArray();
				List<Domain> domains=domainDao.listAllDomain();
				for(Domain domain:domains){
					json.put(domain.getDomain());
				}
				model.addObject("domains", json.toString());
				
				JSONArray json2=new JSONArray();
				List<Module> modules=moduleDao.listAllModule();
				for(Module module:modules){
					json2.put(module.getId());
				}
 				model.addObject("modules", json2.toString());
				return model;
			}
		}
		model=new ModelAndView("error");
		return model;
	}
	
	@RequestMapping(value="/kbms/modifymodule",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String modifyModule(@RequestBody String str){
		JSONArray json=new JSONArray();
		JSONObject modulejson=new JSONObject(str);
		Module module=new Module();
		module.setDescription(modulejson.getString("description"));
		module.setCreate_id(modulejson.getInt("user_id"));
		module.setModule(modulejson.getString("module"));
		module.setLast_time(new Date());
		module.setId(modulejson.getInt("id"));
		module.setDomain_id(domainDao.finddomainBydomain(modulejson.getString("domain")).getId());
		moduleDao.update(module);
		json.put("Y");
		return json.toString();
	}
	
	@RequestMapping(value="/findmodules",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String findModules(){
		JSONArray json=new JSONArray();
		List<Module> modules=moduleDao.listAllModule();
		for(Module module:modules){
			json.put(module.getModule());
		}
		return json.toString();
	}
	
	@RequestMapping(value="/findmodulebyid",method=RequestMethod.GET,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String findModuleByid(@RequestParam("id") Integer id){
		JSONObject json=new JSONObject();
		Module module=moduleDao.findModulebyId(id);
		json.put("module", module.getModule());
		json.put("description", module.getDescription());
		json.put("domain", domainDao.finddomainByid(module.getDomain_id()).getDomain());
		return json.toString();
	}
}
