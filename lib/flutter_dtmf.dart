import 'dart:async';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class FlutterDtmf {
  static const MethodChannel _channel = MethodChannel('flutter_dtmf');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<void> playTone(
      {@required String digits, double samplingRate}) async {
    assert(digits != null);
    final Map<String, Object> args = <String, dynamic>{
      'digits': digits,
      'samplingRate': samplingRate
    };
    await _channel.invokeMethod('playTone', args);
  }
  static Future<void> playCallWaiting() async {
    await _channel.invokeMethod('playCallWaiting');
  }
  static Future<void> playCallAlert() async {
    await _channel.invokeMethod('playCallAlert');
  }
  static Future<void> playCallTerm() async {
    await _channel.invokeMethod('playCallTerm');
  }
}
