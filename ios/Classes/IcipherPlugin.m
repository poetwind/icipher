#import "IcipherPlugin.h"
#import <icipher/icipher-Swift.h>

@implementation IcipherPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftIcipherPlugin registerWithRegistrar:registrar];
}
@end
