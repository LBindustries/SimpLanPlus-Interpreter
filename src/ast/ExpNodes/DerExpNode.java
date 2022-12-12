package ast.ExpNodes;

import ast.IdNode;
import ast.Node;
import ast.STentry;
import ast.Types.FunctionTypeNode;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;
import util.TypeCheckException;

import java.util.ArrayList;

public class DerExpNode implements Node {

    private IdNode id;
    private int line;
    // Variable invocation
    public DerExpNode(IdNode id, int line){
        this.id = id;
        this.line = line;
    }

    public IdNode getId() {
        return id;
    }

    @Override
    public String toPrint(String indent) {
        return "\n"+indent + "DerExpNode" + this.id.toPrint(indent+" ");
    }

    @Override
    public TypeNode typeCheck(Environment env) throws TypeCheckException {
        if(env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()) == null){
            // If the variable is not declared.
            throw new TypeCheckException("[!] Variable "+this.id.getId()+" not declared at line "+line+".");
        }
        if(env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getType() instanceof FunctionTypeNode){
            // If Symbol is actually a function
            throw new TypeCheckException("[!] Trying to use function "+this.id.getId()+" as a variable at line "+line+".");
        }
        if (! env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getEffect().isInitialized() && ! env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getEffect().isUsed()){
            // If the Variable is not initialized
            throw new TypeCheckException("[!] Variable "+this.id.getId()+" not initialized at line "+line+".");
        }
        // Set this variable as used and return its type
        env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getEffect().setUsed();
        return env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getType();
    }

    @Override
    public String codeGeneration(LabelGenerator labgen, Environment localenv) {
        String asm = ";Variable load\n";

        asm += "mov $t1 $fp\n";

        for(int i = 0; i < (localenv.getNestingLevel() - localenv.getSymbolTableManager().getLastEntry(id.getId(), localenv.getNestingLevel()).getNestinglevel()); i++ ){
            asm += "lw $t1 0($t1)\n";
        }
        // Use different asm opcodes to load up a word or a boolean
        if(localenv.getSymbolTableManager().getLastEntry(id.getId(), localenv.getNestingLevel()).getType().getType().equals("int")) {
            asm += "lw $a0 " + localenv.getSymbolTableManager().getLastEntry(id.getId(), localenv.getNestingLevel()).getOffset() + "($t1)\n";
        } else if (localenv.getSymbolTableManager().getLastEntry(id.getId(), localenv.getNestingLevel()).getType().getType().equals("bool")) {
            asm += "lb $a0 " + localenv.getSymbolTableManager().getLastEntry(id.getId(), localenv.getNestingLevel()).getOffset() + "($t1)\n";
        }
        return asm;
    }

    @Override
    public ArrayList<SemanticError> checkSemantics(Environment env) {
        ArrayList<SemanticError> res = new ArrayList<SemanticError>();

        STentry entry = env.getSymbolTableManager().getLastEntry(id.getId(), env.getNestingLevel());

        if (entry==null)
            res.add(new SemanticError("[!] Variable "+this.id.getId()+" not declared at line "+line+"."));
        return res;
    }

    @Override
    public void setupBreaks(ArrayList<Integer> breaks){
        return;
    }
}
