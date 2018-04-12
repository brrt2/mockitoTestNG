package receresults.subscriptions;

import receresults.Client;
import receresults.RaceResultsService;
import receresults.messages.BoatRaceMessage;
import receresults.messages.Message;


public class BoatRaceSubscription implements Subscription {

    BoatRaceMessage boatRaceMessage;

    public BoatRaceSubscription(BoatRaceMessage boatRaceMessage) {
        this.boatRaceMessage = boatRaceMessage;
    }

    @Override
    public void send(Client client) {

        client.receive(boatRaceMessage);
        System.out.println("Boat Race message sent to " + client.toString());

    }
}
