import java.util.ArrayList;

import au.com.billigbuddy.utils.BBUtils;
import au.com.billingbuddy.business.objects.ProcesorFacade;
import au.com.billingbuddy.common.objects.Utilities;
import au.com.billingbuddy.exceptions.objects.ProcesorFacadeException;
import au.com.billingbuddy.vo.objects.UserMerchantVO;


public class Fechas {
	Integer val;
	public static void main(String[] args) {
//		System.out.println(BBUtils.getCurrentDate(6,-7));
//		System.out.println(BBUtils.getCurrentDate(6,0));
//		System.out.println(BBUtils.formatDate(3,"2015-01-18 19:19:13.0",3));
//		System.out.println(BBUtils.printCardNumber("1234567890111213"));
//		String val = "boo:and:foo";
//		String val = "123456******1213";
		String val = "123456------1213";
		
//		String[] valores = val.split("------");
//		
//		for (int i = 0; i < valores.length; i++) {
//			System.out.println(valores[i]);
//		}
		ProcesorFacade instance = ProcesorFacade.getInstance();
		UserMerchantVO userMerchantVO = new UserMerchantVO();
        userMerchantVO.setUserId("18811");
        ArrayList<UserMerchantVO> listUserMerchants;
		try {
			listUserMerchants = instance.listUserMerchants(userMerchantVO);
			String merchants = BBUtils.configureUserMerchants(listUserMerchants);
			System.out.println(merchants);
		} catch (ProcesorFacadeException e) {
			e.printStackTrace();
		}
        
	}

}
