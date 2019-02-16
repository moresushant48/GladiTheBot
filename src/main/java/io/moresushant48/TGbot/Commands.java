package io.moresushant48.TGbot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.xml.sax.SAXException;

import io.moresushant48.TGbot.plugins.Weather;

public class Commands {
		
	File file = new File("C:\\Users\\mores\\Documents\\workspace-sts-3.9.6.RELEASE\\TGbot\\src\\main\\java\\io\\moresushant48\\TGbot\\DataStorage.txt");
	
	public SendMessage executeCommand(Update update) throws IOException, ParserConfigurationException, SAXException, NullPointerException {

		final String msg = update.getMessage().getText();
		
		Long chatId = update.getMessage().getChatId();
		
		SendMessage sendMessage = new SendMessage();
		
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String lastMsg = reader.readLine();
		reader.close();
		
		if(msg.charAt(0) == '/') {
		
			switch(msg)
			{
			case "/start":
				sendMessage = startCommand(chatId, update, sendMessage);
				break;
				
			case "/owner":
				sendMessage = ownerCommand(chatId, update, sendMessage);
				break;
	
			case "/weather":
				sendMessage = weatherCommand(chatId, update, sendMessage);
				break;
				
			default:
				sendMessage = invalidCommand(chatId, update, sendMessage);
			}
		}
		else		
		{  
			switch(lastMsg)
			{
			case "/weather":
				
				sendMessage = new Weather().getWeather(update, msg);
				break;
				
				default:
					System.out.println("Invalid Parameter");
			}			
		}
	
		FileWriter writer = new FileWriter(file);
		writer.write(msg);
		writer.close();

		return sendMessage;
	}

	private SendMessage weatherCommand(Long chatId, Update update, SendMessage sendMessage) throws IOException {
		sendMessage.setChatId(chatId)
		.setText("Enter a City");
		

		//lastMsg = update.getMessage();
		
		return sendMessage;
	}

	private SendMessage ownerCommand(Long chatId, Update update, SendMessage sendMessage) {
		sendMessage.setChatId(chatId)
		.setText("This bot is Developed by Sushant More.");
		
		return sendMessage;
	}

	private static SendMessage startCommand(Long chatId, Update update, SendMessage sendMessage) {
		
		sendMessage.setChatId(chatId)
		.setText("Hello " + update.getMessage().getFrom().getFirstName());
				
		return sendMessage;
	}
	
	private SendMessage invalidCommand(Long chatId, Update update, SendMessage sendMessage) {
		
		sendMessage.setChatId(chatId)
		.setText("Invalid Commands. Use /help for valid Commands.");
		
		return sendMessage;
	}
	
}
