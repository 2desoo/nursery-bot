package nursery.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.Objects;

@Configuration
@PropertySource("application.properties")
public class BotConfig {

    @Value("${telegram.bot.name}")
    private String botName;

    @Value("${telegram.bot.token}")
    private String token;

    private String startText = "Я бот который хочет помочь людям.\n" +
            "Которые задумываются о том, чтобы забрать собаку или кошку домой.\n" +
            "Выберите нужное меню.";

    private String startTextCat = "Вы выбрали приют для кошек, что хочешь узнать.";

    private String startTextDog = "Вы выбрали приют для Собак, что хочешь узнать.";

    private String infoText = "Мы приют животных из Санкт-Петербурга,\n " +
            "мы помогает людям,\n " +
            "которые задумываются о том,\n " +
            "чтобы забрать собаку или кошку домой";

    private String animalisticText = "Ведутся тех. работы скоро тут будет (Как взять животное из приюта)";
    private String reportText = "Ведутся тех. работы скоро тут будет (Прислать отчет о питомце)";

    private String helpText = "Ведутся тех. работы скоро тут будет (Позвать волонтера )";

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStartText() {
        return startText;
    }

    public void setStartText(String startText) {
        this.startText = startText;
    }

    public String getStartTextCat() {
        return startTextCat;
    }

    public void setStartTextCat(String startTextCat) {
        this.startTextCat = startTextCat;
    }

    public String getStartTextDog() {
        return startTextDog;
    }

    public void setStartTextDog(String startTextDog) {
        this.startTextDog = startTextDog;
    }

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public String getAnimalisticText() {
        return animalisticText;
    }

    public void setAnimalisticText(String animalisticText) {
        this.animalisticText = animalisticText;
    }

    public String getReportText() {
        return reportText;
    }

    public void setReportText(String reportText) {
        this.reportText = reportText;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
    }
}
