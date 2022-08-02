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
    private Lagar lagar = new Lagar.Builder().build();
    private static boolean todasPlantacoesFinalizadas = false;
    // private final List<String> azeitonas = List.of("Galega",
    // "Cordovil",
    // "Picual");

    public static boolean isTodasPlantacoesFinalizadas() {
        return todasPlantacoesFinalizadas;
    }

    private void verificaTodasPlantacoesFinalizadas() {
        for (Thread thread : threads) {
            System.out.println("Thread " + thread.getName() + ": " + thread.getState().name());
            if (!thread.getState().name().equals("TERMINATED")) {

                // return false;
            }
        }
        // return true;
    }

    private Fazenda() {

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
                .distanciaLagarSegundos(3)
                .lagar(lagar)
                .build());

        plantacoes.add(new Plantacao.Builder().nomePlantacao("C2")
                .variedadePlantacao("Cordovil")
                .distanciaLagarSegundos(3)
                .lagar(lagar)
                .build());

        plantacoes.add(new Plantacao.Builder().nomePlantacao("P1")
                .variedadePlantacao("Picual")
                .distanciaLagarSegundos(2)
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

    private void verificaFila() {
        if (lagar.getIsCapacidadeMaxima()) {
            plantacoes.stream().forEach(plantacao -> {
                plantacao.setEmEspera(true);

            });
        } else {
            plantacoes.stream().forEach(plantacao -> {
                plantacao.setEmEspera(false);

            });
        }
        ;
    }

    public void criaPlantacoes() {

        // Threads de plantação.
        plantacoes.stream().forEach(plantacao -> {
            {
                threads.add(new Thread(plantacao, "Plantação " + plantacao.getNomePlantacao()));
            }
        });

        long tempoProducaoMinutos = ((System.currentTimeMillis() - inicioProducao) / 60000);

        // Inicialização das plantações.
        threads.stream().forEach(thread -> {
            {
                thread.start();
            }
        });

        new Thread(lagar, "Lagar").start();

        // Execução da produção.
        while (tempoProducaoMinutos < 2) {

            tempoProducaoMinutos = ((System.currentTimeMillis() - inicioProducao) / 60000);

            verificaFila();

            // if (lagar.getTamanhoFila() > 0 && lagar.getEmProcessamento() <
            // lagar.getCapacidadeRecepcaoLagar()) {
            // new Thread(new Processamento(lagar), "Processamento caminhão").start();
            // }

        }

        // Notificação para encerrar a produção.
        plantacoes.stream().forEach(plantacao -> {
            {
                plantacao.setProduzir(false);
            }

            todasPlantacoesFinalizadas = true;
        });

        // todasPlantacoesFinalizadas = verificaTodasPlantacoesFinalizadas();
        // verificaTodasPlantacoesFinalizadas();
        // while (!lagar.recepcaoVazia() || !todasPlantacoesFinalizadas) {

        // if (lagar.getEmProcessamento() < lagar.getCapacidadeRecepcaoLagar()) {
        // new Thread(new Processamento(lagar)).start();
        // }

        // }

    }

}
