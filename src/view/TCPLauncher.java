package view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class TCPLauncher extends Thread {	
	
	//INICIO SINGLETON
    private static TCPLauncher onlyInstance;

    public static TCPLauncher getInstance(){
        if (onlyInstance == null){
            onlyInstance = new TCPLauncher();
        }
        return onlyInstance;
    }
    
    private TCPLauncher() {}
    //FIN SINGLETON
    
    
    //SUSCRPCIÓN
	public void setCuyMain (CuyMain observer) {
		this.observer = observer;
	}
    
    private CuyMain observer;
    private ServerSocket server;
    private ArrayList<Session>sesiones;

    
    @Override
    public void run(){    	
        try {
        	
        	sesiones = new ArrayList<Session>();
         	// Paso 1: Esperar una conexion
			server = new ServerSocket(6969);
			
			while (sesiones.size()<2) {
				// Paso 3: Cliente y Server conectados
				//Se vuelve repetitivo al estar dentro del while true
				//Deja conectar un cliente y espera otro..
				System.out.println("Esperando conexión....");
				Socket socketcito = server.accept();
				Session session = new Session(socketcito, this.sesiones.size());
				System.out.println("Sesión creada");
				
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

	public void setSesiones(ArrayList<Session> sesiones) {
		this.sesiones = sesiones;
	}
  
	
}
