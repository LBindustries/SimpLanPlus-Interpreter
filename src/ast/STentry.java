package ast;

import ast.Types.TypeNode;
import util.Effect;

public class STentry {

    private int nl;
    private TypeNode type;
    private int offset;
    private Effect effect;

    public STentry(int n, int os, Effect ef) {
        nl = n;
        offset = os;
        effect = ef;
    }

    public STentry(int n, TypeNode t, int os, Effect ef) {
        nl = n;
        type = t;
        offset = os;
        effect = ef;
    }

    public Effect getEffect() {
        return effect;
    }

    public void addType(TypeNode t) {
        type = t;
    }

    public TypeNode getType() {
        return type;
    }

    public int getOffset() {
        return offset;
    }

    public int getNestinglevel() {
        return nl;
    }

    public String toPrint(String s) { //
        return s + "STentry: nestlev " + Integer.toString(nl) + "\n" +
                s + "STentry: type\n" +
                type.toPrint(s + "  ") +
                s + "STentry: offset " + Integer.toString(offset) + "\n";
    }
}