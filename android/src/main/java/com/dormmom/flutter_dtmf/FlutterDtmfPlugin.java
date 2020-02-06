package com.dormmom.flutter_dtmf;

import android.media.AudioManager;
import android.media.ToneGenerator;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterDtmfPlugin
 */
public class FlutterDtmfPlugin implements FlutterPlugin, MethodCallHandler {
    private ToneGenerator generator;
    private ToneGenerator voiceGenerator;

    @Override
    public void onAttachedToEngine(FlutterPluginBinding flutterPluginBinding) {
        final MethodChannel channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "flutter_dtmf");
        channel.setMethodCallHandler(new FlutterDtmfPlugin());
    }

    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_dtmf");
        channel.setMethodCallHandler(new FlutterDtmfPlugin());
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        switch (call.method) {
            case "getPlatformVersion":
                result.success("Android " + android.os.Build.VERSION.RELEASE);
                break;
            case "playTone":
                String digits = call.argument("digits");
                if (digits != null) {
                    playTone(digits);
                }
                result.success(null);
                break;
            case "playCallWaiting":
                playVoiceTone(ToneGenerator.TONE_SUP_RINGTONE, 1000);
                result.success(null);
                break;
            case "playCallAlert":
                playVoiceTone(ToneGenerator.TONE_SUP_CONFIRM, 100);
                result.success(null);
                break;
            case "playCallTerm":
                playVoiceTone(ToneGenerator.TONE_SUP_RADIO_NOTAVAIL, 200);
                result.success(null);
                break;
            case "releaseGenerator":
                if (generator != null) {
                    generator.stopTone();
                    generator.release();
                    generator = null;
                }
                if (voiceGenerator != null) {
                    voiceGenerator.stopTone();
                    voiceGenerator.release();
                    voiceGenerator = null;
                }
                break;
            default:
                result.notImplemented();
                break;
        }
    }

    private void playVoiceTone(int toneType, int durationMs) {
        try {
            if (generator == null) {
                generator = new ToneGenerator(AudioManager.STREAM_VOICE_CALL, 100);
            }
            generator.startTone(toneType, durationMs);
        } catch (Exception ignore) {
        }
    }

    private void playTone(String digits) {
        try {
            if (voiceGenerator == null) {
                voiceGenerator = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
            }
            for (int i = 0; i < digits.length(); i++) {
                int toneType = getToneType(digits.charAt(i));
                voiceGenerator.startTone(toneType, 200);
            }
        } catch (Exception ignore) {
        }
    }

    private int getToneType(char digit) {
        switch (digit) {
            case '0':
                return ToneGenerator.TONE_DTMF_0;
            case '1':
                return ToneGenerator.TONE_DTMF_1;
            case '2':
                return ToneGenerator.TONE_DTMF_2;
            case '3':
                return ToneGenerator.TONE_DTMF_3;
            case '4':
                return ToneGenerator.TONE_DTMF_4;
            case '5':
                return ToneGenerator.TONE_DTMF_5;
            case '6':
                return ToneGenerator.TONE_DTMF_6;
            case '7':
                return ToneGenerator.TONE_DTMF_7;
            case '8':
                return ToneGenerator.TONE_DTMF_8;
            case '9':
                return ToneGenerator.TONE_DTMF_9;
            case '*':
                return ToneGenerator.TONE_DTMF_S;
            case '#':
                return ToneGenerator.TONE_DTMF_P;
            case 'A':
                return ToneGenerator.TONE_DTMF_A;
            case 'B':
                return ToneGenerator.TONE_DTMF_B;
            case 'C':
                return ToneGenerator.TONE_DTMF_C;
            case 'D':
                return ToneGenerator.TONE_DTMF_D;
            default:
                return -1;
        }
    }

    @Override
    public void onDetachedFromEngine(FlutterPluginBinding binding) {
    }
}
