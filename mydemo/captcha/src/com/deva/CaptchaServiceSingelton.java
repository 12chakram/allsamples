package com.deva;

import java.awt.Color;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.FunkyBackgroundGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.sound.soundconfigurator.FreeTTSSoundConfigurator;
import com.octo.captcha.component.sound.wordtosound.FreeTTSWordToSound;
import com.octo.captcha.component.word.DictionaryReader;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.DictionaryWordGenerator;
import com.octo.captcha.component.word.wordgenerator.FixedCharactersGenerator;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator; 
import com.octo.captcha.component.word.wordgenerator.WordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;
import com.octo.captcha.sound.gimpy.GimpySoundFactory;

public class CaptchaServiceSingelton {

private static GenericManageableCaptchaService instance;

private static String voiceName = "kevin16";
private static String voicePackage = "com.sun.speech.freetts.en.us.cmu_time_awb.AlanVoiceDirectory,com.sun.speech.freetts.en.us.cmu_us_kal .KevinVoiceDirectory";

/*private static CaptchaFactory soundFactory[] = { 
new GimpySoundFactory( 
//new FixedCharactersGenerator("D"), 
new FreeTTSWordToSound(new FreeTTSSoundConfigurator(voiceName, voicePackage, 1.0f, 100, 100), 4, 10) 
) 
};*/


private static CaptchaFactory soundFactory[] = { 
new GimpySoundFactory( 
new DictionaryWordGenerator(new FileDictionary("sound")), 
new FreeTTSWordToSound(new FreeTTSSoundConfigurator(voiceName, voicePackage, 1.0f, 100, 100), 4, 10) 
) 
};


private static CaptchaFactory characterFactory[] = { 
new GimpyFactory( 
new RandomWordGenerator("ABCDEFGHIJKLMNOPQRSTUVWXYZ"), 
new ComposedWordToImage(new RandomFontGenerator(new Integer(12), new Integer(16)), 
new FunkyBackgroundGenerator(new Integer(100), new Integer(50)), 
new RandomTextPaster(new Integer(6), new Integer(10), Color.BLACK)))
};

public static synchronized GenericManageableCaptchaService getInstance() {
if(instance==null) { 
GenericCaptchaEngine engine = new GenericCaptchaEngine(characterFactory); 
instance = new GenericManageableCaptchaService(new FastHashMapCaptchaStore(), engine, 80, 100000, 75000); 
}

return instance;

}

public static void setSoundEngine() {

GenericCaptchaEngine engine = new GenericCaptchaEngine(soundFactory); 
instance.setCaptchaEngine(engine); 
}

public static void setCharacterEngine() {

GenericCaptchaEngine engine = new GenericCaptchaEngine(characterFactory); 
instance.setCaptchaEngine(engine); 
}
}