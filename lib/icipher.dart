import 'dart:async';

import 'package:flutter/services.dart';

class Icipher {
  static const MethodChannel _channel =
  const MethodChannel('icipher');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> encryptAes128CBCZeroPadding(
      String plainText, String key, String iv) async =>
      await _channel.invokeMethod("encryptAes128CBCZeroPadding", {
        "data": plainText,
        "key": key,
        "iv": iv,
      });

  static Future<String> decryptAes128CBCZeroPadding(
      String encryptedText, String key, String iv) async {
    final decrypted = await _channel.invokeMethod("decryptAes128CBCZeroPadding", {
      "data": encryptedText,
      "key": key,
      "iv": iv,
    });
    return decrypted;
  }
}
