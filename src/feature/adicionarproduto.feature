Feature: Fluxos possiveis de compra
 
Scenario: Adicionar um produto ao carrinho
	Given Usuario esta na Home Page
	When Usuario busca um produto
	And Usuario escolhe um produto
	Then Produto selecionado exibido
	When Usuario clica em comprar
	And Usuario clica em sim continuar
	And Usuario clica em continuar
	Then Resumo produto exibido
	When Usuario clica em continuar apos resumo
	Then Tela de login do cliente exibida

Scenario: Adicionar um produto ao carrinho com garantia extendida
	Given Usuario esta na Home Page
	When Usuario busca um produto
	And Usuario escolhe um produto
	Then Produto selecionado exibido
	When Usuario clica em comprar
	And Usuario clica em sim continuar
	And Usuario clica em garantia 12 meses
	And Usuario clica em continuar
	Then Garantia extendida marcada
	Then Resumo produto exibido
	When Usuario clica em continuar apos resumo
	Then Tela de login do cliente exibida