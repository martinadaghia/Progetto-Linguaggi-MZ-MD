//start MultiDecNode
move SP FP  
pushr FP 
move SP AL 
pushr AL 
//start DecNode
push 0 
//and DecNode
//start DecNode
push 0 
//and DecNode
//start DecNode
push 0 
//and DecNode
//start AssNode
//start IntNode
storei A0 3
//end IntNode
move AL T1 
subi T1 1
load A0 0(T1) 
//and AssNode
//start IfStmNode
//start GreatNode
//start IdNode
move AL T1 
subi T1 1
store A0 0(T1) 
//end IdNode
pushr A0 
//start IntNode
storei A0 0
//end IntNode
popr T1 
bleq T1 A0 label2 
storei A0 1 
b label3 
label2: 
storei A0 0 
label3: 
//end GreatNode
storei T1 1 
beq A0 T1 label0
//start AssNode
//start IntNode
storei A0 7
//end IntNode
move AL T1 
subi T1 3
load A0 0(T1) 
//and AssNode
//start AssNode
//start IntNode
storei A0 5
//end IntNode
move AL T1 
subi T1 2
load A0 0(T1) 
//and AssNode
b label1
label0:
//start AssNode
//start IntNode
storei A0 3
//end IntNode
move AL T1 
subi T1 2
load A0 0(T1) 
//and AssNode
label1:
//end IfStmNode
//start IdNode
move AL T1 
subi T1 2
store A0 0(T1) 
//end IdNode
halt
//end MultiDecNode
