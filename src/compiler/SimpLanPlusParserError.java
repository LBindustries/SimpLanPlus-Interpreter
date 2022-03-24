package compiler;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

import java.util.ArrayList;

public class SimpLanPlusParserError extends BaseErrorListener {
    public static final SimpLanPlusParserError INSTANCE = new SimpLanPlusParserError();

    ArrayList<String> err_list;

    public SimpLanPlusParserError() {
        this.err_list = new ArrayList<String>();
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        this.err_list.add("Errore in posizione: " + line + ":" + charPositionInLine + " " + msg);
    }

    @Override
    public String toString() {
        String res = "Errori di compilazione: \n";
        for (String event : this.err_list) {
            res += event + "\n";
        }
        return res;
    }
}
