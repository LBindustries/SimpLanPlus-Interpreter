package ast.ExpNodes;

import ast.IdNode;
import ast.Node;
import ast.STentry;
import ast.Types.FunctionTypeNode;
import ast.Types.TypeNode;
import util.Environment;
import util.LabelGenerator;
import util.SemanticError;

import java.util.ArrayList;

public class DerExpNode implements Node {

    private IdNode id; // "Perché non c'è una stringa e basta T.T" -Ale "Perchè sì" -Balu
    private int line;

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
    public TypeNode typeCheck(Environment env) {
        if(env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()) == null){
            System.out.println("Variable "+this.id.getId()+" not declared at line "+line+"."); // "Vogliamo ristamparlo?" -Ale
            System.exit(0);
        }
        if(env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getType() instanceof FunctionTypeNode){
            System.out.println("Trying to use function "+this.id.getId()+" as a variable at line "+line+".");
            System.exit(0);
        }
        if (! env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getEffect().isInitialized() && ! env.getSymbolTableManager().getLastEntry(this.id.getId(), env.getNestingLevel()).getEffect().isUsed()){
            System.out.println("Variable "+this.id.getId()+" not initialized at line "+line+".");
            System.exit(0);
        }

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
            res.add(new SemanticError("Variable "+this.id.getId()+" not declared at line "+line+"."));
        return res;
    }

    @Override
    public void setupBreaks(ArrayList<Integer> breaks){
        return;
    }
}
