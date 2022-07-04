//test case TC006: After registering an account , there should be native browser popup .

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
public class TC006{
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
		System.out.println("Test case TC006 starting : \n");
        //opening url in browser
		driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/r");

        //logging into account
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello"); 
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        // waiting for alert
        TimeUnit.SECONDS.sleep(5);
        try {
            // checking alert
            if(ExpectedConditions.alertIsPresent() != null){
                Alert alert=driver.switchTo().alert();
                System.out.println("Alert is present with Message \" "+alert.getText()+" \" \nTest case Success ");
                //taking screenshot of alert 
                try {
                    BufferedImage image = new Robot().createScreenCapture(new Rectangle(500,800));
                    ImageIO.write(image, "png", new File("./Test/src/TC006.png"));
                } catch (Exception d) {
                    System.out.println("Screenshot not saved");
                } 
                alert.accept();
            }
        } catch (Exception e) {
            //taking screen shot if test case is failed
            try {
                TakesScreenshot scrShot =((TakesScreenshot)driver);
                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
                File DestFile=new File("./Test/src/TC006.png");
                FileUtils.copyFile(SrcFile, DestFile);
            } catch (Exception E) {
                try {
                    BufferedImage image = new Robot().createScreenCapture(new Rectangle(500,800));
                    ImageIO.write(image, "png", new File("./Test/src/TC006.png"));
                } catch (Exception d) {
                    System.out.println("Screenshot not saved");
                } 
            }
            System.out.println("Test case Failed No pop up is present on the page");
        }
        driver.quit();
    }
}