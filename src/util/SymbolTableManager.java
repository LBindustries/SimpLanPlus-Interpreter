package util;

import ast.STentry;
import ast.Types.TypeNode;

import java.util.ArrayList;
import java.util.HashMap;

public class SymbolTableManager {

    private ArrayList<HashMap<String, STentry>> symbolTable;

    public SymbolTableManager(){
        symbolTable = new ArrayList<HashMap<String, STentry>>();
    }

    public SymbolTableManager(ArrayList<HashMap<String, STentry>> st){
        symbolTable = new ArrayList<HashMap<String, STentry>>();
        symbolTable.addAll(st);
    }

    public SymbolTableManager(ArrayList<HashMap<String, STentry>> st, boolean copy){
        this.symbolTable = new ArrayList<>();
        for(HashMap<String, STentry> m : st){
            HashMap tmp = new HashMap<>();
            for(String key : m.keySet()){
                int nl = m.get(key).getNestinglevel();
                TypeNode type = new TypeNode(m.get(key).getType().getType());
                int offset = m.get(key).getOffset();
                Effect effect = new Effect(m.get(key).getEffect());
                tmp.put(key, new STentry(nl, type, offset, effect));
            }
            this.symbolTable.add(tmp);
        }
    }

    public ArrayList<HashMap<String, STentry>> getSymbolTable() {
        return symbolTable;
    }

    public HashMap<String, STentry> getLevel(int nestingLevel){
        return symbolTable.get(nestingLevel);
    }

    public STentry getLastEntry(String id, int nestingLevel){
        for(int i=nestingLevel; i>=0; i--){
            if(symbolTable.get(i).get(id) != null)
                return symbolTable.get(i).get(id);
        }

        return null;
    }

    public int getSize(){
        return symbolTable.size();
    }

    public void addLevel(HashMap<String, STentry> newLevel){
        symbolTable.add(newLevel);
    }

    public void removeLevel(int index){
        symbolTable.remove(index);
    }
}
