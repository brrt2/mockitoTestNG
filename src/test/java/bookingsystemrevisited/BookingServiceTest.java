package bookingsystemrevisited;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
import java.time.LocalDateTime;
import java.time.Month;

import static org.testng.Assert.*;

import static org.mockito.Mockito.mock;
@Test
public class BookingServiceTest {

    private BookingService bookingService;
    private ClassRoom classRoom1;
    private ClassRoom classRoom2;
    private ClassRoom classRoom3;
    private String NAME_OF_CLASSROOM_TO_BE_BOOKED = "A2";


    @BeforeMethod
    public void setUp() {
        bookingService = new BookingService();
        classRoom1 = new ClassRoom("A1",Equipment.BLACKBOARD,10);
        classRoom2 = new ClassRoom(NAME_OF_CLASSROOM_TO_BE_BOOKED,Equipment.PROJECTOR,20);
        classRoom3 = new ClassRoom("A3",Equipment.LAPTOP,15);
    }


    public void shouldListAllExistingClassrooms() {

        int expectedNumberOfClassrooms = 2;

        bookingService.addClassroom(new ClassRoom("A1",Equipment.BLACKBOARD,10));
        bookingService.addClassroom(new ClassRoom("A2",Equipment.PROJECTOR,15));

        assertThat(bookingService.listAllRooms().size()).isEqualTo(expectedNumberOfClassrooms);
    }

    public void listAllAvailableClassroomsForAGivenTimeSlot() {

        int expectedNumberOfAvailableRooms = 1;

        LocalDateTime localDateTime1 = LocalDateTime.of(2018,Month.APRIL,15,14,30);

        bookingService.addClassroom(classRoom1);
        bookingService.addClassroom(classRoom2);
        bookingService.addClassroom(classRoom3);

        classRoom1.addBookedHour(localDateTime1);
        classRoom2.addBookedHour(localDateTime1);

        assertThat(bookingService.listAvailableClassrooms(localDateTime1).size()).isEqualTo(expectedNumberOfAvailableRooms);

    }

    public void shouldBeAbleToBookAClassRoomByName() {
        int EXPECTED_NUMBER_OF_AVAILABLE_ROOMS = 2;

        LocalDateTime localDateTime1 = LocalDateTime.of(2018,Month.APRIL,15,14,30);

        bookingService.addClassroom(classRoom1);
        bookingService.addClassroom(classRoom2);
        bookingService.addClassroom(classRoom3);

        bookingService.bookClassroomByName(NAME_OF_CLASSROOM_TO_BE_BOOKED,localDateTime1);

        assertThat(bookingService.listAvailableClassrooms(localDateTime1).size()).isEqualTo(EXPECTED_NUMBER_OF_AVAILABLE_ROOMS);
    }

    public void shouldBeAbleToBookClassroomWithConstraints() {
        int DESIRED_CLASSROOM_CAPACITY = 20;
        int DESIRED_CLASSROOM_CAPACITY_FOR_FALSEASSERTION = 10;


        LocalDateTime bookingDate = LocalDateTime.of(2018,Month.APRIL,15,14,30);

        bookingService.addClassroom(classRoom2);

        assertThat(bookingService.bookWithConstraints(DESIRED_CLASSROOM_CAPACITY,Equipment.PROJECTOR, bookingDate)).isTrue();

        assertThat(bookingService.bookWithConstraints(DESIRED_CLASSROOM_CAPACITY,Equipment.BLACKBOARD, bookingDate)).isFalse();
        assertThat(bookingService.bookWithConstraints(DESIRED_CLASSROOM_CAPACITY_FOR_FALSEASSERTION,Equipment.PROJECTOR, bookingDate)).isFalse();

    }

    public void shouldAllowOnlyBookingsForOneHour() {

        LocalDateTime firstbooking = LocalDateTime.of(2018,Month.APRIL,15,14,30);
        LocalDateTime tooShortIntervalBooking = LocalDateTime.of(2018,Month.APRIL,15,14,39);


    }




}