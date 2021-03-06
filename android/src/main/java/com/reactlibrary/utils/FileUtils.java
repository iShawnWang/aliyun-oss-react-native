package com.reactlibrary.utils;

import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {

    /**
     * copy file
     * @param context
     * @param srcUri
     * @param dstFile
     */
    public static void copy(Context context, Uri srcUri, File dstFile) {
        try {
            InputStream is = context.getContentResolver().openInputStream(srcUri);
            if (is == null) return;
            OutputStream fos = new FileOutputStream(dstFile);

            byte[] bytes = new byte[1024];

            int count = is.read(bytes, 0, 1024);
            try {
                while (count > 0){
                    fos.write(bytes, 0, count);
                    count = is.read(bytes, 0, 1024);
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally{
                // close inputstream
                fos.close();
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * getFileName
     * @param uri
     * @return
     */
    public static String getFileName(Uri uri) {
        if (uri == null) return null;
        String fileName = null;
        String path = uri.getPath();
        int cut = path.lastIndexOf('/');
        if (cut != -1) {
            fileName = path.substring(cut + 1);
        }
        return fileName;
    }

    /**
     * getFilePathFromURI
     * @param context
     * @param contentUri
     * @return
     */
    public static String getFilePathFromURI(Context context, Uri contentUri) {
        try {
            return contentUri.toString().substring(6);
        } catch (Exception e) {
        }
        return null;
    }
}
