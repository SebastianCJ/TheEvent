package com.example.gatsu.theevent;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Gatsu on 11/14/2016.
 */
public class CustomFontsLoader {

    public static final int Black =   0;
    public static final int Bold =   1;
    public static final int Hairline =   2;
    public static final int Light =   3;
    public static final int Regular =   4;

    private static final int NUM_OF_CUSTOM_FONTS = 5;

    private static boolean fontsLoaded = false;

    private static Typeface[] fonts = new Typeface[5];

    private static String[] fontPath = {
            "fonts/Montserrat-Black.otf",
            "fonts/Montserrat-Bold.otf",
            "fonts/Montserrat-Hairline.otf",
            "fonts/Montserrat-Light.otf",
            "fonts/Montserrat-Regular.otf"
    };


    /**
     * Returns a loaded custom font based on it's identifier.
     *
     * @param context - the current context
     * @param fontIdentifier = the identifier of the requested font
     *
     * @return Typeface object of the requested font.
     */
    public static Typeface getTypeface(Context context, int fontIdentifier) {
        if (!fontsLoaded) {
            loadFonts(context);
        }
        return fonts[fontIdentifier];
    }


    private static void loadFonts(Context context) {
        for (int i = 0; i < NUM_OF_CUSTOM_FONTS; i++) {
            fonts[i] = Typeface.createFromAsset(context.getAssets(), fontPath[i]);
        }
        fontsLoaded = true;

    }
}