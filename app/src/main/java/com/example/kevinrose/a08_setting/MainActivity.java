package com.example.kevinrose.a08_setting;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private SeekBar seekBar;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox=(CheckBox)findViewById(R.id.cbx);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        sp=this.getSharedPreferences("config",0);
        boolean ischeckstatus=sp.getBoolean("isChecked",false);
        checkBox.setChecked(ischeckstatus);
        int pgs = sp.getInt("progress", 0);
        seekBar.setProgress(pgs);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override//buttonView是当前的checkbox后面的boolean是检查是否勾选
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //保存到sharedpreference
                SharedPreferences.Editor edit=sp.edit();
                edit.putBoolean("isChecked",isChecked);
                //提交
                edit.commit();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            //进度改变
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
            //开始拖动
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            //停止拖动
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //将进度保存到sharedpreference
                int progress=seekBar.getProgress();
                SharedPreferences.Editor edit=sp.edit();
                edit.putInt("progress",progress);
                edit.commit();
            }
        });
    }
}
