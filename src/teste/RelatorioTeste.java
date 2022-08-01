package teste;

import java.io.IOException;

import output.Relatorio;

public class RelatorioTeste {

    public static void main(String[] args) throws IOException {

        Relatorio relatorio = new Relatorio();

        relatorio.adicionaLinha("09:24:56", "0004", "4", "Galega", "1", "2", "8");
        relatorio.adicionaLinha("09:24:57", "0012", "8", "Picual", "2", "5", "6");
        relatorio.adicionaLinha("09:24:58", "0018", "6", "Cordovil", "1", "3", "6");
        relatorio.escreveRelatorio();
        
    }

}
