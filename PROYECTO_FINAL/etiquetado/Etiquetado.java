package etiquetado;

import java.util.Stack;

import asint.Procesamiento;
import asint.SintaxisAbstractaTiny.Blo;
import asint.SintaxisAbstractaTiny.Dec;
import asint.SintaxisAbstractaTiny.Dec_proc;
import asint.SintaxisAbstractaTiny.Dec_simple;
import asint.SintaxisAbstractaTiny.Dec_type;
import asint.SintaxisAbstractaTiny.Decs;
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
import asint.SintaxisAbstractaTiny.LDecs;
import asint.SintaxisAbstractaTiny.Muchas_decs;
import asint.SintaxisAbstractaTiny.Muchas_exp;
import asint.SintaxisAbstractaTiny.Muchas_inst;
import asint.SintaxisAbstractaTiny.Muchas_var;
import asint.SintaxisAbstractaTiny.Muchos_pformal;
import asint.SintaxisAbstractaTiny.No_decs;
import asint.SintaxisAbstractaTiny.No_inst;
import asint.SintaxisAbstractaTiny.No_pformal;
import asint.SintaxisAbstractaTiny.No_preales;
import asint.SintaxisAbstractaTiny.Nodo;
import asint.SintaxisAbstractaTiny.PReales;
import asint.SintaxisAbstractaTiny.Pformal_noref;
import asint.SintaxisAbstractaTiny.Pformal_ref;
import asint.SintaxisAbstractaTiny.Prog;
import asint.SintaxisAbstractaTiny.Si_decs;
import asint.SintaxisAbstractaTiny.Si_inst;
import asint.SintaxisAbstractaTiny.Si_pformal;
import asint.SintaxisAbstractaTiny.Si_preales;
import asint.SintaxisAbstractaTiny.Tipado;
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
import asint.SintaxisAbstractaTiny.Var;
import maquinap.MaquinaP;

public class Etiquetado implements Procesamiento{
    private int etq = 0;
    private Stack<Dec_proc> subs;
    private MaquinaP m;
	
	public Etiquetado(MaquinaP m) {
		subs = new Stack<>();
		this.m = m;
	}
	
    public void procesa(Prog prog) {
        prog.bloq().procesa(this);
    }

    public void procesa(Blo bloq) {
        bloq.setPrim(etq);
        bloq.instr().procesa(this);
        etq++;
        while(!subs.empty()){
            Dec_proc sub =  subs.pop();
            sub.setPrim(etq);
            etq++;
            recolecta_procs(bloq.decla());
            sub.bloq().instr().procesa(this);
            etq = etq+2;
            sub.setSig(etq);
        }
        bloq.setSig(etq);
    }
    public void procesa(Si_decs dec) {
        dec.setPrim(etq);
        dec.decs().procesa(this);
        dec.setSig(etq);
    }

    public void procesa(No_decs dec) {}
    
    public void procesa(Muchas_decs dec) {
        dec.setPrim(etq);
        dec.decs().procesa(this);
        dec.dec().procesa(this);
        dec.setSig(etq);
    }

    public void procesa(Una_dec dec) {
        dec.setPrim(etq);
        dec.dec().procesa(this);
        dec.setSig(etq);
    }

    public void procesa(Muchas_var var) {
        var.setPrim(etq);
        var.vars().procesa(this);
        var.var().procesa(this);
        var.setSig(etq);
    }

    public void procesa(Una_var var) {
        var.setPrim(etq);
        var.var().procesa(this);
        var.setSig(etq);
    }

    public void procesa(Var var) {
        var.setPrim(etq);
        var.tipo().procesa(this);
        var.setSig(etq);
    }

    public void procesa(Dec_simple dec) {
        dec.setPrim(etq);
        dec.var().procesa(this);
        dec.setSig(etq);
    }

    public void procesa(Dec_type dec) {
        dec.setPrim(etq);
        dec.var().procesa(this);
        dec.setSig(etq);
    }

    public void procesa(Dec_proc dec) {
        dec.setPrim(etq);
        dec.par_for().procesa(this);
        dec.bloq().procesa(this);
        dec.setSig(etq);
    }

