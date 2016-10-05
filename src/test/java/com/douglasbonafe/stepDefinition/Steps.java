package com.douglasbonafe.stepDefinition;

import static com.douglasbonafe.util.Helpers.driver;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.douglasbonafe.util.Helpers;
import com.douglasbonafe.viewObjects.HomeView;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

public class Steps {

	/***********************************************************************************************
	 * Initial Configuration Setup: Needs to be here in this version of Cucumber
	 ***********************************************************************************************/

	static {
		// Disable annoying cookie warnings.
		// WARNING: Invalid cookie header
		LogFactory.getFactory().setAttribute("org.apache.commons.logging.Log",
				"org.apache.commons.logging.impl.NoOpLog");
	}

	/** Keep the same date prefix to identify job sets. **/
	private static Date date = new Date();

	@Before
	/** Run before each test **/
	public void setUp() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
		capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

		// Set job name on Sauce Labs
		capabilities.setCapability("name", "Triangle App automation" + date);

		URL serverAddress;
		String appName = "TrianguloApp.apk";
		File appDir = new File("app");
		File apk = new File(appDir, appName);
		String apkPath = apk.getAbsolutePath();
		capabilities.setCapability(MobileCapabilityType.APP, apkPath);
		serverAddress = new URL("http://127.0.0.1:4723/wd/hub");

		driver = new AndroidDriver<AndroidElement>(serverAddress, capabilities);

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		Helpers.init(driver, serverAddress);
	}

	/***********************************************************************************************
	 * Cucumber Steps
	 ***********************************************************************************************/

	@Given("^we are at home view$")
	public void we_are_at_home_view() throws Throwable {
		Assert.assertTrue(HomeView.loaded());
	}

	@When("^we define the edge one (\\d+)$")
	public void we_define_the_edge_one(int l1) throws Throwable {
		HomeView.setEdge1(l1);
	}

	@When("^we define the edge two (\\d+)$")
	public void we_define_the_edge_two(int l2) throws Throwable {
		HomeView.setEdge2(l2);
	}

	@When("^we define the edge three (\\d+)$")
	public void we_define_the_edge_three(int l3) throws Throwable {
		HomeView.setEdge3(l3);
	}

	@When("^we click on the button Calcular$")
	public void we_click_on_the_button_Calcular() throws Throwable {
		HomeView.clickOnBtnCalcular();
	}

	@Then("^we should to see the message \"([^\"]*)\" with the type \"([^\"]*)\"$")
	public void we_should_to_see_the_message_with_the_type(String msg, String type) throws Throwable {
		Assert.assertTrue(HomeView.getMessage().equals(msg + type));
	}

	/***********************************************************************************************
	 * After test
	 ***********************************************************************************************/

	@After
	/** Run after each test **/
	public void tearDown() throws Exception {
		if (driver != null)
			driver.quit();
	}
}
