package com.vilensky.carrental.database_fillers;

import com.vilensky.carrental.entities.Car;
import com.vilensky.carrental.entities.Client;

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

public class Filler {
    private final String carSqlName = "fill_car_table.sql";
    private final String clientSqlName = "fill_client_table.sql";
    private final String orderSqlName = "fill_order_table.sql";
    private final SecureRandom random = new SecureRandom();
    private FillerCarTable fillerCarTable = new FillerCarTable();
    private FillerClientTable fillerClientTable = new FillerClientTable();

    public void fill(){
        try{
            FileWriter writer = new FileWriter(orderSqlName);
            StringBuilder bld = new StringBuilder();
            String str;
            List<Client> clientList = fillerClientTable.fill(clientSqlName);
            List<Car> carList = fillerCarTable.fill(carSqlName);
            Client[] clients = new Client[clientList.size()];
            Car[] cars = new Car[carList.size()];
            carList.toArray(cars);
            clientList.toArray(clients);

            for(int i = 0; i<20; i++){
                //TODO: add checks for intersecting dates?
                ZonedDateTime startDate = generateDate();
                ZonedDateTime endDate = generateDate(startDate);
                Client client = clients[random.nextInt(clients.length)];
                System.out.println(client);
                Car car = cars[random.nextInt(cars.length)];
                bld.append("insert into rentalOrder (id, rentStart, rentEnd, client_id, car_id) values (");
                str = "'" + UUID.randomUUID() + "' ,'"
                        + startDate + "', '"
                        + endDate + "', '"
                        + client.getId() + "', '"
                        + car.getId() + "');\n";
                bld.append(str);
            }
            writer.write(bld.toString());
            writer.close();

        }catch(IOException e){}
    }

    private ZonedDateTime generateDate(){
        int plusOrMinus = random.nextInt(2);
        if(plusOrMinus == 0)
            return ZonedDateTime.now().minusMonths(random.nextInt(1,12)).minusDays(random.nextInt(15));
        else return ZonedDateTime.now().plusMonths(random.nextInt(1,12)).plusDays(random.nextInt(16));
    }
    private ZonedDateTime generateDate(ZonedDateTime startDate){
        return startDate.plusDays(random.nextInt(1,15));
    }
}
