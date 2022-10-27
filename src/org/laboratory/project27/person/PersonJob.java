package org.laboratory.project27.person;

public enum PersonJob {
    //перечисления в виде char с параметрами для удобства ввода
    //todo не найду как вводить перечисления с консоли при вводе нового person
    A("DIRECTOR"), B("MANAGER"), C("DRIVER"), D("SELLER"), E("LOADER"), F("OFFICEMANAGER");
    private final String job;

    PersonJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return this.job;
    }
}
