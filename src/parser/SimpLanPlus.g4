grammar SimpLanPlus;

// THIS IS THE PARSER INPUT
program       : '{' declaration* statement* '}' EOF;
block	    : '{' declaration* statement* '}';

statement   : assignment ';'
	    | print ';'
	    | ret ';'
	    | ite
	    | call ';'
	    | block;


declaration : decFun
            | decVar ;

decFun	    : (type | 'void') ID '(' (arg (',' arg)*)? ')' '{' decVar* statement* '}';

decVar      : type ID ('=' exp)? ';' ;

type        : 'int'
            | 'bool';

arg         : ('var')? type ID;

assignment  : ID '=' exp ;

print	    : 'print' exp;

ret	    : 'return' (exp)?;

ite         : 'if' '(' exp ')' statement ('else' statement)?;

call        : ID '(' (exp(',' exp)*)? ')';

exp	    : '(' exp ')'				        #baseExp
	    | '-' exp					        #negExp
	    | '!' exp                                           #notExp
	    | ID						#derExp
	    | left=exp op=('*' | '/')               right=exp   #binExp
	    | left=exp op=('+' | '-')               right=exp   #binExp
	    | left=exp op=('<' | '<=' | '>' | '>=') right=exp   #binExp
	    | left=exp op=('=='| '!=')              right=exp   #binExp
	    | left=exp op='&&'                      right=exp   #binExp
	    | left=exp op='||'                      right=exp   #binExp
	    | call                                              #callExp
	    | BOOL                                              #boolExp
	    | NUMBER					        #valExp;


// THIS IS THE LEXER INPUT

//Booleans
BOOL        : 'true'|'false';

//IDs
fragment CHAR 	    : 'a'..'z' |'A'..'Z' ;
ID          : CHAR (CHAR | DIGIT)* ;

//Numbers
fragment DIGIT	    : '0'..'9';
NUMBER      : DIGIT+;

//ESCAPE SEQUENCES
WS              : (' '|'\t'|'\n'|'\r')-> skip;
LINECOMMENTS 	: '//' (~('\n'|'\r'))* -> skip;
BLOCKCOMMENTS   : '/*'( ~('/'|'*')|'/'~'*'|'*'~'/'|BLOCKCOMMENTS)* '*/' -> skip;

