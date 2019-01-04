package android.huyhuynh.musicapp;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtNamesong,txtStart,txtEnd;
    ImageButton btnPlay,btnPause,btnNext,btnBack;
    SeekBar seekSong;
    ArrayList<Song> arrSong;
    int position=0;
    MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ánh xạ:
        init();
        //Add Song
        addMusic();
        //
        creatMedia();
        //
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                }
                else {
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);
                }
                setTimePlayer();
                updateTimeSong();

            }

        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                btnPlay.setImageResource(R.drawable.play);
                creatMedia();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=position+1;
                if (position>=arrSong.size()){
                    position=0;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                creatMedia();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                setTimePlayer();
                updateTimeSong();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=position-1;
                if (position<0){
                    position=arrSong.size()-1;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                creatMedia();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                setTimePlayer();
                updateTimeSong();
            }
        });
        seekSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekSong.getProgress());
            }
        });
    }

    private void addMusic() {
        arrSong = new ArrayList<>();
        arrSong.add(new Song("Rồi người thương cũng hoá người dưng",R.raw.roi_nguoi_thuong_cung_hoa_nguoi_dung_hien_ho));
        arrSong.add(new Song("Lỡ thương một người",R.raw.lo_thuong_mot_nguoi_nguyen_dinh_vu));
        arrSong.add(new Song("Đừng quên tên anh",R.raw.dung_quen_ten_anh_hoa_vinh));
        arrSong.add(new Song("Con trai cưng",R.raw.con_trai_cung_k));
    }

    private void init() {
        txtNamesong = findViewById(R.id.txtNameSong);
        txtStart = findViewById(R.id.txtStart);
        txtEnd = findViewById(R.id.txtEnd);
        btnPlay = findViewById(R.id.btnplay);
        btnPause = findViewById(R.id.btnpause);
        btnBack = findViewById(R.id.btnback);
        btnNext = findViewById(R.id.btnnext);
        seekSong = findViewById(R.id.seeSong);
    }
    private void creatMedia(){
        mediaPlayer = MediaPlayer.create(MainActivity.this,arrSong.get(position).getFile());
        txtNamesong.setText(arrSong.get(position).getTittle());
    }
    private void setTimePlayer(){
        SimpleDateFormat gio = new SimpleDateFormat("mm:ss");
        txtEnd.setText(gio.format(mediaPlayer.getDuration()));//Thời gian của cả bài hát
        //Gán max seekbar
        seekSong.setMax(mediaPlayer.getDuration());
    }
    private void updateTimeSong(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat gio = new SimpleDateFormat("mm:ss");
                txtStart.setText(gio.format(mediaPlayer.getCurrentPosition())); //Vị trí hiện taijd dnag phát
                seekSong.setProgress(mediaPlayer.getCurrentPosition());
                //tự động chuyển bài
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position=position+1;
                        if (position>=arrSong.size()){
                            position=0;
                        }
                        if (mediaPlayer.isPlaying()){
                            mediaPlayer.stop();
                        }
                        creatMedia();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause);
                        setTimePlayer();
                        updateTimeSong();
                    }
                });

                handler.postDelayed(this,500);

            }
        },100);
    }
}
