import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        VerificaRegras regras = new VerificaRegras("regras.txt");
        String informacao = regras.getDadosArquivo();

        List<Map<String, String>> dadosPlantacoes = new ArrayList<>();
        dadosPlantacoes = regras.getDadosPlantacoes(informacao);
        System.out.println("imprime dados de plantacoes: " + dadosPlantacoes);

        String data = regras.getDataArquivo(informacao);
        System.out.println(data);
        Integer[] rangeEnchimento = new Integer[2];
        rangeEnchimento = regras.getRangeEnchimento(informacao);
        Integer[] rangeRecepcao = new Integer[2];
        rangeRecepcao = regras.getRangeRecepcao(informacao);
        System.out.println("de " + rangeRecepcao[0] + " a " + rangeRecepcao[1] + "segundos");

    }
}
