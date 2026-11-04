import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws Exception {
        int porta = 12345;

        try (ServerSocket socket = new ServerSocket(porta)) {
            System.out.println("=== SERVIDOR INICIADO NA PORTA " + porta + " ===");
            while (true) {
                System.out.println("Aguardando um cliente se conectar...");
                Socket socketClient = socket.accept();
                Thread threadCliente = new Thread(() -> lidarCliente(socketClient));
                threadCliente.start();
            }

        } catch (Exception e) {
            throw new Error("Cuida");
        }
    }

    private static void lidarCliente(Socket socket) {
        byte[] buffer = new byte[1024];
        try (Socket s = socket) {
            InputStream entrada = s.getInputStream();
            OutputStream saida = s.getOutputStream();

            saida.write("Conectado ao servidor multithread!\n".getBytes("UTF-8"));
            saida.flush();

            while (true) {
                int bytesLidos = entrada.read(buffer);
                if (bytesLidos == -1) {
                    break;
                }
                String mensagem = new String(buffer, 0, bytesLidos, "UTF-8").trim();
                String resposta = "Recebido: " + mensagem + "\n";
                saida.write(resposta.getBytes("UTF-8"));
                saida.flush();
            }
        } catch (Exception e) {
            System.out.println("Erro na conexão do cliente: " + e.getMessage());
        } finally {
            System.out.println("[Servidor] Conexão finalizada para um cliente.");
        }
    }
}
