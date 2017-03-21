package ir.banobat.myapplication;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyServerAuthenticator implements IServerAuthenticator {

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static Map<String, String> mCredentialsRepo;

    static {
        Map<String, String> credentials = new HashMap<String, String>();
        credentials.put("demo@example.com", "demo");
        credentials.put("foo@example.com", "foobar");
        credentials.put("user@example.com", "pass");
        mCredentialsRepo = Collections.unmodifiableMap(credentials);
    }

    @Override
    public String signUp(final String email, final String username, String password) {
        // TODO: register new user on the server and return its auth token
        return null;
    }


    @Override
    public String signIn(final String email, final String password) {
        String authToken = null;
        final String url = "http://banobat.ir/token";

        // TODO: get auth token from server
        final OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("grant_type", "password")
                .add("username", email)
                .add("password", password).build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            JSONObject jsonObject = new JSONObject(response.body().string());
            authToken = jsonObject.get("access_token").toString();
        } catch (IOException ex) {

        } catch (JSONException ex) {

        }

        return authToken;
        // Instantiate the RequestQueue.
        /*final RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest jsonObjRequest = new StringRequest(Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccessResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("OnErrorResponse", error.toString());
                    }
                }) {

            @Override
            public String getBodyContentType() {
                return "application/x-www-form-urlencoded";
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("grant_type", "password");
                params.put("username", email);
                params.put("password", password);
                return params;
            }

        };

        queue.add(jsonObjRequest);*/

    }

}
