package br.com.alura.leilao.acceptance.steps;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.github.javafaker.Faker;

import br.com.alura.leilao.e2e.pages.Browser;
import br.com.alura.leilao.e2e.pages.LeiloesPage;
import br.com.alura.leilao.e2e.pages.LoginPage;
import br.com.alura.leilao.e2e.pages.NovoLeilaoPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LeilaoSteps {

	private Browser browser;
	private LoginPage loginPage;
	private LeiloesPage leiloesPage;
	private NovoLeilaoPage novoLeilaoPage;
	private Faker fake = new Faker(new Locale("pt-BR"));
	private String _inicio;
	private String _nome;
	private String _valor;

	@Given("um usuario logado")
	public void um_usuario_logado() {
		browser = new Browser();
		loginPage = browser.getLoginPage();
		leiloesPage = loginPage.realizaLoginComoFulano();
	}

	@When("acessa a pagina de novo leilao")
	public void acessa_a_pagina_de_novo_leilao() {
		novoLeilaoPage = this.leiloesPage.visitaPaginaParaCriarUmNovoLeilao();
	}

	@When("prenche o formulario com dados validos")
	public void prenche_o_formulario_com_dados_validos() {
		_inicio = LocalDate.now().plusDays(2).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		_nome = fake.commerce().productName();
		_valor = fake.commerce().price(100, 1000).replace(",", ".");
		this.leiloesPage = this.novoLeilaoPage.preencheForm(_nome, _valor, _inicio);
	}

	@Then("volta para a pagina de leiloes")
	public void volta_para_a_pagina_de_leiloes() {
		assertTrue(this.leiloesPage.estaNaPaginaDeLeiloes());
	}

	@Then("o novo leilao aparece na tabela")
	public void o_novo_leilao_aparece_na_tabela() {
		assertTrue(this.leiloesPage.existe(_nome, _valor, _inicio, "fulano"));
		this.browser.close();
	}

}
