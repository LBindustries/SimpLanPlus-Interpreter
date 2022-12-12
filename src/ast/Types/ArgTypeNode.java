package ast.Types;

public class ArgTypeNode extends TypeNode {

    private boolean isVar;
    // Type of an arg, keeps track if it's used as a var or not
    public ArgTypeNode(String type, boolean isVar) {
        super(type);
        this.isVar = isVar;
    }

    public boolean isVar() {
        return isVar;
    }
}
