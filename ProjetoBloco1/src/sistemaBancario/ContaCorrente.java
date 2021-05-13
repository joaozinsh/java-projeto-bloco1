package sistemaBancario;

public class ContaCorrente extends ContaBancaria {

	//Atributos
	protected boolean especial;
	protected double limite;
	
	//Metodo Construtor para a criação de uma conta corrente
	public ContaCorrente(String nome, String cpf, double renda, int tipo, boolean especial, String senha) {
		
		super(nome, cpf, renda, tipo, senha);
		this.especial = especial;
		this.limite = renda * 0.10;
		
		if(especial) {
			System.out.println("Limite especial: "+formatarMoeda(limite));
		}
		
		System.out.println("Saldo: "+formatarMoeda(saldo));
	}

	//Metodo polimorfico para sacar dinheiro
	public boolean sacar(double valor, int numeroConta, String senha) {
		
		double novoValor = saldo - valor;
		boolean valido = false;
		
		//Verifica se a conta existe e se os dados passados como parametro são iguais
		if(numeroConta == this.numeroConta && senha.equals(this.senha)) {
			valido = true;
			
			if(novoValor >= 0) {
				System.out.println("\n\n***** Saque Realizado com Sucesso! *****\n");
				System.out.println("\nVocê sacou "+formatarMoeda(valor));
				saldo = novoValor;
				System.out.println("\nNovo saldo: "+formatarMoeda(saldo));
				
			} else if(especial && novoValor >= (limite * -1)) {
				System.out.println("\n\n***** Saque Realizado com Sucesso! *****\n");
				System.out.println("\nVocê sacou "+formatarMoeda(valor));
				saldo = novoValor;
				System.out.println("\nNovo saldo: "+formatarMoeda(saldo));

			} else {
				System.err.println("\n\n***** Erro no Saque *****\n");
				System.out.println("\nVocê não tem "+formatarMoeda(valor)+" em conta e/ou não tem limite o suficiente");
			}
			
		} 
		return valido;
	}
	
	//Metodo polimorfico para imprimir os dados de uma conta
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
					+ "\nNúmero da conta: "+numeroConta);
			
			if(especial) {
				System.out.println("Limite especial: "+formatarMoeda(limite));
			}
			
			System.out.println("Saldo: "+formatarMoeda(saldo));
		}
		return valido;
	}

}