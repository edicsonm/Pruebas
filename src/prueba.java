
public class prueba {

	public prueba() {
		System.out.println(scaleValue(500, 55076, 1048798));
	}

	public static void main(String[] args) {
		new prueba();

	}
	
	public double percent(int number, int mayor){
		System.out.println("% " + (number * 100)/mayor);
		return (number * 100)/mayor;
	}
	
	public String scaleValue(int dimensionXScreen, int number, int mayor){
		return String.valueOf(dimensionXScreen*percent(number, mayor)/100);
	}
	
}
