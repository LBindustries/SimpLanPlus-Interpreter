grammar Assembly;

@header {
import java.util.HashMap;
}

@lexer::members {
public int lexicalErrors=0;
}

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
push    : PUSH src=REG;
pop     : POP dest=REG;
top     : TOP dest=REG;
li      : LI dest=REG n=NUMBER;
mov     : MOV dest=REG src=REG;
lw      : LW reg1=REG offset=NUMBER'('reg2=REG')';
sw      : SW reg1=REG offset=NUMBER'('reg2=REG')';
lb      : LB reg1=REG offset=NUMBER'('reg2=REG')';
sb      : SB reg1=REG offset=NUMBER'('reg2=REG')';
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

print   : PRINT src=REG;
beq     : BEQ reg1=REG reg2=REG lab=LABEL;

label   : LABEL_IST lab=LABEL':';
halt    : HALT;
jal     : JAL lab=LABEL;
jr      : JR dest=REG;

PUSH  	 : 'push' ;
POP	 : 'pop' ;
TOP : 'top' ;
LI      : 'li';
MOV     : 'mov';
LW      : 'lw';
SW      : 'sw';
LB      : 'lb';
SB      : 'sb';

ADD	 : 'add' ;
ADDI : 'addi' ;
SUB	 : 'sub' ;
SUBI	: 'subi' ;
MULT	 : 'mult' ;
MULTI	: 'multi' ;
DIV	 : 'div' ;
DIVI : 'divi' ;
LT        : 'lt' ;
LTE       : 'lte' ;
GT        : 'gt' ;
GTE       : 'gte' ;
EQ        : 'eq' ;
NEQ       : 'neq' ;
AND       : 'and' ;
OR        : 'or' ;
NOT     : 'not' ;
NEG     : 'neg' ;

//STM
PRINT	 : 'print' ;
BEQ      : 'beq' ;

//Program
LABEL_IST : 'label' ;
HALT	 : 'halt' ;
JAL     : 'jal' ;
JR      : 'jr' ;
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
