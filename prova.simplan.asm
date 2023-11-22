//start ProgNode
//start EqualNode
//start IntNode
storei A0 3
//end IntNode
pushr A0 
//start BoolNode
storei A0 0
//and BoolNode
popr T1 
beq A0 T1 label0
storei A0 0
b label1
label0:
storei A0 1
label1:
//and EqualNode
halt
//end ProgNode
