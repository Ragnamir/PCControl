package ru.shcherbakov.pccontrols.model;

/**
 * Класс хранящий данные о состоянии одной платы пускающего устройства
 */

public class LauncherBoard {
    private boolean[] check = new boolean[8];           //данные текущей прозвонки
    private boolean[] everChecked = new boolean[8];     //данные о том, была ли когда-то успешная прозвонка
    private boolean[] fired = new boolean[8];           //данные о том, был ли отстрел
    private boolean[] programmed = new boolean[8];      //данные о том, запрограммирован ли канал

    public LauncherBoard() {
        for (int i=0; i<check.length; i++){
            check[i]=false;
            everChecked[i]=false;
            fired[i]=false;
            programmed[i]=false;
        }
    }

    public void setCheck(int index, boolean state) {
        this.check[index] = state;

        if (state) {
            everChecked[index] = true;
        }
    }

    public void setProgrammed(int index, boolean state) {
        this.programmed[index] = state;
    }

    public void setFired(int index, boolean state) {
        this.fired[index] = state;
    }

    public void clearEverChecked() {
        for (int i=0; i<check.length; i++){
            everChecked[i]=false;
        }
    }

    public void clearAll() {
        for (int i=0; i<check.length; i++){
            check[i]=false;
            everChecked[i]=false;
            fired[i]=false;
            programmed[i]=false;
        }
    }

    public boolean[] getCheck() {
        return check;
    }

    public boolean[] getEverChecked() {
        return everChecked;
    }

    public boolean[] getFired() {
        return fired;
    }

    public boolean[] getProgrammed() {
        return programmed;
    }
}
