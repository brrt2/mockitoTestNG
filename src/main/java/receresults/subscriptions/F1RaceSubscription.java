package receresults.subscriptions;

import receresults.Client;
import receresults.messages.BoatRaceMessage;
import receresults.messages.F1RaceMessage;

import java.util.logging.Level;
import java.util.logging.Logger;

public class F1RaceSubscription implements Subscription {

    F1RaceMessage f1RaceMessage;


    public F1RaceSubscription(F1RaceMessage f1RaceMessage) {
        this.f1RaceMessage = f1RaceMessage;
    }

    @Override
    public void send(Client client) {

        client.receive(f1RaceMessage);
        System.out.println("F1 race message sent to " + client.toString());
    }
}
