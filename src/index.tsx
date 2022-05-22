import { NativeModules } from 'react-native';

type KhaltiResponse = {
  mobile: string;
  product_name: string;
  product_identity: string;
  product_url?: string;
  amount: number;
  token: string;
  idx: string;
}
type KhaltiSdkType = {
  startKhaltiSdk(
    merchantKey: string,
    productName: string,
    productId: string,
    productUrl: string,
    amount: number,
    additionalData: object,
    KhaltiAppScheme: string
  ): Promise<KhaltiResponse>;
};

const { KhaltiSdk } = NativeModules;

export default KhaltiSdk as KhaltiSdkType;
