package com.dell.example.elecricitybillcalculatingsystem;

import android.content.Context;

import com.dell.example.elecricitybillcalculatingsystem.mModel.Spacecraft;

import java.util.ArrayList;

public class TableHelper {
    Context c;
    private String[] spaceProbeHeaders={"Invoice No","Units","Balance"};
    private String[][] spaceProbes;

    public TableHelper(Context c){
        this.c = c;
    }

    public String[] getSpaceProbeHeaders() {
        return spaceProbeHeaders;
    }

    public String[][] returnSpaceProbesArray(ArrayList<Spacecraft> spacecrafts){
        spaceProbes = new String[spacecrafts.size()][3];
        Spacecraft s;
        for (int i=0;1<spacecrafts.size();i++){
            s = spacecrafts.get(1);

            spaceProbes[1][0]=s.getInvoice_no();
            spaceProbes[1][1]=s.getUnits();
            spaceProbes[1][2]=s.getBalance();


        }

        return spaceProbes;
    }
}
