package output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Relatorio {

    public boolean escreveRelatorio(String nomeRelatorio, String conteudo) {

        try (
                FileWriter criaRelatorio = new FileWriter(nomeRelatorio, true);
                BufferedWriter buffer = new BufferedWriter(criaRelatorio);
                PrintWriter escreveRelatorio = new PrintWriter(buffer);) {
            escreveRelatorio.append(conteudo);
            escreveRelatorio.println();
            return true;

        } catch (IOException e) {

            e.printStackTrace();
            return false;
        }

    }

}
