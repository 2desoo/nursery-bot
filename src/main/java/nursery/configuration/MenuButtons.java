package nursery.configuration;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuButtons {

    /*
    List of commands
     */
    public List<BotCommand> listOfCommands() {
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "Приветствие."));
        listOfCommands.add(new BotCommand("/cat_shelter", "Кошачий приют."));
        listOfCommands.add(new BotCommand("/dog_shelter", "Собачий приют ."));
        listOfCommands.add(new BotCommand("/info_cat_shelter", "Информация о приюте для кошек."));
        listOfCommands.add(new BotCommand("/schedule_work_shelter", "Расписание работы приюта для кошек."));
        listOfCommands.add(new BotCommand("/address_shelter", "Адрес приюта для кошек."));
        listOfCommands.add(new BotCommand("/travel_map_cat", "Схема проезда к приюту для кошек."));
        listOfCommands.add(new BotCommand("/contact_security_cat", "Контактные данные охраны приюта для кошек."));
        listOfCommands.add(new BotCommand("/safety_shelter_cat", "Тех. безопасности в приюте для кошек."));
        listOfCommands.add(new BotCommand("/animalistic", "Как взять животное из приюта."));
        listOfCommands.add(new BotCommand("/report", "Прислать отчет о питомце."));
        listOfCommands.add(new BotCommand("/help", "Позвать волонтера."));
        return listOfCommands;
    }
}
