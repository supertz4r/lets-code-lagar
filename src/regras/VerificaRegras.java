package regras;

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

    private String dadosArquivo = "";

    public String getDadosArquivo() {
        return dadosArquivo;
    }

    public VerificaRegras(String nomeArquivo) throws IOException {
        this.dadosArquivo = leArquivo(nomeArquivo);
    }

    private String leArquivo(String nomeArquivo) throws IOException {
        String dados;
        Path path = Paths.get(nomeArquivo);
        dados = new String(Files.readString(path));
        return dados;
    }

    public String getDataArquivo() {
        final String regex = "\\d{2}/\\d{2}/\\d{4}";
        final String string = this.dadosArquivo;
        String data = " ";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            data = matcher.group(0);
        }
        return data;// fazer tratamento qdo não achar nada.
    }

    // 5 Plantações de Azeitonas:

    public List<Map<String, String>> getPlantacoes() {

        final String regex = "(\\d{1,2})\\s[A-zçõã]+\\s[de]{1,2}\\s([A-z]+)\\s[A-z]+\\s[a]\\s[A-zâ]+\\s[a-z]+\\s(\\d{1,2})\\s[a-z]+";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(this.dadosArquivo);
        List<Map<String, String>> variaveis = new ArrayList<>();

        while (matcher.find()) {

            Map<String, String> plantacao = new HashMap<>();

            for (int i = 1; i <= matcher.groupCount(); i++) {
                String chave = "";
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

    // Cada plantação enche um caminhão entre 2 a 8 segundos:
    public Integer[] getRangeEnchimentoSegundos() {
        Integer[] range = new Integer[2];
        final String regex = "\\w[A-z]+\\s\\w[A-zãç]+\\s\\w[a-z]+\\s(\\d{1})+\\s[a]+\\s(\\d{1})";
        final String string = this.dadosArquivo;

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                range[i - 1] = Integer.parseInt(matcher.group(i));
            }
        }
        return range;
    }

    // Cada recepção demora entre 2 a 8 segundos para ser processada.
    public Integer[] getRangeRecepcao() {
        Integer[] range = new Integer[2];
        final String regex = "\\w[A-z]+\\s\\w[recepção]+\\s\\w[a-z]+\\s[a-z]+\\s(\\d{1})\\s[a]\\s(\\d{1})\\s[segundos]+";
        final String string = this.dadosArquivo;

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                range[i - 1] = Integer.parseInt(matcher.group(i));
            }
        }
        return range;
    }

    public int getCapacidadeRecepcaoLagar() {

        final String regex = "(\\d{1,2})\\s(Capacidades|Capacidade|capacidade|capacidades) de Recepção no lagar.+";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(this.dadosArquivo);
        int capacidadeRecepcaoLagar = 0;

        while (matcher.find()) {

            for (int i = 1; i <= matcher.groupCount(); i++) {
                if (i == 1) {
                    capacidadeRecepcaoLagar = Integer.parseInt(matcher.group(i));
                }
            }
        }
        return capacidadeRecepcaoLagar;
    }

    // Varia entre 4 até 16 toneladas de azeitonas.
    public int[] getRangeCapacidadeCaminhao() {

        final String regex = "[A-z]+\\sentre\\s(\\d{1,})\\s[a-zé]+\\s(\\d{1,})\\s[A-z]+\\sde\\sazeitonas.";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(dadosArquivo);

        int[] rangeCapacidadeCaminhao = new int[1];
        while (matcher.find()) {
            rangeCapacidadeCaminhao = new int[matcher.groupCount()];
            for (int i = 1; i <= matcher.groupCount(); i++) {
                rangeCapacidadeCaminhao[i - 1] = Integer.parseInt(matcher.group(i));
            }
        }
        return rangeCapacidadeCaminhao;

    }

    // Quando atingir 12 caminhões na fila em espera no Lagar, as plantações param
    // de produzir.
    public Integer getMaxCaminhoesNaFila() {
        int maximo = 0;
        final String regex = "(\\d{1,2})\\s[caminhõoes]+\\s[na]+\\s[fila]+";
        final String string = this.dadosArquivo;

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            maximo = Integer.parseInt(matcher.group(1));
        }

        return maximo;
    }

    // Sendo que 2 segundos corresponde a 4 toneladas.
    // public Map<String, Integer> getTempoXToneladas() {
    // Map<String, Integer> tempoXToneladas = new HashMap<>();

    // final String regex =
    // "(\\d{1})\\s+[segundos]+\\s[a-z]+\\s[a]\\s(\\d{1})\\s+[toneladas]+";
    // final String string = this.dadosArquivo;

    // final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
    // final Matcher matcher = pattern.matcher(string);

    // for (int i = 1; i <= matcher.groupCount(); i++) {
    // String chave = "";
    // if (i == 1) {
    // chave = "tempo";
    // }
    // if (i == 2) {
    // chave = "toneladas";
    // }
    // tempoXToneladas.put(chave, Integer.parseInt(matcher.group(i)));
    // }

    // return tempoXToneladas;
    // }

    // Quando o lagar voltar a atingir 4 caminhões em espera, então as plantações
    // podem enviar mais.
    public Integer getMinCaminhoesNaFila() {
        int minimo = 0;
        final String regex = "(\\d{1,2})\\s[caminhõoes]+\\s[em]+\\s[espera]+";
        final String string = this.dadosArquivo;

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            minimo = Integer.parseInt(matcher.group(1));
        }
        return minimo;
    }

    // Com 2 minutos de execução geral as plantações fecham a sua produção.
    public Integer getTempoExecucaoGeralMax() {
        int tempo = 0;
        final String regex = "(\\d{1,2})\\s[minutos]+\\s[de]+\\s[execucçãao]+\\s[geral]+";
        final String string = this.dadosArquivo;

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            tempo = Integer.parseInt(matcher.group(1));
        }
        return tempo;
    }
}
