package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.net.*;

/**
 * 
 * @author kapis
 *
 */
public class DriverFactory {
	public Properties prop;
	public WebDriver driver;
	public static String highLight;
	public OptionsManager optionManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * this method is used to initialized the browser on the basis of given browser
	 * 
	 * @param browser
	 * @return this method will return the driver
	 */

	public WebDriver initDriver(Properties prop) {
		highLight = prop.getProperty("highlistes");
		optionManager = new OptionsManager(prop);
		String browser = prop.getProperty("browser").trim();
		System.out.println("browser name is : " + browser);

		if (browser.equalsIgnoreCase("chrome")) {

//			driver = new ChromeDriver(optionManager.getChromeOptions());
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver(browser);
			} else {
				// for local excecution
				WebDriverManager.chromedriver().setup();
				tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
			}

		} else if (browser.equalsIgnoreCase("firefox")) {

//			driver = new FirefoxDriver(optionManager.getFireFoxOptions());
			if (Boolean.parseBoolean(prop.getProperty("remote"))) {
				init_remoteDriver(browser);
			} else {
				// for local excecution
				WebDriverManager.firefoxdriver().setup();
				tlDriver.set(new FirefoxDriver(optionManager.getFireFoxOptions()));
			}

		} else if (browser.equalsIgnoreCase("safari")) {
//			driver = new SafariDriver();
			tlDriver.set(new SafariDriver());

		}

		else {
			System.out.println("please pass the correct browser");
		}

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}

	private void init_remoteDriver(String browser) {
		System.out.println("Running test on remote grid server+++++++" + browser);
		if (browser.equals("chrome")) {
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			cap.setCapability("browserName", "chrome");
			cap.setCapability(ChromeOptions.CAPABILITY, optionManager.getChromeOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		} else {
			DesiredCapabilities cap = DesiredCapabilities.firefox();
			cap.setCapability("browserName", "firfox");
			cap.setCapability(FirefoxOptions.FIREFOX_OPTIONS, optionManager.getFireFoxOptions());
			try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), cap));
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}

	}

	public synchronized WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * This method is used to initialized properties on the basis of given
	 * environment
	 * 
	 * @return this method return prop
	 */

	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
		String env = System.getProperty("env");
		if (env == null) {
			System.out.println("Running Envoirnment : " + env);
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Running on Environment: " + env);
			try {
				switch (env.toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				case "stg":
					ip = new FileInputStream("./src/test/resources/config/stg.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
					break;

				default:
					System.out.println(".....Please pass the right environment....." + env);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
