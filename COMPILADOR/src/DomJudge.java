import asint.SintaxisAbstractaTiny.Prog;
import c_ast_ascendente.AnalizadorLexicoTiny;
import c_ast_descendente.ConstructorASTsTiny;
import c_ast_descendente.ParseException;
import c_ast_descendente.SimpleCharStream;
import c_ast_descendente.TokenMgrError;
import errors.GestionErroresTiny.ErrorLexico;
import errors.GestionErroresTiny.ErrorSintactico;
import evaluador.Evaluador;
import generacion.GeneracionCodigo;
import impresion.Impresion;
import maquinap.MaquinaP;
import vinculacion.Vinculacion;
import tipado.Comprobacion_tipos;
import espacio.Espacio;
import etiquetado.Etiquetado;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;

public class DomJudge {
   public static void main(String[] args) throws Exception {
	   try {
	        Reader input = new InputStreamReader(new FileInputStream("/home/sergio/Downloads/casos_prueba_correctos(1)/casos_basicos/02basico_d.in"));
	        BufferedReader br = new BufferedReader(input); //Ya tenemos el "lector"
	        Prog p;
	        if(br.readLine().startsWith("a")) {
	            System.out.println("CONSTRUCCION AST ASCENDENTE");
	            AnalizadorLexicoTiny alex = new AnalizadorLexicoTiny(br);
	            c_ast_ascendente.ConstructorASTsTiny asint = new c_ast_ascendente.ConstructorASTsTinyDJ(alex);
	            p = (Prog)asint.debug_parse().value;
	        }
	        else {
	            System.out.println("CONSTRUCCION AST DESCENDENTE");
	            c_ast_descendente.ConstructorASTsTiny asint = new c_ast_descendente.ConstructorASTsTinyDJ(br);
	            asint.disable_tracing();
	            p = asint.analiza();
	        }

			(new Vinculacion()).vincula(p);

			(new Comprobacion_tipos()).procesa(p);

			(new Espacio()).asigEspacio(p);

			MaquinaP mp = new MaquinaP(1000, 1000, 1000, 5);

			(new Etiquetado(mp)).procesa(p);

			(new GeneracionCodigo(mp)).procesa(p);

			mp.ejecuta();
	   }
	   catch(TokenMgrError e) {
	      System.out.println("ERROR_LEXICO"); 
	   }
	   catch(ParseException e) {
	      System.out.println("ERROR_SINTACTICO"); 
	   }
	   catch(ErrorLexico e) {
	      System.out.println("ERROR_LEXICO"); 
	   }
	   catch(ErrorSintactico e) {
	      System.out.println("ERROR_SINTACTICO"); 
	   }
    }
}   
