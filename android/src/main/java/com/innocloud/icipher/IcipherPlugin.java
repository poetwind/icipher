package com.innocloud.icipher;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Objects;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** IcipherPlugin */
public class IcipherPlugin implements MethodCallHandler {
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "icipher");
    channel.setMethodCallHandler(new IcipherPlugin());
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    switch (call.method) {
      case "getPlatformVersion":
        result.success("Android " + android.os.Build.VERSION.RELEASE);
        break;
      case "encryptAes128CBCZeroPadding":
        result.success(encryptAes128CBCZeroPadding((String) Objects.requireNonNull(call.argument("key")),
                (String) Objects.requireNonNull(call.argument("iv")),
                (String) Objects.requireNonNull(call.argument("data"))));
        break;
      case "decryptAes128CBCZeroPadding":
        result.success(decryptAes128CBCZeroPadding((String) Objects.requireNonNull(call.argument("key")),
                (String) Objects.requireNonNull(call.argument("iv")),
                (String) Objects.requireNonNull(call.argument("data"))));
        break;
      default:
        result.notImplemented();

    }
  }

  private String encryptAes128CBCZeroPadding(String key, String iv, String data) {
    Base64.Encoder encoder = Base64.getEncoder();
    byte[] keyBytes = key.getBytes();

    byte[] ivBytes = iv.getBytes();
    byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
    String result = "";
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
      int blockSize = cipher.getBlockSize();
      int length = dataBytes.length;
      if (length % blockSize != 0) {
        length = length + (blockSize - (length % blockSize));
      }
      byte[] paddedData = new byte[length];
      System.arraycopy(dataBytes, 0, paddedData, 0, dataBytes.length);

      SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
      IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
      cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
      byte[] encrypted = cipher.doFinal(paddedData);
      result = encoder.encodeToString(encrypted);
      System.out.println(result);
    }catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  private String decryptAes128CBCZeroPadding(String key, String iv, String data) {
    Base64.Decoder decoder = Base64.getDecoder();
    byte[] keyBytes = key.getBytes();

    byte[] ivBytes = iv.getBytes();
    byte[] dataBytes = decoder.decode(data.getBytes());
    String result = "";
    try {
      Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
      int blockSize = cipher.getBlockSize();
      int length = dataBytes.length;
      if (length % blockSize != 0) {
        length = length + (blockSize - (length % blockSize));
      }
      byte[] paddedData = new byte[length];
      System.arraycopy(dataBytes, 0, paddedData, 0, dataBytes.length);

      SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
      IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
      cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
      byte[] decrypted = cipher.doFinal(paddedData);
      result = new String(decrypted);
      System.out.println(result);
    }catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

}
