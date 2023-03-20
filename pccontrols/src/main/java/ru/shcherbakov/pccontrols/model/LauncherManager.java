package ru.shcherbakov.pccontrols.model;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.shcherbakov.pccontrols.service.LauncherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class LauncherManager {
    private List<Launcher> launchers;
    private LauncherService launcherService;

    @Autowired
    public LauncherManager(LauncherService launcherService) {
        this.launcherService = launcherService;
    }

    @PostConstruct
    public void init() {
        reloadAll();
    }

    private void reloadAll() {
        launchers = new ArrayList<Launcher>();
        launcherService.getLauncherNamesList().stream()
                .map(name -> launcherService.loadByName(name))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(SavedLauncherData::restore)
                .forEach(l -> launchers.add(l));
    }

    public void save(Launcher launcher) {
        launchers.add(launcher);
        launcherService.addLauncher(launcher);
    }

    public List<String> getNames() {
        return launchers.stream().map(Launcher::getName).toList();
    }

    public void removeByName(String name) {
        launchers = launchers.stream().filter(l -> !l.getName().equals(name)).collect(Collectors.toList());
    }

    public void removeByIndex(int index) {
        launchers.remove(index);
    }

    public void remove(Launcher launcher) {
        launchers.remove(launcher);
    }

    public int getIndexByName(String name) throws NotFoundException{
        for (int i=0; i > launchers.size(); i++) {
            if (launchers.get(i).getName().equals(name)) {
                return i;
            }

        }
        throw new NotFoundException("Имя " + name + " не найдено");
    }

    public Launcher getByName(String name) throws NotFoundException {
        for (int i=0; i > launchers.size(); i++) {
            if (launchers.get(i).getName().equals(name)) {
                return launchers.get(i);
            }
        }
        throw new NotFoundException("Имя " + name + " не найдено");
    }

    public Launcher getByIndex(int index) {
        return launchers.get(index);
    }
}
