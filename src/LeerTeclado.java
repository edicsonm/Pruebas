import java.util.Scanner;


public class LeerTeclado {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		InputStreamReader isr = new InputStreamReader(System.in);
//		BufferedReader br = new BufferedReader (isr);
//		String cadena = br.readLine();
		Scanner sc = new Scanner(System.in);
		System.out.println("Entre cadena....");
		String cadena = sc.nextLine();
		System.out.println("Entre entero....");
		int entero = sc.nextInt();
		System.out.println("Cadena: " + cadena);
		System.out.println("Entero: " + entero);
	}

}
