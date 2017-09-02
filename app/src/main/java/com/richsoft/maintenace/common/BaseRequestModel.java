package com.richsoft.maintenace.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;

import com.richsoft.maintenace.login.ui.LoginActivity;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import id.zelory.compressor.Compressor;
import ren.solid.library.activity.base.BaseActivity;
import ren.solid.library.net.BaseSingleListener;
import ren.solid.library.net.CallServer;
import ren.solid.library.net.HttpListener;
import ren.solid.library.utils.LogUtils;
import ren.solid.library.utils.SPUtil;

/**
 * 作者：周麟 on 2016/9/8 20:41
 * <p/>
 * 邮箱：chengzehao@163.com
 */
public abstract class BaseRequestModel {

    protected void requestServer(final BaseActivity activity, int what, Request<String> request, final BaseSingleListener listener, boolean canCancel, boolean isLoading,boolean addToken) {
        request.setCancelSign(activity);
        if(addToken){
            request.addHeader("token",SPUtil.getInstance().getAppToken());
        }
        CallServer.getRequestInstance().add(activity, what, request, new HttpListener<String>() {
            @Override
            public void onSucceed(int what, Response<String> response) {
                LogUtils.i("zhoul", "网络连接成功:"+response.get());
                //将服务器返回的字符串数据封装未Json对象
                try {
                    JSONObject obj = new JSONObject(response.get());
                    if (obj.has("state")) {
                        JSONObject stateObj = obj.getJSONObject("state");
                        switch (stateObj.getInt("st")) {
                            case 0:
                                if(stateObj.has("token")){
                                    SPUtil.getInstance().saveAppToken(stateObj.getString("token"));
                                }
                                //请求返回成功，解析content，交由子类实现
                                parseContent(obj.getString("content"), listener);
                                break;
                            case 2:
                                //登录超时
                                listener.onError(new Exception(stateObj.getString("msg")));
                                activity.showMsgShortTime("登录超时，请您重新登录！");
                                Intent it = new Intent();
                                it.setClass(activity, LoginActivity.class);
                                activity.startActivity(it);
                                break;
                            default:
                                //st返回异常
                                listener.onError(new Exception(stateObj.getString("msg")));
                                break;
                        }
                    } else {
                        listener.onError(new Exception(response.get()));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    listener.onException(e);
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                LogUtils.i("zhoul","网络连接失败："+response.getException().getMessage());
                listener.onException(response.getException());
            }
        }, canCancel, isLoading);
    }

    protected abstract void parseContent(String content, BaseSingleListener listener);

    /**
     * 相片在上传之前需要进行压缩
     */
    public File compressImage(File actualImage, Context context) {

        if(actualImage.getPath().endsWith(".webp")){
            return actualImage;
        }else {
            File compressedImage = new Compressor.Builder(context)
                    .setMaxWidth(720)
                    .setMaxHeight(1280)
                    .setQuality(90)
                    .setCompressFormat(Bitmap.CompressFormat.WEBP)
                    .setDestinationDirectoryPath(Environment.getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES).getAbsolutePath())
                    .build()
                    .compressToFile(actualImage);
            return compressedImage;
        }
    }

}
