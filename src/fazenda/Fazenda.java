package fazenda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import lagar.Lagar;
import plantacao.Plantacao;
import regras.RegrasLagar;
import regras.VerificaRegras;

public class Fazenda {

    private static Fazenda fazenda;
    private static boolean todasPlantacoesFinalizadas = false;
    final private long inicioProducao;
    Double tempoEncherEsvaziarCaminhao;
    private List<Plantacao> plantacoes = new ArrayList<>();
    private List<Thread> threads = new ArrayList<>();
    private Lagar lagar = new Lagar.Builder().build();
    private RegrasLagar regras = new VerificaRegras();

    private Fazenda() {

        List<Map<String, String>> plantacoesInformacoes = regras.getPlantacoes();
        IntStream.range(0, plantacoesInformacoes.size()).forEach((i) -> {
            IntStream.range(0, Integer.parseInt(plantacoesInformacoes.get(i).get("qtde")))
                    .forEach((plantacao) -> {
                        String tipoAzeitona = plantacoesInformacoes.get(i).get("azeitona");
                        int distanciaLagar = Integer.parseInt(plantacoesInformacoes.get(i).get("distancia"));
                        plantacoes.add(new Plantacao.Builder()
                                .nomePlantacao(tipoAzeitona + " " + (plantacao + 1))
                                .variedadePlantacao(tipoAzeitona)
                                .distanciaLagarSegundos(distanciaLagar)
                                .lagar(lagar)
                                .build());
                    });
        });
        this.inicioProducao = System.currentTimeMillis();
        criaPlantacoes();
    }

    public static boolean isTodasPlantacoesFinalizadas() {
        return todasPlantacoesFinalizadas;
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
    }

    public void criaPlantacoes() {

        // Threads de plantação.
        plantacoes.stream().forEach(plantacao -> {
            threads.add(new Thread(plantacao, "Plantação " + plantacao.getNomePlantacao()));
        });

        long tempoProducaoMinutos = ((System.currentTimeMillis() - inicioProducao) / 60000);

        // Inicialização das plantações.
        threads.stream().forEach(thread -> thread.start());

        new Thread(lagar, "Lagar").start();

        // Execução da produção.
        while (tempoProducaoMinutos < 2) {
            tempoProducaoMinutos = ((System.currentTimeMillis() - inicioProducao) / 60000);
            verificaFila();
        }

        // Notificação para encerrar a produção.
        plantacoes.stream().forEach(plantacao -> {
            plantacao.setProduzir(false);
            todasPlantacoesFinalizadas = true;
        });
    }

}
