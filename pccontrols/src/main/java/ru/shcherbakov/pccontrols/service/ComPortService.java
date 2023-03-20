package ru.shcherbakov.pccontrols.service;

import jssc.SerialPortList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ComPortService {
    public List<String> getPortsList() {
        return Arrays.stream(SerialPortList.getPortNames()).toList();
    }
}
