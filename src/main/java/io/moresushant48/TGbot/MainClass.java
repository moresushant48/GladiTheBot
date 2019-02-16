package io.moresushant48.TGbot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

/**
 * Hello world!
 *
 */
public class MainClass
{
	
    public static void main( String[] args )
    {
        ApiContextInitializer.init();
        
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        
        try {
        	telegramBotsApi.registerBot(new BotConfig());
        }
        catch (Exception e) {
			e.printStackTrace();
		}
    }
}
