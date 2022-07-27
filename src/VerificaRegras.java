import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificaRegras {

    private String dadosArquivo;

    public String getDadosArquivo() {
        return dadosArquivo;
    }

    public VerificaRegras(String nomeArquivo) throws IOException {
        this.dadosArquivo = leArquivo(nomeArquivo);
    }

    private static String leArquivo(String nomeArquivo) throws IOException {
        String dados;
        Path path = Paths.get(nomeArquivo);
        dados = new String(Files.readString(path));
        return dados;
    }

    public static List<Map<String, String>> buscaPlantacoes(String dados) {

        final String regex = "(\\d{1,2})\\s[A-zçõã]+\\s[de]{1,2}\\s([A-z]+)\\s[A-z]+\\s[a]\\s[A-zâ]+\\s[a-z]+\\s(\\d{1,2})\\s[a-z]+";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(dados);
        List<Map<String, String>> variaveis = new ArrayList<>();

        while (matcher.find()) {

            Map<String, String> plantacao = new HashMap<>();

            for (int i = 1; i <= matcher.groupCount(); i++) {
                String chave = " ";
                if (i == 1) {
                    chave = "qtde";
                }
                if (i == 2) {
                    chave = "azeitona";
                }
                if (i == 3) {
                    chave = "distancia";
                }
                plantacao.put(chave, matcher.group(i));
            }

            variaveis.add(plantacao);
        }
        return variaveis;
    }

}