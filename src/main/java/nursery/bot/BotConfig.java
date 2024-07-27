package nursery.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("application.properties")
public class BotConfig {

    @Value("${telegram.bot.name}")
    //Telegram bot name
    private String botName;
    @Value("${telegram.bot.token}")
    //Telegram bot token
    private String token;
    @Value("${telegram.text.startText}")
    //Telegram bot start text
    private String startText;
    @Value("${telegram.text.animalisticText}")
    //Telegram bot animalistic text
    private String animalisticText;
    @Value("${telegram.text.reportText}")
    //Telegram bot report text
    private String reportText;
    @Value("${telegram.text.helpText}")
    //Telegram bot help text
    private String helpText;

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
