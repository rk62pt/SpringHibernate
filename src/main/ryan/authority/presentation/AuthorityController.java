package main.ryan.authority.presentation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import main.ryan.authority.business.vo.StockVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthorityController {
	@Autowired 
	private SimpMessagingTemplate template;
	private TaskScheduler scheduler = new ConcurrentTaskScheduler();
	private List<StockVO> stockPrices = new ArrayList<StockVO>();
	private Random rand = new Random(System.currentTimeMillis());
	
	private void updatePriceAndBroadcast() {
	    for(StockVO stock : stockPrices) {
	      double chgPct = rand.nextDouble() * 5.0;
	      if(rand.nextInt(2) == 1) chgPct = -chgPct;
	      stock.setPrice(stock.getPrice() + (chgPct / 100.0 * stock.getPrice()));
	      stock.setTime(new Date());
	    }
	    template.convertAndSend("/topic/price", stockPrices);
	  }
	   
	  /**
	   * Invoked after bean creation is complete, this method will schedule 
	   * updatePriceAndBroacast every 1 second
	   */
	  @PostConstruct
	  private void broadcastTimePeriodically() {
	    scheduler.scheduleAtFixedRate(new Runnable() {
	      @Override public void run() {
	        //updatePriceAndBroadcast();
	      }
	    }, 1000);
	  }
	   
	  /**
	   * Handler to add one stock
	   */
	  @MessageMapping("/addStock")
	  public void addStock(StockVO stock) throws Exception {
	    stockPrices.add(stock);
	    updatePriceAndBroadcast();
	  }
	  
	  /**
	   * Handler to add one stock
	   */
	  @MessageMapping("/updateStock")
	  public void updateStock() throws Exception {
	    updatePriceAndBroadcast();
	  }
	   
	  /**
	   * Handler to remove all stocks
	   */
	  @MessageMapping("/removeAllStocks")
	  public void removeAllStocks() {
	    stockPrices.clear();
	    updatePriceAndBroadcast();
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
