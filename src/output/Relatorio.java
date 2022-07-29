package output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Relatorio {

    private List<String> linhas = new ArrayList<>();

    public List<String> getLinhas() {
        return this.linhas;
    }

    public void setLinhas(String linha) {
        this.linhas.add(linha);
    }

    public void escreveRelatorio(String nomeRelatorio, List<String> linhas) throws IOException {

        // try (
        // FileWriter criaRelatorio = new FileWriter(nomeRelatorio, true);
        // BufferedWriter buffer = new BufferedWriter(criaRelatorio);
        // PrintWriter escreveRelatorio = new PrintWriter(buffer);) {
        // escreveRelatorio.append(conteudo);
        // escreveRelatorio.println();
        // return true;

        // } catch (IOException e) {

        // e.printStackTrace();
        // return false;
        // }

        Files.write(Path.of("src/output/" + nomeRelatorio), linhas);
    }

    public List<String> adicionaLinha(String linha) {

        return null;
    }
}
