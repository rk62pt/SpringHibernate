package main.ryan.message.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {
	
	@RequestMapping(value="message",method=RequestMethod.GET)
	public String bulletin(){ 
		return "message/message";
	}
}
