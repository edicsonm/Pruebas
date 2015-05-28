package Json;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import au.com.billingbuddy.business.objects.ProcessSubscriptionsMDTR;
import au.com.billingbuddy.exceptions.objects.SubscriptionsMDTRException;

public class Main {


	public static void main(String[] args) throws IOException {
		boolean unpaids =  true;
		boolean noUpdated =  true;
		boolean errorFileExist =  true;
		/*System.out.println(Calendar.getInstance().getTime().toString());
		
		Calendar now = Calendar.getInstance();
        System.out.println(now.getTimeZone());
        System.out.println(now.getTime());*/
		
		/*JSONObject informationDetails = new JSONObject();
		
		JSONObject information = new JSONObject();
		information.put("unpaids", unpaids);
		if(unpaids){
			information.put("Recomendation","Run the \"Reprocess Process\" to resend the transactions to our processor.");
			information.put("Information", "There are subscripcions that our procesor could not charge to some card holders. The uncharges transactions information are available in the tables Reprocess_X ");
		}
		informationDetails.put("InformationUnpaids", information);
		
		
		information = new JSONObject();
		information.put("noUpdated", noUpdated);
		if(noUpdated){
			information.put("InformationNoUpdated", "There are subscripcions that were charged by our processor but the information was not updated in our systems.");
			information.put("Recomendation","Check system logs to determine the causes of the error.");
		}
		informationDetails.put("InformationNoUpdated", information);
		
		
		information = new JSONObject();
		information.put("errorFileExist", errorFileExist);
		if(errorFileExist){
			information.put("Information", "Was created a file that content information about the subscripcions that could not Update.");
			information.put("Recomendation","Execute the recovery process to update the correct field in our data base.");
		}
		informationDetails.put("InformationErrorFileExist", information);
		
		
		System.out.println(informationDetails.toJSONString());*/
		
//		for (int i = 0; i < 50; i++) {
//			imprimirMensaje("Mensaje numero " + i);
//		}
		
//		JSONObject object = new JSONObject();
//		object.put("name", "Ejemplo");
//		JSONArray array = new JSONArray();
//		
//		JSONObject arrayElementOne = new JSONObject();
//		arrayElementOne.put("setId", 1);
//		JSONArray arrayElementOneArray = new JSONArray();
//		
//		JSONObject arrayElementOneArrayElementOne = new JSONObject();
//		arrayElementOneArrayElementOne.put("name", "ABC");
//		arrayElementOneArrayElementOne.put("type", "STRING");
//
//		JSONObject arrayElementOneArrayElementTwo = new JSONObject();
//		arrayElementOneArrayElementTwo.put("name", "XYZ");
//		arrayElementOneArrayElementTwo.put("type", "STRING");
//
//		
//		arrayElementOneArray.add(arrayElementOneArrayElementOne);
//		arrayElementOneArray.add(arrayElementOneArrayElementTwo);
//		
//		arrayElementOne.put("asdasd", arrayElementOneArray);
//		
//		object.put("lista", arrayElementOne);
//		System.out.println(object.toJSONString());
		/*
		JSONObject attributeDetail = new JSONObject();
		attributeDetail.put("Dasu_ID", "dailySubscriptionVO.getId()");
		attributeDetail.put("Subs_ID", "dailySubscriptionVO.getSubscriptionId()");
								
		JSONArray arrayAttributes = new JSONArray();
		arrayAttributes.add(attributeDetail);
		
		JSONObject attributes = new JSONObject();
		attributes.put("Attributes", arrayAttributes);

		JSONObject root = new JSONObject();
		root.put("ErrorDetails", attributes);
		
		System.out.println(attributeDetail.toJSONString());
		System.out.println(arrayAttributes.toJSONString());
		System.out.println(root.toJSONString());
		
		ErrorLogVO errorLogVO = new ErrorLogVO();
		errorLogVO.setProcessName("DailySubscriptions");
		errorLogVO.setInformation(root.toJSONString());
		
		try {
			ErrorLogDAO errorLogDAO = new ErrorLogDAO();
			errorLogDAO.insert(errorLogVO);
		} catch (MySQLConnectionException e) {
			e.printStackTrace();
		} catch (ErrorLogDAOException e) {
			e.printStackTrace();
		}*/
		
		
//		JSONObject attributeDetail = new JSONObject();
//		attributeDetail.put("Dasu_ID", "dailySubscriptionVO.getId()");
//		SubscriptionsMDTR.getInstance().writeError("lalala",attributeDetail);
		
		
//		try {
//			instance.reprocessFile("/BBErrors/SaveInformationSubscriptions/ProccesDailySubscriptions - Fri May 22 08:50:49 AEST 2015");
//		} catch (SubscriptionsMDTRException e) {
//			e.printStackTrace();
//		}
		
//		processFile("/BBErrors/SaveInformationSubscriptions/ProccesDailySubscriptions - Tue May 19 22:24:15 AEST 2015");
		new Main().ejecutarSubscripciones();
	}
	

	public void ejecutarSubscripciones(){
		ProcessSubscriptionsMDTR instance = ProcessSubscriptionsMDTR.getInstance();
		try {
			boolean respuesta = instance.proccesDailySubscriptions();
			if (!respuesta){//Se presento algun error
				if(instance.isWriteInErrorLog()){
					System.out.println("Se presentaron errores, la informacion se encuentra almacenada en el archivo " + instance.getLogFileName());
					System.out.println("Verifique las causas de los errores y ejecute el proceso de recuperacion de errores.");
//					processFile(instance.getLogFileName());
//					instance.reprocessFile(instance.getLogFileName());
				}
			}
		} catch (SubscriptionsMDTRException e) {
		}
	}
	
	public static void processFile(String fileName) {
	      File archivo = null;
	      FileReader fileReader = null;
	      BufferedReader bufferedReader = null;
	      try {
	         archivo = new File (fileName);
	         fileReader = new FileReader (archivo);
	         bufferedReader = new BufferedReader(fileReader);
	         String linea;
	         while((linea = bufferedReader.readLine())!=null){
		         Object obj = JSONValue.parse(linea);
		         JSONObject jSONObject = (JSONObject)obj;
		         System.out.println("jSONObject: " + jSONObject.get("CALL_DailySubscriptionDAO"));
	         }
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         try{                    
	            if( null != fileReader ){   
	            	fileReader.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	   }
	
	public static void imprimirMensaje(String mensaje) throws IOException {
		File file = new File("pepito.txt");
		if(!file.exists()){
			file.createNewFile();
		}
		FileWriter fstream = new FileWriter(file, true);
		BufferedWriter out = new BufferedWriter(fstream);
		out.write(mensaje);
		out.newLine();
		out.close();
	}

}
