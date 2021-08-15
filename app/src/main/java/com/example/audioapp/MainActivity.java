package com.example.audioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
TextView t1,t2;SeekBar s;ImageView i1,i2,i3,i4;MediaPlayer mediaPlayer;Handler handler=new Handler();Runnable runnable;@Override
    protected void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);setContentView(R.layout.activity_main);
        t1=(TextView )findViewById(R.id.text);t2=(TextView )findViewById(R.id.text2);s=(SeekBar ) findViewById(R.id.see);i1=(ImageView ) findViewById(R.id.image2);
        i2=(ImageView ) findViewById(R.id.image3);i3=(ImageView ) findViewById(R.id.image4);i4=(ImageView ) findViewById(R.id.image5);
        mediaPlayer=MediaPlayer.create(this,R.raw.tune);runnable=new Runnable() {@Override
            public void run() { s.setProgress(mediaPlayer.getCurrentPosition());
                handler.postDelayed(this,500); }};
        int duration=mediaPlayer.getDuration();String sduration=convertForm(duration);
        t2.setText(sduration);i2.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View v) { i2.setVisibility(View.GONE); i3.setVisibility(View.VISIBLE);
                mediaPlayer.start();s.setMax(mediaPlayer.getDuration());handler.postDelayed(runnable,0); }});
        i3.setOnClickListener(new View.OnClickListener() {@Override
            public void onClick(View v) { i3.setVisibility(View.GONE);i2.setVisibility(View.VISIBLE);mediaPlayer.pause();
                handler.removeCallbacks(runnable); }});
        i4.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { int pos=mediaPlayer.getCurrentPosition();
                int duration=mediaPlayer.getDuration();if(mediaPlayer.isPlaying() && duration !=pos){
                    pos+=5000;t1.setText(convertForm(pos));mediaPlayer.seekTo(pos); } }});
        i1.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) { int pos=mediaPlayer.getCurrentPosition();
                if(mediaPlayer.isPlaying() && pos>5000){ pos-=5000; t1.setText(convertForm(pos)); mediaPlayer.seekTo(pos); } }});
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {@Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){ mediaPlayer.seekTo(progress); }t1.setText(convertForm(mediaPlayer.getCurrentPosition())); }
                @Override public void onStartTrackingTouch(SeekBar seekBar) { }
                @Override public void onStopTrackingTouch(SeekBar seekBar) { }});
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {@Override public void onCompletion(MediaPlayer mp) {
                i3.setVisibility(View.GONE);i2.setVisibility(View.VISIBLE);mediaPlayer.seekTo(0); }}); }
@SuppressLint("DefaultLocale") private  String convertForm(int duration){
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(duration),TimeUnit.MILLISECONDS.toSeconds(duration)
        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))); }}