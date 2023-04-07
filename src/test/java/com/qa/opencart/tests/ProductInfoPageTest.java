package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetUp() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productHeaderTest() {
		resultPage = accPage.doSearch("Macbook");
		productInfoPage = resultPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeaderName(), "MacBook Pro");
	}

	@DataProvider
	public Object[][] productData() {
		return new Object[][] { { "Macbook", "MacBook Pro", Constants.MACBOOK_IMAGES_COUNT },
				{ "Macbook", "MacBook Air", Constants.MACBOOK_IMAGES_COUNT },
				{ "iMac", "iMac", Constants.IMAC_IMAGES_COUNT }, };
	}

	@Test(dataProvider = "productData")
	public void productImagesCountTest(String productName, String mainProductName, int imagesCount) {
		resultPage = accPage.doSearch(productName);
		productInfoPage = resultPage.selectProduct(mainProductName);
		int totalImages = productInfoPage.getProductImageCount();
		System.out.println("total images for : " + mainProductName + ":" + totalImages);
		Assert.assertEquals(totalImages, imagesCount);
	}

//	MacBook Pro
//	Key = Brand, Value = Apple
//	Product Code
//	Key = ExTaxPrice, Value = Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
//	Key = Price, Value = Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: In Stock
//	Key = name, Value = MacBook Pro
//	Key = totalimages, Value = 4
	@Test
	public void productDataTest() {
		resultPage = accPage.doSearch("Macbook");
		productInfoPage = resultPage.selectProduct("MacBook Pro");

		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
		// actProductInfoMap.forEach((k,v)-> System.out.println(k+":"+v));
		for (Map.Entry<String, String> entry : actProductInfoMap.entrySet())
			// System.out.println(entry.getKey()+":"+entry.getValue());
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

		Assert.assertEquals(actProductInfoMap.get("totalimages"), "4");
		softAssert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
//	Assert.assertEquals(actProductInfoMap.get("Product Code"),"Product 18");
//	Assert.assertEquals(actProductInfoMap.get("Brand"),"Apple");
//	Assert.assertEquals(actProductInfoMap.get("price"),"$2,000.00");
		softAssert.assertAll();

	}

}
