package br.curso.tdd.testes;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import br.curso.tdd.resources.Avaliador;
import br.curso.tdd.resources.Lance;
import br.curso.tdd.resources.Leilao;
import br.curso.tdd.resources.Usuario;

public class AvaliadorTest {
	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria,250.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(jose,400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 400;
        double menorEsperado = 250;
        
        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
       
    }
	
	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");
        Usuario juca = new Usuario("Juca");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(jose,400.0));
        leilao.propoe(new Lance(joao,300.0));        
        leilao.propoe(new Lance(maria,200.0));
        leilao.propoe(new Lance(juca,100.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 400;
        double menorEsperado = 100;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
       
    }
	
	@Test
	public void deveEntenderLancesSemOrdemEspecifica() {
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(jose,400.0));
        leilao.propoe(new Lance(joao,350.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(joao,330.0));
        leilao.propoe(new Lance(maria,250.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        double maiorEsperado = 400;
        double menorEsperado = 250;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
       
    }
	
	@Test
	public void testaValorMedio() {
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(maria,250.0));
        leilao.propoe(new Lance(joao,300.0));
        leilao.propoe(new Lance(jose,400.0));

        Avaliador leiloeiro = new Avaliador();
        
        double valorMedio = 0;
    	for(Lance lance : leilao.getLances()){
    		valorMedio += lance.getValor(); 
    	}
    	
    	valorMedio =  valorMedio/leilao.getLances().size();
        
        assertEquals(valorMedio, leiloeiro.calculaValorMedio(leilao), 0.0001);
    }
	
	@Test
    public void deveEntenderLeilaoComApenasUmLance() {
        Usuario joao = new Usuario("Joao"); 
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,1000.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(1000, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.0001);
    }
	
	@Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
        Usuario joao = new Usuario("Joao"); 
        Usuario maria = new Usuario("Maria"); 
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,450.0));
        leilao.propoe(new Lance(joao,120.0));
        leilao.propoe(new Lance(maria,700.0));
        leilao.propoe(new Lance(joao,630.0));
        leilao.propoe(new Lance(maria,230.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
    }
	
	@Test
    public void deveEncontrarOsTresMaioresLances() {
        Usuario joao = new Usuario("João");
        Usuario maria = new Usuario("Maria");
        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao, 100.0));
        leilao.propoe(new Lance(maria, 200.0));
        leilao.propoe(new Lance(joao, 300.0));
        leilao.propoe(new Lance(maria, 400.0));

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        
        assertEquals(400, maiores.get(0).getValor(), 0.00001);
        assertEquals(300, maiores.get(1).getValor(), 0.00001);
        assertEquals(200, maiores.get(2).getValor(), 0.00001);
    }
	
	@Test
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new Leilao("Playstation 3 Novo");

        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }
}
