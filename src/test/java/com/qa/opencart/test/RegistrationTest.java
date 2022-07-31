//package com.qa.opencart.test;
//
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import com.qa.opencart.base.BaseTest;
//import com.qa.opencart.utils.Constants;
//import com.qa.opencart.utils.ExcelUtils;
//
//public class RegistrationTest extends BaseTest {
//
//	@BeforeClass
//	public void regsPageSetup() {
//
//		registerPage = loginPage.navigateToRegisterPage();
//	}
//
//	@DataProvider
//	public Object[][] getRegisterData() {
//		Object data[][] = ExcelUtils.getTestData(Constants.REGISTER_SHEET_NAME);
//		return data;
//	}
//	
//    
//	@Test(dataProvider = "getRegisterData")
//	public void registrationTest(String firstName, String lastName, String email, String telephone, String password,
//			String subscribe) {
//		Assert.assertTrue(registerPage.register(firstName, lastName, email, telephone, password, subscribe));
//	}
//}
