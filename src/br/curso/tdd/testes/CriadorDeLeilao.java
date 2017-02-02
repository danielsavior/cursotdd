package br.curso.tdd.testes;

import br.curso.tdd.resources.Lance;
import br.curso.tdd.resources.Leilao;
import br.curso.tdd.resources.Usuario;

public class CriadorDeLeilao {

    private Leilao leilao;

    public CriadorDeLeilao() { }

    public CriadorDeLeilao para(String descricao) {
        this.leilao = new Leilao(descricao);
        return this;
    }

    public CriadorDeLeilao lance(Usuario usuario, double valor) {
        leilao.propoe(new Lance(usuario, valor));
        return this;
    }

    public Leilao constroi() { 
        return leilao;
    }
}