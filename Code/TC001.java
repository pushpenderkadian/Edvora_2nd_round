// Test case TC001: User able to create account using special symbol “@” in password field

// importing libraries
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Point;
import org.openqa.selenium.Dimension;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

// main class 
public class TC001{
    //main function
	public static void main(String[] args) throws InterruptedException {
		ChromeOptions options = new ChromeOptions(); 
		WebDriverManager.chromedriver().setup();
        //setting up driver with dynamic chrome driver
		WebDriver driver = new ChromeDriver(options);

        // setting brower window position and size
        driver.manage().window().setSize(new Dimension(500, 800));
        driver.manage().window().setPosition(new Point(0,0));
        System.out.println("========================================================================");
		System.out.println("Test case TC001 starting : \n");

        // opening url in browser
		driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/r");

        // Registering account with @ in password
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello"); 
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hi@12");
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        // waiting for alert
        TimeUnit.SECONDS.sleep(5);
        try {
            // checking if alert is present or not
            if(ExpectedConditions.alertIsPresent() != null){
                Alert alert=driver.switchTo().alert();
                if((alert.getText()).contains("something went wrong")){
                    System.out.println("Alert is present with Error \" "+alert.getText()+" \" \nTest case failed User is not able to create account");
                }
                if((alert.getText()).contains("Account scussfully created") || (alert.getText()).contains("Account sucessfully created")){
                    System.out.println("Alert is present with Message \" "+alert.getText()+" \" \nTest case Success User is able to create account");
                }
            }
        } catch (Exception e) {
            System.out.println("No pop up is present on the page");
        }

        // taking screenshot of alert and saving it
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(500,800));
            ImageIO.write(image, "png", new File("./Test/src/TC001.png"));
        } catch (Exception e) {
            System.out.println("Screenshot not saved");
        } 
        driver.quit();
	}

}