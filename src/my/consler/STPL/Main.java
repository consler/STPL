package my.consler.STPL;

public class Main {
    public static void main(String[] args) {
        if(args.length==1){
            String input = args[0];
            runtime.run(files.getContent(input));
        } else {
            System.out.println("Provide your .stpl file:");
            String input = files.getContent(files.getInput());
            runtime.run(input);
        }

    }
}
