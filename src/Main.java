import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import au.com.billingbuddy.business.objects.ProcessorMDTR;
import au.com.billingbuddy.business.objects.TransactionMDTR;
import au.com.billingbuddy.common.objects.ConfigurationSystem;
import au.com.billingbuddy.common.objects.Currency;
import au.com.billingbuddy.common.objects.Utilities;
import au.com.billingbuddy.common.objects.ValidateData;
import au.com.billingbuddy.exceptions.objects.DataSanitizeException;
import au.com.billingbuddy.exceptions.objects.TransactionMDTRException;
import au.com.billingbuddy.vo.objects.CardVO;
import au.com.billingbuddy.vo.objects.TransactionVO;


public class Main {
	public static ConfigurationSystem configurationSystem = ConfigurationSystem.getInstance();
	public static void main(String[] args) {
		
		//System.out.println(Utilities.formatDate("2014-12-25 15:11:25.0"));
		
		int days = Integer.parseInt(configurationSystem.getKey("days.PROC_SEARCH_AMOUNT_BY_DAY"));
		System.out.println("days: " + days);
		System.out.println(Utilities.validateDateReport("", days));
		System.out.println(Utilities.validateDateReport("", 0));
//		String original = "23120.9";
//		String guardado = Utilities.currencyToStripe("23120.9", Currency.USD);
//		String recuperado = Utilities.stripeToCurrency(guardado, Currency.USD);
//		System.out.println(original);
//		System.out.println(guardado);
//		System.out.println(recuperado);
//		
//		Pattern p = Pattern.compile("Charge.*has already been refunded.*");
//		Matcher m = p.matcher("Charge ch_15GMF82eZvKYlo2CHT5NlTYm has already been refunded.");
//		System.out.println("Salida: " + m.matches());
//		String s="550.76";
//		int i= new Double(s).intValue();
//		System.out.println(Utilities.currencyToStripe("23120.9", Currency.USD));
		
//		System.out.println(new DecimalFormat("00").format(Double.parseDouble("2112")));
		
//		try {
//			TransactionVO transactionVO = new TransactionVO();
//			transactionVO.setCardVO(new CardVO());
////			transactionVO.getCardVO().setCardNumber("5401324612153930");
//			transactionVO.getCardVO().setNumber("4242424242424242");
//			transactionVO.getCardVO().setExpMonth("12");
//			transactionVO.getCardVO().setExpYear("15");
//			transactionVO.getCardVO().setCvv("499");
//			transactionVO.getCardVO().setName("EDICSON MORALES");
//			transactionVO.setIp("27.32.44.176");
//			transactionVO.setOrderCurrency("usd");
//			transactionVO.setOrderAmount("400");
//			
////			FraudDetectionMDRT fraudDetectionMDRT = FraudDetectionMDRT.getInstance();
////			fraudDetectionMDRT.CreditCardFraudDetection(transactionVO);
//			
////			ProcessorMDTR processorMDTR = ProcessorMDTR.getInstance();
////			processorMDTR.chargeTransaction(transactionVO);
//			
//			TransactionMDTR transactionMDTR = TransactionMDTR.getInstance();
//			transactionMDTR.chargePayment(transactionVO);
//			
//		} catch (TransactionMDTRException e) {
//			e.printStackTrace();
//		}
	}

}
