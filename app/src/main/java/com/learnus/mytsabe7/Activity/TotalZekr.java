package com.learnus.mytsabe7.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.learnus.mytsabe7.Adapter.RecyeclerElzkar;
import com.learnus.mytsabe7.Model.AzkarName;
import com.learnus.mytsabe7.R;

import java.util.ArrayList;
import java.util.List;

public class TotalZekr extends AppCompatActivity {
    SharedPreferences SettingApp;
    SharedPreferences.Editor  editorSettingApp;
     private List<AzkarName> NameAzkar = new ArrayList<>();
  RecyclerView myRecyclerView ;
    private static String Myzekr[]= {"الحمد لله","استغفر الله","الله اكبر","سبحان الله","لا اله الا الله وحده لاشريك له له الملك وله الحمد وهو علي كل شئ قدير","اللهم صلي علي سيدنا محمد","سبحان الله وبحمده سبحان الله العظيم"};
    private static String NumberZekr[]={"0","0","0","0","0","0","0"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_zekr);

        SettingApp = getSharedPreferences("Setting", Context.MODE_PRIVATE); /// make file
        editorSettingApp = SettingApp.edit();

        final int numberAction = SettingApp.getInt("ActionRecycleView",0);

        for(int i =0; i<Myzekr.length;i++){

            AzkarName ZekrList = new AzkarName( Myzekr[i],NumberZekr[i]);
            NameAzkar.add(ZekrList);


        }
        myRecyclerView=(RecyclerView)findViewById(R.id.recyele);




       riunAnimation(myRecyclerView,numberAction);





    }

    private void riunAnimation(RecyclerView myRecyclerView, int type) {


        Context context= myRecyclerView.getContext();
        LayoutAnimationController controller = null;

        if(type==0){
      controller=AnimationUtils.loadLayoutAnimation(context,R.anim.layout_fall_down);

       }else if(type==1){

            controller=AnimationUtils.loadLayoutAnimation(context,R.anim.layout_slid_from_left);

        }else if(type==2){

            controller=AnimationUtils.loadLayoutAnimation(context,R.anim.layout_slid_from_right);

        }else  if(type==3){
            controller = AnimationUtils.loadLayoutAnimation(context,R.anim.layout_slide_from_bottom);
        }


        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyeclerElzkar adapter = new RecyeclerElzkar(NameAzkar,this);
        myRecyclerView.setAdapter(adapter);

        ///
        myRecyclerView.setLayoutAnimation(controller);
        myRecyclerView.getAdapter().notifyDataSetChanged();
        myRecyclerView.scheduleLayoutAnimation();

    }


}
