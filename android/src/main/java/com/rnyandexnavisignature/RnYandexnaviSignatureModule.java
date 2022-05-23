package com.rnyandexnavisignature;

import android.content.Context;
import android.net.Uri;
import android.util.Base64;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;

import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;

@ReactModule(name = RnYandexnaviSignatureModule.NAME)
public class RnYandexnaviSignatureModule extends ReactContextBaseJavaModule {
    public static final String NAME = "RnYandexnaviSignature";

    public RnYandexnaviSignatureModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    @NonNull
    public String getName() {
        return NAME;
    }

    @ReactMethod
    public void sign(String url, String client, String key, Promise promise) {
      try {
        Uri uri = Uri.parse(url).buildUpon()
          .appendQueryParameter("client", client).build();

        uri = uri.buildUpon()
          .appendQueryParameter("signature", sha256rsa(key, uri.toString()))
          .build();

        promise.resolve(uri.toString());
      } catch (Exception e) {
        promise.reject(e);
      }
    }

    private String sha256rsa(String key, String data) throws SecurityException {
      String trimmedKey = key.replaceAll("-----\\w+ PRIVATE KEY-----", "")
        .replaceAll("\\s", "");

      try {
        byte[]         result    = Base64.decode(trimmedKey, Base64.DEFAULT);
        KeyFactory     factory   = KeyFactory.getInstance("RSA");
        EncodedKeySpec keySpec   = new PKCS8EncodedKeySpec(result);
        Signature      signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(factory.generatePrivate(keySpec));
        signature.update(data.getBytes());

        byte[] encrypted = signature.sign();
        return Base64.encodeToString(encrypted, Base64.NO_WRAP);
      } catch (Exception e) {
        throw new SecurityException("Error calculating cipher data. SIC!");
      }
    }

    public static native String nativeSign(String url, String client, String key);
}
