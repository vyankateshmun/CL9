import java.io.*;
import java.net.*;

class server{
    public static void main(String s[]) throws Exception{
        Socket sa = null;
        ServerSocket ss2 = null;
        System.out.println("server listening ");
        try {
            ss2 = new ServerSocket(5555);
        } catch (IOException e) {
            System.out.println("server error");
        }
        int i =1;
        while (true) {
            try {
                sa = ss2.accept();
                System.out.println("connetion established");
                
                ServerThread st = new ServerThread(sa,"Client "+String.valueOf(i));
                i++;
                st.start();
            } catch (Exception e) {
                System.out.println("connetion error");
            }
        }

    }
}

class ServerThread extends Thread{
    private static DataInputStream din=null,cin=null;
    private static DataOutputStream dout=null; 
    private static int num=0;
    Socket s1 = null;
    String clientnum = "";
    public ServerThread(Socket s, String clientnum) {
        s1 = s;
        this.clientnum= clientnum; 
    }
    public void run() {
        try{
            System.out.println("Client Connected");
            cin=new DataInputStream(System.in);
            din=new DataInputStream(new BufferedInputStream(s1.getInputStream()));
            dout=new DataOutputStream(s1.getOutputStream());
            num=din.readInt();
            System.out.println(num);
            int finans=num*1000;
            dout.writeInt(finans);
            s1.close();
            din.close();
            cin.close();
            dout.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}