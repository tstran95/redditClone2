package com.vn.redditclone.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;


@Service
@AllArgsConstructor
public class MailContentBuilderImpl {
    private final SpringTemplateEngine templateEngine;

    String build(String message) {
        Context context = new Context();
        context.setVariable("message" , message);
        return templateEngine.process("mailTemplate" , context);
    }
}
