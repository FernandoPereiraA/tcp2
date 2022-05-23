
package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

//---------------------------------------------------------------
//Clase servidor que atiende a un Cliente para enviarle un archivo solicitado
//Recibe el nombre del archivo
//Env�a el archivo solicitado al cliente
//Atiende a un unico cliente
//Debe crearse inicialmente un archivo txt en el paquete del proyecto
//Conexi�n TCP
//---------------------------------------------------------------


public class Servidor {

	//Puerto por el que el servidor escuchar� al cliente
	static final int PORT=1600;
	
	//M�todo principal del servidor (lanzara a su propio constructor)
	public static void main(String[] args) throws IOException {
		new Servidor();
	}
	
	//Constructor del servidor
	public Servidor() throws IOException
	{
		

		//Creamos el socket por el que el servidor se mantendr� a la escucha de clientes
		ServerSocket skServidor = new ServerSocket(PORT);
		
		//Escribe por pantalla el puerto al que va a escuchar
		System.out.println("Puerto de escucha del servidor: "+PORT);
				
		
		//El servidor escuchar� aqu� a un potencial cliente
		Socket sCliente = skServidor.accept();
		
			//Cuando termine la escucha, lanzar� un hilo para atenderlo
			new Hilo(sCliente).start();
		}
	}


