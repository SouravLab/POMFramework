package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class OrderPage {
	WebDriver driver;
	By order = By.id("order");

	public void order() {
		driver = new ChromeDriver();
		driver.findElement(order).click();

	}

}
