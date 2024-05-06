// Package not detected, please report project structure on CodeTogether's GitHub Issues
package tipado;

import asint.SintaxisAbstractaTiny.Tipado;
import asint.SintaxisAbstractaTiny.Prog;
import asint.SintaxisAbstractaTiny.Blo;
import asint.SintaxisAbstractaTiny.Dec_proc;
import asint.SintaxisAbstractaTiny.Dec_simple;
import asint.SintaxisAbstractaTiny.Dec_type;
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
import asint.SintaxisAbstractaTiny.Muchas_decs;
import asint.SintaxisAbstractaTiny.Muchas_exp;
import asint.SintaxisAbstractaTiny.Muchas_inst;
import asint.SintaxisAbstractaTiny.Muchas_var;
import asint.SintaxisAbstractaTiny.Muchos_pformal;
import asint.SintaxisAbstractaTiny.No_decs;
import asint.SintaxisAbstractaTiny.No_inst;
import asint.SintaxisAbstractaTiny.No_pformal;
import asint.SintaxisAbstractaTiny.No_preales;
import asint.SintaxisAbstractaTiny.Pformal_noref;
import asint.SintaxisAbstractaTiny.Pformal_ref;
import asint.SintaxisAbstractaTiny.Si_decs;
import asint.SintaxisAbstractaTiny.Si_inst;
import asint.SintaxisAbstractaTiny.Si_pformal;
import asint.SintaxisAbstractaTiny.Si_preales;
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
import asint.Procesamiento;
import errors.GestionErroresTiny;

public class Comprobacion_tipos implements Procesamiento {
    //private enum Tipado {Exp, Tipo, Boolean, Error};

    private Boolean ok;
    private GestionErroresTiny GestionErrores;

    public Comprobacion_tipos() {
        ok = true;
        GestionErrores = new GestionErroresTiny();
    }

    public Boolean isOk() { return ok; }

    public void procesa(Prog p) {
        p.bloq().procesa(this);
    }

    public void procesa(Blo b) {
        b.decla().procesa(this);
        b.instr().procesa(this);
        if (!(b.decla().getOk() && b.instr().getOk())) {
            ok &= false;
            b.setOk(false);
        }
    }

    public void procesa(Si_decs decs) {
        decs.decs().procesa(this);
	}

	public void procesa(No_decs decs) {
        //Nada
	}

    public void procesa(Muchas_decs decs) {
        decs.decs().procesa(this);
        decs.dec().procesa(this);
        if (!(decs.decs().getOk() && decs.dec().getOk())) {
            ok &= false;
            decs.setOk(false);
        }
	}

	public void procesa(Una_dec decs) {
        decs.dec().procesa(this);
	}

    public void procesa(Muchas_var vars) {
        vars.vars().procesa(this);
		vars.var().procesa(this);
        if (!(vars.vars().getOk() && vars.var().getOk())) {
            ok &= false;
            vars.setOk(false);
        }
	}

	public void procesa(Una_var vars) {
        vars.var().procesa(this);
	}

    public void procesa(Var v) {
        v.tipo().procesa(this);
	}

    public void procesa(Dec_simple dec) {
        dec.var().procesa(this);
	}
    
    public void procesa(Dec_type dec) {
        dec.var().procesa(this);
	}
    
    public void procesa(Dec_proc dec) {
        dec.par_for().procesa(this);
        dec.bloq().procesa(this);
	}

    public void procesa(Tipo_array tipo) {
        int tam = Integer.parseInt(tipo.litEnt())
		if (tam <= 0) {
            ok &= false;
            tipo.setOk(false);
            GestionErrores.errorTamanioArray();
        }
        else {
            tipo.tipo().procesa(this);
        }
        tipo.setTam(tam);
	}
    
    public void tipado(Tipo_punt tipo) {
        tipo.tipo().procesa(this);
    }

    public void procesa(Tipo_bool tipo) {}
	public void procesa(Tipo_int tipo) {}
	public void procesa(Tipo_real tipo) {}
	public void procesa(Tipo_string tipo) {}
    public void procesa(Tipo_ident tipo) {}
    
