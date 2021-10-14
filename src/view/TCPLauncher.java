package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import model.Message;

public class TCPLauncher extends Thread {

    //INICIO SINGLETON
    private static TCPLauncher onlyInstance;

    private TCPLauncher() {}
    
    public static TCPLauncher getInstance(){
        if (onlyInstance == null){
            onlyInstance = new TCPLauncher();
        }
        return onlyInstance;
    }
    //FIN SINGLETON
    
    private ServerSocket server;
    //falta observer
    //private Servidor observer;
    
    //M�todo de suscrpci�n
    /*public void setObserver (Servidor observer){
     	this.observer = observer;
    }*/
    
    @Override
    public void run(){

        try {
        	// Paso 1: Esperar una conexion
			server = new ServerSocket(6969);
			
			while (true) {
				// Paso 3: Cliente y Server conectados
				//Se vuelve repetitivo al estar dentro del while true
				//Deja conectar un cliente y espera otro..
				System.out.println("Esperando conexi�n....");
				Socket socketcito = server.accept();
				Session session = new Session(socketcito);
				//session.setObserver(observer);
				System.out.println("Cliente conectado!!!");
			}
			
			
		
			
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
  
}
