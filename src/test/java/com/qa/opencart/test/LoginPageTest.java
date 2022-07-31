package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
@Epic("Epic 100:OpenCart application, Design The Login Page..")
@Story("User Stroy 101:Implement the Login page features")
public class LoginPageTest extends BaseTest {

//	@Description("Login page title test....")
//	@Severity(SeverityLevel.NORMAL)
//	@Test
//	public void loginPageTitleTest() {
//		String title = loginPage.getLoginPageTitle();
//		System.out.println("act page title is: " + title);
//		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
//	}
//	
//	@Description("Login page url test....")
//	@Severity(SeverityLevel.NORMAL)
//	@Test
//	public void loginPageUrlTest() {
//		String pageUrl = loginPage.getLoginPageUrl();
//		System.out.println("act page url: " + pageUrl);
//		Assert.assertTrue(pageUrl.contains(Constants.LOGIN_PAGE_URL_VALUE));
//	}
//
//	@Description("Forgot password link test....")
//	@Severity(SeverityLevel.CRITICAL)
//	@Test
//	public void forgotPwdLinkTest() {
//		System.out.println("Is Forgot password link exits " + loginPage.isForgotPwdLinkExist());
//		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
//	}
//	
//	
//	@Description("Register Link test....")
//	@Severity(SeverityLevel.CRITICAL)
//	@Test
//	public void isRegisterLinkTest() {
//		System.out.println("Is Register link exits " + loginPage.isRegisterLinkExist());
//		Assert.assertTrue(loginPage.isRegisterLinkExist());
//
//	}
	@Description("Login test....")
	@Severity(SeverityLevel.BLOCKER)
	@Test
	public void loginTest() {
		loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

}
