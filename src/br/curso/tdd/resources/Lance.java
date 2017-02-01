package br.curso.tdd.resources;

public class Lance {
	
	private Usuario usuario;
	
	private double valor;
	
	public Lance(Usuario usuario, double valor) {
		this.usuario = usuario;
		this.valor = valor;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getValor() {		
		return valor;
	}

}
