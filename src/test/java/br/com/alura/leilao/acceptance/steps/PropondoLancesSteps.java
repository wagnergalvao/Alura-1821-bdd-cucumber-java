package br.com.alura.leilao.acceptance.steps;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PropondoLancesSteps {

	private Usuario usuario;
	private Lance lance;
	private Leilao leilao;
	private ArrayList<Lance> lancesPropostos;

	@Before
	public void setup() {
		this.lancesPropostos = new ArrayList<Lance>();
		leilao = new Leilao("Computador Z3");
	}

	@Given("Informo um lance valido")
	public void informo_um_lance_valido() {
		usuario = new Usuario("fulano");
		lance = new Lance(usuario, BigDecimal.TEN);
	}

	@Given("Informo um lance valido de {double} reais do usuario {string}")
	public void informo_um_lance_valido_de_reais_do_usuario(Double valorLance, String nomeUsuario) {
		lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valorLance));
		this.lancesPropostos.add(lance);
	}

	@When("Propor o lance")
	public void propor_o_lance() {
		leilao.propoe(lance);
	}

	@When("Propor os lances")
	public void propor_os_lances() {
		this.lancesPropostos.forEach(lance -> leilao.propoe(lance));
	}

	@Then("Adiciona o lance com sucesso")
	public void adiciona_o_lance_com_sucesso() {
		assertEquals(1, leilao.getLances().size());
		assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
	}

	@Then("Adiciona os lances com sucesso")
	public void adiciona_os_lances_com_sucesso() {
		assertEquals(lancesPropostos.size(), leilao.getLances().size());
		for (int index = 0; index < lancesPropostos.size(); index++) {
			assertEquals(lancesPropostos.get(index).getUsuario(), leilao.getLances().get(index).getUsuario());
			assertEquals(lancesPropostos.get(index).getValor(), leilao.getLances().get(index).getValor());
		}
	}

}
