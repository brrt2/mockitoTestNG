package bookingsystemrevisited;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ClassRoom {

    private String name;
    private Equipment equipment;
    private int capacity;
    private Set<LocalDateTime> listOfBookedHours = new HashSet<>();

    public ClassRoom(String name, Equipment equipment, int capacity) {
        this.name = name;
        this.equipment = equipment;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public Set<LocalDateTime> getListOfBookedHours() {
        return listOfBookedHours;
    }

    public void addBookedHour(LocalDateTime localDateTime) {
        listOfBookedHours.add(localDateTime);
    }

    public String getName() {
        return name;
    }

    public Equipment getEquipment() {
        return equipment;
    }


    @Override
    public String toString() {
        return "ClassRoom{" +
                "name='" + name + '\'' +
                ", equipment=" + equipment +
                ", listOfBookedHours=" + listOfBookedHours +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassRoom classRoom = (ClassRoom) o;
        return Objects.equals(name, classRoom.name) &&
                equipment == classRoom.equipment;
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, equipment);
    }
}
