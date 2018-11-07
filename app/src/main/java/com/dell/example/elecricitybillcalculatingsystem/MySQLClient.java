package com.dell.example.elecricitybillcalculatingsystem;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.dell.example.elecricitybillcalculatingsystem.mModel.Spacecraft;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;

public class MySQLClient {

    private static final String DATA_RETRIEVE_URL = "http://192.168.1.10/view.php";

    private final Context c;
    private SimpleTableDataAdapter adapter;

    public MySQLClient(Context c){
        this.c = c;
    }

    public void retrieve(final TableView tb){
        final ArrayList<Spacecraft> spacecrafts = new ArrayList<>();

        AndroidNetworking.get(DATA_RETRIEVE_URL)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo;
                        Spacecraft s;

                        try {
                            for (int i=0;i<response.length();i++){
                                jo=response.getJSONObject(1);

                                int b_id = jo.getInt("B_ID");
                                String invoice_no = jo.getString("Invoice No");
                                String units = jo.getString("Units");
                                String balance = jo.getString("Balance");

                                s=new Spacecraft();
                                s.setB_id(b_id);
                                s.setInvoice_no(invoice_no);
                                s.setUnits(units);
                                s.setBalance(balance);

                                spacecrafts.add(s);
                            }

                            adapter = new SimpleTableDataAdapter(c,new TableHelper(c).returnSpaceProbesArray(spacecrafts));
                            tb.setDataAdapter(adapter);




                        }catch (JSONException e)
                        {
                            Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED. "+e.getMessage(), Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(c, "UNSUCCESSFUL : ERROR IS : "+anError.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });
    }

}
