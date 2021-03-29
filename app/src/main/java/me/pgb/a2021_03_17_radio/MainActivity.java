package me.pgb.a2021_03_17_radio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.net.URL;

import me.pgb.a2021_03_17_radio.models.RadioStation;
import me.pgb.a2021_03_17_radio.models.RadioStationArray;
import me.pgb.a2021_03_17_radio.service.RadioService;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MAIN__";
    private MediaPlayer mediaPlayer;
    private static String url = "http://stream.whus.org:8000/whusfm"; //";//http://vprbbc.streamguys.net:80/vprbbc24.mp3";
    private Button internetRadioButton;
    private boolean radioOn;
    private boolean radioWasOnBefore;
    private RadioService mService;
    private boolean mBound = false;
    private Button binderButton;
    private Spinner spinner;
    private static String[] stations;
    private static String[] links;
    private static String[] images;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imageView = findViewById(R.id.imageView);

        spinner = findViewById(R.id.radio_stations);
        stations = RadioStationArray.getArrayOfRadioNames();
        links = RadioStationArray.getArrayOfRadioLinks();
        images = RadioStationArray.getArrayOfImageNames();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stations);


        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        /*

        cant use ontouchlistener WHY?....
        spinner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(getBaseContext(), "CLICK", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        */



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(selectedItem.equals("WHUS"))
                {
                    //change image
                    Picasso.get().load(images[position]).into(imageView);

                    //stop current station
                    radioOn = false;
                    internetRadioButton.setText("Turn radio ON");
                    if (mediaPlayer.isPlaying()) {
                        Log.i(TAG, "Radio is playing- turning off " );
                        radioWasOnBefore = true;
                    }
                    mediaPlayer.pause();

                    //Notify user of current station
                    Toast.makeText(getBaseContext(), "Now Playing WHUS" , Toast.LENGTH_SHORT).show();

                    //change station
                    url = links[position];


                }
                if(selectedItem.equals("TKDISCO"))
                {
                    //change image
                    Picasso.get().load(images[position]).into(imageView);

                    //stop current station
                    radioOn = false;
                    internetRadioButton.setText("Turn radio ON");
                    if (mediaPlayer.isPlaying()) {
                        Log.i(TAG, "Radio is playing- turning off " );
                        radioWasOnBefore = true;
                    }
                    mediaPlayer.pause();

                    //Notify user of current station
                    Toast.makeText(getBaseContext(), "Now Playing TKDISCO" , Toast.LENGTH_SHORT).show();

                    //change station
                    url = links[position];

                }
                if(selectedItem.equals("TECHNOWORLD"))
                {
                    //change image
                    Picasso.get().load(images[position]).into(imageView);

                    //stop current station
                    radioOn = false;
                    internetRadioButton.setText("Turn radio ON");
                    if (mediaPlayer.isPlaying()) {
                        Log.i(TAG, "Radio is playing- turning off " );
                        radioWasOnBefore = true;
                    }
                    mediaPlayer.pause();

                    //Notify user of current station
                    Toast.makeText(getBaseContext(), "Now Playing TECHNOWORLD" , Toast.LENGTH_SHORT).show();

                    //change station
                    url = links[position];

                }
                if(selectedItem.equals("COLLEGEMUSIC"))
                {
                    //change image
                    Picasso.get().load(images[position]).into(imageView);

                    //stop current station
                    radioOn = false;
                    internetRadioButton.setText("Turn radio ON");
                    if (mediaPlayer.isPlaying()) {
                        Log.i(TAG, "Radio is playing- turning off " );
                        radioWasOnBefore = true;
                    }
                    mediaPlayer.pause();

                    //Notify user of current station
                    Toast.makeText(getBaseContext(), "Now Playing COLLEGEMUSIC" , Toast.LENGTH_SHORT).show();

                    //change station
                    url = links[position];

                }

            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


        radioOn = false;
        radioWasOnBefore = false;

        mediaPlayer = new MediaPlayer();

        internetRadioButton = findViewById(R.id.internet_radio_button);

        internetRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (radioOn) { // ON so Turn OFF
                    radioOn = false;
                    internetRadioButton.setText("Turn radio ON");
                    if (mediaPlayer.isPlaying()) {
                        Log.i(TAG, "Radio is playing- turning off " );
                        radioWasOnBefore = true;
                    }
                    mediaPlayer.pause();
                } else { // OFF so Turn ON
                    radioOn = true;
                    internetRadioButton.setText("Turn radio OFF");
                    if (!mediaPlayer.isPlaying()) {
                        if (radioWasOnBefore) {
                            mediaPlayer.release();
                            mediaPlayer = new MediaPlayer();
                        }
                        radioSetup(mediaPlayer, url);
                        mediaPlayer.prepareAsync();
                    }
                }

            }
        });
    }



    public void radioSetup(MediaPlayer mediaPlayer, String radio_url) {

        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );

        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(TAG, "onPrepared" );
                mediaPlayer.start();
            }
        });

        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i(TAG, "onError: " + String.valueOf(what).toString());
                return false;
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i(TAG, "onCompletion" );
                mediaPlayer.reset();
            }
        });

        try {
            mediaPlayer.setDataSource(radio_url);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void setUpMediaPlayer() {
        Handler handler = null;

        HandlerThread handlerThread = new HandlerThread("media player") {
            @Override
            public void onLooperPrepared() {
                Log.i(TAG, "onLooperPrepared");

            }
        };

    }
}