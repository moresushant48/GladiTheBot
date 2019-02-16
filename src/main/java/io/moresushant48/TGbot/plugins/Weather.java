package io.moresushant48.TGbot.plugins;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Weather {
	
	private static String baseURL = "http://api.openweathermap.org/data/2.5/weather?q=";
	private static String mode = "&mode=xml";
	private static String APPID = "&APPID=06611d52e2c73e126812b67838c99400";
	SendMessage sendMessage = new SendMessage();
	URL finalURL;
	String url;
	
	public SendMessage getWeather(Update update, String city) throws MalformedURLException, IOException, ParserConfigurationException, SAXException {
		
		Long chatId = update.getMessage().getChatId();
		
		url = baseURL + city + mode + APPID;
		finalURL = new URL(url);
				
		HttpURLConnection connection = (HttpURLConnection) finalURL.openConnection();
		connection.setConnectTimeout(5000);
		connection.setReadTimeout(5000);
					
		sendMessage.setChatId(chatId)
		.setText(fetchXmlData());
		
		return sendMessage;
		
	};
	
	private String fetchXmlData() throws ParserConfigurationException, MalformedURLException, SAXException, IOException
	{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new URL(url).openStream());
		
		doc.getDocumentElement().normalize();
        
		NodeList nListCity = doc.getElementsByTagName("city");
		NodeList nListTemp = doc.getElementsByTagName("temperature");
		NodeList nListHumid = doc.getElementsByTagName("humidity");
		
        Node nodeCity = nListCity.item(0).getAttributes().getNamedItem("name");
        Node nodeCountry = nListCity.item(0).getChildNodes().item(1);
        Node nodeTemp = nListTemp.item(0).getAttributes().getNamedItem("value");
        Node nodeHumid = nListHumid.item(0).getAttributes().getNamedItem("value");
        
        String fetchedData = "City : " + nodeCity.getNodeValue() + ", " + nodeCountry.getTextContent() +
        		 "\nTemp : " + kelvinToCelcius(Float.parseFloat(nodeTemp.getNodeValue())) + "° C"+
        		 "\nHumidity : " + nodeHumid.getNodeValue() + "%";
        
		return fetchedData;
		
	}
	
	private float kelvinToCelcius(float kelvin)
	{
		return (kelvin - 273.15F);
	}
	
}
