import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Cliente {
    
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);

        double suceptivel, infectados, recuperados = 0.0;
        double populacaoTotal;
        double beta, gamma;
        int dias;
        double passo;
        String resultado = "";

        System.out.println("Bem vindo ao Cliente RMI para simulação SIR e SIS.");

        System.out.println("Opções de Serviços: ");
        System.out.println("1 - Modelo SIR");
        System.out.println("2 - Modelo SIS");
        System.out.print("Escolha o modelo (1 ou 2): ");
        int escolha = entrada.nextInt();

        System.out.print("Digite o número de suscetíveis iniciais: ");
        suceptivel = entrada.nextDouble();

        System.out.print("Digite o número de infectados iniciais: ");
        infectados = entrada.nextDouble();

        if (escolha == 1) {
            System.out.print("Digite o número de recuperados iniciais: ");
            recuperados = entrada.nextDouble();
        }

        populacaoTotal = suceptivel + infectados + recuperados;

        System.out.print("Digite a taxa de transmissão (beta): ");
        beta = entrada.nextDouble();

        System.out.print("Digite a taxa de recuperação (gamma): ");
        gamma = entrada.nextDouble();

        System.out.print("Digite o número de dias para simulação: ");
        dias = entrada.nextInt();

        System.out.print("Digite o passo de tempo (ex: 0.1): ");
        passo = entrada.nextDouble();

        entrada.close();

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 5000);
            SIR sir = (SIR) registry.lookup("SIR");
            SIS sis = (SIS) registry.lookup("SIS");

            long inicio = System.currentTimeMillis();

            if (escolha == 1) {
                resultado = sir.CalcularSIR(suceptivel, infectados, recuperados, populacaoTotal, beta, gamma, dias, passo);

            } else if (escolha == 2) {
                resultado = sis.CalcularSIS(suceptivel, infectados, populacaoTotal, beta, gamma, dias, passo);

            } else {
                System.out.println("Opção inválida. Encerrando o cliente.");
                return;
            }

            long fim = System.currentTimeMillis();

            System.out.println("Resultados da simulação:");
            System.out.println(resultado);
            System.out.println("\nTempo de execução via RMI: " + (fim - inicio) + " ms");

        } catch (Exception e) {
            System.out.println("Erro no cliente: " + e.getMessage());
        }
    }
}
