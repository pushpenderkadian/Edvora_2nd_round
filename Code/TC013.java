//test case TC013:User is able to change Username on Profile Setting page

//importing libraries
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Point;

//main class
public class TC013{
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
		System.out.println("Test case TC013 starting : \n");
        //opening link in browser and logging into account
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys("hello");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/button")).click();
        TimeUnit.SECONDS.sleep(8);
        // clicking edit button
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[2]/button")).click();
        // checking text field is enabled or not
        
        System.out.println("Checking username field is editable or not\n");
        Boolean check=driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div/input")).isEnabled();
        if(check==true){
            System.out.println("Test case Success user is able to change value of Username");
        }
        else{
            System.out.println("Test case Failed user is not able to change value of username");
        }
        try {
            driver.quit();
        } catch (Exception e) {
        }
    }
}