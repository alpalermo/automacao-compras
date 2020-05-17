package stepdefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TestingSteps {
	static WebDriver driver;
	
	@Given("^Usuario esta na Home Page$")
	public void user_is_on_Home_Page() {
		System.setProperty("webdriver.chrome.driver", "webdrivers/chromedriver");
		driver = new ChromeDriver();
		String baseURL = "http://www.americanas.com.br/";
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);	
		driver.get(baseURL);
	}

	@When("^Usuario busca um produto$")
	public void usuario_busca_um_produto() throws InterruptedException {
		WebElement buscaInput = driver.findElement(By.id("h_search-input"));
		buscaInput.click();
		buscaInput.clear();
		buscaInput.sendKeys("Monitor lg ajustável");
		WebElement buscaButton = driver.findElement(By.id("h_search-btn"));
		buscaButton.click();		
	}
	
	@And("^Usuario escolhe um produto$")
	public void usuario_escolhe_um_produto() throws InterruptedException {
		WebElement produtoButton = driver.findElement(By.xpath("//a[contains(@href,'72025918')]"));
		produtoButton.click();
	}

	@Then("^Produto selecionado exibido$")
	public void produto_selecionado_exibido() {
		WebElement produtoExibido = driver.findElement(By.xpath("//h1[@id='product-name-default' and contains(text(),'Monitor LG Ajustável')]"));
		System.out.println("Produto exibido");
	}

	@When("^Usuario clica em comprar$")
	public void usuario_clica_em_comprar() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement comprarButton = driver.findElement(By.id("btn-buy"));
		js.executeScript("window.scrollBy(0,100)");
		comprarButton.click();
	}
	
	@When("^Usuario clica em continuar$")
	public void usuario_clica_em_continuar() throws InterruptedException {
		WebElement simContinuarButton = driver.findElement(By.xpath("//a[@value='Sim, continuar']"));
		simContinuarButton.click();
		WebElement continuarButton = driver.findElement(By.id("btn-continue"));
		continuarButton.click();
	}
	
	@Then("^Resumo produto exibido$")
	public void resumo_produto_exibido() {
		WebElement produtoExibido = driver.findElement(By.xpath("//a[@title='Monitor LG Ajustável 23,8” Full HD IPS LED 1920x1080 VGA HDMI DisplayPort 24BL550J']"));
		System.out.println("Produto exibido");
	}
	
	@When("^Usuario clica em continuar apos resumo$")
	public void usuario_clica_em_continuar_apos_resumo() throws InterruptedException {
		WebElement continuarButton = driver.findElement(By.id("buy-button"));
		continuarButton.click();
	}	
	
	@Then("^Tela de login do cliente exibida$")
	public void tela_de_login_do_cliente_exibida() {
		WebElement inputEmail = driver.findElement(By.id("email-input"));
		WebElement inputPassword = driver.findElement(By.id("password-input"));
		WebElement loginButton = driver.findElement(By.id("email-input"));
		System.out.println("Tela de login exibida");
	}
	
}
