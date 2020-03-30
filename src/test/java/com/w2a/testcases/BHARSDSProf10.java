package com.w2a.testcases;


import java.io.FileInputStream;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;



public class BHARSDSProf10 {
	
	 //public static void main(String[] args) throws Exception  {	
	@Test (priority=1) 	
	public void move() throws Exception {
		
	
	  		
		 
	  		Properties OR1 = new Properties();
	  		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR1.properties");
	          OR1.load(fis);
	  		
	  		try {
	  			//moveFile("D:\\Temp\\BHAR1","\\\\HPSDEVXESEFST01\\shared\\Pickup\\837\\Inbound\\BHAR1");
	  			moveFile("D:\\Temp\\"+OR1.getProperty("TC10Name"),"\\\\HPSDEVXESEFST01\\shared\\Pickup\\837\\Inbound\\"+OR1.getProperty("TC10Name"));
	  		} catch (Exception e) {
	  			// TODO Auto-generated catch block
	  			e.printStackTrace();
	  		}
	  		
	  	}

	
	    // code to move the file from source destination to target destination 	
		

public static void moveFile(String src, String dest ) throws Exception {
	
	          
		      Path result = null;
		      try {
		         result =  Files.move(Paths.get(src), Paths.get(dest));
		      } catch (Exception e) {
		         System.out.println("Exception while moving file: " + e.getMessage());
		         Assert.fail("Failed");	         
		        
		      }
 if(result != null) {
		         System.out.println("TC10: File moved successfully.");
		      }
		      else
		      {
		         System.out.println("File movement failed");
		         
		      }  
}


		      
		     @Test (priority=2,dependsOnMethods="move")
		  	public void login() throws Exception {
		      
		      
      //code to take a break of 1 minute between file load and execution on browser
		      Thread.sleep(60000);
		      		     
		//code to start validation on Chrome browser
		
		      
		      System.setProperty("webdriver.chrome.driver", "D:\\Selenium\\BHARAutomation\\src\\test\\resources\\executables\\chromedriver.exe");
		
		Properties OR1 = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR1.properties");
        OR1.load(fis);
        
        
        
		WebDriver driver = new ChromeDriver();
		
		
		try
		{
			driver.get(OR1.getProperty("devURL"));
			
		}
		catch(SkipException E) {
			
			throw new SkipException("URL not accessible");
		}
		driver.manage().window().maximize();		
		driver.findElement(By.cssSelector("#Username")).sendKeys(OR1.getProperty("username"));
		driver.findElement(By.cssSelector("form:nth-child(2) div.form:nth-child(1) div:nth-child(2) div:nth-child(4) > input.textbox")).sendKeys(OR1.getProperty("password"));
		driver.findElement(By.cssSelector("form:nth-child(2) div.form:nth-child(1) div:nth-child(2) div.button:nth-child(5) > input.login-button")).click();
		Thread.sleep(2000);
	    driver.findElement(By.xpath(OR1.getProperty("BHARSDStesturl"))).click();
	    driver.findElement(By.cssSelector("table:nth-child(5) tbody:nth-child(1) tr:nth-child(4) td:nth-child(1) > input:nth-child(1)")).click();
		      
		      
	    Thread.sleep(5000);
	    try {
	    	Thread.sleep(1000);
	    	driver.switchTo().frame("CommerceDesk_DisplayFrame");
	    }
            catch(NoSuchElementException E) {
			
			    
            	throw new NoSuchElementException("Server Down");
            	
		}
	    //Removed below path on 10th Mar due to change in element location
	    //driver.findElement(By.xpath("//a[contains(text(),'PHKY1')]")).click();
	    
	    
	    driver.findElement(By.linkText("BHAR10")).click();
	    //driver.findElement(By.xpath("//td[@id='ViewDatavw_gbd_generated_ETCPTransmission_Last 24 Hours (Batch)_3_1']//a[contains(text(),'PHKY1')]")).click();
	   
	    
	    
	   	Thread.sleep(5000);
	   	
	   	driver.findElement(By.cssSelector("span[title='Claims']")).click();
	    //driver.findElement(By.cssSelector("table.inspector-content div.WebTabs-tab div.WebTabs-tab-text-container:nth-child(3) div:nth-child(1) div:nth-child(1) a:nth-child(1) > span:nth-child(1)")).click();
	    
	    //trying to validate the expected result is in line with output
	    Thread.sleep(2000);
	    
	    
	    try
	    {
	    	
	    if(driver.findElement(By.xpath("//div[contains(text(),'Processed')]")).isDisplayed())
	    { 
	    	System.out.println("Getting Correct Result :Disposition column displays value as 'Processed' ");
	    }
	    	   
	    }
	    catch(Exception E)
	    {
	    	driver.close();
	    	Assert.fail("Getting Claim Status as 'Rejected'-->Hence quitting the execution" );
	    	
	    	
	    	
	    	
	    }
	    
	    driver.findElement(By.cssSelector("table.inspector-content div.WebTabs-tab div.WebTabs-internal-page-container td.viewer-table-td table.JCLRFlex.JColResizer:nth-child(2) tbody:nth-child(1) tr.viewer-table-row-1:nth-child(2) td.viewer-table-cell-text.nowrap-cls:nth-child(1) div.viewer-table-cell-inner > a:nth-child(2)")).click();
	    Thread.sleep(2000);    
	    try {
	   if(driver.findElement(By.xpath("//span[contains(text(),'Front Channel Processing')]")).isDisplayed()) 
	   {
	    	
	  	System.out.println("Getting Correct Result :Activity State displays value as 'Front Channel Processing' ");
	   }
	    }
	    catch (Exception E) {
	    	
	    	driver.close();	    	
	    	Assert.fail("Activity State is not 'Front Channel Processing'-->Hence quitting the execution" );
	    	
	    	
	    }
	   driver.switchTo().defaultContent();
	    Thread.sleep(2000);
	    driver.switchTo().frame("CommerceDesk_LeftFrame");
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//a[@id='text1']")).click();
	    Thread.sleep(2000);
	    driver.switchTo().defaultContent();
	    Thread.sleep(4000);
	    driver.switchTo().frame("CommerceDesk_DisplayFrame");
	    
	    
	    Thread.sleep(2000);
	    
	    try {
	    	if(driver.findElement(By.linkText("BHAR10.277CA")).isDisplayed() )
	 	   //if(driver.findElement(By.xpath("//a[contains(text(),'PHKY1.277CA')]")).isDisplayed() )
	 	   {
	 	    	
	 	  	System.out.println("277CA file is present ");
	 	   }
	 	    }
	 	    catch (Exception E) {
	 	    	driver.close();
	 	    	Assert.fail("277CA file not generated-->Hence quitting the execution" );
	 	    	
	 	    	
	 	    }
	    driver.findElement(By.xpath("//a[contains(text(),'BHAR10.277CA')]")).click();
	    Thread.sleep(2000);
	    try{
	    	if(driver.findElement(By.xpath("//span[@class='labelValue property-icon icon-processed']")).isDisplayed()) {
	    
	    	System.out.println("Getting Correct Status in 277CA");
	    }
}
catch (Exception E) {
	driver.close(); 	
 	Assert.fail("277CA status is not correct-->Hence quitting the execution" );
 	
 }
	    	    
	    driver.close();
		 
	           
		     
	    
	}

	
}