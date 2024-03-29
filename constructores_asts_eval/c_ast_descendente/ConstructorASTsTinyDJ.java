package c_ast_descendente;

import java.io.InputStream;
import java.io.Reader;


public class ConstructorASTsTinyDJ extends ConstructorASTsTiny {
    private void imprime(Token t) {
        switch(t.kind) {
            case bool: System.out.println("<bool>"); break;
            case INT: System.out.println("<int>"); break;
            case real: System.out.println("<real>"); break;
            case string: System.out.println("<string>"); break;
            case and: System.out.println("<and>"); break;
            case or: System.out.println("<or>"); break;
            case not: System.out.println("<not>"); break;
            case TRUE: System.out.println("<true>"); break;
            case FALSE: System.out.println("<false>"); break;
            case NULL: System.out.println("<null>"); break;
            case proc: System.out.println("<proc>"); break;
            case IF: System.out.println("<if>"); break;
            case ELSE: System.out.println("<else>"); break;
            case WHILE: System.out.println("<while>"); break;
            case struct: System.out.println("<struct>"); break;
            case NEW: System.out.println("<new>"); break;
            case delete: System.out.println("<delete>"); break;
            case read: System.out.println("<read>"); break;
            case write: System.out.println("<write>"); break;
            case nl: System.out.println("<nl>"); break;
            case type: System.out.println("<type>"); break;
            case call: System.out.println("<call>"); break;
            case operadorSuma: System.out.println("+"); break;
            case operadorResta: System.out.println("-"); break;
            case operadorMul: System.out.println("*"); break;
            case operadorDiv: System.out.println("/"); break;
            case operadorMod: System.out.println("%"); break;
            case operadorMenor: System.out.println("<"); break;
            case operadorMayor: System.out.println(">"); break;
            case operadorMenIgual: System.out.println("<="); break;
            case operadorMayIgual: System.out.println(">="); break;
            case operadorIgual: System.out.println("=="); break;
            case operadorAsig: System.out.println("="); break;
            case operadorDistinto: System.out.println("!="); break;
            case punto: System.out.println("."); break;
            case puntoYComa: System.out.println(";"); break;
            case coma: System.out.println(","); break;
            case parentesisAp: System.out.println("("); break;
            case parentesisCi: System.out.println(")"); break;
            case arroba: System.out.println("@"); break;
            case llaveAp: System.out.println("{"); break;
            case llaveCi: System.out.println("}"); break;
            case corcheteAp: System.out.println("["); break;
            case corcheteCi: System.out.println("]"); break;
            case indireccion: System.out.println("^"); break;
            case FINAL: System.out.println("&&"); break;
            case porReferencia: System.out.println("&"); break;
            case EOF: System.out.println("<EOF>"); break;
            default: System.out.println(t.image);
        }
    }
    
    public ConstructorASTsTinyDJ(Reader in) {
        super(in);
        //disable_tracing();
    }

    @Override
    protected void trace_token(Token t, String where) {
        imprime(t);
    }
}