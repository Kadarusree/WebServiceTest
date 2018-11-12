package l.admin_2.webservicetest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RequestQueue mRequestQueue;


    String url = "https://api.github.com/users/kadarusree/repos";

    ListView myList;

    ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRequestQueue = Volley.newRequestQueue(this);


    }

    public void loadData(View view) {

        JsonArrayRequest mJsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                parseArray(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(mJsonArrayRequest);
    }


    public ArrayList<Reopsitory> parseArray(JSONArray mArray) {


    ArrayList<Reopsitory> mRepositries = new ArrayList<>();

        for (int i = 0; i<mArray.length();i++){
            try {
                JSONObject mObj = mArray.getJSONObject(i);
                Reopsitory rsp = new Reopsitory();
                rsp.setId(mObj.get("id").toString());
                rsp.setName(mObj.getString("name"));

                mRepositries.add(rsp);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return mRepositries;
    }
}
