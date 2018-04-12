package receresults;

import receresults.messages.Logger;
import receresults.messages.Message;
import receresults.subscriptions.Subscription;

import java.util.*;

public class RaceResultsService {

    private Logger logger;
    private Collection<Client> clients = new HashSet<>();

    private Map<Client,Set<Subscription>> clientsWithCategories = new HashMap<>();

    public RaceResultsService(Logger logger) {
        this.logger = logger;
    }

    public void addSubscriber(Client client) {
        this.clients.add(client);
    }

    public void addSubscriberToCategory(Client client, Subscription subscription) {

        if(clientsWithCategories.containsKey(client)){
            Set<Subscription> setOfSubscriptions;
            setOfSubscriptions = clientsWithCategories.get(client);
            setOfSubscriptions.add(subscription);
            clientsWithCategories.put(client,setOfSubscriptions);
        }else {
            Set<Subscription> newSetOfSubscriptions = new HashSet<>();
            newSetOfSubscriptions.add(subscription);
            clientsWithCategories.put(client,newSetOfSubscriptions);
        }
    }

    public void send(Message message) {
        for (Client client : clients) {
            client.receive(message);
        }
    }

    public void sendWithCategories() {

        for(Client client : clientsWithCategories.keySet()) {

            clientsWithCategories.get(client).forEach(x->{
                x.send(client);
                logger.log();
            });

        }

    }

    public void removeSubscriber(Client client) {
        clients.remove(client);
    }

    public boolean isClientSubscribed(Client client) {

        return clientsWithCategories.containsKey(client);
    }


    public void unsubscribeClient(Client client) {

        if(!clientsWithCategories.containsKey(client)) throw new IllegalArgumentException();
        else clientsWithCategories.remove(client);
    }
}
