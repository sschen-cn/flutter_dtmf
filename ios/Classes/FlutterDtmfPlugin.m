#import "FlutterDtmfPlugin.h"
#if __has_include(<flutter_dtmf/flutter_dtmf-Swift.h>)
#import <flutter_dtmf/flutter_dtmf-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_dtmf-Swift.h"
#endif

@implementation FlutterDtmfPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterDtmfPlugin registerWithRegistrar:registrar];
}
@end
