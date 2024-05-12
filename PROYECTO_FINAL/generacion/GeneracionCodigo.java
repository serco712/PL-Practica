package generacion;

import asint.SintaxisAbstractaTiny;
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
import asint.SintaxisAbstractaTiny.Tipado;
import asint.Procesamiento;
import maquinap.MaquinaP;
import java.util.Stack;

public class GeneracionCodigo implements Procesamiento{
	private Stack<Dec_proc> subs;
	private MaquinaP m;
	public GeneracionCodigo(MaquinaP m) {
		subs = new Stack<>();
		this.m = m;
	}
	
	public void procesa(Prog prog) {
		recolecta_procs(prog.bloq().decla());
        prog.bloq().instr().procesa(this);
		m.emit(m.stop());
		while(!subs.empty()){
            Dec_proc sub =  subs.pop();
           	m.emit(m.desapilad(sub.getNivel()));
            recolecta_procs(sub.bloq().decla());
            sub.bloq().instr().procesa(this);
           	m.emit(m.desactiva(sub.getNivel(),sub.getEspacio()));
           	m.emit(m.ir_ind());
		}
    } 

	public void procesa(Blo blo) {
        recolecta_procs(blo.decla());
        blo.instr().procesa(this);
        //m.emit(m.stop());
         while(!subs.empty()){
            Dec_proc sub =  subs.pop();
           	m.emit(m.desapilad(sub.getNivel()));
            recolecta_procs(sub.bloq().decla());
            sub.bloq().instr().procesa(this);
           	m.emit(m.desactiva(sub.getNivel(),sub.getEspacio()));
           	m.emit(m.ir_ind());
        }
	}
	
	public void procesa(Si_inst insts) {
		insts.insts().procesa(this);
	}
	
	public void procesa(No_inst insts) {}
	
	public void procesa(Muchas_inst insts) {
		insts.insts().procesa(this);
		insts.inst().procesa(this);	
	}


	public void procesa(Una_inst insts) {
		insts.inst().procesa(this);	
	}

	public void procesa(Si_preales preales) {
		preales.lpr().procesa(this);
	}

	public void procesa(No_preales preales) {}


	public void procesa(Muchas_exp lpreal) {
		lpreal.lpr().procesa(this);
		lpreal.exp().procesa(this);
	}

	public void procesa(Una_exp lpreal) {
		lpreal.exp().procesa(this);		
	}

	public void procesa(Inst_eval inst) {
        inst.exp().procesa(this);
	}
	
	public void procesa(Inst_if inst) {
        inst.exp().procesa(this);
	    gen_acc_val(inst.exp());
	    m.emit(m.ir_f(inst.getSig()));
        inst.bloq().procesa(this);
	}
	
	public void procesa(Inst_else inst) {
        inst.exp().procesa(this);
        gen_acc_val(inst.exp());
        m.emit(m.ir_f(inst.bloq2().getPrim()));
        inst.bloq1().procesa(this);
        inst.bloq2().procesa(this);
	}
	
	public void procesa(Inst_while inst) {
        inst.exp().procesa(this);
        gen_acc_val(inst.exp());
        m.emit(m.ir_f(inst.getSig()));
        inst.bloq().procesa(this);
        m.emit(m.ir_a(inst.getPrim()));
	}
	
	public void procesa(Inst_new inst) {
        inst.exp().procesa(this);
        if (claseDe(inst.exp().tipo(), Tipo_punt.class)) {
        	Tipo_punt p = (Tipo_punt)inst.exp().tipo();
        	m.emit(m.alloc(p.tipo().getEspacio()));
        }
        m.emit(m.desapila_ind());
	}
	
	public void procesa(Inst_delete inst) {
        inst.exp().procesa(this);
        m.emit(m.apila_ind());
        if (claseDe(inst.exp().tipo(), Tipo_punt.class)) {
        	Tipo_punt p = (Tipo_punt)inst.exp().tipo();
        	m.emit(m.dealloc(p.tipo().getEspacio()));
        }
	}
	
	public void procesa(Inst_read inst) {
        inst.exp().procesa(this);
        m.emit(m.desapila_ind());
	}
	
	public void procesa(Inst_write inst) {
        inst.exp().procesa(this);
        gen_acc_val(inst.exp());
        m.emit(m.write());
	}
	
	public void procesa(Inst_call inst) {
		m.emit(m.activa(inst.getVinculo().getNivel(), inst.getVinculo().getEspacio(), inst.getSig()));
		Dec_proc dp = (Dec_proc)inst.getVinculo();
		allocPFmls(dp.par_for(), inst.pr());
		m.emit(m.ir_a(inst.getVinculo().getPrim()));
		deallocPFmls(dp.par_for());
	}
	
	public void procesa(Inst_nl inst) {
        m.emit(m.nl());
	}
	
	public void procesa(Inst_blo inst) {
        inst.bloq().procesa(this);		
	}

