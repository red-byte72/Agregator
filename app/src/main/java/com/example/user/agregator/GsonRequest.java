package com.example.user.agregator;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class GsonRequest<T> extends Request<T> {

    private Gson gson = new Gson();
    private Map<String, String> params;
    private Response.Listener<T> listener;
    private Class<T> clazz;
   // private ProgressDialog pd;

    public GsonRequest(Context context, String url, Map<String, String> params, Class<T> clazz,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.params = params;
        this.listener = listener;
        this.clazz = clazz;
        Log.d("7777","url "+url);
      //  pd = new ProgressDialog(context);
      //  pd.setCancelable(false);
     //   pd.show();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<>();

        headers.put("X-MSP-TOKEN", "12345");
        headers.put("X-MSP-APP-GUID", "qwerty");
        headers.put("X-MSP-DEV-GUID", "terweq");
        headers.put("X-MSP-REQUEST-TYPE", "ACTION");
        headers.put("Content-Type", "application/json");
        Log.d("7777", "headers"+headers);
        return headers;

    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Log.d("7777","params "+params);
        return params;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
    //    pd.dismiss();
    //    pd = null;
        Log.d("7777", "response " + response);
        Log.d("7777", "data " + response.data);
        Log.d("7777", "headers " + HttpHeaderParser.parseCharset(response.headers));

        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            Log.d("7777", "json " + json);

            BaseResult br = (BaseResult)gson.fromJson(json, clazz);

            if (br.isSuccess()) {
                Log.d("7777","br "+br.getErrorMessage());
                return (Response<T>) Response.success(br, HttpHeaderParser.parseCacheHeaders(response));
            } else {
                return Response.error(new VolleyError(br.getErrorMessage()));
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }

}