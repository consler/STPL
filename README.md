# STPL
### STPL is a simple turing programming language with an assembly-like look.


Commands:


```all``` - 1 argument, allocats cells to the program. Must always be at line 0. ```all 10 `` would give 11 cells to the program, from 0 to 10.


```set``` - 1 argument, sets current cell to an integer from -2³¹ to 2³¹


```gto``` - 1 argument, goes to a cell, limited to what you allocated 


```pri``` - no arguments, prints the current cell's integer


```pru``` - no arguments, prints the UTF-8 equivalent of the current cell's integer 


```add``` - 2 arguments, adds two numbers and writes the result to the current cell


```sub``` - 2 arguments, subtracts two numbers and writes the result to the current cell

```mul``` - 2 arguments, multiplies two numbers and writes the result to the current cell


### Code example can be found at the ```input``` variable in src/global.java

https://www.notion.so/STPL-38d999327b5c4cd097c5d30a85c8d494
