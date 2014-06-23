package com.deva;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import com.octo.captcha.service.CaptchaServiceException;

/**
 * Servlet implementation class AudioCaptchaServlet
 */
@WebServlet("/AudioCaptchaServlet")
public class AudioCaptchaServlet extends HttpServlet {       

	    /**
	     * Serial uid.
	     */
	    private static final long serialVersionUID = 1L;

	    	    /**
	     *
	     */
	    @Override
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {

	        

	        AudioInputStream challenge = null;
	        try {
	            // get the session id that will identify the generated captcha.
	            // the same id must be used to validate the response, the session id
	            // is a good candidate!
	            String captchaId = request.getSession().getId();
	            captchaId = request.getParameter("captchaId");
	            // call the AudioCaptchaService getChallenge method
	            byte bufSound[] = Main.getCaptchaSound(captchaId);
	            InputStream in = new ByteArrayInputStream(bufSound);
	            challenge = AudioSystem.getAudioInputStream(in);
	        } catch (IllegalArgumentException e) {
	        } catch (CaptchaServiceException e) {
	        } catch (Exception e) {
	        }

	        response.setDateHeader("Expires", 0);
	        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
	        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
	        response.setHeader("Pragma", "no-cache");
	        response.setContentType("audio/wav");
	       
	        ServletOutputStream out = response.getOutputStream();
	        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
	        AudioSystem.write(challenge,
	                javax.sound.sampled.AudioFileFormat.Type.WAVE,
	                byteOutputStream);
	        out.write(byteOutputStream.toByteArray());
	        out.flush();
	        out.close();
	    }

}
