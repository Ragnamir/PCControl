package ru.shcherbakov.pccontrols.model;

import java.util.List;
/**
 * Класс описывающий элемент пуска,
 * т.е. множество каналов запускаемых одновременно
 * */
public class LaunchElement {
    List<LaunchChannel> launchChannels;

    public List<LaunchChannel> getLaunchChannels() {
        return launchChannels;
    }

    public void setLaunchChannels(List<LaunchChannel> launchChannels) {
        this.launchChannels = launchChannels;
    }

    public void addLaunchChannels(LaunchElement element) {
        this.launchChannels.addAll(element.getLaunchChannels());
    }
}
