package bookingsystemrevisited;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class BookingService {

    private Set<ClassRoom> classRooms = new HashSet<>();

    public Set<ClassRoom> getClassRooms() {
        return classRooms;
    }

    Set<ClassRoom> listAllRooms() {
        return classRooms;
    }

    void addClassroom(ClassRoom classRoom) {

        classRooms.add(classRoom);
    }

    Set<ClassRoom> listAvailableClassrooms(LocalDateTime localDateTime) {

        Set<ClassRoom> availableRooms = new HashSet<>();

        classRooms.forEach(x -> {
            if (!x.getListOfBookedHours().contains(localDateTime)) {
                availableRooms.add(x);
            }
        });
        return availableRooms;
    }


    void bookClassroomByName(String name_of_classroom_to_be_booked, LocalDateTime bookingDate) {

        boolean nameFound = false;

        for (ClassRoom cr : classRooms) {

            if (cr.getName().equals(name_of_classroom_to_be_booked)) {
                nameFound = true;
                cr.addBookedHour(bookingDate);
            }


      if (!nameFound) throw new IllegalArgumentException("No such classroom name found");

        }

    }

    boolean bookWithConstraints(int desired_classroom_capacity, Equipment equipment, LocalDateTime bookingDate) {

        for (ClassRoom cr : classRooms) {

            if (cr.getCapacity() == desired_classroom_capacity && cr.getEquipment() == equipment) {
                cr.addBookedHour(bookingDate);
                return true;
            }

        }

        return false;
    }
}
