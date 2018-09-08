package com.cultivation.javaBasic.showYourIntelligence;

import java.util.Objects;

@SuppressWarnings("unused")
public class PersonForEquals implements Comparable<PersonForEquals> {
    private final String name;
    private final short yearOfBirth;
    private Object aNull;

    public PersonForEquals(String name, short yearOfBirth) {
        if (name == null) {
            throw new IllegalArgumentException("name is mandatory.");
        }

        if (yearOfBirth <= 1900 || yearOfBirth >= 2019) {
            throw new IllegalArgumentException("year of birth is out of range.");
        }

        this.name = name;
        this.yearOfBirth = yearOfBirth;
    }


    public String getName() {
        return name;
    }

    public short getYearOfBirth() {
        return yearOfBirth;
    }

    @Override
    public int compareTo(PersonForEquals person) {
        if (person == null){
            throw new NullPointerException();
        }

        int compare = person.name.compareTo(this.name);
        if(compare != 0){
            return compare;
        }

        if (this.yearOfBirth == person.yearOfBirth) return 0;
        return this.yearOfBirth - person.yearOfBirth > 0 ? 1 : -1;
    }

    @SuppressWarnings("Contract")
    @Override
    public boolean equals(Object obj) {
        // TODO: please modify the following code to pass the test
        // <--start
        if (obj == null || this.getClass() != obj.getClass()) return false;

        if (this == obj) return true;

        PersonForEquals person = (PersonForEquals) obj;

        return person.name.equals(this.name) && person.yearOfBirth == this.yearOfBirth;
        // --end-->
    }

    @Override
    public int hashCode() {
        // TODO: please modify the following code to pass the test
        // <--start
        return Objects.hash(name, yearOfBirth);
        // --end-->
    }
}
