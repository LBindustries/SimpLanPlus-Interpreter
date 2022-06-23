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

    public Effect(Effect effect){
        this.status = effect.getStatus();
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
        //this.status = this.join(this.status, INITIALIZED);
    }

    public void setUsed(){
        if(this.status >= INITIALIZED){
            this.status = USED;
        }
        //this.status = this.join(this.status, USED);
    }

    public void setTop(){this.status = TOP;}

    public void join(int effect2){
        // D < I < U < T
        int effect1 = this.status;
        int iRet = DECLARED;
        if(effect1 == TOP || effect2 == TOP){
            iRet = TOP;
        }
        else if(effect1 == INITIALIZED && effect2 == INITIALIZED){
            iRet = INITIALIZED;
        }
        else if(effect1 == USED && effect2 == USED){
            iRet = USED;
        }
        else if(effect1 == USED && effect2 !=USED){
            if(effect2==DECLARED){
                iRet = DECLARED;
            }
            iRet = USED;
        }
        else if(effect2 == USED && effect1 !=USED){
            if(effect1==DECLARED){
                iRet = DECLARED;
            }
            iRet = USED;
        }
        this.status = iRet;
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

    public int getStatus() {
        return status;
    }
}
