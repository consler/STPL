package my.consler.STPL;

public class tape {
    public static int[] tape;
    public static int pointer = 0;

    public static void tape_load(int all) {
        tape = new int[all];
        for(int i=0; i<all; i++)
        {
            tape[i] = 0;
        }
    }

}
