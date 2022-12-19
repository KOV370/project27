package org.laboratory.project27.runProgram;

import org.laboratory.project27.repository.DriverRepository;

public class TestDb {
    public static void main(String[] args) {
        new DriverRepository().findAll();

    }
}
