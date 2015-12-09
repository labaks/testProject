package labaks.mybrowser;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MyWebView extends Activity {

    String user, pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);


        user = getIntent().getExtras().getString("username");
        pass = getIntent().getExtras().getString("password");

        WebView mWebView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setSaveFormData(true);
        webSettings.setSavePassword(true);
        webSettings.setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new HelloWebViewClient());
        mWebView.loadUrl("http://labaks.github.io/");
    }


    private class HelloWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        public void onPageFinished(WebView view, String url) {
            view.loadUrl(
                    "javascript:(function(){" +
                            "var login = document.getElementById('loginForm\\:login');" +
                            "var pass = document.getElementById('loginForm\\:password')" +
                            "login.value = " + user + ";" +
                            "pass.value = " + pass + ";" +
                            "document.getElementById('loginForm\\:submit').click();" +
                            "})();"
            );
        }
    }
}
