package stepdefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.junit.Assert.*;

public class TestingSteps {
	static WebDriver driver;
	WebDriverWait wait = null;
	
	@Before
	public void setUp(){
		System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver");
		driver = new ChromeDriver();
    }
	
	@Given("^Usuario esta na Home Page$")
	public void user_is_on_Home_Page() {
		String baseURL = "http://www.americanas.com.br/";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
		driver.get(baseURL);
	}

	@When("^Usuario busca um produto$")
	public void usuario_busca_um_produto() throws InterruptedException {
		try {
			wait = new WebDriverWait(driver, 3);
			
			WebElement buscaInput = wait.until(
					ExpectedConditions.elementToBeClickable(By.id("h_search-input")));
			buscaInput.click();
			buscaInput.clear();
			buscaInput.sendKeys("Monitor lg ajustável");
			WebElement buscaButton = driver.findElement(By.id("h_search-btn"));
			buscaButton.click();
		
		} catch(Exception e) {
			System.out.println("Input não encontrado!");
		}
	}
	
	@And("^Usuario escolhe um produto$")
	public void usuario_escolhe_um_produto() throws InterruptedException {
		try {
			wait = new WebDriverWait(driver, 3);
			
			WebElement produtoButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(@href,'72025918')]")));
			produtoButton.click();			
		} catch(Exception e) {
			System.out.println("Botão não encontrado!");
		}
	}

	@Then("^Produto selecionado exibido$")
	public void produto_selecionado_exibido() {
		try {
			wait = new WebDriverWait(driver, 3);
			
			WebElement produtoExibido = wait.until(
					ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[@id='product-name-default' and contains(text(),'Monitor LG Ajustável')]"))));

			System.out.println("Produto exibido" + produtoExibido);			
		} catch(Exception e) {
			System.out.println("Produto não encontrado!");
		}
		
	}

	@When("^Usuario clica em comprar$")
	public void usuario_clica_em_comprar() throws InterruptedException {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			wait = new WebDriverWait(driver, 3);
			js.executeScript("window.scrollBy(0,100)");
			
			WebElement comprarButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.id("btn-buy")));
			comprarButton.click();
		} catch(Exception e) {
			System.out.println("Botão não encontrado!");
		}
	}
	
	@When("^Usuario clica em sim continuar$")
	public void usuario_clica_em_sim_continuar() throws InterruptedException {
		try {
			wait = new WebDriverWait(driver, 3);

			WebElement simContinuarButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//a[@value='Sim, continuar']")));
			simContinuarButton.click();

		} catch(Exception e) {
			System.out.println("Botão não encontrado!");
		}
	}
	
	@When("^Usuario clica em continuar$")
	public void usuario_clica_em_continuar() throws InterruptedException {
		try {
			wait = new WebDriverWait(driver, 3);

			WebElement continuarButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.id("btn-continue")));
			continuarButton.click();
		} catch(Exception e) {
			System.out.println("Botão não encontrado!");
		}
	}
	
	@When("^Usuario clica em \"([^\"]*)\" 12 meses$")
	public void usuario_clica_em_garantia_12_meses(boolean garantia) throws InterruptedException {
		try {
			wait = new WebDriverWait(driver, 3);

			WebElement continuarButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='+ 12 meses']")));
			if (garantia) {
				continuarButton.click();				
			}
		} catch(Exception e) {
			System.out.println("Botão não encontrado!");
		}
	}

	@Then("^Verifica \"([^\"]*)\" extendida$")
	public void garantia_extendida_marcada(boolean garantia) {
		try {
			wait = new WebDriverWait(driver, 10);
		
			WebElement resumoProdutoExibido = wait.until(
					ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@title='Monitor LG Ajustável 23,8” Full HD IPS LED 1920x1080 VGA HDMI DisplayPort 24BL550J']"))));
			if (garantia) {
				assertTrue(driver.findElement(By.xpath("//input[@type='checkbox']")).isSelected());
				System.out.println("Produto exibido" + resumoProdutoExibido);
			}
		} catch(Exception e) {
			System.out.println("Produto não encontrado!");
		}
	}
	
	@Then("^Resumo produto exibido$")
	public void resumo_produto_exibido() {
		try {
			wait = new WebDriverWait(driver, 3);
		
			WebElement resumoProdutoExibido = wait.until(
					ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@title='Monitor LG Ajustável 23,8” Full HD IPS LED 1920x1080 VGA HDMI DisplayPort 24BL550J']"))));

			System.out.println("Produto exibido" + resumoProdutoExibido);
		} catch(Exception e) {
			System.out.println("Produto não encontrado!");
		}
	}
	
	@When("^Usuario clica em continuar apos resumo$")
	public void usuario_clica_em_continuar_apos_resumo() throws InterruptedException {
		
		try {
			wait = new WebDriverWait(driver, 3);
			WebElement continuarButton = wait.until(
					ExpectedConditions.elementToBeClickable(By.id("buy-button")));
			continuarButton.click();
		} catch(Exception e) {
			System.out.println("Botão não encontrado!");
		}
	}	
	
	@Then("^Tela de login do cliente exibida$")
	public void tela_de_login_do_cliente_exibida() {
		try {
			wait = new WebDriverWait(driver, 3);
		
			WebElement inputEmail = wait.until(
					ExpectedConditions.visibilityOf(driver.findElement(By.id("email-input"))));
			WebElement inputPassword = wait.until(
					ExpectedConditions.visibilityOf(driver.findElement(By.id("password-input"))));
			WebElement loginButton = wait.until(
					ExpectedConditions.visibilityOf(driver.findElement(By.id("login-button"))));

			System.out.println("Tela de login exibida" + inputEmail + "" + inputPassword + "" + loginButton);
		} catch(Exception e) {
			System.out.println("Elementos não encontrados!");
		}			
	}
	
	@After
	public void cleanUp(){
    	System.out.println("Releasing resources now.....");
    	if (null != driver) {
    		driver.close();
    		driver.quit();
    	}
    }
	
}
