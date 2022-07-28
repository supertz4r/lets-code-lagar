package teste;

import java.nio.file.Path;

import output.Relatorio;

public class RelatorioTeste {

    public static void main(String[] args) {

        Relatorio relatorio = new Relatorio();

        String nomeRelatorio = "src/output/relatorio.txt";
        String registro = "hh:mm:ss - XXXX >> Y toneladas de AZEITONA na recepcao X de origem da plantacao X com tempo total de T segundos. ";
        Boolean relatorioCriado = relatorio.escreveRelatorio(nomeRelatorio, registro);
        System.out.println(relatorioCriado);
    }

}
