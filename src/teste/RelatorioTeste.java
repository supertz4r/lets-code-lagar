package teste;

import java.io.IOException;

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
        ///Relatorio.adicionaLinha("linha 1");
        //Relatorio.adicionaLinha("linha 2");
       // Relatorio.adicionaLinha("linha 3");
        relatorio.escreveRelatorio();

        System.out.println(relatorio);

        relatorio.adicionaLinha("09:24:56", "0004", "4", "Galega", "1", "2", "8");
        relatorio.adicionaLinha("09:24:57", "0012", "8", "Picual", "2", "5", "6");
        relatorio.adicionaLinha("09:24:58", "0018", "6", "Cordovil", "1", "3", "6");
        relatorio.escreveRelatorio();
        
    }

}
