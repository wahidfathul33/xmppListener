package vgame.xmppListener;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.roster.RosterListener;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.jid.Jid;
import org.jxmpp.jid.impl.JidCreate;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.util.Collection;

public class XMPPListener {

    private static IMInterface imInterface;
    private static ChatManager chatManager;

    public static void initiateXMPPClient() {
        XmppListenerApplication.logger.info("Logging in to XMPP . . . .");
        try {
            XMPPTCPConnectionConfiguration config = XMPPTCPConnectionConfiguration.builder()
                    .setUsernameAndPassword(Properties.xmppUsername, Properties.xmppPassword)
                    .setXmppDomain(Properties.xmppDomain)
                    .setHost(Properties.xmppHost)
                    .build();

            AbstractXMPPConnection conn1 = new XMPPTCPConnection(config);
            conn1.connect();
            conn1.login();
            System.out.println("==== XMPP Logged In ====");

            chatManager = ChatManager.getInstanceFor(conn1);
            chatManager.addIncomingListener((from, message, chat) -> {
                String messageBody = message.getBody();
                XmppListenerApplication.logger.info("Message from " + from + " : " + messageBody);
                String[] messageArray = messageBody.split("#");
                XmppListenerApplication.logger.info("Parsed Message to request : " + Properties.vGameUrl);
                String url = "http://" +Properties.vGameUrl + "/" + messageArray[0] + "/" + messageArray[1] + "/" + messageArray[2] + "/" + messageArray[3];
                String responseMessage = "";
                try {
                    responseMessage = ApiCall.makeRequest(url);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                sendMessage(responseMessage, from);
            });

            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() {
                    conn1.disconnect();
                    XmppListenerApplication.logger.info("Disconnected");
                }
            });

            boolean a = true;
            while(a){
            }

        } catch (SmackException | IOException | XMPPException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(String imMessage, EntityBareJid from) {
        XmppListenerApplication.logger.info("Sending Response Message To : " + from);
        try {
            EntityBareJid jid = JidCreate.entityBareFrom(from);
            Chat chat = chatManager.chatWith(jid);
            chat.send(imMessage);
            XmppListenerApplication.logger.info("Message sent");
        } catch (SmackException | IOException | InterruptedException e) {
            e.printStackTrace();
            XmppListenerApplication.logger.info("Failed Send Message");
        }
    }


}
