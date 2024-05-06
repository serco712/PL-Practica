package c_ast_ascendente;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import errors.GestionErroresTiny.ErrorLexico;
import errors.GestionErroresTiny.ErrorSintactico;

public class Main {
   public static void main(String[] args) throws Exception {
         Reader input = new InputStreamReader(System.in);
	 AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(input);
	 ConstructorASTsTiny asint = new ConstructorASTsTinyDJ(alex);
	 //asint.setScanner(alex);
	 try {    
        asint.debug_parse();
     }
     catch(ErrorLexico e) {
        System.out.println("ERROR_LEXICO"); 
     }
     catch(ErrorSintactico e) {
        System.out.println("ERROR_SINTACTICO"); 
     }
 }
}   
   
