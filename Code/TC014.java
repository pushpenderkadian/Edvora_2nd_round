//test case TC014: User is able to pass numeric values in address field on Profile page

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Point;

//main class
public class TC014{
    //main method
	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions(); 
		WebDriverManager.chromedriver().setup();
        //setting up driver with dynamic chrome driver
		WebDriver driver = new ChromeDriver(options);

        // setting brower window position and size
        driver.manage().window().setSize(new Dimension(500, 800));
        driver.manage().window().setPosition(new Point(0,0));
        System.out.println("========================================================================");
		System.out.println("Test case TC014 starting : \n");
        String value="12344545";

        //opening link in browser and logging into account
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        TimeUnit.SECONDS.sleep(8);

        //clicking edit button
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[2]/button")).click();
        TimeUnit.SECONDS.sleep(2);

        //findinvg address field and changing value
        System.out.println("\nchanging value in address field");
        WebElement addr=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div/input"));
        int l=addr.getAttribute("value").length();
        for(int i=0;i<l;i++){
            addr.sendKeys(Keys.BACK_SPACE);
        }
        addr.sendKeys(value);
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[2]/button[2]")).click();
        TimeUnit.SECONDS.sleep(5);
        // verifing the change in address
        System.out.println("verifing the change in value");

        //logging into account
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        TimeUnit.SECONDS.sleep(8);

        //finding address field
        String New=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[1]/div[1]/div[2]/div[3]/div/input")).getAttribute("Value");
        //confirming value
        if(value.equals(New)){
            System.out.println("\n\nTest case Success User is able to pass numeric values in address field");
        }
        else{
            System.out.println("\n\nTest case failed user is not able to pass numeric values in address field");
        }
        try {
            driver.quit();
        } catch (Exception e) {
        }
    }
}