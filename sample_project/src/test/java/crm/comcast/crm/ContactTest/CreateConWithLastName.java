package crm.comcast.crm.ContactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcat.crm.excelUtility.ExcelUtility;
import com.comcat.crm.genericFileutility.FileUtility;
import com.comcat.genericWebDriverUtility.JavaUtility;

public class CreateConWithLastName {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		WebDriver   driver=null;
		FileUtility fileutilityobject = new FileUtility();
		ExcelUtility exUtility = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();
		
		String BROWSER= fileutilityobject.getDataFromPropertiesFile("browser");
		String URL = fileutilityobject.getDataFromPropertiesFile("url");
		String USERNAME = fileutilityobject.getDataFromPropertiesFile("username");
		String PASSWORD =fileutilityobject.getDataFromPropertiesFile("password"); 
		//reading data from excel	
		
		int randomnum = jlib.getRandomNumber();

		String lastname = exUtility.getDataFromExcelFile("cont", 1, 2)+ randomnum;
		
		//Wb.close();
		if(BROWSER.equals("chrome")){
			driver=new ChromeDriver();
		}
		else if(BROWSER.equals("firefox")){
			driver=new FirefoxDriver();
		}else if(BROWSER.equals("edge")){
			driver =new EdgeDriver();
		}else {
			driver =new ChromeDriver();
		}
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.findElement(By.xpath("//input[@name=\"user_name\"]")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name=\"user_password\"]")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@type=\"submit\"]")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
		driver.findElement(By.xpath("//input[@name=\"lastname\"]")).sendKeys(lastname);
		driver.findElement(By.xpath("//input[@name=\"button\"][1]")).click();
		String Con_info=driver.findElement(By.xpath("//input[@name=\\\"lastname\\\"]")).getText();
		if( Con_info.contains(lastname)) {
			System.out.println(lastname+"is created==PASS");
		}			else {
			System.out.println(lastname+"is not created==FAIL");
		}
		////			
		////			 //verify org name info expected result.
		////			String ActOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		////			if(ActOrgName.contains(orgname)) {
		////				System.out.println(orgname+"is created==PASS");
		////			}
		////			else {
		////				System.out.println(orgname+"is not created==FAIL");
		////			}
		////		  
		driver.quit();

	}

}
