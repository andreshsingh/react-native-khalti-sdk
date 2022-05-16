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
import com.facebook.react.bridge.WritableMap;

import com.khalti.checkout.helper.Config;
import com.khalti.checkout.helper.KhaltiCheckOut;
import com.khalti.checkout.helper.PaymentPreference;
import com.khalti.checkout.helper.OnCheckOutListener;
import com.khalti.checkout.helper.OnCancelListener;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

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

  @ReactMethod
  public void startKhaltiSdk(String public_key, String product_name, String product_id, String product_url, Double amount, ReadableMap additional_data, String KhaltiAppScheme, Promise promise) {

    Config eBankingConfig = new Config.Builder(public_key, product_id, product_name, amount.longValue(), new OnCheckOutListener() {
      @Override
      public void onError(@NonNull String action, @NonNull Map<String, String> errorMap) {
        Log.e("Khalti", String.valueOf(errorMap));
//           promise.reject("PAYMENT_ERROR", "Payment was unsuccessful");
      }

      @Override
      public void onSuccess(@NonNull Map<String, Object> data) {
        WritableMap map = Arguments.createMap();
        for (String key : data.keySet()) {
          Object value = data.get(key);
          System.out.println("Key = " + key + ", Value = " + value);
          if (value instanceof Boolean) {
            map.putBoolean(key, (Boolean) value);
          } else if (value instanceof Integer) {
            map.putInt(key, (Integer) value);
          } else if (value instanceof Double) {
            map.putDouble(key, (Double) value);
          } else if (value instanceof String) {
            map.putString(key, (String) value);
          } else {
            map.putString(key, value.toString());
          }
        }
        promise.resolve(map);
      }
    })
      .paymentPreferences(new ArrayList<PaymentPreference>() {{
        add(PaymentPreference.KHALTI);
      }})
      .onCancel(new OnCancelListener() {
        @Override
        public void onCancel() {
          promise.reject("PAYMENT_CANCELED", "Payment cancelled by the user");
        }
      })
      .additionalData(additional_data.toHashMap())
      .productUrl(product_url)
      .build();
    Activity currentActivity = getCurrentActivity();

    KhaltiCheckOut khaltiCheckOut = new KhaltiCheckOut(currentActivity, eBankingConfig);
    khaltiCheckOut.show();

  }
}
