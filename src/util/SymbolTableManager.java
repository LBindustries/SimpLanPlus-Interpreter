package util;

import ast.STentry;

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
