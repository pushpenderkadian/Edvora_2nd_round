//Test case TC007: User stays on the same page after registering .

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

//main class
public class TC007{

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
		
		System.out.println("Test case TC007 starting : \n");

        //opening link and registering account
		driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/r");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello"); 
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hi12");
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        // waiting for alert
        TimeUnit.SECONDS.sleep(5);
        try {
            //checking if alert is present or not
            if(ExpectedConditions.alertIsPresent() != null){
                Alert alert=driver.switchTo().alert();
                if((alert.getText()).contains("something went wrong")){
                    System.out.println("Alert with Message \" "+alert.getText()+" \" \n");
                }
                if((alert.getText()).contains("Account scussfully created") || (alert.getText()).contains("Account sucessfully created")){
                    System.out.println("Alert with Message \" "+alert.getText()+" \" \n");
                }
                alert.accept();
            }
        } catch (Exception e) {
        }
        TimeUnit.SECONDS.sleep(5);

        //checking current page of the user
        if(((driver.getCurrentUrl()).compareTo("https://testing-assessment-foh15kew9-edvora.vercel.app/r"))==0){
            System.out.println("\nTest case Success User is on same page after registering");
        }
        else{
            //taking screenshot of failed test case
            try {
                TakesScreenshot scrShot =((TakesScreenshot)driver);
                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
                File DestFile=new File("./Test/src/TC007.png");
                FileUtils.copyFile(SrcFile, DestFile);
            } catch (Exception e) {
                try {
                    BufferedImage image = new Robot().createScreenCapture(new Rectangle(500,800));
                    ImageIO.write(image, "png", new File("./Test/src/TC007.png"));
                } catch (Exception E) {
                    System.out.println("Screenshot not saved");
                } 
            }
            System.out.println("\nTest case Failed User is not on the same page after registering");
        }
        
        driver.quit();
    }
}