package ProBook;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class LoginTest {
	WebDriver driver ;
	String url = "https://probook.progideo.com";
	String profile = "Ghizlane talaoui";
	String profileactual= null;

@DataProvider(name="Data1")
public static Object[][]data1(){
	return new Object[][] {{"talaoui","talaoui24","OK"},{"talaoui2","12345","KO"}};
	
}

  @Test(dataProvider="Data1")
  public void login(String userNamme,String passWord,String state) {
	  WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(10));
	  driver.findElement(By.className("btn-enter")).click();
		
		driver.switchTo().activeElement();
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login_username")));
		// On saisit le username et le password
		driver.findElement(By.id("login_username")).click();
		driver.findElement(By.id("login_username")).sendKeys(userNamme);
		driver.findElement(By.id("login_password")).sendKeys(passWord);
		// On clique sur le bouton "Sign in"
		driver.findElement(By.id("login-button")).click();
		
		if(state=="OK") {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("account-dropdown-link")));
		profileactual=driver.findElement(By.id("account-dropdown-link")).getText();
		assertEquals(profileactual,profile);
		}else {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='help-block']")));
			System.out.println(driver.findElement(By.xpath("//div[@class='help-block']")).getText());
		}
	  
  }
  //@Parameters("browser")
  @BeforeMethod
  public void beforeMethod() {
	  String browser = "firefox";
	  if(browser.equalsIgnoreCase("firefox")) {
	  
		  System.setProperty("webdriver.gecko.driver","/usr/local/bin/geckodriver");
		  FirefoxOptions option=new FirefoxOptions();
		  option.addArguments("--headless");
	  driver= new FirefoxDriver(option);
	}else if(browser.equalsIgnoreCase("chrome")) {
		
		 System.setProperty("webdriver.gecko.driver","C:\\Users\\FORMATION\\Downloads\\Formation SE\\geckodriver.exe");
		  driver= new ChromeDriver();
	}
	  driver.get(url);

  }

  @AfterMethod
  public void afterMethod() {
	   
	  driver.close();
	  
  }

}
