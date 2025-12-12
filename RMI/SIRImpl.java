import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SIRImpl extends UnicastRemoteObject implements SIR {
    
    public SIRImpl() throws RemoteException {
        super();
    }

    @Override
    public String CalcularSIR(double S, double I, double R, double N, double beta, double gamma, int dias, double passo) throws RemoteException {

        String resultado = "Dia\tSusceptiveis\tInfectados\tRecuperados\n";
        
         for (int dia = 0; dia < dias; dia++) {
            double S_t = (-beta * S * I) / N;
            double I_t = (beta * S * I) / N - (gamma * I);
            double R_t = gamma * I;

            S += S_t * passo;
            I += I_t * passo;
            R += R_t * passo;

            resultado += String.format("%d\t%.2f\t%.2f\t\t%.2f\n", (dia + 1), S, I, R);

        }

        return resultado;
    }
}