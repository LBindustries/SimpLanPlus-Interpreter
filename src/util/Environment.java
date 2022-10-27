package util;

import ast.Types.TypeNode;

public class Environment {
    private SymbolTableManager symbolTableManager;
    private int nestingLevel;
    private int offset;

    // indica se sia presente un return o no
    private boolean ret;

    // indica il tipo del return
    private TypeNode type;


    public Environment(Environment env){
        this.symbolTableManager = new SymbolTableManager(env.getSymbolTableManager().getSymbolTable());
        this.nestingLevel = env.nestingLevel;
        this.offset = env.offset;
        this.ret = env.ret;
        this.type = env.type;
    }
    public Environment(){
        symbolTableManager = new SymbolTableManager();
        nestingLevel = -1;
        offset = 0;
        ret = false;
        type = new TypeNode("none");
    }

    public Environment(Environment env, boolean copy){
        this.symbolTableManager = new SymbolTableManager(env.getSymbolTableManager().getSymbolTable(), copy);
        this.nestingLevel = env.getNestingLevel();
        this.offset = env.getOffset();
        this.ret = env.getRet();
        this.type = new TypeNode(env.type.getType());
    }

    public int getDecSpace(){
        return symbolTableManager.getDecSpace(nestingLevel);
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public TypeNode getType() {
        return type;
    }
    public void setType(TypeNode type) {
        this.type = type;
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

    public boolean getRet(){
        return ret;
    }

    public void setNestingLevel(int nestingLevel){
        this.nestingLevel = nestingLevel;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int decOffset(int toDec){
        this.offset += toDec;
        return offset - toDec;
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