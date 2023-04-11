package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public OptionManager optionsManager;

	public static String highlight;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal();

	/**
	 * this method is used to initialize the driver using browser name from
	 * properties reference
	 * 
	 * @param Properties reference
	 * @return this returns the webdriver
	 */
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionManager(prop);
		  if(browserName.equalsIgnoreCase("chrome"));{
			WebDriverManager.chromedriver().setup();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		  }
		

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url").trim());
		return getDriver();
	}

	/**
	 * this will return the thread local copy of the driver
	 * 
	 * @return
	 */
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	

	
	// ThreadLocal -- JDK 8 --> create a local copy of driver
	// set driver with TL
	// getdriver() -- driver
	// dribver null problem
	// u can take ur driver copy anywhere in ur framework
	// better thread management
	// to avoid the dead local conditon -- TL driver copy
	// large test cases count -- 200, 300 TCS --> proper test results

	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + "sourav" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	/*
	 * this method is used to initialize the properties
	 * @return this returns properties class reference
	 */

	public Properties init_prop() {

		prop = new Properties();
		try {
			FileInputStream file = new FileInputStream(
					"D:\\RS_Workspace\\onlineShopping\\src\\test\\resources\\config\\config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	// take entire screen shot
	public static String getEntirePageScreenshot() {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(getDriver());
		String path = "C:\\Users\\Sourav\\Desktop\\A";
		// System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis()
		// + ".png";
		//
		File destination = new File(path);

		try {
			ImageIO.write(screenshot.getImage(), "PNG", destination);
			// new File
			// (System.getProperty("user.dir")+"\\screenshots\\fulllpagescreeshot.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;

	}

}
