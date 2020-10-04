package appiumtest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.TouchAction;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public class SuitRedReader {
	
	
	static AppiumDriver<MobileElement> driver;
	

	public static void main(String[] args) {
		try {
			openReader();
		} catch (Exception e) {
			System.out.println(e.getCause());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}
	private static void mostrarObjDisponibles( ) {
		List<MobileElement> elements = driver.findElements(MobileBy.xpath("//UIAWindow[1]/*"));
		for (MobileElement ele : elements) {
			String claseItem = ele.getClass().getName();
			String textoItem = ele.getText();
			System.out.println(" class "+claseItem+" texto "+textoItem);
		}
	}
	public static void openReader() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability("deviceName", "SM-A715F/DS");
		cap.setCapability("udid", "R58N21FFV3F");
		cap.setCapability("platformName", "Android");
		cap.setCapability("platformVersion", "10");
		cap.setCapability("apkPackage", "org.quantumbadger.redreader");
		cap.setCapability("apkActivity", "org.quantumbadger.redreader.activities.MainActivity");
		
		URL url = new URL("http://127.0.0.1:4723/wd/hub");
		driver = new AppiumDriver<MobileElement>(url, cap);
		System.out.println("Application started...");
		String ELEMENTO_UBICACION_PERSONALIZADA = "Ubicación personalizada";
		MobileElement itemGeneral = driver.findElement(By.id("org.quantumbadger.redreader:id/list_item_text"));
		//scroll(itemGeneral);
		//otroScroll();
		List<MobileElement> item = driver.findElements(By.id("org.quantumbadger.redreader:id/list_item_text"));
		int cont =0;
		System.out.println(" size item list "+item.size());
		while (item != null && ( cont < item.size() )) {
		    boolean sePudoDarClick = false;
			String claseItem = item.get(cont).getClass().getName();
			String textoItem = item.get(cont).getText();
			MobileElement mob = item.get(cont);
			System.out.println(" class "+claseItem+" texto "+textoItem);
			if( textoItem.equals(ELEMENTO_UBICACION_PERSONALIZADA)) {
				//Cuando se salta un objeto toca buscar nuevamente 
				item = driver.findElements(By.id("org.quantumbadger.redreader:id/list_item_text"));
				cont++;continue;
			}
			try {
				mob.click();
				sePudoDarClick = true;
			} catch( Exception ex) {
				ex.printStackTrace();
			}
			/*if(claseItem.contains("TextView")) {
				System.out.println("cont: "+cont);
			}else {
			System.out.println("cont: "+cont);
			
			item.get(cont).click();
			//System.out.println("subtitle Funciona------------------------------"+item.get(cont).getText());
			 MobileElement backArrow = driver.findElement(By.id("org.quantumbadger.redreader:id/actionbar_title_back_image"));
			 backArrow.click();
			}*/
			System.out.println(" se pudo dar click "+sePudoDarClick);
			mostrarObjDisponibles();
			if( sePudoDarClick ) {
				MobileElement backArrow = driver.findElement(By.id("org.quantumbadger.redreader:id/actionbar_title_back_image"));
				 backArrow.click();
			}
		    cont++;
		    
		    item = driver.findElements(By.id("org.quantumbadger.redreader:id/list_item_text"));
		    System.out.println(" count "+cont+" size "+item.size());
		    
		    if(cont >= (item.size() - 3)) {
		    	//scroll(itemGeneral);
		    	otroScroll();
		    }
		    //reintentos porque a veces no encuentro el objeto
		    while(  item.size() == 0) {
		    	item = driver.findElements(By.id("org.quantumbadger.redreader:id/list_item_text"));
		    	System.out.println(" count "+cont+" size "+item.size());
		    }
		    
	    }
		
		System.out.println(" fin ");
	    
	    
	   
	    	    
	    
	
	}

	public static void scroll(MobileElement element){
	    try {
			System.out.println("Pasando por scroll__________");
			JavascriptExecutor js = (JavascriptExecutor) driver;
		       HashMap<String, String> scrollObject = new HashMap<String,String>();
		       scrollObject.put("direction", "up");
		       scrollObject.put("strategy", "up");
		       scrollObject.put("selector", "up");
		       js.executeScript("mobile: scroll",scrollObject);
		       //driver.executeScript("mobile", "scroll", "direction" , "up");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void otroScroll() {
		System.out.println("Pasando por el otro scroll__________");
		String idDEAlgo = "jokes";
		WebElement element = driver.findElement(By.linkText(idDEAlgo));
		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		// actions.click();
		actions.perform();
	}
	
	
	
}
