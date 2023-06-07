import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class NewOrder {

    public static void main(String[] args) throws InterruptedException {


        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");

        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        Thread.sleep(1000);
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
        Thread.sleep(1000);
        driver.findElement(By.name("ctl00$MainContent$login_button")).click();

        driver.findElement(By.linkText("Order")).click();
        int quantity = (new Random().nextInt(100));
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(quantity+"");
        Thread.sleep(1000);


        driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(1000);
        int total;
        if(quantity<=10){
            total = quantity*100;
            Assert.assertEquals(driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getAttribute("value"),total+"" );
        }else {
            total = (int)(quantity*100 - quantity*100*0.08);
            Assert.assertEquals(driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal")).getAttribute("value"),total+"" );
        }
        
        Thread.sleep(1000);

        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();

        driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(firstName+" "+lastName);
        Thread.sleep(1000);

        //Faker faker = new Faker();
        String streetAddress = faker.address().streetAddress();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys(streetAddress);
        Thread.sleep(1000);

        String city = faker.address().city();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys(city);
        Thread.sleep(1000);

        String state = faker.address().state();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys(state);
        Thread.sleep(1000);

        String zip = new Random().nextInt(99999)+"";

        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(zip);
        Thread.sleep(1000);

        List<WebElement> radioButtons = driver.findElements(By.xpath("//input[@type='radio']"));
        Thread.sleep(1000);
        radioButtons.get(new Random().nextInt(3)).click();
        Thread.sleep(1000);
        StringBuilder cardNumber = new StringBuilder();
        Random random = new Random();
        //String card

        if(radioButtons.get(0).isSelected()){
            cardNumber.append(4);
            for (int i = 0; i < 15; i++) {
                int digit = random.nextInt(10);
                cardNumber.append(digit);
            }
            cardNumber.toString();

        } else if (radioButtons.get(1).isSelected()){
            cardNumber.append(5);
            for (int i = 0; i < 15; i++) {
                int digit = random.nextInt(10);
                cardNumber.append(digit);
            }
            cardNumber.toString();
        } else{
            cardNumber.append(3);
            for (int i = 0; i < 14; i++) {
                int digit = random.nextInt(10);
                cardNumber.append(digit);
            }
            cardNumber.toString();
        }


        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(cardNumber);
        Thread.sleep(1000);
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys("01/24");
        Thread.sleep(1000);
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
        Thread.sleep(1000);

        Assert.assertTrue(driver.getPageSource().contains("New order has been successfully added."));

        driver.findElement(By.linkText("View all orders")).click();
        Thread.sleep(1000);


        driver.findElement(By.cssSelector("input[src='App_Themes/Default/images/button_edit.gif'")).click();

        Assert.assertEquals(driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).getAttribute("value") ,firstName+" "+lastName);
        Thread.sleep(1000);
       Assert.assertEquals(driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).getAttribute("value") ,streetAddress);
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).getAttribute("value") ,city);
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).getAttribute("value") ,state);
        Thread.sleep(1000);
        Assert.assertEquals(driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).getAttribute("value") ,zip);
        Thread.sleep(1000);

        driver.findElement(By.id("ctl00_logout")).click();
        driver.quit();












    }
}
