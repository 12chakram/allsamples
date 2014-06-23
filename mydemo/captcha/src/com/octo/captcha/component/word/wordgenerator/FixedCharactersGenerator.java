package com.octo.captcha.component.word.wordgenerator;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Random;

/**
 * <p>Random word generator. must be constructed with a String containing all possible chars</p>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class FixedCharactersGenerator implements WordGenerator {

    private String chars;

    public FixedCharactersGenerator(String chars) {
    	this.chars = chars;
    }

    /**
     * Return a word of length between min and max length
     *
     * @return a String of length between min and max length
     */
    public String getWord(Integer length) {
       
        return chars;
    }

    /**
     * Return a word of length between min and max length according to the given locale
     *
     * @param length the word length
     * @return a String of length between min and max length according to the given locale
     */
    public String getWord(Integer length, Locale locale) {
        return chars;
    }

}