import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        String chromeDriverPath = "C:/Users/techn/Desktop/CodeSeite/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("file:///C:/Users/techn/Desktop/CodeSeite/main.html");

        boolean codegeknackt = false;

        while (codegeknackt == false){
            for (int i = 0; i <= 9999; i++) {
                String currentUrl = driver.getCurrentUrl();
                String formattedNumber = String.format("%04d", i);
                WebElement inputfield = driver.findElement(By.id("code"));
                inputfield.sendKeys(formattedNumber);
                inputfield.sendKeys(Keys.ENTER);
                String nextUrl = driver.getCurrentUrl();
                if(!currentUrl.equals(nextUrl)){
                    System.out.println("Herzlichen Glückwunsch, der Code wurde geknackt!");
                    System.out.print("Der Code lautet: " + formattedNumber);
                    codegeknackt = true;
                    break;
                }
                else{
                    inputfield.clear();
                }
            }
        }
    }
}