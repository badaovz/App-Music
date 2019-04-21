package com.example.datvl.testcn.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.datvl.testcn.Fragment.Fragment_Mv;
import com.example.datvl.testcn.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayMvActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    YouTubePlayerView youTubePlayerView;
    String id = "";
    int REQUEST_CODE_MV = 113;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_mv);

        youTubePlayerView = findViewById(R.id.ypvplay);

        Intent intent = getIntent();
        id = intent.getStringExtra("idVideo");

        youTubePlayerView.initialize(Fragment_Mv.API_KEY,this);


    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(id);
        //youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(PlayMvActivity.this, REQUEST_CODE_MV);
        }else {
            Toast.makeText(this,"Error!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_MV){
            youTubePlayerView.initialize(Fragment_Mv.API_KEY, PlayMvActivity.this);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
