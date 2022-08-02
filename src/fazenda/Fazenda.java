package fazenda;

import java.util.ArrayList;
import java.util.List;

import lagar.Lagar;
import lagar.Processamento;
import plantacao.Plantacao;

public class Fazenda {

    Double tempoEncherEsvaziarCaminhao;
    final private long inicioProducao;
    private static Fazenda fazenda;
    private List<Plantacao> plantacoes = new ArrayList<>();
    private List<Thread> threads = new ArrayList<>();
    private Lagar lagar = new Lagar.Builder().build();
    private boolean todasPlantacoesFinalizadas = false;
    // private final List<String> azeitonas = List.of("Galega",
    // "Cordovil",
    // "Picual");

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

    public Lagar getLagar() {
        return lagar;
    }

    private void criaPlantacoes() {

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

        // Execução da produção.
        while (tempoProducaoMinutos < 2) {

            tempoProducaoMinutos = ((System.currentTimeMillis() - inicioProducao) / 60000);

            if (lagar.getTamanhoFila() > 0 && lagar.getEmProcessamento() < lagar.getCapacidadeRecepcaoLagar()) {
                lagar.incrementaProcessamento();
                new Thread(new Processamento(lagar, lagar.getNumeroReceptora()), "Processamento caminhão").start();
            }

        }

        // Notificação para encerrar a produção.
        plantacoes.stream().forEach(plantacao -> {
            {
                plantacao.setProduzir(false);
            }
        });

        while (lagar.getTamanhoFila() != 0 || !this.todasPlantacoesFinalizadas) {

            threads.stream().forEach(thread -> {
                {
                    if (!thread.getState().name().equals("TERMINATED")) {
                        this.todasPlantacoesFinalizadas = false;
                    }
                }
            });

            if (lagar.getEmProcessamento() < lagar.getCapacidadeRecepcaoLagar()) {
                lagar.incrementaProcessamento();
                new Thread(new Processamento(lagar, lagar.getNumeroReceptora())).start();
            }

            this.todasPlantacoesFinalizadas = true;

        }
        System.out.println("################## FIM DA SIMULAÇÃO ##################");
    }

}
