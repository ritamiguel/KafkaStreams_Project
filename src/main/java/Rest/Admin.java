package Rest;

import Classes.Country;
import Classes.Item;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import javax.persistence.*;
import javax.ws.rs.client.*;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.*;
import java.*;

public class Admin {
    static Dictionary<String,String> waiting;

    private static Country generateRandomCountries() {
        Country country = new Country();
        String[] countryCodes = Locale.getISOCountries();
        for (String countryCode : countryCodes) {
            Locale obj = new Locale("", countryCode);
            System.out.println("Classes.Country Name = " + obj.getDisplayCountry());
            country.setName(obj.getDisplayCountry());
        }
        return country;
    }


    private static Item generateRandomItems() {
        List<String> itemsList = new ArrayList<>();
        itemsList.add("Computador Asus");
        itemsList.add("Smartphone Samsung");
        itemsList.add("Mouse Sony");
        itemsList.add("Pen drive USB");
        itemsList.add("Car Opel");
        itemsList.add("Jeans Zara Women");
        itemsList.add("T-shirt Zara Man");
        itemsList.add("Jacket H&M");

        List<String> countries = new ArrayList<>();
        String[] countryCodes = Locale.getISOCountries();
        for (String countryCode : countryCodes) {
            Locale obj = new Locale("", countryCode);
            System.out.println("Classes.Country Name = " + obj.getDisplayCountry());
            countries.add(obj.getDisplayCountry());
        }

        float max = 5000;
        float min = 5;

        Random random = new Random();
        int rand_item = random.nextInt(itemsList.size());
        float rand_price = min + random.nextFloat() * (max - min);
        int rand_country = random.nextInt(countries.size());
        int numUnits = random.nextInt(50)+1;
        String itemName = itemsList.get(rand_item);
        String countryName = countries.get(rand_country);

        String generate_items = itemName + numUnits + countryName;

        Country c = new Country();
        c.setName(countryName);
        Item items = null;
        for (int count = 0; count<50; count++) {
            items = new Item();
            items.setCountry(c);
            items.setName(itemName);
            items.setNumber_of_units(numUnits);
            items.setPrice(rand_price);
        }

        return items;
    }

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("shopapplication");
        EntityManager em = emf.createEntityManager();
        Properties props = Utilities.consumerAndProducer();

        waiting = new Hashtable<>();

        Client client = ClientBuilder.newClient();
        //WebTarget webTarget = client.target("http://localhost:9998/products/");
        //webTarget.request().post(Entity.entity("ShopStreams", MediaType.WILDCARD_TYPE));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add countries to database\n" +
                    "2. List countries from the database \n" +
                    "3. Add items for sale in the shop to the database \n" +
                    "4. List items from the database \n" +
                    "5. Get the revenue per item \n" +
                    "6. Get the expenses per item\n" +
                    "7. Get the profit per item \n" +
                    "8. Get the total revenues \n" +
                    "9. Get the total expenses \n" +
                    "10. Get the total profit \n" +
                    "11. Get the average amount spent in each purchase (separated by item) \n" +
                    "12. Get the average amount spent in each purchase (aggregated for all items)\n" +
                    "13. Get the item with the highest profit of all \n" +
                    "14. Get the total revenue in the last hour \n" +
                    "15. Get the total expenses in the last hour \n" +
                    "16. Get the total profits in the last hour \n" +
                    "17. Get the name of the country with the highest sales per item. Include the value of such sales ");

            int option = scanner.nextInt();
            if (option == 1) {
                Country countryGenerated = generateRandomCountries();
                Country country;
                long id;

                try {
                    Query newQuery = em.createQuery("from Country c where c.name=?1");
                    newQuery.setParameter(1, countryGenerated.getName());
                    country = (Country) newQuery.getSingleResult();
                    System.out.println(country.getName());

                }  catch (NoResultException NRE) {
                    System.out.println("Country does not exist");

                    Country new_country = new Country(countryGenerated.getName());
                    id = new_country.getId();
                    em.persist(new_country);
                }

            } else if(option == 2){
                Query newQuery = em.createQuery("select c from Country c");
                List<Country> results = newQuery.getResultList();

                if(results.isEmpty()){
                    System.out.println("Does not exist any country on database");
                }


            } else if(option == 3){
                /*webTarget = client.target("http://localhost:9998/products/orders");
                Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
                */
                Item itemsGenerated = generateRandomItems();
                Item item;
                long id;
                int units;
                float price;

                try {
                    Query newQuery = em.createQuery("select i from Item i where i.name=?1");
                    newQuery.setParameter(1, itemsGenerated.getName());
                    item = (Item)newQuery.getSingleResult();
                    System.out.println(item.getName() + " " + item.getNumber_of_units() + " " + item.getPrice() + " " + item.getCountry());

                    price = itemsGenerated.getPrice();
                    float new_price = (float)(price + (price * 0.3));
                    item.setNumber_of_units(item.getNumber_of_units()+itemsGenerated.getNumber_of_units());
                    item.setPrice(new_price);
                    id = item.getId();
                    units = item.getNumber_of_units();

                }  catch (NoResultException NRE) {
                    System.out.println("Item does not exist");

                    price = itemsGenerated.getPrice();
                    float new_price = (float)(price + (price * 0.3));


                    Item new_item = new Item(itemsGenerated.getName(), new_price, itemsGenerated.getNumber_of_units(), itemsGenerated.getSale(), itemsGenerated.getCountry());
                    id = new_item.getId();
                    units = new_item.getNumber_of_units();
                    em.persist(new_item);
                }

            } else if(option == 4){
                Query newQuery = em.createQuery("select i.price from Item i");
                List<Country> results = newQuery.getResultList();

                if(results.isEmpty()){
                    System.out.println("Does not exist any country on database");
                }

            } else if(option == 5){
                Scanner scan = new Scanner(System.in);
                int id_item = scan.nextInt();
                Query newQuery = em.createQuery("select i from Item i where i.id=?1");
                newQuery.setParameter(1, id_item);

            } else if(option == 6){
                Scanner scan = new Scanner(System.in);

            } else if(option == 7){
                Scanner scan = new Scanner(System.in);

            } else if(option == 8){

            } else if(option == 9){

            } else if(option == 10){

            } else if(option == 11){

            } else if(option == 12){

            } else if(option == 13){

            } else if(option == 14){

            } else if(option == 15){

            } else if(option == 16){

            } else if(option == 17){

            }
        }
    }

}
