package br.curso.tdd.testes;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import br.curso.tdd.resources.Lance;
import br.curso.tdd.resources.Leilao;

public class LeilaoMatcher extends TypeSafeMatcher<Leilao> {
	
	private Lance lance;

	public LeilaoMatcher(Lance lance) {
        this.lance = lance;
    }
	
	@Override
	public void describeTo(Description description) {
		description.appendText("leilao com lance " + lance.getValor());
	}

	@Override
	protected boolean matchesSafely(Leilao item) {
		return item.getLances().contains(lance);
	}
	
	public static Matcher<Leilao> temUmLance(Lance lance) {
        return new LeilaoMatcher(lance);
    }

}
