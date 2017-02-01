package br.curso.tdd.resources;

import java.util.ArrayList;
import java.util.List;

public class Leilao {
	
	private String nome;
	
	private List<Lance> lances;
	
	public Leilao(String nome) {
		this.nome = nome;
		this.lances =  new ArrayList<>();
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}	

	public List<Lance> getLances() {		
		return lances;
	}

	public void propoe(Lance lance) {
        if(lances.isEmpty() || podeDarLance(lance.getUsuario())) {
        	
        	if(jaDeuLance(lance.getUsuario())){
        		lances.add(dobraLance(lance));
        	}else{
        		lances.add(lance);
        	}
        	
        }
    }
	
	private Lance dobraLance(Lance lance){
		return new Lance(lance.getUsuario(), getUltimoLance(lance.getUsuario()).getValor()*2);		
	}
	
	private boolean jaDeuLance(Usuario usuario){
		return qtdDelancesDo(usuario)>0;
	}
	
	private boolean podeDarLance(Usuario usuario) {
        return !ultimoLanceDado().getUsuario().equals(usuario) 
            && qtdDelancesDo(usuario) < 5;
    }
	
	private Lance ultimoLanceDado() {
        return lances.get(lances.size()-1);
    }
	
	private int qtdDelancesDo(Usuario usuario) {
        int total = 0;
        for(Lance lance : lances) {
            if(lance.getUsuario().equals(usuario)) total++;
        }
        return total;
    }
	
	private Lance getUltimoLance(Usuario usuario) {
        Lance ultimoLance = null;
        for(Lance lance : lances) {
            if(lance.getUsuario().equals(usuario))
            ultimoLance = lance;
        }
        return ultimoLance;
    }

}
