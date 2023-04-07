package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	ResultsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	private By searchHeader = By.cssSelector("div #content h1");
	private By productResults = By.cssSelector("div.caption a");

	public int getProductListCount() {
		int prodcount = eleUtil.waitForElementsVisible(productResults, 5).size();
		System.out.println("total search prod count  is : " + prodcount);
		return prodcount;
	}

	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("main product name :" + mainProductName);
		List<WebElement> searchlist = eleUtil.waitForElementsVisible(productResults, 10);
		for (WebElement e : searchlist) {
			String text = e.getText();
			if (text.equals(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);
	}

}
