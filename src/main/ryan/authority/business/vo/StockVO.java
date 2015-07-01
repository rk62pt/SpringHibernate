package main.ryan.authority.business.vo;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class StockVO implements Serializable{
	 private static final long serialVersionUID = 1L;
	  private String code = "";
	  public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public DateFormat getDf() {
		return df;
	}

	public void setDf(DateFormat df) {
		this.df = df;
	}

	private double price = 0.0;
	  private Date time = new Date();
	   
	  public StockVO() {
	     
	  }
	   
	  public StockVO(String code, double price) {
	    this.code = code;
	    this.price = price;
	  }
	   
	  private DateFormat df = new SimpleDateFormat("dd MMM yyyy, HH:mm:ss");
	   
	  public String getTimeStr() {
	    return df.format(time);
	  }
	   
	  /* standard getters & setters */
}
