package util;

public class Environment {
    private SymbolTableManager symbolTableManager;
    private int nestingLevel;
    private int offset;

    public Environment(Environment env){
        this.symbolTableManager = new SymbolTableManager(env.getSymbolTableManager().getSymbolTable());
        this.nestingLevel = env.nestingLevel;
        this.offset = env.offset;
    }
    public Environment(){
        symbolTableManager = new SymbolTableManager();
        nestingLevel = -1;
        offset = 0;
    }

    public SymbolTableManager getSymbolTableManager() {
        return symbolTableManager;
    }

    public int getNestingLevel() {
        return nestingLevel;
    }

    public int getOffset() {
        return offset;
    }

    public void setNestingLevel(int nestingLevel){
        this.nestingLevel = nestingLevel;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int decOffset(int toDec){
        this.offset -= toDec;
        return offset + toDec;
    }

    public int decNestingLevel(int toInc){
        this.nestingLevel -= toInc;
        return nestingLevel + toInc;
    }

    public int incNestingLevel(int toInc){
        this.nestingLevel += toInc;
        return nestingLevel - toInc;
    }

}