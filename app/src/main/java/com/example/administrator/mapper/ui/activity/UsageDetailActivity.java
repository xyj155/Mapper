package com.example.administrator.mapper.ui.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.administrator.mapper.R;
import com.example.administrator.mapper.brodercasr.MyBroadCastReceiver;
import com.example.administrator.mapper.service.PlayingMusicServices;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechSynthesizer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsageDetailActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;

    TextView tvContent;
    CheckBox imageView;
    FloatingActionButton btnThumb;
    private SpeechSynthesizer mTts;
    /**
     * 规定开始音乐、暂停音乐、结束音乐的标志
     */
    public static final int PLAT_MUSIC = 1;
    public static final int PAUSE_MUSIC = 2;
    public static final int STOP_MUSIC = 3;
    private MyBroadCastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usage_detail);
        imageView= (CheckBox) findViewById(R.id.imageView);
        btnThumb = (FloatingActionButton) findViewById(R.id.btn_thumb);
        btnThumb.setOnClickListener(this);
        imageView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    playingmusic(PLAT_MUSIC);
                }else {
                    playingmusic(STOP_MUSIC);
                }
            }
        });
        tvContent = (TextView) findViewById(R.id.tv_content);
        Typeface mtypeface = Typeface.createFromAsset(getAssets(), "font.ttf");
        tvContent.setTypeface(mtypeface);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ButterKnife.bind(this);
        receiver = new MyBroadCastReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.complete");
        registerReceiver(receiver, filter);
    }

    private void playingmusic(int type) {
        //启动服务，播放音乐
        Intent intent = new Intent(this, PlayingMusicServices.class);
        intent.putExtra("type", type);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        if (mTts!=null){
            mTts.destroy();
            mTts.stopSpeaking();
        }
    }

    //语音合成
    public void startSpeak() {
        //1.创建 SpeechSynthesizer 对象, 第二个参数：本地合成时传 InitListener
        mTts = SpeechSynthesizer
                .createSynthesizer(this, null);
        //2.合成参数设置，详见《科大讯飞MSC API手册(Android)》SpeechSynthesizer 类
        //设置发音人（更多在线发音人，用户可参见 附录12.2
        mTts.setParameter(SpeechConstant.VOICE_NAME, "nannan"); //设置发音人
        mTts.setParameter(SpeechConstant.SPEED, "40");//设置语速
        mTts.setParameter(SpeechConstant.VOLUME, "50");//设置音量，范围 0~100
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        //设置合成音频保存位置（可自定义保存位置），保存在“./sdcard/iflytek.pcm”
        //保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        //仅支持保存为 pcm 和 wav 格式，如果不需要保存合成音频，注释该行代码
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./sdcard/iflytek.wav");
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_thumb:
                startSpeak();
                if (!mTts.isSpeaking()) {
                    mTts.startSpeaking(tvContent.getText().toString(), null);
                } else {
                    mTts.stopSpeaking();
                }
                break;
        }
    }
}
