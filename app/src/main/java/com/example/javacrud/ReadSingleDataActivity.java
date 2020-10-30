package com.example.javacrud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class ReadSingleDataActivity extends AppCompatActivity {
    private Button read;
    String id;
    String name;
    private EditText uid1ET;
    private TextView id_l, name_l, id_v, name_v;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_single_data);

        read = (Button) findViewById(R.id.insert_btn);
        uid1ET = (EditText) findViewById(R.id.uid);

        id_l = (TextView) findViewById(R.id.id_l);
        name_l = (TextView) findViewById(R.id.name_l);
        id_v = (TextView) findViewById(R.id.id_v);
        name_v = (TextView) findViewById(R.id.name_v);

        view= this.getCurrentFocus();

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = uid1ET.getText().toString();

                new ReadDataActivity().execute();
            }
        });
    }


    class ReadDataActivity extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            dialog = new ProgressDialog(ReadSingleDataActivity.this);
            dialog.setTitle("Hey Wait Please...");
            dialog.setMessage("Fetching your values");
            dialog.show();

        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            Log.i(Controller.TAG, "IDVALUE" + id);
            JSONObject jsonObject = Controller.readData(id);
            Log.i(Controller.TAG, "Json obj " + jsonObject);

            try {
                /**
                 * Check Whether Its NULL???
                 */
                if (jsonObject != null) {

                    JSONObject user = jsonObject.getJSONObject("user");
                    name = user.getString("name");

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

            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

            if (name != null) {
                id_l.setText("ID");
                name_l.setText("NAME");
                id_v.setText(id);
                name_v.setText(name);

            } else
                Toast.makeText(getApplicationContext(), "ID not found", Toast.LENGTH_LONG).show();
        }
    }
}