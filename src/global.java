public class global {
    public static String input = """
all 6;
gto 1;
set 32;
gto 0;
set 10;
sub p0 1;
pri;
gto 1;
pru;
gto 0;
jui 0 4;
""";

    public static String[] readable = input.split("\n");
}
