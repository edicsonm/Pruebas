import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import au.com.billingbuddy.common.objects.Utilities;


public class ParseDate {
	public static void main(String[] args) {
//			Calendar calendar = Utilities.getCalendarDateSystem("2015-01-10 00:00:00.0");
//			System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
//			Date date = new SimpleDateFormat("dd-MM-yyyy").parse("9-1-2015");
//			System.out.println(Utilities.getSimpleDateFormat().format(date));
		String referencia1 = "3-11-2014";
		String referencia2 = "17-11-2015";
		Date date;
		try {
			System.out.println("referencia1: " + referencia1);
			date = Utilities.getDateFormat(5).parse(referencia1);
			System.out.println("date: " + date);
			System.out.println(Utilities.getDateFormat(2).format(date));
			
			System.out.println("referencia2: " + referencia2);
			date = Utilities.getDateFormat(5).parse(referencia2);
			System.out.println("date: " + date);
			System.out.println(Utilities.getDateFormat(2).format(date));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
