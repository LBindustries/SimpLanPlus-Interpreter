package util;

public class Effect {
    private int status;

    private final int DECLARED = 0;
    private final int INITIALIZED = 1;
    private final int USED = 2;
    private final int TOP = 3;

    public Effect(boolean initialized){
        if(!initialized)
            this.status = DECLARED;
        else
            this.status = INITIALIZED;
    }

    public boolean isLess(Effect e){
        return this.status < e.status;
    }

    public boolean isMore(Effect e){
        return this.status > e.status;
    }

    public boolean isDeclared(){return status == DECLARED;}

    public boolean isInitialized(){
        return status == INITIALIZED;
    }

    public boolean isUsed(){
        return status == USED;
    }

    public void setInitialized(){
        if(this.status < INITIALIZED)
            this.status = INITIALIZED;
    }

    public void setUsed(){
        if(this.status >= INITIALIZED){
            this.status = USED;
        }
    }

    public void setTop(){this.status = TOP;}

    public int join(int effect1, int effect2){
        // D < I < U < T
        if(effect1 == TOP || effect2 == TOP){
            return TOP;
        }
        else if(effect1 == INITIALIZED && effect2 == INITIALIZED){
            return INITIALIZED;
        }
        else if(effect1 == USED && effect2 == USED){
            return USED;
        }
        else if(effect1 == USED && effect2 !=USED){
            if(effect2==DECLARED){
                return DECLARED;
            }
            return USED;
        }
        else if(effect2 == USED && effect1 !=USED){
            if(effect1==DECLARED){
                return DECLARED;
            }
            return USED;
        }
        return DECLARED;
    }

    public Effect getMax(Effect e1, Effect e2){
        if(e1.isMore(e2))
            return e1;
        return e2;
    }

    public Effect getMin(Effect e1, Effect e2){
        if(e1.isLess(e2))
            return e1;
        return e2;
    }
}
