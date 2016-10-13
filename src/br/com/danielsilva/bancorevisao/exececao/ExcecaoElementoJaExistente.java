package br.com.danielsilva.bancorevisao.exececao;

public class ExcecaoElementoJaExistente extends Exception{
	
	public ExcecaoElementoJaExistente (String mensagem) {
		super(mensagem);
	}

}
