package interpreter;

import interpreter.Nodes.ProgramNode;
import interpreter.Nodes.InstructionNode;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.assembly.AssemblyLexer;
import parser.assembly.AssemblyParser;

import java.io.FileInputStream;
import java.util.HashMap;

public class Interpreter {

    public ProgramNode code;
    public int MEMSIZE = 10000;
    public int pc = 0;

    public byte[] stack = new byte[MEMSIZE];
    public int sp = MEMSIZE;
    public int fp = MEMSIZE;
    public int a0, t1, t2, ra;

    HashMap<String, Integer> labels;

    public Interpreter(String path) throws Exception{
        labels = new HashMap<>();
        FileInputStream is;
        try {
            is = new FileInputStream(path);
        } catch (Exception e){
            System.out.println("Something went wrong while accessing the ASM file. Please check the filename.");
            return;
        }
        ANTLRInputStream input = new ANTLRInputStream(is);
        AssemblyLexer lexer = new AssemblyLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AssemblyParser parser = new AssemblyParser(tokens);
        AssemblyVisitorImpl visitor = new AssemblyVisitorImpl();
        this.code = (ProgramNode) visitor.visit(parser.program());
    }



    private int fromByteArray(byte[] bytes, int start) {
        return ((bytes[start] & 0xFF) << 24) |
                ((bytes[start + 1] & 0xFF) << 16) |
                ((bytes[start + 2] & 0xFF) << 8 ) |
                ((bytes[start + 3] & 0xFF) << 0 );
    }

    private byte[] toByteArray(int value) {
        return new byte[] {
                (byte)(value >> 24),
                (byte)(value >> 16),
                (byte)(value >> 8),
                (byte)value };
    }

    private int LoadBool(int offset, int addrs){
        int start = offset + addrs;
        return ((stack[start] & 0xFF) << 24) |
                ((stack[start] & 0xFF) << 16) |
                ((stack[start] & 0xFF) << 8 ) |
                ((stack[start] & 0xFF) << 0 );
    }

