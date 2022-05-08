import java.io.*;
import java.net.*;

class client{
    private static Socket sock=null;
    private static DataInputStream din=null,cin=null;
    private static DataOutputStream dout=null; 
    private static int num=0;
    public static void main(String s[]) throws Exception{
        try{
            sock=new Socket("localhost",5555);
            System.out.println("Connected");
            cin=new DataInputStream(System.in);
            din=new DataInputStream(new BufferedInputStream(sock.getInputStream()));
            dout=new DataOutputStream(sock.getOutputStream());
            num=Integer.parseInt(cin.readLine());
            dout.writeInt(num);
            num=din.readInt();
            System.out.println(num);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            sock.close();
            din.close();
            cin.close();
            dout.close();
        }

    }
}