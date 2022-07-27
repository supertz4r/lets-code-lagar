import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        VerificaRegras regras = new VerificaRegras("regras.txt");
        String informacao = regras.getDadosArquivo();
        System.out.println("imprime regras: ");
        System.out.println(informacao);

        List<Map<String, String>> dadosPlantacoes = new ArrayList<>();
        dadosPlantacoes = regras.buscaPlantacoes(informacao);
        System.out.println("imprime dados de plantacoes: " + dadosPlantacoes);
    }
}
