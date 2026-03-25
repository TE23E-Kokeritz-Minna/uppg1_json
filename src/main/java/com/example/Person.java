package com.example;

import java.util.Objects;

public class Person {
    public String namn;

    public String getNamn() {
        return namn;
    }

    @Override
    public String toString() {
        return namn+ "\n";
    }


       @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return namn.equals( person.namn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namn);
    }
}
