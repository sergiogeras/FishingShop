package fishingshop.utils;


import fishingshop.domain.customer.Customer;
import fishingshop.domain.order.OrderItem;
import fishingshop.domain.order.Orders;
import fishingshop.domain.user.User;
import fishingshop.service.shop.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by sergei on 28.11.15.
 */

@Component
@Scope("request")
public class MailSender {


    @Autowired
    private Cart cart;

    /** Send e-mail with registration data */
    public void sendRegistrationMessage(User user){
        final String username = "poplavok.fishshop@gmail.com";
        final String password = "poplavok1234";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Poplavok"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getCustomer().getEmail()));
            message.setSubject("Интернет-магазин рыболовных товаров Поплавок Регистрация");
            message.setText("   Здравствуйте, " + user.getCustomer().getName() + "!\n\n"
                    + "Вы зарегистрировались на сайте Интернет-магазина Поплавок\n\n"
                    + " Ваши учётные данные: \n"
                    + "Имя пользователя: " + user.getUsername()+"\n"
                    + "Пароль: "+ user.getPassword());

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /** Send e-mail with order data */
    public void sendOrderMessage(){
        final String username = "poplavok.fishshop@gmail.com";
        final String password = "poplavok1234";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            FacesContext context=FacesContext.getCurrentInstance();
            Customer customer=(Customer)context.getExternalContext().getSessionMap().get("customer");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Poplavok"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(customer.getEmail()));
            message.setSubject("Интернет-магазин рыболовных товаров \"Поплавок\"");

            StringBuilder builder=new StringBuilder();
            builder.append("<html><body>");
            builder.append("<div style=\" font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#666666; background-color:#EEEEEE;\">");

            builder.append("<table>");
            builder.append("<tr>");
            builder.append("<th>№</th>");
            builder.append("<th>Название</th>");
            builder.append("<th>Количество</th>");
            builder.append("<th>Цена</th>");
            builder.append("<th>Стоимость</th>");
            builder.append("</tr>");

            int i=0;
            for(OrderItem item: cart.getOrderItems()){
                builder.append("<tr>");
                builder.append("<td>");
                builder.append(++i);
                builder.append("</td>");
                builder.append("<td>");
                builder.append(item.getGoods().getName());
                builder.append("</td>");
                builder.append("<td>");
                builder.append(item.getAmount());
                builder.append("</td>");
                builder.append("<td>");
                builder.append(item.getGoods().getPrice());
                builder.append("</td>");
                builder.append("<td>");
                builder.append(item.getPrice());
                builder.append("</td>");
                builder.append("</tr>");
            }


            builder.append("</table>");
            builder.append("</div>");
            builder.append("</body></html>");


            message.setContent(builder.toString(), "text/html; charset=utf-8");


            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
