package ru.shcherbakov.pccontrols.model;

import java.util.ArrayList;
import java.util.List;

public class SavedLauncherData {
    private String name;
    private long launcherCode;
    private long configuredChannel;
    private int boardCount;

    public SavedLauncherData() {
    }

    public SavedLauncherData(String name, long launcherCode, long configuredChannel, int boardCount) {
        this.name = name;
        this.launcherCode = launcherCode;
        this.configuredChannel = configuredChannel;
        this.boardCount = boardCount;
    }

    public SavedLauncherData(Launcher launcher) {
        this.name = launcher.getName();
        this.launcherCode = launcher.getLauncherCode();
        this.configuredChannel = launcher.getConfiguredChannel();
        this.boardCount = launcher.getBoards().size();
    }

    public Launcher restore() {
        Launcher launcher = new Launcher();
        launcher.setName(this.name);
        launcher.setLauncherCode(this.launcherCode);
        launcher.setConfiguredChannel(this.configuredChannel);
        launcher.setLauncherState(LauncherState.NOT_CONNECTED);

        ArrayList<LauncherBoard> boards = new ArrayList<LauncherBoard>(8);

        for (int i = 0; i < 8; i++) {
            boards.add(new LauncherBoard());
        }

        launcher.setBoards(boards);
        return launcher;
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

    public int getBoardCount() {
        return boardCount;
    }

    public void setBoardCount(int boardCount) {
        this.boardCount = boardCount;
    }
}
