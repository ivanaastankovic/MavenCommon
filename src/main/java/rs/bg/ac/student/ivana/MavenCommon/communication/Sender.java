package rs.bg.ac.student.ivana.MavenCommon.communication;

import java.io.BufferedOutputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Sender {
    private Socket socket;

    public Sender(Socket socket) {
        this.socket = socket;
    }
    
    public void send(Object object) throws Exception{
        try{
            ObjectOutputStream out=new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            out.writeObject(object);
            out.flush();
        }catch(Exception e){

            throw new Exception("Error sending object!\n"+e.getMessage());
        }
    }
}
