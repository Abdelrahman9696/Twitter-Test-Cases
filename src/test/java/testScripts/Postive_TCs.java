package testScripts;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Postive_TCs {
	WebDriver driver;

	@BeforeTest
	public void Open_URL() {

		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Resources\\geckodriver.exe");

		driver = new FirefoxDriver();

		driver.navigate().to("http://twitter.com/login");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.findElement(By.name("session[username_or_email]")).sendKeys("username");
		driver.findElement(By.name("session[password]")).sendKeys("password");

		driver.findElement(By.cssSelector("span.css-bfa6kz > span:nth-child(1)")).click();
	}

	@Test
	public void gifWithText_tweet() {

		// click on tweet button
		driver.findElement(By.cssSelector("a.r-urgr8i > div:nth-child(1)")).click();

		// click on gif button
		driver.findElement(By.cssSelector(
				".r-1dqxon3 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(3) > div:nth-child(1) > svg:nth-child(1)"))
		.click();

		// click on gif category
		driver.findElement(By.cssSelector(
				"div.r-1pp923h > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)"))
		.click();

		// choose gif
		driver.findElement(By.cssSelector(
				"div.r-1pp923h > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > img:nth-child(2)"))
		.click();

		// write on tweet box
		driver.findElement(By.xpath("//div[@data-testid='tweetTextarea_0']")).sendKeys("GIF");

		// click on publish tweet button
		driver.findElement(
				By.cssSelector("div.r-urgr8i:nth-child(4) > div:nth-child(1) > span:nth-child(1) > span:nth-child(1)"))
		.click();

		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.urlToBe("https://twitter.com/home"));

	}

	@Test
	public void plainText_tweet() {

		// click on tweet button
		driver.findElement(By.cssSelector("a.r-urgr8i > div:nth-child(1)")).click();

		// write on tweet box
		driver.findElement(By.xpath("//div[@data-testid='tweetTextarea_0']")).sendKeys("My First tweet");

		// click on publish tweet button
		driver.findElement(
				By.cssSelector("div.r-urgr8i:nth-child(4) > div:nth-child(1) > span:nth-child(1) > span:nth-child(1)"))
		.click();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.urlToBe("https://twitter.com/home"));
	
	}

	@Test
	public void poll_Tweet() {

		// click on tweet button
		driver.findElement(By.cssSelector("a.r-urgr8i > div:nth-child(1)")).click();

		// click on create poll button
		driver.findElement(By.cssSelector(
				".r-1dqxon3 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(4) > div:nth-child(1) > svg:nth-child(1)"))
		.click();

		// write on tweet box
		driver.findElement(By.xpath("//div[@data-testid='tweetTextarea_0']")).sendKeys("Do You Like Twitter ? ");

		// write on choice 1
		driver.findElement(By.cssSelector(
				"div.r-1qhn6m8:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > label:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)"))
		.sendKeys("yes");

		// write on choice 2
		driver.findElement(By.cssSelector(
				"div.r-1qhn6m8:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > label:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)"))
		.sendKeys("No");

		// click on publish tweet button
		driver.findElement(
				By.cssSelector("div.r-urgr8i:nth-child(4) > div:nth-child(1) > span:nth-child(1) > span:nth-child(1)"))
		.click();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.urlToBe("https://twitter.com/home"));
	}
	
	@AfterMethod
	public void Take_ScreenShot(ITestResult result) throws Exception 
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("./ScreenShots/"+result.getName()+".png"));
	}

	@AfterTest
	public void Close_Driver() {
		driver.quit();
	}

}
