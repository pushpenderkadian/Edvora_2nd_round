//test case TC011: User is not able to type more than 5 characters in username field of login page

//importing libraries
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Point;

//main class
public class TC011{
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
		System.out.println("Test case TC011 starting : \n");

        //opening link in browser
        driver.get("https://testing-assessment-foh15kew9-edvora.vercel.app/");
        String value="harrypotter";
        // finding username field
        System.out.println("\nSending keys in the field\n");
        driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).sendKeys(value); 
        //checking and cofirming the value in the field
        if(value.equals((driver.findElement(By.xpath("//*[@id=\"__next\"]/div/main/div/div/div[1]/div/input")).getAttribute("value")))){
            System.out.println("Test case is failed user is able to type more than 5 characters");
        }
        else{
            System.out.println("Test case is Success User is not able to type more than 5 characters");
        }

        //quiting the browser
        try{
            driver.quit();
        }
        catch(Exception e){
        }
    }
}