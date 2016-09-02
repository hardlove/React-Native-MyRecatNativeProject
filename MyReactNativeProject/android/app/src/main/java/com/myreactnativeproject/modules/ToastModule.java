package com.myreactnativeproject.modules;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Toast;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.IllegalViewOperationException;
import com.facebook.react.uimanager.PixelUtil;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nullable;

/**
 * Created by Administrator on 2016/9/2 0002.
 */
public class ToastModule extends ReactContextBaseJavaModule {

    private static final String TAG = "ToastModule";
    private static final String DURATION_SHORT_KEY = "SHORT";
    private static final String DURATION_LONG_KEY = "LONG";
    private Activity activity;
    public ToastModule(ReactApplicationContext reactContext) {
        super(reactContext);
        activity = getCurrentActivity();
        Log.i(TAG, "the activity is:" + activity);
    }

    @Override
    public String getName() {
        return "ToastAndroid_M";
    }

    @Nullable
    @Override
    public Map<String, Object> getConstants() {
        Map<String, Object> map = new HashMap<>();

        map.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
        map.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
        return map;
    }

    @ReactMethod
    public void showToast(String msg, int duration) {
        Toast.makeText(getReactApplicationContext(), msg, duration).show();
    }

    @ReactMethod
    public void showDialog(String title, String msg, final Callback okCallback, final Callback cancelCallBack) {
        new AlertDialog.Builder(getCurrentActivity()).setTitle(title).setMessage(msg).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                okCallback.invoke("click ok.",which);

                WritableMap params = Arguments.createMap();
                params.putString("key","hello js.");

                sendEven(getReactApplicationContext(),"JAVA",params);
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancelCallBack.invoke("click cancel.");
            }
        }).create().show();
    }

    @ReactMethod
    public void measureLayout(
            int tag,
            int ancestorTag,
            Promise promise) {
        try {

            WritableMap map = Arguments.createMap();

            map.putDouble("relativeX", PixelUtil.toDIPFromPixel(10));
            map.putDouble("relativeY", PixelUtil.toDIPFromPixel(11));
            map.putDouble("width", PixelUtil.toDIPFromPixel(12));
            map.putDouble("height", PixelUtil.toDIPFromPixel(13));

            promise.resolve(map);
        } catch (IllegalViewOperationException e) {
            promise.reject(e.getMessage());
        }
    }

    /**
     * 发送事件到JavaScript
     * @param reactContext
     * @param eventName
     * @param params
     */
    private void sendEven(ReactContext reactContext,
                          String eventName,
                          @Nullable WritableMap params) {
        reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit(eventName,params);
    }

    @Override
    public boolean canOverrideExistingModule() {
        return true;
    }
    /**
     *
     *
     * 参数类型#
     下面的参数类型在@ReactMethod注明的方法中，会被直接映射到它们对应的JavaScript类型。

     Boolean -> Bool
     Integer -> Number
     Double -> Number
     Float -> Number
     String -> String
     Callback -> function
     ReadableMap -> Object
     ReadableArray -> Array
     参阅ReadableMap和ReadableArray。
     */
}
