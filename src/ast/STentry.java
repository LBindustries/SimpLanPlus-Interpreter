package ast;
public class STentry {

    private int nl; // Nest Level
    private Node type; // tipo della variabile (?)
    private int offset; // "sarà utile per l'interprete"
    private boolean isFunction = false;
    private int nargs = 0;

    public STentry (int n, int os)
    {nl=n;
        offset=os;}

    public STentry(int n, Node t, int os, boolean isFunction, int nargs){
        nl = n;
        offset = os;
        this.type = t;
        this.isFunction = isFunction;
        this.nargs = nargs;
    }

    public STentry (int n, Node t, int os)
    {nl=n;
        type=t;
        offset=os;}

    public void addType (Node t)
    {type=t;}

    public int getNargs() {
        return nargs;
    }

    public boolean isFunction() {
        return isFunction;
    }

    public Node getType ()
    {return type;}

    public int getOffset ()
    {return offset;}

    public int getNestinglevel ()
    {return nl;}

    public String toPrint(String s) { //
        return s+"STentry: nestlev " + Integer.toString(nl) +"\n"+
                s+"STentry: type\n" +
                type.toPrint(s+"  ") +
                s+"STentry: offset " + Integer.toString(offset) + "\n";
    }
}