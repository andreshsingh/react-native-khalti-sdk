package com.reactnativekhaltisdk;

import androidx.annotation.NonNull;

import android.app.Activity;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.bridge.WritableMap;

import com.khalti.checkout.helper.Config;
import com.khalti.checkout.helper.KhaltiCheckOut;
import com.khalti.checkout.helper.PaymentPreference;
import com.khalti.checkout.helper.OnCheckOutListener;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class KhaltiSdkModule extends ReactContextBaseJavaModule {
   private Config config;
   private Config.Builder builder;
   private final ReactApplicationContext reactContext;
   private List<PaymentPreference> defaultPaymentPreferences;

  KhaltiSdkModule(ReactApplicationContext reactContext) {
       super(reactContext);
       this.reactContext = reactContext;
   }

    @Override
    public String getName() {
        return "KhaltiSdk";
    }

    // Example method
    // See https://reactnative.dev/docs/native-modules-android
    @ReactMethod
    public void startKhaltiSdk(String public_key, String product_name, String product_id, String product_url, Double amount, Boolean eBanking, ReadableMap additional_data  , Promise promise) {
       Log.e("public_key", public_key);
       Log.e("product_name", product_name);
       Log.e("product_id", product_id);
       Log.e("product_url", product_url);
       Log.e("amount", String.valueOf(amount));
       Log.e("eBanking", String.valueOf(eBanking));
       Log.e("additional_data", String.valueOf(additional_data));

      Config eBankingConfig = new Config.Builder(public_key, product_id, product_name, amount.longValue(), new OnCheckOutListener() {
         @Override
         public void onError(@NonNull String action, @NonNull Map<String, String> errorMap) {
           promise.reject("PAYMENT_ERROR", "Payment was unsuccessful");
         }

         @Override
         public void onSuccess(@NonNull Map<String, Object> data) {
           WritableMap map = Arguments.createMap();
           map.putString("result", String.valueOf(data));
           promise.resolve(map);
         }
       })
         .paymentPreferences(new ArrayList<PaymentPreference>() {{
           add(PaymentPreference.KHALTI);
            if(eBanking) {
              add(PaymentPreference.EBANKING);
            }
         }})
           .additionalData(additional_data.toHashMap())
           .productUrl(product_url)
           .build();
      Activity currentActivity = getCurrentActivity();

       KhaltiCheckOut khaltiCheckOut = new KhaltiCheckOut(currentActivity,eBankingConfig);
       khaltiCheckOut.show();

    }
}
