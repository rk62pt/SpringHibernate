package main.ryan.authority.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthorityController {
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String mainPage(){
		
		return "index";
	}
	
	@RequestMapping(value="initContent",method=RequestMethod.GET)
	public String initContent(){
		
		return "authority/initContent";
	}
}
