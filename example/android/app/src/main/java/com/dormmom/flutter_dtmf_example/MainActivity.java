package com.dormmom.flutter_dtmf_example;

import android.os.Bundle;

import io.flutter.app.FlutterActivity;
import io.flutter.plugins.GeneratedPluginRegistrant;

public class MainActivity extends FlutterActivity {
//  @Override
//  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
//    GeneratedPluginRegistrant.registerWith(flutterEngine);
//  }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GeneratedPluginRegistrant.registerWith(this);
    }
}