    public void procesa(Tipo_struct tipo) {
        tipo.lvar().procesa(this);
	}

    public void procesa(Si_inst insts) {
		insts.insts().procesa(this);
	}

	public void procesa(No_inst insts) {}

    public void procesa(Muchas_inst insts) {
		insts.insts().procesa(this);
        insts.inst().procesa(this);	
        if (!(insts.insts().getOk() && insts.inst().getOk())) {
            ok &= false;
            insts.setOk(false);
        }
	}

	public void procesa(Una_inst insts) {
		insts.inst().procesa(this);	
	}

	public void procesa(Si_pformal pfmls) {
        pfmls.lpfml().procesa(this);
	}

	public void procesa(No_pformal pfmls) {}

    public void procesa(Muchos_pformal pfmls) {
		pfmls.lpfml().procesa(this);
		pfmls.pfml().procesa(this);
        if (!(pfmls.lpfml().getOk() && pfmls.pfml().getOk())) {
            ok &= false;
            pfmls.setOk(false);
        }
	}

	public void procesa(Un_pformal pfmls) {
		pfmls.pfml().procesa(this);
	}

    public void procesa(Si_preales preales) {
		preales.lpr().procesa(this);
	}

	public void procesa(No_preales preales) {}

	public void procesa(Muchas_exp lpreal) {
		lpreal.lpr().procesa(this);
        lpreal.exp().procesa(this);
        if (!(lpreal.lpr().getOk() && lpreal.exp().getOk())) {
            ok &= false;
            lpreal.setOk(false);
        }
	}

	public void procesa(Una_exp lpreal) {
		lpreal.exp().procesa(this);		
	}

    public void procesa(Pformal_ref pfml) {
		pfml.tipo().procesa(this);
	}

	public void procesa(Pformal_noref pfml) {
		pfml.tipo().procesa(this);
	}

    public void procesa(Inst_eval inst) {
        inst.exp().procesa(this);
	}

    public void procesa(Inst_if inst) {
        inst.exp().procesa(this);
        inst.bloq().procesa(this);
        if (!(inst.exp().getTipado() == Tipado.bool && inst.bloq().getOk())) {
            ok &= false;
            inst.setOk(false);
            if (!(inst.exp().getTipado() == Tipado.bool))
            	GestionErrores.errorTipoInadecuado(inst.exp().getTipado(), Tipado.bool);
        }
	}

    public void procesa(Inst_else inst) {
        inst.exp().procesa(this);
        inst.bloq1().procesa(this);
        inst.bloq2().procesa(this);
        if (!(inst.exp().getTipado() == Tipado.bool && inst.bloq1().getOk() && inst.bloq2().getOk())) {
            ok &= false;
            inst.setOk(false);
            if (!(inst.exp().getTipado() == Tipado.bool))
            	GestionErrores.errorTipoInadecuado(inst.exp().getTipado(), Tipado.bool);
        }
	}

    public void procesa(Inst_while inst) {
        inst.exp().procesa(this);
        inst.bloq().procesa(this);
        if (!(inst.exp().getTipado() == Tipado.bool && inst.bloq().getOk())) {
            ok &= false;
            inst.setOk(false);
            if (!(inst.exp().getTipado() == Tipado.bool))
            	GestionErrores.errorTipoInadecuado(inst.exp().getTipado(), Tipado.bool);
        }
	}

    public void procesa(Inst_new inst) {
        inst.exp().procesa(this);
        if (inst.exp().getTipado() != Tipado.tipoPuntero) {
            ok &= false;
            inst.setOk(false);
            if (!(inst.exp().getTipado() == Tipado.tipoPuntero))
            	GestionErrores.errorTipoInadecuado(inst.exp().getTipado(), Tipado.tipoPuntero);
        }
	}

