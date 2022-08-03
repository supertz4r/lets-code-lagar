package teste;

import java.io.IOException;

import output.Relatorio;

public class RelatorioTeste {

    public static void main(String[] args) throws IOException {

        Relatorio relatorio = new Relatorio();

        relatorio.adicionaLinha(4, "Galega", 1, "2", 8);
        relatorio.adicionaLinha(8, "Picual", 2, "5", 6);
        relatorio.adicionaLinha(6, "Cordovil", 1, "3", 6);
        relatorio.escreveRelatorio();
        
    }

}
