package com.example.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Created by jian
 */
public enum EcIcons implements Icon {
    icon_down_menu('\ue749'),
    icon_right_menu('\ue6f8'),
    icon_point('\ue608'),
    icon_gou('\ue611'),
    icon_shop('\ue69a'),
    icon_activity('\ue716'),
    icon_hotgoods('\ue60a'),
    icon_study('\ue628'),
    icon_newgoods('\ue615'),
    icon_news('\ue685'),
    icon_vip('\ue642'),
    icon_accredit('\ue60b'),
    icon_team('\ue610'),
    icon_wallet('\ue60c'),
    icon_feedback('\ue607'),
    icon_setting('\ue65b'),
    icon_address('\ue625'),
    icon_check_in('\ue604'),
    icon_check_out('\ue605'),
    icon_check_doing('\ue606'),
    icon_ali_pay('\ue609');

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }
}
