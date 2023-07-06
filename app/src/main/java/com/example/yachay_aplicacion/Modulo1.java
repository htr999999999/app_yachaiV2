package com.example.yachay_aplicacion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class Modulo1 extends AppCompatActivity {
    ListView listView;
    VideoView videoView;
    YouTubePlayerView youTubePlayerView;
    ArrayList<String> videolist;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo1);

        listView=findViewById(R.id.listvieww);
        videoView=findViewById(R.id.videoView2);
        videolist = new ArrayList<>();
        videolist.add("Proactividad");
        videolist.add("Estilos de liderazgo");
        videolist.add("Toma de decisiones");
        videolist.add("Trabajo en equipo");
        adapter=new ArrayAdapter(this,  android.R.layout.simple_list_item_1,videolist);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        videoView.requestFocus();
                        videoView.start();
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a11));
                        break;
                    case 1:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a12));
                        break;

                    case 2:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a13));
                        break;
                    case 3:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a14));
                        break;
                    default:
                        break;

                }
                videoView.setMediaController(new MediaController(Modulo1.this));
                videoView.requestFocus();
                videoView.start();
                }
        });




    }
}