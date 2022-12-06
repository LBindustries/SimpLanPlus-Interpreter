grammar Assembly;

@header {
import java.util.HashMap;
}

@lexer::members {
public int lexicalErrors=0;
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

program: instruction* ;

instruction : label
            | push
            | pop
            | top
            | li
            | mov
            | lw
            | sw
            | lb
            | sb
            | add
            | addi
            | sub
            | subi
            | mult
            | multi
            | div
            | divi
            | lt
            | lte
            | gt
            | gte
            | eq
            | neq
            | and
            | or
            | not
            | neg
            | print
            | jal
            | jr
            | beq
            | halt;

// MEM
push    : PUSH src=REG;
pop     : POP dest=REG;
top     : TOP dest=REG;
li      : LI dest=REG n=NUMBER;
mov     : MOV dest=REG src=REG;
lw      : LW reg1=REG offset=NUMBER'('reg2=REG')';
sw      : SW reg1=REG offset=NUMBER'('reg2=REG')';
lb      : LB reg1=REG offset=NUMBER'('reg2=REG')';
sb      : SB reg1=REG offset=NUMBER'('reg2=REG')';

// EXP
add     : ADD dest=REG reg1=REG reg2=REG;
addi    : ADDI dest=REG reg1=REG val=NUMBER;
sub     : SUB dest=REG reg1=REG reg2=REG;
subi    : SUBI dest=REG reg1=REG val=NUMBER;
mult    : MULT dest=REG reg1=REG reg2=REG;
multi   : MULTI dest=REG reg1=REG val=NUMBER;
div     : DIV dest=REG reg1=REG reg2=REG;
divi    : DIVI dest=REG reg1=REG val=NUMBER;
lt      : LT dest=REG reg1=REG reg2=REG;
lte     : LTE dest=REG reg1=REG reg2=REG;
gt      : GT dest=REG reg1=REG reg2=REG;
gte     : GTE dest=REG reg1=REG reg2=REG;
eq      : EQ dest=REG reg1=REG reg2=REG;
neq     : NEQ dest=REG reg1=REG reg2=REG;
and     : AND dest=REG reg1=REG reg2=REG;
or      : OR dest=REG reg1=REG reg2=REG;
not     : NOT dest=REG src=REG;
neg     : NEG dest=REG src=REG;

// STM
print   : PRINT src=REG;
beq     : BEQ reg1=REG reg2=REG lab=LABEL;

// Program
label   : LABEL_IST lab=LABEL':';
halt    : HALT;
jal     : JAL lab=LABEL;
jr      : JR dest=REG;
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

// MEM
PUSH  	 : 'push' ; 	// pushes constant in the stack
POP	 : 'pop' ; 	// pops from stack
TOP : 'top' ;
LI      : 'li';
MOV     : 'mov';
LW      : 'lw';
SW      : 'sw';
LB      : 'lb';
SB      : 'sb';

// EXP
ADD	 : 'add' ;  	// add two values from the stack
ADDI : 'addi' ;
SUB	 : 'sub' ;	// add two values from the stack
SUBI	: 'subi' ;
MULT	 : 'mult' ;  	// add two values from the stack
MULTI	: 'multi' ;
DIV	 : 'div' ;	// add two values from the stack
DIVI : 'divi' ;
LT        : 'lt' ; // Lower than
LTE       : 'lte' ; // Lower than or equal
GT        : 'gt' ; // Greater than
GTE       : 'gte' ; // Greater than or equal
EQ        : 'eq' ; // Equal
NEQ       : 'neq' ; // Not Equal
AND       : 'and' ;
OR        : 'or' ;
NOT     : 'not' ;
NEG     : 'neg' ;

//STM
PRINT	 : 'print' ;	// print top of stack
BEQ      : 'beq' ;	// Break if equal w

//Program
LABEL_IST : 'label' ;
HALT	 : 'halt' ;	// stop execution
JAL     : 'jal' ; // Jump to label
JR      : 'jr' ; // Jump to register
SYMBOLS : '_';
LABEL   : STRING(STRING|NUMBER|SYMBOLS)*;

fragment DIGIT  : '0'..'9';
REG : '$'('t'DIGIT|'ra'|'sp'|'fp'|'a0');

fragment CHAR : ('a'..'z'|'A'..'Z');
STRING : CHAR+;

COL	 : ':' ;
NUMBER	 :  '0' | ('-')?(('1'..'9')DIGIT*) ;

WHITESP  : ( '\t' | ' ' | '\r' | '\n' )+   -> skip;
LINECOMMENTS 	: ';' (~('\n'|'\r'))* -> skip;
