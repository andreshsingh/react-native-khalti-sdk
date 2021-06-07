import { NativeModules } from 'react-native';

type KhaltiSdkType = {
  multiply(a: number, b: number): Promise<number>;
};

const { KhaltiSdk } = NativeModules;

export default KhaltiSdk as KhaltiSdkType;
