package com.bwie.lonely.taobao;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends AppCompatActivity {

//    @BindView(R.id.time_text)
//    TextView timeText;
    @BindView(R.id.tiyan_btn)
    Button tiyanBtn;

    private TextView time_text;

    int time = 3;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (time > 0) {
                    time--;
                    time_text.setText(time + "秒后进入");
                    handler.sendEmptyMessageDelayed(0, 1000);
                } else {
                    Intent intent = new Intent(StartActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        time_text = findViewById(R.id.time_text);

        //发送
        handler.sendEmptyMessageDelayed(0 , 1000);

    }

    @OnClick(R.id.tiyan_btn)
    public void onViewClicked() {
        Intent intent = new Intent(StartActivity.this , MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeMessages(0);
    }
}
