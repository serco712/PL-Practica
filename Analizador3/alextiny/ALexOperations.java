package alextiny;

public class ALexOperations {
	
	public static class ECaracterInesperado extends RuntimeException {
	      public ECaracterInesperado(String msg) {
	          super(msg);
	      }
	}  
	
	private AnalizadorLexicoTiny alex;
	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;   
	}
	public UnidadLexica unidadIden() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.IDEN,alex.lexema());      
	}
	public UnidadLexica unidadBool() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.BOOL); 
	}
	public UnidadLexica unidadlitEnt() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LIT_ENT,alex.lexema());     
	}    
	public UnidadLexica unidadlitReal() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LIT_REAL,alex.lexema());     
	}  
	public UnidadLexica unidadInt() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.ENT);     
	}  
	public UnidadLexica unidadReal() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.REAL);     
	}   
	public UnidadLexica unidadlitCad() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(),ClaseLexica.LIT_CAD,alex.lexema());     
	}    
	public UnidadLexica unidadArroba() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EVAL); 
	} 
	public UnidadLexica unidadSuma() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.SUMA); 
	} 
	public UnidadLexica unidadResta() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.RESTA); 
	} 
	public UnidadLexica unidadMult() {
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
	public UnidadLexica unidadMenIgual() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.MEN_IGUAL); 
	} 
	public UnidadLexica unidadMayIgual() {
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
	public UnidadLexica unidadPCi() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PCI); 
	} 
	public UnidadLexica unidadComa() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.COMA); 
	}
	public UnidadLexica unidadPuntoYComa() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PCOMA); 
	} 
	public UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EOF); 
	}
	public UnidadLexica unidadDistinto() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DISTINTO); 
	} 
	public UnidadLexica unidadLlaveAp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LLAP); 
	} 
	public UnidadLexica unidadLlaveCi() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.LLCI); 
	}
	public UnidadLexica unidadExp0() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EXP0); 
	}
	public UnidadLexica unidadTrue() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TRUE); 
	} 
	public UnidadLexica unidadFalse() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.FALSE); 
	}
	public UnidadLexica unidadString() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.STRING); 
	}  
	public UnidadLexica unidadNot() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NOT); 
	} 
	public UnidadLexica unidadAnd() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.AND); 
	} 
	public UnidadLexica unidadOr() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.OR); 
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
	public UnidadLexica unidadDel() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.DELETE); 
	}
	public UnidadLexica unidadRead() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.READ); 
	}
	public UnidadLexica unidadWrite() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.WRITE); 
	}
	public UnidadLexica unidadNl() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.NL); 
	}
	public UnidadLexica unidadType() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.TYPE); 
	}
	public UnidadLexica unidadCall() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CALL); 
	}
	public UnidadLexica unidadIndireccion() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.INDIRECCION); 
	}
	public UnidadLexica unidadReferencia() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.REFERENCIA); 
	}
	public UnidadLexica unidadCorcheteAp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CAP); 
	}
	public UnidadLexica unidadCorcheteCi() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.CCI); 
	}
	public UnidadLexica unidadPunto() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.PUNTO); 
	}
	public UnidadLexica unidadFinal() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.FIN); 
	}
	public void error() {
		throw new ECaracterInesperado(alex.lexema());
	}
}
