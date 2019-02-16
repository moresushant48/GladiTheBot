package io.moresushant48.TGbot;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.xml.sax.SAXException;

public class BotConfig extends TelegramLongPollingBot{

	private Commands commands;
	private SendMessage sendMessage;
	
	@Override
	public void onUpdateReceived(Update update){
		
//		System.out.println("BotConfig lastmsg Before: " + lastMsg);
		commands = new Commands();
//		System.out.println("BotConfig lastmsg After: " + lastMsg);
		try {
			
			sendMessage = commands.executeCommand(update);
		
		} catch (IOException e1) {
			
			e1.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		
		try {
			
			execute(sendMessage);

		} catch (TelegramApiException e) {

			e.printStackTrace();
		}		
	}

	public String getBotUsername() {
		
		return "GladiTheBot";
	}

	@Override
	public String getBotToken() {
		
		return "553082068:AAF6jt4eVtShqHP6ku_-UrnFUMVJcK4PpY0";
	}

	
}
