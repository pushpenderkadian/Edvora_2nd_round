//test case TC010: User able to go back after logging out using native browser back button.

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
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

//main class
public class TC010{

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
		System.out.println("Test case TC010 starting : \n");

        //opening url and logging into account
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        TimeUnit.SECONDS.sleep(8);
        //clicking on log out button
        System.out.println("Logout success \npressing back button of browser");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[3]/div[2]/div[2]/button")).click();
        TimeUnit.SECONDS.sleep(8);

        //pressing back button of browser
        driver.navigate().back();
        TimeUnit.SECONDS.sleep(2);

        //checking current page
        if((((driver.getCurrentUrl()).compareTo("https://testing-assessment-foh15kew9-edvora.vercel.app/s"))==0)){
            System.out.println("\nTest case Success User is able to go back to settings page with back button");
        }
        else{
            //taking screenshot of failed test case
            try {
                TakesScreenshot scrShot =((TakesScreenshot)driver);
                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
                File DestFile=new File("./Test/src/TC010.png");
                FileUtils.copyFile(SrcFile, DestFile);
            } catch (Exception e) {
                try {
                    BufferedImage image = new Robot().createScreenCapture(new Rectangle(500,800));
                    ImageIO.write(image, "png", new File("./Test/src/TC010.png"));
                } catch (Exception E) {
                    System.out.println("Screenshot not saved");
                } 
            }
            System.out.println("\nTest case Failed User is not able to go back to settings page with back button");
        }
        try{
            driver.quit();
        }
        catch(Exception e){
        }
    }
}
