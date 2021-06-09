import Khalti

@objc(KhaltiSdk)
class KhaltiSdk: NSObject, KhaltiPayDelegate {

    var resolveCallback: RCTPromiseResolveBlock?
    var rejectCallback: RCTPromiseRejectBlock?

    @objc
    static func requiresMainQueueSetup() -> Bool {
      return true
    }

    @objc
    func startKhaltiSdk(_
        merchantKey: String,
        productName: String,
        productId: String,
        productUrl: String,
        amount: NSNumber,
        ebankingPayment: Bool,
        additionalData: NSDictionary,
        KhaltiAppScheme: String,
        resolver resolve: @escaping RCTPromiseResolveBlock,
        rejector reject: @escaping RCTPromiseRejectBlock
    ) -> Void {
        resolveCallback = resolve
        rejectCallback = reject

        debugPrint("Starting Khalti SDK with params....")
        debugPrint("merchantKey: \(merchantKey)")
        debugPrint("productName: \(productName)")
        debugPrint("productId: \(productId)")
        debugPrint("productUrl: \(productUrl)")
        debugPrint("amount: \(amount)")
        debugPrint("ebankingPayment: \(ebankingPayment)")
        debugPrint("additionalData: \(additionalData)")

        Khalti.shared.appUrlScheme = KhaltiAppScheme;

        let khaltiConfig:Config = Config(publicKey: merchantKey, amount: Int(truncating: amount), productId: productId, productName: productName, productUrl: productUrl,additionalData: additionalData as? Dictionary<String, String>, ebankingPayment: ebankingPayment)

        DispatchQueue.main.async {
            Khalti.present(caller: (UIApplication.shared.windows.first?.rootViewController)!, with: khaltiConfig, delegate: self)
        }
    }

    func onCheckOutSuccess(data: Dictionary<String, Any>) {
        debugPrint("Khalti SDK payment successful")
        debugPrint(data)
        resolveCallback!(data as NSDictionary)
    }

    func onCheckOutError(action: String, message: String, data: Dictionary<String, Any>?) {
        debugPrint("Khalti SDK payment error")
        debugPrint(message)
        // if let value = data {
        //     print(value)
        // }

        // let error = NSError(domain: "Esewa Error", code: 101, userInfo: data)
        // rejectCallback!("action", message, error)
    }

}
