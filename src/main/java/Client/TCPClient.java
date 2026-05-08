package Client;

import java.net.*;
import java.io.*;
public class TCPClient {
	public static void main (String args[]) {
		Socket s = null;
                Comando cadena = new Comando("comando", "origen", "destino", "datos");
		try{
			int serverPort = 7896;
			s = new Socket("localhost", serverPort);    
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out =new DataOutputStream( s.getOutputStream());
			out.writeUTF(cadena.toString());      	
			String data = in.readUTF();	
			System.out.println("Received: "+ data) ; 
		}catch (UnknownHostException e){System.out.println("Socket:"+e.getMessage());
		}catch (EOFException e){System.out.println("EOF:"+e.getMessage());
		}catch (IOException e){System.out.println("readline:"+e.getMessage());
		}finally {if(s!=null) try {s.close();}catch (IOException e){System.out.println("close:"+e.getMessage());}}
     }
}