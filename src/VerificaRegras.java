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

    public static void main(String[] args) throws IOException {

    Path path = Paths.get("regras.txt");
    String dados = new String(Files.readString(path));
    // System.out.println(dados);
        final String regex = "(\\d{1,2})\\s[A-zçõã]+\\s[de]{1,2}\\s([A-z]+)\\s[A-z]+\\s[a]\\s[A-zâ]+\\s[a-z]+\\s(\\d{1,2})\\s[a-z]+";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(dados);
        List<Map<String, String>> variaveis = new ArrayList<>();

        while (matcher.find()) {
            // System.out.println("Full match: " + matcher.group(0));
            Map<String, String> plantacao = new HashMap<>();

            // System.out.println("group: " + matcher.group(0));
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
                // System.out.println("Group " + i + ": " + matcher.group(i));
                plantacao.put(chave, matcher.group(i));
            }

            variaveis.add(plantacao);
        }

        System.out.println(variaveis);
    }

}