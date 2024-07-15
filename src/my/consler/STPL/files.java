package my.consler.STPL;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static java.lang.System.*;


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
            err.println("Please provide a valid file");
            exit(0);
        }
        return result;
    }
    public static String getInput(){
        Scanner scanner2 = new Scanner(in);
        String input = scanner2.nextLine();
        if(!(input.endsWith(".stpl"))){
            err.println("Please provide a .stpl file");
            exit(0);
        }
        return input;
    }
}
