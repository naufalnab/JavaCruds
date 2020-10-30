package com.example.javacrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class InsertDataActivity extends AppCompatActivity {

    private Button insert;
    String id;
    String name;
    private EditText uid1ET,uid2,nameET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);
        insert=(Button)findViewById(R.id.insert_btn);
        uid1ET=(EditText)findViewById(R.id.uid);
        nameET=(EditText)findViewById(R.id.name);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id=uid1ET.getText().toString();
                name=nameET.getText().toString();
                new Insert().execute();
            }
        });
    }
    class Insert extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        String result=null;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(com.example.javacrud.InsertDataActivity.this);
            dialog.setTitle("Hey Wait Please...");
            dialog.setMessage("Inserting your values..");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = Controller.insertData(id,name);
            Log.i(Controller.TAG, "Json obj ");

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    result=jsonObject.getString("result");

                }
            } catch (JSONException je) {
                Log.i(Controller.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        }
    }
}