package impresion;

import asint.ProcesamientoDef;
import asint.SintaxisAbstractaTiny.Prog;
import asint.SintaxisAbstractaTiny.Si_decs;
import asint.SintaxisAbstractaTiny.Si_inst;
import asint.SintaxisAbstractaTiny.Si_pformal;
import asint.SintaxisAbstractaTiny.Si_preales;
import asint.SintaxisAbstractaTiny.Blo;
import asint.SintaxisAbstractaTiny.Decs;
import asint.SintaxisAbstractaTiny.LDecs;
import asint.SintaxisAbstractaTiny.LVar;
import asint.SintaxisAbstractaTiny.Muchas_decs;
import asint.SintaxisAbstractaTiny.Muchas_exp;
import asint.SintaxisAbstractaTiny.Muchas_inst;
import asint.SintaxisAbstractaTiny.Muchas_var;
import asint.SintaxisAbstractaTiny.Muchos_pformal;
import asint.SintaxisAbstractaTiny.No_decs;
import asint.SintaxisAbstractaTiny.No_inst;
import asint.SintaxisAbstractaTiny.No_pformal;
import asint.SintaxisAbstractaTiny.No_preales;
import asint.SintaxisAbstractaTiny.Var;
import asint.SintaxisAbstractaTiny.Dec;
import asint.SintaxisAbstractaTiny.Dec_proc;
import asint.SintaxisAbstractaTiny.Dec_simple;
import asint.SintaxisAbstractaTiny.Dec_type;
import asint.SintaxisAbstractaTiny.Tipo;
import asint.SintaxisAbstractaTiny.Tipo_array;
import asint.SintaxisAbstractaTiny.Tipo_bool;
import asint.SintaxisAbstractaTiny.Tipo_ident;
import asint.SintaxisAbstractaTiny.Tipo_int;
import asint.SintaxisAbstractaTiny.Tipo_punt;
import asint.SintaxisAbstractaTiny.Tipo_real;
import asint.SintaxisAbstractaTiny.Tipo_string;
import asint.SintaxisAbstractaTiny.Tipo_struct;
import asint.SintaxisAbstractaTiny.Un_pformal;
import asint.SintaxisAbstractaTiny.Una_dec;
import asint.SintaxisAbstractaTiny.Una_exp;
import asint.SintaxisAbstractaTiny.Una_inst;
import asint.SintaxisAbstractaTiny.Una_var;
import asint.SintaxisAbstractaTiny.Insts;
import asint.SintaxisAbstractaTiny.LInst;
import asint.SintaxisAbstractaTiny.PFmls;
import asint.SintaxisAbstractaTiny.LPFml;
import asint.SintaxisAbstractaTiny.PFml;
import asint.SintaxisAbstractaTiny.PReales;
import asint.SintaxisAbstractaTiny.Pformal_noref;
import asint.SintaxisAbstractaTiny.Pformal_ref;
import asint.SintaxisAbstractaTiny.LPReal;
import asint.SintaxisAbstractaTiny.Inst;
import asint.SintaxisAbstractaTiny.Inst_blo;
import asint.SintaxisAbstractaTiny.Inst_call;
import asint.SintaxisAbstractaTiny.Inst_delete;
import asint.SintaxisAbstractaTiny.Inst_else;
import asint.SintaxisAbstractaTiny.Inst_eval;
import asint.SintaxisAbstractaTiny.Inst_if;
import asint.SintaxisAbstractaTiny.Inst_new;
import asint.SintaxisAbstractaTiny.Inst_nl;
import asint.SintaxisAbstractaTiny.Inst_read;
import asint.SintaxisAbstractaTiny.Inst_while;
import asint.SintaxisAbstractaTiny.Inst_write;
import asint.SintaxisAbstractaTiny.Exp;
import asint.SintaxisAbstractaTiny.Exp_and;
import asint.SintaxisAbstractaTiny.Exp_asig;
import asint.SintaxisAbstractaTiny.Exp_dist;
import asint.SintaxisAbstractaTiny.Exp_div;
import asint.SintaxisAbstractaTiny.Exp_false;
import asint.SintaxisAbstractaTiny.Exp_iden;
import asint.SintaxisAbstractaTiny.Exp_igual;
import asint.SintaxisAbstractaTiny.Exp_index;
import asint.SintaxisAbstractaTiny.Exp_indir;
import asint.SintaxisAbstractaTiny.Exp_litCadena;
import asint.SintaxisAbstractaTiny.Exp_litEnt;
import asint.SintaxisAbstractaTiny.Exp_litReal;
import asint.SintaxisAbstractaTiny.Exp_mayIgual;
import asint.SintaxisAbstractaTiny.Exp_mayor;
import asint.SintaxisAbstractaTiny.Exp_menIgual;
import asint.SintaxisAbstractaTiny.Exp_menor;
import asint.SintaxisAbstractaTiny.Exp_menos;
import asint.SintaxisAbstractaTiny.Exp_mod;
import asint.SintaxisAbstractaTiny.Exp_mult;
import asint.SintaxisAbstractaTiny.Exp_not;
import asint.SintaxisAbstractaTiny.Exp_null;
import asint.SintaxisAbstractaTiny.Exp_or;
import asint.SintaxisAbstractaTiny.Exp_reg;
import asint.SintaxisAbstractaTiny.Exp_resta;
import asint.SintaxisAbstractaTiny.Exp_suma;
import asint.SintaxisAbstractaTiny.Exp_true;

