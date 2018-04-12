package receresults.subscriptions;

import receresults.Client;
import receresults.messages.HorseRaceMessage;

public class HorseRaceSubscription implements Subscription {

    HorseRaceMessage horseRaceMessage;

    public HorseRaceSubscription(HorseRaceMessage horseRaceMessage) {
        this.horseRaceMessage = horseRaceMessage;
    }

    @Override
    public void send(Client client) {
        client.receive(horseRaceMessage);
        System.out.println("Horse races message sent to" + client.toString());
    }
}
