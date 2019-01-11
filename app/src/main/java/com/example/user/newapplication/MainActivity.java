package com.example.user.newapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;

    ArrayList<LoanInfo> list = new ArrayList<>();

    private FirebaseAnalytics mFirebaseAnalytics;

    String txtJson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fabric.with(this, new Crashlytics());

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        ButterKnife.bind(this);

        new GetData().execute("http://babloback.ru/productscredits.php?credit");

    }

    private class GetData extends AsyncTask<String, String, String> {

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            txtJson = result;
            if (result != null) {
                try {
                    JSONObject jsonObj = new JSONObject(result);
                    JSONArray items = jsonObj.getJSONArray("items");

                    for (int i = 0; i < items.length(); i++) {
                        JSONObject c = items.getJSONObject(i);

                        Gson gson = new Gson();

                        LoanInfo loan = gson.fromJson(c.toString(), LoanInfo.class);

                        list.add(loan);
                    }
                    rv.setAdapter(new CustomListAdapter(getBaseContext(), list));
                    rv.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                } catch (final JSONException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Не удалось подключиться к серверу",
                                Toast.LENGTH_LONG).show(); }
                });
            }
        }

        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line+"\n");
                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}
