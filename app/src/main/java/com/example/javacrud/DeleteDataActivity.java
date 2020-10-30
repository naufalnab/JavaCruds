package com.example.javacrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class DeleteDataActivity extends AppCompatActivity {

    private Button delete;
    String id;
    String name;
    private EditText uid1ET;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_data);
        delete=(Button)findViewById(R.id.delete_btn);
        uid1ET=(EditText)findViewById(R.id.uid);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=uid1ET.getText().toString();
                new DeleteData().execute();
            }
        });
    }

    class DeleteData extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;
        String result=null;



        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(com.example.javacrud.DeleteDataActivity.this);
            dialog.setTitle("Hey Wait Please...");
            dialog.setMessage("Deleting... ");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            Log.i(Controller.TAG,"IDVALUE"+id);
            JSONObject jsonObject = Controller.deleteData(id);
            Log.i(Controller.TAG, "Json obj "+jsonObject);

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