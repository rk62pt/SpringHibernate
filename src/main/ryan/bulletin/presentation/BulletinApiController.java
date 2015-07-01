package main.ryan.bulletin.presentation;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.ryan.bulletin.business.BulletinManager;
import main.ryan.bulletin.business.vo.BulletinVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

@RestController
public class BulletinApiController {
	
	@Autowired
	private BulletinManager bulletinService;
	
	@RequestMapping(value="rest/bulletin/add",method=RequestMethod.POST)
	@ResponseBody
	public BulletinVO bulletin_addData(HttpServletRequest request, HttpServletResponse response){ 
		BulletinVO vo = new BulletinVO();
		String title = WebUtils.findParameterValue(request, "title");
		String description = WebUtils.findParameterValue(request, "description");
		try{
			vo.setTitle(title);
			vo.setDescription(description);
			vo.setCreate_time(new Date());
			vo.setCreator("admin");
			vo = bulletinService.create(vo);
		}catch(Exception e){
			
		}
		
		return vo;
	}
	
	@RequestMapping(value="rest/bulletin/edit",method=RequestMethod.POST)
	@ResponseBody
	public BulletinVO bulletin_editData(HttpServletRequest request, HttpServletResponse response){ 
		BulletinVO vo = new BulletinVO();
		
		try{
			Integer id = Integer.parseInt(WebUtils.findParameterValue(request, "id"));
			String title = WebUtils.findParameterValue(request, "title");
			String description = WebUtils.findParameterValue(request, "description");
			vo.setId(id);
			vo.setTitle(title);
			vo.setDescription(description);
			vo.setCreate_time(new Date());
			vo.setCreator("admin");
			System.out.println(vo);
			vo = bulletinService.modify(vo);
		}catch(Exception e){
			System.out.println(e);
			
		}
		
		return vo;
	}
	
	
}
