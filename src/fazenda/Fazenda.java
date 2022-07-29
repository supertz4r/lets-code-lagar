package fazenda;

import java.util.ArrayList;
import java.util.List;

import lagar.Lagar;
import plantacao.Plantacao;

public class Fazenda {

    Double tempoEncherEsvaziarCaminhao;
    final private long inicioProducao;
    private static Fazenda fazenda;
    private List<Plantacao> plantacoes = new ArrayList<>();
    private List<Thread> threads = new ArrayList<>();
    private Lagar lagar;
    // private final List<String> azeitonas = List.of("Galega",
    // "Cordovil",
    // "Picual");

    private Fazenda() {

        this.lagar = new Lagar();

        // Automatizar essa parte com a extração de arquivos.

        plantacoes.add(new Plantacao.Builder().nomePlantacao("G1")
                .variedadePlantacao("Galega")
                .distanciaLagarSegundos(4)
                .lagar(lagar)
                .build());

        plantacoes.add(new Plantacao.Builder().nomePlantacao("G2")
                .variedadePlantacao("Galega")
                .distanciaLagarSegundos(4)
                .lagar(lagar)
                .build());

        plantacoes.add(new Plantacao.Builder().nomePlantacao("C1")
                .variedadePlantacao("Cordovil")
                .distanciaLagarSegundos(4)
                .lagar(lagar)
                .build());

        plantacoes.add(new Plantacao.Builder().nomePlantacao("C2")
                .variedadePlantacao("Cordovil")
                .distanciaLagarSegundos(4)
                .lagar(lagar)
                .build());

        plantacoes.add(new Plantacao.Builder().nomePlantacao("P1")
                .variedadePlantacao("Picual")
                .distanciaLagarSegundos(4)
                .lagar(lagar)
                .build());

        // =======================================================================
        this.inicioProducao = System.currentTimeMillis();

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
                threads.add(new Thread(plantacao));
            }
        });

        long tempoProducaoMinutos = ((System.currentTimeMillis() - inicioProducao) / 60000);

        threads.stream().forEach(thread -> {
            {
                thread.start();
            }
        });

        while (tempoProducaoMinutos < 2) {

            tempoProducaoMinutos = ((System.currentTimeMillis() - inicioProducao) / 60000);

        }

        plantacoes.stream().forEach(plantacao -> {
            {
                plantacao.setProduzir(false);
            }
        });

    }

}
