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

    @Value("${telegram.bot.msg.startText}")
    private String startText;

    @Value("${telegram.bot.msg.infoText}")
    private String infoText;

    @Value("${telegram.bot.msg.workdays}")
    private String workDays;

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

    public String getInfoText() {
        return infoText;
    }

    public void setInfoText(String infoText) {
        this.infoText = infoText;
    }

    public String getWorkDays() {
        return workDays;
    }

    public void setWorkDays(String workdays) {
        this.workDays = workdays;
    }
}
