package Edumarket_brokenlinks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class link_validations {
	
//	private static WebDriver driver = null;

	public static void main(String[] args) {
	// TODO Auto-generated method stub

	String homePage = "https://www.stfc.in/";
	String url = "";
	HttpURLConnection huc = null;
	int respCode = 200;

	WebDriverManager.chromedriver().setup();
	
	WebDriver driver;
	
	driver = new ChromeDriver();
	
	driver.manage().window().maximize();

	driver.get(homePage);

	List<WebElement> links = driver.findElements(By.tagName("a"));

	Iterator<WebElement> it = links.iterator();

	while(it.hasNext()){

	url = it.next().getAttribute("href");

//	System.out.println(url);

	if(url == null || url.isEmpty()){
	System.out.println("URL is either not configured for anchor tag or it is empty");
	continue;
	}

	if(!url.startsWith(homePage)){
		System.out.println(url +""+ "other domain");
	System.out.println("URL belongs to another domain, skipping it.");
	continue;
	}

	try {
	huc = (HttpURLConnection)(new URL(url).openConnection());

	huc.setRequestMethod("HEAD");

	huc.connect();

	respCode = huc.getResponseCode();

	if(respCode >= 400){
	System.out.println(url+" is a broken link" +" "+respCode);
	}
	else{
	System.out.println(url+" is a valid link"+" "+respCode);
	
	}

	} catch (MalformedURLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}

	driver.quit();

	}

}
