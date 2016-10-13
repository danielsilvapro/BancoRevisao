package br.com.danielsilva.bancorevisao.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.danielsilva.bancorevisao.exececao.ExcecaoContaAcimaLimite;
import br.com.danielsilva.bancorevisao.exececao.ExcecaoElementoInexistente;
import br.com.danielsilva.bancorevisao.exececao.ExcecaoElementoJaExistente;
import br.com.danielsilva.bancorevisao.model.Cliente;
import br.com.danielsilva.bancorevisao.model.Conta;

public class ContaDAOLista implements ContaDAO {

	public static int LIMITE_CONTAS = 50;
	public static List<Conta> contas = new ArrayList<>();
	public static List<Cliente> clientes = new ArrayList<>();
	@Override
	public void salvar(Conta conta) throws ExcecaoElementoJaExistente, ExcecaoContaAcimaLimite {
		// TODO Auto-generated method stub

		if (contas.contains(conta)) {
			throw new ExcecaoElementoJaExistente("Cliente já cadastrado.");
		} else {
			if (contas.size() < LIMITE_CONTAS) {
				contas.add(conta);
			} else {
				throw new ExcecaoContaAcimaLimite("Limite de contas excedido");
			}
			
		}
		
	}

	@Override
	public void deletar(String cpf) throws ExcecaoElementoInexistente {
		// TODO Auto-generated method stub
		
		Conta contaEncontrada = null;
		
		for(Conta conta : contas) {
			if(cpf.equals(conta.getCliente().getCpf())) {
				contaEncontrada = conta;
			}
		}
		
		if(!contas.remove(contaEncontrada)) {
			throw new ExcecaoElementoInexistente("Conta não cadastrado");
		}
		
	}

	@Override
	public List<Conta> buscarTodos() {
		// TODO Auto-generated method stub
		return contas;
	}

	@Override
	public Cliente buscarPor(String nomeConta) throws ExcecaoElementoInexistente {
		// TODO Auto-generated method stub
		Cliente clienteEncontrado = null;
		
		for(Cliente cliente : clientes) {
			if(nomeConta == cliente.getNome()) {
				clienteEncontrado = cliente;
			} 
		}
		if (clienteEncontrado == null) {
			throw new ExcecaoElementoInexistente("Conta não encontrada");
		}
		return clienteEncontrado;
		
		//return clienteEncontrado;
	}
	
}
