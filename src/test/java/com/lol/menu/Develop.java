package com.lol.menu;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Develop {
	
	WebDriver driver = null;
	WebElement table=null;
	List<WebElement> rows = null;
	
	//public SearchFor SearchFor;
	
	public Develop(WebDriver driver){
		this.driver = driver;
		//this.SearchFor = new Specification(driver, this);
	}
	
	public void Specification(){
		try{
			navigateToItem("Specification");
		}catch(Exception e){
			navigateToItem("Specification");
		}
	}
	public void Project(){
		try{
			navigateToItem("Project");
		}catch(Exception e){
			navigateToItem("Project");
		}
	}
	
	
	private void navigateToItem(String navigateItem){
		
		//System.out.println("Into");
		driver.findElement(By.xpath("//*[@id='inforApplicationNav1']/ul/li[1]/a")).click();
		table = driver.findElement(By.xpath("html/body/form/div[4]/table/tbody/tr/td[2]/div/ul"));
		rows = table.findElements(By.tagName("li"));
		System.out.println(rows.size());
		//System.out.println("navigate "+navigateItem);
		
		if(navigateItem.contains("Specification")){
			driver.findElement(By.xpath(".//*[@id='inforApplicationNav1']/ul/li[1]/ul/li[1]/a ")).click();
			}
		if(!navigateItem.contains("Specification")){
		for(int i =1;i<rows.size();i++){
			//System.out.println("hi : "+i+" -- "+rows.get(i).getText().trim());
			if(rows.get(i).getText().trim().equals(navigateItem)){
				driver.findElement(By.xpath("//*[@id='inforApplicationNav1']/ul/li[1]/ul/li["+(i+1)+"]/a")).click();
				break;
			}	}
			/*table = driver.findElement(By.xpath("//*[@id='inforApplicationNav1']/ul/li["+(i)+"]/ul"));
			rows = table.findElements(By.tagName("li"));
			for(int j =0;j<rows.size();j++){
				System.out.println(rows.size());
				//System.out.println("hellow : "+j+" -- "+rows.get(j).getText().trim());
*/			//if(rows.get(j).getText().trim().equals(navigateItem)){
				//System.out.println("html/body/div[3]/table/tbody/tr["+(i+1)+"]/td[2]");
					
		}
	

}}
