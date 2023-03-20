package ru.shcherbakov.pccontrols.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.shcherbakov.pccontrols.dto.UIDTO;
import ru.shcherbakov.pccontrols.service.UIDTOCreator;

@Controller
public class UIUpdater {
    private Logger logger = LoggerFactory.getLogger(UIUpdater.class);
    private UIDTOCreator uidtoCreator;

    @Autowired
    public UIUpdater(UIDTOCreator uidtoCreator) {
        this.uidtoCreator = uidtoCreator;
    }

    /*
    @MessageMapping("/updatelab")
    public void updateLab(AllLabs labs) throws Exception {
        logger.info("Got request for updating labs: ");
        logger.info(labs.toString());
        allLabs.updateLabs(labs);
        logger.info(allLabs.toString());
        scherduledUpdater.saveLabConfig();
    }

    @MessageMapping("/updatemessage")
    public void updateMessage(AllLabs labs) throws Exception {
        logger.info("Got request for updating messages: " + labs.toString());
        allLabs.updateMessages(labs);
    }

    @MessageMapping("/redeploybeacon")
    public void updateMessage(String ip) throws Exception {
        ip = ip.replaceAll("\"", "");
        logger.info("Got request for redeploying beacon to " + ip);
        Runtime.
                getRuntime().
                exec("cmd /c start \"\" obs\\deploy.bat " + ip);
    }*/

    @MessageMapping("/getdata")
    @SendTo("/topic/data")
    public UIDTO sendLabData() {
        logger.info("Got request for data");
        return uidtoCreator.create();
    }
}
