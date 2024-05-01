// Package not detected, please report project structure on CodeTogether's GitHub Issues
package foo.bar;

import asint.SintaxisAbstractaTiny.*;

public class Comprobacion_tipos {
    private enum Tipado {Exp, Tipo, Boolean, Error};

    private Boolean ok;

    public Comprobacion_tipos() {
        ok = true;
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
            //error
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
        }
	}

    public void procesa(Inst_else inst) {
        inst.exp().procesa(this);
        inst.bloq1().procesa(this);
        inst.bloq2().procesa(this);
        if (!(inst.exp().getTipado() == Tipado.bool && inst.bloq1().getOk() && inst.bloq2().getOk())) {
            ok &= false;
            inst.setOk(false);
        }
	}

    public void procesa(Inst_while inst) {
        inst.exp().procesa(this);
        inst.bloq().procesa(this);
        if (!(inst.exp().getTipado() == Tipado.bool && inst.bloq().getOk())) {
            ok &= false;
            inst.setOk(false);
        }
	}

    public void procesa(Inst_new inst) {
        inst.exp().procesa(this);
        if (inst.exp().getTipado() != Tipado.tipoPuntero) {
            ok &= false;
            inst.setOk(false);
            //Error
        }
	}

    public void procesa(Inst_delete inst) {
        inst.exp().procesa(this);
        if (inst.exp().getTipado() != Tipado.tipoPuntero) {
            ok &= false;
            inst.setOk(false);
            //Error
        }
	}
    
    public void procesa(inst_read exp){
        exp.exp().procesa(this);
        if (exp.exp().esDesignador()){
            if (exp.exp().getTipado() != Tipado.literalEntero && exp.exp().getTipado()) != Tipado.literalReal && exp.exp().getTipado() != Tipado.literalCadena){
            	ok &=false;
            	exp.setOk(false);
                //error
        }
        else 
        	ok &= false;
        	exp.setOk(false);
            // error
    }
    }

    public void procesa(inst_write exp){
         exp.exp().procesa(this);
        if (exp.exp().getTipado()) != Tipado.literalEntero && exp.exp().getTipado() != Tipado.literalReal && exp.exp().getTipado() != Tipado.literalCadena && exp.exp().getTipado() != Tipado.bool){
            ok &= false;
            exp.setOk(false);
            //error
        }
	}
    public void proceas(inst_call instr){
        instr.id().procesa(this);
        instr.pr().procesa(this);
    }   
     
    tipado(inst_call(id,PReales)):
        if $.vinculo == dec_proc(id, PFmls, Blo) then
            if num_elems(PReales) != num_elems(PFmls)
                $.tipo = error
            else
                tipado_parametros(PReales, PFmls)
            end if
        else 
            $.tipo = error
        end if
        tipado(PReales)
        $.tipo = ok

    tipado_parametros(no_preales(), no_pformal()):
        return ok

    tipado_parametros(si_preales(LPReal), si_pformal(LPFml)):
        return tipado_paramentros(LPReal, LPFml)

    tipado_parametros(muchas_exp(LPReal,Exp), muchas_pformal(LPFml, PFml)):
        return ambos_ok(tipado_parametros(LPReal,LPFml), tipado_parametros(Exp,PFml))

    tipado_parametros(una_exp(Exp), pformal_ref(Tipo,id)):
        tipado(Exp)
        if compatibles(Exp.tipo,Tipo.tipo) entonces
            return ok
        else
            aviso-error(Tipo.tipo)
            return error

    public void procesa(exp_asig exp){
        exp.opnd0().procesa(this);
        exp.opn1().procesa(this);
        
        if (exp.opnd0().esDesignador()){
            if (!compatibles(exp.opnd0(), exp.opnd1()){
				
				//aviso_error(exp.opnd0(),exp.opnd1())
				ok&=false;
				exp.setOk(false);
                //error  
            } 
        }
        else
            ok &= false;
            exp.setOk(false);
            //error
   }

	public void procesa(exp_menor exp){
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        tipado_bin2(exp, exp.opnd0(), exp.opnd1());
    }
    public void procesa(exp_menIgual exp){
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        tipado_bin2(exp, exp.opnd0(), exp.opnd1());
    }
    public void procesa(exp_mayor exp){
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        tipado_bin2(exp, exp.opnd0(), exp.opnd1());
    }
    public void procesa(exp_mayIgual exp){
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        tipado_bin2(exp, exp.opnd0(), exp.opnd1());
    }
	public void procesa(exp_igual exp){
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        tipado_bin3(exp, exp.opnd0(), exp.opnd1());
    }
  
    public void procesa(exp_dist exp){
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        tipado_bin3(exp, exp.opnd0(), exp.opnd1());
    }
    public void procesa(exp_sum exp){
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        tipado_bin1(exp, exp.opnd0(), exp.opnd1());
    }
    public void procesa(exp_resta exp){
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        tipado_bin1(exp, exp.opnd0(), exp.opnd1());
    }

    public void procesa(exp_mult exp){
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        tipado_bin1(exp, exp.opnd0(), exp.opnd1());
    }

    public void procesa(exp_div exp){
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        tipado_bin1(exp, exp.opnd0(), exp.opnd1());
    }
	public void procesa(exp_mod exp){
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        if  (exp.opnd0().getType() == Type.LiteralEntero &&  exp.opnd1().getType() == Type.LiteralEntero){
        	exp.setTipado(Type.LiteralEntero);
        else
        	exp.setTipado(Type.error);
    	}
    }
	public void procesa(exp_and exp){
        exp.opnd0().procesa(this);
        exp.opnd1().procesa(this);
        if  (exp.opnd0().getType() == Type.bool &&  exp.opnd1().getType() == Type.bool){
        	exp.setTipado(Type.bool);
        else
        	exp.setTipado(Type.error);
    	}
    }
   
	

      
    public void procesa(Exp_menos menos) {
        menos.exp1().procesa(this);
        if (menos.exp1().getTipado() != Tipado.literalEntero && menos.exp1().getTipado() != Tipado.literalReal) {
            ok &= false;
            menos.setOk(false);
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
        }
        else {
            index.setTipado(index.exp1().getVinculo().tipo().getTipado());
        }
	}

    public void procesa(Exp_reg reg) {
		reg.exp1().procesa(this);
        if (reg.exp1().getTipado != Tipado.tipoStruct) {
            ok &= false;
            reg.setOk(false);
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
            //error
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


    tipado-bin1(E0,E1,E):
        tipado(E0)
        tipado(E1)
        E.tipo = tipo-bin1(E0.tipo,E1.tipo)

    tipo-bin1(T0,T1):
        if compatibles(T0,T1)  then
            return T0
        else if ref!(T1) == literalReal then
            if ref!(T0) == literalReal v ref!(T0) == literalEntero then
                return literalEntero
            else 
                aviso-error(T0,T1)
                return error
            end if
        else
            aviso-error(T0,T1)
            return error
        end if

    tipado-bin2(E0,E1,E):
        tipado(E0)
        tipado(E1)
        E.tipo = tipo-bin2(E0.tipo,E1.tipo)

    private Tipado tipo-bin2(T0,T1):
        if (ref!(T0) == literalReal v ref!(T0) == literalEntero) ^ (ref!(T1) == literalReal v ref!(T1) == literalEntero) then
            return bool
        else if ref!(T0) == bool ^ ref!(T1) == bool then
            return bool
        else if ref!(T0) == literalCadena v ref!(T1) == literalCadena then 
            return bool
        else
            aviso-error(T0,T1)
            return error
        end if

    tipado-bin3(E0,E1,E):
        tipado(E0)
        tipado(E1)
        E.tipo = tipo-bin3(E0.tipo,E1.tipo)

     tipo-bin3(T0,T1):
        if (ref!(T0) == literalReal v ref!(T0) == literalEntero) ^ (ref!(T1) == literalReal v ref!(T1) == literalEntero) then
            return bool
        else if ref!(T0) == bool ^ ref!(T1) == bool then
            return bool
        else if ref!(T0) == literalCadena v ref!(T1) == literalCadena then 
            return bool
        else if (ref!(T0) == tipo_punt(T) v ref!(T0) == null) ^ (ref!(T1) == tipo_punt(T) v ref!(T1) == null) then
            return bool
        else
            aviso-error(T0,T1)
            return error
        end if

     compatibles(T1,T2):
        let T1' = ref!(T1) ^ T2' = ref!(T2) in
            if T1' == T2' then
                return true;
            else if T1 == literalReal ^ (T2== literalEntero v T2==literalReal)) then
            	return true;
            else if T1 == tipo_array(T,literalEntero) ^ T2 == tipo_array(T,literalEntero) then
            	return true;
            else if T1 == tipo_struct(LVar) ^ T2 == tipo_struct(LVar) then
            	return true;
            else if T1== tipo_punt(T) ^ T2== null then
            	return true;
            else if  T1== tipo_punt(T) ^ T2 == tipo_punt(T) then
            	return true;
            else
                return false
            end if
        end let

     esDesignador(E):
        return E = id(v) v E = elem1(E') v E = elem2(E')
    
    } 
}