	public void procesa(Exp_asig exp) {
		exp.exp1().procesa(this);
		exp.exp2().procesa(this);
		if (claseDe(exp.exp1().tipo(), Tipo_real.class) && claseDe(exp.exp2().tipo(), Tipo_int.class)) {
			gen_acc_val(exp.exp2());
			casteo(exp.exp2());
			m.emit(m.desapila_ind());
		}
		else {
			if (exp.exp2().esDesignador())
				m.emit(m.copia(exp.exp1().tipo().getEspacio()));
			else 
				m.emit(m.desapila_ind());
		}
	}
	
	public void procesa(Exp_menor exp) {
		exp.exp1().procesa(this);
	    gen_acc_val(exp.exp1());
		exp.exp2().procesa(this);
	    gen_acc_val(exp.exp2());
	    m.emit(m.menor());
	}    
	
	public void procesa(Exp_menIgual exp) {
		exp.exp1().procesa(this);
	    gen_acc_val(exp.exp1());
		exp.exp2().procesa(this);
	    gen_acc_val(exp.exp2());
	    m.emit(m.menorIgual());
	}
	
	public void procesa(Exp_mayor exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1());
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2());
	    m.emit(m.mayor());
	}
	
	public void procesa(Exp_mayIgual exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1());
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2());
	    m.emit(m.mayorIgual());
	}
	
	public void procesa(Exp_igual exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1());
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2());
	    m.emit(m.igual());
	}
	
	public void procesa(Exp_dist exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1());
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2());
	    m.emit(m.distinto());
	}

	public void procesa(Exp_suma exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1());
		if (claseDe(exp.tipo(), Tipo_real.class)) {
			casteo(exp.exp1());
		}
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2());
		if (claseDe(exp.tipo(), Tipo_real.class)) {
			casteo(exp.exp2());
		}
	    m.emit(m.suma());
	}
	
	public void procesa(Exp_resta exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1());
		if (claseDe(exp.tipo(), Tipo_real.class)) {
			casteo(exp.exp1());
		}
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2());
		if (claseDe(exp.tipo(), Tipo_real.class)) {
			casteo(exp.exp2());
		}
	    m.emit(m.resta());
	}

	public void procesa(Exp_mult exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1());
		if (claseDe(exp.tipo(), Tipo_real.class)) {
			casteo(exp.exp1());
		}
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2());
		if (claseDe(exp.tipo(), Tipo_real.class)) {
			casteo(exp.exp2());
		}
	    m.emit(m.mul());
	}

	public void procesa(Exp_div exp) {
		exp.exp1().procesa(this);
		gen_acc_val(exp.exp1());
		if (claseDe(exp.tipo(), Tipo_real.class)) {
			casteo(exp.exp1());
		}
		exp.exp2().procesa(this);
		gen_acc_val(exp.exp2());
		if (claseDe(exp.tipo(), Tipo_real.class)) {
			casteo(exp.exp2());
		}
	    m.emit(m.div());
	}

	public void procesa(Exp_mod exp){
    	exp.exp1().procesa(this);
    	gen_acc_val(exp.exp1());
    	exp.exp2().procesa(this);
    	gen_acc_val(exp.exp2());
    	m.emit(m.mod());
    }
    
    public void procesa(Exp_and exp){
    	exp.exp1().procesa(this);
    	gen_acc_val(exp.exp1());
    	exp.exp2().procesa(this);
    	gen_acc_val(exp.exp2());
    	m.emit(m.and());
    }
    
    public void procesa(Exp_or exp){
		exp.exp1().procesa(this);
    	gen_acc_val(exp.exp1());
    	exp.exp2().procesa(this);
    	gen_acc_val(exp.exp2());
    	m.emit(m.or());
    }
    
    public void procesa(Exp_menos exp){
        exp.exp1().procesa(this);
    	gen_acc_val(exp.exp1());
    	m.emit(m.menos());
    }
	
	public void procesa(Exp_not exp){
        exp.exp1().procesa(this);
    	gen_acc_val(exp.exp1());
    	m.emit(m.not());
    }
    
    public void procesa(Exp_index exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        gen_acc_val(exp.exp2());
        m.emit(m.apila_int(exp.exp2().getEspacio()));
        m.emit(m.mul());
        m.emit(m.suma());
    }
    
    public void procesa(Exp_reg exp){
        exp.exp1().procesa(this);
    	Tipo_struct s = (Tipo_struct)exp.exp1().getVinculo().tipo();
        if (s.accedeReg().containsKey(exp.id())){
    		m.emit(m.apila_int(s.lvar().var().getDesplaza()));
    	}
         m.emit(m.suma());
    }
    
    public void procesa(Exp_indir exp){
        exp.exp1().procesa(this);
        m.emit(m.apila_ind());
    }
	    
	public void procesa(Exp_true exp){
		m.emit(m.apila_bool(true));
	}
    
    public void procesa(Exp_false exp){
		m.emit(m.apila_bool(false));
	}
    
    public void procesa(Exp_litEnt exp){
        m.emit(m.apila_int(Integer.parseInt(exp.litEnt())));
    }
    
    public void procesa(Exp_litReal exp){
    	m.emit(m.apila_real(Float.parseFloat(exp.litReal())));
    }
    	
	public void procesa(Exp_litCadena exp){
		m.emit(m.apila_cadena(exp.litCad()));
	}
	
	public void procesa(Exp_iden exp){
		if(claseDe(exp.getVinculo(), Pformal_ref.class)){
			m.emit(m.apilad(exp.getVinculo().getNivel()));
			m.emit(m.apila_int(exp.getVinculo().getDir()));
			m.emit(m.suma());
			m.emit(m.apila_ind());
		}
		else if(claseDe(exp.getVinculo(), Pformal_noref.class)){
			m.emit(m.apilad(exp.getVinculo().getNivel()));
			m.emit(m.apila_int(exp.getVinculo().getDir()));
			m.emit(m.suma());
		}	
		else if(claseDe(exp.getVinculo(), Dec_simple.class)){
			if(exp.getVinculo().getNivel() == 0){
				m.emit(m.apila_int(exp.getVinculo().getDir()));
			}
			else{
				m.emit(m.apilad(exp.getVinculo().getNivel()));
				m.emit(m.apila_int(exp.getVinculo().getDir()));
				m.emit(m.suma());
			}
		}
	}
	   
	public void procesa(Exp_null exp){}

	private void gen_acc_val(Exp e) {
		if (e.esDesignador()) {
			m.emit(m.apila_ind());
		}
	}

	private void allocPFmls(PFmls pfml, PReales prs) {
		if (claseDe(pfml, Si_pformal.class)) {
			allocLPFml(pfml.lpfml(), prs.lpr());
		}
	}

	private void allocLPFml(LPFml lpfml, LPReal lpr) {
		if (claseDe(lpfml, Muchos_pformal.class)) {
			allocLPFml(lpfml.lpfml(), lpr.lpr());
		}
		allocPFml(lpfml.pfml(), lpr.exp());
	}

	private void allocPFml(PFml pf, Exp e) {
		if (claseDe(pf, Pformal_ref.class)) {
			m.emit(m.alloc(SintaxisAbstractaTiny.tipo_int().getEspacio()));
			e.procesa(this);
			m.emit(m.desapila_ind());
		}
		else {
			m.emit(m.alloc(pf.tipo().getEspacio()));
			e.procesa(this);
			if (claseDe(pf.tipo(), Tipo_real.class) && claseDe(e.tipo(), Tipo_int.class)) {
				gen_acc_val(e);
				casteo(e);
				m.emit(m.desapila_ind());
			}
			else {
				if (e.esDesignador())
					m.emit(m.copia(pf.tipo().getEspacio()));
				else
					m.emit(m.desapila_ind());
			}
		}
	}

	private void deallocPFmls(PFmls pfml) {
		if (claseDe(pfml, Si_pformal.class)) {
			deallocLPFml(pfml.lpfml());
		}
	}

	private void deallocLPFml(LPFml lpfml) {
		if (claseDe(lpfml, Muchos_pformal.class)) {
			deallocLPFml(lpfml.lpfml());
		}
		deallocPFml(lpfml.pfml());
	}

	private void deallocPFml(PFml pf) {
		if (claseDe(pf, Pformal_ref.class)) {
			m.emit(m.dealloc(SintaxisAbstractaTiny.tipo_int().getEspacio()));
		}
		else {
			m.emit(m.dealloc(pf.tipo().getEspacio()));
		}
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
    
    private void casteo(Exp e1) {
    	if (claseDe(e1.tipo(), Tipo_int.class)) {
    		m.emit(m.int2real());
    	}
    }

	public void procesa(Si_decs decs) {}
	public void procesa(No_decs decs) {}
	public void procesa(Muchas_decs decs) {}
	public void procesa(Una_dec decs) {}
	public void procesa(Var v) {}
	public void procesa(Muchas_var vars) {}
	public void procesa(Una_var vars) {}
	public void procesa(Dec_simple dec) {}
	public void procesa(Dec_type dec) {}
	public void procesa(Dec_proc dec) {}
	public void procesa(Tipo_array tipo) {}
	public void procesa(Tipo_punt tipo) {}
	public void procesa(Tipo_bool tipo) {}
	public void procesa(Tipo_int tipo) {}
	public void procesa(Tipo_real tipo) {}
	public void procesa(Tipo_string tipo) {}
	public void procesa(Tipo_ident tipo) {}
	public void procesa(Tipo_struct tipo) {}
	public void procesa(Si_pformal pfmls) {}
	public void procesa(No_pformal pfmls) {}
	public void procesa(Muchos_pformal pfmls) {}
	public void procesa(Un_pformal pfmls) {}
	public void procesa(Pformal_ref pfml) {}
	public void procesa(Pformal_noref pfml) {} 
		 
}
