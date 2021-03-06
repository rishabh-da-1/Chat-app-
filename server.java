import java.net.*;
import java.io.*;
import java.util.Scanner;

class server{
    //variables required -
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;
    
    
    public server(){
        int port;
        Scanner mysc = new Scanner(System.in);
        System.out.println("enter port : ");
        port = mysc.nextInt();
        
        try{
            server = new ServerSocket(port);
            System.out.println("WELCOME TO THATCORD ! ");
            System.out.println("chat server established .." );
            System.out.println("now waiting for client request ...");
            socket = server.accept();;
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            
            startReading();
            startWritting();
        }catch(Exception e ){
            e.printStackTrace();
            
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
                System.out.println("Client : "+msg); 
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
            System.out.println("Starting server....");
            new server();
        }
}   