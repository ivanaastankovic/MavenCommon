package rs.bg.ac.student.ivana.MavenCommon.communication;

import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.net.SocketException;

public class Receiver{
    private Socket socket;

    public Receiver(Socket socket) {
        this.socket = socket;
    }
    
    public Object receive() throws Exception{
        try{
            ObjectInputStream in=new ObjectInputStream(socket.getInputStream());
            return in.readObject();
        }catch(EOFException ex){
           throw new Exception("client has left!\n");
        }catch(SocketException exp){
            throw new Exception("server is out!\n");
        }
            
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("Error receiving object!\n");
        }
    }
}
