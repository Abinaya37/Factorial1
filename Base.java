package com.MyProject;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Base {

	public static WebDriver driver;

	public static void main(String[] args) throws IOException {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Lenovo\\eclipse-workspace\\Selenium_Project\\Driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://adactinhotelapp.com/");

		driver.manage().window().maximize();

		implicitly_Wait();

		//login module
				
		WebElement username = driver.findElement(By.xpath("//input[@id='username']"));

		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		
		//&& -->and operator   || ---> or operator
		
		if (username.isEnabled() || password.isEnabled()) {

			username.sendKeys("Abinaya37");

			password.sendKeys("W4326B");
		}
		
		get_Title();

		screenshot("login page");
		System.out.println("Login page screenshot done");

		WebElement login = driver.findElement(By.xpath("//input[@type='Submit']"));
		click(login);
		
		//Search Hotel module

		get_Title();

		WebElement location = driver.findElement(By.xpath("//select[@name='location']"));
		dropdown_Index(location, 5);

		WebElement hotel = driver.findElement(By.xpath("//select[@name='hotels']"));
		dropdown_Value(hotel, "Hotel Hervey");

		WebElement room_Type = driver.findElement(By.xpath("//select[@name='room_type']"));
		dropdown_Text(room_Type, "Deluxe");

		WebElement room_No = driver.findElement(By.xpath("//select[@name='room_nos']"));
		dropdown_Index(room_No, 2);

		WebElement checkin = driver.findElement(By.xpath("(//input[@type='text'])[2]"));
		clear(checkin);
		send_Values(checkin, "15/11/2022");

		WebElement checkout = driver.findElement(By.xpath("//input[@name='datepick_out']"));
		clear(checkout);
		send_Values(checkout, "30/11/2022");

		WebElement adult_Room = driver.findElement(By.xpath("//select[@name='adult_room']"));
		dropdown_Index(adult_Room, 3);

		WebElement child_Room = driver.findElement(By.xpath("//select[@name='child_room']"));
		dropdown_Value(child_Room, "2");

		screenshot("search page");
		System.out.println("Search page screenshot done");

		WebElement search = driver.findElement(By.id("Submit"));
		click(search);

		WebElement radio_Button = driver.findElement(By.xpath("//input[@type='radio']"));
		click(radio_Button);

		get_Title();

		
		WebElement button = driver.findElement(By.xpath("//input[@value='Continue']"));
		click(button);

		
		//booking hotel module
		
		WebElement first_Name = driver.findElement(By.xpath("//input[@name='first_name']"));
		send_Values(first_Name, "Abinaya");

		get_Title();

		WebElement last_Name = driver.findElement(By.xpath("//input[@name='last_name']"));
		send_Values(last_Name, "Nachiyappan");

		WebElement address = driver.findElement(By.id("address"));
		send_Values(address, "chennai");

		WebElement cc = driver.findElement(By.id("cc_num"));
		send_Values(cc, "1234567890123456");

		WebElement cc_Type = driver.findElement(By.id("cc_type"));
		dropdown_Index(cc_Type, 3);

		WebElement month = driver.findElement(By.id("cc_exp_month"));
		dropdown_Value(month, "9");

		WebElement year = driver.findElement(By.id("cc_exp_year"));
		dropdown_Text(year, "2022");

		WebElement ccv = driver.findElement(By.id("cc_cvv"));
		send_Values(ccv, "123");

		screenshot("booking page");
		System.out.println("Booking page Screenshot done");

		WebElement book_Now = driver.findElement(By.id("book_now"));
		click(book_Now);

		WebElement my_Iternary = driver.findElement(By.xpath("//a[.='Booked Itinerary']"));
		click(my_Iternary);

		WebElement check_Box = driver.findElement(By.xpath("(//input[@type='checkbox'])[2]"));
		click(check_Box);

		WebElement third_Box = driver.findElement(By.xpath("(//input[@type='checkbox'])[4]"));
		click(third_Box);

		WebElement cancel = driver.findElement(By.name("cancelall"));
		cancel.click();

		okay();

		WebElement logout = driver.findElement(By.id("logout"));
		click(logout);

		get_Title();

		screenshot("logout page");
		System.out.println("Logout page screenshot done");
	}

	public static void screenshot(String name) throws IOException {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File des = new File("C:\\Users\\Lenovo\\eclipse-workspace\\Selenium_Demo\\Screenshot\\" + name +".png");
		FileUtils.copyFile(src, des);
	}

	public static void okay() {

		driver.switchTo().alert().accept();
	}

	public static void click(WebElement element) {

		element.click();
	}

	public static void clear(WebElement element) {

		element.clear();
	}

	public static void implicitly_Wait() {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	}

	public static String get_Title() {

		String title = driver.getTitle();
		
		return title;
		
	}

	public static void dropdown_Index(WebElement element, int index) {

		Select s = new Select(element);
		s.selectByIndex(index);
	}

	public static void dropdown_Value(WebElement element, String name) {
		Select s = new Select(element);
		s.selectByValue(name);
	}

	public static void dropdown_Text(WebElement element, String text) {

		Select s = new Select(element);
		s.selectByVisibleText(text);
	}

	public static void send_Values(WebElement element, String values) {

		element.sendKeys(values);
	}
}
