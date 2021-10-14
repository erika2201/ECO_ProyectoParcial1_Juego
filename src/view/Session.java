package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.UUID;

import model.Message;

public class Session extends Thread {

	
	private Message message;
	private String id;
    private Socket socketcito;
    private BufferedWriter escritorcito;
    private BufferedReader lectorcito; //este talvez no es necesario
    private CuyMain observer;
    
    
    //METODO DE SUSCRIPCIÓN
    public Session (Socket socketcito) {
    	this.socketcito = socketcito;
    	this.id = UUID.randomUUID().toString();
    	message = new Message();
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
                observer.onMessage(this, line);
          
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
    public void setObserver (CuyMain observer){
     	this.observer = observer;
    }
    
    
    public String getID () {
    	return this.id;
    }

	public Message getMessage() {
		return this.message;
	}

	public void setMessage(Message direccion) {
		this.message = message;
		
	}


}
