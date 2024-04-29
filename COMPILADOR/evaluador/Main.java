package evaluador;


import c_ast_descendente.ConstructorASTsTiny;
import c_ast_descendente.ParseException;
import c_ast_descendente.TokenMgrError;

import java.io.FileReader;
import asint.SintaxisAbstractaTiny.Prog;

public class Main {
   public static void main(String[] args) throws Exception {
	   try {
	         ConstructorASTsTiny asint = new ConstructorASTsTiny(new FileReader("C:/hlocal/PL-Practica/constructores_asts_eval/prueba.in"));
	         asint.disable_tracing();
	         Prog prog = asint.analiza();
	         new Evaluador().evalua(prog);
	   }
	      catch(ParseException e) {
	         System.out.println("ERROR_SINTACTICO"); 
	      }
	      catch(TokenMgrError e) {
	         System.out.println("ERROR_LEXICO"); 
	      }
	   }
}   
