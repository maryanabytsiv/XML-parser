package main;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.SAXException;

import entity.User;
import parser.DOMParser;
import parser.SAXHandler;
import parser.StAXParser;

public class Main {
	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, XMLStreamException {
		
		System.out.println("-------------DOM----------");
		DOMParser parserDOM = new DOMParser();
		
		try {
			parserDOM.transform();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("------------SAX------------");
		SAXParserFactory parserFactor = SAXParserFactory.newInstance();
	    SAXParser parserSAX;
		parserSAX = parserFactor.newSAXParser();
	    
		SAXHandler handler = new SAXHandler();
		InputStream is = SAXHandler.class.getResourceAsStream("users.xml");
	    parserSAX.parse(is, handler);

	    //Printing the list of employees obtained from XML
	    for ( User user : handler.users){
	      System.out.println(user);
	    }
	    
	    System.out.println("-------------StAX----------");
	    StAXParser parserStAX = new StAXParser();
	    
	    parserStAX.transform();
	    
	  }
	
	}
