package espacio;


import asint.SintaxisAbstractaTiny.*;

public class Espacio {
    public static class ErrorVinculacion extends RuntimeException {} 
    private int dir = 0;
	private int nivel = 0;
	private int max_dir = 0;

    public void asigEspacio(Prog p) {
        asigEspacio(p.bloq());
    }

    public void asigEspacio(Blo b) {
        int aux = dir;
        asigEspacio(b.decla());
        asigEspacio(b.instr());
        dir = aux;
    }

    public void asigEspacio(Decs d) {
        if(claseDe(d, Si_decs.class)){
            asigEspacio1(d.decs());
        	asigEspacio2(d.decs());
        }
    }
    
    public void asigEspacio1(LDecs d){
        if(claseDe(d, Muchas_decs.class)){
             asigEspacio1(d.decs());
        } 
        asigEspacio1(d.dec());
    }
    
    public void asigEspacio2(LDecs d){
        if(claseDe(d, Muchas_decs.class)){
             asigEspacio2(d.decs());
        } 
        asigEspacio2(d.dec());
    }
	
	public void asigEspacio1(LVar v) {
        if(claseDe(v, Muchas_var.class)){
            asigEspacio1(v.vars());
        }
        asigEspacio1(v.var());
    }
	
	public void asigEspacio2(LVar v) {
        if(claseDe(v, Muchas_var.class)){
            asigEspacio2(v.vars());
        }
        asigEspacio2(v.var());
    }
    

    public void asigEspacio1(Var v) {
        //v.setDir(dir);
        //v.setNivel(nivel);
        asigEspacioTipo(v.tipo());
        v.setDesplaza(v.tipo().getEspacio());
        dir = dir + v.tipo().getEspacio();
    }

    public void asigEspacio2(Var v) {}
    
    public void asigEspacio1(Dec d){
         if (claseDe(d, Dec_simple.class) || claseDe(d, Dec_type.class)) {
             asigEspacio1(d.var());
         }
         else if(claseDe(d, Dec_proc.class)){
              int aux = dir;
              int max_dir_aux = max_dir;
       		  nivel++;
              d.setNivel(nivel);
              dir = 0;
              max_dir = 0;
              asigEspacio1(d.par_for());
              asigEspacio(d.bloq());
              d.setNumDatos(dir);
              dir = aux;
              max_dir = max_dir_aux;
              nivel--;
              if (dir > max_dir)
            	  max_dir = dir;
         }
    }
    
    public void asigEspacio2(Dec d){
         if (claseDe(d, Dec_simple.class) || claseDe(d, Dec_type.class)) {
             asigEspacio2(d.var());
         }
    }
    

  
    public void asigEspacioTipo(Tipo t){
        if(t.getEspacio() == -1){
            asigEspacioTipo1(t);
            asigEspacioTipo2(t);
        }

    }
    
    public void asigEspacioTipo1(Tipo t){
        if(claseDe(t, Tipo_array.class)){
        	t.setEspacio(Integer.parseInt(t.litEnt()));
       		if (t.tipo().getClass() != Tipo_ident.class) {
            	asigEspacioTipo1(t);
        	}
        }
        else if(claseDe(t, Tipo_punt.class)){
            t.setEspacio(1);
	        if (t.tipo().getClass() != Tipo_ident.class) {
	            asigEspacioTipo1(t.tipo());
	        }
	        dir++;
        }
        else if(claseDe(t, Tipo_bool.class) || claseDe(t, Tipo_int.class) || claseDe(t, Tipo_real.class) || claseDe(t, Tipo_string.class)){
             t.setEspacio(1);
        }
        else if (claseDe(t, Tipo_ident.class)){
        	Tipo_ident t1 = (Tipo_ident)t;
            asigEspacioTipo1(t1.getVinculo().tipo());
			if (claseDe(t1.getVinculo().tipo(), Dec_type.class)) {
            	t.setEspacio(t1.getVinculo().tipo().getEspacio());
        	}
        }
        else if(claseDe(t, Tipo_struct.class)){
         	int dir_ant = dir;
         	dir = 0;
         	asigEspacio1(t.lvar());
         	t.setEspacio(dir);
         	dir = dir_ant;
        }
    }
    
     public void asigEspacioTipo2(Tipo t){
        if(claseDe(t, Tipo_array.class)){
        	if (t.tipo().getClass() == Tipo_ident.class) {
        		Tipo_ident t1 = (Tipo_ident)t.tipo();
    			if (claseDe(t1.getVinculo().tipo(), Dec_type.class)) {
 	                asigEspacioTipo2(t1.getVinculo().tipo());
            	}
	            t.setEspacio(Integer.parseInt(t.litEnt())* t.getEspacio());
	        }
        }
        else if(claseDe(t, Tipo_punt.class)){
            
        	if (t.tipo().getClass() == Tipo_ident.class) {
        		Tipo_ident t1 = (Tipo_ident)t.tipo();
    			if (claseDe(t1.getVinculo().tipo(), Dec_type.class)) {
 	                asigEspacioTipo2(t1.getVinculo().tipo());
            	}
	            t.setEspacio(t.getEspacio());
	        }
        }
        else if(claseDe(t, Tipo_struct.class)){
        	Tipo_struct t2 = (Tipo_struct)t;
         	asigEspacio2(t2.lvar());
        }
    }

