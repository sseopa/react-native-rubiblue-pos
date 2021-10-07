
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
    final String receiptContent = receiptText;
    WeiposImpl.as().init(this.reactContext, new OnInitListener() {
      @Override
      public void onInitOk() {
          RunPrintJob(receiptContent);

      }

      @Override
      public void onError(String s) {
        //listener.onInitializeError(new Error(s));
        RunPrintJob(receiptContent);
      }


      @Override
      public void onDestroy() {
        // listener.onPrinterClosed("");
      }
    });
  }
  public void RunPrintJob(String receiptContent){
     FontSize size = FontSize.MEDIUM;
     FontStyle style = FontStyle.NORMAL;

    String deviceInfo = WeiposImpl.as().getDeviceInfo();
    LatticePrinter latticePrinter = WeiposImpl.as().openLatticePrinter();
    String[] receiptContents = receiptContent.split("\n", 0);
    for (String line : receiptContents) {
      latticePrinter.printText(line, LatticePrinter.FontFamily.SONG, size, style);
      latticePrinter.printText("\n", FontFamily.SONG,
              FontSize.MEDIUM, FontStyle.NORMAL);
    }
    if(receiptContents.length == 0) {
      latticePrinter.printText(receiptContent, LatticePrinter.FontFamily.SONG, size, style);
    }
    latticePrinter.submitPrint();
  }
}