package view;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class TCPLauncher extends Thread {	
	public TCPLauncher (IObserver app) {
		this.app = app;
		sesiones = new ArrayList<Session>();
	}
    
    private IObserver app;
    private ServerSocket server;
    private ArrayList<Session>sesiones;

    
    @Override
    public void run(){    	
        try {
         	// Paso 1: Esperar una conexion
			server = new ServerSocket(2021);
			
			while (sesiones.size()<2) {
				// Paso 3: Cliente y Server conectados
				//Se vuelve repetitivo al estar dentro del while true
				//Deja conectar un cliente y espera otro..
				System.out.println("Esperando conexión....");
				Socket socketcito = server.accept();
				Session session = new Session(socketcito, app, this.sesiones.size());
				sesiones.add(session);
				session.start();
				
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
