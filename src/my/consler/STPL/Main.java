package my.consler.STPL;

public class Main {
    public static void main(String[] args) {
        if(args.length==1){
            String input = args[0];
            runtime.run(files.getContent(input));
        } else {
            String input = files.getContent(files.getInput());
            runtime.run(input);
        }

    }
}
