package alex;

public enum ClaseLexica {
 BOOL("<bool>"), ENT("<int>"), REAL("<real>"), AND("<and>"), OR("<or>"), NOT("<not>"), LIT_ENT, LIT_REAL,
 IDEN, SUMA("+"), RESTA("-"), MULT("*"), DIV("/"), MENOR("<"), MAYOR(">"), MEN_IGUAL("<="), MAY_IGUAL("<="), 
 IGUAL("=="), ASIG("="), PAP("("), PCIERRE(")"), COMA(","), PCOMA(";"), ARROBA("@"), EOF("EOF"), DISTINTO("!="), 
 TRUE("<true>"), FALSE("<false>"), LLAVE_AP("{"), LLAVE_CIERRE("}"), FINAL("&&");
 
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
