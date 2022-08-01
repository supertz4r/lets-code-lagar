package output;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {

    private List<String> linhas = new ArrayList<>();

    public  void adicionaLinha(String linha) {
        this.linhas.add(linha);
    }
    
    public void escreveRelatorio(String nomeRelatorio) throws IOException {

        Files.write(Path.of("src/output/" + nomeRelatorio), this.linhas);
        this.linhas.clear(); //limpa arraylist linhas
        System.out.println("Relatorio criado");

    }

    

}
