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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

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
			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver(optionManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionManager.getChromeOptions()));
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
//			driver = new FirefoxDriver(optionManager.getFireFoxOptions());
			tlDriver.set(new FirefoxDriver(optionManager.getFireFoxOptions()));
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
