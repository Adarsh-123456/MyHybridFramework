package CommonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;

import Constant.AppUtil;

public class FunctionLibrary extends AppUtil {
	//method for login
	public static boolean verifyLogin(String username,String password)throws Throwable
	{
		driver.findElement(By.xpath(adi.getProperty("objuser"))).sendKeys(username);
		driver.findElement(By.xpath(adi.getProperty("objpass"))).sendKeys(password);
		driver.findElement(By.xpath(adi.getProperty("objlog"))).click();
		Thread.sleep(2000);
		String expected="adminflow";
		String actual=driver.getCurrentUrl();
		if(actual.toLowerCase().contains(expected.toLowerCase()))
		{
			Reporter.log("Login Success::"+expected+"   "+actual,true);
			return true;
		}
		else
		{
			Reporter.log("Login Fail::"+expected+"   "+actual,true);
			return false;
		}
		
	}
	//method for click branch
		public static void clickBraches()
		{
			driver.findElement(By.xpath(adi.getProperty("objbranch"))).click();
		}
	//method for branch creation
		public static boolean verifynewBranch(String branchname,String addresss1,String address2,
				String address3,String area,String zipcode,String country
				,String state,String city) throws Throwable
		{
			driver.findElement(By.xpath(adi.getProperty("objnewbranch"))).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(adi.getProperty("objbranchname"))).sendKeys(branchname);
			driver.findElement(By.xpath(adi.getProperty("objaddress1"))).sendKeys(addresss1);
			driver.findElement(By.xpath(adi.getProperty("objaddress2"))).sendKeys(address2);
			driver.findElement(By.xpath(adi.getProperty("objaddress3"))).sendKeys(address3);
			driver.findElement(By.xpath(adi.getProperty("objarea"))).sendKeys(area);
			driver.findElement(By.xpath(adi.getProperty("objzip"))).sendKeys(zipcode);
			new Select(driver.findElement(By.xpath(adi.getProperty("objcountry")))).selectByVisibleText(country);
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath(adi.getProperty("objstate")))).selectByVisibleText(state);
			Thread.sleep(2000);
			new Select(driver.findElement(By.xpath(adi.getProperty("objcity")))).selectByVisibleText(city);
			Thread.sleep(2000);
			driver.findElement(By.xpath(adi.getProperty("objsubmit"))).click();
			//capture alert message
			String expectedalertmessage =driver.switchTo().alert().getText();
			Thread.sleep(5000);
			driver.switchTo().alert().accept();
			String actualalertmessage = "created Sucessfully";
			if(expectedalertmessage.toLowerCase().contains(actualalertmessage.toLowerCase()))
			{
				Reporter.log(expectedalertmessage,true);
				return true;
			}
			else
			{
				Reporter.log("New branch creation Fail",true);
				return false;
			}
			
		}
		//method for branch updation
		public static boolean verifybranchUpdation(String branch,String address,String zip) throws Throwable
		{
			driver.findElement(By.xpath(adi.getProperty("objedit"))).click();
			Thread.sleep(3000);
			WebElement updatebranch=driver.findElement(By.xpath(adi.getProperty("objbname")));
			updatebranch.clear();
			updatebranch.sendKeys(branch);
			Thread.sleep(1000);
			WebElement updateaddress=driver.findElement(By.xpath(adi.getProperty("objadds1")));
			updateaddress.clear();
			updateaddress.sendKeys(address);
			Thread.sleep(1000);
			WebElement updatezip=driver.findElement(By.xpath(adi.getProperty("objzipc")));
			updatezip.clear();
			updatezip.sendKeys(zip);
			Thread.sleep(1000);
		    driver.findElement(By.xpath(adi.getProperty("objupdate"))).click();
			Thread.sleep(4000);
			String expectedalert = driver.switchTo().alert().getText();
			Thread.sleep(4000);
			driver.switchTo().alert().accept();
			String actualalert = "Branch updated";
			if(expectedalert.toLowerCase().contains(actualalert.toLowerCase()))
			{
				Reporter.log(expectedalert,true);
				return true;
			}
			else
			{
				Reporter.log("Branch update fail",true);
				return false;
			}
			
		}
		//method for logout
		public static boolean verifyLogout()throws Throwable
		{
			driver.findElement(By.xpath(adi.getProperty("objlogout"))).click();
			Thread.sleep(4000);
			if(driver.findElement(By.xpath(adi.getProperty("objlog"))).isDisplayed())
			{
				Reporter.log("Logout is successful",true);
				return true;
			}
			else
			{
				Reporter.log("Logout Fails",true);
				return false;
			}
		}
			
			
			
			
			
		
	

}
