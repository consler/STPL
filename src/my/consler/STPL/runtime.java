package my.consler.STPL;

public class runtime {

    public static void run(String input) {
        // taking it from global for easier access
        String[] readable = input.split("\n");
        
        String command;


        for (int current_line_count = 0; current_line_count<readable.length;current_line_count++) {
            int semi_colon = readable[current_line_count].indexOf(';');
            String current_line = readable[current_line_count].substring(0, semi_colon + 1);
            //if something unknown skip
            if(current_line.length() == 1){
                continue;
            } else if(current_line.length()<4) {
                System.err.println("Not a valid string at line " + current_line_count + ". Skipping");
                continue;
            }
            //if longer then 3 skip
            if (!(current_line.charAt(3) == ' ' || current_line.charAt(3) == ';')) {
                System.err.println("Command at line " + current_line_count + " seems to not be equal to 3 characters long. Skipping");
                continue;
            }



            // current command assignment

            command = current_line.substring(0, 3);

            // error handling (I just want to add some frustration to this world)
            if(!(current_line.endsWith(";")))
            {
                System.err.println("Semicolon missed at line " + current_line_count + ". Skipping");
                continue;
            }

            if(current_line_count == 0 && !(command.equals("all"))) {
                System.err.println("Allocation should be done at line 0. Terminating");
                break;
            }


            // making an arguments table for arguments  (duh)
            String[] temp_arguments = new String[0];
            if (current_line.length() > 4){
                temp_arguments = current_line.substring(4, current_line.length()-1).split(" ");
            }
            int[] arguments = new int[temp_arguments.length];

            // making every argument an integer
            try
            {
                int j=0;
                for(String i : temp_arguments) {
                    if(i.charAt(0) == 'p'){
                        try{
                            arguments[j] = my.consler.STPL.tape.tape[Integer.parseInt(i.substring(1))];
                        } catch (Exception e) {
                            System.err.println("A cell seems to not exist at " + current_line_count + ". Skipping");
                            continue;
                        }
                    } else{
                        arguments[j] = Integer.parseInt(i);
                    }
                    j++;
                }

            } catch (Exception e){

                System.err.println("One of the arguments does not seem to be an integer at line " + current_line_count + ". Skipping");
                continue;
            }
            //no need for temp now
            temp_arguments = null;
            System.gc();



            //runtime process itself
            try{
                switch (command)
                {
                    case "all" -> { // memory allocation
                        if(arguments.length != 1) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        my.consler.STPL.tape.tape_load(arguments[0]);
                    }
                    case "pri" -> { //print out the number
                        if(arguments.length != 0) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        System.out.print(my.consler.STPL.tape.tape[my.consler.STPL.tape.pointer]);
                    }
                    case "set" -> { // set current cell to
                        if(arguments.length != 1) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        my.consler.STPL.tape.tape[tape.pointer] = arguments[0];
                    }
                    case "gto" -> {
                        if(arguments.length != 1) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        if(arguments[0] < tape.tape.length){
                            tape.pointer = arguments[0];
                        } else {
                            System.out.println("Cell " + arguments[0] + " does not exist. Skipping");
                            continue;
                        }

                    }
                    case "gof" -> {
                        if(arguments.length != 1) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        try{
                            tape.pointer =+ arguments[0];
                        } catch (Exception e){
                            System.out.println("Cell " + (tape.pointer + arguments[0]) + " does not exist. Skipping");
                            continue;
                        }

                    }
                    case "gob" -> {
                        if(arguments.length != 1) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        try{
                            tape.pointer =- arguments[0];
                        } catch (Exception e){
                            System.out.println("Cell " + (tape.pointer - arguments[0]) + " does not exist. Skipping");
                            continue;
                        }

                    }
                    case "pru" -> {
                        if(arguments.length != 0) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        System.out.print((char) tape.tape[tape.pointer]);
                    }
                    case "add" -> {
                        if(arguments.length != 2) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.pointer] = arguments[0] + arguments[1];
                    }
                    case "sub" -> {
                        if(arguments.length != 2) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.pointer] = arguments[0] - arguments[1];
                    }
                    case "mul" -> {
                        if(arguments.length != 2) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.pointer] = arguments[0] * arguments[1];
                    }
                    case "div" -> {
                        if(arguments.length != 2) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.pointer] = arguments[0] / arguments[1];
                    }
                    case "and" -> {
                        if(arguments.length != 2) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.pointer] = arguments[0] & arguments[1];
                    }
                    case "orr" -> {
                        if(arguments.length != 2) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.pointer] = arguments[0] | arguments[1];
                    }
                    case "xor" -> {
                        if(arguments.length != 2) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.pointer] = arguments[0] ^ arguments[1];
                    }
                    case "com" -> {
                        if(arguments.length != 1) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.pointer] = ~(arguments[0]);
                    }
                    case "jum" -> {
                        if(arguments.length != 1) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        try{
                            current_line_count = arguments[0];
                        } catch (Exception e) {
                            System.err.println("Out of bounds at line " + current_line_count + ". Skipping");
                            continue;
                        }

                    }
                    case "juf" -> {
                        if(arguments.length != 1) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        try{
                            current_line_count = current_line_count + arguments[0];
                        } catch (Exception e){
                            System.err.println("Out of bounds at line " + current_line_count + ". Skipping");
                        }
                    }
                    case "jub" -> {
                        if(arguments.length != 1) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        try{
                            current_line_count = current_line_count - arguments[0];
                        } catch (Exception e){
                            System.err.println("Out of bounds at line " + current_line_count + ". Skipping");
                        }
                    }
                    case "jui" -> {
                        if(arguments.length != 2) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        try{
                            if(!(tape.tape[tape.pointer] == arguments[0])){
                                current_line_count = arguments[1];
                            }
                        } catch (Exception e){
                            System.err.println("Out of bounds at line " + current_line_count + ". Skipping");
                        }
                    }
                    case "ter" -> {
                        if(arguments.length != 0) {
                            System.err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        break;
                    }
                    default -> {
                        System.err.println("An unknown command at line " + current_line_count + ". Skipping");
                        continue;
                    }
                }
            } catch (Exception e) {
                System.err.println("Something weird happened at line " + current_line_count + ". Terminating");
                break;
            }


            //garbage collection
            arguments = null;
            System.gc();
        }
    }
}
