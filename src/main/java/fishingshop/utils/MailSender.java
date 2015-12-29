package fishingshop.utils;


import fishingshop.domain.customer.Customer;
import fishingshop.domain.order.OrderItem;
import fishingshop.domain.user.User;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.context.FacesContext;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Created by sergei on 28.11.15.
 */

@Component
@Scope("request")
public class MailSender {


    private static final String SHOP_EMAIL="poplavok.fishshop@gmail.com";
    private static final String SHOP_PASSWORD="poplavok1234";

    private static final String ADMIN_EMAIL="sergiogeras@bk.ru";

    private Session session;

    public MailSender(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(SHOP_EMAIL, SHOP_PASSWORD);
                    }
                });
    }

    /** Send e-mail with registration data */
    public void sendRegistrationMessage(User user){
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Poplavok"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(user.getCustomer().getEmail()));
            message.setSubject("��������-������� ���������� ������� �������� �����������");
            message.setText("   ������������, " + user.getCustomer().getName() + "!\n\n"
                    + "�� ������������������ �� ����� ��������-�������� ��������\n\n"
                    + "���� ������� ������: \n"
                    + "��� ������������: " + user.getUsername()+"\n"
                    + "������: "+ user.getPassword());

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /** Send e-mail with order data for user */
    public void sendOrderMessage(List<OrderItem> orderItems, int totalSum, int orderId){
        try {

            FacesContext context=FacesContext.getCurrentInstance();
            Customer customer=(Customer)context.getExternalContext().getSessionMap().get("customer");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Poplavok"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(customer.getEmail()));
            message.setSubject("��� �����. ��������-������� ���������� ������� \"��������\"");

            StringBuilder builder=new StringBuilder();
            builder.append("<html><body>");
            builder.append("<div style=\" font: 100%/1.4 Verdana, Arial, Helvetica, sans-serif; font-size:14px;" +
                    " color: #535353; text-align: left; padding-left: 30px;\">");

            builder.append("<h3>�� ������� ����� �� ����� Poplavok.</h3>");
            builder.append("<h3>����� ������ ������: ");
            builder.append(orderId);
            builder.append("</h3>");
            builder.append("<table style=\"border: 1px solid #dddddd; cellspacing=\"2\" border=\"1\" cellpadding=\"5\"\">");
            builder.append("<tr>");
            builder.append("<th>�</th>");
            builder.append("<th>��������</th>");
            builder.append("<th>����������</th>");
            builder.append("<th>����</th>");
            builder.append("<th>���������</th>");
            builder.append("</tr>");

            int i=0;
            for(OrderItem item: orderItems){
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
            builder.append("<h3 style=\"padding-top: 10px;\">�������� ����� ������: ");
            builder.append(totalSum);
            builder.append(" ���.</h3>");
            builder.append("<p>��� �������� �������� � ���� � ��������� ����� ��� ������������� ������.</p>");
            builder.append("</div>");
            builder.append("</body></html>");

            message.setContent(builder.toString(), "text/html; charset=utf-8");
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /** Send e-mail with order data for admin */
    public void sendAdminOrderMessage(List<OrderItem> orderItems, int totalSum, int orderId){
        try {

            FacesContext context=FacesContext.getCurrentInstance();
            Customer customer=(Customer)context.getExternalContext().getSessionMap().get("customer");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Poplavok"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(ADMIN_EMAIL));
            message.setSubject("����� �����. ������� \"��������\"");

            StringBuilder builder=new StringBuilder();
            builder.append("<html><body>");
            builder.append("<div style=\" font: 100%/1.4 Verdana, Arial, Helvetica, sans-serif; font-size:14px;" +
                    " color: #535353; text-align: left; padding-left: 30px;\">");

            builder.append("<h3>������ ������� ����� �����</h3>");
            builder.append("<h3>����������: ");
            builder.append(customer.getName());
            builder.append(" ");
            builder.append(customer.getSurname());
            builder.append("<h3>�������: ");
            builder.append(customer.getPhone());
            builder.append("<h3>����� ������: ");
            builder.append(orderId);
            builder.append("</h3>");
            builder.append("<table style=\"border: 1px solid #dddddd; cellspacing=\"2\" border=\"1\" cellpadding=\"5\"\">");
            builder.append("<tr>");
            builder.append("<th>�</th>");
            builder.append("<th>��������</th>");
            builder.append("<th>����������</th>");
            builder.append("<th>����</th>");
            builder.append("<th>���������</th>");
            builder.append("</tr>");

            int i=0;
            for(OrderItem item: orderItems){
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
            builder.append("<h3 style=\"padding-top: 10px;\">�������� ����� ������: ");
            builder.append(totalSum);
            builder.append(" ���.</h3>");
            builder.append("</div>");
            builder.append("</body></html>");

            message.setContent(builder.toString(), "text/html; charset=utf-8");
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
