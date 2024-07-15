package my.consler.STPL;

public class tape {
    public static int[][] tape;
    public static int pointer = 0;
    public static int tape_pointer = 0;

    public static void tape_load(int all, int tap) {
        tape = new int[all][tap];
        for(int i=0; i<all; i++)
        {
            for(int l=0; l<tap; l++)
            {
                tape[i][l] = 0;
            }

        }
    }

}
