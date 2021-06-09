import { NativeModules } from 'react-native';

type KhaltiSdkType = {
  startKhaltiSdk(
    merchantKey: string,
    productName: string,
    productId: string,
    productUrl: string,
    amount: number,
    ebankingPayment: boolean,
    additionalData: object,
    KhaltiAppScheme: string | null
  ): void;
};

const { KhaltiSdk } = NativeModules;

export default KhaltiSdk as KhaltiSdkType;
