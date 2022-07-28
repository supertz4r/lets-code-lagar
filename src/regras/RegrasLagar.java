package regras;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface RegrasLagar {

    public String getDadosArquivo();

    public String leArquivo(String nomeArquivo) throws IOException;

    // public String getDataArquivo(); precisa mudar o metodo para retornar String
    public List<Map<String, String>> getPlantacoes(); // 5 Plantações de Azeitonas:...

    public Integer[] getRangeEnchimentoSegundos(); // Cada plantação enche um caminhão entre 2 a 8 segundos:

    public Integer[] getRangeRecepcao();// Cada recepção demora entre 2 a 8 segundos para ser processada.

    public int getCapacidadeRecepcaoLagar(); // 3 Capacidades de Recepção no lagar em simultâneo.

    public int[] getRangeCapacidadeCaminhao(); // Varia entre 4 até 16 toneladas de azeitonas.

    public Integer getMaxCaminhoesNaFila(); // Quando atingir 12 caminhões na fila em espera no Lagar, as plantações
                                            // param de produzir.
    // public Map<String, Integer> getTempoXToneladas(); // Sendo que 2 segundos
    // corresponde a 4 toneladas.

    public Integer getMinCaminhoesNaFila(); // Quando o lagar voltar a atingir 4 caminhões em espera, então as
                                            // plantações podem enviar mais.

    public Integer getTempoExecucaoGeralMax(); // Com 2 minutos de execução geral as plantações fecham a sua produção.

}
