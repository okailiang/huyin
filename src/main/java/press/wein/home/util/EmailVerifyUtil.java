package press.wein.home.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import press.wein.home.constant.Constants;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class EmailVerifyUtil {

    private final static Logger LOG = LoggerFactory.getLogger(EmailVerifyUtil.class);
    /**
     * a4print@163.com  密码：@A4printT
     */
    private static final String A4PRINT_EMAIL = "a4print@163.com";
    /**
     * 第三方授权码 A4printT 和密码不一样
     */
    private static final String A4PRINT_EMAIL_PASSWORD = "A4printT";

    private static final String EMAIL_HOST = "smtp.163.com";

    public static final String SUBJECT = "微印社验证";

    public static final String NICK = "微印社";

    /**
     * @param receiveEmail
     * @param code
     * @throws Exception
     */
    public static void verifyEmail(String receiveEmail, String code)
            throws Exception {
        LOG.info(String.format("EmailVerifyUtil.verifyEmail receiveEmail = %s, code = %s", receiveEmail, code));
        Properties prop = new Properties();
        prop.setProperty("mail.host", EMAIL_HOST);
        prop.setProperty("mail.transport.protocol", "smtp");
        prop.put("mail.store.protocol", "imap");
        prop.setProperty("mail.smtp.auth", "true");
        //prop.setProperty("mail.smtp.starttls.enable", "true");
        // 使用JavaMail发送邮件的5个步骤
        // 1、创建session
        Session session = Session.getInstance(prop);
        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        // 2、通过session得到transport对象
        Transport ts = session.getTransport("smtp");
        // 3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect(EMAIL_HOST, A4PRINT_EMAIL, A4PRINT_EMAIL_PASSWORD);
        // 4、创建邮件
        Message message = createSimpleMail(session, receiveEmail, code);
        // 5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());
        ts.close();
    }

    /**
     * @param session
     * @return
     * @throws Exception
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     * @Anthor:孤傲苍狼
     */
    public static MimeMessage createSimpleMail(Session session,
                                               String receiveEmail, String code) throws Exception {

        // 创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //设置自定义发件人昵称
        String nick = MimeUtility.encodeText(NICK);
        // 指明邮件的发件人
        message.setFrom(new InternetAddress(String.format(nick + " <%s>", A4PRINT_EMAIL)));
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(
                receiveEmail));
        // 邮件的标题
        message.setSubject(SUBJECT);
        String content = getEmailContent(code);

        // 邮件的文本内容
        message.setContent(content, "text/html;charset=utf-8");
        message.setSentDate(new Date());
        // 返回创建好的邮件对象
        return message;
    }

    /**A4printT
     * 邮箱验证码
     *
     * @param code
     * @return
     */
    private static String getEmailContent(String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("<h2 style='color:#58b2dc'>您的注册码为：</strong>").append(code).append("</strong>，五分钟内有效！</h2>");
        return sb.toString();
    }

    /**
     * 点击链接校验内容
     *
     * @param code
     * @param receiveEmail
     * @return
     */
    private static String getEmailContent(String code, String receiveEmail) {

        StringBuilder sb = new StringBuilder();
        String registerId = code + new Random().nextInt(10000);
        StringBuffer sbUrl = new StringBuffer();
        sbUrl.append(Constants.WEIN_SERVER_ADDRESS)
                .append("verifyEmail.do?code=").append(registerId)
                .append("&email=").append(receiveEmail);
        String url = sbUrl.toString();
        sb.append("<h1 style='color:#58b2dc'>请确认您的邮箱地址！</h1><br/>点击下面的链接进行邮箱验证：<br/>");
        sb.append("点击<a href='").append(url).append("'>").append(url).append("</a>完成注册");
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        verifyEmail("1024978310@qq.com", "123456");
    }

}
