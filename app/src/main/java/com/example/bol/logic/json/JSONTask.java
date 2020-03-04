package com.example.bol.logic.json;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.bol.domain.Product;
import com.example.bol.gui.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class JSONTask extends AsyncTask<URL, String, ArrayList<Product>> {
    private ProgressDialog mDialog;
    private Activity mActivity;

    public JSONTask(Activity activity) {
        this.mActivity = activity;
        mDialog = new ProgressDialog(this.mActivity);
    }

    @Override
    protected void onPreExecute() {
        mDialog.setMessage("Please load while we load the json data");
        mDialog.show();
    }

    @Override
    protected ArrayList<Product> doInBackground(URL... urls) {
        InputStream in;
        BufferedReader br;
        StringBuilder sb;
        ArrayList<Product> json;
        HttpsURLConnection urlConnection = null;

        try {
            Log.i("API", urls[0].toString());

            URL requestUrl = urls[0];
            urlConnection = (HttpsURLConnection) requestUrl.openConnection();

            String line;
            sb = new StringBuilder();
            in = urlConnection.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }


            json = JSONReader.procesJSON(sb.toString());


        }catch (IOException IOE){
            IOE.printStackTrace();
            return null;
        }finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        return json;
    }

    @Override
    protected void onPostExecute(ArrayList<Product> products) {
        if (mDialog.isShowing()){
            mDialog.dismiss();
        }
    }
}
