//test case TC016: User is able to select other in Gender on Profile page

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Point;

//main class
public class TC016{
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
		System.out.println("Test case TC016 starting : \n");

        //openning link in browser and logging into account
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        TimeUnit.SECONDS.sleep(8);

        //clicking on edit button
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[2]/button")).click();
        WebElement gen=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]/select"));
        Select dd = new Select(gen);
        System.out.println("\nEditing value of gender\nVerifing value is changed or not");
        dd.selectByValue("other");
        TimeUnit.SECONDS.sleep(1);
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[2]/button[2]")).click();
        
        //verifing details of gender field
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        TimeUnit.SECONDS.sleep(8);
        gen=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[2]/select"));
        dd=new Select(gen);
        //confirming the change
        WebElement option=dd.getFirstSelectedOption();
        String current=option.getText();
        if(current.equals("other")){
            System.out.println("\nTest case Success User is able to select other in gender");
        }
        else{
            System.out.println("\nTest case Failed User is not able to select other in gender");
        }
        try {
            driver.quit();
        } catch (Exception e) {
        }
    }
}