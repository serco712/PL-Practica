package impresion;

import java.io.FileReader;

import c_ast_descendente.ConstructorASTsTiny;
import c_ast_descendente.TokenMgrError;

public class Main {
   public static void main(String[] args) throws Exception {
	  try {
		   ConstructorASTsTiny asint = new ConstructorASTsTiny(new FileReader("C:/hlocal/PL-Practica/constructores_asts_eval/prueba.in"));
	       asint.disable_tracing();
	       asint.analiza().procesa(new Impresion());
	  }
      catch(TokenMgrError e) {
         System.out.println("ERROR_LEXICO"); 
      }
	}
}   
