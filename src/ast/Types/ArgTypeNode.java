package ast.Types;

public class ArgTypeNode extends TypeNode {

    private boolean isVar;

    public ArgTypeNode(String type, boolean isVar) {
        super(type);
        this.isVar = isVar;
    }

    public boolean isVar() {
        return isVar;
    }
}
