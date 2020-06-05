package WikipediaCreateAccount;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class TestingUsingXLSFile {
	WebDriver driver;
	//TestMethod to setUp chrome and open URL
	@BeforeClass
	public void config() {
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium Installation\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		driver.get("https://en.wikipedia.org/w/index.php?title=Special:CreateAccount&returnto=Login");
		}
	
	//TestMethod to create account
	@Test(dataProvider="testData")
	//parameters for the method should be same as in excel
	public void createAccount(String username,String password,String confirmPassword,String emailAddress) {
		driver.findElement(By.name("wpName")).clear();
		driver.findElement(By.name("wpName")).sendKeys(username);
		driver.findElement(By.name("wpPassword")).clear();
		driver.findElement(By.name("wpPassword")).sendKeys(password);
		driver.findElement(By.name("retype")).clear();
		driver.findElement(By.name("retype")).sendKeys(confirmPassword);
		driver.findElement(By.name("email")).clear();
		driver.findElement(By.name("email")).sendKeys(emailAddress);
		
	}
	@AfterClass
	public void close() {
		driver.close();
	}
		
	
	
	
	
	//DataProvider Method
	//Always return type will be of object[][]
	//provide data provider annotation so that it can be used in test method
	//if name is not provided,then it ll take dataprovider method name i.e readExcel
	@DataProvider(name="testData")
	public Object[][] readExcel() throws BiffException, IOException{
		//use file class to give the path of xls file
		//object f stores the location of excel file
		File f = new File("C:\\Users\\LATHA ARUNACHALAM\\Desktop\\TestDataForWikipediaCreateAccount.xls");
		//get the workboook
		//use work book class to get the excel book
		Workbook wb = Workbook.getWorkbook(f);
		//get the sheet in which the data are there
		//Sheet class is used to store the sheet and getSheet 
		//from workbook class method is used to get the sheet
		Sheet sh = wb.getSheet("Sheet1");
		//get the number of rows and columns having data
		int noOfRows = sh.getRows();
		int noOfColumns = sh.getColumns();
		//create an array that stores all data in rows and columns
		String data[][] = new String[noOfRows][noOfColumns];
		//read the data in excel
		//index starts with 0 and excel sheet starts with 1
		//data starts from line 2 in excel,so counter needs to be from 1
		for(int i =1;i<noOfRows;i++) {
			for(int j=0;j<noOfColumns;j++) {
				//get the cell value
				//note:get cell method always accepts columns first and then rows
				//store it in type Cell
				
				Cell c=sh.getCell(j, i);//this method only points the cell
				//this method gets the contents and this method is there in Cell Class
				//store in the array which is declared before
				data[i][j]=c.getContents();
				
				
			}
		}
		return data;
		
	}

}
