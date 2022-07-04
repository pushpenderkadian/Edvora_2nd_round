//test case TC018:User is able to login on login page after clicking login button from sign in(register) page.

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
public class TC018{
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
		System.out.println("Test case TC018 starting : \n");
        
        //opening link in browser
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        //clicking sign in link from home page
        System.out.println("\nClicking Sign in button ");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/p[2]/button")).click();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Clicking Login button\ntrying logging into account");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/p[3]/button")).click();
        TimeUnit.SECONDS.sleep(1);
        //Logging into account
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        TimeUnit.SECONDS.sleep(8);
        try {
            //checking if any alert is present or not
            if(ExpectedConditions.alertIsPresent() != null){
                Alert alert=driver.switchTo().alert();
                System.out.println("Alert is present with Error \" "+alert.getText()+" \" \n");
                
                //taking screenshot of alert
                try {
                    BufferedImage image = new Robot().createScreenCapture(new Rectangle(500,800));
                    ImageIO.write(image, "png", new File("./Test/src/TC018.png"));
                } catch (Exception e) {
                    try {
                        TakesScreenshot scrShot =((TakesScreenshot)driver);
                        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
                        File DestFile=new File("./Test/src/TC018.png");
                        FileUtils.copyFile(SrcFile, DestFile);
                    } catch (Exception E) {
                        System.out.println("Screenshot not saved");
                    } 
                }
                alert.accept();
            }
        } catch (Exception e) {
            System.out.println();
        }
        //verifing login
        if(((driver.getCurrentUrl()).compareTo("https://testing-assessment-foh15kew9-edvora.vercel.app/s"))==0){
            System.out.println("\nTest case Success User is able to login after clicking login button from sign in(register) page.");
        }
        else{
            System.out.println("\nTest case Failed User is not able to login after clicking login button from sign in(register) page.");
        }
    }
}