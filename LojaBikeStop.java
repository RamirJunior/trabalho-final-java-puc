/* 
 * Objetivo do programa: gerenciar controle de estoque e vendas diárias
 * de uma loja de bicicletas.
 * 
 * Programador: Ramir Junior
 * 
 * Escrito em 02/06/2021
 * 
 * Última atualização: 14/06/2021
 * 
 * */

package puc.trabalhoatp;

import java.util.Scanner;

public class LojaBikeStop {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int escolha = 0;
	
		String tipos[] = vetorTipos();
		String atributos[] = vetorAtributos();

		double tabelaPrincipal[][] = criaTabelaInicial(tipos, atributos);

		do {
			System.out.println("\n##### LOJA BIKE STOP #####");
			System.out.print("\n1. Registrar venda\n2. Imprimir relatório\n3. Sair\n\nSua opção: ");
			escolha = scan.nextInt();
			if (escolha == 1) {
				// ATRIBUINDO RETORNO À UMA TABELA QUE SERÁ ATUALIZADA
				tabelaPrincipal = registraVenda(tipos, tabelaPrincipal);
			}
			if (escolha == 2) {
				imprimeRelatorio(tipos, atributos, tabelaPrincipal);
			}
		} while (escolha != 3);
		System.out.println("Você saiu da aplicação.");
	}

	
	// CRIANDO VETOR USADO PARA AS LINHAS DA TABELA PRINCIPAL
	public static String[] vetorTipos() {
		String vetTipo[] = { "Dobrável", "Elétrica", "Híbrida", "Speed", "Urbana" };
		return vetTipo;
	}

	
	
	// CRIANDO VETOR USADO PARA AS COLUNAS DA TABELA PRINCIPAL
	public static String[] vetorAtributos() {
		String vetAtributo[] = { "Valor Fábrica", "Valor de Venda", "QTDE Vendidas", "Em estoque", "Venda total" };
		return vetAtributo;
	}

	
	
	// CRIANDO MATRIZ RECEBENDO VETORES
	public static double[][] criaTabelaInicial(String tipo[], String atributo[]) {
		Scanner scan = new Scanner(System.in);
		
		// PERCENTUAL PARA CALCULO DO VALOR DE VENDA
		double percentualLoja = 0.8;
		
		// CRIANDO MATRIZ COM TAMANHO DOS VETORES
		double tabela[][] = new double[tipo.length][atributo.length];

		System.out.println("##### Preencha a Tabela Inicial #####");
		System.out.println("( Percentual do valor de venda: "+(percentualLoja*100)+"% )");
		for (int linha = 0; linha < tipo.length; linha++) {
			for (int coluna = 0; coluna < atributo.length; coluna++) {

				// ZERANDO COLUNAS QUANTIDADE E VALOR TOTAL AUTOMATICAMENTE
				if ((coluna == 2) || (coluna == 4)) {
					tabela[linha][coluna] = 0;
					continue;
				}
				
				// ATRIBUINDO O VALOR DE VENDA AUTOMATICAMENTE
				// COM BASE NO PERCENTUAL
				if (coluna == 1) {
					tabela[linha][coluna] = tabela[linha][coluna-1]+((tabela[linha][coluna-1]*percentualLoja));
					continue;
				}
								
				// PREENCHENDO MATRIZ DE ACORDO COM O CAMPO REFERENCIADO NO VETOR
				System.out.println("\nTipo da bicicleta: " + tipo[linha]);
				System.out.print(atributo[coluna] + ": ");
				tabela[linha][coluna] = scan.nextFloat();	
			}
		}
		return tabela;
	}
	
	
	
	// REGISTRA VENDAS NA TABELA CRIADA
	public static double[][] registraVenda(String vetTipo[], double tab[][]) {
		Scanner scan = new Scanner(System.in);
		String continua = "s";

		do {
			System.out.println("\n##### Registro de Venda #####");
			System.out.println("Selecione uma opção para registrar venda");
			System.out.print(
					"\n1. Bicicleta Dobrável\n2. Bicicleta Elétrica\n3. Bicicleta Híbrida\n4. Bicicleta Speed\n5. Bicicleta Urbana\n\nSua Opção: ");
			int opcao = scan.nextInt();
			int valor = 0;
			switch (opcao) {
			case 1:
				if (tab[0][3] != 0) {
					// IMPRIME VALOR DO VETOR VINCULADO
					System.out.println("Tipo: " + vetTipo[0]);

					// IMPRIME QUANTIDADE DE ESTOQUE ATUALIZADA
					System.out.println("Quantidade em estoque: " + tab[0][3]);
					System.out.print("Quantidade vendida: ");
					valor = scan.nextInt();

					// VALIDANDO VALOR POSITIVO MAIOR QUE ZERO
					if (valor > 0) {
						// VERIFICANDO O ESTOQUE
						if (valor <= tab[0][3]) {
							// ATRIBUINDO SOMATORIO DAS VENDAS NESSE TIPO
							tab[0][4] += (valor * tab[0][1]);
							// ADICIONANDO VALOR A COLUNA DE VENDIDOS
							tab[0][2] += valor;
							// SUBTRAINDO VALOR DA COLUNA QTDE ESTOQUE
							tab[0][3] -= valor;
							System.out.println(valor + " venda(s) registrada(s) com sucesso!");
						} else {
							System.out.println("\nQuantidade acima do disponível no estoque.\nDigite um valor menor.");
						}
					} else {
						// INVALIDA OPERAÇÃO
						System.out.println("Quantidade inválida. Operação não realizada.");
					}
				} else {
					System.out.println("Bicicleta "+vetTipo[0]+" sem estoque disponível.");
				}
				break;

			case 2:
				if (tab[1][3] != 0) {
					System.out.println("Tipo: " + vetTipo[1]);
					System.out.println("Quantidade em estoque: " + tab[1][3]);
					System.out.print("Quantidade vendida: ");
					valor = scan.nextInt();
					if (valor > 0) {
						if (valor <= tab[1][3]) {
							tab[1][4] += (valor * tab[1][1]);
							tab[1][2] += valor;
							tab[1][3] -= valor;
							System.out.println(valor + " venda(s) registrada(s) com sucesso!");
						} else {
							System.out.println("\nQuantidade acima do disponível no estoque.\nDigite um valor menor.");
						}
					} else {
						System.out.println("Quantidade inválida. Operação não realizada.");
					}
				} else {
					System.out.println("Bicicleta "+vetTipo[1]+" sem estoque disponível.");
				}
				break;

			case 3:
				if (tab[2][3] != 0) {
					System.out.println("Tipo: " + vetTipo[2]);
					System.out.println("Quantidade em estoque: " + tab[2][3]);
					System.out.print("Quantidade vendida: ");
					valor = scan.nextInt();
					if (valor > 0) {
						if (valor <= tab[2][3]) {
							tab[2][4] += (valor * tab[2][1]);
							tab[2][2] += valor;
							tab[2][3] -= valor;
							System.out.println(valor + " venda(s) registrada(s) com sucesso!");
						} else {
							System.out.println("\nQuantidade acima do disponível no estoque.\nDigite um valor menor.");
						}
					} else {
						System.out.println("Quantidade inválida. Operação não realizada.");
					}
				} else {
					System.out.println("Bicicleta "+vetTipo[2]+" sem estoque disponível.");
				}
				break;

			case 4:
				if (tab[3][3] != 0) {
					System.out.println("Tipo: " + vetTipo[3]);
					System.out.println("Quantidade em estoque: " + tab[3][3]);
					System.out.print("Quantidade vendida: ");
					valor = scan.nextInt();
					if (valor > 0) {
						if (valor <= tab[3][3]) {
							tab[3][4] += (valor * tab[3][1]);
							tab[3][2] += valor;
							tab[3][3] -= valor;
							System.out.println(valor + " venda(s) registrada(s) com sucesso!");
						} else {
							System.out.println("\nQuantidade acima do disponível no estoque.\nDigite um valor menor.");
						}
					} else {
						System.out.println("Quantidade inválida. Operação não realizada.");
					}
				} else {
					System.out.println("Bicicleta "+vetTipo[3]+" sem estoque disponível.");
				}
				break;

			case 5:
				if (tab[4][3] != 0) {
					System.out.println("Tipo: " + vetTipo[4]);
					System.out.println("Quantidade em estoque: " + tab[4][3]);
					System.out.print("Quantidade vendida: ");
					valor = scan.nextInt();
					if (valor > 0) {
						if (valor <= tab[4][3]) {
							tab[4][4] += (valor * tab[4][1]);
							tab[4][2] += valor;
							tab[4][3] -= valor;
							System.out.println(valor + " venda(s) registrada(s) com sucesso!");
						} else {
							System.out.println("\nQuantidade acima do disponível no estoque.\nDigite um valor menor.");
						}
					} else {
						System.out.println("Quantidade inválida. Operação não realizada.");
					}
				} else {
					System.out.println("Bicicleta "+vetTipo[4]+" sem estoque disponível.");
				}
				break;

			default:
				scan.nextLine();
				System.out.print("Opção inválida.\nDeseja tentar novamente? (s/n): ");
				continua = scan.nextLine();
			}

			scan.nextLine();
			System.out.print("\nDeseja continuar? (s/n): ");
			continua = scan.nextLine();

		} while (continua.equalsIgnoreCase("s"));

		// RETORNA MATRIZ ALTERADA
		return tab;
	}
	
	

	// IMPRIME RELATÓRIO COM DADOS ATUAIS
	public static void imprimeRelatorio(String tipo[], String atributo[], double tab[][]) {
		System.out.println("\n##### Relatório de bicicletas em estoque #####");
		for (int linha = 0; linha < tipo.length; linha++) {
			System.out.println("\n\nTipo: " + tipo[linha]);
			// OCULTANDO COLUNA DE VALOR UNITÁRIO
			for (int coluna = 1; coluna < atributo.length; coluna++) {
				System.out.print(atributo[coluna] + " = " + tab[linha][coluna] + " |");
			}
		}
		// IMPRIMINDO LUCRO TOTAL DA LOJA
		double somaTotal = 0;
		for (int i = 0; i < tipo.length; i++) {
			somaTotal = somaTotal + tab[i][4];
		}
		System.out.println("\n\n\t\t\tLucro total da loja = R$ " + somaTotal + "\n\n##### Fim do Relatório #####");
	}
}
