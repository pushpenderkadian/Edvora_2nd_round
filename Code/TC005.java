// Test case TC005: User able to login after 10 minutes of staying in the login screen.

//importing libraries
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.concurrent.TimeUnit;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

// main class
public class TC005{

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
		System.out.println("Test case TC005 starting : \n");

        //openinh link in browser
		driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");

        //waiting on the page for 10 minutes
        TimeUnit.MINUTES.sleep(10);

        // checking if any alert is present or not 
        try {
            if(ExpectedConditions.alertIsPresent() != null){
                Alert alert=driver.switchTo().alert();
                if((alert.getText()).contains("Getting server resources failed")){
                    System.out.println("Alert is present with Error \" "+alert.getText()+" \" \nTrying to login after accepting ");
                }
                else{
                    System.out.println("Alert is present with Message \" "+alert.getText()+"\" ");
                }
                // accepting the alert
                alert.accept();
            }

            // logging into the account
            driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
            driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
            driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
            TimeUnit.SECONDS.sleep(8);

            // verifing the loging
            if(((driver.getCurrentUrl()).compareTo("https://testing-assessment-foh15kew9-edvora.vercel.app/s"))==0){
                System.out.println("\nTest case Success User is able to login after waiting for 10 minutes");
            }
        } catch (Exception e) {
            try {
                // trying to login again if any exception occurs
                if(((driver.getCurrentUrl()).compareTo("https://testing-assessment-foh15kew9-edvora.vercel.app/"))==0){
                    driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
                    driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
                    driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
                    TimeUnit.SECONDS.sleep(8);
                }
                //confirming the login
                if(((driver.getCurrentUrl()).compareTo("https://testing-assessment-foh15kew9-edvora.vercel.app/s"))==0){
                    System.out.println("\n\nTest case Success User is able to login after waiting for 10 minutes");
                }
            } catch (Exception E) {
                //taking screen shot of failed login
                try {
                    TakesScreenshot scrShot =((TakesScreenshot)driver);
                    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
                    File DestFile=new File("./Test/src/TC005.png");
                    FileUtils.copyFile(SrcFile, DestFile);
                } catch (Exception a) {
                    try {
                        BufferedImage image = new Robot().createScreenCapture(new Rectangle(500,800));
                        ImageIO.write(image, "png", new File("./Test/src/TC005.png"));
                    } catch (Exception d) {
                        System.out.println("Screenshot not saved");
                    } 
                }
                System.out.println("\n\nTest Case failed User is unable to login after waiting for 10 minutes");
            }
        }
        
    driver.quit();
    }
}