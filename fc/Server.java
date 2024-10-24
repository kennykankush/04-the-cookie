package fc;
import java.net.*;
import java.io.*;

public class Server {

    public static final String ARG_MSG = "<port> <filename>";

    public static void main(String[] args) throws IOException{

        if (args.length < 2 || args.length > 2){
            System.out.println(ARG_MSG);
            return;
        }

        String PORT = args[0];
        String FILENAME = args[1];

        System.out.println("PORT: " + PORT + ", FILENAME: " + FILENAME);

        Cookie cookie = new Cookie();
        cookie.fileReader(FILENAME);

        ServerSocket server = new ServerSocket(Integer.parseInt(PORT));
        Socket socket = server.accept();
        System.out.println("A client has connected to " + PORT +".");

        InputStream is = socket.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);
        DataInputStream dis = new DataInputStream(bis);
        String incomingMsg = "";

        OutputStream os = socket.getOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(os);
        DataOutputStream dos = new DataOutputStream(bos);

        while (!incomingMsg.equals("quit")){
            incomingMsg = dis.readUTF();

            if (incomingMsg.equalsIgnoreCase("get cookie")){
                String messageOut = cookie.getARandomCookie();
                dos.writeUTF(messageOut);
                dos.flush();
            } else {
                String messageOut = "I can only'get cookie'";
                dos.writeUTF(messageOut);
                dos.flush();

            }

        }

        dos.close();
        bos.close();
        os.close();

        dis.close();
        bis.close();
        is.close();

        server.close();












    }
    
}
