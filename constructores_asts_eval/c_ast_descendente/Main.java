package c_ast_descendente;
import java.io.FileReader;
public class Main{
   public static void main(String[] args) throws Exception {
      ConstructorASTsTiny asint = new ConstructorASTsTiny(System.in);
      //asint.disable_tracing();
      System.out.println(asint.analiza());
   }
}