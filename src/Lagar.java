import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class Lagar implements Callable<Boolean>{

    private static BlockingQueue<Caminhao> filaCaminhoes = new ArrayBlockingQueue<>(12);
    private boolean isCapacidadeMaxima = false;
    private boolean isRecepcaoProcessada = false;
    private double tempoEspera;

    public Lagar() {
    }

    public boolean getIsCapacidadeMaxima() {
        return isCapacidadeMaxima;
    }

    public boolean getIsRecepcaoProcessada() {
        return isRecepcaoProcessada;
    }

    public int getTamanhoFila() {
        return filaCaminhoes.size();
    }

    public void enfileraCaminhao(Caminhao caminhao) {
        if (filaCaminhoes.remainingCapacity() == 0) {
            isCapacidadeMaxima = true;
            return;
        }
        filaCaminhoes.add(caminhao);
    }

    public double processaRecepcao() {
        try {
            Caminhao primeiroCaminhaoDaFila = filaCaminhoes.take();
            tempoEspera = primeiroCaminhaoDaFila.getCapacidade() / 2; //pois 2 segundos corresponde a 4 toneladas e podemos ter de 4 até 16 toneladas
            isRecepcaoProcessada = true;
            isCapacidadeMaxima = false;
            return tempoEspera;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Boolean call() throws Exception {
        long tempoEsperaEmMilis = (long) processaRecepcao() * 1000;
        Thread.sleep(tempoEsperaEmMilis);
        return true;
    }
}


