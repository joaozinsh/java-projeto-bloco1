package sistemaBancario;

import java.util.ArrayList;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) {

		int op;
		
		//Collection Array List para armazenar as contas bancarias
		ArrayList<ContaBancaria> contaBancaria = new ArrayList<>(); 
		
		//Objetos das classes
		ContaPoupanca cp;
		ContaCorrente cc;
		ContaBancaria cb;
		Scanner sc = new Scanner(System.in);
		
		// Menu de Funções
		System.out.println  ("############# BANCO 22 #############\n");
		do { 
			System.out.print("\n########## Menu de Opções ##########\n"
					+ "\n(1) - Criar Conta"
					+ "\n(2) - Sacar"
					+ "\n(3) - Depositar"
					+ "\n(4) - Transferir"
					+ "\n(5) - Atualizar Saldo Poupança"
					+ "\n(6) - Mostrar Informações da Conta"
					+ "\n(0) - Sair"
					+ "\n\nDigite sua opção: ");
			op = sc.nextInt();
			sc.nextLine();

			String nome, cpf, senha, senhaOrigem;
			int tipo, numero, numeroOrigem, numeroDestino, erro = 0;
			double renda = 0, valor;
			boolean valido = false;
			
			//Opção de Criar conta
			switch (op) {
			case 1:
				do {
					try {
						erro = 0;
						
						System.out.println("\n########## Criação de Conta ##########");
						System.out.println("\nDigite o nome do titular: ");
						nome = sc.nextLine();
						
						System.out.println("\nDigite o CPF do titular: ");
						cpf = sc.nextLine();
						
						System.out.println("\nDigite a renda do titular: ");
						renda = sc.nextDouble();
						
						System.out.print("\nTipo de conta\n"
								+ "\n1 - Conta Corrente"
								+ "\n2 - Conta Especial"
								+ "\n3 - Conta Poupança"
								+ "\n\nDigite aqui: ");
						tipo = sc.nextInt();
						sc.nextLine();
						
						if (tipo < 1 || tipo > 3) {
							System.err.println("\n\nTipo de conta inválida!\n");
							erro = 1;
							continue;
						}
						
						System.out.println("\nDigite a senha da nova conta: ");
						senha = sc.nextLine();

						//Determina qual o tipo de conta vai ser criada
						if (tipo == 1) {
							cc = new ContaCorrente(nome, cpf, renda, tipo, false, senha);
							contaBancaria.add(cc);
		
						} else if (tipo == 2) {
							cc = new ContaCorrente(nome, cpf, renda, tipo, true, senha);
							contaBancaria.add(cc);
							
						} else if (tipo == 3) {
							cp = new ContaPoupanca(nome, cpf, renda, tipo, senha);
							contaBancaria.add(cp);
						}
						
					} catch (Exception e) {
						erro = 1;
						sc.nextLine();
						System.err.print("\n\nVocê digitou dados inválidos"
								+ "\nTente novamente!"
								+ "\n\nPossiveis Erros:"
			                    + "\n- Letras em campos numéricos"
			                    + "\n\nSoluções:"
			                    + "\n- Digite somente números nos campos númericos\n");
					}
					
				} while (erro == 1);
						
				break;
			
			//Opção de Sacar
			case 2:
				do {
					try {
						erro = 0;
						
						System.out.println("\n########## Sacar ##########");
						System.out.println("\nDigite o valor do saque: ");
						valor = sc.nextDouble();
						
						System.out.println("\nDigite o numero da sua conta: ");
						numero = sc.nextInt();
						sc.nextLine();
						
						System.out.println("\nDigite sua senha: ");
						senha = sc.nextLine();
						
						//Pegar os dados da conta e por no objeto, para que ele possa executar metodos com os dados
						for(int i = 0; i < contaBancaria.size(); i++) {
							cb = contaBancaria.get(i);
							valido = cb.sacar(valor, numero, senha);
							
							if(valido) {
								break;
							}
						}
						if(valido == false) {
							System.err.println("\n\nDados inválidos ou inexistentes\n");
						}
						
					} catch (Exception e) {
						erro = 1;
						sc.nextLine();
						System.err.print("\n\nVocê digitou dados inválidos"
								+ "\nTente novamente!"
								+ "\n\nPossiveis Erros:"
			                    + "\n- Letras em campos numéricos"
			                    + "\n\nSoluções:"
			                    + "\n- Digite somente números nos campos númericos\n");
					} 
					
				} while (erro == 1);
				
				break;
				
			//Opção de Depositar
			case 3:
				do {
					try {		
						erro = 0;
						
						System.out.println("\n########## Deposito ##########");
						System.out.println("\nDigite o valor do deposito: ");
						valor = sc.nextDouble();
						
						System.out.println("\nDigite o numero da conta: ");
						numero = sc.nextInt();
						sc.nextLine();
						
						//Pegar os dados da conta e por no objeto, para que ele possa executar metodos com os dados
						valido = false;
						for(int i = 0; i < contaBancaria.size(); i++) {
							cb = contaBancaria.get(i);
							valido = cb.depositar(valor, numero);
							
							if(valido) {
								break;
							}
						}
						if(valido == false) {
							System.err.println("\n\nDados inválidos ou inexistentes\n");
						}
						
					} catch (Exception e) {
						erro = 1;
						sc.nextLine();
						System.err.print("\n\nVocê digitou dados inválidos"
								+ "\nTente novamente!"
								+ "\n\nPossiveis Erros:"
			                    + "\n- Letras em campos numéricos"
			                    + "\n\nSoluções:"
			                    + "\n- Digite somente números nos campos númericos\n");
					}
					
				} while (erro == 1);
						
				break;

			//Opção de transferência
			case 4:
				do {
					try {
						erro = 0;
						
						System.out.println("\n########## Transferência ##########");
						System.out.println("\nDigite o valor da transferência: ");
						valor = sc.nextDouble();
						
						System.out.println("\nDigite o numero da conta de origem: ");
						numeroOrigem = sc.nextInt();
						sc.nextLine();
							
						System.out.println("\nDigite o numero da conta de destino: ");
						numeroDestino = sc.nextInt();
						sc.nextLine();
						
						System.out.println("\nDigite a senha da conta de origem: ");
						senhaOrigem = sc.nextLine();
						
						//Pegar os dados da conta e por no objeto, para que ele possa executar metodos com os dados
						valido = false;
						for(int i = 0; i < contaBancaria.size(); i++) {
							cb = contaBancaria.get(i);
							valido = cb.transferencia(numeroOrigem, senhaOrigem, numeroDestino, valor);
							
							if(valido) {
								break;
							}
						}
						if(valido == false) {
							System.err.println("\n\nDados da conta de origem inválidos ou inexistentes\n");
						}
						
					} catch (Exception e) {
						erro = 1;
						sc.nextLine();
						System.err.print("\n\nVocê digitou dados inválidos"
								+ "\nTente novamente!"
								+ "\n\nPossiveis Erros:"
			                    + "\n- Letras em campos numéricos"
			                    + "\n\nSoluções:"
			                    + "\n- Digite somente números nos campos númericos\n");
					}
					
				} while (erro == 1);
				
				break;

			//Opção atualizar saldo da poupança
			case 5:
				do {
					try {
						erro = 0;
						
						System.out.println("\n########## Atualizar Saldo da Poupança ##########");
						System.out.println("\nDigite o número da conta: ");
						numero = sc.nextInt();
						sc.nextLine();
						
						System.out.println("\nDigite a senha da conta: ");
						senha = sc.nextLine();
						
						//Pegar os dados da conta e por no objeto, para que ele possa executar metodos com os dados
						valido = false;
						for(int i = 0; i < contaBancaria.size(); i++) {
							cb = contaBancaria.get(i);
							valido = cb.atualizarSaldo(numero, senha);
							
							if(valido) {
								break;
							}
						}
						if(valido == false) {
							System.err.println("\n\nDados inválidos ou inexistentes\n");
						}
						
					} catch (Exception e) {
						erro = 1;
						sc.nextLine();
						System.err.print("\n\nVocê digitou dados inválidos"
								+ "\nTente novamente!"
								+ "\n\nPossiveis Erros:"
			                    + "\n- Letras em campos numéricos"
			                    + "\n\nSoluções:"
			                    + "\n- Digite somente números nos campos númericos\n");
					}
					
				} while (erro == 1);
				
				break;

			//Opção de mostrar informações da conta
			case 6:	
				do {
					try {
						erro = 0;
						
						System.out.println("\n########## Informações da Conta ##########");
						System.out.println("\nDigite o número da conta: ");
						numero = sc.nextInt();
						sc.nextLine();
						
						System.out.println("\nDigite a senha da conta: ");
						senha = sc.nextLine();
						
						//Pegar os dados da conta e por no objeto, para que ele possa executar metodos com os dados
						valido = false;
						for(int i = 0; i < contaBancaria.size(); i++) {
							cb = contaBancaria.get(i);
							valido = cb.imprimirDados(numero, senha);
							
							if(valido) {
								break;
							}
						}
						if(valido == false) {
							System.err.println("\n\nDados inválidos ou inexistentes\n");
						}
						
					} catch (Exception e) {
						erro = 1;
						sc.nextLine();
						System.err.print("\n\nVocê digitou dados inválidos"
								+ "\nTente novamente!"
								+ "\n\nPossiveis Erros:"
			                    + "\n- Letras em campos numéricos"
			                    + "\n\nSoluções:"
			                    + "\n- Digite somente números nos campos númericos\n");
					}
					
				} while (erro == 1);
				
				break;

			//Opção de Sair
			case 0:
				System.out.println("\n\nEncerrando programa...");
				break;

			//Caso nenhuma das opção forem selecionadas
			default:
				System.err.println("\n\nOpção inválida, tente novamente!\n");
			}

		} while (op != 0);
		
		sc.close();
	}

}
