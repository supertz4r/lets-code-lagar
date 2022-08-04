package regras;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VerificaRegras implements RegrasLagar {

    private String dadosArquivo = "";
    File resourceFile = new File("src/input");
    private String caminhoInput = resourceFile.getAbsolutePath() + "/regras.txt";

    public VerificaRegras() {
        try {
            this.dadosArquivo = leArquivo(this.caminhoInput);
        } catch (IOException exception) {
            System.out.println("Erro ao buscar o arquivo: " + exception.getMessage());
        }
    }

    private String leArquivo(String nomeArquivo) throws IOException {
        String dados;
        Path path = Paths.get(nomeArquivo);
        dados = Files.readString(path);
        return dados;
    }

    @Override
    public String getDadosArquivo() {
        return dadosArquivo;
    }

    @Override
    public LocalDate getDataArquivo() {
        final String regex = "\\d{2}/\\d{2}/\\d{4}";
        final String string = this.dadosArquivo;
        LocalDate data = LocalDate.now();

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (matcher.find()) {
            data = LocalDate.parse(matcher.group(0).trim(), formatter);
        }
        return data;
    }

    @Override
    public List<String> getAzeitonas() {
        List<String> azeitonas = new ArrayList<>();

        getPlantacoes().stream()
                .map((l) -> l.get("azeitona").toUpperCase())
                .forEach((azeitona) -> azeitonas.add(azeitona));

        return azeitonas;
    }

    @Override
    public List<Map<String, String>> getPlantacoes() {

        final String chaveQuantidade = "qtde";
        final String chaveAzeitona = "azeitona";
        final String chaveDistancia = "distancia";

        final String regex = "(?<" + chaveQuantidade + ">\\d{1,2})\\s[A-zçõã]+\\s[de]{1,2}\\s(?<" + chaveAzeitona
                + ">[A-z]+)\\s[A-z]+\\sa\\s[A-zâ]+\\s[a-z]+\\s(?<" + chaveDistancia + ">\\d{1,2})\\s[a-z]+";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(this.dadosArquivo);
        List<Map<String, String>> variaveis = new ArrayList<>();

        while (matcher.find()) {

            Map<String, String> plantacao = new HashMap<>();

            for (int i = 1; i <= matcher.groupCount(); i++) {
                plantacao.put(chaveQuantidade, matcher.group(chaveQuantidade));
                plantacao.put(chaveAzeitona, matcher.group(chaveAzeitona));
                plantacao.put(chaveDistancia, matcher.group(chaveDistancia));
            }

            variaveis.add(plantacao);
        }
        return variaveis;
    }

    @Override
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

    @Override
    public int[] getRangeCapacidadeCaminhao() {
        int[] range = new int[2];
        final String regex = "[A-z]+\\sentre\\s(\\d{1,})\\s[a-zé]+\\s(\\d{1,})\\s[A-z]+\\sde\\sazeitonas.";
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

    @Override
    public Integer[] getRangeEnchimentoSegundos() {
        Integer[] range = new Integer[2];
        final String regex = "\\w[A-z]+\\s\\w[A-zãç]+\\s\\w[a-z]+\\s(\\d{1})+\\sa+\\s(\\d)";
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

    @Override
    public Integer[] getRangeRecepcao() {
        Integer[] range = new Integer[2];
        final String regex = "\\w[A-z]+\\s\\w[recepção]+\\s\\w[a-z]+\\s[a-z]+\\s(\\d)\\sa\\s(\\d)\\s[segundos]+";
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

    @Override
    public Map<String, Integer> getTempoXToneladas() {

        Map<String, Integer> tempoXToneladas = new HashMap<>();
        String segundos = "tempo";
        String toneladas = "toneladas";

        final String regex = "(?<" + segundos + ">\\d)\\ssegundos\\s[a-z]+\\sa\\s(?<" + toneladas
                + ">\\d)\\stoneladas?";
        final String string = this.dadosArquivo;

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                tempoXToneladas.put(segundos, Integer.parseInt(matcher.group(segundos)));
                tempoXToneladas.put(toneladas, Integer.parseInt(matcher.group(toneladas)));
            }
        }

        return tempoXToneladas;
    }

    @Override
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

    @Override
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

    @Override
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
