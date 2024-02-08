package tiny;

public class ALexOperations {
	private AnalizadorLexicoTiny alex;
	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;   
	}
	public UnidadLexica unidadId() {
		switch(alex.lexema()) {
		case "true":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TRUE);
		case "false":  
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.FALSE);
		case "and":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.AND);
		case "or":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.OR);
		case "not":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NOT);
		case "null":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NULL);
		case "proc":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PROC);
		case "if":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IF);
		case "else":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ELSE);
		case "while":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WHILE);
		case "new":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NEW);
		case "delete":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DELETE);
		case "read":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.READ);
		case "write":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WRITE);
		case "nl":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NL);
		case "type":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TYPE);
		case "call":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CALL);
		case "int":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ENT);
		case "real":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.REAL);
		case "bool":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.BOOL);
		case "string":
			return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.STRING);
		default:    
			return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.IDEN,alex.lexema());      
		}
	}
	public UnidadLexica unidadLitBool() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LIT_BOOL,alex.lexema()); 
	}
	public UnidadLexica unidadLitEnt() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LIT_ENT,alex.lexema());     
	}    
	public UnidadLexica unidadLitReal() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LIT_REAL,alex.lexema());     
	}   
	public UnidadLexica unidadLitCad() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LIT_CAD,alex.lexema());     
	}    
	public UnidadLexica unidadEval() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EVAL); 
	} 
	public UnidadLexica unidadSuma() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.SUMA); 
	} 
	public UnidadLexica unidadResta() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.RESTA); 
	} 
	public UnidadLexica unidadMul() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MUL); 
	} 
	public UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DIV); 
	} 
	public UnidadLexica unidadMod() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MOD); 
	} 
	public UnidadLexica unidadMenor() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MENOR); 
	} 
	public UnidadLexica unidadMayor() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MAYOR); 
	} 
	public UnidadLexica unidadMenorIgual() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MEN_IGUAL); 
	} 
	public UnidadLexica unidadMayorIgual() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MAY_IGUAL); 
	} 
	public UnidadLexica unidadIgual() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IGUAL); 
	} 
	public UnidadLexica unidadAsig() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ASIG); 
	} 
	public UnidadLexica unidadPAp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PAP); 
	} 
	public UnidadLexica unidadPCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PCI); 
	} 
	public UnidadLexica unidadComa() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.COMA); 
	}
	public UnidadLexica unidadPuntoComa() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PCOMA); 
	} 
	public UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EOF); 
	}
	public UnidadLexica unidadDistinto() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DISTINTO); 
	} 
	public UnidadLexica unidadLlAp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LLAP); 
	} 
	public UnidadLexica unidadLlCi() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LLCI); 
	}
	public UnidadLexica unidadExp0() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EXP0); 
	}
	/*public UnidadLexica unidadTrue() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TRUE); 
	} 
	public UnidadLexica unidadFalse() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.FALSE); 
	} 
	public UnidadLexica unidadNull() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NULL); 
	}
	public UnidadLexica unidadProc() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PROC); 
	}
	public UnidadLexica unidadIf() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.IF); 
	}
	public UnidadLexica unidadElse() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ELSE); 
	}
	public UnidadLexica unidadWhile() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WHILE); 
	}
	public UnidadLexica unidadStruct() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.STRUCT); 
	}
	public UnidadLexica unidadNew() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NEW); 
	}
	public UnidadLexica unidadDelete() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DELETE); 
	}
	public UnidadLexica unidadRead() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.READ); 
	}
	public UnidadLexica unidadWrite() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WRITE); 
	}
	public UnidadLexica unidadNL() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NL); 
	}
	public UnidadLexica unidadType() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TYPE); 
	}
	public UnidadLexica unidadCall() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CALL); 
	}
	*/
	public UnidadLexica unidadIndireccion() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.INDIRECCION); 
	}
	public UnidadLexica unidadReferencia() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.REFERENCIA); 
	}
	public UnidadLexica unidadCAp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CAP); 
	}
	public UnidadLexica unidadCCi() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CCI); 
	}
	public UnidadLexica unidadPunto() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PUNTO); 
	}
	public UnidadLexica unidadFin() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.FIN); 
	}
	public void error() {
		System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
	}
}
