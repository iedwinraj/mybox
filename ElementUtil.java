package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.factory.DriverFactory;

public class ElementUtil {

	private WebDriver driver;
	private Actions act;
	private JavaScriptUtil JsUtil;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
		act = new Actions(this.driver);
		JsUtil = new JavaScriptUtil(this.driver);
	}

	public WebElement getElement(By locator) {
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			JsUtil.flash(ele);
		}
		return ele;
	}
	
	public WebElement clickMenu(By locator) {
		WebElement ele = driver.findElement(locator);
		if(Boolean.parseBoolean(DriverFactory.highlight)) {
			JsUtil.flash(ele);
			JsUtil.clickElementByJS(ele);
		}
		return ele;
	}
	
	public void waitforelement() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException|ElementClickInterceptedException e) {
			// TODO Auto-generated catch block
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public void doSendKeys(By locator, String value) {
		WebElement ele = getElement(locator);
		ele.clear();
		ele.sendKeys(value);
	}

	public void doClick(By locator) {
		getElement(locator).click();
	}

	public String doGetElementText(By locator) {
		return getElement(locator).getText();
	}

	public boolean doIsDisplayed(By locator,int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return getElement(locator).isDisplayed();
	}

	public void doLinkClick(By locator, String value) {
		List<WebElement> linksList = getElements(locator);
		System.out.println("total links : " + linksList.size());
		for (WebElement e : linksList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}
	
	public void StatusbasedSelections(By locator, String value) {
		List<WebElement> linksList = getElements(locator);
		System.out.println("total links : " + linksList.size());
		for (WebElement e : linksList) {
			String text = e.getText();
			System.out.println(text);
			if (text.contains(value)) {
				e.click();
				break;
			}
		}
	}
	
	
	public String StatusbasedSelections1(By locator1, String value1,By locator2,By locator3, String value2) {
		String text2="";
		try {
			List<WebElement> linksList1 = getElements(locator1);
			List<WebElement> linksList2 = getElements(locator2);
			List<WebElement> linksList3 = getElements(locator3);
			System.out.println("total links : " + linksList1.size());
			int a;			
			for ( a = 0; a < linksList1.size(); a++) {
				String text = linksList1.get(a).getText();
				System.out.println(text);
				if (text.contains(value2)) {
					linksList1.get(a).click();
					break;
			}
			}
			int b=a;
			System.out.println(a);
			int i=0;
			for (WebElement e : linksList2) {
				int j=0;
				String text = e.getText();
//				System.out.println(text);
				if (text.equals(value2)) {
					for (WebElement e1 : linksList3) {
						
						if(i==j) {
						text2= e1.getText();
						waitforelement();
						linksList3.get(i).click();
						waitforelement();
						linksList3.clear();
						linksList2.clear();
						break;
						}
						j++;
						
					
					
				}}
				i++;
				
			}
			
			
			
		} catch (ConcurrentModificationException e) {
			// TODO: handle exception
		}
		
		return text2;
		
	}
	
	
	
	
	public String doGetElementText1(By locator,By locator1, String value) {
		String text2="";
		try {
			List<WebElement> linksList = getElements(locator);
			List<WebElement> linksList2 = getElements(locator1);
			System.out.println("total links : " + linksList.size());
			int i=0;
			for (WebElement e : linksList) {
				int j=0;
				String text = e.getText();
//				System.out.println(text);
				if (text.equals(value)) {
					for (WebElement e1 : linksList2) {
						
						if(i==j) {
						text2= e1.getText();
						waitforelement();
						linksList2.get(i).click();
						waitforelement();
						linksList2.clear();
						linksList.clear();
						break;
						}
						j++;
						
					
					
				}}
				i++;
				
			}
		} catch (ConcurrentModificationException e) {
			// TODO: handle exception
		}
		return text2;
		
		
	}
	
	public String doGetElementText2(By locator, By locator1,String value2, By locator2) {
		List<WebElement> linksList = getElements(locator);
		List<WebElement> linksList2 = getElements(locator1);
		System.out.println("total links : " + linksList.size());
		System.out.println("total links : " + linksList2.size());
		for (int i = 0; i < linksList.size(); i++) {
			String text = linksList.get(i).getText();
			System.out.println(text);
			if (text.equals(value2)) {
				String text2 = linksList2.get(i).getText();
				System.out.println(text2);				
			}
		}
		
		return getElement(locator1).getText();
	}
	
	
	
	// **********************************Drop Down Utils *************************

	public void doSelectByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public void doSelectByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public List<String> getDropDownOptionsList(By locator) {
		Select select = new Select(getElement(locator));
		List<String> optionsTextList = new ArrayList<String>();
		List<WebElement> optionsList = select.getOptions();
		// System.out.println(optionsList.size());
		for (WebElement e : optionsList) {
			optionsTextList.add(e.getText());
		}
		return optionsTextList;
	}

	public void doSelectByTextOption(By locator, String text) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			if (e.getText().equals(text)) {
				e.click();
				break;
			}
		}
	}

	/**
	 * this method is used to select the value from drop down without select class
	 * 
	 * @param locator
	 * @param value
	 */
	public void doSelectDropDownWithoutSelectClass(By locator, String value) {
		List<WebElement> optionsList = getElements(locator);
		for (WebElement e : optionsList) {
			if (e.getText().equals(value)) {
				e.click();
				break;
			}
		}
	}

	// *************************Actions Class Utils *************
	// 2 level manu and sub menu
	public void doMoveToElement(By locator) {
		act.moveToElement(getElement(locator)).perform();
	}

	// 3 level
	public void doMoveToElement(By locator1, By locator2) {
		act.moveToElement(getElement(locator1)).perform();
		act.moveToElement(getElement(locator2)).perform();

	}

	// 3 level with click
	public void doMoveToElement(By locator1, By locator2, By locator3) {
		act.moveToElement(getElement(locator1)).perform();
		act.moveToElement(getElement(locator2)).perform();
		doClick(locator3);
	}

	public void doActionsClick(By locator) {
		act.click(getElement(locator)).perform();
	}

	public void doActionsSendKeys(By locator, String value) {
		act.sendKeys(getElement(locator), value).perform();
	}

	// ****************Calendar Util*************

	public void selectDate(String day, String tagName) {
		String xpath = "//" + tagName + "[text()='" + day + "']";
		doClick(By.xpath(xpath));
	}

	public void selectDate(By locator, String day) {
		boolean flag = false;
		List<WebElement> daysList = getElements(locator);
		for (WebElement e : daysList) {
			if (e.getText().equals(day)) {
				System.out.println("right date...." + day);
				e.click();
				flag = true;
				break;
			}
		}

		if (!flag) {
			System.out.println("wrong date..." + day);
		}
	}

	// *****************************Wait Util ***************************

	public Alert waitForAlertPresent(int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public String getAlertText(int timeOut) {
		String text = waitForAlertPresent(timeOut).getText();
		System.out.println(text);
		acceptAlert(timeOut);
		return text;
	}

	public void acceptAlert(int timeOut) {
		waitForAlertPresent(timeOut).accept();
	}

	public void dismissAlert(int timeOut) {
		waitForAlertPresent(timeOut).dismiss();
	}

	public String waitForTitleContains(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public String waitForTitleIs(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.titleIs(title));
		return driver.getTitle();
	}

	public boolean waitForTitle(int timeOut, String title) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.titleIs(title));
	}

	public WebElement waitForPresenceOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	/**
	 * An expectation for checking that an element, known to be present on the DOM
	 * of a page, is visible. Visibility means that the element is not only
	 * displayed but also has a height and width that is greater than 0.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForVisiblilityOfElement(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOf(getElement(locator)));
	}

	public WebElement waitForVisiblilityOfElementlocatedby(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfElementLocated((locator)));
	}	
				
	public List<WebElement> waitForVisiblilityOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void printElementsTexts(By locator, int timeOut) {
		waitForVisiblilityOfElements(locator, timeOut).stream().forEach(ele -> System.out.println(ele.getText()));
	}

	/**
	 * An expectation for checking an element is visible and enabled such that you
	 * can click it.
	 * 
	 * @param locator
	 * @param timeOut
	 * @return
	 */
	public WebElement waitForElementToBeClickable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public boolean waitForElementToBeVisibleAssert(By locator, int timeOut){
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
	//	return wait.until(ExpectedConditions.visibilityOf(element));
		if(wait.until(ExpectedConditions.visibilityOf(element)) != null){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean waitForElementToBeClickVisiblility(By locator, int timeOut){
		WebElement element = getElement(locator);
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
	//	return wait.until(ExpectedConditions.visibilityOf(element));
		if(wait.until(ExpectedConditions.elementToBeClickable(element))	!= null){
			return true;
		}else{
			return false;
		}
	}
	
	public void Pagedown(){
		try {
			Actions action = new Actions(driver);
		    
            //SCROLL DOWN
	action.sendKeys(Keys.PAGE_DOWN).sendKeys(Keys.PAGE_DOWN).build().perform();
		} 
         catch (Exception e) {
			// TODO: handle exception
		}   	
	}
	
	public void Pageup(){
		try {
			Actions action = new Actions(driver);
		    
            //SCROLL DOWN
	action.sendKeys(Keys.PAGE_UP).sendKeys(Keys.PAGE_UP).build().perform();
		} 
         catch (Exception e) {
			// TODO: handle exception
		}   	
	}
		
	public void mouseaction(By locator1) {	
		WebElement el1 = (getElement(locator1));
		//Used points class to get x and y coordinates of element.
		Point point = el1.getLocation();
		int xcord = point.getX();
		System.out.println("Position of the webelement from left side is "+xcord +" pixels");
		int ycord = point.getY();
		System.out.println("Position of the webelement from top side is "+ycord +" pixels");
		if (ycord > 1 && ycord < 200) {
			act.clickAndHold(el1);
			act.moveByOffset(0, 200).release().build().perform();
		}
		else if(ycord > 200 && ycord < 250) {
			act.clickAndHold(el1);
			act.moveByOffset(0, 30).release().build().perform();
		}
	}
	
	public void movemouseaction(By locator1) {	
		WebElement el1 = (getElement(locator1));
		//Used points class to get x and y coordinates of element.
		System.out.println(el1.getText());
		Point point = el1.getLocation();
		int xcord = point.getX();
		System.out.println("Position of the webelement from left side is "+xcord +" pixels");
		int ycord = point.getY();
		System.out.println("Position of the webelement from top side is "+ycord +" pixels");
		act.clickAndHold(el1);
		act.moveByOffset(0,100).release().build().perform();
	}
	
	
	public void getpostion(By locator1,By locator2) {	
		
		WebElement el2 = (getElement(locator2));
		//Used points class to get x and y coordinates of element.
		System.out.println(el2.getText());
		Point point1 = el2.getLocation();
		int xcord1 = point1.getX();
		System.out.println("Position of the webelement el2 from left side is "+xcord1 +" pixels");
		int ycord1 = point1.getY();
		System.out.println("Position of the webelement from el12 top side is "+ycord1 +" pixels");
		
		WebElement el1 = (getElement(locator1));
		//Used points class to get x and y coordinates of element.
		System.out.println(el1.getText());
		Point point = el1.getLocation();
		int xcord = point.getX();
		System.out.println("Position of the webelement from left side is "+xcord +" pixels");
		int ycord = point.getY();
		System.out.println("Position of the webelement from top side is "+ycord +" pixels");
		act.clickAndHold(el1);
//		act.moveToElement(el2).release().build().perform();
		act.moveByOffset(0,100).release().build().perform();
	}
	
	
	public void clickWhenReady(By locator, int timeOut) {
		try {
			waitForElementToBeClickable(locator, timeOut).click();
		} catch (Exception e) {
			// TODO: handle exception
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}{	try {
				waitForElementToBeClickable(locator, timeOut).click();
			} catch (ElementClickInterceptedException e2) {
				// TODO: handle exception
			}
				
			}
		}
			
	}

	public String getElementAttribute(By locator, int timeOut, String name) {
		return waitForElementToBeClickable(locator, timeOut).getAttribute(name);
	}

	public boolean waitForElementTextToBe(By locator, int timeOut, String value) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.textToBe(locator, value));
	}
	
	public boolean waitForElementTextToBePresentInElementLocated(By locator, int timeOut, String value) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, value));
	}
	
	public boolean waitFortextToBePresentInElementValue(By locator, int timeOut, String value) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.textToBePresentInElementValue(locator, value));
	}
	

	public boolean waitForUrlToBe(int timeOut, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlContains(urlValue));
	}

	public boolean waitForExactUrlToBe(int timeOut, String urlValue) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.urlToBe(urlValue));
	}

	/**
	 * An expectation for checking if the given element is selected.
	 * 
	 * @param timeOut
	 * @param urlValue
	 * @return
	 */
	public boolean waitForElementToBeSelected(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		return wait.until(ExpectedConditions.elementToBeSelected(locator));
	}

	public void waitForFrameToBeAvailable(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}
	
	public void waitForFrameToBeAvailable(String nameOrId, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrId));
	}

	public void waitForFrameToBeAvailable(int frameIndex, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
	}

	public WebElement waitForElementWithFluentWait(By locator, int timeOut, int pollingTime) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofSeconds(pollingTime))
				.ignoring(StaleElementReferenceException.class)
				.ignoring(NoSuchElementException.class);

		return wait.until(ExpectedConditions.presenceOfElementLocated(locator));

	}
	
	  public boolean alertPresent(By locator,int timeOut) 
	   { 
	   try 
	   { 
	    driver.switchTo().alert(); 
	    return true; 
	    }   
	   catch (NoAlertPresentException Ex) 
	   { 
	    return false; 
	    }    
	    } 
	  
	  public void waitForPageLoad() {

		  WebDriverWait wait = new WebDriverWait(driver, 30);
		    wait.until(new ExpectedCondition<Boolean>() {
		        public Boolean apply(WebDriver driver) {
		            System.out.println("Current Window State       : "
		                + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
		            return String
		                .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
		                .equals("complete");
		        }
		    });
		}
	  
	  public void WaitForAjax2Complete() throws InterruptedException
	  {
	      while (true)
	      {
	          if ((Boolean) ((JavascriptExecutor)driver).executeScript("return jQuery.active == 0")){
	              break;
	      }
	      Thread.sleep(1000);
	      }
	  }
	  
	 
	
}
