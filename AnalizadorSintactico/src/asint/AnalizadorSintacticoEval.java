package asint;

import alex.UnidadLexica;
import alex.AnalizadorLexicoEval;
import alex.ClaseLexica;
import errors.GestionErroresEval;
import java.io.IOException;
import java.io.Reader;
import java.util.EnumSet;
import java.util.Set;

public class AnalizadorSintacticoEval {
	private UnidadLexica anticipo; // token adelantado
	private AnalizadorLexicoEval alex; // analizador léxico
	private GestionErroresEval errores; // gestor de errores
	private Set<ClaseLexica> esperados; // clases léxicas esperadas

	public AnalizadorSintacticoEval(Reader input) throws IOException {
		// se crea el gestor de errores
		errores = new GestionErroresEval();
		// se crea el analizador léxico
		alex = new AnalizadorLexicoEval(input,errores);
		// se fija el gestor de errores en el analizador léxico
		// (debe añadirse el método 'fijaGestionErrores' a
		// dicho analizador)
		//alex.fijaGestionErrores(errores);
		// se crea el conjunto de clases léxicas esperadas
		// (estará incializado a vacío)
		esperados = EnumSet.noneOf(ClaseLexica.class);
		// Se lee el primer token adelantado
		sigToken();
	}

	public void analiza() {
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
			case BOOL: case ENT: case REAL:
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
		rlista_declaraciones();
	}

	private void rlista_declaraciones() {
		switch (anticipo.clase()) {
			case PCOMA:
				empareja(ClaseLexica.PCOMA);
				declaracion();
				rlista_declaraciones();
				break;
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
				break;
			case ENT:
				empareja(ClaseLexica.ENT);
				break;
			case REAL:
				empareja(ClaseLexica.REAL);
				break;
			default:
				esperados(ClaseLexica.BOOL, ClaseLexica.ENT, ClaseLexica.REAL);
				error();
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
		case PCOMA:
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
		E1();
		RE0();
	}
	
	private void RE0() {
		switch (anticipo.clase()) {
		case ASIG:
			empareja(ClaseLexica.ASIG);
			E0();
			break;
		default:
			esperados(ClaseLexica.ASIG);
			break;
		}
	}

	private void E1() {
		E2();
		RE1();
	}

	private void RE1() {
		switch (anticipo.clase()) {
		case MAY_IGUAL: case MEN_IGUAL: case MAYOR: case MENOR: case IGUAL: case DISTINTO:
			OP1();
			E2();
			RE1();
			break;
		default:
			esperados(ClaseLexica.MAY_IGUAL, ClaseLexica.MEN_IGUAL,ClaseLexica.IGUAL,ClaseLexica.DISTINTO,ClaseLexica.MAYOR,ClaseLexica.MENOR);
			break;
		}
	}

	private void E2() {
		E3();
		RE2();
		RE2PRIMA();
	}

	private void RE2() {
		switch (anticipo.clase()) {
		case RESTA:
			empareja(ClaseLexica.RESTA);
			E3();
			break;
		default:
			esperados(ClaseLexica.RESTA);
			break;
		}
	}

	private void RE2PRIMA() {
		switch (anticipo.clase()) {
		case SUMA:
			empareja(ClaseLexica.SUMA);
			E3();
			RE2PRIMA();
			break;
		default:
			esperados(ClaseLexica.SUMA);
			break;
		}
	}
	
	private void E3() {
		E4();
		RE3();
	}
	
	private void RE3() {
		switch (anticipo.clase()) {
		case AND:
			empareja(ClaseLexica.AND);
			E3();
			break;
		case OR:
			empareja(ClaseLexica.OR);
			E4();
		default:
			esperados(ClaseLexica.AND,ClaseLexica.OR);
			break;
		}
	}
	
	private void E4() {
		E5();
		RE4();
	}
	
	private void RE4() {
		switch (anticipo.clase()) {
		case MULT: case DIV:
			OP4();
			E5();
			RE4();
			break;
		default:
			esperados(ClaseLexica.MULT,ClaseLexica.DIV);
			break;
		}
	}
	
	private void E5() {
		switch (anticipo.clase()) {
		case SUMA: case RESTA:
			OP5();
			E6();
			break;
		case TRUE: case FALSE: case LIT_ENT: case LIT_REAL: case PAP:
			E6();
			break;
		default:
			esperados(ClaseLexica.SUMA,ClaseLexica.RESTA,ClaseLexica.TRUE,
			ClaseLexica.FALSE,ClaseLexica.LIT_ENT,ClaseLexica.LIT_REAL,ClaseLexica.PAP);
			error();
		}
	}
	
	private void E6() {
		switch (anticipo.clase()) {
		case TRUE:
			empareja(ClaseLexica.TRUE);
			break;
		case FALSE:
			empareja(ClaseLexica.FALSE);
			break;
		case LIT_ENT:
			empareja(ClaseLexica.LIT_ENT);
			break;
		case LIT_REAL:
			empareja(ClaseLexica.LIT_REAL);
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
			esperados(ClaseLexica.TRUE, ClaseLexica.FALSE, ClaseLexica.LIT_ENT, ClaseLexica.LIT_REAL,
					ClaseLexica.IDEN, ClaseLexica.PAP);
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
			anticipo = alex.sigToken();
			esperados.clear();
		} catch (IOException e) {
			errores.errorFatal(e);
		}
	}

	private void error() {
		errores.errorSintactico(anticipo.fila(), anticipo.columna(), anticipo.clase(), esperados);
	}

}
