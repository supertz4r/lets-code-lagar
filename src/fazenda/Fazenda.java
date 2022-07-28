package fazenda;

import java.util.ArrayList;
import java.util.List;

import lagar.Lagar;
import plantacao.Plantacao;

public class Fazenda {
    private final List<String> azeitonas = List.of("Galega",
            "Cordovil",
            "Picual");
    Double tempoEncherEsvaziarCaminhao;
    private static Fazenda fazenda;
    private List<Plantacao> plantacoes = new ArrayList<>();
    private Lagar lagar;

    private Fazenda() {
        criaPlantacoes();
    }

    public static Fazenda getInstance() {
        if (fazenda == null) {
            return new Fazenda();
        }
        return fazenda;
    }

    public void criaPlantacoes() {

    }

}
