package output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import regras.VerificaRegras;

public class Relatorio {

    private VerificaRegras regras = new VerificaRegras();
    private String ano = String.valueOf(regras.getDataArquivo().getYear());
    private String data = String.valueOf(regras.getDataArquivo().getDayOfMonth()) + "/" + String.valueOf(regras.getDataArquivo().getMonthValue());
    private String nomeRelatorio = "relatorio-yyyy.txt";
    private String modeloLinha = "horarioFinal - toneladasAcumuladas >> valorToneladas toneladas de tipoAzeitona na recepção numeroRecepcao de origem da plantação origemPlantacao com tempo total de totalDeSegundos segundos.";

    private List<String> linhas = new ArrayList<>();

    public Relatorio() {
        linhas.add(data);
        linhas.add("");
    }

    //refatorar para receber esses argumetos talvez em um array, ou em um map
    public void adicionaLinha(String horarioFinal,
            String toneladasAcumuladas, 
            String valorToneladas, 
            String tipoAzeitona, 
            String numeroRecepcao,
            String origemPlantacao,
            String totalDeSegundos) {

        this.linhas.add(modeloLinha.replace("horarioFinal", horarioFinal)
                .replace("toneladasAcumuladas", toneladasAcumuladas)
                .replace("valorToneladas", valorToneladas)
                .replace("tipoAzeitona", tipoAzeitona)
                .replace("numeroRecepcao", numeroRecepcao)
                .replace("origemPlantacao", origemPlantacao)
                .replace("totalDeSegundos", totalDeSegundos));
    }
    
    public void escreveRelatorio() throws IOException {

        this.nomeRelatorio = nomeRelatorio.replace("yyyy", ano);
        Path path = Path.of("src/output/" + this.nomeRelatorio);
        Files.write(path, this.linhas);
        
        this.linhas.clear(); //limpa arraylist linhas

        System.out.println("Relatorio criado\n\n");
        System.out.println(Files.readString(path));
    }

}
