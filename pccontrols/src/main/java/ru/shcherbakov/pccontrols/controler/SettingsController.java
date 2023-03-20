package ru.shcherbakov.pccontrols.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.shcherbakov.pccontrols.model.LauncherManager;
import ru.shcherbakov.pccontrols.model.SavedLauncherData;
import ru.shcherbakov.pccontrols.service.ComPortService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/settings")
public class SettingsController {
    private ComPortService comPortService;
    private LauncherManager launcherManager;

    @Autowired
    public SettingsController(ComPortService comPortService, LauncherManager launcherManager) {
        this.comPortService = comPortService;
        this.launcherManager = launcherManager;
    }

    @GetMapping(value = "ports")
    public List<String> getPorts() {
        return comPortService.getPortsList();
    }

    @GetMapping(value = "launchers")
    public List<String> getKnownLaunchers() {
        return launcherManager.getNames();
    }

    @PostMapping(value = "create_launcher")
    public void createLauncher(@RequestBody SavedLauncherData s) {
        launcherManager.save( s.restore() );
    }
}
