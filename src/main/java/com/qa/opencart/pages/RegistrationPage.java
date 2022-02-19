package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementsUtils;

public class RegistrationPage {

	private WebDriver driver;
	private ElementsUtils elementUtil;

	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");

	private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input");
	private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input");

	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");
	private By sucessMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegistrationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub

		this.driver = driver;
		elementUtil = new ElementsUtils(driver);
	}

	public boolean register(String firstName, String lastName, String email, String telephone, String password,
			String subscribe) {
		fillRegistrationForm(firstName, lastName, email, telephone, password);
		subscribe(subscribe);
		selectAgreementAndContiniue();
		return getRegistrationStatus();

	}

	private boolean getRegistrationStatus() {
		String mseg = elementUtil.doGetText(sucessMessg);
		System.out.println(mseg);
		if (mseg.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
			elementUtil.doClick(logoutLink);
			elementUtil.doClick(registerLink);
			return true;
		}
		return false;
	}

	private void subscribe(String subscribe) {
		if (subscribe.equalsIgnoreCase("Yes")) {
			elementUtil.doClick(subscribeYes);
		} else {
			elementUtil.doClick(subscribeNo);
		}
	}

	private void selectAgreementAndContiniue() {
		elementUtil.doClick(agreeCheckBox);
		elementUtil.doClick(continueButton);
	}

	private void fillRegistrationForm(String firstName, String lastName, String email, String telephone,
			String password) {
		elementUtil.doSendKeys(this.firstName, firstName);
		elementUtil.doSendKeys(this.lastName, lastName);
		elementUtil.doSendKeys(this.email, email);
		elementUtil.doSendKeys(this.telephone, telephone);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doSendKeys(this.confirmpassword, password);
	}

}
