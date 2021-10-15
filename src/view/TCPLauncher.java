package view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


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
    private CuyMain observer;
    private ArrayList<Session>sesiones;
    
    //Método de suscrpción
    public void setCuyMain (CuyMain observer){
     	this.observer = observer;
    }
    
    @Override
    public void run(){    	
        try {
        	sesiones = new ArrayList<Session>();
    
        	// Paso 1: Esperar una conexion
			server = new ServerSocket(6969);
			
			while (true) {
				// Paso 3: Cliente y Server conectados
				//Se vuelve repetitivo al estar dentro del while true
				//Deja conectar un cliente y espera otro..
				System.out.println("Esperando conexión....");
				Socket socketcito = server.accept();
				Session session = new Session(socketcito);
				session.setObserver(observer);
				session.start();
				
				sesiones.add(session);
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

	public ArrayList<Session> getSesiones() {
		return this.sesiones;
	}
  
}
