package sistemaBancario;

import java.text.NumberFormat;

public class ContaBancaria {
	
	//Atributos
	protected String nomeTitular;
	protected String cpf;
	protected String senha;
	protected String tipoContaS;
	protected int numeroConta;
	protected int tipoConta;
	protected double renda;
	protected double saldo;
	
	//Metodo Construtor, passando como parametros de criação de conta o nome, cpf, renda, tipo de conta, senha
	public ContaBancaria(String nome, String cpf, double renda, int tipo, String senha) {
		
		this.nomeTitular = nome;
		this.cpf = cpf;
		this.renda = renda;
		this.tipoConta = tipo;
		this.numeroConta = 10000000 + (int) (Math.random() * 90000000); //Número da conta é generado aleatoriamente
		this.senha = senha;
		this.saldo = 0;
		
		switch(tipoConta) {
		case 1: tipoContaS = "Corrente"; break;
		case 2: tipoContaS = "Especial"; break;
		case 3: tipoContaS = "Poupança"; break;
		}
		
		System.out.println("\n***** Sua conta foi criada! *****\n"
				+ "\nTitular: "+nomeTitular
				+ "\nCPF do titular: "+cpf
				+ "\nRenda: "+formatarMoeda(renda)
				+ "\nTipo da conta: "+tipoContaS
				+ "\nNúmero da conta: "+numeroConta);
	}
	
	//Metodo para sacar dinheiro de uma conta
	public boolean sacar(double valor, int numeroConta, String senha) {

		double novoValor = saldo - valor;
		boolean valido = false;

		//Verifica se a conta existe e se os dados passados como parametro são iguais
		if(numeroConta == this.numeroConta && senha.equals(this.senha)) {
			valido = true;
			
			if (novoValor >= 0) {
				System.out.println("\n\n***** Saque Realizado com Sucesso! *****");
				System.out.println("\nVocê sacou: "+formatarMoeda(valor));
				saldo = novoValor;
				System.out.println("\nNovo saldo: "+formatarMoeda(saldo));
				
			} else {
				System.err.println("\n\n***** Erro no Saque *****");
				System.out.println("\nVocê não tem "+formatarMoeda(valor)+" na conta");
			}
		} 
		return valido;
	}

	//Metodo para depositar dinheiro em uma conta
	public boolean depositar(double valor, int numeroConta) {

		boolean valido = false;
		
		//Verifica se a conta existe e se os dados passados como parametro são iguais
		if(numeroConta == this.numeroConta) {
			valido = true;
			saldo += valor;
			
			System.out.println("\n\n***** Deposito Realizado com Sucesso! *****");
			System.out.println("\nNovo saldo: "+formatarMoeda(saldo));
		}
		return valido;
	}
	
	//Metodo para transferir valores entre contas
	public boolean transferencia(int numeroContaOrigem, String senhaOrigem, int numeroContaDestino, double valor) {
		
		double novoValor = saldo - valor;
		boolean valido = false;
		
		//Verifica se a conta existe e se os dados passados como parametro são iguais
		if(numeroContaOrigem == this.numeroConta && senhaOrigem.equals(this.senha)) {
			if (novoValor >= 0) {
				valido = true;
				System.out.println("\n\n***** Transferência Realizada com Sucesso! *****");
				System.out.println("\nVocê transferiu: "+formatarMoeda(valor));
				saldo = novoValor;
				System.out.println("\nNovo saldo: "+formatarMoeda(saldo));
				
			} else {
				System.err.println("\n\n***** Erro na Transferência *****");
				System.out.println("\nVocê não tem "+formatarMoeda(valor)+" na conta");
			}
		} 
		
		//Verifica se a conta existe e se os dados passados como parametro são iguais
		if(valido && numeroContaDestino == this.numeroConta) {
			saldo += valor;
		}
		return valido;
	}
	
	//Metodo que mostra os dados de uma conta
	public boolean imprimirDados(int numeroConta, String senha) {
		
		boolean valido = false;
		
		//Verifica se a conta existe e se os dados passados como parametro são iguais
		if(numeroConta == this.numeroConta && senha.equals(this.senha)) {
			valido = true;
			
			System.out.println("\n\n***** Dados Bancários *****"
					+ "\nTitular: "+nomeTitular
					+ "\nCPF do titular: "+cpf
					+ "\nRenda: "+formatarMoeda(renda)
					+ "\nTipo da conta: "+tipoContaS
					+ "\nNúmero da conta: "+numeroConta
					+ "\nSaldo: "+formatarMoeda(saldo));
		} 
		return valido;
	}
	
	//Metodo para formatar um valor no formata da moeda do brasil
	public String formatarMoeda(double valor)
	{
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		nf.setMinimumFractionDigits(2);
		String formatoMoeda = nf.format(valor);
		return formatoMoeda;
	}

	//Metodo para atualiazr o saldo da conta poupança, porem esse metodo é efetivamente usado pela Classe ContaPoupanca
	public boolean atualizarSaldo(int numero, String senha) {
		return true;
	}
	
}