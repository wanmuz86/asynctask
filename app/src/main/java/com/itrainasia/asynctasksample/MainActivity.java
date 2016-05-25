package com.itrainasia.asynctasksample;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    Context context;
    String result = "Executed";
    ProgressDialog progressDialog;
    TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        context = this;
        txtView = (TextView) findViewById(R.id.output);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button:
                new LongOperation().execute();
                break;
        }
    }

    private class LongOperation extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... params) {
            for (int i = 0; i < 5; i++){
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result){

            txtView.setText("Completed"); // txt.setText(result);
// might want to change "executed" for the returned string passed // into onPostExecute() but that is upto you
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }
        @Override
        protected void onPreExecute() {
            txtView.setText("Please wait");
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Processing");
            progressDialog.show();
        }
        }

}
