import { NativeModules } from 'react-native';

type KhaltiSdkType = {
  startKhaltiSdk(
    merchantKey: string,
    productName: string,
    productId: string,
    productUrl: string,
    amount: number,
    additionalData: object,
    KhaltiAppScheme: string
  ): void;
};

const { KhaltiSdk } = NativeModules;

export default KhaltiSdk as KhaltiSdkType;
