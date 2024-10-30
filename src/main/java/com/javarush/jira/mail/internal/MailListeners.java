package com.javarush.jira.mail.internal;

import com.javarush.jira.common.internal.config.AppProperties;
import com.javarush.jira.login.User;
import com.javarush.jira.login.internal.UserMapper;
import com.javarush.jira.login.internal.passwordreset.PasswordResetEvent;
import com.javarush.jira.login.internal.verification.RegistrationConfirmEvent;
import com.javarush.jira.mail.MailService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Map;

@Component
@AllArgsConstructor
public class MailListeners {
    private final UserMapper userMapper;
    private final MailService mailService;
    private final AppProperties appProperties;

    @EventListener
    public void confirmRegistration(RegistrationConfirmEvent event) {
        String confirmationUrl = appProperties.getHostUrl() + "/ui/register/confirm?token=" + event.token();
        User user = userMapper.toEntity(event.userto());
        Locale locale = determineLocale(user);
        mailService.sendToUserAsync("email-confirmation.html", user, Map.of("confirmationUrl", confirmationUrl), locale);
    }

    @EventListener
    public void resetPassword(PasswordResetEvent event) {
        String resetUrl = appProperties.getHostUrl() + "/ui/password/change?token=" + event.token();
        Locale locale = determineLocale(event.user());
        mailService.sendToUserAsync("password-reset.html", event.user(), Map.of("resetUrl", resetUrl), locale);
    }

    private Locale determineLocale(User user) {

        // Fixed locale
        String userLocale = user.getLocale();

        switch (userLocale) {
            case "en":
                return Locale.ENGLISH;
            case "it":
                return Locale.ITALIAN;
            case "ru":
            default:
                return Locale.forLanguageTag("ru");
        }
    }
}
