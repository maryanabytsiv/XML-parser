package parser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import entity.User;

public class SAXHandler extends DefaultHandler {

	  public List<User> users = new ArrayList<>();
	  private User user = null;
	  private String content = null;
	  @Override
	  public void startElement(String uri, String localName, 
	                           String qName, Attributes attributes) 
	                           throws SAXException {

	    switch(qName){
	      case "user":
	        user = new User();
	        user.id = attributes.getValue("id");
	        break;
	    }
	  }

	  @Override
	  public void endElement(String uri, String localName, 
	                         String qName) throws SAXException {
	   switch(qName){
	     case "user":
	       users.add(user);       
	       break;
	     case "name":
	       user.firstName = content;
	       break;
	     case "surname":
	       user.lastName = content;
	       break;
	     case "nickname":
	       user.nickName = content;
	       break;
	   }
	  }

	  @Override
	  public void characters(char[] ch, int start, int length) 
	          throws SAXException {
	    content = String.copyValueOf(ch, start, length).trim();
	  }

	}