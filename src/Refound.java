import java.util.HashMap;
import java.util.Map;

import au.com.billingbuddy.common.objects.Utilities;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;
import com.stripe.model.ChargeRefundCollection;
import com.stripe.model.Refund;


public class Refound {
	
	public static void main(String[] args) {
		new Refound().searchStripeError();
	}
	
	public void refund(){
		Stripe.apiKey = "sk_test_BQokikJOvBiI2HlWgH4olfQ2";
		try {
			Map<String, Object> params = new HashMap<String, Object>(); 
			Charge charge = Charge.retrieve("ch_15Gk3W2eZvKYlo2CFKXqcVGV");
			params.put("amount", 9999999);
			Refund refund = charge.getRefunds().create(params);
			System.out.println("refund object: " + refund);
		} catch (AuthenticationException e) {
			e.printStackTrace();
		} catch (InvalidRequestException e) {
			e.printStackTrace();
		} catch (APIConnectionException e) {
			e.printStackTrace();
		} catch (CardException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}
	}
	
	public void listCharge(){
		Stripe.apiKey = "sk_test_BQokikJOvBiI2HlWgH4olfQ2";
		try {
			Charge charge = Charge.retrieve("ch_15Gk3W2eZvKYlo2CFKXqcVGV");
			System.out.println("charge: " + charge);
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | CardException | APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void listCharges(){
		Stripe.apiKey = "sk_test_BQokikJOvBiI2HlWgH4olfQ2";
		try {
			Map<String, Object> refundParams = new HashMap<String, Object>(); 
			refundParams.put("limit", 100); 
			ChargeRefundCollection refunds = Charge.retrieve("ch_15Gk3W2eZvKYlo2CFKXqcVGV").getRefunds().all(refundParams);
			
		} catch (AuthenticationException | InvalidRequestException
				| APIConnectionException | CardException | APIException e) {
			e.printStackTrace();
		}
	}
	
	public void searchStripeError(){
		System.out.println(Utilities.searchStripeError("Refund amsount ($999,999.00) is greater than unrefunded amount on charge ($381.69)"));
	}
	
}
