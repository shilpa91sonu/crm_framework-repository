package crm.comcast.crm.ContactTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Properties;
import java.util.Random;

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

public class CreateContactWithSupportDate {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
				WebDriver  driver=null;
			    
				FileUtility flib = new FileUtility();
				ExcelUtility exUtility = new ExcelUtility();
				JavaUtility jlib = new JavaUtility();
				    String BROWSER= flib.getDataFromPropertiesFile("browser");
				    String URL = flib.getDataFromPropertiesFile("url");
				    String USERNAME = flib.getDataFromPropertiesFile("username");
				    String PASSWORD = flib.getDataFromPropertiesFile("password");
				    	//reading data from excel	
				    int randomint = jlib.getRandomNumber();
					
				    FileInputStream fis1 = new FileInputStream("C:\\Users\\chand\\Desktop\\data\\TestScriptdata.xlsx");
					Workbook Wb = WorkbookFactory.create(fis1);		
					org.apache.poi.ss.usermodel.Sheet  sheet1 = Wb.getSheet("Contacts");
				org.apache.poi.ss.usermodel.Row row = sheet1.getRow(1);
				String cnt_Lname= row.getCell(2).toString()+randomint;
					Wb.close();
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
			    Thread.sleep(2000);
			    driver.findElement(By.linkText("Contacts")).click();
			    String start_date = jlib.getCurrentSystemDateYYYYMMDD();
			    String end_date = jlib.getRequiredDateYYYYMMDD(30);
				driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
				driver.findElement(By.xpath("//input[@name=\"lastname\"]")).sendKeys(cnt_Lname);
				driver.findElement(By.xpath("//input[@name=\"support_start_date\"]")).clear();
				driver.findElement(By.xpath("//input[@name=\"support_start_date\"]")).sendKeys(start_date);
				driver.findElement(By.xpath("//input[@name=\"support_end_date\"]")).clear();
				driver.findElement(By.xpath("//input[@name=\"support_end_date\"]")).sendKeys(end_date);
				
				driver.findElement(By.xpath("//input[@name=\"button\"][1]")).click();
				String Con_info=driver.findElement(By.xpath("//input[@name=\\\"lastname\\\"]")).getText();
				if( Con_info.contains(cnt_Lname)) {
						System.out.println(cnt_Lname+"is created==PASS");
					}			else {
						System.out.println(cnt_Lname+"is not created==FAIL");
		}
				driver.quit();
	}

}
