package com.qa.opencart.pages;

import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementsUtils;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

@Epic("Epic 200: Design Account page features with all the details")
@Story("User Stroy 201: Design the account page and its features")
public class AccountPage {

	private WebDriver driver;
	private ElementsUtils elementUtil;

	private By search = By.name("search");
	private By seatchButton = By.xpath("//span[@class='input-group-btn']");
	private By logOutLink = By.linkText("Logout");
	private By accSectionHeaders = By.xpath("//div[@id='content']/h2");

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementsUtils(driver);

	}

	@Step("Getting account page title")
	public String accPageTitle() {
		return elementUtil.waitForTitleToBe(Constants.DEFAULT_TIME_OUT, Constants.ACCOUNT_PAGE_TITLE);
	}

	@Step("Checking if Logout option displayed on the account page")
	public boolean isLogoutLinkExist() {
		return elementUtil.doIsDiplayed(logOutLink);
	}

	@Step("Checking if search field exist")
	public boolean isSearchFieldExist() {
		return elementUtil.doIsDiplayed(search);
	}

	@Step("Getting the section headers on the page")
	public List<String> getAccountSectionList() {
		List<WebElement> accSecList = elementUtil.getElements(accSectionHeaders);
		List<String> secHeaderList = new ArrayList<String>();
		for (WebElement e : accSecList) {

			secHeaderList.add(e.getText());
		}

		return secHeaderList;
	}

	@Step("Seaching the product")
	public ResultPage doSearch(String productName) {
		System.out.println("The product name=" + productName);
		elementUtil.doSendKeys(search, productName);
		elementUtil.doClick(seatchButton);

		return new ResultPage(driver);
	}
}
