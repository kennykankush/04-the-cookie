package fc;

import java.util.*;
import java.io.*;


public class Cookie {

    List<String> cookieList = new ArrayList<>();

    public void fileReader(String fileName) throws IOException{

        String path = ".\\data\\";

        if (!fileName.contains(".")){
            System.out.println("No extension detected");
        }

        File file = new File(path + fileName);

        if (file.exists()){
            System.out.println("We found the file @" + file.getCanonicalPath());
        } else {
            System.out.println("Unable to load file @ " + file.getCanonicalPath());
        }

        String line = "";

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        while((line = br.readLine()) != null){
            cookieList.add(line);
        }

        br.close();
        fr.close();
    }
    
    public String getARandomCookie(){
        Random rand = new Random();

        if (cookieList != null){
            if (cookieList.size() > 0){
                return cookieList.get(rand.nextInt(cookieList.size()));
            } else {
                return "No cookie found";
            }

        } 
        
        return "No cookie found";
    } 



}
