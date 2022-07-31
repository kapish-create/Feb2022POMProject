//package com.qa.opencart.test;
//
//import java.util.List;
//
//import org.testng.Assert;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import com.qa.opencart.base.BaseTest;
//import com.qa.opencart.utils.Constants;
//
//import io.qameta.allure.Description;
//import io.qameta.allure.Epic;
//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;
//import io.qameta.allure.Step;
//import io.qameta.allure.Story;
//@Epic("Epic 200: Design Account page features with all the details")
//@Story("User Stroy 201: Design the account page and its features")
//public class AccountsPageTest extends BaseTest {
//
//	@BeforeClass
//	public void accPageSetup() {
//		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
//	}
//
//	@Description("Getting account page title")
//	@Severity(SeverityLevel.NORMAL)
//	@Test
//	public void accPageTitleTest() {
//
//		String title = accPage.accPageTitle();
//		System.out.println("Account page title " + title);
//
//		Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
//
//	}
//
//	@Description("Checking account page Logout test")
//	@Severity(SeverityLevel.NORMAL)
//	@Test
//	public void accPageLogoutLinkTest() {
//		Assert.assertTrue(accPage.isLogoutLinkExist());
//	}
//
//	@Description("Checking headers secton count")
//	@Severity(SeverityLevel.NORMAL)
//	@Test
//	public void accSecListTest() {
//		List<String> actualSecList = accPage.getAccountSectionList();
//		System.out.println("List of section headers " + actualSecList);
//
//		Assert.assertEquals(actualSecList, Constants.EXPECTED_ACCOUNTS_SECTION_LIST);
//	}
//
//	@DataProvider
//	public Object[][] productData() {
//		return new Object[][] { { "macbook" }, { "Apple" }, { "iMac" } };
//	}
//
//	@Description("Searching for the specific product")
//	@Severity(SeverityLevel.CRITICAL)
//	@Test(dataProvider = "productData")
//	public void searchTest(String mainProduct) {
//		resultPage = accPage.doSearch(mainProduct);
//		Assert.assertTrue(resultPage.getProductSearchCount() > 0);
//	}
//
//	@DataProvider
//	public Object[][] productSelectData() {
//		return new Object[][] { { "macbook", "MacBook" }, { "Apple", "Apple Cinema 30\"" }, { "iMac", "iMac" } };
//	}
//
//	@Description("Selecting the product for getting product details")
//	@Severity(SeverityLevel.CRITICAL)
//	@Test(dataProvider = "productSelectData")
//	public void selectProductTest(String mainProduct, String selectProdcutName) {
//		resultPage = accPage.doSearch(mainProduct);
//		System.out.println("Product is seleceted");
//		resultPage.selectProductWithName(selectProdcutName);
//
//	}
//
//}
