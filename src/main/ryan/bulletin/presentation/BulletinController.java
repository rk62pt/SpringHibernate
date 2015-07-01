package main.ryan.bulletin.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.ryan.bulletin.business.BulletinManager;
import main.ryan.bulletin.business.vo.BulletinVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

@Controller
//@RequestMapping("/bulletin")
public class BulletinController {

	@Autowired
	private BulletinManager bulletinService;
	
	@RequestMapping(value="bulletin",method=RequestMethod.GET)
	public String bulletin(){ 
		return "bulletin/bulletin";
	}
	
	@RequestMapping(value="bulletin/content",method=RequestMethod.GET)
	public ModelAndView bulletin_content(){ 
		System.out.println("bulletin");
		List<BulletinVO> voList = new ArrayList<BulletinVO>();
		try{
			voList = bulletinService.list();
		}catch(Exception e){
			System.out.println(e);
		}
		
		ModelAndView modelAndView = new ModelAndView("bulletin/bulletin_content", "voList", voList);
		return modelAndView;
	}
	
	@RequestMapping(value="bulletin/add",method=RequestMethod.GET)
	public String bulletin_add(){ 
		
		return "bulletin/bulletin_add";
	}
	
	@RequestMapping(value="bulletin/edit",method=RequestMethod.POST)
	public ModelAndView bulletin_edit(HttpServletRequest request, HttpServletResponse response){ 
		BulletinVO vo = new BulletinVO();
		try{
			Integer id = Integer.parseInt(WebUtils.findParameterValue(request, "id"));
			vo = bulletinService.load(id);
		}catch(Exception e){
			System.out.println(e);
		}
		ModelAndView modelAndView = new ModelAndView("bulletin/bulletin_edit", "vo", vo);
		return modelAndView;
	}
	
}
