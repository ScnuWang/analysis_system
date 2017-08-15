package cn.geekview.analysisSystem.utils;

import jodd.mail.Email;
import jodd.mail.SendMailSession;
import jodd.mail.SmtpServer;

public class MailUtil {
	public static void sendEmail(String subject, String text) {
		String from = "message@geekview.cn";// 用户名，登录邮箱的账号
		String psw = "GeekView2017";// 授权码
		String to = "jason@geekview.cn";
		Email email = Email.create().from(from).to(to).subject(subject)// 邮件主题
				.addText(text);// 邮件内容

		SmtpServer smtpServer = SmtpServer.create("smtp.exmail.qq.com")
				.authenticateWith(from, psw);

		SendMailSession session = smtpServer.createSession();
		session.open();
		session.sendMail(email);// 执行发送
		session.close();
	}
}
