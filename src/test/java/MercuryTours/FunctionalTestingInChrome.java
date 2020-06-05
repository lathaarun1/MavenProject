package MercuryTours;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FunctionalTestingInChrome {
	WebDriver Driver ;
	@BeforeClass
	public void Config() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Program Files\\Selenium Installation\\chromedriver_win32\\chromedriver.exe");
		Driver = new ChromeDriver();
		Driver.manage().deleteAllCookies();
		Driver.manage().window().maximize();
		Driver.get("http://newtours.demoaut.com/");
		
	}
	@BeforeMethod
	public void waitToFindEle() {
		Driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
	}
	@Parameters({"username","password"})
	@Test
	public void signIn(String username , String password) {
		Driver.findElement(By.name("userName")).sendKeys(username);
		Driver.findElement(By.name("password")).sendKeys(password);
		Driver.findElement(By.name("login")).click();
	}
	@AfterClass
	public void close() {
		Driver.close();
	}

}
