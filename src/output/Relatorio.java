package output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import regras.VerificaRegras;

public class Relatorio {

    private VerificaRegras regras;
    private List<String> linhas;
    private int toneladasAcumuladasCount = 0;

    public Relatorio() {
        this.regras = new VerificaRegras();
        this.linhas = new ArrayList<>();
        criaCabecalho();
    }

    private void criaCabecalho() {
        LocalDate localDate = this.regras.getDataArquivo();
        String dia = String.valueOf(localDate.getDayOfMonth());
        String mes = String.valueOf(localDate.getMonthValue());
        
        if (dia.length() < 2) {
            dia = 0 + dia;
        }
        if (mes.length() < 2) {
            mes = 0 + mes;
        }

        String data = dia + "/" + mes;
        this.linhas.add(data);
        this.linhas.add("");
    }

    public void adicionaLinha(int valorToneladas, String tipoAzeitona, int numeroRecepcao, String origemPlantacao, int totalDeSegundos) {
        String modeloLinha = "horarioFinal - toneladasAcumuladas >> valorToneladas toneladas de tipoAzeitona na recepção numeroRecepcao de origem da plantação origemPlantacao com tempo total de totalDeSegundos segundos.";
        
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String horarioFinal = new SimpleDateFormat("HH:mm:ss").format(timestamp.getTime());

        this.toneladasAcumuladasCount += valorToneladas;
        String toneladasAcumuladas = "0000" + String.valueOf(this.toneladasAcumuladasCount);
        toneladasAcumuladas = toneladasAcumuladas.substring(toneladasAcumuladas.length() - 4);

        this.linhas.add(modeloLinha.replace("horarioFinal", horarioFinal)
                .replace("toneladasAcumuladas", toneladasAcumuladas)
                .replace("valorToneladas", String.valueOf(valorToneladas))
                .replace("tipoAzeitona", tipoAzeitona)
                .replace("numeroRecepcao", String.valueOf(numeroRecepcao))
                .replace("origemPlantacao", origemPlantacao)
                .replace("totalDeSegundos", String.valueOf(totalDeSegundos)));
    }
    
    public void escreveRelatorio() throws IOException {
        try {
            Path path = Path.of("src/output/" + gerarNomeArquivo());
            Files.write(path, this.linhas);

            System.out.println("Relatorio criado\n\n");
        
        } catch (IOException exception) {
            System.out.println("Não foi possível criar o relatório:");
            System.out.println(exception.getMessage());
            System.out.println(exception.getStackTrace());
        }
        this.linhas.clear();
    }

    public String gerarNomeArquivo() {
        String nomeRelatorio = "relatorio-yyyy.txt";
        String ano = String.valueOf(regras.getDataArquivo().getYear());
        nomeRelatorio = nomeRelatorio.replace("yyyy", ano);

        return nomeRelatorio;
    }

}
