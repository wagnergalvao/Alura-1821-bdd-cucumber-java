package br.com.alura.leilao.acceptance.steps;

import static org.junit.Assert.assertTrue;

import br.com.alura.leilao.e2e.pages.Browser;
import br.com.alura.leilao.e2e.pages.LeiloesPage;
import br.com.alura.leilao.e2e.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

	private Browser browser;
	private LoginPage loginPage;
	private LeiloesPage leiloesPage;
	private String _userName;
	private String _password;

	@Given("o usuario valido")
	public void o_usuario_valido() {
		browser = new Browser();
		loginPage = browser.getLoginPage();
		_userName = "fulano";
		_password = "pass";
	}

	@Given("o usuario invalido")
	public void o_usuario_invalido() {
		browser = new Browser();
		loginPage = browser.getLoginPage();
		_userName = "fulano";
		_password = "passs";

	}

	@When("realiza login")
	public void realiza_login() {
		leiloesPage = this.loginPage.realizaLoginComo(_userName, _password);
	}

	@When("tenta se logar")
	public void tenta_se_logar() {
		this.loginPage.realizaLoginComo(_userName, _password);
	}

	@Then("acessa a pagina de leiloes")
	public void acessa_a_pagina_de_leiloes() {
		leiloesPage.esperaCarregar();
		assertTrue(leiloesPage.estaNaPaginaDeLeiloes());
		browser.close();
	}

	@Then("continua na página de login")
	public void continua_na_página_de_login() {
		loginPage.esperaCarregar();
		assertTrue(loginPage.estaNaPaginaDeLoginComErro());
		browser.close();
	}

}
