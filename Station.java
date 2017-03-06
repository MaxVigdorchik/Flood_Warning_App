package org.maxvigdorchik.floodwarning;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ResourceCursorTreeAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;


public class Station
{
    public List<StationItem> ITEMS;

    public Station()
    {
        ITEMS = new ArrayList<StationItem>();
    }

    public void populateItems() throws IOException
    {
        AsyncTask asynctask = new RetrieveStations().execute();
        try
        {
            ITEMS = (ArrayList<StationItem>) asynctask.get(999999, TimeUnit.MILLISECONDS);
        } catch (Exception e)
        {
            e.printStackTrace();
            Log.e("Timeout", "populateItems: TIMEOUT EXCEPTION");
        }
        Log.w("ITEMS", Integer.toString(ITEMS.size()));
        //Log.e("First Task Item", ITEMS.get(0).toString());
    }

    private class RetrieveStations extends AsyncTask<Void, Void, ArrayList<StationItem>>
    {
        Exception exception;
        public List<StationItem> ITEMLIST;

        protected ArrayList<StationItem> doInBackground(Void... params)
        {
            ArrayList<StationItem> result = new ArrayList<StationItem>();
            try
            {

                URL stationrisks = new URL("http://maxvigdorchik.com:5000/floodwarning/risks");
                URLConnection sc = stationrisks.openConnection();
                if (!(sc instanceof HttpURLConnection))
                {
                    throw new IOException("Not an http connection");
                }
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        sc.getInputStream()));

                String line;
                while ((line = in.readLine()) != null)
                {
                    //Log.w("line",line);
                    JSONObject mainObject = new JSONObject(line);
                    //This part is a bit experimental
                    JSONArray stationlist = mainObject.getJSONArray("stations");
                    Log.w("JSONARRAY", Integer.toString(stationlist.length()));
                    for (int i = 0; i < stationlist.length(); i++)
                    {
                        JSONObject jo = stationlist.getJSONObject(i);

                        String coords = jo.getString("coord");
                        String arr[] = coords.split(",");
                        arr[0] = arr[0].substring(1);
                        arr[1] = arr[1].substring(0, arr[1].length() - 1);
                        double lat = Double.parseDouble(arr[0]);
                        double lon = Double.parseDouble(arr[1]);

                        result.add(new StationItem(jo.getString("name"), jo.getString("risk"), lat, lon));
                    }
                }
                return result;
            } catch (Exception e)
            {
                this.exception = e;
                e.printStackTrace();
                Log.e("Error in background", "doInBackground: MAJOR ERROR");
                //return null;
                result.add(new StationItem("Couldn't Parse", "Shit", 0, 0));
                return result;
            }
        }

        protected void onPostExecute(ArrayList<StationItem> stations)
        {
            ITEMS = (ArrayList<StationItem>) stations.clone();
            Log.w("ONPOSTEXECUTE", Integer.toString(ITEMS.size()));
        }
    }
}
