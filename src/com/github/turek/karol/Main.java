package com.github.turek.karol;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {
	private WebDriver driver;
	private String url;
	private Actions actions;
	private WebDriverWait wait;
	
	@Before
	public void setUp(){
		driver = new FirefoxDriver();
		url = "http://145.239.81.157:8010/meal/create";
		actions = new Actions(driver);
		wait = new WebDriverWait(driver, 10L);
	}
	
	@Test
	public void tests(){
		driver.get(url);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='form_type_meal_name']")));
		WebElement mealName = driver.findElement(By.id("form_type_meal_name"));
		mealName.sendKeys("Jagniêcina pieczona z rozmarynem i tymiankiem");
		
		WebElement workName = driver.findElement(By.id("form_type_meal_workName"));
		workName.sendKeys("Jagniêcina pieczona");
		
		List<WebElement> inputs = driver.findElements(By.className("multiselect__input"));
		WebElement mealNameInput = inputs.get(0);
		clickInput(mealNameInput, "//ul[@class='multiselect__content']//span[text()='delikatne']");
		actions
			.moveToElement(mealNameInput)
			.sendKeys(Keys.TAB)
			.build().perform();
		clickInput(mealNameInput, "//ul[@class='multiselect__content']//span[text()='miêsne']");
		
		WebElement workNameInput = inputs.get(1);
		clickInput(workNameInput, "//ul[@class='multiselect__content']//span[text()='du¿o miêsa']");
		
		WebElement elementsInput = inputs.get(2);
		clickInput(elementsInput, "//ul[@class='multiselect__content']//span[text()='Tad Lebsack']");
		
		WebElement recipe = driver.findElement(By.id("form_type_meal_recipe"));
		recipe.sendKeys("Jagniêcinê umyæ, osuszyæ papierowym rêcznikiem");
		
		WebElement type = inputs.get(3);
		clickInput(type, "//ul[@class='multiselect__content']//span[text()='obiad']");
		
		WebElement mark = inputs.get(4);
		clickInput(mark, "//ul[@class='multiselect__content']//span[text()='Bernier, Reilly and Roberts']");
		
		WebElement mealType = inputs.get(5);
		clickInput(mealType, "//ul[@class='multiselect__content']//span[text()='Kuchenka']");
		
		WebElement save = driver.findElement(By.id("button-save-and-back"));
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("scroll(0, 250)");
		save.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='grid_meal__name__query__from']")));
		WebElement findName = driver.findElement(By.xpath("//input[@id='grid_meal__name__query__from']"));
		findName.clear();
		findName.sendKeys("Jagniêcina pieczona", Keys.ENTER);
		
		wait.until(ExpectedConditions.elementToBeClickable(findName));
		WebElement typeFind = driver.findElement(By.name("grid_meal[kind][from]"));
		typeFind.clear();
		typeFind.sendKeys("Posi³ek sk³adowy", Keys.ENTER);
		
		wait.until(ExpectedConditions.elementToBeClickable(findName));
		typeFind.clear();
		findName.clear();
		findName.sendKeys("Jagniêcina pieczona z rozmarynem i tymiankiem", Keys.ENTER);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='grid_meal__name__query__from']")));
		driver.findElement(By.xpath("//span[@class='fa fa-pencil-square-o']")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.name("form_type_meal[name]")));
		
		List<WebElement> selectInputs = driver.findElements(By.className("multiselect__single"));
		
		WebElement typeOfMeal = selectInputs.get(0);
		typeOfMeal.click();
		typeOfMeal.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		
		List<WebElement> inputsEdit = driver.findElements(By.className("multiselect__input"));
		WebElement typeEdit = inputsEdit.get(6);
		typeEdit.click();
		typeEdit.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		
		WebElement edit = driver.findElement(By.id("button-save-and-back"));
		jse.executeScript("scroll(0, 250)");
		edit.click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='grid_meal__name__query__from']")));
		WebElement findNameEdit = driver.findElement(By.xpath("//input[@id='grid_meal__name__query__from']"));
		findNameEdit.clear();
		findNameEdit.sendKeys("Jagniêcina pieczona", Keys.ENTER);
	}
	
	@After
	public void afterTest(){
		driver.close();
	}
	
	public void clickInput(
			WebElement input,
			String path){
		input.click();
		input.findElement(By.xpath(path)).click();
	}
}
