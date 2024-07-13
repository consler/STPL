package my.consler.STPL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class files {
    public static String getContent(String path){
        String result = "";
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                result = result + scanner.nextLine() + "\n";
            }
        } catch (FileNotFoundException e) {
            System.err.println("Not a valid file");
            System.exit(0);
        }
        return result;
    }
    public static String getInput(){
        Scanner scanner2 = new Scanner(System.in);
        return scanner2.nextLine();
    }
}
