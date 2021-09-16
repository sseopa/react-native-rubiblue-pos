
package com.reactlibrary;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;

import org.json.JSONException;
import org.json.JSONObject;

import cn.weipass.pos.sdk.IPrint.*;
import cn.weipass.pos.sdk.LatticePrinter;
import cn.weipass.pos.sdk.LatticePrinter.*;
import cn.weipass.pos.sdk.Weipos.*;
import cn.weipass.pos.sdk.impl.WeiposImpl;

public class RNReactNativeRubibluePosModule extends ReactContextBaseJavaModule {

  private final ReactApplicationContext reactContext;

  public RNReactNativeRubibluePosModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @Override
  public String getName() {
    return "RNReactNativeRubibluePos";
  }

  @ReactMethod
  public void Print(String receiptText, Promise promise) {
    final FontSize size = FontSize.MEDIUM;
    final FontStyle style = FontStyle.NORMAL;
    final String receiptContent = receiptText;
    WeiposImpl.as().init(this.reactContext, new OnInitListener() {
      @Override
      public void onInitOk() {
          String deviceInfo = WeiposImpl.as().getDeviceInfo();
        LatticePrinter latticePrinter = WeiposImpl.as().openLatticePrinter();
        latticePrinter.printText(receiptContent, LatticePrinter.FontFamily.SONG, size, style);
        latticePrinter.submitPrint();

      }

      @Override
      public void onError(String s) {
        //listener.onInitializeError(new Error(s));
      }

      @Override
      public void onDestroy() {
        // listener.onPrinterClosed("");
      }
    });
  }
}