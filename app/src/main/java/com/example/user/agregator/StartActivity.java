package com.example.user.agregator;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class StartActivity extends AppCompatActivity {

    final String base_url = "http://dev.msp-tl.ru:8080/msp-magw-ws/mobile/index";
    EditText evPassword = null;
    EditText evTelNumber = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        evPassword = (EditText) findViewById(R.id.editPassStart);
        evTelNumber = (EditText) findViewById(R.id.editTelephoneNumberStart);
        evTelNumber.setSelection(2);
        evPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    if (evPassword.getText().toString().equals("qwe123"))
                        jmpCatalogGoods();
                    else showToastMessage("Неверный пароль");
                }

                return false;
            }
        });

        final TextView tvEdit = (TextView) findViewById(R.id.editTelephoneNumberStart);
        Map<String,String> param = new HashMap<>();
        param.put("action", "accountCreateRequest");
        param.put("msisdn", "89604486288");
/*        Log.d("7777", key.get("action"));
        Log.d("7777",key.get("msisdn"));*/

        final GsonRequest<UserResult> loginRequest = new GsonRequest<>(this, base_url, param, UserResult.class,
                new Response.Listener<UserResult>() {
                    @Override
                    public void onResponse(UserResult res) {
                        tvEdit.setText(res.getUser().getRequestId());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //tvEdit.setText("Error: " + error.getMessage());
                error.printStackTrace();
                Log.d("7777", "err " + error.getMessage());
/*                Log.d("7777", "code " + error.networkResponse.statusCode);
                Log.d("7777", "headers "+error.networkResponse.headers);*/
            }
        });

        Volley.newRequestQueue(this).add(loginRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void onClickEnterButton(View view) {
/*        Intent i = new Intent(StartActivity.this, MultiTrackerActivity.class);
        startActivity(i);      */
        // evPassword = (EditText) findViewById(R.id.editPassword);
        if (evPassword.getText().toString().equals("qwe123")){
            jmpCatalogGoods();

        }
        else showToastMessage("Неверный пароль");



/*        final TextView tvResponse = (TextView) findViewById(R.id.editText);


        final GsonRequest<UserResult> loginRequest = new GsonRequest<>(this, requestUrl(base_url), null, UserResult.class,
                new Response.Listener<UserResult>() {
                    @Override
                    public void onResponse(UserResult res) {

                        tvResponse.setText(res.getUser().getS1() + ";" + res.getUser().getS2());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tvResponse.setText("Error: " + error.getMessage());
                Log.d("7777","er: "+error.getMessage());
                error.printStackTrace();
            }
        });

        Volley.newRequestQueue(this).add(loginRequest);*/
    }


    public void onClickSmsButton(View view) {
        Map<Integer,String> dic = new HashMap<>();
        for(int i = 0; i < 10; i++)
            dic.put(i,"Lenin191"+i);
        for(int i = 0; i < 10; i++)
            if(dic.get(i).equals("Lenin1917"))
                Log.d("7777","423 / 3 = " + (423)/3);

    }

    public void onClickRegistry(View view) {
        Intent i = new Intent(StartActivity.this, RegistrationActivity.class);
        startActivity(i);
/*        Intent i = new Intent(StartActivity.this, TutorialActivity.class);
        startActivity(i);*/
    }

//
    public static String requestUrl(String BASE_URL) {
        final String X_MSP_TOKEN_PARAM = "X-MSP-TOKEN" ;
        final String X_MSP_APP_GUID_PARAM = "X-MSP-APP-GUID";
        final String X_MSP_DEV_GUID_PARAM = "X-MSP-DEV-GUID";
        final String X_MSP_REQUEST_TYPE_PARAM = "X-MSP-REQUEST-TYPE";
        final String CONTENT_TYPE_PARAM = "Content-Type";
        final String MSISDN_PARAM = "msisdn";

        final String ACTION_PARAM = "action";
        String x_msp_token = "12345";
        String x_msp_app_guid = "qwert";
        String x_msp_dev_guid = "terweq";
        String x_msp_request_type = "ACTION";
        String content_type = "application/json";

        String action = "accountCreateRequest";

        String telephone = "89604486288";
/*        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(X_MSP_TOKEN_PARAM, x_msp_token)
                .appendQueryParameter(X_MSP_APP_GUID_PARAM,x_msp_app_guid)
                .appendQueryParameter(X_MSP_DEV_GUID_PARAM,x_msp_dev_guid)
                .appendQueryParameter(X_MSP_REQUEST_TYPE_PARAM,x_msp_request_type)
                .appendQueryParameter(CONTENT_TYPE_PARAM,content_type)
                .appendQueryParameter(ACTION_PARAM,action)
                .appendQueryParameter(TELEPHONE_PARAM,telephone)
                .build();   */
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(ACTION_PARAM, action)
            //    .appendQueryParameter(MSISDN_PARAM,telephone)
                .build();


        return builtUri.toString();
    }
    void jmpCatalogGoods(){
        evPassword.setText("");
        evTelNumber.setText("");
        Intent i = new Intent(StartActivity.this, ListOfShoppingListsActivity.class);
        startActivity(i);
    }
    void showToastMessage(String text){
        Toast toast = Toast.makeText(getApplicationContext(),
                text, Toast.LENGTH_SHORT);
        toast.show();
    }
}
