//start ProgNode
//start GreatNode
//start IntNode
storei A0 5
//end IntNode
pushr A0 
//start IntNode
storei A0 7
//end IntNode
popr T1 
bleq T1 A0 label0 
label2: 
storei A0 1 
b label1 
label0: 
beq T1 A0 label2 
storei A0 0 
label1: 
//end GreatNode
halt
//end ProgNode
