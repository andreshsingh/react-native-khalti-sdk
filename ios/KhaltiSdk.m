#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(KhaltiSdk, NSObject)

RCT_EXTERN_METHOD(
                  startKhaltiSdk:(NSString *)merchantKey
                    productName:(NSString *)productName
                    productId:(NSString *)productId
                    productUrl:(NSString *)productUrl
                    amount:(nonnull NSNumber *)amount
                    ebankingPayment:(BOOL *)ebankingPayment
                    additionalData:(NSDictionary *)additionalData
                    resolver: (RCTPromiseResolveBlock)resolve
                    rejector: (RCTPromiseRejectBlock)reject
                  );

@end
