#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(KhaltiSdk, NSObject)

RCT_EXTERN_METHOD(
                  startKhaltiSdk:(NSString *)merchantKey
                    productName:(NSString *)productName
                    productId:(NSString *)productId
                    productUrl:(NSString *)productUrl
                    amount:(nonnull NSNumber *)amount
                    additionalData:(NSDictionary *)additionalData
                    KhaltiAppScheme:(NSString *)KhaltiAppScheme
                    resolver: (RCTPromiseResolveBlock)resolve
                    rejector: (RCTPromiseRejectBlock)reject
                  );

@end
