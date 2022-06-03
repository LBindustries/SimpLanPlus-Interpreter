package util;

public class Effect {
    private int status;

    public Effect(boolean initialized){
        if(!initialized)
            this.status = 0;
        else
            this.status = 1;
    }

    public boolean isLess(Effect e){
        return this.status < e.status;
    }

    public boolean isMore(Effect e){
        return this.status > e.status;
    }

    public boolean isInitialized(){
        return status == 1;
    }

    public boolean isUsed(){
        return status == 2;
    }

    public void setInitialized(){
        if(this.status < 1)
            this.status = 1;
    }

    public void setUsed(){
        this.status = 2;
    }
}
