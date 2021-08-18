package com.commons.email.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@PropertySource({"classpath:email.properties"})
public class EmailConfig {
  @Value("${spring.mail.username}")
  protected String username;

  @Value("${spring.mail.password}")
  protected String password;

  @Value("${spring.mail.host}")
  protected String smtp;

  @Value("${spring.mail.port}")
  protected Integer port;

  @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
  protected String smtpStarttls;

  @Value("${spring.mail.properties.mail.smtp.auth}")
  protected String smtpAuth;

  @Value("${spring.mail.transport.protocol}")
  protected String protocol;

  @Value("${spring.mail.debug}")
  protected String debug;

  @Bean
  public JavaMailSender getJavaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost(this.smtp);
    mailSender.setPort(this.port);
    mailSender.setUsername(this.username);
    mailSender.setPassword(this.password);
    Properties props = mailSender.getJavaMailProperties();
    props.put("mail.transport.protocol", this.protocol);
    props.put("mail.smtp.auth", this.smtpAuth);
    props.put("mail.smtp.starttls.enable", this.smtpStarttls);
    props.put("mail.debug", this.debug);
    return mailSender;
  }
}
