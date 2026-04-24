package com.poly.asm.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {
    public static void send(String to, String subject, String body) throws Exception {
        // Khai báo thông tin tài khoản (Nên để trong file config riêng nếu làm dự án thật)
        final String from = "nguyenquangnhat286@gmail.com"; 
        final String password = "xgdtkijjdddcplld"; // Viết liền, không dấu cách // Lấy App Password từ Google Account

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        // Fix lỗi SSL/TLS trên một số mạng
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B")); // Encode tiêu đề
        
        // QUAN TRỌNG: Gửi HTML utf-8 để hiển thị tiếng Việt đẹp và link click được
        message.setContent(body, "text/html; charset=utf-8");
        
        Transport.send(message);
    }
}