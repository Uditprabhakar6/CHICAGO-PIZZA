package in.ac.sharda.chicagopizza;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Welcome extends AppCompatActivity {

    WebView webView;
    private String webUrl = "https://chicagopizza.in/";
    ProgressBar progressBarWeb;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        webView=(WebView)findViewById(R.id.mywebview);
        progressBarWeb= (ProgressBar)findViewById(R.id.progressBar);
        Context context;
        progressDialog= new ProgressDialog(this);
        progressDialog.setMessage("Loading Please Wait ........");


        webView.loadUrl(webUrl);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBarWeb.setVisibility(View.VISIBLE);
                progressBarWeb.setProgress(newProgress);
                setTitle("Loading.....");
                progressDialog.show();
                if(newProgress == 100){
                    progressBarWeb.setVisibility(View.GONE);
                    setTitle(view.getTitle());
                    progressDialog.dismiss();
                }

                super.onProgressChanged(view, newProgress);
            }
        });
    }
    @Override
    public void onBackPressed(){
        if(webView.canGoBack()){
            webView.goBack();
        }
        else
        super.onBackPressed();
    }
}
