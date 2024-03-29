package c_ast_descendente;

import asint.SintaxisAbstractaTiny.Prog;

import java.io.FileReader;
public class Main{
   public static void main(String[] args) throws Exception {
      try {
         ConstructorASTsTiny asint = new ConstructorASTsTiny(new FileReader("/home/sergio/Desktop/Estudios/UCM/4º/PL/PL-Practica/constructores_asts_eval/prueba.in"));
         asint.disable_tracing();
         Prog p = asint.analiza();
         p.imprime();
      }
      catch(ParseException e) {
         System.out.println("ERROR_SINTACTICO"); 
      }
      catch(TokenMgrError e) {
         System.out.println("ERROR_LEXICO"); 
      }
   }
}