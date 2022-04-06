package compiler;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

public class SimpLanPlusParserError extends BaseErrorListener {
    public static final SimpLanPlusParserError INSTANCE = new SimpLanPlusParserError();

    ArrayList<String> err_list;

    public SimpLanPlusParserError() {
        this.err_list = new ArrayList<String>();
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        this.err_list.add("An error occurred at " + line + ", character " + charPositionInLine + ":" + msg);
    }

    @Override
    public String toString() {
        String res = "";
        for (String event : this.err_list) {
            res += event + "\n";
        }
        return res;
    }

    public void dumpToFile(String filename) throws IOException {
        BufferedWriter wr = new BufferedWriter(new FileWriter(filename));
        wr.write(this.toString());
        wr.close();
    }
}
