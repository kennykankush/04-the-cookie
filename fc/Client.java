package fc;
import java.net.*;
import java.io.*;
import java.util.*;
import java.net.*;

public class Client {

    public static final String ARG_MSG = "<port>";
    static String hostname = "user";

    public static void main(String[] args) throws NumberFormatException, UnknownHostException, IOException{

        if (args.length > 2 || args.length == 0){
            System.out.println(ARG_MSG);
            return;

        }

        try
        {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            hostname = addr.getHostName();
        }
        catch (UnknownHostException ex)
        {
            System.out.println("Hostname can not be resolved");
        }

        String PORT = args[0];

        Socket socket = new Socket("localhost", Integer.parseInt(PORT));

        System.out.println("Hello " + hostname + ". Type 'get cookie' to get a random cookie");

        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        Scanner scanner = new Scanner(System.in);
        String messageIn = "";
        String input = "";

        while (!input.equals("quit")){
            
            input = scanner.nextLine();

            dos.writeUTF(input);
            dos.flush();

            if (input.equalsIgnoreCase("Get Cookie")){
                messageIn = dis.readUTF();
                System.out.println("You got a " + messageIn + ".");
            }
        }

        scanner.close();
        dos.close();
        bos.close();
        os.close();
        dis.close();
        bis.close();
        is.close();
        socket.close();

    }
    
}
