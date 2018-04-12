package raceresults;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import receresults.Client;
import receresults.messages.*;
import receresults.RaceResultsService;
import receresults.subscriptions.BoatRaceSubscription;
import receresults.subscriptions.F1RaceSubscription;
import receresults.subscriptions.HorseRaceSubscription;
import static org.assertj.core.api.Assertions.*;


import static org.mockito.Mockito.*;

@Test
public class RaceResultsServiceTest {

    private RaceResultsService raceResults;
    private Message message;
    private Client clientA;
    private Client clientB;
    private BoatRaceMessage boatRaceMessage;
    private HorseRaceMessage horseRaceMessage;
    private F1RaceMessage f1RaceMessage;
    private BoatRaceSubscription boatRaceSubscription;
    private HorseRaceSubscription horseRaceSubscription;
    private F1RaceSubscription f1RaceSubscription;
    private Logger logger;

    @BeforeMethod
    protected void setUp() {
        logger = mock(Logger.class);
        raceResults = new RaceResultsService(logger);
        message = mock(Message.class);
        clientA = mock(Client.class);
        clientB = mock(Client.class);

        boatRaceMessage = mock(BoatRaceMessage.class);
        horseRaceMessage = mock(HorseRaceMessage.class);
        f1RaceMessage = mock(F1RaceMessage.class);

        boatRaceSubscription = new BoatRaceSubscription(boatRaceMessage);
        horseRaceSubscription = new HorseRaceSubscription(horseRaceMessage);
        f1RaceSubscription = new F1RaceSubscription(f1RaceMessage);



    }



public void subscribedClientShouldReceiveMessage() {

    raceResults.addSubscriber(clientA);
    raceResults.send(message);

    verify(clientA).receive(message);
}

    public void messageShoouldBeSentToAllSubscribedClients() {

        raceResults.addSubscriber(clientA);
        raceResults.addSubscriber(clientB);
        raceResults.send(message);

        verify(clientA).receive(message);
        verify(clientB).receive(message);

    }


    public void notSubscribedClientShouldNotReceiveMessage() {

        raceResults.send(message);

        verify(clientA,never()).receive(message);
        verify(clientB,never()).receive(message);


    }

    public void shouldSendOnlyOneMessageToMultipleSubscriber() {

        raceResults.addSubscriber(clientA);
        raceResults.addSubscriber(clientA);
        raceResults.send(message);

        verify(clientA).receive(message);
    }

    public void unsubscribedClientShouldNotReceiveMessages() {

        raceResults.addSubscriber(clientA);
        raceResults.removeSubscriber(clientA);
        raceResults.send(message);

        verify(clientA,never()).receive(message);

    }

    public void shouldReceiveMessagesFromSubscribedToCategories() {

        raceResults.addSubscriberToCategory(clientA,boatRaceSubscription);
        raceResults.addSubscriberToCategory(clientA,horseRaceSubscription);
        raceResults.addSubscriberToCategory(clientB,f1RaceSubscription);

        raceResults.sendWithCategories();

        verify(clientA).receive(boatRaceMessage);
        verify(clientA).receive(horseRaceMessage);
        verify(clientB).receive(f1RaceMessage);

    }

    public void shouldNotReceiveMessagesFromCategoriesNotSubscribedTo() {

        raceResults.addSubscriberToCategory(clientA,boatRaceSubscription);
        raceResults.addSubscriberToCategory(clientA,horseRaceSubscription);
        raceResults.addSubscriberToCategory(clientB,f1RaceSubscription);

        raceResults.sendWithCategories();

        verify(clientB,never()).receive(boatRaceMessage);
        verify(clientB,never()).receive(horseRaceMessage);


    }

    public void shouldLogEverySentMessage() {
        raceResults.addSubscriberToCategory(clientA,boatRaceSubscription);
        raceResults.addSubscriberToCategory(clientA,horseRaceSubscription);
        raceResults.addSubscriberToCategory(clientB,f1RaceSubscription);

        raceResults.sendWithCategories();

        verify(logger,times(3)).log();


    }

    public void shouldAllowSubscribersToReceiveAnyNumberOfMessages() {
        raceResults.addSubscriberToCategory(clientA,boatRaceSubscription);
        raceResults.addSubscriberToCategory(clientA,horseRaceSubscription);
        raceResults.addSubscriberToCategory(clientA,f1RaceSubscription);

        raceResults.addSubscriberToCategory(clientB,boatRaceSubscription);
        raceResults.addSubscriberToCategory(clientB,horseRaceSubscription);
        raceResults.addSubscriberToCategory(clientB,f1RaceSubscription);

        raceResults.sendWithCategories();

        verify(clientA).receive(boatRaceMessage);
        verify(clientA).receive(horseRaceMessage);
        verify(clientA).receive(f1RaceMessage);
        verify(clientB).receive(f1RaceMessage);
        verify(clientB).receive(horseRaceMessage);
        verify(clientB).receive(boatRaceMessage);

    }


    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowAnExceptionWhenUnsubscribedClientTriesToUnsubscribe() {

        raceResults.unsubscribeClient(clientA);

    }

    public void shouldAllowToUnsubscribeAClient() {

        raceResults.addSubscriberToCategory(clientA,boatRaceSubscription);
        assertThat(raceResults.isClientSubscribed(clientA)).isTrue();
        raceResults.unsubscribeClient(clientA);
        assertThat(raceResults.isClientSubscribed(clientA)).isFalse();

    }






}
