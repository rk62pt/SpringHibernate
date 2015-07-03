package main.ryan.authority.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import main.ryan.authority.business.vo.MessageVO;
import main.ryan.authority.business.vo.StockVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.sockjs.client.WebSocketClientSockJsSession;

@Controller
public class AuthorityController {
	@Autowired 
	private SimpMessagingTemplate template;
	private TaskScheduler scheduler = new ConcurrentTaskScheduler();
	private List<MessageVO> msgs = new ArrayList<MessageVO>();
	private Random rand = new Random(System.currentTimeMillis());
    
	
	
	private void updateMessageAndBroadcast() {
//	    for(MessageVO msg : msgs) {
//	      double chgPct = rand.nextDouble() * 5.0;
//	      if(rand.nextInt(2) == 1) chgPct = -chgPct;
//	      stock.setPrice(stock.getPrice() + (chgPct / 100.0 * stock.getPrice()));
//	      stock.setTime(new Date());
//	    }
	    template.convertAndSend("/topic/msg", msgs);
	  }
	   
	  /**
	   * Invoked after bean creation is complete, this method will schedule 
	   * updatePriceAndBroacast every 1 second
	   */
	 // @PostConstruct
	 // private void broadcastTimePeriodically() {
	    //scheduler.scheduleAtFixedRate(new Runnable() {
	     // @Override public void run() {
	        //updatePriceAndBroadcast();
	     // }
	    //}, 1000);
	  //}
	   
	 
	  
	  @MessageMapping("/addMsg")
	  public void addMsg(SimpMessageHeaderAccessor headerAccessor,MessageVO msg) throws Exception {
		Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
		System.out.println(sessionAttributes.get("HTTP.SESSION.ID"));  
	    msgs.add(msg);
	    updateMessageAndBroadcast();
	  }
	  
	  @MessageMapping("/updateMsg")
	  public void updateMsg() throws Exception {
		updateMessageAndBroadcast();
	  }
	  
	 
	
	@RequestMapping(value="index",method=RequestMethod.GET)
	public String mainPage(){
		
		return "index";
	}
	
	@RequestMapping(value="initContent",method=RequestMethod.GET)
	public String initContent(){
		
		return "authority/initContent";
	}
}
