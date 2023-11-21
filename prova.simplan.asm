//start MultiDecNode
move SP FP  
pushr FP 
move SP AL 
pushr AL 
//start DecNode
push 0 
//and DecNode
//start FunNode
push function0
//end FunNode
//start CallFunNode
pushr FP 
move SP FP 
addi FP 1 
move AL T1
pushr T1 
//start IntNode
storei A0 5
//end IntNode
pushr A0
//start IntNode
storei A0 4
//end IntNode
pushr A0
move FP AL 
subi AL 1 
jsub function0
//and CallFunNode
halt

function0:
pushr RA 
//start BodyNode
//start DecNode
push 0 
//and DecNode
//start IfStmNode
//start GreatNode
//start IdNode
move AL T1 
subi T1 1
store A0 0(T1) 
//end IdNode
pushr A0 
//start IdNode
move AL T1 
subi T1 2
store A0 0(T1) 
//end IdNode
popr T1 
gt A0 T1 label2 
push 0 
b label3 
label2: 
push 1 
label3: 
//end GreatNode
storei T1 1 
beq A0 T1 label0
//start assegnamentoNode
//start IntNode
storei A0 1
//end IntNode
move AL T1 
subi T1 4
load A0 0(T1) 
//and assegnamentoNode
//start CallFunNode
pushr FP 
move SP FP 
addi FP 1 
move AL T1
store T1 0(T1) 
pushr T1 
//start PlusNode
//start IdNode
move AL T1 
subi T1 1
store A0 0(T1) 
//end IdNode
pushr A0 
//start IntNode
storei A0 1
//end IntNode
popr T1 
add A0 T1 
popr A0 
//end PlusNode
pushr A0
//start PlusNode
//start IdNode
move AL T1 
subi T1 2
store A0 0(T1) 
//end IdNode
pushr A0 
//start IntNode
storei A0 1
//end IntNode
popr T1 
add A0 T1 
popr A0 
//end PlusNode
pushr A0
move FP AL 
subi AL 1 
jsub function0
//and CallFunNode
b label1
label0:
//start assegnamentoNode
//start PlusNode
//start IdNode
move AL T1 
subi T1 1
store A0 0(T1) 
//end IdNode
pushr A0 
//start IdNode
move AL T1 
subi T1 2
store A0 0(T1) 
//end IdNode
popr T1 
add A0 T1 
popr A0 
//end PlusNode
move AL T1 
store T1 0(T1) 
subi T1 1
load A0 0(T1) 
//and assegnamentoNode
label1:
//end IfStmNode
addi SP 1
//and BodyNode
popr RA 
addi SP 2
pop 
store FP 0(FP) 
move FP AL 
subi AL 1 
pop 
rsub RA 
//end MultiDecNode
