package asint;

import alex.UnidadLexica;
import alex.AnalizadorLexicoEval;
import alex.ClaseLexica;
import errors.GestionErroresEval;
import java.io.IOException;
import java.io.Reader;
import java.util.EnumSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnalizadorSintacticoEval {
	private UnidadLexica anticipo; // token adelantado
	private AnalizadorLexicoEval alex; // analizador léxico
	private GestionErroresEval errores; // gestor de errores
	private Set<ClaseLexica> esperados; // clases léxicas esperadas

	public AnalizadorSintacticoEval(Reader input) {
		// se crea el gestor de errores
		errores = new GestionErroresEval();
		// se crea el analizador léxico
		alex = new AnalizadorLexicoEval(input);
		// se fija el gestor de errores en el analizador léxico
		// (debe añadirse el método 'fijaGestionErrores' a
		// dicho analizador)
		alex.fijaGestionErrores(errores);
		// se crea el conjunto de clases léxicas esperadas
		// (estará incializado a vacío)
		esperados = EnumSet.noneOf(ClaseLexica.class);
		// Se lee el primer token adelantado
		sigToken();
	}

	private void analiza() {
		programa();
		empareja(ClaseLexica.EOF);
	}

	private void programa() {
		bloque();
	}

	private void bloque() {
		empareja(ClaseLexica.LLAVE_AP);
		declaraciones();
		instrucciones();
		empareja(ClaseLexica.LLAVE_CIERRE);
	}

	private void declaraciones() {
		switch (anticipo.clase()) {
			case BOOL:
			case ENT:
			case REAL:
				lista_declaraciones();
				empareja(ClaseLexica.FINAL);
				break;
			default:
				esperados(ClaseLexica.BOOL, ClaseLexica.ENT, ClaseLexica.REAL);
				break;
		}
	}

	private void lista_declaraciones() {
		declaracion();
		r_lista_declaraciones();
	}

	private void r_lista_declaraciones() {
      switch (anticipo.clase()) {
         case PCOMA:
         	empareja(ClaseLexica.PCOMA);
         	declaracion();
         	r_lista_declaraciones();
         default:
         	esperados(ClaseLexica.PCOMA);
         	break;
      }
   }
   
   private void declaracion() {
      tipo();
      empareja(ClaseLexica.IDEN);
   }
   
   private void tipo() {
      switch(anticipo.clase()) {
         case BOOL:
         	empareja(ClaseLexica.BOOL);
         case ENT:
         	empar
      }
   }
   

	private void expresion() {
		empareja(ClaseLexica.EVALUA);
		E0();
	}

	private void rlista_decs() {
		switch (anticipo.clase()) {
		case COMA:
			empareja(ClaseLexica.COMA);
			declaracion();
			rlista_decs();
			break;
		default:
			esperados(ClaseLexica.COMA);
			break;
		}
	}

	private void instrucciones() {
		switch (anticipo.clase()) {
		case ARROBA:
			lista_instrucciones();
			break;
		default:
			esperados(ClaseLexica.ARROBA);
			break;
		}
	}

	private void lista_instrucciones() {
		instruccion();
		rlista_instrucciones();
	}

	private void rlista_instrucciones() {
		switch (anticipo.clase()) {
		case COMA:
			empareja(ClaseLexica.PCOMA);
			instruccion();
			rlista_instrucciones();
			break;
		default:
			esperados(ClaseLexica.PCOMA);
			break;
		}
	}

	private void instruccion() {
		eval();
	}

	private void eval() {
		empareja(ClaseLexica.ARROBA);
		E0();
	}

	private void E0() {
		switch (anticipo.clase()) {
		case IDEN:
			empareja(ClaseLexica.IDEN);
			empareja(ClaseLexica.IGUAL);
			E0();
			break;
		default:
			E1();
			break;
		}

	}

	private void E1() {
		E2();
		RE1();
	}

	private void RE1() {
		switch (anticipo.clase()) {
		case SUMA, RESTA:
			OP1();
			E2();
			RE1();
			break;
		default:
			esperados(ClaseLexica.SUMA, ClaseLexica.RESTA);
			break;
		}
	}

	private void E2() {
		switch (anticipo.clase()) {
		case ENT:
			empareja(ClaseLexica.ENT);
			break;
		case REAL:
			empareja(ClaseLexica.REAL);
			break;
		case IDEN:
			empareja(ClaseLexica.IDEN);
			break;
		case PAP:
			empareja(ClaseLexica.PAP);
			E0();
			empareja(ClaseLexica.PCIERRE);
			break;
		default:
			esperados(ClaseLexica.ENT, ClaseLexica.REAL, ClaseLexica.IDEN, ClaseLexica.PAP);
			error();
		}
	}

	

	private void OP1() {
		switch (anticipo.clase()) {
		case MAYOR:
			empareja(ClaseLexica.MAYOR);
			break;
		case MENOR:
			empareja(ClaseLexica.MENOR);
			break;
		case MAY_IGUAL:
			empareja(ClaseLexica.MAY_IGUAL);
			break;
		case MEN_IGUAL:
			empareja(ClaseLexica.MEN_IGUAL);
			break;
		case IGUAL:
			empareja(ClaseLexica.IGUAL);
			break;
		case DISTINTO:
			empareja(ClaseLexica.DISTINTO);
			break;
		default:
			esperados(ClaseLexica.MAYOR, ClaseLexica.MENOR, ClaseLexica.MAY_IGUAL, ClaseLexica.MEN_IGUAL,
					ClaseLexica.IGUAL, ClaseLexica.DISTINTO);
			error();
		}
	}
	private void OP4() {
		switch (anticipo.clase()) {
		case MULT:
			empareja(ClaseLexica.MULT);
			break;
		case DIV:
			empareja(ClaseLexica.DIV);
			break;
		default:
			esperados(ClaseLexica.MULT, ClaseLexica.DIV);
			error();
		}
	}
	private void OP5() {
		switch (anticipo.clase()) {
		case SUMA:
			empareja(ClaseLexica.SUMA);
			break;
		case RESTA:
			empareja(ClaseLexica.RESTA);
			break;
		default:
			esperados(ClaseLexica.SUMA, ClaseLexica.RESTA);
			error();
		}
	}
	private void esperados(ClaseLexica... esperadas) {
		for (ClaseLexica c : esperadas) {
			esperados.add(c);
		}
	}

	private void empareja(ClaseLexica claseEsperada) {
		if (anticipo.clase() == claseEsperada)
			sigToken();
		else {
			esperados(claseEsperada);
			error();
		}
	}

	private void sigToken() {
		try {
			anticipo = alex.yylex();
			esperados.clear();
		} catch (IOException e) {
			errores.errorFatal(e);
		}
	}

	private void error() {
		errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), esperados);
	}

}
