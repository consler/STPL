# STPL

## STPL is a simple turing programming language with an assembly-like look, made in Java.

### Installation

You will need to download JRE 17 to run STPL.

You can find the latest version at [Tags](https://github.com/consler/STPL/tags) .


### Running

``` java -jar path/to/STPL.jar path/to/your/program.stpl```

It is recommended to set up an alias.

### Commands:

```all``` - 2 argument, allocats cells to the program. Must always be at the first line. all 10 20 would give 11 tapes to the program, from 0 to 10. Every tape would have 21 cells.

```set``` - 1 argument, sets current cell to an integer from -2³¹ to 2³¹

```gto``` - 1 argument, goes to a cell, limited to what you allocated

```pri``` - no arguments, prints the current cell's integer

```pru``` - no arguments, prints the UTF-8 equivalent of the current cell's integer

```add``` - 2 arguments, adds two numbers and writes the result to the current cell

```sub``` - 2 arguments, subtracts two numbers and writes the result to the current cell

```mul``` - 2 arguments, multiplies two numbers and writes the result to the current cell

```div``` - 2 arguments, divides two numbers and writes the result to the current cell(integer only)

```juf``` - 1 argument, jumps forward in code by lines

```jub``` - 1 argument, jumps backward in code by lines

```jum``` - 1 argument, jumps to a line in code (NOTE: count for lines start from 0 after all)

```jif```- 2 arguments, jumps forward by 2nd argument lines in code if current cell does not equal to 1st argument

```jib```- 2 arguments, jumps backward by 2nd argument lines in code if current cell does not equal to 1st argument

```jui```- 2 arguments, to line 2nd argument in code if current cell does not equal to 1st argument

```orr``` - 2 arguments, bitwise or

```and``` - 2 arguments, bitwise and

```xor``` - 2 arguments, bitwise xor

```com``` - 1 argument, java bitwise "complement" operator

```ter``` - no arguemnts, terminates the program

```tap``` - 1 arguement, switches current tape

```inp``` - no arguements, takes user input(must be an integer) and writes it to the current cell

```inu``` - no arguements, takes the user input(must be 1 character string) and writes the utf8 equvalent of it to the curren cell

```int``` - 1 arguement, takes the user input and writes every letter in separate cell in tape 1st arguement

### Notes: 

You can get a value of a cell by doing t\<tape\>p\<cell\>, for example t2p13.

Most runtime things to know is at [src/runtime.](https://github.com/consler/STPL/blob/main/src/my/consler/STPL/runtime.java).

You can find some code examples at [examples](https://github.com/consler/STPL/tree/main/examples).
