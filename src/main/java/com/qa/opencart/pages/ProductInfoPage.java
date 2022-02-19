package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementsUtils;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementsUtils elementUtil;

	private By productHeader = By.cssSelector("div#content h1");
	private By productImageCount = By.cssSelector("ul.thumbnails img");
	private By addToCart = By.id("button-cart");
	private By prodMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By pricingData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementsUtils(driver);
	}

	public String getProductHeader() {
		return elementUtil.doGetText(productHeader);
	}

	public int getProductImageCount() {
		return elementUtil.waitForElementsVisible(productImageCount, Constants.DEFAULT_TIME_OUT).size();
	}

	public Map<String, String> getProdcutMetaData() {
		Map<String, String> prodMap = new HashMap<String, String>();
		String prodName = elementUtil.doGetText(productHeader);
		prodMap.put("name", prodName);
		getProdMetaData(prodMap);
		getProdPriceMetaData(prodMap);
		return prodMap;

	}

	private void getProdMetaData(Map<String, String> prodMap) {
		List<WebElement> metaList = elementUtil.getElements(prodMetaData);
		for (WebElement e : metaList) {
			String metaText = e.getText();
			String metaName = metaText.split(":")[0].trim();
			String metaValue = metaText.split(":")[1].trim();
			prodMap.put(metaName, metaValue);

		}
	}

	private void getProdPriceMetaData(Map<String, String> prodMap) {
		List<WebElement> priceList = elementUtil.getElements(pricingData);
		String price = priceList.get(0).getText().trim();
		String exTaxPrice = priceList.get(1).getText().trim();
		prodMap.put("Actual Price", price);
		prodMap.put("Ex tax price", exTaxPrice.split(":")[1].trim());

	}

}
