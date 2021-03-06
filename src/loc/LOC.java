package loc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LOC {

    public static void main(String[] args) {
        
        File folder = new File("./new");
        File[] files = folder.listFiles();
        String result="";
        int totalLinesProject=0;
        
        for(File file:files){
                try {
                    int totalLines = 0;
                    int enter = 0;
                    int comment = 0;
                    int close = 0;
                    int loc = 0;
                    Scanner sc = new Scanner(file);
                    while(sc.hasNext()){
                        totalLines++;
                        switch(checkLine(sc.nextLine())){
                            case "n":
                                enter++;
                                break;
                            case "comment":
                                comment++;
                                break;
                            case "":
                                close++;
                                break;
                            case "loc":
                                loc++;
                                break;
                        }   
                    }
                    totalLinesProject+=loc;
                    result+="\nFile: "+file.getName()+"\nTotal lines= "+totalLines+" | enter= "+enter+" | comments="+comment+" | }= "+close+" | LOC= "+loc;

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(LOC.class.getName()).log(Level.SEVERE, null, ex);
                }
            
        }
        File output = new File("output.txt");
        try {
            output.createNewFile();
            FileWriter fw = new FileWriter (output);
            fw.write(result+"\nsum LOC= "+totalLinesProject);
            fw.close();
            
        } catch (IOException ex) {
            Logger.getLogger(LOC.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
        
    }
    public static String checkLine(String line){
        
        if (line.matches("^[\\s/\\t]*}[^a-z]*$")){
            System.out.println("} "+line);
            return "";
        }
        if (line.matches("^[\\s]*[//*].*$")){
            System.out.println("comment "+line);
            return "comment";
        }
        if (!line.matches("^.*[a-z]+.*$")){
            System.out.println("n "+line);
            return "n";
        }
        
        System.out.println("loc "+line);
        return "loc";
    }
       
    
}
