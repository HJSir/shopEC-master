package com.example.mi.ui.camera;

import android.net.Uri;

import com.example.mi.delegates.PermissionCheckerDelegate;
import com.example.mi.utils.file.FileUtil;


/**
 * Created by jian
 */

public class MiCamera {

    public static Uri createCropFile() {
        return Uri.parse
                (FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
