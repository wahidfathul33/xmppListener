package vgame.xmppListener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XmppListenerApplication {
    public static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(XmppListenerApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(XmppListenerApplication.class, args);
        XMPPListener.initiateXMPPClient();
    }

}