    public void procesa(Inst_delete inst) {
        inst.exp().procesa(this);
        if (inst.exp().getTipado() != Tipado.tipoPuntero) {
            ok &= false;
            inst.setOk(false);
            if (!(inst.exp().getTipado() == Tipado.tipoPuntero))
            	GestionErrores.errorTipoInadecuado(inst.exp().getTipado(), Tipado.tipoPuntero);
        }
	}
    
    public void procesa(Inst_read inst){
        inst.exp().procesa(this);
        if (inst.exp().esDesignador()){
            if (inst.exp().getTipado() != Tipado.literalEntero && inst.exp().getTipado() != Tipado.literalReal && inst.exp().getTipado() != Tipado.literalCadena){
            	ok &=false;
            	inst.setOk(false);
            	 if (!(inst.exp().getTipado() == Tipado.literalEntero))
                 	GestionErrores.errorTipoInadecuado(inst.exp().getTipado(), Tipado.literalEntero);
            	 else if (!(inst.exp().getTipado() == Tipado.literalReal))
                 	GestionErrores.errorTipoInadecuado(inst.exp().getTipado(), Tipado.literalReal);
            	 else if (!(inst.exp().getTipado() == Tipado.literalCadena))
                  	GestionErrores.errorTipoInadecuado(inst.exp().getTipado(), Tipado.literalCadena);
            }
        }
        else {
        	ok &= false;
        	inst.setOk(false);
            GestionErrores.errorNoDesignador();
        }
    }

    public void procesa(Inst_write inst){
         inst.exp().procesa(this);
        if (inst.exp().getTipado() != Tipado.literalEntero && inst.exp().getTipado() != Tipado.literalReal && inst.exp().getTipado() != Tipado.literalCadena && inst.exp().getTipado() != Tipado.bool) {
            ok &= false;
            inst.setOk(false);
            if (!(inst.exp().getTipado() == Tipado.literalEntero))
             	GestionErrores.errorTipoInadecuado(inst.exp().getTipado(), Tipado.literalEntero);
        	 else if (!(inst.exp().getTipado() == Tipado.literalReal))
             	GestionErrores.errorTipoInadecuado(inst.exp().getTipado(), Tipado.literalReal);
        	 else if (!(inst.exp().getTipado() == Tipado.literalCadena))
              	GestionErrores.errorTipoInadecuado(inst.exp().getTipado(), Tipado.literalCadena);
        	 else if (!(inst.exp().getTipado() == Tipado.bool))
               	GestionErrores.errorTipoInadecuado(inst.exp().getTipado(), Tipado.bool);
        }
	}
    
    public void procesa(Inst_call instr){
        Dec_proc p = (Dec_proc)instr.getVinculo();
        
        if (p == null) {
        	ok &= false;
        	instr.setOk(false);
           	GestionErrores.errorTipoInadecuado(inst.getVinculo().tipo().getTipado(), Tipado.dec_proc);
        }
        else {
        	instr.setTipado(tipado_parametros(instr.pr(), p.par_for()));
        }
        if (instr.getTipado() == Tipado.error) {
        	ok &= false;
        	instr.setOk(false);
        	GestionErrores.errorParametrosNoCoincidentes();
        }
        
        instr.id().procesa(this);
        instr.pr().procesa(this);
    }   
    
    public Tipado tipado_parametros(No_preales preales, No_pformal pfmls) {
        return Tipado.ok;
    }
    
    public Tipado tipado_parametros(Si_preales preales, Si_pformal pfmls) {
        return tipado_parametros(preales.lpr(), pfmls.lpfml());
    }

    public Tipado tipado_parametros(Muchas_exp lpreal, Muchos_pformal pfmls) {
        return tipado_parametros(lpreal.lpr(), pfmls.lpfml()) == Tipado.ok &&
        		tipado_parametros(lpreal.exp(), pfmls.pfml()) == Tipado.ok;
    }
    
