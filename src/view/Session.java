package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Session extends Thread {

    private Socket socketcito;
    private BufferedWriter escritorcito;
    private BufferedReader lectorcito; //este talvez no es necesario
    //Falta observer
    
    public Session (Socket socketcito) {
    	this.socketcito = socketcito;
    }
    
    @Override
    public void run() {
    	try {
    		//Declaraciones
            InputStream is = socketcito.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            lectorcito = new BufferedReader(isr);

            OutputStream os = socketcito.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            escritorcito = new BufferedWriter(osw);

            while(true) {
                System.out.println("Esperando mensaje....");
                String line = lectorcito.readLine();
                System.out.println("Recibido: " + line);
                //Falta observer
                //observer.cuandoLlegueMensaje("J1", lastMessage);
          
            }
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
    
    public void sendMessage(String msg) {
        new Thread(
                ()->{
                    try {
                        escritorcito.write(msg+"\n");
                        escritorcito.flush();

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }).start();
    }
    
  //Método de suscrpción
    /*public void setObserver (Servidor observer){
     	this.observer = observer;
    }*/
}
