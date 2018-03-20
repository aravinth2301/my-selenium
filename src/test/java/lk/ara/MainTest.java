
package lk.ara;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class MainTest {

	private WebDriver driver;
	private ScreenshotHelper screenshotHelper = new ScreenshotHelper();

	@Test
	public void helloTestNG() throws IOException {
		String driverName = null;
		if ("Mac OS X".equals(System.getProperty("os.name"))) {
			driverName = "drivers/chromedriver";
		} else {
			driverName = "drivers/chromedriver-l";
		}

		ClassLoader classLoader = getClass().getClassLoader();
		System.setProperty("webdriver.chrome.driver", classLoader.getResource(driverName).getFile());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		driver = new ChromeDriver(options);
		driver.get("http://www.google.com/xhtml");

		// Thread.sleep(5000);
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("ChromeDriver");
		searchBox.submit();
		screenshotHelper.saveScreenshot("screenshot-" + new Date() + ".png");
		driver.quit();
		// driver.quit();
		// Thread.sleep(5000); // Let the user actually see something!

	}

	// @After
	public void saveScreenshotAndCloseBrowser() throws IOException {
		screenshotHelper.saveScreenshot("screenshot-ara.png");
		driver.quit();
	}

	private class ScreenshotHelper {

		public void saveScreenshot(String screenshotFileName) throws IOException {
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screenshot, new File(screenshotFileName));
		}
	}
}
