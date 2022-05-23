
package Cliente;

import java.io.*;
import java.net.*;
import java.util.Random;

//---------------------------------------------------------------
//El proceso Cliente solicita un archivo al Servidor
//Muestra el contenido del archivo por pantalla
//Guarda el archivo en la carpeta del proyecto
//El fichero guardado tendrá el nombre salida.txt
//Conexión TCP
//---------------------------------------------------------------


class Cliente {
	static final String HOST = "localhost";
	static final int Puerto = 1600;
	boolean acertado = false;

	public Cliente() {
		try {
			int numCliente = 0;
			String fichero = "";
			String linea = "";
			String respuestaServer = "";

			Socket sCliente = new Socket(HOST, Puerto);
			// flujo entrada
			InputStream auxLeer = sCliente.getInputStream();
			DataInputStream flujoEntrada = new DataInputStream(auxLeer);
			// Flujo salida
			OutputStream auxEscribir = sCliente.getOutputStream();
			DataOutputStream flujoSalida = new DataOutputStream(auxEscribir);
			//Se pide al usuario nombre del fichero a leer y se envía al servidor
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Escriba el nombre del fichero: ");
			fichero = reader.readLine();

			flujoSalida.writeUTF(fichero);

			// conexión archivo para escritura
			FileOutputStream fstream = new FileOutputStream("salida.txt");
			DataOutputStream in = new DataOutputStream(fstream);
			//BufferedWriter br = new BufferedWriter(new OutputStreamWriter(in));

			do {
				// recibir
				linea = flujoEntrada.readUTF();
				// escribir en fichero
				System.out.print(linea);
				//br.write(linea+"\n");
				in.writeUTF(linea+"\n");

			} while (!linea.equals("EOF"));
			//br.close();
			in.close();
			sCliente.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] arg) {
		new Cliente();
	}
}