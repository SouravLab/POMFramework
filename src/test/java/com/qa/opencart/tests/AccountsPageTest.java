package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountsPageTest extends BaseTest {

	/*
	 * @BeforeClass will execute before @BeforeTest here we ll use base class
	 * reference and also login method return referenc which is AccounPage ref
	 */
	@BeforeClass
	public void accPageSetup() {
		// accPage ref created in base class and here accPage is holding the return type
		// ..
		// we ll not create unnecessary accountpage object in base class.only ref we ll
		// create.
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void accPageTitleTest() {
		String actualTitle = accPage.getAccountsPageTitle();
		System.out.println("Acc page title is  : " + actualTitle);
		Assert.assertEquals(actualTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}

	@Test
	public void accPageUrlTest() {
		String actualUrl = accPage.getAccountsPageUrl();
		System.out.println("Acc page title is  : " + actualUrl);
		Assert.assertTrue(actualUrl.contains(Constants.ACCOUNTS_URL_FRACTION));

	}

	@Test (enabled=false)
	public void accPageHeaderTest() {
		String header = accPage.getAccPageHeader();
		System.out.println(header);
		Assert.assertEquals(header, Constants.ACCOUNT_PAGE_HEADER);
	}

	@Test
	public void logoutLinkTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}

	@Test
	public void searchExistTest() {
		Assert.assertTrue(accPage.searchExist());
	}

	@Test
	public void accpageSectionsTest() {
		List<String> actualSectionList = accPage.getAccPageSection();
		System.out.println("Actual section list : " + actualSectionList);
Assert.assertEquals(actualSectionList, Constants.ACCOUNT_PAGE_SECTION_LIST);
	}
	
	@DataProvider
	public Object[][] prodData() {
		return new Object[][] {
			{"MacBook"},
			{"Imac"},
			{"apple"}
		};
	}
	
	@Test (dataProvider = "prodData")
	public void searchTest(String prodName) {
	resultPage=	accPage.doSearch(prodName);
	Assert.assertTrue(resultPage.getProductListCount()>0);
	}
	
	
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] {
			{"MacBook","Search - MacBook Pro"},
			{"MacBook","Search - MacBook Air"},
			{"imac","Search - imac"},
			{"apple","Search - Apple Cinema 30\""}
		};
	}
	
	
	
	
	
	@Test(dataProvider="productSelectData")
	public void selectProductTest(String prodName,String mainProductName) {
		resultPage=	accPage.doSearch(prodName);
		productInfoPage=resultPage.selectProduct(mainProductName);
		System.out.println(productInfoPage.getProductHeaderName());
		Assert.assertEquals(productInfoPage.getProductHeaderName(), mainProductName);
		
		
		
	}
	

}
