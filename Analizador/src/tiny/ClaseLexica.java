package tiny;

public enum ClaseLexica {
	BOOL,ENT,REAL,STRING,AND("<and>"),OR("<or>"),NOT("<not>"),LIT_BOOL,LIT_ENT,LIT_REAL,LIT_CAD,IDEN,SUMA("+"),
	RESTA("-"),MUL("*"),DIV("/"),MOD("%"),MENOR("<"),MAYOR(">"),MEN_IGUAL("<="),MAY_IGUAL(">="),IGUAL("=="),
	ASIG("="),PAP("("),PCI(")"),COMA(","),PCOMA(";"),EVAL("<eval>"),EOF("EOF"),DISTINTO("!="),TRUE("<true>"),
	FALSE("<false>"),LLAP("{"),LLCI("}"),EXP0,NULL("<null>"),PROC("<proc>"),IF("<if>"),ELSE("<else>"),WHILE("<while>"),
	STRUCT("<struct>"),NEW("<new>"),DELETE("<delete>"),READ("<read>"),WRITE("<write>"),NL("NL"),TYPE("type"),
	CALL("call"),INDIRECCION("^"),REFERENCIA("&"),CAP("["),CCI("]"),PUNTO("."),FIN("&&");
	
	private String image;
	public String getImage() {
	     return image;
	 }
	 private ClaseLexica() {
	     image = toString();
	 }
	 private ClaseLexica(String image) {
	    this.image = image;  
	 }

}
