import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
	
	public static void main(String[] args) {
		// 1 - Estabelecer uma conexão
		// 2 - Trocar mensagens com o servidor
		String host = "localhost";
		int porta = 5555;
		try {
			// cria a conexão entre o cliente o servidor
			Socket s = new Socket(host, porta);
			
			// criação dos strems de entrada e saída
			ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(s.getInputStream());
						
			String msg = "Olá";
			output.writeUTF(msg);
			output.flush();
			
			msg = input.readUTF();
			System.out.println("Resposta: " + msg);
			input.close();
			output.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
