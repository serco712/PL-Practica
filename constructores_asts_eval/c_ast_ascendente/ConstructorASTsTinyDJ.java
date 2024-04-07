package c_ast_ascendente;

import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;

public class ConstructorASTsTinyDJ extends ConstructorASTsTiny {
    public void debug_message(String msg) {}
    public void debug_shift(Symbol token) {
       System.out.println(token.value);
    }
    public ConstructorASTsTinyDJ(Scanner alex) {
        super(alex);
    }
}
