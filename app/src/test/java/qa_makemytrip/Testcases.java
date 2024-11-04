package qa_makemytrip;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;

public class Testcases {
 WebDriver driver;
    public Testcases(){
      WebDriverManager.chromedriver().setup();
      driver= new ChromeDriver();
    }
   @AfterTest 
    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();
    }

    @Test(enabled=true)
     public void Testcase01()throws InterruptedException, IOException{
      System.out.println("Start testcase: 01");
        driver.get("https://www.lambdatest.com/");
        driver.manage().window().maximize();
        // Thread.sleep(4000);
        WebElement login = driver.findElement(By.xpath("//a[text()='Login']"));
        Actions ac = new Actions(driver);
        ac.moveToElement(login).perform();
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // driver.navigate().back();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(3000);

        String timestamp = String.valueOf(java.time.LocalDateTime.now());
        String screenshot = String.format("screenshot_%s.png", timestamp);
        ru.yandex.qatools.ashot.Screenshot scr = new AShot(). takeScreenshot(driver);
        ImageIO.write(scr.getImage(), "png", new File("C:\\Users\\X280\\Documents\\Crio Projects\\screenshot.png"));
        System.out.println(screenshot);

       System.out.println("End testcase: 01");
    }

    @Test(enabled=true)
    public void Testcase02()throws InterruptedException, IOException{
     System.out.println("Start testcase: 02");
        driver.get("https://www.flipkart.com/");
        driver.manage().window().maximize();
        WebElement search = driver.findElement(By.xpath("//input[@type='text']"));
        search.sendKeys("Mobile");
        search.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        List<WebElement> phoneRows = driver.findElements(By.xpath("//div[@class='yKfJKb row']"));
        HashMap<Integer,String> iphoneTitleDiscountMap = new HashMap<>();
        for(WebElement phoneRow:phoneRows){
            String discountpercent = phoneRow.findElement(By.xpath(".//div[@class='UkUFwK']/span")).getText();
            int disresult = Integer.parseInt(discountpercent.replaceAll("[^0-9]",""));
            if(disresult>17){
            String phoneTitle = phoneRow.findElement(By.xpath(".//div[contains(@class,'KzDlHZ')]")).getText();
            iphoneTitleDiscountMap.put(disresult,phoneTitle);
            }
        }
             for(Map.Entry<Integer, String> m:iphoneTitleDiscountMap.entrySet()){
                System.out.println("Phone discountpercentage: "+m.getKey()+" "+"Phone Title: "+m.getValue());
                
            }
            System.out.println("End testcase: 02");
        }
    //    List<WebElement> discountRates = driver.findElements(By.xpath("//div[@class='yKfJKb row']/ancestor::div//div[@class='UkUFwK']"));
    //    for(WebElement discountRate:discountRates){
    //     String discounttxts = discountRate.getText();
    //     int result = Integer.parseInt(discounttxts.replaceAll("[^0-9]",""));
    //     if(result>17){
    //      System.out.println("DiscountRate perentage more than 17: " + result);
    //     }
      

    @Test(enabled=true)
    public void Testcase03()throws InterruptedException, IOException{
     System.out.println("Start testcase: 03");
     driver.get("https://www.scrapethissite.com/pages/");
     driver.manage().window().maximize();
     WebElement oscarwinning = driver.findElement(By.xpath("//a[contains(text(),'Oscar Winning Films')]"));
     oscarwinning.click();
     Thread.sleep(3000);
     WebElement yearElement = driver.findElement(By.xpath("//a[text()='2015']"));
     WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
     wait.until(ExpectedConditions.elementToBeClickable(yearElement));
     yearElement.click();
     Thread.sleep(4000);
     List<WebElement> rowsdataElement = driver.findElements(By.xpath("//tr[position()<6]"));
      for(WebElement rowdata:rowsdataElement){
      String txtdata = rowdata.getText();
      System.out.println(txtdata);
      }
     System.out.println("End testcase: 03");
     }
    @Test(enabled=true)
    public void Testcase04()throws InterruptedException, IOException{
        System.out.println("Start testcase: 04");
        driver.get("https://www.irctc.co.in/nget/train-search");
        Actions ac = new Actions(driver);
        ac.sendKeys(Keys.ESCAPE);
        WebElement vigElement = driver.findElement(By.xpath("//button[contains(@aria-label,'Confirmation. Vigilance')]"));
        vigElement.click();
        Thread.sleep(3000);
        WebElement frame1 = driver.findElement(By.xpath("//iframe[contains(@name,'https://irctclive.nlpcaptcha.in')]"));
        driver.switchTo().frame(frame1 );
        WebElement srcElement = driver.findElement(By.xpath("//div[contains(@class,'cubeBox')]/ancestor::div//a/img"));
        String txt = srcElement.getAttribute("src");
        System.out.println(txt);
        srcElement.click();
        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(2000);
        driver.close();
        System.out.println("End testcase: 04");
        driver.quit();
    }
}

