package ru.shcherbakov.pccontrols.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shcherbakov.pccontrols.dto.UIDTO;
import ru.shcherbakov.pccontrols.model.Launcher;
import ru.shcherbakov.pccontrols.model.LauncherManager;
import ru.shcherbakov.pccontrols.model.SavedLauncherData;

import java.util.ArrayList;

@Component
public class UIDTOCreator {
    private ComPortService comPortService;
    private LauncherManager launcherManager;

    @Autowired
    public UIDTOCreator(ComPortService comPortService, LauncherManager launcherManager) {
        this.comPortService = comPortService;
        this.launcherManager = launcherManager;
    }

    public UIDTO create() {
        UIDTO dto = new UIDTO();

        /*Известные порты*/
        dto.setPorts(comPortService.getPortsList());
        /*Названия всех известных пускалок*/
        dto.setKnownLaunchers(launcherManager.getNames());

        /*Права*/
        dto.setCanFire(true);
        dto.setCanProgramm(false);
        dto.setLoorker(false);

        /*Устройство связи подключено*/
        dto.setDeviceConnected(false);

        Launcher launcher = (new SavedLauncherData("Виртуальная пускалка", 12, 43, 8)).restore();
        for (int i=0; i < launcher.getBoards().size(); i++) {
            launcher.getBoards().get(i).getCheck()[i] = true;
        }

        ArrayList<Launcher> launchers = new ArrayList<>();
        launchers.add(launcher);

        /*Пускалки и их состояние*/
        dto.setLaunchersInUse(launchers);

        return dto;
    }
}
