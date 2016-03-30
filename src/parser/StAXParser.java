package parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import entity.User;

public class StAXParser {
	
	public void transform() throws XMLStreamException{
	 List<User> users = null;
	    User curUser = null;
	    String tagContent = null;
	    XMLInputFactory factory = XMLInputFactory.newInstance();
	    InputStream is = SAXHandler.class.getResourceAsStream("users.xml");
	    XMLStreamReader reader = 
	        factory.createXMLStreamReader(is);

	    while(reader.hasNext()){
	      int event = reader.next();

	      switch(event){
	        case XMLStreamConstants.START_ELEMENT: 
	          if ("user".equals(reader.getLocalName())){
	            curUser = new User();
	            curUser.id = reader.getAttributeValue(0);
	          }
	          if("users".equals(reader.getLocalName())){
	            users = new ArrayList<>();
	          }
	          break;

	        case XMLStreamConstants.CHARACTERS:
	          tagContent = reader.getText().trim();
	          break;

	        case XMLStreamConstants.END_ELEMENT:
	          switch(reader.getLocalName()){
	            case "user":
	              users.add(curUser);
	              break;
	            case "name":
	              curUser.firstName = tagContent;
	              break;
	            case "surname":
	              curUser.lastName = tagContent;
	              break;
	            case "nickname":
	              curUser.nickName = tagContent;
	              break;
	          }
	          break;

	        case XMLStreamConstants.START_DOCUMENT:
	          users = new ArrayList<>();
	          break;
	      }

	    }

	    for ( User user : users){
	      System.out.println(user);
	    }
	}
	}
