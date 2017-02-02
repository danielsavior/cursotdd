package br.curso.tdd.testes;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import java.util.List;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import br.curso.tdd.resources.Avaliador;
import br.curso.tdd.resources.Lance;
import br.curso.tdd.resources.Leilao;
import br.curso.tdd.resources.Usuario;

public class AvaliadorTest {
	
	private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;
    private Usuario juca; 
	
    @Before
    public void criaAvaliador() {
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("João");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
        this.juca  = new Usuario("Juca");
        System.out.println("lalalalala");
    }
    
    @AfterClass
    public static void finalizar(){
    	System.out.println("fim");
    }

	
	@Test
	public void deveEntenderLancesEmOrdemCrescente() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
	        .lance(maria,250.0)
	        .lance(joao,300.0)
	        .lance(jose,400.0)
	        .constroi();

        leiloeiro.avalia(leilao);

        
        
        assertThat(leiloeiro.getMenorLance(), equalTo(250.0));
        assertThat(leiloeiro.getMaiorLance(), equalTo(400.0));
       
    }
	
	@Test
	public void deveEntenderLancesEmOrdemDecrescente() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
	        .lance(jose,400.0)
	        .lance(joao,300.0)        
	        .lance(maria,200.0)
	        .lance(juca,100.0)
	        .constroi();

        leiloeiro.avalia(leilao);

        double maiorEsperado = 400;
        double menorEsperado = 100;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
       
    }
	
	@Test
	public void deveEntenderLancesSemOrdemEspecifica() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
	        .lance(jose,400.0)
	        .lance(joao,350.0)
	        .lance(joao,300.0)
	        .lance(joao,330.0)
	        .lance(maria,250.0)
	        .constroi();

        leiloeiro.avalia(leilao);

        double maiorEsperado = 400;
        double menorEsperado = 250;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
       
    }
	
	@Test
	public void testaValorMedio() {

        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
	        .lance(maria,250.0)
	        .lance(joao,300.0)
	        .lance(jose,400.0)
			.constroi();

        double valorMedio = 0;
    	for(Lance lance : leilao.getLances()){
    		valorMedio += lance.getValor(); 
    	}
    	
    	valorMedio =  valorMedio/leilao.getLances().size();
        
        assertEquals(valorMedio, leiloeiro.calculaValorMedio(leilao), 0.0001);
    }
	
	@Test
    public void deveEntenderLeilaoComApenasUmLance() {
        
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
        		.lance(joao,1000.0).constroi();

        leiloeiro.avalia(leilao);

        assertEquals(1000, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.0001);
    }
	
	@Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomica() {
        
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
        		.lance(joao,200.0)
        		.lance(maria,450.0)
        		.lance(joao,120.0)
        		.lance(maria,700.0)//900
        		.lance(joao,630.0)//800
        		.lance(maria,230.0)//1800
        		.constroi();

        leiloeiro.avalia(leilao);

        assertEquals(1800.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(200.0, leiloeiro.getMenorLance(), 0.0001);
    }
	
	@Test
    public void deveEncontrarOsTresMaioresLances() {
        
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo")
		        .lance(joao, 100.0)
		        .lance(maria, 200.0)
		        .lance(joao, 300.0)//200
		        .lance(maria, 400.0)
		        .constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        
        assertEquals(400, maiores.get(0).getValor(), 0.00001);
        assertEquals(200, maiores.get(1).getValor(), 0.00001);
        assertEquals(200, maiores.get(2).getValor(), 0.00001);
        
        assertThat(maiores, hasItems(
                new Lance(maria, 400), 
                new Lance(joao, 200),
                new Lance(maria, 200)
        ));
        
        
    }
	
	@Test
	@Ignore
    public void deveDevolverListaVaziaCasoNaoHajaLances() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();

        leiloeiro.avalia(leilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(0, maiores.size());
    }
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
	    Leilao leilao = new CriadorDeLeilao()
	        .para("Playstation 3 Novo")
	        .constroi();

	    leiloeiro.avalia(leilao);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAvaliarLanceIgualQueZero() {
	    Leilao leilao = new CriadorDeLeilao()
	        .para("Playstation 3 Novo")
	        .lance(maria, 0)
	        .constroi();

	    leiloeiro.avalia(leilao);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void naoDeveAvaliarLanceMenorQueZero() {
	    Leilao leilao = new CriadorDeLeilao()
	        .para("Playstation 3 Novo")
	        .lance(maria, -1)
	        .constroi();

	    leiloeiro.avalia(leilao);
	}
		
}
