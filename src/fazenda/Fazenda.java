package fazenda;

import java.util.ArrayList;
import java.util.List;

import lagar.Lagar;
import plantacao.Plantacao;

public class Fazenda {

    Double tempoEncherEsvaziarCaminhao;
    private static Fazenda fazenda;
    private List<Plantacao> plantacoes = new ArrayList<>();
    private Lagar lagar;
    private final List<String> azeitonas = List.of("Galega",
            "Cordovil",
            "Picual");

    private Fazenda() {

        // Automatizar essa parte com a extração de arquivos.

        plantacoes.add(new Plantacao.Builder().nomePlantacao("G1")
                .variedadePlantacao("Galega")
                .distanciaLagarSegundos(4)
                .build());

        plantacoes.add(new Plantacao.Builder().nomePlantacao("G2")
                .variedadePlantacao("Galega")
                .distanciaLagarSegundos(4)
                .build());

        plantacoes.add(new Plantacao.Builder().nomePlantacao("C1")
                .variedadePlantacao("Cordovil")
                .distanciaLagarSegundos(4)
                .build());

        plantacoes.add(new Plantacao.Builder().nomePlantacao("C2")
                .variedadePlantacao("Cordovil")
                .distanciaLagarSegundos(4)
                .build());

        plantacoes.add(new Plantacao.Builder().nomePlantacao("P1")
                .variedadePlantacao("Picual")
                .distanciaLagarSegundos(4)
                .build());

        // =======================================================================

        criaPlantacoes();
    }

    public static Fazenda getInstance() {
        if (fazenda == null) {
            return new Fazenda();
        }
        return fazenda;
    }

    public void criaPlantacoes() {

        plantacoes.stream().forEach(plantacao -> {
            {
                new Thread(plantacao).start();
            }
        });

    }

}
