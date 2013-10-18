package com.tpk.testcases.workflow;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thoughtworks.selenium.DefaultSelenium;
import com.tpk.utils.AdminConfig;
import com.tpk.utils.EditorConfig;
import com.tpk.utils.FileUtils;
import com.tpk.utils.ImageStaffConfig;
import com.tpk.utils.ProductConfig;
import com.tpk.utils.ProofReaderConfig;
import com.tpk.utils.PublicConfig;

import junit.framework.TestCase;

public class TestCase1 extends TestCase {
	private DefaultSelenium selenium;

	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, PublicConfig.browser, PublicConfig.web_site);
		selenium.start();
	}

	@Test
	public void testCase() throws Exception {
		selenium.windowMaximize();
		selenium.open("");
        selenium.waitForPageToLoad("3000000");
        try {
			selenium.click("link=Sign in");
		} catch (Exception e) {
			selenium.click("link=Sign out");
		}
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Sign in");
		selenium.waitForPageToLoad("30000");
		//admin upload products
		selenium.type("id=user_email", AdminConfig.user_name);
		selenium.type("id=user_password", AdminConfig.user_pwd);
		selenium.click("id=user_submit");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Product Import");
		selenium.waitForPageToLoad("30000");
		selenium.focus("id=search_client");
		selenium.select("id=search_client", "label=" + AdminConfig.products_import_client);
		selenium.focus("id=shooting_plan_select");
		boolean flag = true;
		while (flag) {
			try {
			    selenium.fireEvent("id=search_client", "blur");
				selenium.select("id=shooting_plan_select", "label=" + AdminConfig.products_import_shooting_project);
				flag = false;
			} catch (Exception e) {
				selenium.fireEvent("id=search_client", "blur");
			}
		}
		String filePath = AdminConfig.products_import_filepath;
		String windowTitle = AdminConfig.products_import_window_title;
		FileUtils.uploadFile(windowTitle, filePath);
		selenium.click("id=file");
		selenium.click("id=import_products_button");
		selenium.waitForPageToLoad("3000000");
		assertTrue(selenium.isTextPresent("imported successfully"));
		// end admin upload products
		selenium.click("link=Sign out");
		selenium.waitForPageToLoad("30000");
		//image staff upload pic
		selenium.type("id=user_email", ImageStaffConfig.user_name);
		selenium.type("id=user_password", ImageStaffConfig.user_pwd);
		selenium.click("id=user_submit");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Image Management");
		selenium.waitForPageToLoad("30000");
		selenium.click("//table[@id='image_item_list_table']//tr[1]//a[text()='Edit']");
		selenium.waitForPageToLoad("30000");
		//upload image
		String imagePath = ImageStaffConfig.upload_image_path;
		String imageWindowTitle = ImageStaffConfig.upload_iamge_win_title;
		FileUtils.uploadFile(imageWindowTitle, imagePath);
		selenium.click("id=imageuploadField");
		selenium.click("name=commit");
		selenium.waitForPageToLoad("3000000");
		assertTrue(selenium.isTextPresent("Image was successfully updated"));
		selenium.click("id=image_update_and_confirm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Image confirmed. It has shifted to the next product."));
		//end image staff upload pic
		selenium.click("link=Sign out");
		selenium.waitForPageToLoad("30000");
		//editor audit product
		selenium.type("id=user_email", EditorConfig.user_name);
		selenium.type("id=user_password", EditorConfig.user_pwd);
		selenium.click("id=user_submit");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Product Management");
		selenium.waitForPageToLoad("30000");
		selenium.click("//table[@id='product_list_table']//tr[1]//a[text()='Edit']");
		selenium.waitForPageToLoad("30000");
	    //brand
		selenium.type("id=product_brand_select", ProductConfig.brand.substring(0, 1));
		selenium.typeKeys("id=product_brand_select", ProductConfig.brand.substring(0, 1));
		for (int second = 0;; second++) {
		    if (second >= 60) fail("timeout");
		    try { if (selenium.isTextPresent(ProductConfig.brand)) break; } catch (Exception e) {}
		    Thread.sleep(1000);
		}
		selenium.type("id=product_brand_select", ProductConfig.brand);
		//products_categories
		selenium.type("id=product_productCategories_select", ProductConfig.productCategories.substring(0, 1));
		selenium.typeKeys("id=product_productCategories_select", ProductConfig.productCategories.substring(0, 1));
		for (int second = 0;; second++) {
		    if (second >= 60) fail("timeout");
		    try { if (selenium.isTextPresent(ProductConfig.productCategories)) break; } catch (Exception e) {}
		    Thread.sleep(1000);
		}
		selenium.type("id=product_productCategories_select", ProductConfig.productCategories);
		//end products_categories
		selenium.click("id=product_update_and_confirm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Product confirmed. It has shifted to the next product."));
		//end editor audit product
		selenium.click("link=Sign out");
		selenium.waitForPageToLoad("30000");
		//proof reader audit product
		selenium.type("id=user_email", ProofReaderConfig.user_name);
		selenium.type("id=user_password", ProofReaderConfig.user_pwd);
		selenium.click("id=user_submit");
		selenium.waitForPageToLoad("30000");
		selenium.click("link=Product Management");
		selenium.waitForPageToLoad("30000");
		selenium.click("//table[@id='product_list_table']//tr[1]//a[text()='Edit']");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Image Status: Images approved"));
		assertTrue(selenium.isTextPresent("Content Status:Content finished"));
		selenium.click("id=product_update_and_confirm");
		selenium.waitForPageToLoad("30000");
		assertTrue(selenium.isTextPresent("Product confirmed. It has shifted to the next product."));
		//end proof reader audit product
	}
	
	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
