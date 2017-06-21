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

import com.jsxnh.kbms.dao.AttributeDao;
import com.jsxnh.kbms.dao.DomainDao;
import com.jsxnh.kbms.dao.ModuleDao;
import com.jsxnh.kbms.dao.RattributeDao;
import com.jsxnh.kbms.dao.ResourceDao;
import com.jsxnh.kbms.dao.UncheckDao;
import com.jsxnh.kbms.entities.Authority;
import com.jsxnh.kbms.entities.Domain;
import com.jsxnh.kbms.entities.Module;
import com.jsxnh.kbms.entities.Rattribute;
import com.jsxnh.kbms.entities.Resource;
import com.jsxnh.kbms.entities.Uncheck;
import com.jsxnh.kbms.service.FindAuthorityService;
import com.jsxnh.kbms.service.PageService;

@Controller
public class ResourceHandler {

	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private FindAuthorityService findAuthorityService;
	@Autowired
	private DomainDao domainDao;
	@Autowired
	private ModuleDao moduleDao;
	@Autowired
	private UncheckDao uncheckDao;
	@Autowired
	private PageService pageService;
	@Autowired
	private RattributeDao rattributeDao;
	@Autowired
	private AttributeDao attributeDao;
	
	@RequestMapping(value="/resourcenum",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String Domainnum(){
		JSONArray json=new JSONArray();
		json.put(resourceDao.listAllResource().size());
		return json.toString();
	}
	
	@RequestMapping(value="/kbms/showuploadresource",method=RequestMethod.GET)
	public ModelAndView showUploadResource(HttpServletRequest request){
		ModelAndView model;
		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("上传资源")){
				model=new ModelAndView("uploadresource");
				List<Domain> domains=domainDao.listAllDomain();
				JSONArray json=new JSONArray();
				for(Domain domain:domains){
					json.put(domain.getDomain());
				}
				model.addObject("domains", json);
				return model;
			}
		}
		model=new ModelAndView("error");
		return model;
	}
	
	@RequestMapping(value="/kbms/showaddresource",method=RequestMethod.GET)
	public ModelAndView showAddResource(HttpServletRequest request){
		ModelAndView model;
		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("添加资源")){
				model=new ModelAndView("addresource");
				List<Domain> domains=domainDao.listAllDomain();
				JSONArray json=new JSONArray();
				for(Domain domain:domains){
					json.put(domain.getDomain());
				}
				model.addObject("domains", json);
				return model;
			}
		}
		model=new ModelAndView("error");
		return model;
	}
	
	@RequestMapping(value="/kbms/uploadresource",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String uploadResource(@RequestBody String str){
		JSONArray res=new JSONArray();
		JSONObject json=new JSONObject(str);
		Uncheck uncheck=new Uncheck();
		uncheck.setCheck_time(0);
		uncheck.setCreate_id(json.getInt("user_id"));
		uncheck.setIs_check(0);
		uncheck.setResource(json.getString("resource"));
		uncheck.setDomain_id(domainDao.finddomainBydomain(json.getString("domain")).getId());
		uncheck.setModule_id(moduleDao.findModulebymodule(json.getString("module")).getId());
		uncheck.setTitle(json.getString("title"));
		uncheck.setCreate_time(new Date());
		uncheckDao.addUncheck(uncheck);
		res.put("等待审核");
		return res.toString();
	}
	
	@RequestMapping(value="/kbms/addresource",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String addResource(@RequestBody String str){
		JSONArray res=new JSONArray();
		JSONObject json=new JSONObject(str);
		Resource resource=new Resource();
		resource.setContent(json.getString("resource"));
		resource.setCreate_id(json.getInt("user_id"));
		resource.setLast_time(new Date());
		resource.setSummary(json.getString("title"));
		resource.setDomain_id(domainDao.finddomainBydomain(json.getString("domain")).getId());
		resource.setModule_id(moduleDao.findModulebymodule(json.getString("module")).getId());
		resourceDao.addResource(resource);
		res.put("添加成功");
		return res.toString();
	}
	
	@RequestMapping("/searchresource")
	public ModelAndView searchResource(HttpServletRequest request){
		ModelAndView model=new ModelAndView("searchresource");
		return model;
	}
	
	@RequestMapping(value="/searchresource/domain/{id}",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String searchDomian(@PathVariable("id") Integer id,HttpServletRequest request){
		JSONObject res=new JSONObject();
		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
		res.put("is_modify", false);
		res.put("is_delete", false);
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("修改资源")){
				res.put("is_modify", true);
				break;
			}
		}
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("删除资源")){
				res.put("is_delete", true);
				break;
			}
		}		
		res.put("totalpage", pageService.findResourceBydomain(domainDao.finddomainBydomain(request.getParameter("domain")).getId()));
		res.put("resources", pageService.findResourceBydomian(domainDao.finddomainBydomain(request.getParameter("domain")).getId(), id));
		res.put("page", id);
		return res.toString();
	}
	
	@RequestMapping(value="/searchresource/module/{id}",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String searchModule(@PathVariable("id") Integer id,HttpServletRequest request){
		JSONObject res=new JSONObject();
		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
		res.put("is_modify", false);
		res.put("is_delete", false);
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("修改资源")){
				res.put("is_modify", true);
				break;
			}
		}
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("删除资源")){
				res.put("is_delete", true);
				break;
			}
		}		
		res.put("totalpage", pageService.findResourceBymodule(moduleDao.findModulebymodule(request.getParameter("module")).getId()));
		res.put("resources", pageService.findResourceBymodule(moduleDao.findModulebymodule(request.getParameter("module")).getId(), id));
		res.put("page", id);
		return res.toString();
	}
	
	@RequestMapping(value="/searchresource/attribute/{id}",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody String searchAttribute(@PathVariable("id") Integer id,HttpServletRequest request){
		JSONObject res=new JSONObject();
		List<Authority> authorities=findAuthorityService.findAuthority((Integer)request.getSession().getAttribute("user_id"));
		res.put("is_modify", false);
		res.put("is_delete", false);
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("修改资源")){
				res.put("is_modify", true);
				break;
			}
		}
		for(Authority authority:authorities){
			if(authority.getAuthority().equals("删除资源")){
				res.put("is_delete", true);
				break;
			}
		}
		String attribute=request.getParameter("attribute");
		String value=request.getParameter("value");
		res.put("totalpage", pageService.findResourceByattribute(attribute, value));
		res.put("resources", pageService.findResourceByattribute(attribute, value, id));
		res.put("page", id);
		return res.toString();
	}
	
	
	@RequestMapping("/lookresource/{id}")
	public ModelAndView lookResource(@PathVariable("id") Integer id){
		ModelAndView model=new ModelAndView("lookresource");
		Resource resource=resourceDao.findResourceByid(id);
		model.addObject("resource", resource.getContent());
		model.addObject("domain", domainDao.finddomainByid(resource.getDomain_id()).getDomain());
		model.addObject("module",moduleDao.findModulebyId(resource.getModule_id()).getModule());
		model.addObject("create_id", resource.getCreate_id());
		model.addObject("title", resource.getSummary());
		model.addObject("last_time", resource.getLast_time());
		List<Rattribute> rattributes=rattributeDao.findRattributeByResource(id);
		JSONArray json=new JSONArray();
		for(Rattribute rattribute:rattributes){
			JSONObject item=new JSONObject();
			item.put("key", attributeDao.findAttributebyId(rattribute.getAttribute_id()).get(0).getAttribute());
			item.put("value", rattribute.getValue());
			json.put(item);
		}
		model.addObject("attributes", json);
		return model;
	}
	
	@RequestMapping("/kbms/deleteresource/{id}")
	public @ResponseBody String deleteResurce(@PathVariable("id") Integer id){
		JSONArray json=new JSONArray();
		resourceDao.deleteResource(id);
		return json.toString();
	}
	
	@RequestMapping("/kbms/showmodifyresource/{id}")
	public ModelAndView showmodifyResource(@PathVariable("id") Integer id){
		ModelAndView model=new ModelAndView("modifyresource");
		Resource resource=resourceDao.findResourceByid(id);
		model.addObject("resource_id",resource.getId());
		model.addObject("resource", resource.getContent());
		model.addObject("domain", domainDao.finddomainByid(resource.getDomain_id()).getDomain());
		model.addObject("module",moduleDao.findModulebyId(resource.getModule_id()).getModule());
		model.addObject("create_id", resource.getCreate_id());
		model.addObject("title", resource.getSummary());
		model.addObject("last_time", resource.getLast_time());		
		List<Domain> domains=domainDao.listAllDomain();
		JSONArray jsondomain=new JSONArray();
		for(Domain domain:domains){
			jsondomain.put(domain.getDomain());
		}
		model.addObject("domains", jsondomain);		
		List<Module> modules=moduleDao.findModuleByDomain(resource.getDomain_id());
		JSONArray jsonmodule=new JSONArray();
		for(Module module:modules){
			jsonmodule.put(module.getModule());
		}
		model.addObject("modules", jsonmodule);		
		List<Rattribute> rattributes=rattributeDao.findRattributeByResource(id);
		JSONArray json=new JSONArray();
		for(Rattribute rattribute:rattributes){
			JSONObject item=new JSONObject();
			item.put("id", rattribute.getId());
			item.put("key", attributeDao.findAttributebyId(rattribute.getAttribute_id()).get(0).getAttribute());
			item.put("value", rattribute.getValue());
			json.put(item);
		}
		model.addObject("attributes", json);
		return model;
	}
	
	@RequestMapping(value="/kbms/updateresourcemodelanddomain",method=RequestMethod.POST,produces="application/json;charset=UTF-8",consumes = "application/json")
	public @ResponseBody String updateresourceModelAndDomain(@RequestBody String str){
		JSONObject json=new JSONObject(str);
		Integer resource_id=json.getInt("resource_id");
		Integer module_id=moduleDao.findModulebymodule(json.getString("module")).getId();
		Integer domain_id=domainDao.finddomainBydomain(json.getString("domain")).getId();
		Integer create_id=json.getInt("create_id");
		resourceDao.updateDomain(domain_id, module_id, resource_id, create_id);
		return new JSONArray().toString();
	}
}
