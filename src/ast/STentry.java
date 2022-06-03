package ast;
import util.Effect;

public class STentry {

    private int nl; // Nest Level
    private Node type; // tipo della variabile (?) "possiamo usare TypeNode?" -Ale
    private Effect effect; // 0 = bottom   1 = initialized     2 = used
    private int offset; // "sarà utile per l'interprete"

    public STentry (int n, int os, Effect ef) {
        nl=n;
        offset=os;
        effect=ef;
    }

    public STentry (int n, Node t, int os, Effect ef){
        nl=n;
        type=t;
        offset=os;
        effect=ef;
    }

    public void addType (Node t){
        type=t;
    }

    public Effect getEffect() {
        return effect;
    }

    public Node getType () {
        return type;
    }

    public int getOffset () {
        return offset;
    }

    public int getNestinglevel (){
        return nl;
    }

    public String toPrint(String s) { //
        return s+"STentry: nestlev " + Integer.toString(nl) +"\n"+
                s+"STentry: type\n" +
                type.toPrint(s+"  ") +
                s+"STentry: offset " + Integer.toString(offset) + "\n";
    }
}