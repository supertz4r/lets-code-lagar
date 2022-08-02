package teste;

import fazenda.Fazenda;

public class FazendaTeste {

    public static void main(String[] args) {

        Fazenda trindade = Fazenda.getInstance();

        System.out.println("TONELADAS ACUMULADAS NO LAGAR: " + trindade.getLagar().getToneladasRecebidas());

    }

}
