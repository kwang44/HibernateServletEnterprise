package com.WangWick.util;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;

public enum GSonUtil {
    ;
    private Gson g = new Gson();
    public static String parseHttpBody(Reader r) throws IOException {
        char[] buffer = new char[4096];
        StringBuilder builder = new StringBuilder();
        int numChars;

        while ((numChars = r.read(buffer)) >= 0) {
            builder.append(buffer, 0, numChars);
        }

        return builder.toString();



    }

}
