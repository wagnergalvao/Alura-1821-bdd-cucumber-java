
Feature: Propondo lances
  Como usuário
  Eu quero propor lances
  Para leilões cadastrados
  
  Scenario: Propondo um unico lance valido
    Given Informo um lance valido
    When Propor o lance
    Then Adiciona o lance com sucesso
  
  Scenario: Propondo varios lances validos
    Given Informo um lance valido de 10.00 reais do usuario "fulano"
    And Informo um lance valido de 20.00 reais do usuario "beltrano"
    And Informo um lance valido de 39.99 reais do usuario "ciclano"
    When Propor os lances
    Then Adiciona os lances com sucesso
  
  Scenario Outline: Propondo um unico lance invalido
    Given Informo um lance invalido de <valorLance> reais do usuario '<nomeUsuario>'
    When Propor o lance
    Then Nao adiciona o lance

    Examples:
    | valorLance | nomeUsuario |
    |  0         | fulano      |
    | -0.99      | clicano     |

  Scenario: Propondo lances do usuario no leilao
    Given dois lances
	    | valorLance | nomeUsuario |
  	  |  99.00     | beltrano    |
    	|  99.01     | beltrano    |
    When Propor os lances
    Then Nao adiciona o segundo lance
  