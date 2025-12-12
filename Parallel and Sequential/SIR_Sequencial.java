public class SIR_Sequencial {

    public static void main(String[] args) {

        // Inicia cronômetro da simulação
        long inicio = System.nanoTime();

        // Classe com valores iniciais
        Dados dSIR = new Dados();

        // Loop principal da simulação
        for (int dia = 0; dia < dSIR.dias; dia++) {
            // Fórmula clássica do modelo SIR (equações diferenciais)
            // Variação dos suscetíveis (reduz conforme infectados contaminam)
            double novosSuceptivel = 
             (-dSIR.beta * dSIR.suceptivel * dSIR.infectados)/ dSIR.populacaoTotal;
            // Variação dos infectados
            double novosInfectados =
             (dSIR.beta * dSIR.suceptivel * dSIR.infectados) / dSIR.populacaoTotal
             - (dSIR.gamma * dSIR.infectados);

            // Variação dos recuperados (imunes)
            double novosRecuperados = dSIR.gamma * dSIR.infectados;

            // Aplicação do passo (método de Euler)
            dSIR.suceptivel += novosSuceptivel * dSIR.passo;
            dSIR.infectados += novosInfectados * dSIR.passo;
            dSIR.recuperados += novosRecuperados * dSIR.passo;

            // Impressão diária
            System.out.printf("%d\t%.2f\t%.2f\t%.2f\n",
                dia, dSIR.suceptivel, dSIR.infectados, dSIR.recuperados);
        }

        // Finaliza cronômetro
        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;

        System.out.printf("Tempo de execução: %.3f ms%n", tempoMs);
    }
}
