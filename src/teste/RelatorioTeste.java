package teste;

import java.io.IOException;
import java.nio.file.Path;

import output.Relatorio;

public class RelatorioTeste {

    public static void main(String[] args) throws IOException {

        Relatorio relatorio = new Relatorio();
        // String caminho = "src/output/";
        String nomeRelatorio = "relatorio.txt";
        // String nomeRelatorio = caminho.concat("relatorio.txt");
        // String registro = "hh:mm:ss - XXXX >> Y toneladas de AZEITONA na recepcao X
        // de origem da plantacao X com tempo total de T segundos. ";
        // Boolean relatorioCriado = relatorio.escreveRelatorio(nomeRelatorio,
        // registro);
        // System.out.println(relatorioCriado);
        Relatorio.adicionaLinha("linha 1");
        Relatorio.adicionaLinha("linha 2");
        Relatorio.adicionaLinha("linha 3");
        relatorio.escreveRelatorio(nomeRelatorio);

        System.out.println(relatorio);

    }

}
