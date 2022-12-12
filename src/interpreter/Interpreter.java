package interpreter;

import interpreter.Nodes.ProgramNode;
import interpreter.Nodes.InstructionNode;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import parser.assembly.AssemblyLexer;
import parser.assembly.AssemblyParser;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Interpreter {

    private ProgramNode code;
    private int MEMSIZE;
    private int pc = 0;

    private byte[] stack;
    private int sp;
    private int fp;
    private int a0, t1, t2, ra;
    private boolean debug;

    HashMap<String, Integer> labels;
    // Interpreter constructor. Takes the asm path, the stack size and if it's in debug mode
    public Interpreter(String path, int memsize, boolean debug) throws Exception {
        // Initializes pointers, registers, label managers and stack.
        labels = new HashMap<>();
        this.MEMSIZE = memsize;
        this.stack = new byte[this.MEMSIZE];
        this.sp = this.MEMSIZE;
        this.fp = this.MEMSIZE;
        this.debug = debug;
        // Try to read the asm file.
        FileInputStream is;
        try {
            is = new FileInputStream(path);
        } catch (Exception e) {
            System.out.println("Something went wrong while accessing the ASM file. Please check the filename.");
            return;
        }
        // Tokenize and parse the asm;
        ANTLRInputStream input = new ANTLRInputStream(is);
        AssemblyLexer lexer = new AssemblyLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        AssemblyParser parser = new AssemblyParser(tokens);
        AssemblyVisitorImpl visitor = new AssemblyVisitorImpl();
        this.code = (ProgramNode) visitor.visit(parser.program());
    }


    private int fromByteArray(byte[] bytes, int start) {
        // Convert a bytearray to an integer
        return ((bytes[start] & 0xFF) << 24) |
                ((bytes[start + 1] & 0xFF) << 16) |
                ((bytes[start + 2] & 0xFF) << 8) |
                ((bytes[start + 3] & 0xFF) << 0);
    }

    private byte[] toByteArray(int value) {
        // Convert an integer value to a bytearray
        return new byte[]{
                (byte) (value >> 24),
                (byte) (value >> 16),
                (byte) (value >> 8),
                (byte) value};
    }

    private int LoadBool(int offset, int addrs) {
        // Load a boolean from stack
        try{
            int start = offset + addrs;
            return stack[start];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: out of memory. Please adjust the amount of allocated memory with the -m param.");
            System.exit(1);
        }
        return 0;
    }

    private void StoreBool(int trg, int offset, int addrs) {
        // Store a boolean
        try {
            byte[] trgByte;
            trgByte = toByteArray(trg);
            stack[addrs + offset] = trgByte[3];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: out of memory. Please adjust the amount of allocated memory with the -m param.");
            System.exit(1);
        }
    }

    private void StoreWord(int trg, int offset, int addrs) {
        // Save an integer
        try {
            byte[] trgByte;
            trgByte = toByteArray(trg);
            for (int i = 0; i < 4; i++) {
                stack[addrs + offset + i] = trgByte[i];
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: out of memory. Please adjust the amount of allocated memory with the -m param.");
            System.exit(1);
        }
    }

    private int LoadWord(int offset, int addrs) {
        // Load an integer
        try{
            return fromByteArray(stack, offset + addrs);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: out of memory. Please adjust the amount of allocated memory with the -m param.");
            System.exit(1);
        }
        return 0;
    }

    private void MemoryInspector(ASMNode prev, ASMNode next) {
        // Memory inspector routine
        System.out.println("");
        String input = "";

        Scanner sc = new Scanner(System.in);
        while (!input.equals("q")) {
            System.out.println("SLP Memory Inspector started. \nProgram Counter:" + this.pc);
            System.out.println("Previous instruction: " + prev.toPrint() + " Next instruction:" + next.toPrint());
            System.out.println("MEMORY STATUS: Frame pointer:" + this.fp + " Stack pointer:" + this.sp + "\nRegisters: a0:" + this.a0 + " t1:" + this.t1 + " t2:" + this.t2 + "\nReturn address: " + this.ra);
            System.out.println("\nTo inspect memory allocations, please type a range of memory addresses \nin the format start end [!], where ! will show only the non-zero values. \nType q to resume execution.");
            System.out.println("Maximum available memory is " + this.MEMSIZE + " bytes.");
            input = sc.nextLine();
            if (!input.equals("q")) {
                String[] parts = input.split(" ");
                if (parts.length < 2 || parts.length > 3) {
                    System.out.println("Could not recognize pattern.");
                    continue;
                }
                int start = Integer.parseInt(parts[0]);
                int end = Integer.parseInt(parts[1]);
                boolean zero = false;
                if (parts.length == 3) {
                    zero = true;
                }
                if (start > MEMSIZE || start > end || start < 0 || end > MEMSIZE || end < 0) {
                    System.out.println("Given interval is not valid.");
                    continue;
                }
                int elements_in_line = 0;
                for (int i = start; i < end; i++) {
                    if (elements_in_line > 9) {
                        System.out.print("\n");
                        elements_in_line = 0;
                    }
                    if ((zero && stack[i] != 0) || !zero) {
                        int value = stack[i];
                        //System.out.print(i+": "+value);
                        System.out.printf("%2d. %-10d ",  i, value);
                        elements_in_line++;
                    }
                }
                System.out.println("\nPress enter to proceed...");
                try{
                    System.in.read();
                } catch (Exception e){

                }

            }
        }
        System.out.println("Resuming execution...\n");
    }

    private int getRegister(String regName) {
        // Get register by name
        int iRet = 0;
        switch (regName) {
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

    private void setRegister(String regName, int value) {
        // Set register by name
        switch (regName) {
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

    public void runVM() {
        // Run the VM
        while (pc < code.getProgSize()) {
            ASMNode instruction = code.getInstruction(pc++);
            InstructionNode ist = (InstructionNode) instruction;
            // Create labels
            switch (ist.getOpcode()) {
                case "push", "pop", "top", "li", "mov", "lw", "sw", "lb", "sb", "add", "addi", "sub", "subi", "mult", "multi", "div", "divi", "lt", "lte", "gt", "gte", "eq", "neq", "and", "or", "not", "neg", "print", "jal", "jr", "beq", "halt":
                    break;
                case "label":
                    pc -= 1;
                    labels.put(ist.getParam1(), pc);
                    code.delIst(pc);
                    //System.out.println(ist.getParam1() + " = " + pc);
            }
        }

        System.out.println("-----------------------------");

        pc = 0;
        // Start execution
        while (pc < code.getProgSize()) {
            ASMNode instruction = code.getInstruction(pc++);
            InstructionNode ist = (InstructionNode) instruction;
            // Handle instructions
            switch (ist.getOpcode()) {
                case "push":
                    sp = sp - 4;
                    StoreWord(getRegister(ist.getParam1()), 0, sp);
                    break;
                case "pop":
                    setRegister(ist.getParam1(), LoadWord(0, sp));
                    sp = sp + 4;
                    break;
                case "top":
                    setRegister(ist.getParam1(), LoadWord(0, sp));
                    break;
                case "li":
                    setRegister(ist.getParam1(), Integer.parseInt(ist.getParam2()));
                    break;
                case "mov":
                    setRegister(ist.getParam1(), getRegister(ist.getParam2()));
                    break;
                case "lw":
                    setRegister(ist.getParam1(), LoadWord(Integer.parseInt(ist.getParam2()), getRegister(ist.getParam3())));
                    break;
                case "sw":
                    StoreWord(getRegister(ist.getParam1()), Integer.parseInt(ist.getParam2()), getRegister(ist.getParam3()));
                    break;
                case "lb":
                    setRegister(ist.getParam1(), LoadBool(Integer.parseInt(ist.getParam2()), getRegister(ist.getParam3())));
                    break;
                case "sb":
                    StoreBool(getRegister(ist.getParam1()), Integer.parseInt(ist.getParam2()), getRegister(ist.getParam3()));
                    break;
                case "add":
                    setRegister(ist.getParam1(), getRegister(ist.getParam2()) + getRegister(ist.getParam3()));
                    break;
                case "addi":
                    setRegister(ist.getParam1(), getRegister(ist.getParam2()) + Integer.parseInt(ist.getParam3()));
                    break;
                case "sub":
                    setRegister(ist.getParam1(), getRegister(ist.getParam2()) - getRegister(ist.getParam3()));
                    break;
                case "subi":
                    setRegister(ist.getParam1(), getRegister(ist.getParam2()) - Integer.parseInt(ist.getParam3()));
                    break;
                case "mult":
                    setRegister(ist.getParam1(), getRegister(ist.getParam2()) * getRegister(ist.getParam3()));
                    break;
                case "multi":
                    setRegister(ist.getParam1(), getRegister(ist.getParam2()) * Integer.parseInt(ist.getParam3()));
                    break;
                case "div":
                    setRegister(ist.getParam1(), getRegister(ist.getParam2()) / getRegister(ist.getParam3()));
                    break;
                case "divi":
                    setRegister(ist.getParam1(), getRegister(ist.getParam2()) / Integer.parseInt(ist.getParam3()));
                    break;
                case "lt":
                    if (getRegister(ist.getParam2()) < getRegister(ist.getParam3())) {
                        setRegister(ist.getParam1(), 1);
                    } else {
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "lte":
                    if (getRegister(ist.getParam2()) <= getRegister(ist.getParam3())) {
                        setRegister(ist.getParam1(), 1);
                    } else {
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "gt":
                    if (getRegister(ist.getParam2()) > getRegister(ist.getParam3())) {
                        setRegister(ist.getParam1(), 1);
                    } else {
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "gte":
                    if (getRegister(ist.getParam2()) >= getRegister(ist.getParam3())) {
                        setRegister(ist.getParam1(), 1);
                    } else {
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "eq":
                    if (getRegister(ist.getParam2()) == getRegister(ist.getParam3())) {
                        setRegister(ist.getParam1(), 1);
                    } else {
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "neq":
                    if (getRegister(ist.getParam2()) != getRegister(ist.getParam3())) {
                        setRegister(ist.getParam1(), 1);
                    } else {
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "and":
                    setRegister(ist.getParam1(), getRegister(ist.getParam2()) & getRegister(ist.getParam3()));
                    break;
                case "or":
                    setRegister(ist.getParam1(), getRegister(ist.getParam2()) | getRegister(ist.getParam3()));
                    break;
                case "not":
                    // da rifare
                    if (getRegister(ist.getParam2()) == 0) {
                        setRegister(ist.getParam1(), 1);
                    } else {
                        setRegister(ist.getParam1(), 0);
                    }
                    break;
                case "neg":
                    setRegister(ist.getParam1(), -1 * getRegister(ist.getParam2()));
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
                    if (getRegister(ist.getParam1()) == getRegister(ist.getParam2())) {
                        pc = labels.get(ist.getParam3());
                    }
                    break;
                case "halt":
                    if (!debug) {
                        break;
                    }
                    try {
                        MemoryInspector(code.getInstruction(pc - 2), code.getInstruction(pc + 1));
                    } catch (Exception e) {
                        System.out.println("Something went wrong within the memory inspector. Terminating session...");
                    }
                    break;
                default:
                    break;
            }
        }

    }

}