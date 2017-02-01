package br.curso.tdd.resources;

public class Palindromo {
	
	public static void main(String[] args) {
		System.out.println(ehPalindromo("Anotaram a data da maratona"));
	}

    public static boolean ehPalindromo(String frase) {

        String fraseFiltrada = frase
                .toUpperCase().replace(" ", "").replace("-", "");

        for(int i = 1; i <= fraseFiltrada.length(); i++) {
            if(fraseFiltrada.charAt(i-1) != 
                    fraseFiltrada.charAt(fraseFiltrada.length() -i)) {
                return false;
            }
        }

        return true;
    }
}