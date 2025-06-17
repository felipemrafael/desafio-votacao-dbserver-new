package com.desafio.dbserver.pauta.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum VotoEnum {
    SIM("Sim"), NAO("Não");

    private String shortName;

    VotoEnum (String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String toString() {
        return shortName;
    }

    @JsonCreator
    public static VotoEnum create (String value) {
        if(value == null) {
            throw new IllegalArgumentException();
        }
        for(VotoEnum v : values()) {
            if(value.equals(v.getShortName())) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getShortName() {
        return shortName;
    }
}
