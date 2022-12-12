package util;

import java.util.ArrayList;

public class LabelGenerator {
    private ArrayList<String> labels;
    private int counter;
    // Label generation utility, used to ensure uniqueness among labels.
    public LabelGenerator(){
        this.labels = new ArrayList<String>();
        this.counter = 0;
    }

    public String new_label(String label){
        String elem = label+"_"+counter;
        this.labels.add(elem);
        this.counter++;
        return elem;
    }


}
