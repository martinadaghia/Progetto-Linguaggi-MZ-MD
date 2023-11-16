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
//start assegnamentoNode
//start IntNode
storei A0 1
//end IntNode
move AL T1 
subi T1 1
load A0 0(T1) 
//and assegnamentoNode
//start assegnamentoNode
//start PlusNode
//start IdNode
move AL T1 
subi T1 1
store A0 0(T1) 
//end IdNode
pushr A0 
//start IntNode
storei A0 2
//end IntNode
popr T1 
add A0 T1 
popr A0 
//end PlusNode
move AL T1 
subi T1 2
load A0 0(T1) 
//and assegnamentoNode
//start assegnamentoNode
//start MulNode
//start IdNode
move AL T1 
subi T1 2
store A0 0(T1) 
//end IdNode
pushr A0 
//start IntNode
storei A0 3
//end IntNode
popr T1 
mul A0 T1 
popr A0 
//end MulNode
move AL T1 
subi T1 3
load A0 0(T1) 
//and assegnamentoNode
halt
//end MultiDecNode
