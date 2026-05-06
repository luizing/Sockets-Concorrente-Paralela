import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("172.19.7.97", 12346);
            System.out.println("Conectado ao servidor");

            // Streams de envio/recebimento
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());


            // Exemplo de matrizes
            int[][] A = {
                    {2, 2},
                    {3, 4}
            };

            int[][] B = {
                    {5, 6},
                    {7, 8}
            };

            String mensagem = "oi";

            // Enviando as matrizes
            out.writeObject(A);
            out.writeObject(B);
            out.writeObject(mensagem);
            out.flush();

            // Recebendo resultado
            int[][] resultado = (int[][]) in.readObject();

            // Exibindo resultado
            System.out.println("Resultado:");
            for (int i = 0; i < resultado.length; i++) {
                for (int j = 0; j < resultado[i].length; j++) {
                    System.out.print(resultado[i][j] + " ");
                }
                System.out.println();
            }

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
