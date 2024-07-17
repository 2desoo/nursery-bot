package nursery.service;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

public interface MenuButtonsService {
    List<BotCommand> listOfCommands();
}
