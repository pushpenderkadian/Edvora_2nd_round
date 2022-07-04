//test case TC009: Users should not be able to open the settings page after logging out by using the url.

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
public class TC009{
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
		System.out.println("Test case TC009 starting : \n");

        //opeing link and logging into account
		driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello"); 
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
		driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        
        TimeUnit.SECONDS.sleep(5);

        //clicking on logout button
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[3]/div[2]/div[2]/button")).click();
        TimeUnit.SECONDS.sleep(5);

        //checing successful logout
        if(((driver.getCurrentUrl()).compareTo("https://testing-assessment-foh15kew9-edvora.vercel.app/"))==0){
            System.out.println("Logout is success");
        }

        //opening url
        System.out.println("Opening settings page from url");
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/s");
        TimeUnit.SECONDS.sleep(3);

        //storeing page source to confirm page
        String Page=driver.getPageSource();

        //confirming the page
        if(Page.contains("Your Profile") && (((driver.getCurrentUrl()).compareTo("https://testing-assessment-foh15kew9-edvora.vercel.app/s"))==0)){
            //taking screenshot of failed test case
            try {
                TakesScreenshot scrShot =((TakesScreenshot)driver);
                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
                File DestFile=new File("./Test/src/TC009.png");
                FileUtils.copyFile(SrcFile, DestFile);
            } catch (Exception e) {
                try {
                    BufferedImage image = new Robot().createScreenCapture(new Rectangle(500,800));
                    ImageIO.write(image, "png", new File("./Test/src/TC009.png"));
                } catch (Exception E) {
                    System.out.println("Screenshot not saved");
                } 
            }
            System.out.println("\nTest case Failed user is able to open the settings page after logging out by using the url");
        }
        else{
            System.out.println("\nTest case Success User is unable to open the settings page after logging out by using the url");
        }
        try{
            driver.quit();
        }
        catch(Exception e){
        }

    }

}