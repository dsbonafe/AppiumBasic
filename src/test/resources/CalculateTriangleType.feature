Feature: Calculate Triangle type 

Scenario Outline: Define a valid triangle 
	Given we are at home view 
	When we define the edge one <EDGE1> 
	And we define the edge two <EDGE2> 
	And we define the edge three <EDGE3> 
	And we click on the button Calcular 
	Then we should to see the message "O triângulo é " with the type <TYPE> 
	
	Examples: 
		| EDGE1 | EDGE2 | EDGE3 | TYPE |
		| 1 | 1 | 1 | "Equilátero" |
		| 2 | 1 | 1 | "Isósceles" |
		| 10 | 8 | 14 | "Escaleno" |