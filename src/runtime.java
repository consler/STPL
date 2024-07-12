import static java.lang.Character.compare;

public class runtime {

    public static void run() {
        // taking it from global for easier access
        String[] readable = global.readable;
        
        String command;


        for (int current_line_count = 0; current_line_count<readable.length;current_line_count++) {
            String current_line = readable[current_line_count];
            //if something unknown skip
            if(current_line.length() < 4){
                continue;
            }

            // current command assignment
            command = current_line.substring(0, 3);

            // making an arguments table for arguments  (duh)
            String[] temp_arguments = new String[0];
            if (current_line.length() > 4){
                temp_arguments = current_line.substring(4, current_line.length()-1).split(" ");
            }
            int arguments[] = new int[temp_arguments.length];

            // making every argument an integer
            try
            {
                int j=0;
                for(String i : temp_arguments) {
                    if(Character.compare(i.charAt(0), 'p') == 0){
                        try{
                            arguments[j] = tape.tape[Integer.parseInt(i.substring(1))];
                        } catch (Exception e) {
                            System.err.println("A cell seems to not exist at " + current_line_count);
                            break;
                        }
                    } else{
                        arguments[j] = Integer.parseInt(i);
                    }
                    j++;
                }

            } catch (Exception e){

                System.err.println("One of the arguments does not seem to be an integer at line " + current_line_count);
                break;
            }
            //no need for temp now
            temp_arguments = null;
            System.gc();

            // error handling (I just want to add some frustration to this world)
            if(!(current_line.endsWith(";")))
            {
                System.err.println("Semicolon missed at line " + current_line_count);
                break;
            }

            if(current_line_count == 0 && !(command.equals("all"))) {
                System.err.println("Allocation should be done at line 0");
                break;
            }

            //runtime process itself
            switch (command)
            {
                case "all" -> { // memory allocation
                    tape.tape_load(arguments[0]);
                }
                case "pri" -> { //print out the number
                    System.out.print(tape.tape[tape.pointer]);
                }
                case "set" -> { // set current cell to
                    tape.tape[tape.pointer] = arguments[0];
                }
                case "gto" -> {
                    try{
                        tape.pointer = arguments[0];
                    } catch (Exception e){
                        System.out.println("Cell " + arguments[0] + " does not exist. Not terminating");
                        continue;
                    }

                }
                case "gof" -> {
                    try{
                        tape.pointer++;
                    } catch (Exception e){
                        System.out.println("Cell " + (tape.pointer + 1) + " does not exist. Not terminating");
                        continue;
                    }

                }
                case "gob" -> {
                    try{
                        tape.pointer--;
                    } catch (Exception e){
                        System.out.println("Cell " + (tape.pointer - 1) + " does not exist. Not terminating");
                        continue;
                    }

                }
                case "pru" -> {
                    System.out.print((char) tape.tape[tape.pointer]);
                }
                case "add" -> {
                    tape.tape[tape.pointer] = arguments[0] + arguments[1];
                }
                case "sub" -> {
                    tape.tape[tape.pointer] = arguments[0] - arguments[1];
                }
                case "mul" -> {
                    tape.tape[tape.pointer] = arguments[0] * arguments[1];
                }
                case "div" -> {
                    tape.tape[tape.pointer] = arguments[0] / arguments[1];
                }
                case "and" -> {
                    tape.tape[tape.pointer] = arguments[0] & arguments[1];
                }
                case "orr" -> {
                    tape.tape[tape.pointer] = arguments[0] | arguments[1];
                }
                case "xor" -> {
                    tape.tape[tape.pointer] = arguments[0] ^ arguments[1];
                }
                case "com" -> {
                    tape.tape[tape.pointer] = ~(arguments[0]);
                }
                case "jum" -> {
                    current_line_count = arguments[0];
                }
                case "juf" -> {
                    current_line_count = current_line_count + arguments[0];
                }
                case "jub" -> {
                    current_line_count = current_line_count - arguments[0];
                }
                case "jui" -> {
                    if(!(tape.tape[tape.pointer] == arguments[0])){
                        current_line_count = arguments[1];
                    }
                }
                default -> {
                    System.err.println("An unknown command at line " + current_line_count + ". Not terminating");
                    continue;
                }
            }

            //garbage collection
            arguments = null;
            System.gc();
        }
    }
}
