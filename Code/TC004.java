//Test case TC004: User able to give spaces in full name field inside settings page

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
public class TC004{
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
		System.out.println("Test case TC004 starting : \n");
        String Fname="h ii";

        // opening link in browser
		driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");

        //loging into account
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        TimeUnit.SECONDS.sleep(8);

        // checking login is success or not to proceed further
        if(((driver.getCurrentUrl()).compareTo("https://testing-assessment-foh15kew9-edvora.vercel.app/s"))==0){
            System.out.println("\nlogin Success Changing Full Name");
            driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[2]/button")).click();
            WebElement fname=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div/input"));
            int l=fname.getAttribute("value").length();
            for(int i=0;i<l-1;i++){
                fname.sendKeys(Keys.BACK_SPACE);
            }
            fname.sendKeys(Fname);

            //clicking on save button
            driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[2]/button[2]")).click();
            TimeUnit.SECONDS.sleep(8);

            // confirming the save operation
            if(((driver.getCurrentUrl()).compareTo("https://manike.com/"))==0){
                System.out.println("Changes are saved Successfully\nVerifing the change");
                //logging back again to check value is changed or not
                driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
                driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
                driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
                driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
                TimeUnit.SECONDS.sleep(8);
                String current_value=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div/input")).getAttribute("value");
                
                //displaying the result of operation
                if(Fname.equals(current_value)){
                    System.out.println("\n\nTest case is Success User is able to pass spaces in Full name");
                }
                else{
                    // taking Screenshot of failed error
                    
                    try {
                        TakesScreenshot scrShot =((TakesScreenshot)driver);
                        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
                        File DestFile=new File("./Test/src/TC004.png");
                        FileUtils.copyFile(SrcFile, DestFile);
                    } catch (Exception e) {
                        try {
                            BufferedImage image = new Robot().createScreenCapture(new Rectangle(500,800));
                            ImageIO.write(image, "png", new File("./Test/src/TC004.png"));
                        } catch (Exception E) {
                            System.out.println("Screenshot not saved");
                        } 
                    }
                    System.out.println("\n\nTest case is Failed\nUser is not able to give spaces in full name ");
                }
            }

        }
        driver.quit();
    }
}
       