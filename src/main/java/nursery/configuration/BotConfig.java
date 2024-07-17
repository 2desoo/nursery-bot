package nursery.configuration;

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
    @Value("${telegram.text.startText}")
    private String startText;
    @Value("${telegram.text.animalisticText}")
    private String animalisticText;
    @Value("${telegram.text.reportText}")
    private String reportText;
    @Value("${telegram.text.helpText}")
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
