package com.example.ec.main.personal.profile;

import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.SimpleClickListener;
import com.example.ec.main.personal.list.ListBean;
import com.example.mi.delegates.MiDelegate;
import com.example.mi.net.RestClient;
import com.example.mi.net.callback.ISuccess;
import com.example.mi.utils.callback.CallbackManager;
import com.example.mi.utils.callback.CallbackType;
import com.example.mi.utils.callback.IGlobalCallback;
import com.example.miec.R;


/**
 * Created by jian
 */

public class UserProfileClickListener extends SimpleClickListener {

    private final UserProfileDelegate DELEGATE;

    private String[] mGenders = new String[]{"男", "女", "保密"};

    public UserProfileClickListener(UserProfileDelegate DELEGATE) {
        this.DELEGATE = DELEGATE;
    }

    @Override
    public void onItemClick(final BaseQuickAdapter adapter, final View view, int position) {
        final ListBean bean = (ListBean) baseQuickAdapter.getData().get(position);
        final int id = bean.getId();
        switch (id) {
            case 1:
//                开始照相机或选择图片
                CallbackManager.getInstance()
                        .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                            @Override
                            public void executeCallback(Uri args) {
//                                LatteLogger.d("ON_CROP", args);
                                final ImageView avatar = (ImageView) view.findViewById(R.id.img_arrow_avatar);
                                Glide.with(DELEGATE)
                                        .load(args)
                                        .into(avatar);

                                RestClient.builder()
                                        .url(UploadConfig.UPLOAD_IMG)
                                        .loader(DELEGATE.getContext())
                                        .file(args.getPath())
                                        .success(new ISuccess() {
                                            @Override
                                            public void onSuccess(String response) {

                                                final String path = JSON.parseObject(response).getJSONObject("result")
                                                        .getString("path");

                                                //通知服务器更新信息
                                                RestClient.builder()
                                                        .url("user_profile.php")
                                                        .params("avatar", path)
                                                        .loader(DELEGATE.getContext())
                                                        .success(new ISuccess() {
                                                            @Override
                                                            public void onSuccess(String response) {
                                                                //获取更新后的用户信息，然后更新本地数据库
                                                                //没有本地数据的APP，每次打开APP都请求API，获取信息
                                                            }
                                                        })
                                                        .build()
                                                        .post();
                                            }
                                        })
                                        .build()
                                        .upload();
                            }
                        });
                DELEGATE.startCameraWithCheck();
                break;
            case 2:
                final MiDelegate nameDelegate = bean.getDelegate();
                DELEGATE.getSupportDelegate().start(nameDelegate);
                break;
            case 3:
                getGenderDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
//                        textView.setText(mGenders[which]);
//                        dialog.cancel();
                    }
                });
                break;
            case 4:
//                final DateDialogUtil dateDialogUtil = new DateDialogUtil();
//                dateDialogUtil.setDateListener(new DateDialogUtil.IDateListener() {
//                    @Override
//                    public void onDateChange(String date) {
//                        final TextView textView = (TextView) view.findViewById(R.id.tv_arrow_value);
//                        textView.setText(date);
//                    }
//                });
//                dateDialogUtil.showDialog(DELEGATE.getContext());
                break;
            default:
                break;
        }
    }

    private void getGenderDialog(DialogInterface.OnClickListener listener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(DELEGATE.getContext());
        builder.setSingleChoiceItems(mGenders, 0, listener);
        builder.show();
    }

    @Override
    public void onItemLongClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {

    }
}
