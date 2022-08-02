package regras;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RegrasLagar {

    //regras.txt
    public String getDadosArquivo();

    //28/04/2022
    public LocalDate getDataArquivo(); 

    //Galega, Cordovil, Picual
    public List<String> getAzeitonas();

    //5 Plantações de Azeitonas:...
    public List<Map<String, String>> getPlantacoes(); 

    //3 Capacidades de Recepção no lagar em simultâneo.
    public int getCapacidadeRecepcaoLagar(); 

    //Varia entre 4 até 16 toneladas de azeitonas.
    public int[] getRangeCapacidadeCaminhao(); 

    //Cada plantação enche um caminhão entre 2 a 8 segundos:
    public Integer[] getRangeEnchimentoSegundos(); 

    // Cada recepção demora entre 2 a 8 segundos para ser processada.
    public Integer[] getRangeRecepcao();

    //Sendo que 2 segundos corresponde a 4 toneladas.
    public Map<String, Integer> getTempoXToneladas();

    // Quando atingir 12 caminhões na fila...
    public Integer getMaxCaminhoesNaFila();

    // Quando o lagar voltar a atingir 4 caminhões...
    public Integer getMinCaminhoesNaFila(); 

    //Com 2 minutos de execução geral as plantações...
    public Integer getTempoExecucaoGeralMax(); 
    
}