    private  void StoreBool(int trg, int offset, int addrs){
        try {
            byte[] trgByte;
            trgByte = toByteArray(trg);
            stack[addrs + offset] = trgByte[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: out of memory.");
            System.exit(1);
        }
    }

    private void StoreWord(int trg, int offset, int addrs){
        try {
            byte[] trgByte;
            trgByte = toByteArray(trg);
            for (int i = 0; i < 4; i++) {
                stack[addrs + offset + i] = trgByte[i];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: out of memory.");
            System.exit(1);
        }
    }

    private int LoadWord(int offset, int addrs){
        return fromByteArray(stack,offset+addrs);
    }

    private int getRegister(String regName){
        int iRet = 0;
        switch (regName){
            case "$a0":
                iRet = this.a0;
                break;
            case "$t1":
                iRet = this.t1;
                break;
            case "$t2":
                iRet = this.t2;
                break;
            case "$sp":
                iRet = this.sp;
                break;
            case "$fp":
                iRet = this.fp;
                break;
            case "$ra":
                iRet = this.ra;
                break;
        }
        return iRet;
    }

    private void setRegister(String regName, int value){
        switch (regName){
            case "$a0":
                this.a0 = value;
                break;
            case "$t1":
                this.t1 = value;
                break;
            case "$t2":
                this.t2 = value;
                break;
            case "$sp":
                this.sp = value;
                break;
            case "$fp":
                this.fp = value;
                break;
            case "$ra":
                this.ra = value;
                break;
        }
    }

    public void runVM(){

        while(pc<code.getProgSize()){
            ASMNode instruction = code.getInstruction(pc++);
            InstructionNode ist = (InstructionNode) instruction;

            switch(ist.getOpcode()){
                case "push","pop","top","li","mov","lw","sw","lb","sb","add","addi","sub","subi","mult","multi","div","divi","lt","lte","gt","gte","eq","neq","and","or","not","neg","print","jal","jr","beq","halt": break;
                case "label":
                    pc -= 1;
                    labels.put(ist.getParam1(), pc);
                    code.delIst(pc);
                    //System.out.println(ist.getParam1() + " = " + pc);
            }
        }

        pc = 0;

        while(pc<code.getProgSize()){
            ASMNode instruction = code.getInstruction(pc++);
            InstructionNode ist = (InstructionNode) instruction;

            System.out.println(instruction.toPrint());
        }

        System.out.println("-----------------------\n\n");

        pc = 0;

        while(pc<code.getProgSize()){
            ASMNode instruction = code.getInstruction(pc++);
            InstructionNode ist = (InstructionNode) instruction;

            switch(ist.getOpcode()){
                case "push":
                    sp = sp - 4;
                    StoreWord(getRegister(ist.getParam1()),0,sp);
                    break;
                case "pop":
                    setRegister(ist.getParam1(),LoadWord(0,sp));
                    sp = sp + 4;
                    break;
                case "top":
                    setRegister(ist.getParam1(),LoadWord(0,sp));
                    break;
                case "li":
                    setRegister(ist.getParam1(),Integer.parseInt(ist.getParam2()));
                    break;
                case "mov":
                    setRegister(ist.getParam1(), getRegister(ist.getParam2()));
                    break;
                case "lw":
                    setRegister(ist.getParam1(), LoadWord(Integer.parseInt(ist.getParam2()),getRegister(ist.getParam3())));
                    break;
                case "sw":
                    StoreWord(getRegister(ist.getParam1()), Integer.parseInt(ist.getParam2()), getRegister(ist.getParam3()));
                    break;
                case "lb":
                    setRegister(ist.getParam1(), LoadBool(Integer.parseInt(ist.getParam2()),getRegister(ist.getParam3())));
                    break;
                case "sb":
                    StoreBool(getRegister(ist.getParam1()), Integer.parseInt(ist.getParam2()), getRegister(ist.getParam3()));
                    break;
                case "add":
                    setRegister(ist.getParam1(),getRegister(ist.getParam2()) + getRegister(ist.getParam3()));
                    break;
                case "addi":
                    setRegister(ist.getParam1(),getRegister(ist.getParam2()) + Integer.parseInt(ist.getParam3()));
                    break;
                case "sub":
                    setRegister(ist.getParam1(),getRegister(ist.getParam2()) - getRegister(ist.getParam3()));
                    break;
                case "subi":
                    setRegister(ist.getParam1(),getRegister(ist.getParam2()) - Integer.parseInt(ist.getParam3()));
                    break;
                case "mult":
                    setRegister(ist.getParam1(),getRegister(ist.getParam2()) * getRegister(ist.getParam3()));
                    break;
                case "multi":
                    setRegister(ist.getParam1(),getRegister(ist.getParam2()) * Integer.parseInt(ist.getParam3()));
                    break;
                case "div":
                    setRegister(ist.getParam1(),getRegister(ist.getParam2()) / getRegister(ist.getParam3()));
                    break;
                case "divi":
                    setRegister(ist.getParam1(),getRegister(ist.getParam2()) / Integer.parseInt(ist.getParam3()));
                    break;
                case "lt":
                    if (getRegister(ist.getParam2()) < getRegister(ist.getParam3())) {
                        setRegister(ist.getParam1(), -1);
                    }else{
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "lte":
                    if (getRegister(ist.getParam2()) <= getRegister(ist.getParam3())) {
                        setRegister(ist.getParam1(), -1);
                    }else{
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "gt":
                    if (getRegister(ist.getParam2()) > getRegister(ist.getParam3())) {
                        setRegister(ist.getParam1(), -1);
                    }else{
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "gte":
                    if (getRegister(ist.getParam2()) >= getRegister(ist.getParam3())) {
                        setRegister(ist.getParam1(), -1);
                    }else{
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "eq":
                    if (getRegister(ist.getParam2()) == getRegister(ist.getParam3())) {
                        setRegister(ist.getParam1(), -1);
                    }else{
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "neq":
                    if (getRegister(ist.getParam2()) != getRegister(ist.getParam3())) {
                        setRegister(ist.getParam1(), -1);
                    }else{
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "and":
                    setRegister(ist.getParam2(), getRegister(ist.getParam2()) & getRegister(ist.getParam3()));
                    break;
                case "or":
                    setRegister(ist.getParam2(), getRegister(ist.getParam2()) | getRegister(ist.getParam3()));
                    break;
                case "not":
                    if(getRegister(ist.getParam2()) == 0){
                        setRegister(ist.getParam1(), ~0);
                    }else{
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "neg":
                    setRegister(ist.getParam1(),-1 * getRegister(ist.getParam2()));
                    break;
                case "print":
                    System.out.println(getRegister(ist.getParam1()));
                    break;
                case "jal":
                    ra = pc;
                    pc = labels.get(ist.getParam1());
                    break;
                case "jr":
                    pc = getRegister(ist.getParam1());
                    break;
                case "beq":
                    if(getRegister(ist.getParam1()) == getRegister(ist.getParam2())){
                        pc = labels.get(ist.getParam3());
                    }
                    break;
                case "halt": break;
                default:
                    break;
            }
        }

    }

}