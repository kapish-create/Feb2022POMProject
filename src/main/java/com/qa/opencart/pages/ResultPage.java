package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementsUtils;

public class ResultPage {
	private WebDriver driver;
	private ElementsUtils elementUtil;
	private By searchHeader = By.xpath("//div[@id='content']/h1");
	private By prductList = By.cssSelector("div.caption a");
//	private By productPrice = By.xpath("//p[@class='price']/span");
//	private By addToCart = By.xpath(
//			"//p[@class='price']/span[contains(text(), 'Ex Tax: $500.00')]/parent::p/parent::div/following-sibling::div[@class='button-group']/button/span");

	public ResultPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementsUtils(driver);
	}

	public String getSearchHeaderName() {
		return elementUtil.doGetText(searchHeader);
	}

	public int getProductSearchCount() {
		return elementUtil.waitForElementsVisible(prductList, Constants.DEFAULT_TIME_OUT).size();
	}

	public ProductInfoPage selectProductWithName(String productName) {

		List<WebElement> priceList = elementUtil.waitForElementsVisible(prductList, Constants.DEFAULT_TIME_OUT);

		for (WebElement e : priceList) {

			String text = e.getText().trim();
			System.out.println(text);
			if (text.equals(productName)) {
				e.click();
				break;
			}

		}

		return new ProductInfoPage(driver);

	}

//	public void seletProductWithHighestPrice() {
//		double highestPrice = 0.0;
//		List<WebElement> priceList = elementUtil.waitForElementsVisible(productPrice, Constants.DEFAULT_TIME_OUT);
//		System.out.println("price list");
//		List<Double> priceListValue = new ArrayList<Double>();
//		for (WebElement items : priceList) {
//			String priceValue = items.getText().replace("Ex Tax: $", "");
//			double values = Double.parseDouble(priceValue);
//			priceListValue.add(values);
//
//			System.out.println(values);
//
//		}
//		highestPrice = Collections.max(priceListValue);
//		System.out.println("Max price=" + highestPrice);
//
//		By addToCart = By.xpath("//p[@class='price']/span[contains(text(), 'Ex Tax: $" + highestPrice
//				+ "')]/parent::p/parent::div/following-sibling::div[@class='button-group']/button/span");
//		driver.findElement(addToCart).click();
//
//	}
}
