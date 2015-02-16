import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

public class RunSH {

	private static String DNAMEMERCHANT="cn=Risk Manager Agent SSL Receiver,ou=Tivoli,o=IBM,c=US";
	private static String ALIASMERCHANT="Merchant2";
	private static String PASSWORDMERCHANT="password";
	
	private static String DNAMEBB="cn=Risk Manager Agent SSL Receiver,ou=Tivoli,o=IBM,c=US";
	private static String ALIASBB="BillingBuddy2";
	private static String PASSWORDBB="p@55w0rd!";
	
	private static String DAYS_VALID="365";

	public RunSH() {}

	public static void main(String[] args) {
//		new RunSH().generarCertificados(
		new RunSH().listarPropiedadesCertificado();
//		new RunSH().listarPropiedadesConJava();
	}
	
	public void generarCertificados(){
		try {
//			ProcessBuilder pb = new ProcessBuilder("/run/media/Edicson/Informacion IPG/certificados/genCertJava");
			ProcessBuilder pb = new ProcessBuilder("/run/media/Edicson/Informacion IPG/certificados/genCert",  System.getProperty("java.home") + "/bin", DNAMEMERCHANT, ALIASMERCHANT, PASSWORDMERCHANT, DNAMEBB, ALIASBB, PASSWORDBB, DAYS_VALID);
			Process p = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println("--> " + line);
			}
			}catch (Exception e){
				e.printStackTrace();
			}
	}
	
	public void listarPropiedadesCertificado(){
		try {
			ProcessBuilder pb = new ProcessBuilder("/run/media/Edicson/Informacion IPG/certificados/verifyCert",  System.getProperty("java.home") + "/bin", ALIASMERCHANT, PASSWORDMERCHANT, ALIASBB, PASSWORDBB );
			Process p = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
//				if(line.contains("Nombre de Alias")){
//					System.out.println(line);
//				}else if(line.contains("Fecha de Creación")){
//					System.out.println(line);
//				}else if(line.contains("Tipo de Entrada")){
//					System.out.println(line);
//				}
				System.out.println("--> " + line);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void listarPropiedadesConJava(){
		try {
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            char[] password = PASSWORDBB.toCharArray();//Key Store Password
            java.io.FileInputStream fis = new java.io.FileInputStream("/run/media/Edicson/Informacion IPG/worspaceLifeRay/Pruebas/BillingBuddy2.jks");
            ks.load(fis, password);
            fis.close();
            char[] keypassword = PASSWORDBB.toCharArray();//
            Key myKey = ks.getKey("billingbuddy2", keypassword);
            Certificate cer =  ks.getCertificate("merchant2");
            System.out.println( ks.getCreationDate("merchant2"));
            System.out.println( myKey.getAlgorithm());
            PrivateKey myPrivateKey = (PrivateKey) myKey;
            Signature mySign = Signature.getInstance("MD5withRSA");
//            mySign.initSign(myPrivateKey);
//            mySign.update(mensajeRecibido.getBytes());
//            byte[] byteSignedData = mySign.sign();//Mensaje firmado
//            mensajeFirmado = byteToHexa(byteSignedData);//Convert signed message from bytes to hexa and return the result as string.
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