	public void asigEspacio(Insts i) {
        if(claseDe(i, Si_decs.class)){
            asigEspacio(i.insts());
        }
    }
    
    public void asigEspacio(LInst i){
        if(claseDe(i, Muchas_inst.class)){
             asigEspacio(i.insts());
        } 
        asigEspacio(i.inst());
    }


    public void asigEspacio1(PFmls pf) {
        if(claseDe(pf, Si_pformal.class)){
        	asigEspacio1(pf.lpfml());
        }
    }

    public void asigEspacio2(PFmls pf) {
        if(claseDe(pf, Si_pformal.class)){
        	asigEspacio2(pf.lpfml());
        }
    }

    public void asigEspacio1(LPFml pf) {
        if(claseDe(pf, Muchos_pformal.class)){
	        asigEspacio1(pf.lpfml());  
        }
        asigEspacio1(pf.pfml());
    }
    
    public void asigEspacio2(LPFml pf) {
        if(claseDe(pf, Muchos_pformal.class)){
	        asigEspacio2(pf.lpfml());  
        }
        asigEspacio2(pf.pfml());
    }

   

    public void asigEspacio1(PFml pf) {
        if(claseDe(pf, Pformal_ref.class)){
	        pf.setDir(dir);
	        pf.setNivel(nivel);
	        asigEspacioTipo(pf.tipo());
	        dir++;
        }
        if(claseDe(pf, Pformal_noref.class)){
	        pf.setDir(dir);
	        pf.setNivel(nivel);
	        asigEspacioTipo(pf.tipo());
	        dir = dir + pf.tipo().getEspacio();
        }
    }

	public void asigEspacio2(PFml pf) {
        if(claseDe(pf, Pformal_ref.class) || claseDe(pf, Pformal_noref.class)){
	       asigEspacioTipo(pf.tipo());
        }
    }

    public void asigEspacio(PReales pr) {
        if(claseDe(pr, Si_preales.class) ){
        	asigEspacio(pr.lpr());
        }
    }


    public void asigEspacio(LPReal e) {
        if(claseDe(e, Muchas_exp.class) ){
        	asigEspacio(e.lpr());
        }
        asigEspacio(e.exp());
    }
    
    public void asigEspacio(Inst i) {
        if(claseDe(i, Inst_if.class)|| claseDe(i, Inst_while.class) || claseDe(i, Inst_blo.class)){
        	asigEspacio(i.bloq());
        }
        else if(claseDe(i, Inst_else.class) ){
            asigEspacio(i.bloq1());
        	asigEspacio(i.bloq2());
        }
    }
    
    public void asigEspacio(Exp exp) {
         if(claseDe(exp, Exp_asig.class)|| claseDe(exp, Exp_menor.class) || claseDe(exp, Exp_menIgual.class)
         || claseDe(exp, Exp_mayor.class) || claseDe(exp, Exp_mayIgual.class) || claseDe(exp, Exp_igual.class)
         || claseDe(exp, Exp_dist.class) || claseDe(exp, Exp_suma.class) || claseDe(exp, Exp_resta.class)
         || claseDe(exp, Exp_mult.class)|| claseDe(exp, Exp_div.class)|| claseDe(exp, Exp_mod.class)
         || claseDe(exp, Exp_and.class) || claseDe(exp, Exp_or.class)|| claseDe(exp, Exp_index.class)){
                asigEspacio(exp.exp1());
        		asigEspacio(exp.exp2());
          }
          else if(claseDe(exp, Exp_menos.class) || claseDe(exp, Exp_not.class) || claseDe(exp, Exp_indir.class)){
               asigEspacio(exp.exp1());
          }
          else if(claseDe(exp, Exp_reg.class)){
			asigEspacioTipo1(exp.getVinculo().tipo());
			asigEspacioTipo2(exp.getVinculo().tipo());			
			if (claseDe(exp.getVinculo().tipo(), Tipo_struct.class)) {
			    exp.setEspacio(exp.getVinculo().tipo().getEspacio());
			}
          }
          else if(claseDe(exp, Exp_iden.class)) {
        	asigEspacioTipo1(exp.getVinculo().tipo());
  			asigEspacioTipo2(exp.getVinculo().tipo());	
			if (claseDe(exp.getVinculo().tipo(), Dec_type.class)) {
			    exp.setEspacio(exp.getVinculo().tipo().getEspacio());
  			}
          }
    }

    
    private boolean claseDe(Object o, Class c) {
        return o.getClass() == c;
    } 
}


