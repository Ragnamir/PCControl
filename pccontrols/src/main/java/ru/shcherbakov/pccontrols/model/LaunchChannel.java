package ru.shcherbakov.pccontrols.model;

public class LaunchChannel {
    private String launcherName;
    private int boardIndex;
    private int channelIndex;

    public LaunchChannel(String launcherName, int boardIndex, int channelIndex) {
        this.launcherName = launcherName;
        this.boardIndex = boardIndex;
        this.channelIndex = channelIndex;
    }

    public LaunchChannel() {
    }

    public String getLauncherName() {
        return launcherName;
    }

    public void setLauncherName(String launcherName) {
        this.launcherName = launcherName;
    }

    public int getBoardIndex() {
        return boardIndex;
    }

    public void setBoardIndex(int boardIndex) {
        this.boardIndex = boardIndex;
    }

    public int getChannelIndex() {
        return channelIndex;
    }

    public void setChannelIndex(int channelIndex) {
        this.channelIndex = channelIndex;
    }
}
