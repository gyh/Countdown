package com.yangyi.app.gcountdown;

import android.content.Context;

/**
 * Created by GYH on 2015/12/17.
 */
public class NavigationIn {

    /**
     * 跳转TimeAndTast
     * @param context
     * @param object
     */
    public static void navigatiionTimeAndTast(Context context,Object object){
        TimerAndTastActivity.startIntentTimeAndTast(context, (String) object);
    }

    public static void navigationTimeAndHandler(Context context,Object object){
        TimerAndHandlerActivity.startIntentTastHandler(context, (String) object);
    }

    public static void navigationHandlerAc(Context context,Object object){
        HandlerMsgActivity.startIntentHandlerActivity(context, (String) object);
    }

    public static void navigationHandlerThre(Context context,Object object){
        HandlerThreActivity.startIntentHandlerThre(context, (String) object);
    }

    public static void navigationHandlerRun(Context context,Object object){
        HandlerRunActivity.startIntentHandlerRun(context, (String) object);
    }
}


