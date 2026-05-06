import java.io.*;
import java.net.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int porta = 12346;
        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("=== LOG: Balanceador iniciado na porta " + porta + " ===");
            System.out.println("=== LOG: Aguardando conexão de um trabalhador... ===");

            while (true) {
                // Bloqueia aqui até o parceiro rodar o código dele
                Socket clientSocket = serverSocket.accept();
                System.out.println("\n[CONEXÃO] Trabalhador conectado: " + clientSocket.getInetAddress());

                try (ObjectOutputStream saida = new ObjectOutputStream(clientSocket.getOutputStream());
                     ObjectInputStream entrada = new ObjectInputStream(clientSocket.getInputStream())) {

                    // 1. Recebimento
                    System.out.println("[FLUXO] Aguardando envio das matrizes pelo trabalhador...");
                    int[][] matrizA = (int[][]) entrada.readObject();
                    int[][] matrizB = (int[][]) entrada.readObject();
                    String mensagem = (String) entrada.readObject();
                    System.out.println("[DADOS] Matrizes recebidas com sucesso.");
                    System.out.println("[DADOS] Matriz A: " + Arrays.deepToString(matrizA));
                    System.out.println("[DADOS] Matriz B: " + Arrays.deepToString(matrizB));
                    System.out.println("[DADOS] Mensagem: " + mensagem);

                    // 2. Processamento
                    System.out.println("[PROCESSAMENTO] Iniciando multiplicação de matrizes...");
                    int linhasA = matrizA.length;
                    int colunasA = matrizA[0].length;
                    int colunasB = matrizB[0].length;
                    int[][] resultado = new int[linhasA][colunasB];

                    for (int i = 0; i < linhasA; i++) {
                        for (int j = 0; j < colunasB; j++) {
                            for (int k = 0; k < colunasA; k++) {
                                resultado[i][j] += matrizA[i][k] * matrizB[k][j];
                            }
                        }
                    }
                    System.out.println("[PROCESSAMENTO] Cálculo finalizado.");

                    // 3. Envio de volta
                    System.out.println("[FLUXO] Enviando resultado para o IP: " + clientSocket.getInetAddress());
                    saida.writeObject(resultado);
                    saida.flush();
                    System.out.println("[SUCESSO] Dados enviados. Encerrando conexão temporária.");

                } catch (Exception e) {
                    System.err.println("[ERRO] Falha durante a interação: " + e.getMessage());
                }

                System.out.println("\n=== LOG: Voltando a aguardar novas conexões... ===");
            }
        } catch (IOException e) {
            System.err.println("[ERRO CRÍTICO] Não foi possível abrir a porta: " + e.getMessage());
        }
    }
}
