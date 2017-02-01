package br.curso.tdd.testes;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import br.curso.tdd.resources.MatematicaMaluca;

public class MatematicaMalucaTest {
	
	@Test
	public void testaMaior30(){
		MatematicaMaluca matematica = new MatematicaMaluca();
		
		int numeroMaior = 31;
		assertEquals(numeroMaior*4,matematica.contaMaluca(numeroMaior));
	}
	
	@Test
	public void testaMaior10(){
		MatematicaMaluca matematica = new MatematicaMaluca();
		
		int numeroMaior = 30;
		assertEquals(numeroMaior*3,matematica.contaMaluca(numeroMaior));
	}
	
	@Test
	public void testaMenorIgual10(){
		MatematicaMaluca matematica = new MatematicaMaluca();
		
		int numeroMenor = 10;
		assertEquals(numeroMenor*2,matematica.contaMaluca(numeroMenor));
	}
}
