# STPL
## STPL is a simple turing programming language with an assembly-like look.


### Commands:

all - 1 argument, allocats cells to the program. Must always be at the first line. all 10 would give 11 cells to the program, from 0 to 10.

set - 1 argument, sets current cell to an integer from -2³¹ to 2³¹

gto - 1 argument, goes to a cell, limited to what you allocated

pri - no arguments, prints the current cell's integer

pru - no arguments, prints the UTF-8 equivalent of the current cell's integer

add - 2 arguments, adds two numbers and writes the result to the current cell

sub - 2 arguments, subtracts two numbers and writes the result to the current cell

mul - 2 arguments, multiplies two numbers and writes the result to the current cell

div - 2 arguments, divides two numbers and writes the result to the current cell(integer only)

juf - 1 argument, jumps forward in code by lines

jub - 1 argument, jumps backward in code by lines

jum - 1 argument, jumps to a line in code (NOTE: count for lines start from 0 after all)

jif- 2 arguments, jumps forward by 2nd argument lines in code if current cell does not equal to 1st argument

jib- 2 arguments, jumps backward by 2nd argument lines in code if current cell does not equal to 1st argument

jui- 2 arguments, to line 2nd argument in code if current cell does not equal to 1st argument

orr - 2 arguments, bitwise or

and - 2 arguments, bitwise and

xor - 2 arguments, bitwise xor

com - 1 arguments, java bitwise "complement" operator

### Notes: 
Everything starts from 0.
You can get a value of a cell by doing p<cell>, for example p13
Code is edited at src/global
Most runtime things to know is ar src/runtime
