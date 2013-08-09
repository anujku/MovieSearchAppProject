package com.anuj.android.moviesearchapp.utils;

/**
 * Created by anuj on 8/9/13.
 */

import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static void closeStreamQuietly(InputStream inputStream) {
        try {
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            // ignore exception
        }
    }

}