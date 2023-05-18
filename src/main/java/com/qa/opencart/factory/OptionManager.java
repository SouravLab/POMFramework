package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;

public class OptionManager {
	// srp principle --single response
	private Properties prop;
	private ChromeOptions co;
	
	public OptionManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) co.addArguments("--headless");
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
		return co;
	
	}

}
