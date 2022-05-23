@objc(RnYandexnaviSignature)
class RnYandexnaviSignature: NSObject {

    @objc(sign:withClient:withKey:withResolver:withRejecter:)
    func sign(url: String, client: String, key: String, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        reject("not support", "ios version is not supported yet", nil);
    }
}
