import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:icipher/icipher.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  String _plainText = 'Unknown';
  String _encryptedString = '';
  String _decryptedString = '';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String encryptedString;
    String plainText = 'test';
    String key = '1234567890123456';
    String iv = '1234567890123456';
    String decryptedString;

    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await Icipher.platformVersion;

      encryptedString = await Icipher.encryptAes128CBCZeroPadding(plainText, key, iv);

      decryptedString = await Icipher.decryptAes128CBCZeroPadding(encryptedString, key, iv);

      print("encryptedString:" + encryptedString);
      print("decryptedString:" + decryptedString);


    } on PlatformException catch(e) {

      encryptedString = "";
      decryptedString = "";
      print("exception code: " + e.code);
      print("exception message: " + e.message);

    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
      _plainText = plainText;
      _encryptedString = encryptedString;
      _decryptedString = decryptedString;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('Running on: $_platformVersion \n encryptedString: $_encryptedString \n decryptedString : $_decryptedString \n'),
        ),
      ),
    );
  }
}
