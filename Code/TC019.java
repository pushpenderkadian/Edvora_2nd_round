//Test case TC019:User is able to type uppercase letters in Username field of login page


import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;

//main method
public class TC019{
    //main class
	public static void main(String[] args){
		ChromeOptions options = new ChromeOptions(); 
		WebDriverManager.chromedriver().setup();
        //setting up driver with dynamic chrome driver
		WebDriver driver = new ChromeDriver(options);

        // setting brower window position and size
        driver.manage().window().setSize(new Dimension(500, 800));
        driver.manage().window().setPosition(new Point(0,0));
        System.out.println("========================================================================");
		System.out.println("Test case TC019 starting : \n");
        String value="ABCD";

        //Opening link in browser 
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");

        //findinf user name field of page and sending uppercase values
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys(value);
        String new_val=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).getAttribute("value");
        
        //confirming values of the field
        if(new_val.equals(value)){
            
            System.out.println("Test case is Success User is able to type uppercase letters in Username field");
        }
        else{
            //taking screenshot if test case failed
            try {
                BufferedImage image = new Robot().createScreenCapture(new Rectangle(500,800));
                ImageIO.write(image, "png", new File("./Test/src/TC019.png"));
            } catch (Exception e) {
                try {
                    TakesScreenshot scrShot =((TakesScreenshot)driver);
                    File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
                    File DestFile=new File("./Test/src/TC019.png");
                    FileUtils.copyFile(SrcFile, DestFile);
                } catch (Exception E) {
                    System.out.println("Screenshot not saved");
                } 
            }
            System.out.println("Test case Failed User is not able to type uppercase letters in Username field");
        }
        try {
            driver.close();
        } catch (Exception e) {
        }
    }
}
        