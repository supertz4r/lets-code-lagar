import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificaRegras {

    public static void main(String[] args) throws IOException {

        // Fluxo de Entrada com Arquivo
        FileInputStream fis = new FileInputStream("regras.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        String linha = br.readLine();

        while (linha != null) {

            System.out.println(linha);
            linha = br.readLine();
        }
        br.close();

        // Resumindo:
        /*
         * Primeiro, criamos o fluxo concreto com o arquivo, mas ainda binário, em
         * seguida, conseguimos transforma-los em caracteres, mas apenas a
         * contabilização, por fim, com o BufferedReader, podemos utilizar o método
         * readLine(), que nos permite ler linha a linha.
         */
        final String regex = "(\\d{1,2})\\s[A-zçõã]+\\s[de]{1,2}\\s([A-z]+)\\s[A-z]+\\s[a]\\s[A-zâ]+\\s[a-z]+\\s(\\d{1,2})\\s[a-z]+";

        final String string = " Variedades de Azeitonas:\n"
                + "    Galega\n"
                + "    Cordovil\n"
                + "    Picual\n\n"
                + "5 Plantações de Azeitonas:\n"
                + "    2 plantações de Galega com a distância de 4 segundos para o lagar.\n"
                + "    2 plantações de Cordovil com a distância de 3 segundos para o lagar.\n"
                + "    1 plantação de Picual com a distância de 2 segundos para o lagar.\n\n"
                + "3 Capacidades de Recepção no lagar em simultâneo.\n\n"
                + "Capacidade dos Caminhões de transporte:\n"
                + "    Varia entre 4 até 16 toneladas de azeitonas.\n\n"
                + "Configurações Gerais:\n"
                + "    Cada plantação enche um caminhão entre 2 a 8 segundos.\n"
                + "    Cada recepção demora entre 2 a 8 segundos para ser processada.\n"
                + "    Sendo que 2 segundos corresponde a 4 toneladas.\n"
                + "    Quando atingir 12 caminhões na fila em espera no Lagar, as plantações param de produzir.\n"
                + "    Quando o lagar voltar a atingir 4 caminhões em espera, então as plantações podem enviar mais.\n";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);
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