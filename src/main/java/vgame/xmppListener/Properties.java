package vgame.xmppListener;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {

    public static String xmppUsername;
    @Value("${xmpp.username}")
    public void setXmppUsername(String value) {
        xmppUsername = value;
    }

    public static String xmppPassword;
    @Value("${xmpp.password}")
    public void setXmppPassword(String value) {
        xmppPassword = value;
    }

    public static String xmppDomain;
    @Value("${xmpp.domain}")
    public void setXmppDomain(String value) {
        xmppDomain = value;
    }

    public static String xmppHost;
    @Value("${xmpp.host}")
    public void setXmppHost(String value) {
        xmppHost = value;
    }

    public static String xmppDestination;
    @Value("${xmpp.destination}")
    public void setXmppDestination(String value) {
        xmppDestination = value;
    }

    public static String vGameUrl;
    @Value("${vgame.url}")
    public void setvGameUrl(String value) {
        vGameUrl = value;
    }

}
