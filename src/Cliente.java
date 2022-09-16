import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Cliente {
	
	public static void main(String[] args) {
		// 1 - Estabelecer uma conex�o
		// 2 - Trocar mensagens com o servidor
		String host = "localhost";
		int porta = 5555;
		try {
			// cria a conex�o entre o cliente o servidor
			Socket s = new Socket(host, porta);
			
			// cria��o dos strems de entrada e sa�da
			ObjectOutputStream output = new ObjectOutputStream(s.getOutputStream());
			ObjectInputStream input = new ObjectInputStream(s.getInputStream());
						
			String msg = "Ol�";
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
