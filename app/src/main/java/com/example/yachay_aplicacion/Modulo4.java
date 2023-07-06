package com.example.yachay_aplicacion;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Modulo4 extends AppCompatActivity {
    ListView listView;
    VideoView videoView;
    ArrayList<String> videolist;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modulo1);
        listView=findViewById(R.id.listvieww);
        videoView=findViewById(R.id.videoView2);
        videolist = new ArrayList<>();
        videolist.add("Conociendo la inteligencia emocional");
        videolist.add("Gestion emocional y empatia");
        videolist.add("Habilidades sociales y comunicación emocional");
        videolist.add("Aplicación de la inteligencia emocional en la vida cotidiana");

        adapter=new ArrayAdapter(this,  android.R.layout.simple_list_item_1,videolist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a41));
                        break;
                    case 1:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a42));
                        break;
                    case 2:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a43));
                        break;
                    case 3:
                        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.a44));
                        break;

                    default:
                        break;

                }
                videoView.setMediaController(new MediaController(Modulo4.this));
                videoView.requestFocus();
                videoView.start();
            }
        });




    }
}