package c_ast_ascendente;

import errors.GestionErroresTiny;

public class ALexOperations {
	
	 
	private GestionErroresTiny errores;
	
	private AnalizadorLexicoTiny alex;
	public ALexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex; 
		errores = new GestionErroresTiny();
	}
	public UnidadLexica unidadIden() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.IDEN,alex.lexema());      
	}
	public UnidadLexica unidadBool() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.BOOL,"<bool>"); 
	}
	public UnidadLexica unidadlitEnt() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.LIT_ENT,alex.lexema());     
	}    
	public UnidadLexica unidadlitReal() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.LIT_REAL,alex.lexema());     
	}  
	public UnidadLexica unidadInt() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.ENT,"<int>");     
	}  
	public UnidadLexica unidadReal() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.REAL,"<real>");     
	}   
	public UnidadLexica unidadlitCad() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.LIT_CAD,alex.lexema());     
	}    
	public UnidadLexica unidadArroba() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.EVAL,"@"); 
	} 
	public UnidadLexica unidadSuma() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.SUMA,"+"); 
	} 
	public UnidadLexica unidadResta() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.RESTA,"-"); 
	} 
	public UnidadLexica unidadMult() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MUL,"*"); 
	} 
	public UnidadLexica unidadDiv() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.DIV,"/"); 
	} 
	public UnidadLexica unidadMod() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MOD,"%"); 
	} 
	public UnidadLexica unidadMenor() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MENOR,"<"); 
	} 
	public UnidadLexica unidadMayor() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MAYOR,">"); 
	} 
	public UnidadLexica unidadMenIgual() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MEN_IGUAL,"<="); 
	} 
	public UnidadLexica unidadMayIgual() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.MAY_IGUAL,">="); 
	} 
	public UnidadLexica unidadIgual() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.IGUAL,"=="); 
	} 
	public UnidadLexica unidadAsig() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.ASIG,"="); 
	} 
	public UnidadLexica unidadPAp() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PAP, "("); 
	} 
	public UnidadLexica unidadPCi() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PCI, ")"); 
	} 
	public UnidadLexica unidadComa() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.COMA, ","); 
	}
	public UnidadLexica unidadPuntoYComa() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PCOMA, ";"); 
	} 
	public UnidadLexica unidadEof() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.EOF, "<EOF>"); 
	}
	public UnidadLexica unidadDistinto() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.DISTINTO, "!="); 
	} 
	public UnidadLexica unidadLlaveAp() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.LLAP, "{"); 
	} 
	public UnidadLexica unidadLlaveCi() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.LLCI, "}"); 
	}
	public UnidadLexica unidadExp0() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.LIT_REAL, alex.lexema()); 
	}
	public UnidadLexica unidadTrue() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.TRUE, "<true>");
	}
	public UnidadLexica unidadFalse() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.TRUE, "<false>");
	}
	public UnidadLexica unidadString() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.STRING,"<string>"); 
	}  
	public UnidadLexica unidadNot() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.NOT, "<not>"); 
	} 
	public UnidadLexica unidadAnd() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.AND, "<and>"); 
	} 
	public UnidadLexica unidadOr() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.OR, "<or>"); 
	} 
	public UnidadLexica unidadNull() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.NULL, "<null>"); 
	}
	public UnidadLexica unidadProc() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PROC, "<proc>"); 
	}
	public UnidadLexica unidadIf() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.IF, "<if>"); 
	}
	public UnidadLexica unidadElse() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.ELSE, "<else>"); 
	}
	public UnidadLexica unidadWhile() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.WHILE, "<while>"); 
	}
	public UnidadLexica unidadStruct() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.STRUCT, "<struct>"); 
	}
	public UnidadLexica unidadNew() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.NEW, "<new>"); 
	}
	public UnidadLexica unidadDel() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.DELETE, "<delete>"); 
	}
	public UnidadLexica unidadRead() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.READ, "<read>"); 
	}
	public UnidadLexica unidadWrite() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.WRITE, "<write>"); 
	}
	public UnidadLexica unidadNl() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.NL,"<nl>"); 
	}
	public UnidadLexica unidadType() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.TYPE,"<type>"); 
	}
	public UnidadLexica unidadCall() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.CALL,"<call>"); 
	}
	public UnidadLexica unidadIndireccion() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.INDIRECCION,"^"); 
	}
	public UnidadLexica unidadReferencia() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.REFERENCIA,"&"); 
	}
	public UnidadLexica unidadCorcheteAp() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.CAP,"["); 
	}
	public UnidadLexica unidadCorcheteCi() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.CCI,"]"); 
	}
	public UnidadLexica unidadPunto() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.PUNTO,"."); 
	}
	public UnidadLexica unidadFinal() {
		return new UnidadLexica(alex.fila(), alex.columna(),ClaseLexica.FIN,"&&"); 
	}
	public void error() {
		errores.errorLexico(alex.fila(), alex.columna(), alex.lexema());
		
	}
}