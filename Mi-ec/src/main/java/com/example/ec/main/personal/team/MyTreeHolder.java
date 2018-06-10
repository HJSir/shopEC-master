package com.example.ec.main.personal.team;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miec.R;
import com.example.miec.R2;
import com.joanzapata.iconify.widget.IconTextView;
import com.unnamed.b.atv.model.TreeNode;

import butterknife.BindView;


/**
 * Created by jian
 */

public class MyTreeHolder extends TreeNode.BaseNodeViewHolder<TreeItem>  {


    public MyTreeHolder(Context context) {
        super(context);
    }


    @Override
    public View createNodeView(TreeNode node, final TreeItem value) {


        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.item_tree_menu, null, false);
        final LinearLayout layout = (LinearLayout) view.findViewById(R.id.ll_tree);
        view.setPadding(30*value.getPlies(),view.getPaddingTop(),view.getPaddingRight(),view.getPaddingBottom());
        final IconTextView iconTextView = (IconTextView) view.findViewById(R.id.tv_tree_node_icon);
        final TextView levelText = (TextView) view.findViewById(R.id.tv_tree_node_level);
        final TextView nameText = (TextView) view.findViewById(R.id.tv_tree_node_name);
        final TextView moneyText = (TextView) view.findViewById(R.id.tv_tree_node_money);
//        TextView tvValue = (TextView) view.findViewById(R.id.tv_tree_node_value);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {


                if (value.getIcon() == 1) { //默认为1 即没被点击
                    iconTextView.setText("{icon-down-menu}");
                  view.setBackgroundColor(Color.GRAY);
                    value.setIcon(2);
                } else {
                    iconTextView.setText("{icon-right-menu}");
                    view.setBackgroundColor(Color.WHITE);
                    value.setIcon(1);
                }
                return false;
            }
        });


        nameText.setText(value.getName());
        levelText.setText(value.getLevel());
        moneyText.setText(value.getMoney());

        return view;
    }



}
