package sistemaBancario;

import java.util.Calendar;

public class ContaPoupanca extends ContaBancaria {

	//Atributos
	protected int diaRendimento;

	//Metodo construtor para criar uma conta poupança
	public ContaPoupanca(String nome, String cpf, double renda, int tipo, String senha) {
		
		super(nome, cpf, renda, tipo, senha);
		this.diaRendimento = 1 + (int) (Math.random() * 28); //dia do rendimento é gerado aleatoriamente e pode ser entre o dia 1 e 28
		
		System.out.println("Dia do rendimento: "+diaRendimento);
		System.out.println("Saldo: "+formatarMoeda(saldo));
	}
	
	//Metodo polimorfico para atualizar o saldo da conta poupança
	public boolean atualizarSaldo(int numeroConta, String senha) {

		boolean valido = false;
		
		//Verifica se a conta existe e se os dados passados como parametro são iguais
		if(numeroConta == this.numeroConta && senha.equals(this.senha)) {
			valido = true;
			
			Calendar hoje = Calendar.getInstance();
			
			//Verificar se o dia de rendimento da conta é igual ao dia do sistema
			if(diaRendimento == hoje.get(Calendar.DAY_OF_MONTH)) {
				saldo += (saldo * 0.005); //Calculo de rendimento, 0,5%
				System.out.println("\n***** Rendimento Aplicado *****");
				System.out.println("\nNovo saldo: "+formatarMoeda(saldo));
				
			} else {
				System.err.println("\nHoje não é o dia de rendimento da sua conta, saldo inalterado\n");
			}
		}
		return valido;
	}
	
	//Metodo polimorfico para imprimir os dados da conta
	public boolean imprimirDados(int numeroConta, String senha) {
		
		boolean valido = false;
		
		switch(tipoConta) {
		case 1: tipoContaS = "Corrente"; break;
		case 2: tipoContaS = "Especial"; break;
		case 3: tipoContaS = "Poupança"; break;
		}
		
		//Verifica se a conta existe e se os dados passados como parametro são iguais
		if(numeroConta == this.numeroConta && senha.equals(this.senha)) {
			valido = true;
			
			System.out.println("\n\n***** Dados Bancários *****"
					+ "\nTitular: "+nomeTitular
					+ "\nCPF do titular: "+cpf
					+ "\nRenda: "+formatarMoeda(renda)
					+ "\nTipo da conta: "+tipoContaS
					+ "\nNúmero da conta: "+numeroConta
					+ "\nDia de rendimento: "+diaRendimento
					+ "\nSaldo: "+formatarMoeda(saldo));	
		} 
		
		return valido;
	}

}