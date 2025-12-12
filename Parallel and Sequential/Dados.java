// Classe que armazena todos os parâmetros necessários para os modelos SIS e SIR
public class Dados {  
    
    // Quantidade inicial de indivíduos suscetíveis
    public double suceptivel = 9000000.0;

    // Quantidade inicial de indivíduos infectados
    public double infectados = 1000000.0;

    // Apenas usado no modelo SIR (no SIS sempre fica 0)
    public double recuperados = 0.0;

    // Total populacional (soma das três categorias)
    public double populacaoTotal = Math.max(suceptivel + infectados + recuperados, 0.0);

    // Taxa de transmissão (probabilidade de um infectado contaminar um suscetível)
    public double beta = 0.3;

    // Taxa de recuperação (probabilidade de um infectado se recuperar por dia)
    public double gamma = 0.1;

    // Dias de simulação
    public int dias = 365;

    // Passo temporal — quanto menor, mais precisa a simulação
    public double passo = 0.1;
}