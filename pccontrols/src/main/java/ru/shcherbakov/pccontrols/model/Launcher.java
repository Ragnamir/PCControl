package ru.shcherbakov.pccontrols.model;

import java.util.ArrayList;
import java.util.List;

public class Launcher {
    private List<LauncherBoard> boards;
    private String name;
    private long launcherCode;
    private long configuredChannel;
    private LauncherState launcherState;

    public List<LauncherBoard> getBoards() {
        return boards;
    }

    public void setBoards(ArrayList<LauncherBoard> boards) {
        this.boards = boards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLauncherCode() {
        return launcherCode;
    }

    public void setLauncherCode(long launcherCode) {
        this.launcherCode = launcherCode;
    }

    public long getConfiguredChannel() {
        return configuredChannel;
    }

    public void setConfiguredChannel(long configuredChannel) {
        this.configuredChannel = configuredChannel;
    }

    public LauncherState getLauncherState() {
        return launcherState;
    }

    public void setLauncherState(LauncherState launcherState) {
        this.launcherState = launcherState;
    }
}
