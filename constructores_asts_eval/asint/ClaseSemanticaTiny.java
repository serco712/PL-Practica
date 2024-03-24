package asint;



public class ClaseSemanticaTiny extends SintaxisAbstractaTiny {
    public ClaseSemanticaTiny() {
        super();
    }
    public Exp mkopBin(String op, Exp opnd1, Exp opnd2) {
        switch(op) {
            case "<": return exp_menor(opnd1,opnd2);
            case "<=": return exp_menIgual(opnd1,opnd2);
            case ">": return exp_mayor(opnd1,opnd2);
            case ">=": return exp_mayIgual(opnd1,opnd2);
            case "==": return exp_igual(opnd1,opnd2);
            case "=": return exp_asig(opnd1,opnd2);
            case "*": return exp_mult(opnd1,opnd2);
            case "/": return exp_div(opnd1,opnd2);
            case "%": return exp_mod(opnd1,opnd2);
            default: throw new UnsupportedOperationException("Bad op");
        }
    }
    
    public Exp mkopUn(String op, Exp opnd) {
        switch(op) {
            case "-": return exp_menos(opnd);
            case "not": return exp_not(opnd);
            default: throw new UnsupportedOperationException("Bad op");
        }
    }
}
