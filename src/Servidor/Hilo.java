package Servidor;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

//Clase hilo dónde el servidor atiende al cliente

public class Hilo extends Thread {
	
	//Socket por el que se está atendiendo al cliente
	private Socket sCliente;
	

	//Constructor de la clase
	public Hilo(Socket socket)
	{
		sCliente=socket;		
	}
	
	//Método run de la clase, dónde se atenderá al cliente
	public void run()
	{
		String fichero="";
		String linea="";	
				
		try 
		 {
			
			while (true) {
		
		InputStream auxLeer = sCliente.getInputStream();
		DataInputStream flujoEntrada = new DataInputStream(auxLeer);
		
		OutputStream auxEscribir = sCliente.getOutputStream();
		DataOutputStream flujoSalida= new DataOutputStream(auxEscribir);
		
		fichero=flujoEntrada.readUTF();
		System.out.println("El cliente quiere el fichero: "+fichero);
		

//		FileOutputStream dstream = new FileOutputStream("tres.txt");
//		DataOutputStream out = new DataOutputStream(dstream);
//		BufferedWriter dr = new BufferedWriter(new OutputStreamWriter(out));
//		dr.write("cadena vacia1 \n");
//		dr.write("cadena vacia2 \n");
//		dr.write("cadena vacia3 \n");
//		dr.flush();
//		dstream.close();
//		dr.close();
//		out.close();
		
		FileInputStream fstream = new FileInputStream(fichero);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		
		linea=br.readLine();
//		linea=in.readUTF();
		while (linea != null)
		{
			flujoSalida.writeUTF(linea);
			linea=br.readLine();
		}
		br.close();
		
		flujoSalida.writeUTF("EOF");
			
		sCliente.close();
		System.out.println("Conexion finalizada!");
			}
	}
		catch(Exception e)
		{
			System.out.println("Error al comunicarse con el cliente");
			
			//Cerramos el socket
			try {
				sCliente.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

}


