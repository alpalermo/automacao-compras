Feature: Fluxos possiveis de compra
 
Scenario Outline: Adicionar um produto ao carrinho
	Given Usuario esta na Home Page
	When Usuario busca um produto
	And Usuario escolhe um produto
	Then Produto selecionado exibido
	When Usuario clica em comprar
	And Usuario clica em sim continuar
	And Usuario clica em <garantia> 12 meses
	And Usuario clica em continuar
	Then Verifica <garantia> extendida
	Then Resumo produto exibido
	When Usuario clica em continuar apos resumo
	Then Tela de login do cliente exibida
	
	Examples:
	| garantia |
	| "false"  |
	| "true"   |
