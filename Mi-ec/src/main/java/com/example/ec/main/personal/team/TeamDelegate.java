package com.example.ec.main.personal.team;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mi.delegates.MiDelegate;
import com.example.miec.R;
import com.example.miec.R2;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by jian
 */

public class TeamDelegate extends MiDelegate {


    //头部 **
    @BindView(R2.id.tv_top_mid)
    TextView topMidText = null;

    @OnClick(R2.id.icon_top_left)
    void topFinsh() {
        getSupportDelegate().pop();

    }

    //**
    @BindView(R2.id.cl_delegate_personal_team)
    LinearLayout containerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_team;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        topMidText.setText("我的团队");
        TreeNode root = TreeNode.root();


//        TreeNode child = new TreeNode(new TreeItem(1, "")).setViewHolder(new MyTreeHolder(getContext()));

        TreeNode parent = new TreeNode(new TreeItem( "我","总代理","500元",0)).setViewHolder(new MyTreeHolder(getContext()));
        TreeNode child0 = new TreeNode(new TreeItem( "廖正生","总代理","500元",1)).setViewHolder(new MyTreeHolder(getContext()));
        TreeNode child1 = new TreeNode(new TreeItem("徐凯旋","会员","505元",1)).setViewHolder(new MyTreeHolder(getContext()));
        TreeNode child2 = new TreeNode(new TreeItem( "王吉祥","区域代理","0元",2)).setViewHolder(new MyTreeHolder(getContext()));
        TreeNode child3 = new TreeNode(new TreeItem( "圣兽山","会员","200元",2)).setViewHolder(new MyTreeHolder(getContext()));
        TreeNode child4 = new TreeNode(new TreeItem( "彭终止","区域代理","0.1元",2)).setViewHolder(new MyTreeHolder(getContext()));
        parent.addChildren(child0, child1);
        child1.addChildren(child2, child3);
        child0.addChild(child4);
        root.addChild(parent);
        AndroidTreeView tView = new AndroidTreeView(getContext(), root);
//        MyTreeHolder myTreeHolder =  new MyTreeHolder(getContext());
//        tView.setDefaultViewHolder(new MyTreeHolder(getContext()));
//        tView.setDefaultNodeClickListener();
        containerView.addView(tView.getView());

    }

    @Override
    public void post(Runnable runnable) {

    }

}
