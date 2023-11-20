//start MultiDecNode
move SP FP  
pushr FP 
move SP AL 
pushr AL 
//start DecNode
push 0 
//and DecNode
//start assegnamentoNode
//start IntNode
storei A0 6
//end IntNode
move AL T1 
subi T1 1
load A0 0(T1) 
//and assegnamentoNode
//start IdNode
move AL T1 
subi T1 1
store A0 0(T1) 
//end IdNode
halt
//end MultiDecNode
