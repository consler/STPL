package my.consler.STPL;

import static java.lang.System.*;

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
                err.println("Not a valid string at line " + current_line_count + ". Skipping");
                continue;
            }
            //if longer then 3 skip
            if (!(current_line.charAt(3) == ' ' || current_line.charAt(3) == ';')) {
                err.println("Command at line " + current_line_count + " seems to not be equal to 3 characters long. Skipping");
                continue;
            }



            // current command assignment

            command = current_line.substring(0, 3);

            // error handling (I just want to add some frustration to this world)
            if(!(current_line.endsWith(";")))
            {
                err.println("Semicolon missed at line " + current_line_count + ". Skipping");
                continue;
            }

            if(current_line_count == 0 && !(command.equals("all"))) {
                err.println("Allocation should be done at line 0. Terminating");
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
                            arguments[j] = tape.tape[tape.tape_pointer][Integer.parseInt(i.substring(1))];
                        } catch (Exception e) {
                            err.println("A cell seems to not exist at " + current_line_count + ". Skipping");
                            continue;
                        }
                    } else{
                        arguments[j] = Integer.parseInt(i);
                    }
                    j++;
                }

            } catch (Exception e){

                err.println("One of the arguments does not seem to be an integer at line " + current_line_count + ". Skipping");
                continue;
            }
            //no need for temp now
            temp_arguments = null;
            gc();



            //runtime process itself
            try{
                switch (command)
                {
                    case "all" -> { // memory allocation
                        if(arguments.length != 2) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape_load(arguments[0], arguments[1]);
                    }
                    case "pri" -> { //print out the number
                        if(arguments.length != 0) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        out.print(tape.tape[tape.tape_pointer][tape.pointer]);
                    }
                    case "set" -> { // set current cell to
                        if(arguments.length != 1) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.tape_pointer][tape.pointer] = arguments[0];
                    }
                    case "gto" -> {
                        if(arguments.length != 1) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        if(arguments[0] < tape.tape[tape.tape_pointer].length){
                            tape.pointer = arguments[0];
                        } else {
                            out.println("Cell " + arguments[0] + " does not exist at tape " + tape.tape_pointer +". Skipping");
                            continue;
                        }

                    }
                    case "gof" -> {
                        if(arguments.length != 1) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        if(arguments[0] + 1 < tape.tape[tape.tape_pointer].length){
                            tape.pointer =+ arguments[0];
                        } else {
                            out.println("Cell " + arguments[0] + " does not exist at tape " + tape.tape_pointer +". Skipping");
                            continue;
                        }

                    }
                    case "gob" -> {
                        if(arguments.length != 1) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        if(arguments[0] - 1  < tape.tape[tape.tape_pointer].length){
                            tape.pointer =- arguments[0];
                        } else {
                            out.println("Cell " + arguments[0] + " does not exist at tape " + tape.tape_pointer +". Skipping");
                            continue;
                        }

                    }
                    case "pru" -> {
                        if(arguments.length != 0) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        out.print((char) tape.tape[tape.tape_pointer][tape.pointer]);
                    }
                    case "add" -> {
                        if(arguments.length != 2) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.tape_pointer][tape.pointer] = arguments[0] + arguments[1];
                    }
                    case "sub" -> {
                        if(arguments.length != 2) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.tape_pointer][tape.pointer] = arguments[0] - arguments[1];
                    }
                    case "mul" -> {
                        if(arguments.length != 2) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.tape_pointer][tape.pointer] = arguments[0] * arguments[1];
                    }
                    case "div" -> {
                        if(arguments.length != 2) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.tape_pointer][tape.pointer] = arguments[0] / arguments[1];
                    }
                    case "and" -> {
                        if(arguments.length != 2) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.tape_pointer][tape.pointer] = arguments[0] & arguments[1];
                    }
                    case "orr" -> {
                        if(arguments.length != 2) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.tape_pointer][tape.pointer] = arguments[0] | arguments[1];
                    }
                    case "xor" -> {
                        if(arguments.length != 2) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.tape_pointer][tape.pointer] = arguments[0] ^ arguments[1];
                    }
                    case "com" -> {
                        if(arguments.length != 1) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape[tape.tape_pointer][tape.pointer] = ~(arguments[0]);
                    }
                    case "jum" -> {
                        if(arguments.length != 1) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        try{
                            current_line_count = arguments[0];
                        } catch (Exception e) {
                            err.println("Out of bounds at line " + current_line_count + ". Skipping");
                            continue;
                        }

                    }
                    case "juf" -> {
                        if(arguments.length != 1) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        try{
                            current_line_count = current_line_count + arguments[0];
                        } catch (Exception e){
                            err.println("Out of bounds at line " + current_line_count + ". Skipping");
                        }
                    }
                    case "jub" -> {
                        if(arguments.length != 1) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        try{
                            current_line_count = current_line_count - arguments[0];
                        } catch (Exception e){
                            err.println("Out of bounds at line " + current_line_count + ". Skipping");
                        }
                    }
                    case "jui" -> {
                        if(arguments.length != 2) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        try{
                            if(!(tape.tape[tape.tape_pointer][tape.pointer] == arguments[0])){
                                current_line_count = arguments[1];
                            }
                        } catch (Exception e){
                            err.println("Out of bounds at line " + current_line_count + ". Skipping");
                        }
                    }
                    case "ter" -> {
                        if(arguments.length != 0) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        break;
                    }
                    case "tap" -> {
                        if(arguments.length != 1) {
                            err.println("Wrong amount of arguments at line " + current_line_count + ". Skipping");
                            continue;
                        }
                        tape.tape_pointer = arguments[0];
                    }
                    default -> {
                        err.println("An unknown command at line " + current_line_count + ". Skipping");
                        continue;
                    }
                }
            } catch (Exception e) {
                err.println("Something weird happened at line " + current_line_count + ". Terminating");
                break;
            }

            //debugging only
//            out.print("\n");
//            for(int i = 0; i<tape.tape.length; i++){
//                for(int j = 0; j<tape.tape[0].length; j++){
//                    out.print(tape.tape[i][j] + " ");
//                }
//                out.print("\n");
//            }

            //garbage collection
            arguments = null;
            gc();
        }
    }
}
