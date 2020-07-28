package com.app_style.fish_play;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;

public class GameOverActivity extends AppCompatActivity {
    TextView title, subtitle,start_txt,text_score,number_score;
    // ads
    private AdView adView;
    private InterstitialAd interstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        // Initialize the Audience Network SDK
        AudienceNetworkAds.initialize(this);
        AdSettings.addTestDevice("d2377af8-4b0f-4aa6-a6be-062bf7eb2b1c");

        //
        adView = new AdView(this, "3351881408176150_3351894368174854", AdSize.BANNER_HEIGHT_50);

        //
        interstitialAd = new InterstitialAd(this, "3351881408176150_3351888364842121");


        // Find the Ad Container
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

        // Add the ad view to your activity layout
        adContainer.addView(adView);

        // Request an ad
        adView.loadAd();



    title = findViewById(R.id.text_title);
    subtitle = findViewById(R.id.text_subtitle);
    start_txt = findViewById(R.id.start_btn);
    text_score = findViewById(R.id.text_score);
    number_score = findViewById(R.id.text_score_number);

    //import font
        Typeface MLight = Typeface.createFromAsset(getAssets(),"fonts/deadSpace.ttf");


    // customize font
        title.setTypeface(MLight);
        subtitle.setTypeface(MLight);
        start_txt.setTypeface(MLight);
        text_score.setTypeface(MLight);
        number_score.setTypeface(MLight);

        // Get Score

        Bundle bundle = getIntent().getExtras();

        if ( bundle != null){
            String score=String.valueOf(bundle.getInt("score_nbr"))  ;
            Toast.makeText(this,score + "pts",Toast.LENGTH_LONG).show();
            number_score.setText(score);
        }else{
            number_score.setText("0 pts");
        }

        interstitialAd.loadAd();
        showAdWithDelay();
}

    private void showAdWithDelay() {
        /*
         * Here is an example for displaying the ad with delay;
         * Please do not copy the Handler into your project
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // Check if interstitialAd has been loaded successfully
                if(interstitialAd == null || !interstitialAd.isAdLoaded()) {
                    Toast.makeText(getApplicationContext(),"not loaded",Toast.LENGTH_LONG).show();
                    return;
                }
                // Check if ad is already expired or invalidated, and do not show ad if that is the case. You will not get paid to show an invalidated ad.
                if(interstitialAd.isAdInvalidated()) {
                    Toast.makeText(getApplicationContext(),"not loaded",Toast.LENGTH_LONG).show();
                    return;
                }
                // Show the ad
                Toast.makeText(getApplicationContext(),"is loaded",Toast.LENGTH_LONG).show();
                interstitialAd.show();
            }
        }, 1000 * 30 ); // Show the ad after 30 sec
    }

    public void share_ll(View view) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = "Fishing Game: You can Get From here : https://play.google.com/store/apps/details?id=" + getPackageName();
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Fishing Game");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

    public void review_ll(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
        startActivity(browserIntent);
/*        view=(WebView) findViewById(R.id.w);
        view.loadUrl("https://play.google.com/store/apps/details?id=" + getPackageName());*/
    }

    public void more_ll(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=App-Style"));
        startActivity(browserIntent);
     /*   view=(WebView) findViewById(R.id.w);
        view.loadUrl("https://play.google.com/store/apps/developer?id=App-Style");*/
    }

    public void p_p_ll(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://app-style.000webhostapp.com/app/fish_clash.html"));
        startActivity(browserIntent);
    /*    view.loadUrl("https://play.google.com/store/apps/developer?id=App-Style");*/
    }

    public void start_play_1(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void start_play_2(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
