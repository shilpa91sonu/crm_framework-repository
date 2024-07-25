package crm.comcast.crm.ContactTest;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContactWithOraganizationTest {

	public static void main(String[] args) throws Throwable {
		// TODO Auto-generated method stub
		WebDriver   driver=null;
	    
		 FileInputStream fis = new FileInputStream("C:\\Users\\chand\\Desktop\\data\\commondata.properties");
		    Properties pobj = new Properties();
		    pobj.load(fis);
		    String BROWSER= pobj.getProperty("browser");
		    String URL = pobj.getProperty("url");
		    String USERNAME = pobj.getProperty("username");
		    String PASSWORD = pobj.getProperty("password");
		    	//reading data from excel	
		   Random random = new Random();
			int randomInt = random.nextInt(1000);
			
		    FileInputStream fis1 = new FileInputStream("C:\\Users\\chand\\Desktop\\data\\TestScriptdata.xlsx");
			Workbook Wb = WorkbookFactory.create(fis1);		
			Sheet sheet1 = Wb.getSheet("Contacts");
		Row row = sheet1.getRow(1);
		String cnt_Lname= row.getCell(2).toString()+randomInt;
		Sheet sheet2 = Wb.getSheet("org");
		String org_name = sheet2.getRow(7).getCell(2).toString();
		
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
	    driver.findElement(By.xpath("//img[@src=\"themes/softed/images/btnL3Add.gif\"]")).click();
	    driver.findElement(By.xpath("//input[@name=\"lastname\"]")).sendKeys(cnt_Lname);
//	    driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img)")).click();
//	  Set<String> C_Win = driver.getWindowHandles();
//	    Iterator<String> itr1 = C_Win.iterator();
//	    while(itr1.hasNext()) {
//	    	String windowID = itr1.next();
//		    driver.switchTo().window(windowID);
//		    String act_url = driver.getCurrentUrl();
//		    if (act_url.contains("Accounts&action")) {
//		    	break;
//		    }
//		    driver.findElement(By.xpath("//input[@name=\"search_text\"]")).sendKeys(org_name);
//		    driver.findElement(By.xpath("//a[text()='"+org_name+"']")).click();
//		    Set<String> P_Win = driver.getWindowHandles();
//		    Iterator<String> itr2 = P_Win.iterator();
//		    while(itr1.hasNext()) {
//		    	String P_windowID = itr1.next();
//			    driver.switchTo().window(P_windowID);
//			    String p_url = driver.getCurrentUrl();
//			    if (p_url.contains("DetailView")) {
//			    	break;
//			    }
//		    }
//		    //driver.findElement(By.name(org_name))
//	    driver.findElement(By.xpath("(//input[@title=\"Save [Alt+S]\"])[1]")).click();
//	
//	String act_org_name = driver.findElement(By.xpath("//input[@name=\"account_name\"]")).getText();
//	if(act_org_name.contains(org_name)) {
//		System.out.println("PASS");
//		
//	}
//	else
//		System.out.println("fail");
//
//		
//	}
	driver.quit();
	}
}
