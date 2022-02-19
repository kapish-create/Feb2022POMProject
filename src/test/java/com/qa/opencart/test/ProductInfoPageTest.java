package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
	}

	@Test
	public void productHeaderTest() {
		resultPage = accPage.doSearch("macbook");
		productInfo = resultPage.selectProductWithName("MacBook Pro");
		String prodHeader = productInfo.getProductHeader();
		Assert.assertEquals(prodHeader, "MacBook Pro");

	}

	@DataProvider
	public Object[][] imageCountData() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "Apple", "Apple Cinema 30\"", 6 },
				{ "iMac", "iMac", 3 } };
	}

	@Test(dataProvider = "imageCountData")
	public void productImageCountTest(String mainProduct, String productName, int imageCount) {
		resultPage = accPage.doSearch(mainProduct);
		productInfo = resultPage.selectProductWithName(productName);

		Assert.assertEquals(productInfo.getProductImageCount(), imageCount);

	}

	@Test
	public void prodMetaDataTest() {
		resultPage = accPage.doSearch("macbook");
		productInfo = resultPage.selectProductWithName("MacBook Pro");
		Map<String, String> actualMap = productInfo.getProdcutMetaData();
		actualMap.forEach((k, v) -> System.out.println(k + ":" + v));

		softAssert.assertEquals(actualMap.get("Brand"), "Apple");
		softAssert.assertEquals(actualMap.get("Availability"), "Out Of Stock");
		softAssert.assertEquals(actualMap.get("name"), "MacBook Pro");
		softAssert.assertEquals(actualMap.get("Actual Price"), "$2,000.00");
		softAssert.assertAll();

	}

}