    public void procesa(Tipo_array tipo) {
        tipo.setPrim(etq);
        tipo.tipo().procesa(this);
        tipo.setSig(etq);
    }

    public void procesa(Tipo_punt tipo) {
        tipo.setPrim(etq);
        tipo.tipo().procesa(this);
        tipo.setSig(etq);
    }

    public void procesa(Tipo_bool tipo) {}

    public void procesa(Tipo_int tipo) {}

    public void procesa(Tipo_real tipo) {}

    public void procesa(Tipo_string tipo) {}

    public void procesa(Tipo_ident tipo) {
        tipo.setPrim(etq);
        etq++;
        tipo.setSig(etq);
    }

    public void procesa(Tipo_struct tipo) {
        tipo.setPrim(etq);
        tipo.lvar().procesa(this);
        tipo.setSig(etq);
    }

    public void procesa(Si_inst inst) {
        inst.setPrim(etq);
        inst.insts().procesa(this);
        inst.setSig(etq);
    }

    public void procesa(No_inst inst) {}

    public void procesa(Muchas_inst inst) {
        inst.setPrim(etq);
        inst.insts().procesa(this);
        inst.inst().procesa(this);
        inst.setSig(etq);
    }

    public void procesa(Una_inst inst) {
        inst.setPrim(etq);
        inst.inst().procesa(this);
        inst.setSig(etq);
    }

    public void procesa(Si_pformal pf) {
        pf.setPrim(etq);
        pf.lpfml().procesa(this);
        pf.setSig(etq);
    }

    public void procesa(No_pformal pf) {}

    public void procesa(Muchos_pformal pf) {
        pf.setPrim(etq);
        pf.lpfml().procesa(this);
        pf.pfml().procesa(this);
        pf.setSig(etq);
    }

    public void procesa(Un_pformal pf) {
        pf.setPrim(etq);
        pf.pfml().procesa(this);
        pf.setSig(etq);
    }
    
    public void procesa(Pformal_ref pf) {
        pf.setPrim(etq);
        pf.tipo().procesa(this);
        pf.setSig(etq);
    }

    public void procesa(Pformal_noref pf) {
        pf.setPrim(etq);
        pf.tipo().procesa(this);
        pf.setSig(etq);
    }

    public void procesa(Si_preales pr){
        pr.setPrim(etq);
        pr.lpr().procesa(this);
        pr.setSig(etq);
    }

    public void procesa(No_preales pr){}

    public void procesa(Muchas_exp exp){
        exp.setPrim(etq);
        exp.lpr().procesa(this);
        etq++;
        exp.exp().procesa(this);
    	etiquetado_acc_val(exp.exp());
        etq++;
        exp.setSig(etq);
    }

    public void procesa(Una_exp exp){
        exp.setPrim(etq);
        exp.exp().procesa(this);
        etq = etq +2;
        exp.setSig(etq);
    }

    public void procesa(Inst_eval inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.setSig(etq);
    }

    public void procesa(Inst_if inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
    	etiquetado_acc_val(inst.exp());
        etq++;
        inst.bloq().procesa(this);
        etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Inst_else inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
    	etiquetado_acc_val(inst.exp());
        etq++;
        inst.bloq1().procesa(this);
        etq++;
        inst.bloq2().procesa(this);
        etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Inst_while inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
    	etiquetado_acc_val(inst.exp());
        etq++;
        inst.bloq().procesa(this);
        etq++;
        inst.setSig(etq);
    }

    public void procesa(Inst_new inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.setSig(etq);
    }
    
    public void procesa(Inst_delete inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.setSig(etq);
    }
    
    public void procesa(Inst_read inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.setSig(etq);
    }

    public void procesa(Inst_write inst){
    	inst.setPrim(etq);
    	inst.exp().procesa(this);
        etq = etq +2;
        inst.setSig(etq);
    }
    
    public void procesa(Inst_call inst){
    	inst.setPrim(etq);
    	etq++;
    	etiquetado_paso_param(inst.getVinculo(),inst.pr());
        etq++;
        inst.setSig(etq);
    }
    
    public void procesa(Inst_nl inst){}
    

