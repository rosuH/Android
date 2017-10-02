package me.ghui.v2er.general;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

import me.ghui.v2er.network.APIService;

/**
 * Created by ghui on 13/09/2017.
 */

@GlideModule
public class MyAppGllideModule extends AppGlideModule {

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        OkHttpUrlLoader.Factory factory = new OkHttpUrlLoader.Factory(APIService.httpClient());
        glide.getRegistry().replace(GlideUrl.class, InputStream.class, factory);
    }

}
