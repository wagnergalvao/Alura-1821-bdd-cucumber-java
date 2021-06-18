package br.com.alura.leilao.acceptance.steps;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PropondoLancesSteps {

	private Usuario usuario;
	private Lance lance;
	private Leilao leilao;

	@Given("Informo um lance valido")
	public void informo_um_lance_valido() {
		usuario = new Usuario("fulano");
		lance = new Lance(usuario, BigDecimal.TEN);
	}

	@Given("Informo um lance valido de {double} reais do usuario {string}")
	public void informo_um_lance_valido_de_reais_do_usuario(Double valorLance, String nomeUsuario) {
	}

	@When("Propor o lance")
	public void propor_o_lance() {
		leilao = new Leilao("Computador Z3");
		leilao.propoe(lance);
	}

	@When("Propor os lances")
	public void propor_os_lances() {
	}

	@Then("Adiciona o lance com sucesso")
	public void adiciona_o_lance_com_sucesso() {
		assertEquals(1, leilao.getLances().size());
		assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
	}

	@Then("Adiciona os lances com sucesso")
	public void adiciona_os_lances_com_sucesso() {
	}

}
