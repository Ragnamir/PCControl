package ru.shcherbakov.pccontrols.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import ru.shcherbakov.pccontrols.model.Launcher;
import ru.shcherbakov.pccontrols.model.SavedLauncherData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LauncherService {
    private static final String LAUNCHER_PATH = "launchers/";

    @PostConstruct
    public void init() {
        File launcherFolder = new File(LAUNCHER_PATH);
        if (!launcherFolder.exists()) {
            launcherFolder.mkdir();
        }
    }

    public List<String> getLauncherNamesList() {
        File folder = new File(LAUNCHER_PATH);
        ArrayList<Optional<SavedLauncherData>> launchersData = new ArrayList<>();
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isFile() && fileEntry.getPath().matches(".+\\.json")) {
                launchersData.add(readLauncher(fileEntry));
            }
        }
        return launchersData.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(SavedLauncherData::getName).toList();
    }

    private void saveLauncher(SavedLauncherData savedLauncherData) {
        File file = new File(LAUNCHER_PATH + savedLauncherData.getName() + ".json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            file.createNewFile();
            mapper.writeValue(file, savedLauncherData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Optional<SavedLauncherData> readLauncher(File file) {
        ObjectMapper mapper = new ObjectMapper();
        SavedLauncherData data = null;
        try {
            data = mapper.readValue(file, SavedLauncherData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Optional<SavedLauncherData> op = Optional.ofNullable(data);
        return op;
    }

    public void addLauncher(Launcher launcher) {
        saveLauncher(new SavedLauncherData(launcher));
    }

    public Optional<SavedLauncherData> loadByName(String name) {
        return readLauncher(new File(LAUNCHER_PATH + name + ".json"));
    }
}
