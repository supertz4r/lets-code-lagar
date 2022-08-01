package teste;

import java.io.IOException;

import output.Relatorio;

public class RelatorioTeste {

    public static void main(String[] args) throws IOException {

        Relatorio relatorio = new Relatorio();

        String nomeRelatorio = "relatorio.txt";

        // String registro = "hh:mm:ss - XXXX >> Y toneladas de AZEITONA na recepcao X
        // de origem da plantacao X com tempo total de T segundos. ";

        relatorio.adicionaLinha("linha 1");
        relatorio.adicionaLinha("linha 2");
        relatorio.adicionaLinha("linha 3");
        relatorio.escreveRelatorio(nomeRelatorio);

    }

}
