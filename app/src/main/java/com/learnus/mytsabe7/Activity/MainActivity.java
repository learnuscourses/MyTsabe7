package com.learnus.mytsabe7.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.learnus.mytsabe7.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import me.itangqi.waveloadingview.WaveLoadingView;

public class MainActivity extends AppCompatActivity {
     MediaPlayer click ;
     private Vibrator vibration ;


  private TextView TotalCounter ,TxtTotalZekr,Textzekr;
  private Button ClearCounter ;
  private ImageView soundClick,VibrationImage ,SettingImageView ;
  private  Boolean ImageSoundStatus,ImageVibrationStatus;
  private LinearLayout LayoutNumberZekr;
  private WaveLoadingView mwaveLoadingView;
  private int CurrentNumber , TotalNumber,oldNumber ;
  NumberFormat numberArabic;
    private int newCurrentNumber=0;
  private String CurrentNumberArbic,TotalNumberArabic;
    SharedPreferences totatlZekr ,SettingApp;
    SharedPreferences.Editor editorTotalZekr  , editorSettingApp;
      private int TextType ,Textcolor ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DefindCompanant();
        EventCompanant();




    }
//////************************************************************
    private void EventCompanant() {

///**********************************************************************


        Textzekr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent totaalZekr = new Intent(MainActivity.this,TotalZekr.class);
                startActivity(totaalZekr);
            }
        });
        ///***************************************************************
          soundClick.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                 if(ImageSoundStatus==true) {
                     click.pause();
                     editorSettingApp.putBoolean("soundStatus",false).commit();
                     soundClick.setImageResource(R.drawable.nosound);
                     ImageSoundStatus=false;

                 }else {
                          click.start();
                     soundClick.setImageResource(R.drawable.sound);
                     ImageSoundStatus=true;
                     editorSettingApp.putBoolean("soundStatus",true).commit();
                 }
              }
          });

//********************************************
        VibrationImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ImageVibrationStatus==true){
                    VibrationImage.setImageResource(R.drawable.novibrator);
                    ImageVibrationStatus=false;
                    editorSettingApp.putBoolean("VibrationStatus",false).commit();

                }else{
                    VibrationImage.setImageResource(R.drawable.vibrator);
                    ImageVibrationStatus=true;
                    editorSettingApp.putBoolean("VibrationStatus",true).commit();

                }
            }
        });
//********************************************


        ClearCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               oldNumber=0;
               CurrentNumber=0;
               newCurrentNumber=0;

                editorTotalZekr.putInt("oldTotatalNumber",oldNumber);
                editorTotalZekr.putInt("CurrentNumber",CurrentNumber);
                editorTotalZekr.apply();


                CurrentNumberArbic=numberArabic.format(CurrentNumber);
                mwaveLoadingView.setCenterTitle(CurrentNumberArbic);
                TotalCounter.setText(CurrentNumberArbic);
            }
        });




///////////////////////////////////////////////////////////////////////////////////////////////////
        SettingImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent setting = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(setting);
            }
        });
/////////////////////////////////////////////////
        mwaveLoadingView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // ImageSoundStatus=SettingApp.getBoolean("soundStatus",true);

                if (ImageSoundStatus==true){
                    click.start();

                }else {
                 click.pause();

                }
//********************************************************
                if(ImageVibrationStatus==true){

                    vibration.vibrate(100);
                }else { vibration.vibrate(0);}
                //***********************************
                CurrentNumber++;
                newCurrentNumber++;
                TotalNumber=newCurrentNumber+oldNumber;
                CurrentNumberArbic=numberArabic.format(CurrentNumber);
                TotalNumberArabic=numberArabic.format(TotalNumber);
                mwaveLoadingView.setCenterTitle(CurrentNumberArbic);
                TotalCounter.setText(TotalNumberArabic);

                editorTotalZekr.putInt("oldTotatalNumber",TotalNumber);
                editorTotalZekr.putInt("CurrentNumber",CurrentNumber);
                editorTotalZekr.apply();


                if(CurrentNumber>=33){

                    oldNumber=totatlZekr.getInt("oldTotatalNumber",0);
                    newCurrentNumber=0;
                    CurrentNumber=0;
                    CurrentNumberArbic=numberArabic.format(CurrentNumber);
                    mwaveLoadingView.setCenterTitle(CurrentNumberArbic);

                           vibration.vibrate(500);
                }

          int num =CurrentNumber*3;
                mwaveLoadingView.setProgressValue(num);

            }
        });

    }
//*****************************************************************
    private void DefindCompanant() {
      ClearCounter =(Button)findViewById(R.id.btnClearCounter);
     mwaveLoadingView=(WaveLoadingView)findViewById(R.id.waveLoadingView);
     Textzekr=(TextView)findViewById(R.id.txtZeker);
     TotalCounter=(TextView)findViewById(R.id.textTotalCounter);
     TxtTotalZekr=(TextView)findViewById(R.id.textTotalZekr);
      numberArabic=NumberFormat.getNumberInstance(new Locale("ar","EG"));
     soundClick=(ImageView)findViewById(R.id.imagesound);
        SettingImageView =(ImageView)findViewById(R.id.imagesetting) ;
     click = MediaPlayer.create(MainActivity.this,R.raw.click);

     vibration=(Vibrator)MainActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
     VibrationImage=(ImageView)findViewById(R.id.imagevarb);

        SettingApp = getSharedPreferences("Setting", Context.MODE_PRIVATE); /// make file
        editorSettingApp = SettingApp.edit();

        TextType=SettingApp.getInt("TextType",1);

        Typeface extenalFont = Typeface.createFromAsset(getApplication().getAssets(),"font/"+TextType+".otf");
        Textcolor=SettingApp.getInt("TextColor",0xffffffff);
        totatlZekr=getSharedPreferences("NumberZekr",Context.MODE_PRIVATE);
        editorTotalZekr=totatlZekr.edit();

        oldNumber=totatlZekr.getInt("oldTotatalNumber",0);
        CurrentNumber=totatlZekr.getInt("CurrentNumber",0);

        ImageSoundStatus=SettingApp.getBoolean("soundStatus",true);
        ImageVibrationStatus =SettingApp.getBoolean("VibrationStatus",true);





///*****************************************************
        Textzekr.setTypeface(extenalFont);
        TxtTotalZekr.setTypeface(extenalFont);
        Textzekr.setTextColor(Textcolor);
        TxtTotalZekr.setTextColor(Textcolor);


//*****************************
 if (ImageSoundStatus==true){
     soundClick.setImageResource(R.drawable.sound);


 }else {
     soundClick.setImageResource(R.drawable.nosound);
 }
//***********************************************
        if (ImageVibrationStatus==true){
            VibrationImage.setImageResource(R.drawable.vibrator);


        }else {
            VibrationImage.setImageResource(R.drawable.novibrator);
        }
///*****************************************************

    }



    //****************************************


    @Override
    protected void onResume() {

        TextType=SettingApp.getInt("TextType",1);

        Typeface extenalFont = Typeface.createFromAsset(getApplication().getAssets(),"font/"+TextType+".otf");
        Textcolor=SettingApp.getInt("TextColor",0xffffffff);
        Textzekr.setTypeface(extenalFont);
        TxtTotalZekr.setTypeface(extenalFont);
        Textzekr.setTextColor(Textcolor);
        TxtTotalZekr.setTextColor(Textcolor);
        super.onResume();
    }
}
