package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionManager {
	// srp principle --single response
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;

	OptionManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
	return co;
	
}
	
	public FirefoxOptions getFirefoxOptions() {
		if (Boolean.parseBoolean(prop.getProperty("headless")))
			;
		fo = new FirefoxOptions();
		return fo.addArguments("--headless");
	}

}
