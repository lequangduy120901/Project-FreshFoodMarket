/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

/**
 *
 * @author Dinh Nam
 */
public class Email {

    public static boolean sendConfirmOrderEmail(String receiveEmail, Order order) throws AddressException, MessagingException, UnsupportedEncodingException {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", true);
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", 587);
            props.put("mail.smtp.starttls.enable", true);
            props.put("mail.transport.protocol", "smtp");

            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("namddhe150684@fpt.edu.vn", "trieuha1277");
                }
            };

            Session session = Session.getInstance(props, authenticator);

            String total = String.format(Locale.GERMAN, "%,.0f", order.getTotal());
            String subject = "Xác nhận đặt hàng thành công tại Fresh Food Market";
            String content = "<body style=\"background-color: rgb(194, 255, 204);\">\n"
                    + "    <div style=\"max-width: 750px; margin: 0 auto; background-color: #fff; padding: 1px 25px;\">\n"
                    + "        <div>\n"
                    + "            <h1 style=\"color: #2a8738\">Cảm ơn quý khách đã đặt hàng tại Fresh Food Market</h1>\n"
                    + "        </div>\n"
                    + "        <div style=\"font-size: large; color: #000;\">\n"
                    + "            <div>\n"
                    + "                <p>Đơn hàng #" + order.getOrderID() + " đã đặt hàng thành công. Các sản phẩm sẽ được giao nhanh chóng đến tay bạn sớm nhất có thể.</p>\n"
                    + "            </div>\n"
                    + "            <div>\n"
                    + "                <table>\n"
                    + "                    <tr style=\"height: 30px;\">\n"
                    + "                        <th colspan=\"2\" style=\"text-align: left;\">Thông tin đơn hàng</th>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"padding: 3px 100px 3px 3px;\">Họ tên</td>\n"
                    + "                        <td>" + order.getCustomer().getCusName() + "</td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"padding: 3px 100px 3px 3px;\">Số điện thoại</td>\n"
                    + "                        <td>" + order.getCustomer().getCusPhone() + "</td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"padding: 3px 100px 3px 3px;\">Email</td>\n"
                    + "                        <td>" + order.getCustomer().getEmail() + "</td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"padding: 3px 100px 3px 3px;\">Địa chỉ</td>\n"
                    + "                        <td>" + order.getCustomer().getCusAddress() + "</td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"padding: 3px 100px 3px 3px;\">Ngày đặt hàng</td>\n"
                    + "                        <td>" + order.getDateCreate() + "</td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"padding: 3px 100px 3px 3px;\">Phương pháp thanh toán</td>\n"
                    + "                        <td>Thanh toán khi nhận hàng</td>\n"
                    + "                    </tr>\n"
                    + "                    <tr>\n"
                    + "                        <td style=\"padding: 3px 100px 3px 3px;\">Tổng giá trị đơn hàng</td>\n"
                    + "                        <td>" + total + " VND</td>\n"
                    + "                    </tr>\n"
                    + "                </table>\n"
                    + "            </div>\n"
                    + "            <div>\n"
                    + "                <p>Nếu bạn có bất kỳ thắc mắc nào vui lòng liên hệ tới số điện thoại 0123456789 hoặc gửi thư qua địa chỉ email freshfoodmarket@gmail.com. Chúng tôi luôn sẵn sàng hỗ trợ bạn.</p>\n"
                    + "            </div>\n"
                    + "            <div style=\"margin-top: 30px;\">\n"
                    + "                <p>Chân thành cảm ơn,</p>\n"
                    + "                <p style=\"font-weight: bold; color: #2a8738\">Fresh Food Market</p>\n"
                    + "                <p>--<br>Phone: 0123456789<br>Email: freshfoodmarket@gmail.com<br>Address: No 1, Hacker way, Menlo Park, CA</p>\n"
                    + "            </div>\n"
                    + "        </div>\n"
                    + "    </div>\n"
                    + "</body>";

            Message msg = new MimeMessage(session);
            String subject2 = MimeUtility.encodeText(subject, "UTF-8", null);
            msg.setSubject(subject2);
            msg.setContent(content, "text/html; charset=UTF-8");

            Address addressTo = new InternetAddress(receiveEmail);
            msg.setRecipient(Message.RecipientType.TO, addressTo);

            Transport.send(msg);

            return true;
        } catch (MessagingException e) {
            return false;
        }
    }
}
