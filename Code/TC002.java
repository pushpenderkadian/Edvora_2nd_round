// Test case TC002: User able to login after login after logging out consecutively

// importing libraries
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;


//main class
public class TC002{

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
		System.out.println("Test case TC002 starting : \n");

        // opening link in browser
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        boolean flag=false;


        try {
            // loop for login and logout consicutively 15 times
            for(int i=0;i<15;i++){
                driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
                driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
                driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
                TimeUnit.SECONDS.sleep(8);
                // checking if login is success or not
                if(((driver.getCurrentUrl()).compareTo("https://testing-assessment-foh15kew9-edvora.vercel.app/s"))==0){
                    System.out.println("\nAttempt "+(i+1)+" login Success");
                    System.out.println("Logging out");
                }
                else{
                    flag=false;
                    break;
                }
                // clicking on logout button
                driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[3]/div[2]/div[2]/button")).click();
                TimeUnit.SECONDS.sleep(2);

                //checking log out is success or not
                if(((driver.getCurrentUrl()).compareTo("https://testing-assessment-foh15kew9-edvora.vercel.app/"))==0)
                    flag=true;
                else{
                    flag=false;
                    break;
                }

            }
        //handling exception if any
        } catch (Exception e) {
            flag=false;
        }

        if(flag==true)
            System.out.println("\n\nTest case is Successfull User is able to login after logout consecutively");
        else{
            // taking Screenshot of failed testcase with error if present
            try {
                TakesScreenshot scrShot =((TakesScreenshot)driver);
                File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
                File DestFile=new File("./Test/src/TC002.png");
                FileUtils.copyFile(SrcFile, DestFile);
            } catch (Exception e) {
                try {
                    BufferedImage image = new Robot().createScreenCapture(new Rectangle(500,800));
                    ImageIO.write(image, "png", new File("./Test/src/TC002.png"));
                } catch (Exception E) {
                    System.out.println("Screenshot not saved");
                } 
            }
                        
            
            System.out.println("\n\nTest case Failed User is unable to login after logout consecutively");
        }
        driver.quit();
    }
}