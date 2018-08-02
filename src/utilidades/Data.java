package utilidades;

import java.util.Calendar;
import org.joda.time.DateTime;

public class Data {
	DateTime hoje;
	
	public Data(){
		 Calendar cal = Calendar.getInstance();
		 this.hoje = new DateTime(cal);
	}
	
	public DateTime getData(){
		return hoje;
	}
}
