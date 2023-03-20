package ru.shcherbakov.pccontrols.model;

import java.util.SortedMap;

/**
 * Класс описывающий программу пуска
 * ключевая часть - соответствие времени элементу пуска
 * */

public class LaunchProgram {
    private SortedMap<Long, LaunchElement> program;

    public LaunchProgram() {
    }

    public void add(Long time, LaunchElement element) {
        if (program.containsKey(time)) {
            program.get(time).addLaunchChannels(element);
        } else {
            program.put(time, element);
        }
    }

    public void remove(Long time) {
        program.remove(time);
    }

    public Long getNext(Long time) {
        return program.tailMap(time).firstKey();
    }

    
}
