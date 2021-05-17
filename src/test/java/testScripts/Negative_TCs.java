package testScripts;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Negative_TCs {

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
	public void empty_Tweet() throws IOException {
		// click on tweet button
		driver.findElement(By.cssSelector("a.r-urgr8i > div:nth-child(1)")).click();

		// write on tweet box
		// driver.findElement(By.xpath("//div[@data-testid='tweetTextarea_0']"))
		// .sendKeys("hello");

		CaptureScreenShot(driver,"empty_Tweet");
		
		// check tweet button
		WebElement TweetButton = driver.findElement(By.cssSelector(
				".r-1dqxon3 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2)"));
		TweetButton.findElement(By.cssSelector("[aria-disabled='true']"));

		// click on close button
		driver.findElement(By.cssSelector(".r-s8bhmr > div:nth-child(1)")).click();

		// wait until home page shown
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.urlToBe("https://twitter.com/home"));

	}

	@Test
	public void image_With_GIF() throws IOException {
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
		
		CaptureScreenShot(driver,"image_With_GIF");

		// check image button
		WebElement PicButton = driver.findElement(By.cssSelector(
				".r-1dqxon3 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(4) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1)"));
		PicButton.findElement(By.cssSelector("[aria-disabled='true']"));

		// click on close button
		driver.findElement(By.cssSelector(".r-s8bhmr > div:nth-child(1)")).click();

		// click on Discard tweet button
		driver.findElement(By.cssSelector("div.r-1ifxtd0:nth-child(1)")).click();

		// wait until home page shown
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.urlToBe("https://twitter.com/home"));

	}

	@Test
	public void same_tweet_TwiceInaRow() throws IOException {
		// click on tweet button
		driver.findElement(By.cssSelector("a.r-urgr8i > div:nth-child(1)")).click();

		// write on tweet box
		driver.findElement(By.xpath("//div[@data-testid='tweetTextarea_0']")).sendKeys("hello");

		// click on publish tweet button
		driver.findElement(
				By.cssSelector("div.r-urgr8i:nth-child(4) > div:nth-child(1) > span:nth-child(1) > span:nth-child(1)"))
				.click();

		// Delay for specified amount of time to load page.
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.urlToBe("https://twitter.com/home"));

		// click on tweet button
		driver.findElement(By.cssSelector("a.r-urgr8i > div:nth-child(1)")).click();

		// write on tweet box
		driver.findElement(By.xpath("//div[@data-testid='tweetTextarea_0']")).sendKeys("hello");

		// click on publish tweet button
		driver.findElement(
				By.cssSelector("div.r-urgr8i:nth-child(4) > div:nth-child(1) > span:nth-child(1) > span:nth-child(1)"))
				.click();

		CaptureScreenShot(driver,"same_tweet_TwiceInaRow");
		
		// check Error Message
		WebElement ErrorMsg = driver
				.findElement(By.cssSelector("div.r-1pp923h:nth-child(1) > div:nth-child(1) > div:nth-child(1)"));
		Assert.assertTrue(ErrorMsg.getText().contains("Something went wrong"));

		// click on close button
		driver.findElement(By.cssSelector(".r-s8bhmr > div:nth-child(1)")).click();

		// click on Discard tweet button
		driver.findElement(By.cssSelector("div.r-1ifxtd0:nth-child(1)")).click();

		// wait until home page shown
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		wait1.until(ExpectedConditions.urlToBe("https://twitter.com/home"));

	}

	@AfterTest
	public void quite() {
		driver.quit();
	}
	
	public static void  CaptureScreenShot (WebDriver driver ,String name ) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File("./ScreenShots/"+name+".png"));
	}
	

}
