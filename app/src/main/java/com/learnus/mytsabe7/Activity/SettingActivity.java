package com.learnus.mytsabe7.Activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.learnus.mytsabe7.R;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;


public class SettingActivity extends AppCompatActivity {
  private LinearLayout layout_1,layout_2,layout_3,layout_4,layout_5,layout_6,layout_7;
    private Spinner spinnerAction  , spinnerFontType;
  Animation LayoutAnimation_1,LayoutAnimation_2,LayoutAnimation_3,
          LayoutAnimation_4,LayoutAnimation_5,LayoutAnimation_6,
          LayoutAnimation_7,ImageFaceAnimation;

    SharedPreferences SettingApp;
    SharedPreferences.Editor  editorSettingApp;
  Button changeTextColor ;
  private int Textcolor ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);


        SettingApp = getSharedPreferences("Setting", Context.MODE_PRIVATE); /// make file
        editorSettingApp = SettingApp.edit();
        final int LastAction = SettingApp.getInt("ActionRecycleView",0);
        final  int LastFontType= SettingApp.getInt("LastFontType",0);
        Textcolor=SettingApp.getInt("TextColor",0xffffffff);

        layout_1=(LinearLayout)findViewById(R.id.linearLayout_1);
        layout_2=(LinearLayout)findViewById(R.id.linearLayout_2);
        layout_3=(LinearLayout)findViewById(R.id.linearLayout_3);
        layout_4=(LinearLayout)findViewById(R.id.linearLayout_4);
        layout_5=(LinearLayout)findViewById(R.id.linearLayout_5);
        layout_6=(LinearLayout)findViewById(R.id.linearLayout_6);
        layout_7=(LinearLayout)findViewById(R.id.linearLayout_7);

        spinnerAction= (Spinner)findViewById(R.id.spinnerAnimation);
        spinnerFontType= (Spinner)findViewById(R.id.spinnerFontType);

        changeTextColor = findViewById(R.id.BtnColorText);
        changeTextColor.setBackgroundColor(Textcolor);

         changeTextColor.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 openColorPicker();
             }
         });

        LayoutAnimation_1 = AnimationUtils.loadAnimation(this,R.anim.layout_animtion_1);
        LayoutAnimation_2 = AnimationUtils.loadAnimation(this,R.anim.layout_animtion_2);
        LayoutAnimation_3 = AnimationUtils.loadAnimation(this,R.anim.layout_animtion_3);
        LayoutAnimation_4 = AnimationUtils.loadAnimation(this,R.anim.layout_animtion_4);
        LayoutAnimation_5 = AnimationUtils.loadAnimation(this,R.anim.layout_animtion_5);
        LayoutAnimation_6 = AnimationUtils.loadAnimation(this,R.anim.layout_animtion_6);
        LayoutAnimation_7 = AnimationUtils.loadAnimation(this,R.anim.layout_animtion_7);

        layout_1.startAnimation(LayoutAnimation_1);
        layout_2.startAnimation(LayoutAnimation_2);
        layout_3.startAnimation(LayoutAnimation_3);
        layout_4.startAnimation(LayoutAnimation_4);
        layout_5.startAnimation(LayoutAnimation_5);
        layout_6.startAnimation(LayoutAnimation_6);
        layout_7.startAnimation(LayoutAnimation_7);


        final List<String> listSpinner = new ArrayList<String>();
        listSpinner.add("الاولي");
        listSpinner.add("الثانية");
        listSpinner.add("الثالثة");
        listSpinner.add("الرابعه");   //Data

        ArrayAdapter adapterSpinner = new ArrayAdapter<>(
                SettingActivity.this,R.layout.spinner_item,listSpinner

        );
       spinnerAction.setAdapter(adapterSpinner);
       spinnerAction.setSelection(LastAction);
       spinnerAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

               editorSettingApp.putInt("ActionRecycleView",position)
                       .commit();
               Toast.makeText(SettingActivity.this," تم تحديد الحركة "+listSpinner.get(position),Toast.LENGTH_LONG).show();
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });



        String[] fonts={"الخط1","الخط2","الخط3","الخط4","الخط5","الخط6",};

  ArrayAdapter<String> adapter = new ArrayAdapter<String>
          (this,android.R.layout.simple_spinner_dropdown_item,fonts)
  {
     public View getView(int position ,View convertView ,ViewGroup parent)

     {
         View v =super.getView(position,convertView,parent);
         Typeface externalFont=Typeface.createFromAsset(getContext().getAssets()
         ,"font/"+(position+1)+".otf");

         ((TextView)v).setTextColor(Color.WHITE);
         ((TextView)v).setTypeface(externalFont);
         return v;

     }

      public View getDropDownView(int position ,View convertView ,ViewGroup parent)

      {
          View v =super.getDropDownView(position,convertView,parent);
          Typeface externalFont=Typeface.createFromAsset(getContext().getAssets()
                  ,"font/"+(position+1)+".otf");

          ((TextView)v).setTextColor(Color.RED);
          ((TextView)v).setTypeface(externalFont);
          v.setBackgroundColor(Color.CYAN);
          return v;

      }



  };

        spinnerFontType.setAdapter(adapter);
        spinnerFontType.setSelection(LastFontType);
        spinnerFontType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                editorSettingApp.putInt("LastFontType",position).commit();
                editorSettingApp.putInt("TextType",position+1).commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }

    private void openColorPicker() {

        AmbilWarnaDialog MyColorText = new AmbilWarnaDialog(this, Textcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {

                Textcolor = color ;
                changeTextColor.setBackgroundColor(Textcolor);
                editorSettingApp.putInt("TextColor",Textcolor)
                        .commit();


            }
        });
        MyColorText.show();
    }
    ///*******************************************************************************************************


}
