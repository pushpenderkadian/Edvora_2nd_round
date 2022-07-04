//test case TC012: User is not able to type more than 5 characters in password field of login page

//importing libraries
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Point;

//main class
public class TC012{

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
		System.out.println("Test case TC012 starting : \n");

        //opening url in browser
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        String value="harrypotter";

        //finding password field
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).sendKeys(value); 
        System.out.println("sending keys\n");
        //checking value in password field
        if(value.equals((driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[2]/div/input")).getAttribute("value")))){
            System.out.println("Test case is failed user is able to type more than 5 characters");
        }
        else{
            System.out.println("Test case is Success User is not able to type more than 5 characters");
        }
        try{
            //exiting the browser
            driver.quit();
        }
        catch(Exception e){
        }
    }
}