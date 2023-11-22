//start ProgNode
//start LessNode
//start IntNode
storei A0 5
//end IntNode
pushr A0 
//start IntNode
storei A0 3
//end IntNode
popr T1 
blt T1 A0 label0 
push 0 
b label1 
label0: 
push 1 
label1: 
//end LessNode
halt
//end ProgNode
