package com.learnus.mytsabe7.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.learnus.mytsabe7.Model.AzkarName;
import com.learnus.mytsabe7.R;

import java.util.List;

public class RecyeclerElzkar extends RecyclerView.Adapter<RecyeclerElzkar.ZekrHolder> {
  int TextType ,TextColor  ;
  SharedPreferences SettingApp;
  SharedPreferences.Editor  editorSettingApp;

  List<AzkarName> zekrList;
  Context mContext;


  public RecyeclerElzkar(List<AzkarName> zekrList,Context mContext) {
    this.zekrList = zekrList;
    this.mContext=mContext;

/////////////////////////////////////////
    SettingApp = mContext.getSharedPreferences("Setting", Context.MODE_PRIVATE); /// make file
    editorSettingApp = SettingApp.edit();
    TextType = SettingApp.getInt("TextType",1);
    TextColor=SettingApp.getInt("TextColor",0xffffffff);
//////////////////////////////////////////////////////
  }

  @NonNull
  @Override
  public ZekrHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
   View row= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyecler_itme,viewGroup,false);

        return new ZekrHolder(row)  ;
  }

  @Override
  public void onBindViewHolder(@NonNull ZekrHolder zekrHolder, int i) {

    zekrHolder.NumberZekr.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition));
    //zekrHolder.nameZekr.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition2));
    Typeface extenalfont_Adpter = Typeface.createFromAsset(mContext.getAssets(),"font/"+TextType+".otf");


    AzkarName Azkr = zekrList.get(i);
    zekrHolder.nameZekr.setText(Azkr.nameZekr);
    zekrHolder.nameZekr.setTypeface(extenalfont_Adpter);
    zekrHolder.nameZekr.setTextColor(TextColor);
    zekrHolder.NumberZekr.setText(Azkr.NumberTotal);

  }

  @Override
  public int getItemCount()
  {
    return zekrList.size();
  }

  class ZekrHolder extends RecyclerView.ViewHolder{
    TextView nameZekr , NumberZekr;

    public ZekrHolder(@NonNull View itemView) {
      super(itemView);

      nameZekr =(TextView)itemView.findViewById(R.id.textZerkr);
      NumberZekr = (TextView)itemView.findViewById(R.id.textNumberZekr);


    }
  }

}