    public Tipado tipado_parametros(Una_exp e, Pformal_ref pfml) {
    	e.exp().procesa(this);
    	if (compatibles(e.getTipado(), pfml.tipo().getTipado())) 
    		return Tipado.ok;
    	else {
           	GestionErrores.errorTiposIncompatibles(e.getTipado(), pfml.tipo().getTipado()));
    		return Tipado.error;
    	}
    }

	public Tipado tipado_parametros(Una_exp e, Pformal_noref pfml) {
    	e.exp().procesa(this);
    	if (e.esDesignador() && compatibles(e.getTipado(), pfml.tipo().getTipado())) 
    		return Tipado.ok;
    	else {
    		if (!e.esDesignador()) 
                GestionErrores.errorNoDesignador();
    		else
               	GestionErrores.errorTiposIncompatibles(e.getTipado(), pfml.tipo().getTipado()));
    		return Tipado.error;
    	}
    }
            		
	public void procesa(Inst_nl inst) {}

	public void procesa(Inst_blo inst) {
        inst.bloq().procesa(this);		
	}

    public void procesa(Exp_asig exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        
        if (exp.exp1().esDesignador()){
            if (compatibles(exp.exp1().getTipado(), exp.exp2().getTipado()))
            	exp.setTipado(exp.exp1().getTipado());
            
            else {
               	GestionErrores.errorTiposIncompatibles(exp.exp1().getTipado(), exp.exp2().getTipado());
				ok&=false;
				exp.setOk(false);
            }
        }
        else {
            ok &= false;
            exp.setOk(false);
            GestionErrores.errorNoDesignador();
        }
   }

	public void procesa(Exp_menor exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipado(tipo_bin2(exp.exp1().getTipado(), exp.exp2().getTipado()));
    }
    public void procesa(Exp_menIgual exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipado(tipo_bin2(exp.exp1().getTipado(), exp.exp2().getTipado()));
    }
    public void procesa(Exp_mayor exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipado(tipo_bin2(exp.exp1().getTipado(), exp.exp2().getTipado()));
    }
    public void procesa(Exp_mayIgual exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipado(tipo_bin2(exp.exp1().getTipado(), exp.exp2().getTipado()));
    }
	public void procesa(Exp_igual exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipado(tipo_bin3(exp.exp1().getTipado(), exp.exp2().getTipado()));
    }
  
    public void procesa(Exp_dist exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipado(tipo_bin3(exp.exp1().getTipado(), exp.exp2().getTipado()));
    }
    public void procesa(Exp_suma exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipado(tipo_bin1(exp.exp1().getTipado(), exp.exp2().getTipado()));
    }
    public void procesa(Exp_resta exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipado(tipo_bin1(exp.exp1().getTipado(), exp.exp2().getTipado()));
    }

    public void procesa(Exp_mult exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipado(tipo_bin1(exp.exp1().getTipado(), exp.exp2().getTipado()));
    }

    public void procesa(Exp_div exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        exp.setTipado(tipo_bin1(exp.exp1().getTipado(), exp.exp2().getTipado()));
    }
	public void procesa(Exp_mod exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        if  (exp.exp1().getTipado() == Tipado.literalEntero &&  exp.exp2().getTipado() == Tipado.literalEntero){
        	exp.setTipado(Tipado.literalEntero);
        }
        else {
        	ok &= false;
    		exp.setOk(false);
    		if (exp.exp1().getTipado() != Tipado.literalEntero)
              	GestionErrores.errorTipoInadecuado(exp.exp1().getTipado(), Tipado.literalEntero);
    		if (exp.exp2().getTipado() != Tipado.literalEntero)
              	GestionErrores.errorTipoInadecuado(exp.exp2().getTipado(), Tipado.literalEntero);
        }
    }

	public void procesa(Exp_and exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        if  (exp.exp1().getTipado() == Tipado.bool &&  exp.exp2().getTipado() == Tipado.bool){
        	exp.setTipado(Tipado.bool);
        }
        else {
        	ok &= false;
        	exp.setOk(false);
        	if (exp.exp1().getTipado() != Tipado.bool)
              	GestionErrores.errorTipoInadecuado(exp.exp1().getTipado(), Tipado.bool);
    		if (exp.exp2().getTipado() != Tipado.bool)
              	GestionErrores.errorTipoInadecuado(exp.exp2().getTipado(), Tipado.bool);
        }
    }

    public void procesa(Exp_or exp){
        exp.exp1().procesa(this);
        exp.exp2().procesa(this);
        if  (exp.exp1().getTipado() == Tipado.bool &&  exp.exp2().getTipado() == Tipado.bool){
        	exp.setTipado(Tipado.bool);
        }
        else {
        	ok &= false;
        	exp.setOk(false);
        	if (exp.exp1().getTipado() != Tipado.bool)
              	GestionErrores.errorTipoInadecuado(exp.exp1().getTipado(), Tipado.bool);
    		if (exp.exp2().getTipado() != Tipado.bool)
              	GestionErrores.errorTipoInadecuado(exp.exp2().getTipado(), Tipado.bool);
        }
    }
         
    public void procesa(Exp_menos menos) {
        menos.exp1().procesa(this);
        if (menos.exp1().getTipado() != Tipado.literalEntero && menos.exp1().getTipado() != Tipado.literalReal) {
            ok &= false;
            menos.setOk(false);
            if (menos.exp1().getTipado() != Tipado.literalEntero)
              	GestionErrores.errorTipoInadecuado(menos.exp1().getTipado(), Tipado.literalEntero);
            else
            	GestionErrores.errorTipoInadecuado(menos.exp1().getTipado(), Tipado.literalReal);
        }
        else if (menos.exp1().getTipado() != Tipado.literalEntero) {
            menos.setTipado(Tipado.literalEntero);
        }
        else {
            menos.setTipado(Tipado.literalReal);
        }
	}

    public void procesa(Exp_not not) {
		not.exp1().procesa(this);
        if (not.exp1().getTipado() != Tipado.bool) {
            ok &= false;
            not.setOk(false);
            GestionErrores.errorTipoInadecuado(not.exp1().getTipado(), Tipado.bool);
        }
        else {
            not.setTipado(Tipado.bool);
        }
	}

    public void procesa(Exp_index index) {
		index.exp1().procesa(this);
		index.exp2().procesa(this);
        if (index.exp1().getTipado() != Tipado.tipoArray && index.exp2().getTipado() != Tipado.literalEntero) {
            ok &= false;
            index.setOk(false);
            if (index.exp1().getTipado() != Tipado.tipoArray)
              	GestionErrores.errorTipoInadecuado(index.exp1().getTipado(), Tipado.tipoArray);
            if (index.exp2().getTipado() != Tipado.literalEntero)
              	GestionErrores.errorTipoInadecuado(index.exp2().getTipado(), Tipado.literalEntero);
        }
        else {
            index.setTipado(index.exp1().getVinculo().tipo().getTipado());
        }
	}

    public void procesa(Exp_reg reg) {
		reg.exp1().procesa(this);
        if (reg.exp1().getTipado() != Tipado.tipoStruct) {
            ok &= false;
            reg.setOk(false);
          	GestionErrores.errorTipoInadecuado(reg.exp1().getTipado(), Tipado.tipoStruct);
        }
        else {
            reg.setTipado(reg.exp1().getVinculo().tipo().getTipado());
        }
	}

    public void procesa(Exp_indir indir) {
		indir.exp1().procesa(this);
        if (indir.exp1().getTipado() != Tipado.tipoPuntero) {
            ok &= false;
            indir.setOk(false);
          	GestionErrores.errorTipoInadecuado(indir.exp1().getTipado(), Tipado.tipoPuntero);
        }
        else {
            indir.setTipado(Tipado.ok);
        }        
	}

    public void procesa(Exp_true e) {
        e.setTipado(Tipado.bool);
     }

    public void procesa(Exp_false e) {
        e.setTipado(Tipado.bool);
	}

    public void procesa(Exp_litEnt litEnt) {
        litEnt.setTipado(Tipado.literalEntero);
	}

    public void procesa(Exp_litReal litReal) {
        litReal.setTipado(Tipado.literalReal);
	}

    public void procesa(Exp_litCadena litCadena) {
        litCadena.setTipado(Tipado.literalCadena);
	}

    public void procesa(Exp_iden iden) {
        iden.setTipado(iden.getVinculo().tipo().getTipado());
	}

    public void procesa(Exp_null ex_null) {
        ex_null.setTipado(Tipado.nl);
	}


    private Tipado tipo_bin1(Tipado t0, Tipado t1) {
    	if (compatibles(t0,t1)) 
    		return t0;
    	else if (t1 == Tipado.literalReal) {
    		if (t0 == Tipado.literalReal || t0 == Tipado.literalEntero)
    			return Tipado.literalReal;
    		else 
    			return Tipado.error;
    	}    	
    	else if (t0 == Tipado.literalReal) {
    		if (t1 == Tipado.literalReal || t1 == Tipado.literalEntero)
    			return Tipado.literalReal;
    		else 
    			return Tipado.error;
    	}
    	else 
    		return Tipado.error;
    }

    private Tipado tipo_bin2(Tipado t0, Tipado t1) {
    	if ((t0 == Tipado.literalReal || t0 == Tipado.literalEntero) && (t1 == Tipado.literalReal || t0 == Tipado.literalEntero))
    		return Tipado.bool;
    	else if (t0 == Tipado.bool && t1 == Tipado.bool)
    		return Tipado.bool;
    	else if (t0 == Tipado.literalCadena && t1 == Tipado.literalCadena)
    		return Tipado.bool;
    	else
    		return Tipado.error;
    }


    private Tipado tipo_bin3(Tipado t0, Tipado t1) {
    	 if ((t0 == Tipado.literalReal || t0 == Tipado.literalEntero) && (t1 == Tipado.literalReal || t0 == Tipado.literalEntero))
     		return Tipado.bool;
     	else if (t0 == Tipado.bool && t1 == Tipado.bool)
     		return Tipado.bool;
     	else if (t0 == Tipado.literalCadena && t1 == Tipado.literalCadena)
     		return Tipado.bool;
        else if ((t0 == Tipado.tipoPuntero || t0 == Tipado.nl) && (t1 == Tipado.tipoPuntero || t1 == Tipado.nl))
            return Tipado.bool;
        else
            return Tipado.error;
    }


    //Añadir lista para mayor eficiencia con las compatibilidades
    private boolean compatibles(Tipado t1, Tipado t2) {
    	if (t1 == t2)
            return true;
        else if (t1 == Tipado.literalReal && t2 == Tipado.literalEntero)
        	return true;
        else if (t1 == Tipado.tipoArray && t1 == Tipado.tipoArray) {
        	Tipo_array a1 = (Tipo_array)t1;
        	Tipo_array a2 = (Tipo_array)t2;
        	if (a1.getTam() != a2.getTam())
        		return false;
        	else 
        		return true;
        }
        else if (t1 == Tipado.tipoStruct && t2 == Tipado.tipoStruct) {
        	Tipo struct s1 = (Tipo_struct)t1;
        	Tipo struct s2 = (Tipo_struct)t2;
        	Map<String,Node> r1 = s1.accedeReg();
        	Map<String,Node> r2 = s1.accedeReg();

        	if (r1 == null || r2 == null || r1.size() != r2.size())
        		return false;
        	
            for (String id : r1.keySet()) {
                if (!r2.containsKey(id)) {
                    return false;
                }
                if (r1.get(id).tipo().getTipado() != r2.get(id).tipo().getTipado()) {
                    return false;
                }
            }
        	
        	return true;
        }
        else if (t1 == Tipado.tipoPuntero && t2 == Tipado.nl)
        	return true;
        else if (t1 == Tipado.tipoPuntero && t2 == Tipado.tipoPuntero) {
        	Tipo_punt p1 = (Tipo_punt)t1;
        	Tipo_punt p2 = (Tipo_punt)t2;
        	if (p1.tipo().getTipado() != p2.tipo().getTipado())
        		return false;
        	else 
        		return true;
        }
        else
            return false;
    }

}