    public void procesa(Inst_blo inst){
    	inst.setPrim(etq);
    	inst.bloq().procesa(this);
        inst.setSig(etq);
    }
    
    public void procesa(Exp_asig exp){
    	exp.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_menor exp){
    	exp.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_menIgual exp){
    	exp.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_mayor exp){
    	exp.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_mayIgual exp){
    	exp.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_igual exp){
    	exp.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_dist exp){
    	exp.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_suma exp){
    	exp.setPrim(etq);
    	etiquetado_opnds2(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_resta exp){
    	exp.setPrim(etq);
    	etiquetado_opnds2(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_mult exp){
    	exp.setPrim(etq);
    	etiquetado_opnds2(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_div exp){
    	exp.setPrim(etq);
    	etiquetado_opnds2(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_mod exp){
    	exp.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_and exp){
    	exp.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_or exp){
    	exp.setPrim(etq);
    	etiquetado_opnds(exp.exp1(),exp.exp2());
    	etq++;
    	exp.setSig(etq);
    }
    
    public void procesa(Exp_menos exp){
        exp.setPrim(etq);
        exp.procesa(this);
    	etiquetado_acc_val(exp);
        exp.setSig(etq);
    }
    
    public void procesa(Exp_not exp){
        exp.setPrim(etq);
        exp.procesa(this);
    	etiquetado_acc_val(exp);
    	etq = etq+2;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_index exp){
        exp.setPrim(etq);
        etiquetado_opnds(exp.exp1(),exp.exp2());
        etq++;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_reg exp){
        exp.setPrim(etq);
        etq++;
        exp.exp1().procesa(this);
        etq++;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_indir exp){
        exp.setPrim(etq);
        exp.exp1().procesa(this);
        etq++;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_true exp){}
    
    public void procesa(Exp_false exp){}
    
    public void procesa(Exp_litEnt exp){
        exp.setPrim(etq);
        etq++;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_litReal exp){
        exp.setPrim(etq);
        etq++;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_litCadena exp){
        exp.setPrim(etq);
        etq++;
        exp.setSig(etq);
    }
    
    public void procesa(Exp_iden exp){
        exp.setPrim(etq);
        if(exp.getNivel() == 0) {
        	etq++;
        }
        else {
        	etq = etq +3;
        }
        exp.setSig(etq);
    }
    
    public void procesa(Exp_null exp){}
    
    private void etiquetado_opnds(Exp exp1,Exp exp2) {
    	exp1.procesa(this);
    	etiquetado_acc_val(exp1);
    	exp2.procesa(this);
    	etiquetado_acc_val(exp2);
    }
    
    private void etiquetado_opnds2(Exp exp1,Exp exp2) {
    	exp1.procesa(this);
    	etiquetado_acc_val(exp1);
    	exp2.procesa(this);
    	etiquetado_acc_val(exp2);

    	if ((exp1.getClass() == Exp_litReal.class && exp2.getClass() == Exp_litEnt.class) || (exp2.getClass() == Exp_litReal.class && exp1.getClass() == Exp_litEnt.class))
    		etq++;
    }
    
    private void etiquetado_acc_val(Exp exp) {
    	if(exp.esDesignador()) {
    		etq++;
    	}
    }
    
    private void etiquetado_paso_param(Nodo n,PReales p){
    	etq = etq+ 2;
    	p.lpr().procesa(this);
    	etq++;
    }
    
    private void recolecta_procs(Decs decs) {
    	if (claseDe(decs, Si_decs.class)) {
    		recolecta_procs(decs.decs());
    	}
    }
    
    private void recolecta_procs(LDecs lDecs){
        if(claseDe(lDecs, Muchas_decs.class)){
             recolecta_procs(lDecs.decs());
             recolecta_procs(lDecs.dec());
        }
        else if (claseDe(lDecs, Una_dec.class)) {
        	recolecta_procs(lDecs.dec());
        }
    }
    
    private void recolecta_procs(Dec decs){
        if (claseDe(decs, Dec_proc.class)) {
        	Dec_proc e = (Dec_proc)decs;
        	subs.add(e);
        }
    }
   
	private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    } 
}