public class Impresion extends ProcesamientoDef {
    private void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {System.out.println("(");};
        opnd.procesa(this);
        if(opnd.prioridad() < np) {System.out.println(")");};        
    }
    private void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1, int fila, int col) {
        imprimeOpnd(opnd0,np0);
        System.out.print(op);
        System.out.format("$f:%d,c:%d$%n", fila, col);
        imprimeOpnd(opnd1,np1);
    }
    public void procesa(Prog prog) {
        prog.bloq().procesa(this);
    } 

	public void procesa(Blo blo) {
        System.out.println("{");
        blo.decla().procesa(this);
        blo.instr().procesa(this);
        System.out.println("}");
	}


	public void procesa(Si_decs decs) {
        decs.decs().procesa(this);
		System.out.println("&&");
	}


	public void procesa(No_decs decs) {

	}


	public void procesa(Muchas_decs decs) {
        decs.decs().procesa(this);
        System.out.println(";");
		decs.dec().procesa(this);
	}


	public void procesa(Una_dec decs) {
        decs.dec().procesa(this);
	}

	public void procesa(Var v) {
        v.tipo().procesa(this);
        System.out.format("%s$f:%d,c:%d$%n", v.id(), v.leeFila(), v.leeCol());
	}


	public void procesa(Muchas_var vars) {
        vars.vars().procesa(this);
		System.out.println(",");
        vars.var().procesa(this);
	}


	public void procesa(Una_var vars) {
        vars.var().procesa(this);
	}


	public void procesa(Dec_simple dec) {
        dec.var().procesa(this);
	}


	public void procesa(Dec_type dec) {
        System.out.println("<type>");
        dec.var().procesa(this);
	}


	public void procesa(Dec_proc dec) {
		System.out.println("<proc>");
        System.out.format("%s$f:%d,c:%d$%n", dec.id(), dec.leeFila(), dec.leeCol());
		dec.par_for().procesa(this);
        dec.bloq().procesa(this);
	}


	public void procesa(Tipo_array tipo) {
        tipo.tipo().procesa(this);
		System.out.println("[");
		System.out.println(tipo.litEnt());
        System.out.format("]$f:%d,c:%d$%n", tipo.leeFila(), tipo.leeCol());
	}


	public void procesa(Tipo_punt tipo) {
        System.out.println("^");
        tipo.tipo().procesa(this);
	}


	public void procesa(Tipo_bool tipo) {
        System.out.println("<bool>");
	}


	public void procesa(Tipo_int tipo) {
        System.out.println("<int>");
	}


	public void procesa(Tipo_real tipo) {
		System.out.println("<real>");
	}


	public void procesa(Tipo_string tipo) {
        System.out.println("<string>");
	}


	public void procesa(Tipo_ident tipo) {
        System.out.format("%s$f:%d,c:%d$%n", tipo.id(), tipo.leeFila(), tipo.leeCol());
	}


	public void procesa(Tipo_struct tipo) {
        System.out.println("<struct>");
        System.out.println("{");
        tipo.lvar().procesa(this);
		System.out.println("}");
	}


	public void procesa(Si_inst insts) {
		insts.insts().procesa(this);
	}


	public void procesa(No_inst insts) {
	}


	public void procesa(Muchas_inst insts) {
		insts.insts().procesa(this);
		System.out.println(";");
		insts.inst().procesa(this);	
	}


	public void procesa(Una_inst insts) {
		insts.inst().procesa(this);	
	}


	public void procesa(Si_pformal pfmls) {
		System.out.println("(");
        pfmls.lpfml().procesa(this);
		System.out.println(")");
	}


	public void procesa(No_pformal pfmls) {
		System.out.println("(");
		System.out.println(")");
	}


	public void procesa(Muchos_pformal pfmls) {
		pfmls.lpfml().procesa(this);
		System.out.println(",");
		pfmls.pfml().procesa(this);
	}


	public void procesa(Un_pformal pfmls) {
		pfmls.pfml().procesa(this);
	}


	public void procesa(Pformal_ref pfml) {
		pfml.tipo().procesa(this);
        System.out.format("&%n%s$f:%d,c:%d$%n", pfml.id(), pfml.leeFila(), pfml.leeCol());		
	}


	public void procesa(Pformal_noref pfml) {
		pfml.tipo().procesa(this);
        System.out.format("%s$f:%d,c:%d$%n", pfml.id(), pfml.leeFila(), pfml.leeCol());
	}


	public void procesa(Si_preales preales) {
		System.out.println("(");
		preales.lpr().procesa(this);
		System.out.println(")");
	}


	public void procesa(No_preales preales) {
		System.out.println("(");
		System.out.println(")");
	}


	public void procesa(Muchas_exp lpreal) {
		lpreal.lpr().procesa(this);
        System.out.println(",");
		lpreal.exp().procesa(this);
	}


	public void procesa(Una_exp lpreal) {
		lpreal.exp().procesa(this);		
	}


	public void procesa(Inst_eval inst) {
        System.out.println("@");
        inst.exp().procesa(this);
	}


	public void procesa(Inst_if inst) {
        System.out.println("<if>");
        inst.exp().procesa(this);
        inst.bloq().procesa(this);
	}


	public void procesa(Inst_else inst) {
        System.out.println("<if>");
        inst.exp().procesa(this);
        inst.bloq1().procesa(this);
        System.out.println("<else>");
        inst.bloq2().procesa(this);
	}


	public void procesa(Inst_while inst) {
        System.out.println("<while>");
        inst.exp().procesa(this);
        inst.bloq().procesa(this);
	}


	public void procesa(Inst_new inst) {
        System.out.println("<new>");
        inst.exp().procesa(this);
	}


	public void procesa(Inst_delete inst) {
        System.out.println("<delete>");
        inst.exp().procesa(this);
	}


	public void procesa(Inst_read inst) {
        System.out.println("<read>");
        inst.exp().procesa(this);
	}


	public void procesa(Inst_write inst) {
        System.out.println("<write>");
        inst.exp().procesa(this);
	}


	public void procesa(Inst_call inst) {
        System.out.println("<call>");
        System.out.format("%s$f:%d,c:%d$%n", inst.id(), inst.leeFila(), inst.leeCol());
        inst.pr().procesa(this);
	}


	public void procesa(Inst_nl inst) {
        System.out.println("<nl>");
	}


	public void procesa(Inst_blo inst) {
        inst.bloq().procesa(this);		
	}


	public void procesa(Exp_asig exp) {
        imprimeExpBin(exp.exp1(), "=", exp.exp2(), 1, 0, exp.leeFila(), exp.leeCol());	
	}


	public void procesa(Exp_menor exp) {
        imprimeExpBin(exp.exp1(), "<", exp.exp2(), 1, 2, exp.leeFila(), exp.leeCol());	
	}


	public void procesa(Exp_menIgual exp) {
        imprimeExpBin(exp.exp1(), "<=", exp.exp2(), 1, 2, exp.leeFila(), exp.leeCol());	
	}


	public void procesa(Exp_mayor exp) {
		imprimeExpBin(exp.exp1(), ">", exp.exp2(), 1, 2, exp.leeFila(), exp.leeCol());
	}

	public void procesa(Exp_mayIgual exp) {
		imprimeExpBin(exp.exp1(), ">=", exp.exp2(), 1, 2, exp.leeFila(), exp.leeCol());
	}

	public void procesa(Exp_igual exp) {
		imprimeExpBin(exp.exp1(), "==", exp.exp2(), 1, 2, exp.leeFila(), exp.leeCol());
	}

	public void procesa(Exp_dist exp) {
		imprimeExpBin(exp.exp1(), "!=", exp.exp2(), 1, 2, exp.leeFila(), exp.leeCol());
	}

	public void procesa(Exp_suma exp) {
		imprimeExpBin(exp.exp1(), "+", exp.exp2(), 2, 3, exp.leeFila(), exp.leeCol());
	}

	public void procesa(Exp_resta exp) {
		imprimeExpBin(exp.exp1(), "-", exp.exp2(), 3, 3, exp.leeFila(), exp.leeCol());
	}

	public void procesa(Exp_mult exp) {
		imprimeExpBin(exp.exp1(), "*", exp.exp2(), 4, 5, exp.leeFila(), exp.leeCol());
	}

	public void procesa(Exp_div exp) {
		imprimeExpBin(exp.exp1(), "/", exp.exp2(), 4, 5, exp.leeFila(), exp.leeCol());
	}

	public void procesa(Exp_mod exp) {
		imprimeExpBin(exp.exp1(), "%", exp.exp2(), 4, 5, exp.leeFila(), exp.leeCol());
	}

	public void procesa(Exp_and and) {
        imprimeExpBin(and.exp1(), "<and>", and.exp2(), 4, 3, and.leeFila(), and.leeCol());
	}

	public void procesa(Exp_or or) {
		imprimeExpBin(or.exp1(), "<or>", or.exp2(), 4, 4, or.leeFila(), or.leeCol());
	}

	public void procesa(Exp_menos menos) {
        System.out.format("-$f:%d,c:%d$%n", menos.leeFila(), menos.leeCol());
        menos.exp1().procesa(this);
	}

	public void procesa(Exp_not not) {
    	System.out.format("<not>$f:%d,c:%d$%n", not.leeFila(), not.leeCol());
		not.exp1().procesa(this);
	}

	public void procesa(Exp_index index) {
		index.exp1().procesa(this);
		System.out.format("[$f:%d,c:%d$%n", index.leeFila(), index.leeCol());
		index.exp2().procesa(this);
        System.out.println("]");
	}

	public void procesa(Exp_reg reg) {
		 reg.exp1().procesa(this);
         System.out.println(".");
         System.out.format("%s$f:%d,c:%d$%n", reg.id(), reg.leeFila(), reg.leeCol());
	}

	public void procesa(Exp_indir indir) {
		indir.exp1().procesa(this);
        System.out.format("^$f:%d,c:%d$%n", indir.leeFila(), indir.leeCol());
	}

	public void procesa(Exp_true ex_true) {
        System.out.format("<true>$f:%d,c:%d$%n", ex_true.leeFila(), ex_true.leeCol());	
	}

	public void procesa(Exp_false ex_false) {
        System.out.format("<false>$f:%d,c:%d$%n", ex_false.leeFila(), ex_false.leeCol());
	}

	public void procesa(Exp_litEnt litEnt) {
        System.out.format("%s$f:%d,c:%d$%n", litEnt.litEnt(), litEnt.leeFila(), litEnt.leeCol());
	}

	public void procesa(Exp_litReal litReal) {
        System.out.format("%s$f:%d,c:%d$%n", litReal.litReal(), litReal.leeFila(), litReal.leeCol());
	}

	public void procesa(Exp_litCadena litCadena) {
        System.out.format("%s$f:%d,c:%d$%n", litCadena.litCad(), litCadena.leeFila(), litCadena.leeCol());
	}

	public void procesa(Exp_iden iden) {
        System.out.format("%s$f:%d,c:%d$%n", iden.id(), iden.leeFila(), iden.leeCol());
	}

	public void procesa(Exp_null ex_null) {
        System.out.format("<null>$f:%d,c:%d$%n", ex_null.leeFila(), ex_null.leeCol());
	}
}
