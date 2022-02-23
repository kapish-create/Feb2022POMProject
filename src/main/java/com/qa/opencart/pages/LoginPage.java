package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementsUtils;

import io.qameta.allure.Epic;
import io.qameta.allure.Step;
import io.qameta.allure.Story;

public class LoginPage {

	private WebDriver driver;
	private ElementsUtils elementsUtil;
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By logginButton = By.xpath("//input[@value='Login']");
	private By forgotPassword = By.xpath("//div[@class='form-group']/a");
	private By totalNumberOfLink = By.xpath("//div[@class='list-group']/a");
	private By registerLink = By.linkText("Register");

	public LoginPage(WebDriver driver) {

		this.driver = driver;
		elementsUtil = new ElementsUtils(driver);

	}

	@Step("Getting login page title.....")
	public String getLoginPageTitle() {
		return driver.getTitle();
	}

	@Step("Getting login page url.....")
	public String getLoginPageUrl() {
		return driver.getCurrentUrl();
	}

	@Step("Checking if forgot password link exist.....")
	public boolean isForgotPwdLinkExist() {
		return elementsUtil.doIsDiplayed(forgotPassword);
	}

	@Step("Checking if forgot password link displayed.....")
	public boolean isRegisterLinkExist() {
		return elementsUtil.doIsDiplayed(registerLink);
	}

	@Step("Login into application with userName : {0} and Password : {1}")
	public AccountPage doLogin(String un, String paswd) {
		System.out.println("UserName and Password is : " + un + ":" + paswd);
		elementsUtil.doSendKeys(emailId, un);
		elementsUtil.doSendKeys(password, paswd);
		elementsUtil.doClick(logginButton);
		return new AccountPage(driver);
	}

	@Step("Navigating to Register page..")
	public RegistrationPage navigateToRegisterPage() {
		elementsUtil.doClick(registerLink);
		return new RegistrationPage(driver);
	}
}
