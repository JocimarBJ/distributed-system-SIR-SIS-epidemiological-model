public class SIS_Sequencial {

    public static void main(String[] args) {

        // Inicia cronômetro em nanossegundos
        long inicio = System.nanoTime();

        // Instância com os valores iniciais do modelo
        Dados dSIS = new Dados();

        // Loop principal — simula dia a dia
        for(int dia = 0; dia < dSIS.dias; dia++){

            // Calcula probabilidade de infecção:
            // chance cresce com beta, com infectados e com o passo da simulação
            double chanceInfecacao = (dSIS.beta * dSIS.infectados) / dSIS.populacaoTotal * dSIS.passo;

            // Probabilidade de um infectado se recuperar
            double chanceRecuperacao = dSIS.gamma * dSIS.passo;

            // CONTAGEM DE NOVAS INFECÇÕES
            // Para cada suscetível, sorteia se ele será infectado
            int novos = 0;
            for(int S = 0; S < dSIS.suceptivel; S++){
                if(Math.random() < chanceInfecacao){
                    novos++;
                }
            }

            // CONTAGEM DE RECUPERADOS
            // Cada infectado pode se recuperar com chance "chanceRecuperacao"
            int recuperadosHoje = 0;
            for(int I = 0; I < dSIS.infectados; I++){
                if(Math.random() < chanceRecuperacao){
                    recuperadosHoje++;
                }
            }

            // Atualiza o número de infectados e suscetíveis
            // Modelo SIS: recuperados voltam a ser suscetíveis
            dSIS.infectados += novos - recuperadosHoje;
            dSIS.suceptivel += recuperadosHoje - novos;

            // Impressão do dia atual
            System.out.printf("%d\t%.2f\t%.2f\n", dia, dSIS.suceptivel, dSIS.infectados);
        }

        // Finaliza o cronômetro
        long fim = System.nanoTime();
        double tempoMs = (fim - inicio) / 1_000_000.0;

        // Tempo total em ms
        System.out.printf("Tempo de execução: %.3f ms%n", tempoMs);
    }
}