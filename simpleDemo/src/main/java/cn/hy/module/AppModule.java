package cn.hy.module;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.hy.activity.MainActivity;
import io.dcloud.feature.uniapp.annotation.UniJSMethod;
import io.dcloud.feature.uniapp.bridge.UniJSCallback;
import io.dcloud.feature.uniapp.common.UniModule;

public class AppModule extends UniModule {

    public static String TAG = "AppModule";

    public static int REQUEST_CODE = 1000;

    @UniJSMethod(uiThread = true)
    public void showToast(String msg){
        if(mUniSDKInstance!= null && mUniSDKInstance.getContext() instanceof Activity){
            Toast.makeText(mUniSDKInstance.getContext(),msg,Toast.LENGTH_SHORT).show();
        }
    }

    @UniJSMethod(uiThread = true)
    public void sendMsg(String message, UniJSCallback callback){
        if(mUniSDKInstance!= null && mUniSDKInstance.getContext() instanceof Activity){
            message = message + "finished";
            callback.invoke(message);
            Map<String, Object> params = new HashMap<>();
            params.put("code","0");
            mUniSDKInstance.fireGlobalEventCallback("MyEvent",params);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @UniJSMethod(uiThread = true)
    public void gotoNativePage(){
        if(mUniSDKInstance!= null && mUniSDKInstance.getContext() instanceof Activity){
            Intent intent = new Intent(mUniSDKInstance.getContext(), MainActivity.class);
           ((Activity)mUniSDKInstance.getContext()).startActivityForResult(intent,REQUEST_CODE);
        }
    }

}
