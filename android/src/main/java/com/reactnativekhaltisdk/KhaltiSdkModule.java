package com.reactnativekhaltisdk;

import androidx.annotation.NonNull;
import android.util.Log;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

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
  private Map<String, Object> tabSelectedMap = new HashMap<>();

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
    public void startKhaltiSdk(String public_key,String product_name,String product_id,String product_url,Long amount,Boolean eBanking,  Promise promise) {

      Config eBankingConfig = new Config.Builder(public_key, product_id, product_name, amount, new OnCheckOutListener() {
        @Override
        public void onError(@NonNull String action, @NonNull Map<String, String> errorMap) {
          Log.i(action, errorMap.toString());
        }

        @Override
        public void onSuccess(@NonNull Map<String, Object> data) {
          Log.i("success", data.toString());
        }
      })
        .paymentPreferences(new ArrayList<PaymentPreference>() {{
          add(eBanking?PaymentPreference.EBANKING:PaymentPreference.KHALTI);
        }})
          .additionalData(tabSelectedMap)
          .productUrl(product_url)
          .mobile("9800000000")
          .build();

      KhaltiCheckOut khaltiCheckOut = new KhaltiCheckOut(this.reactContext,eBankingConfig);
      khaltiCheckOut.show();

    }
}
