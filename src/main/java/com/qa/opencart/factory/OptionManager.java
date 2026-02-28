package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager {
	// srp principle --single response
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	
	public OptionManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		co.addArguments("--no-sandbox");
		co.addArguments("--disable-dev-shm-usage");
		// Use system Chrome binary if available
		String chromeBinary = System.getenv("CHROME_BINARY");
		if (chromeBinary != null && !chromeBinary.isEmpty()) {
			co.setBinary(chromeBinary);
		}
		if (Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) fo.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) fo.addArguments("-private");
		return fo;
	}

}
