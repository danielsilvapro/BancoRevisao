package br.com.danielsilva.bancorevisao.dao;

import java.util.List;

import br.com.danielsilva.bancorevisao.exececao.ExcecaoContaAcimaLimite;
import br.com.danielsilva.bancorevisao.exececao.ExcecaoElementoInexistente;
import br.com.danielsilva.bancorevisao.exececao.ExcecaoElementoJaExistente;
import br.com.danielsilva.bancorevisao.model.Cliente;
import br.com.danielsilva.bancorevisao.model.Conta;

public interface ContaDAO {
	
	public void salvar(Conta conta) throws ExcecaoElementoJaExistente, ExcecaoContaAcimaLimite;
	public void deletar(String cpf) throws ExcecaoElementoInexistente;
	public List<Conta> buscarTodos();
	public Cliente buscarPor(String nomeConta) throws ExcecaoElementoInexistente;

}
