package com.example.grocerypricelister;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

public class DataAccess {private final static String TAG = "DataAccess";

    //properties
    private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    static boolean firstTimeOpened = true;
    private Timer timer;
    private TimerTask timerTask;
    DownloadData downloadData;
    String url;
    private ArrayList<Market> Marketler;

    public DataAccess() {
        downloadData = new DownloadData();
        url = "";  ///////// yeni url girilecek

        Marketler = new ArrayList<Market>();

        timer = new Timer(true);
        timerTask = new TimerTask() {
            @Override
            public void run() {

                try {
                    DownloadData downloadData = new DownloadData();
                    downloadData.execute(url);

                } catch (Exception e){
                    e.printStackTrace();
                }

                //printArrayList();
            };
        };
        timer.schedule(timerTask, 0,10000);

    }


    public void printArrayList()
    {
        for( Market cp : Marketler){
            Log.d(TAG, "printCarParks: " + cp.getName());;
        }
    }

    public ArrayList getMarketler(){
        return Marketler;
    }


    private class DownloadData extends AsyncTask<String,Void,String > {

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while(data > 0){

                    char charachter = (char) data;
                    result += charachter;

                    data = inputStreamReader.read();
                }

                return result;
            }catch (Exception e){
                Log.d(TAG, "doInBackground: Exception with DoInBackground");
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //marketler.clear();

            System.out.println("FLAG: ACCESSED TO DATA");

            try {

                JSONObject jsonObject = new JSONObject(s);
                Log.d(TAG, "onPostExecute: ENTERED THE TRY STATEMENT");

                Iterator<String> keys = jsonObject.keys();

                if(firstTimeOpened) {
                    while (keys.hasNext()) {
                        String key = keys.next();
                        HashMap<String, String> hashMap = new HashMap<>();
                        String values = jsonObject.getString(key);
                        Log.d(TAG, "onPostExecute: FIRST TIME ENTERED" + values);

                        JSONObject jsonObject1 = new JSONObject(values);
                        hashMap.put("name", jsonObject1.get("name").toString());
                        hashMap.put("latitude", jsonObject1.get("latitude").toString());
                        hashMap.put("longitude", jsonObject1.get("longitude").toString());
                        hashMap.put("adress", jsonObject1.get("adress").toString());

                        Market market = new Market(hashMap);
                        Marketler.add(market);

                    }
                    firstTimeOpened = false;
                }else{
                    int i = 0;
                    while(keys.hasNext()){
                        String key = keys.next();
                        String values = jsonObject.getString(key);
                        Log.d(TAG, "onPostExecute: NOT THE FIRST TIME ENTERED" + values);

                        JSONObject jsonObject1 = new JSONObject(values);
                        //String name = marketler.get(i).toString();
                        //pcs.firePropertyChange(name, oldValue, newValue);
                        i++;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            System.out.println(s);
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener){
        this.pcs.addPropertyChangeListener(listener);
    }
}
