package dam.rafaaguilera.temporizadorv2;

import android.content.Context;
import android.media.MediaPlayer;

public class Reproductor {
    private MediaPlayer mediaPlayer;
    private Context context;
    public Reproductor(Context c) {
        context = c;
        mediaPlayer = MediaPlayer.create(context, R.raw.beep);
    }

    public void setSound(int id){
        mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(context, id);
    }

    public void start(){
        mediaPlayer.setLooping(true);
        mediaPlayer.seekTo(0);
        mediaPlayer.start();
    }

    public void stop(){
        if (mediaPlayer.isPlaying()) mediaPlayer.pause();
    }

    public void release(){
        mediaPlayer.release();
    }
}
