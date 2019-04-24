# icipher

A flutter plugin project for AES encryption and decrytion which support both ios and android.

本插件帮助开发者在自己的应用内使用AES加密解密。

This package contains a set of high-level functions for the encryption and decryption. For now, this package only support AES algorithm. Also, there are two modes support right now. The first one is `CBC 128 bit padding 7`, and second is `GCM 128 bit`. I will continue working on this project to make it sopport other mode of AES, and the other algorithms like DES, MD5, SHA1 and so on.

**all strings in this plugin use UTF8 encoding**

**本插件所有字符串都使用utf8编码**

Features:

- AES CBC mode 128bit zero padding (works for both ios and android)

## Getting Started

### Instructions

1. Open a command line and cd to your projects root folder. (打开命令行，cd进入到项目根目录)
2. In your pubspec, add an entry for cipher2 to your dependencies. The example shows below(打开pubspec.yaml, 加入icipher依赖项，下面有例子)
3. execute **pub install** in cmd (命令行执行**pub install**命令) 

### Pubspec

you can use 'any' instead of a version if you just want the latest always

```yaml
dependencies:
  icipher: 0.0.1
```

### Usage

#### AES CBC mode 128bit zero padding

Encryption: this method will return a based 64 encoded ciphertext

```dart
/*
Icipher.encryptAes128CBCZeroPadding

Parameters:
    plainText: the string to be encrypted
        plainText: 被加密字符串
    key: a string with 128bit length.
        key:128 bit字符串
    iv: string with 128bit length.
        iv: 128 bit字符串

Return:
    String, the base64 encoded encrypted data
*/
String encryptedString = await Icipher.encryptAes128CBCZeroPadding(plainText, key, iv);
```

Decryption

```dart
/*
Icipher.decryptAes128CBCZeroPadding

Parameters:
    encryptedString: the base64 encoded encrypted data
        encryptedString: base64编码的密文字符串
    key: a string with 128bit length.
        key:128 bit字符串
    iv: string with 128bit length.
        iv: 128 bit字符串

Return:
    String, the plainText
*/
decryptedString = await Icipher.decryptAes128CBCZeroPadding(encryptedString, key, iv);
```


## API

### static Future<String> encryptAes128CBCZeroPadding(String data, String key, String iv) async

Encryption method for the AES CBC mode 128bit pkcs padding 7

- `data`:(String) the string to be encrypted (被加密字符串)
- `key`:(String) a string with 128bit length. (128 bit字符串)
- `iv`:(String) string with 128bit length. (128 bit字符串)

`Icipher.encryptAes128CBCZeroPadding()` return a String, which is a base64 encoded encrypted data

### static Future<String> encryptAes128CBCZeroPadding(String data, String key, String iv) async

Decryption method for the AES CBC mode 128bit pkcs padding 7

- `data`:(String) the base64 encoded encrypted data (base64编码的密文字符串)
- `key`:(String) a string with 128bit length. (128 bit字符串)
- `iv`:(String) string with 128bit length. (128 bit字符串)

`Icipher.encryptAes128CBCZeroPadding()` return the plain text

