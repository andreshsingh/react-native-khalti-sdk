import * as React from 'react';

import { StyleSheet, View, Button } from 'react-native';
import KhaltiSdk from 'react-native-khalti-sdk';

const merchantKey = 'test_public_key_1d57e4d159794c6790a62f8953124053';
const productName = 'testProduct';
const productId = '10001';
const productUrl = 'http://google.com';
const amount = 1000;
const additionalData = {
  merchant_author: 'Andresh Singh',
};
const KhaltiAppScheme = 'KhaltiPayExampleScheme';

export default function App() {
  const _startKhaltiSdk = async () => {
    try {
      console.log('start');
      const result = await KhaltiSdk.startKhaltiSdk(
        merchantKey,
        productName,
        productId,
        productUrl,
        amount,
        additionalData,
        KhaltiAppScheme
      );
      console.log({ result });
    } catch (e) {
      console.log({ e });
    }
  };

  return (
    <View style={styles.container}>
      <Button title={'Start Khalti Sdk'} onPress={_startKhaltiSdk} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
