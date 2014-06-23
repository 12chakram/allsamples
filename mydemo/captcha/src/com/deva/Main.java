package com.deva;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import com.sun.image.codec.jpeg.*;

public class Main {public static void main(String[] args) 
throws IllegalArgumentException, CaptchaServiceException, IOException, Exception {

String captchaId = new Random().nextInt(99999) + "";

byte bufCharacters[] = getCaptchaCharacters(captchaId, "en", "US"); 
byte bufSound[] = getCaptchaSound(captchaId);

}


public static byte[] getCaptchaCharacters(String captchaId, String language, String country) 
throws IllegalArgumentException, CaptchaServiceException, IOException, Exception {

System.setProperty("java.awt.headless", "true");

byte[] captchaChallengeAsJpeg = null; 
ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

Locale locale = new Locale(language, country);

GenericManageableCaptchaService captchaService = CaptchaServiceSingelton.getInstance(); 
CaptchaServiceSingelton.setCharacterEngine();

BufferedImage challengeCharcater = (BufferedImage) captchaService.getImageChallengeForID(captchaId, locale);

JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream); 
jpegEncoder.encode(challengeCharcater);

captchaChallengeAsJpeg = jpegOutputStream.toByteArray();

OutputStream outputStream = new FileOutputStream("captcha.jpg"); 
outputStream.write(captchaChallengeAsJpeg); 
outputStream.close();

System.setProperty("java.awt.headless", "false");

return captchaChallengeAsJpeg;

}


public static String getTextCaptcha(String captchaId, String language, String country) 
throws IllegalArgumentException, CaptchaServiceException, IOException, Exception {

System.setProperty("java.awt.headless", "true");

byte[] captchaChallengeAsJpeg = null; 
ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

Locale locale = new Locale(language, country);

GenericManageableCaptchaService captchaService = CaptchaServiceSingelton.getInstance(); 
CaptchaServiceSingelton.setCharacterEngine();

String chars = captchaService.getTextChallengeForID(captchaId, locale);
System.setProperty("java.awt.headless", "false");

return chars;

}


public static byte[] getCaptchaSound(String captchaId) 
throws IllegalArgumentException, CaptchaServiceException, IOException, Exception {

System.setProperty("java.awt.headless", "true");

byte[] captchaChallengeAsAudio = null;

Locale locale = new Locale("en", "US");

GenericManageableCaptchaService captchaService = CaptchaServiceSingelton.getInstance(); 
CaptchaServiceSingelton.setSoundEngine();

AudioInputStream challangeAudio = captchaService.getSoundChallengeForID(captchaId);

ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream(); 
AudioSystem.write(challangeAudio, javax.sound.sampled.AudioFileFormat.Type.WAVE, byteOutputStream);

captchaChallengeAsAudio = byteOutputStream.toByteArray();

OutputStream outputStream = new FileOutputStream("captcha.wav"); 
outputStream.write(captchaChallengeAsAudio); 
outputStream.close();

System.setProperty("java.awt.headless", "false");

return captchaChallengeAsAudio;

} 
} 
