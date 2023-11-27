//start MultiDecNode
move SP FP  
pushr FP 
move SP AL 
pushr AL 
//start DecNode
push 0 
//and DecNode
//start AssNode
//start IntNode
storei A0 0
//end IntNode
move AL T1 
subi T1 1
load A0 0(T1) 
//and AssNode
halt
//end MultiDecNode
