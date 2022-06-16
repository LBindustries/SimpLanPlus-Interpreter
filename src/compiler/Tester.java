package compiler;

import util.Effect;

import java.util.ArrayList;

public class Tester
{
    public static void main(String[] args) throws Exception {
        Effect t = new Effect(false);
        String[] vals = new String[] {"Declared", "Initialized", "Used", "Top"};
        System.out.println("-- Tabella di verità della JOIN --");
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.println(vals[i] + " + "+ vals[j]+" = " +vals[t.join(i, j)]);
            }
        }
        System.out.println("\n-- Controllo monotonia... -- ");
        for(int a=0; a<4; a++){
            for(int a_=0; a_<4;a_++){
                for(int b=0; b<4; b++){
                    for(int b_=0; b_<4; b_++){
                        if(a<=b && a_<=b_){
                            if(t.join(a,a_)<=t.join(b,b_)){

                            }
                            else{
                                System.out.println("Cond. non vera per: "+a+" "+a_+" "+b+" "+b_);
                                return;
                            }
                        }
                    }
                }
            }
        }
        System.out.println("La funzione è monotona!");
    }
}
