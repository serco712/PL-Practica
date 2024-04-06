package evaluador;

import asint.SintaxisAbstractaTiny;

public class Evaluador extends SintaxisAbstractaTiny {
	
    public Evaluador() {}
    
    private void imprimeOpnd(Exp opnd, int np) {
        if(opnd.prioridad() < np) {System.out.println("(");};
        consEnv(opnd);
        if(opnd.prioridad() < np) {System.out.println(")");};        
    }
    private void imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1, int fila, int col) {
        imprimeOpnd(opnd0,np0);
        System.out.print(op);
        System.out.format("$f:%d,c:%d$%n", fila, col);
        imprimeOpnd(opnd1,np1);
    }


    public void evalua(Prog n) {
        consEnv(n.bloq());  
    }
    
    private void consEnv(Blo b) {
    	System.out.println("{");
    	consEnv(b.decla());
    	consEnv(b.instr());
    	System.out.println("}");
    }
    
    private void consEnv(Decs dec) {
        if(claseDe(dec,Si_decs.class)) {
           consEnv(dec.decs());   
           System.out.println("&&");
        }
    }
    
    private void consEnv(LDecs dcs) {
        if(claseDe(dcs,Muchas_decs.class)) {
            consEnv(dcs.decs());
        	System.out.println(";");
        }
        consEnv(dcs.dec());
    }
    
    private void consEnv(LVar vars) {
    	if(claseDe(vars,Muchas_var.class)) {
            consEnv(vars.vars());
    		System.out.println(",");
        }
        consEnv(vars.var());
    }
    
    private void consEnv(Var var) {
    	consEnv(var.tipo());
		System.out.format("%s$f:%d,c:%d$%n", var.id(), var.leeFila(), var.leeCol());
    }
    
    private void consEnv(Dec dec) {
    	if (claseDe(dec, Dec_simple.class)) {
    		consEnv(dec.var());
    	}
    	if (claseDe(dec, Dec_type.class)) {
    		System.out.println("<type>");
    		consEnv(dec.var());
    	}
    	else if (claseDe(dec, Dec_proc.class)) {
            System.out.println("<proc>");
 			System.out.format("%s$f:%d,c:%d$%n", dec.id(), dec.leeFila(), dec.leeCol());
            consEnv(dec.par_for());
            consEnv(dec.bloq());
    	}
    }
     
    private void consEnv(Tipo tipo) {
    	if (claseDe(tipo, Tipo_array.class)) {
    		consEnv(tipo.tipo());
    		System.out.println("[");
            System.out.println(tipo.litEnt());
    		System.out.format("]$f:%d,c:%d$%n", tipo.leeFila(), tipo.leeCol());
    	}
    	else if (claseDe(tipo, Tipo_punt.class)) {
    		System.out.println("^");
    		consEnv(tipo.tipo());
    	}
    	else if (claseDe(tipo, Tipo_bool.class)) {
    		System.out.println("<bool>");
    	}
    	else if (claseDe(tipo, Tipo_int.class)) {
    		System.out.println("<int>");
    	}
    	else if(claseDe(tipo, Tipo_real.class)) {
    		System.out.println("<real>");
    	}
    	else if(claseDe(tipo, Tipo_string.class)) {
    		System.out.println("<string>");
    	}
    	else if(claseDe(tipo, Tipo_ident.class)) {
    		System.out.format("%s$f:%d,c:%d$%n", tipo.id(), tipo.leeFila(), tipo.leeCol());
    	}
    	else if (claseDe(tipo, Tipo_struct.class)) {
    		System.out.println("<struct>");
    		System.out.println("{");
    		consEnv(tipo.lvar());
    		System.out.println("}");
    	}
    }
        
    private void consEnv(Insts insts) {
        if(claseDe(insts,Si_inst.class)) {
        	consEnv(insts.insts());
        }
    }
    
    private void consEnv(LInst insts) {
    	if(claseDe(insts,Muchas_inst.class)) {
            consEnv(insts.insts());
    		System.out.println(";");
        }
        consEnv(insts.inst());
    }
    
    private void consEnv(PFmls pfmls) {
    	System.out.println("(");
    	if(claseDe(pfmls,Si_pformal.class)) {
        	consEnv(pfmls.lpfml());
        }
    	System.out.println(")");
    }
    
    private void consEnv(LPFml pfmls) {
        if(claseDe(pfmls,Muchos_pformal.class)) {
            consEnv(pfmls.lpfml());
    		System.out.println(",");
        }
        
        consEnv(pfmls.pfml());
    }
    
    private void consEnv(PFml pfml) {
    	consEnv(pfml.tipo());
    	if (claseDe(pfml,Pformal_ref.class)) {
    		System.out.println("&");
    	}
		System.out.format("%s$f:%d,c:%d$%n", pfml.id(), pfml.leeFila(), pfml.leeCol());
    }
    
    private void consEnv(PReales preal) {
    	System.out.println("(");
        if(claseDe(preal,Si_preales.class)) {
        	consEnv(preal.lpr());
        }
        System.out.println(")");
    }
    
    private void consEnv(LPReal preales) {
    	consEnv(preales.exp());
    	if(claseDe(preales,Muchas_exp.class)) {
    		System.out.println(",");
    		consEnv(preales.lpr());
    	}
    }
    
    private void consEnv(Inst inst) {
    	if (claseDe(inst, Inst_eval.class)) {
    		System.out.println("@");
        	consEnv(inst.exp());
    	}
    	else if (claseDe(inst, Inst_new.class)) {
    		System.out.println("<new>");
        	consEnv(inst.exp());
    	}
    	else if (claseDe(inst, Inst_delete.class)) {
    		System.out.println("<delete>");
        	consEnv(inst.exp());
    	}
    	else if (claseDe(inst, Inst_read.class)) {
    		System.out.println("<read>");
        	consEnv(inst.exp());
    	}
    	else if (claseDe(inst, Inst_write.class)) {
    		System.out.println("<write>");
        	consEnv(inst.exp());
    	}
    	else if (claseDe(inst, Inst_if.class)) {
    		System.out.println("<if>");
    		consEnv(inst.exp());
    		consEnv(inst.bloq());
    	}
    	else if (claseDe(inst, Inst_while.class)) {
    		System.out.println("<while>");
    		consEnv(inst.exp());
    		consEnv(inst.bloq());
    	}
    	else if (claseDe(inst, Inst_else.class)) {
    		System.out.println("<if>");
    		consEnv(inst.exp());
    		consEnv(inst.bloq1());
    		System.out.println("<else>");
    		consEnv(inst.bloq2());
    	}
    	else if (claseDe(inst, Inst_call.class)) {
    		System.out.println("<call>");
    		System.out.format("%s$f:%d,c:%d$%n", inst.id(), inst.leeFila(), inst.leeCol());
    		consEnv(inst.pr());
    	}
    	else if (claseDe(inst, Inst_blo.class)) {
    		consEnv(inst.bloq());
    	}
    	else if (claseDe(inst, Inst_nl.class)) {
    		System.out.println("<nl>");
    	}
    }
    
    private void consEnv(Exp exp) {
        if(claseDe(exp,Exp_litEnt.class)) {
            System.out.format("%s$f:%d,c:%d$%n", exp.litEnt(), exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp,Exp_litReal.class)) {
            System.out.format("%s$f:%d,c:%d$%n", exp.litReal(), exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp,Exp_litCadena.class)) {
        	System.out.format("%s$f:%d,c:%d$%n", exp.litCad(), exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp,Exp_iden.class)) {
        	System.out.format("%s$f:%d,c:%d$%n", exp.id(), exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp,Exp_true.class)) {
        	System.out.format("<true>$f:%d,c:%d$%n", exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp,Exp_false.class)) {
        	System.out.format("<false>$f:%d,c:%d$%n", exp.leeFila(), exp.leeCol());
        }
        else if(claseDe(exp,Exp_null.class)) {
        	System.out.format("<null>$f:%d,c:%d$%n", exp.leeFila(), exp.leeCol());
        }
        else {
            //imprimeExpBin(Exp opnd0, String op, Exp opnd1, int np0, int np1, int fila, int col) 
            if(claseDe(exp,Exp_and.class)) {
                imprimeExpBin(exp.exp1(),"<and>",exp.exp2(),4,3,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_or.class)) {
           		imprimeExpBin(exp.exp1(),"<or>",exp.exp2(),4,4,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_asig.class)) {
               	imprimeExpBin(exp.exp1(),"=",exp.exp2(),1,0,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_dist.class)) {
                imprimeExpBin(exp.exp1(),"!=",exp.exp2(),1,2,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_div.class)) {
                 imprimeExpBin(exp.exp1(),"/",exp.exp2(),4,5,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_igual.class)) {
                 imprimeExpBin(exp.exp1(),"==",exp.exp2(),1,2,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_mayIgual.class)) {
                 imprimeExpBin(exp.exp1(),">=",exp.exp2(),1,2,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_mayor.class)) {
                 imprimeExpBin(exp.exp1(),">",exp.exp2(),1,2,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_menIgual.class)) {
                 imprimeExpBin(exp.exp1(),"<=",exp.exp2(),1,2,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_menor.class)) {
                 imprimeExpBin(exp.exp1(),"<",exp.exp2(),1,2,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_mod.class)) {
                 imprimeExpBin(exp.exp1(),"%",exp.exp2(),4,5,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_mult.class)) {
 				 imprimeExpBin(exp.exp1(),"*",exp.exp2(),4,5,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_resta.class)) {
                 imprimeExpBin(exp.exp1(),"-",exp.exp2(),3,3,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_suma.class)) {
                 imprimeExpBin(exp.exp1(),"+",exp.exp2(),2,3,exp.leeFila(),exp.leeCol());
            }
            else if(claseDe(exp,Exp_not.class)) {
                System.out.format("<not>$f:%d,c:%d$%n", exp.leeFila(), exp.leeCol());
                imprimeOpnd(exp.exp1(),5);
            }
     		else if(claseDe(exp,Exp_menos.class)) {
                  System.out.format("-$f:%d,c:%d$%n", exp.leeFila(), exp.leeCol());
                  imprimeOpnd(exp.exp1(),5);
            }
     		else if (claseDe(exp,Exp_indir.class)) {
     			imprimeOpnd(exp.exp1(),6);
                System.out.format("^$f:%d,c:%d$%n", exp.leeFila(), exp.leeCol());
     		}
     		else if (claseDe(exp,Exp_reg.class)) {
     			imprimeOpnd(exp.exp1(),6);
     			System.out.println(".");
                System.out.format("%s$f:%d,c:%d$%n", exp.id(), exp.leeFila(), exp.leeCol());
     		}
     		else if (claseDe(exp,Exp_index.class)) {
     			imprimeOpnd(exp.exp1(),6);
                System.out.format("[$f:%d,c:%d$%n", exp.leeFila(), exp.leeCol());
     			imprimeOpnd(exp.exp2(),0);
     			System.out.println("]");
     		}
        }	
    
    } 
    
    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    } 
    
    
}
