package teste;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import regras.VerificaRegras;

public class VerificaRegrasTeste {

    public static void main(String[] args) throws Exception {
        File resourceFile = new File("src/input");
        VerificaRegras regras = new VerificaRegras(resourceFile.getAbsolutePath() + "/regras.txt");
        String informacao = regras.getDadosArquivo();

        List<Map<String, String>> dadosPlantacoes = new ArrayList<>();
        dadosPlantacoes = regras.getPlantacoes();
        System.out.println("imprime dados de plantacoes: " + dadosPlantacoes);

        LocalDate data = regras.getDataArquivo();
        System.out.println(data);
        Integer[] rangeEnchimento = new Integer[2];
        rangeEnchimento = regras.getRangeEnchimentoSegundos();
        Integer[] rangeRecepcao = new Integer[2];
        rangeRecepcao = regras.getRangeRecepcao();
        System.out.println("de " + rangeRecepcao[0] + " a " + rangeRecepcao[1] + "segundos");

        System.out.println("getPlantacoes\n\t" + regras.getPlantacoes());
        System.out.println("getCapacidadeRecepcaoLagar\n\t" + regras.getCapacidadeRecepcaoLagar());
        System.out.print("getRangeCapacidadeCaminhao\n\t");
        for (int i = 0; i < regras.getRangeCapacidadeCaminhao().length; i++) {
            System.out.print(regras.getRangeCapacidadeCaminhao()[i] + " ");
        }

    }

}
