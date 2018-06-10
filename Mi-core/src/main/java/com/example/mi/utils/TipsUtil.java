package com.example.mi.utils;

import android.content.Context;
import android.view.View;

import com.example.mi.app.Mi;

import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by jian
 */

public class TipsUtil {
   public static MaterialDialog mMaterialDialog = null;
    public static MaterialDialog getInstance(Context context){
        if(mMaterialDialog!=null)
        return mMaterialDialog;
        else
        {
            mMaterialDialog = new MaterialDialog(context);
            mMaterialDialog.setPositiveButton("确认", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mMaterialDialog.dismiss();

                }
            });
        }
       return mMaterialDialog;
    }



}
