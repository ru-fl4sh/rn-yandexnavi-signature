#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(RnYandexnaviSignature, NSObject)

RCT_EXTERN_METHOD(sign:(NSString)url withClient:(NSString)client
                 withKey:(NSString)key withResolver:(RCTPromiseResolveBlock)resolve
                 withRejecter:(RCTPromiseRejectBlock)reject)

@end
