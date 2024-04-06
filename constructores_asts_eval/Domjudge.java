import asint.SintaxisAbstractaTiny.Prog;
import c_ast_ascendente.AnalizadorLexicoTiny;
import c_ast_descendente.ConstructorASTsTiny;
import c_ast_descendente.SimpleCharStream;
import evaluador.Evaluador;
import impresion.Impresion;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class Domjudge {
   public static void main(String[] args) throws Exception {
        Reader input = new FileReader("C:/hlocal/PL-Practica/constructores_asts_eval/sample1d.in");
        BufferedReader br = new BufferedReader(input); //Ya tenemos el "lector"
        Prog p;
        if(br.readLine().startsWith("a")) {
            System.out.println("CONSTRUCCION AST ASCENDENTE");
            AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(br);
            c_ast_ascendente.ConstructorASTsTiny asint = new c_ast_ascendente.ConstructorASTsTiny(alex);
            p = (Prog)asint.parse().value;
        }
        else {
            System.out.println("CONSTRUCCION AST DESCENDENTE");
            c_ast_descendente.ConstructorASTsTiny asint = new c_ast_descendente.ConstructorASTsTinyDJ(br);
            asint.disable_tracing();
            p = asint.analiza();
        }

        System.out.println("IMPRESION RECURSIVA");
        new Evaluador().evalua(p);

        System.out.println("IMPRESION INTERPRETE");
        p.imprime();

        System.out.println("IMPRESION VISITANTE");
        p.procesa(new Impresion());
    }
}   