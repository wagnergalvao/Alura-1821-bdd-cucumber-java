package br.com.alura.leilao.acceptance.steps;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
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

	@Given("Informo um lance invalido de {double} reais do usuario {string}")
	public void informo_um_lance_invalido_de_reais_do_usuario(Double valorLance, String nomeUsuario) {
		lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valorLance));
	}

	@Given("dois lances")
	public void dois_lances(DataTable dataTable) {
		List<Map<String, String>> valores = dataTable.asMaps();
		for (Map<String, String> mapa : valores) {
			lance = new Lance(new Usuario(mapa.get("nomeUsuario")), new BigDecimal(mapa.get("valorLance")));
			this.lancesPropostos.add(lance);
		}
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

	@Then("Nao adiciona o lance")
	public void nao_adiciona_o_lance() {
		assertEquals(0, leilao.getLances().size());
	}

	@Then("Nao adiciona o segundo lance")
	public void nao_adiciona_o_segundo_lance() {
		assertEquals(2, lancesPropostos.size());
		assertEquals(1, leilao.getLances().size());
		assertEquals(lancesPropostos.get(0).getUsuario(), leilao.getLances().get(0).getUsuario());
		assertEquals(lancesPropostos.get(0).getValor(), leilao.getLances().get(0).getValor());
	}

}
