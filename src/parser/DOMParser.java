package parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import entity.User;

public class DOMParser {

	public void transform() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		DocumentBuilder builder = factory.newDocumentBuilder();

		InputStream is = DOMParser.class.getResourceAsStream("users.xml");
		org.w3c.dom.Document document = builder.parse(is);

		List<User> users = new ArrayList<>();

		Node nodeList = (Node) document.getDocumentElement().getChildNodes();

		for (int i = 0; i < ((NodeList) nodeList).getLength(); i++) {

			Node node = ((NodeList) nodeList).item(i);
			if (node instanceof Element) {
				User user = new User();
				user.id = node.getAttributes().getNamedItem("id").getNodeValue();

				NodeList childNodes = node.getChildNodes();
				for (int j = 0; j < childNodes.getLength(); j++) {
					Node cNode = childNodes.item(j);

					if (cNode instanceof Element) {
						String content = cNode.getLastChild().getTextContent().trim();
						switch (cNode.getNodeName()) {
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
				}
				users.add(user);
			}

		}

		for (User user : users) {
			System.out.println(user);
		}

	}

}
