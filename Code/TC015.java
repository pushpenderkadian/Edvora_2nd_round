//test case TC015: User is able to change Date of Birth on Profile Setting page

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Point;

//main class
public class TC015{
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
		System.out.println("Test case TC015 starting : \n");
        String y="2001";
        String m="11";
        String d="11";

        //opening link in browser and logging into account
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        TimeUnit.SECONDS.sleep(8);

        //clicking on edit button
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[2]/button")).click();
        //finding date of birth field and sending values to it
        WebElement dob=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div/input"));
        if(Integer.parseInt(m)<2){
            dob.sendKeys(Integer.toString(Integer.parseInt(m)));
            dob.sendKeys(Keys.ARROW_RIGHT);
        }
        else{
            dob.sendKeys(Integer.toString(Integer.parseInt(m)));
        }
        if(Integer.parseInt(d)<10){
            dob.sendKeys(Integer.toString(Integer.parseInt(d)));
            dob.sendKeys(Keys.ARROW_RIGHT);
        }
        else{
            dob.sendKeys(Integer.toString(Integer.parseInt(d)));
        }
        if(Integer.parseInt(y)<1000){
            dob.sendKeys(Integer.toString(Integer.parseInt(y)));
            dob.sendKeys(Keys.ARROW_RIGHT);
        }
        else{
            dob.sendKeys(Integer.toString(Integer.parseInt(y)));
        }

        System.out.println("Value of Date of Birth changed successfully\nverifing it");
        //clicking on save button
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[2]/button[2]")).click();
        TimeUnit.SECONDS.sleep(5);

        //relogging into account to verify change
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        TimeUnit.SECONDS.sleep(8);

        //verifing value
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[2]/button")).click();
        dob=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div/input"));
        String dbformat=y+"-"+m+"-"+d;
        if(dob.getAttribute("value").contains(dbformat)){
            System.out.println("\n\nTest case Success User is able to change value of Date of Birth");
        }
        else{
            System.out.println("\n\nTest case Failed User is not able to change value of Date of Birth");
        }
        try {
            driver.quit();
        } catch (Exception e) {
        }
    }
}