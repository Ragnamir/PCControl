package ru.shcherbakov.pccontrols.dto;

import ru.shcherbakov.pccontrols.model.Launcher;

import java.util.List;

public class UIDTO {
    private List<Launcher> launchersInUse;
    private boolean canFire;
    private boolean canProgramm;
    private boolean loorker;
    private boolean deviceConnected;
    private List<String> ports;
    private List<String> knownLaunchers;

    public UIDTO() {
    }

    public List<Launcher> getLaunchersInUse() {
        return launchersInUse;
    }

    public void setLaunchersInUse(List<Launcher> launchersInUse) {
        this.launchersInUse = launchersInUse;
    }

    public boolean isCanFire() {
        return canFire;
    }

    public void setCanFire(boolean canFire) {
        this.canFire = canFire;
    }

    public boolean isCanProgramm() {
        return canProgramm;
    }

    public void setCanProgramm(boolean canProgramm) {
        this.canProgramm = canProgramm;
    }

    public boolean isLoorker() {
        return loorker;
    }

    public void setLoorker(boolean loorker) {
        this.loorker = loorker;
    }

    public boolean isDeviceConnected() {
        return deviceConnected;
    }

    public void setDeviceConnected(boolean deviceConnected) {
        this.deviceConnected = deviceConnected;
    }

    public List<String> getPorts() {
        return ports;
    }

    public void setPorts(List<String> ports) {
        this.ports = ports;
    }

    public List<String> getKnownLaunchers() {
        return knownLaunchers;
    }

    public void setKnownLaunchers(List<String> knownLaunchers) {
        this.knownLaunchers = knownLaunchers;
    }
}
