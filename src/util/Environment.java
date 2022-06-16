package util;

public class Environment {
    private SymbolTableManager symbolTableManager = new SymbolTableManager();
    private int nestingLevel = -1;
    private int offset = 0;

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
        return offset;
    }
}