import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	
	// 1 - Criar o servidor de conexões ServerSocket
	// 2 - Recepcionar e aceitar os pedidos de conexão
		// 2.1 - retonar uma conexão
	// 3 - Criar os streams de entrada e saída ObjectOutputStream e ObjectInputStream
	// 4 - Tratar a conversação de clientes e servidor
		// 4.1 - Fechar o socket
		// 4.2 - Fechar os strems de entrada e saida
	// 5 - fechar o socket
	
	private ServerSocket serverSocket;
	
	// 1
	private void criarServerSocket(int porta) throws IOException {
		serverSocket = new ServerSocket(porta);
	}
	
	// 2
	private Socket esperaConexao() throws IOException {
		Socket s = serverSocket.accept();
		return s;
	}
	
	// 3 e 4
	private void trataConexao(Socket socket) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
			
			// Definição do protocolo.
			// Cliente -> HELLO
				// Server --- HELLO WORLD
			String msg = input.readUTF();
			System.out.println("Mensagem recebida..." + msg);
			output.writeUTF(msg.toUpperCase());
			
			output.flush();
			input.close();
			output.close();
			
		} catch (IOException e) {
			System.out.println("Problema no tratamento da conexão com o cliente: " + socket.getInetAddress());
			e.printStackTrace();
		} finally {
			try {
				fecharSocket(socket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//5
	public void fecharSocket(Socket socket) throws IOException {
		socket.close();
	}
	
	public static void main(String[] args) {
		try {
			Servidor servidor = new Servidor();
			System.out.println("Aguardando conexão ...");
			servidor.criarServerSocket(5555);
			while (true) {
				Socket socket = servidor.esperaConexao();
				System.out.println("Cliente conectado ...");
				servidor.trataConexao(socket);
				System.out.println("Cliente finalizado");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
