package utilidades;

import java.util.Date;

import org.joda.time.DateTime;

import lombok.Getter;

@Getter
public class Data {
	private DateTime data;
	
	public Data(Date d){
		this.data = new DateTime(d);
	}
	
	public Data(){
//		inicia com a hora atual
		this.data = new DateTime(new Date());
	}
}
