import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MySQLPattern {

	public MySQLPattern() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
//		String string = "var1'value1', var2'value2', var3'value3'";
//		String string = "Duplicate entry '%s' for key %d ";
		String string = "Duplicate entry '2' for key 'Merc_ID_UNIQUE'";
        Pattern pattern = Pattern.compile("(\\')(.*?)(\\')");
        Matcher matcher = pattern.matcher(string);
//        if(matcher.find())System.out.println(matcher.group(0));
//        if(matcher.find())System.out.println(matcher.group(0));
        
        String value = (matcher.find() ? matcher.group(0): "");
        String sqlObjectName = (matcher.find() ? matcher.group(0): "");
        System.out.println(value.replace("'", ""));
        System.out.println(sqlObjectName.replace("'", ""));
//        System.out.println(matcher.group());
//        while(matcher.find()) {
//            System.out.println(matcher.group(0));
//        }
	}

}
