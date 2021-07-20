import java.net.*;
import java.io.*;

class client{
    
    
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    
    
    public client(){
        try{
            System.out.println("WELCOME TO THAT-CORD");
            System.out.println("sending request to server...");
            socket = new Socket("127.0.0.1",1243);
            System.out.println("connection established !");


            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            
            startReading();
            startWritting();
        }catch(Exception e){

        }
    }
    public void startReading(){
        Runnable r1=()->{
            System.out.println("reading.. ");
            while(true){
                try{
                String msg=br.readLine();
                if(msg.equals("!!cya!!")){
                    System.out.println("your friend said bye ");
                    break;
                }
                System.out.println("Server : "+msg); 
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        new Thread(r1).start();
    }
    public void startWritting(){
        
        Runnable r2=()->{
            System.out.println("Started writting.. ");
            while(true){
                try{
                    BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            
        };   
        new Thread(r2).start();
    }
    
    public static void main(String[] args){
        System.out.println("Connecting to the server ");
        new client();
    }
}