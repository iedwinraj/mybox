package seo_task;

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

import ExcelUtil.Excel_Reader;
import ExcelUtil.writeExcelFinalsrc;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Link_validations extends writeExcelFinalsrc {

	public void verifyLink() throws Exception {

		writeExcelFinalsrc objwriteExcel = new writeExcelFinalsrc();

		String path = System.getProperty("user.dir");
		String filename = "\\Data\\BrokenLink_Result.xls";
		String sheetValidURL = "ValidURL";
		String BrokenURL = "BrokenURL";
		String sheetotherDomain = "OtherDomainURL";
		String sheetNullURL = "NullURL";
		String sheetHttpHead = "HttpHeader";
		String sheetAllLinks = "AllLinks";
		String columName = "PageURL";
		String SheetURL = "Input";

		Excel_Reader readexcel = new Excel_Reader(path + filename);

		String homeURL = readexcel.getCellData(SheetURL, columName, 2);

		String homePage = homeURL;
		String url = "";
		// String url1 = "http://";
		HttpURLConnection huc = null;
		int respCode = 200;

		WebDriver driver;

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(homePage);

		System.out.println(driver.getTitle());

		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}

		Iterator<WebElement> it = links.iterator();

		int count = 0;

		while (it.hasNext()) {

			System.out.println(count);

			url = it.next().getAttribute("href");

//        System.out.println(url);

		//	objwriteExcel.write_to_Excel(path, filename, sheetAllLinks, url,respCode);

			try {
				if (url == null || url.isEmpty()) {

					objwriteExcel.write_to_Excel(path, filename, sheetNullURL, url);

					System.out.println("URL is either not configured for anchor tag or it is empty");
					continue;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				if (!url.startsWith(homePage)) {
					objwriteExcel.write_to_Excel(path, filename, sheetotherDomain, url);
					System.out.println(url + "" + "other domain");
					System.out.println("URL belongs to another domain, skipping it.");
					continue;
				}
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if ((respCode >= 400) || !url.isEmpty()) {
					
					objwriteExcel.write_to_Excel(path, filename, BrokenURL, url);
					System.out.println(url + " is a broken link" + " " + respCode);
				} else {
					objwriteExcel.write_to_Excel(path, filename, sheetValidURL, url);
					System.out.println(url + " is a valid link" + " " + respCode);

				}

				//url = "http://test/index.html";
				
				if (url.contains("http://")) {
					objwriteExcel.write_to_Excel(path, filename, sheetHttpHead, url);
					System.out.println(url + " is a HTTP request" + " " + respCode);
				}

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// count++;
		}

		driver.quit();
	}
}
