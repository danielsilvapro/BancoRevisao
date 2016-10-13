package br.com.danielsilva.bancorevisao;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import javax.tools.JavaCompiler.CompilationTask;

import br.com.danielsilva.bancorevisao.dao.ContaDAO;
import br.com.danielsilva.bancorevisao.dao.ContaDAOLista;
import br.com.danielsilva.bancorevisao.exececao.ExcecaoContaAcimaLimite;
import br.com.danielsilva.bancorevisao.exececao.ExcecaoElementoInexistente;
import br.com.danielsilva.bancorevisao.exececao.ExcecaoElementoJaExistente;
import br.com.danielsilva.bancorevisao.model.Cliente;
import br.com.danielsilva.bancorevisao.model.Conta;

public class Programa {

	private static final int CADASTRAR = 1;
	private static final int APAGAR    = 2;
	private static final int VERTODOS  = 3;
	private static final int BUSCAR    = 4;
	private static final int SAIR      = 0;

	private static Scanner entrada;

	private static final ContaDAO dao = new ContaDAOLista ();
	private static final int List = 0;

	public static void menu (){
		entrada = new Scanner (System.in);

		int op = 0;

		do {

			System.out.println("*****************************************");
			System.out.println("*            Banco Calzabe              *");
			System.out.println("*****************************************");

			System.out.println("1 - Cadastrar");
			System.out.println("2 - Apagar");
			System.out.println("3 - Mostrar todas as contas");
			System.out.println("4 - Pesquisar por numero da conta");
			System.out.println("0 - Sair");

			System.out.println("Digite a opcao desejada");

			try {
				op = Integer.parseInt(entrada.nextLine());

				switch(op) {
				case CADASTRAR:
					cadastrar();
					break;
				case APAGAR:
					apagar();
					break;
				case VERTODOS:
					verTodos ();
					break;
				case BUSCAR:
					System.out.println("Informe a conta do cliente: ");
					buscar(Integer.parseInt(entrada.nextLine()));
					break;
				case SAIR:
					System.out.println("Sistema finalizado");
					break;
				}

			} catch(NumberFormatException | ExcecaoElementoInexistente nfe) {
				System.out.println("Informe um numero valido");
				op = -1;
			}
		} while(op != 0);
	}

	public static void main (String args []) {

		menu();

	}

	private static void cadastrar () {
		Cliente cliente = new Cliente ();

		System.out.println("Informe o nome do cliente: ");
		cliente.setNome(entrada.nextLine());

		System.out.println("Informe o cpf do cliente: ");
		cliente.setCpf(entrada.nextLine());
		ContaDAOLista contaDAO = new ContaDAOLista ();
		contaDAO.clientes.add(cliente);


		try {
			dao.salvar(new Conta(new Random().nextInt(), 100.00, cliente));
		} catch (ExcecaoElementoJaExistente | ExcecaoContaAcimaLimite e) {
			e.printStackTrace();
		}
		menu ();
	}

	private static void apagar () {
		Cliente cliente = new Cliente ();

		System.out.println("Informe o numero do cpf que deseja apagar");
		cliente.setCpf(String.valueOf(entrada.nextInt()));

		try {
			dao.deletar(cliente.getCpf());
		} catch (ExcecaoElementoInexistente e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		menu ();
	}

	private final static java.util.List<Conta> verTodos () {
		List<Conta> contas = dao.buscarTodos();
		for (int i = 0; i < contas.size(); i++) {
		System.out.println("Cliente: " + contas.get(i).getCliente().getNome().toString() + " " + "CFP: " + contas.get(i).getCliente().getCpf() + " " + "Saldo: " + contas.get(i).getSaldo());
		} 

		return dao.buscarTodos();	

	}

	private static Conta buscar (int numero) throws ExcecaoElementoInexistente {
		List<Conta> contas = dao.buscarTodos();
		for (int i = 0; i < contas.size(); i++ ){
			if (contas.get(i).getNumero() == numero){
		System.out.println("Cliente: " + contas.get(i).getCliente().getNome().toString() + " " + "CFP: " + contas.get(i).getCliente().getCpf() + " " + "Saldo: " + contas.get(i).getSaldo());
				return contas.get(i);
			}
		}
		throw new ExcecaoElementoInexistente("Conta não localizada ");

	}
}
