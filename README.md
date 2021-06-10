![banner](https://user-images.githubusercontent.com/30138390/121176798-5c999100-c87c-11eb-95f7-a9532f42972d.png)

# react-native-khalti-sdk

react-native wrapper for khalti iOS and android sdks.

## example
To run the example clone the repo, go to the example folder, yarn , pod install and yarn ios/android.

## Installation

```sh
npm install react-native-khalti-sdk
```
or
```sh
yarn add react-native-khalti-sdk
```

## Android
#### Step 1
Create a folder named **xml** inside the res folder of android and add a file with name **network_security_config.xml** and paste the following code.
```
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <base-config cleartextTrafficPermitted="true" />
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">192.168.52.25</domain>
        <domain includeSubdomains="true">192.168.52.20</domain>
        <domain includeSubdomains="true">192.168.52.22</domain>
        <domain includeSubdomains="true">192.168.52.127</domain>
        <domain includeSubdomains="true">192.168.52.99</domain>
        <domain includeSubdomains="true">192.168.1.195</domain>
        <domain includeSubdomains="true">a.khalti.com</domain>
        <domain includeSubdomains="true">192.168.52.28</domain>
        <domain includeSubdomains="true">192.168.52.38</domain>
        <domain includeSubdomains="true">192.168.12.106</domain>
        <domain includeSubdomains="true">192.168.52.133</domain>
        <domain includeSubdomains="true">192.168.52.126</domain>
        <domain includeSubdomains="true">192.168.52.161</domain>
        <domain includeSubdomains="false">localhost</domain>
    </domain-config>
</network-security-config>

```
#### Step 2
Then goto the manifest.xml and add
```
android:allowBackup="true"
android:networkSecurityConfig="@xml/network_security_config"
```
in the application section.
#### Step 3
```
./gradlew clean
```

## iOS
### Step 1
install [cocoapods-user-defined-build-types](https://github.com/joncardasis/cocoapods-user-defined-build-types)
### Step 2
Add the following in your podfile (see example ios for reference)
```
plugin 'cocoapods-user-defined-build-types'

enable_user_defined_build_types!
```
### Step 3
Add the following line and run pod install
```
pod 'react-native-khalti-sdk', :build_type => :dynamic_framework
```

## Usage

```js
import KhaltiSdk from "react-native-khalti-sdk";

...
    try {
      const result = await KhaltiSdk.startKhaltiSdk(
        merchantKey,
        productName,
        productId,
        productUrl,
        amount,
        ebankingPayment,
        additionalData
      );
      console.log({ result });
    } catch (e) {
      console.log({ e });
    }
```

## Contributing
<a href="https://github.com/aanjan123" target="_blank"><img src="https://avatars.githubusercontent.com/u/19562165?v=4"
width=50
height=50
raw=true
alt="Bhuwan GOD Kandel"
style="border-radius: 40px;margin-top:10px" ></a>

## License

MIT
