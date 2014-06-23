package biz.neustar.dece.portal.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class XmlParser extends DefaultHandler{
	private String tempVal;
	private QuestionAndAnswer questionAndAnswer;
	private List<QuestionAndAnswer> questionAndAnswerList = new ArrayList<QuestionAndAnswer>();
	String searchText = null;
	boolean hasSearchText = false;
    private final StringBuilder buffer = new StringBuilder();

	private void parseDocument(String xmlFileUrl) {
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {
			SAXParser sp = spf.newSAXParser();
			sp.parse(xmlFileUrl, this);			
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		buffer.setLength(0);
		tempVal = "";
		if(qName.equalsIgnoreCase("FAQ")) {
			questionAndAnswer = new QuestionAndAnswer();
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {
			//tempVal = new String(ch,start,length);
		buffer.append(ch,start,length);	
		tempVal=new String(buffer);
			
	}
	
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equalsIgnoreCase("FAQ")) {
			if(hasSearchText){
				questionAndAnswerList.add(questionAndAnswer);
				hasSearchText = false;
			}
		}else if (qName.equalsIgnoreCase("Q")) {
			if(tempVal.toLowerCase().contains(searchText.toLowerCase()))
				hasSearchText = true;
			questionAndAnswer.setQuestion(tempVal);
		}else if (qName.equalsIgnoreCase("A")) {
			if(tempVal.toLowerCase().contains(searchText.toLowerCase()))
				hasSearchText = true;
			questionAndAnswer.setAnswer(tempVal);
		}
	}

	public List<QuestionAndAnswer> getFaqs(String xmlFile, String searchTextValue){
		questionAndAnswerList = new ArrayList<QuestionAndAnswer>();
		searchText = searchTextValue;
		parseDocument(xmlFile);
		return questionAndAnswerList; 
	}
}