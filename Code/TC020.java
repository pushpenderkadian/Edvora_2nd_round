//test case TC020:User is not able to edit personal info values by clicking on Edit P button from security section

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

//main class
public class TC020{
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
		System.out.println("Test case TC020 starting : \n");
        String mob="0123456789";

        //opening link in browser and logging into account
		driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        TimeUnit.SECONDS.sleep(8);
        //confirming the login is success or not
        if(((driver.getCurrentUrl()).compareTo("https://testing-assessment-foh15kew9-edvora.vercel.app/s"))==0){
            System.out.println("\nlogin Success clicking Edit P button from security section Changing mobile number");
            driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[2]/div[2]/div[2]/button")).click();
            WebElement mobile=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div/input"));
            int l=mobile.getAttribute("value").length();
            for(int i=0;i<l;i++){
                mobile.sendKeys(Keys.BACK_SPACE);
            }
            mobile.sendKeys(mob);
            //clicking on save button
            System.out.println("Clicking save button ");
            driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[2]/button[2]")).click();
            TimeUnit.SECONDS.sleep(8);

            // confirming save process success or not
            if(((driver.getCurrentUrl()).compareTo("https://manike.com/"))==0){
                System.out.println("Changes are saved Successfully\nVerifing the change");

                //logging again to verify the change
                driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
                driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
                driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
                driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
                TimeUnit.SECONDS.sleep(8);
                String current_value=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div/input")).getAttribute("value");
                
                //verifing the value
                if(mob.equals(current_value)){
                    // taking Screenshot of failed error
                    try {
                        TakesScreenshot scrShot =((TakesScreenshot)driver);
                        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
                        File DestFile=new File("./Test/src/TC020.png");
                        FileUtils.copyFile(SrcFile, DestFile);
                    } catch (Exception e) {
                        try {
                            BufferedImage image = new Robot().createScreenCapture(new Rectangle(500,800));
                            ImageIO.write(image, "png", new File("./Test/src/TC020.png"));
                        } catch (Exception E) {
                            System.out.println("Screenshot not saved");
                        } 
                    }
                    System.out.println("\nTest case is Failed User is able edit and save personal info values by clicking Edit P button from security section.");
                }
                else{                
                    System.out.println("\nTest case is Success User is not able edit and save personal info values by clicking Edit P button from security section.");
                }
            }

        }
        try{
            driver.quit();
        }
        catch(Exception e){
        }
    }   
}
		