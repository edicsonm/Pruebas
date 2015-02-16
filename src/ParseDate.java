import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import au.com.billingbuddy.common.objects.Utilities;


public class ParseDate {
	public static void main(String[] args) {
			Calendar calendar = Utilities.getCalendarDateSystem("2015-01-10 00:00:00.0");
			System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
//			Date date = new SimpleDateFormat("dd-MM-yyyy").parse("9-1-2015");
//			System.out.println(Utilities.getSimpleDateFormat().format(date));
	}
	
}
