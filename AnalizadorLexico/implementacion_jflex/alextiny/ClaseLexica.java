package alextiny;

public enum ClaseLexica {
	BOOL("<bool>"),ENT("<int>"),REAL("<real>"),STRING("<string>"),AND("<and>"),OR("<or>"),NOT("<not>"),LIT_ENT,LIT_REAL,LIT_CAD,IDEN,SUMA("+"),
	RESTA("-"),MUL("*"),DIV("/"),MOD("%"),MENOR("<"),MAYOR(">"),MEN_IGUAL("<="),MAY_IGUAL(">="),IGUAL("=="),
	ASIG("="),PAP("("),PCI(")"),COMA(","),PCOMA(";"),EVAL("@"),EOF("EOF"),DISTINTO("!="),TRUE("<true>"),
	FALSE("<false>"),LLAP("{"),LLCI("}"),NULL("<null>"),PROC("<proc>"),IF("<if>"),ELSE("<else>"),WHILE("<while>"),
	STRUCT("<struct>"),NEW("<new>"),DELETE("<delete>"),READ("<read>"),WRITE("<write>"),NL("<nl>"),TYPE("<type>"),
	CALL("<call>"),INDIRECCION("^"),REFERENCIA("&"),CAP("["),CCI("]"),PUNTO("."),FIN("&&");
	
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
