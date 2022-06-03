package com.vilensky.carrental.database_fillers;

import com.vilensky.carrental.entities.Car;

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FillerCarTable {
    public final SecureRandom random = new SecureRandom();
    public final String[] brandArr = {"Mazda","Citroen","Kia","BMW","Mercedes","Seat","Hyundai","Honda","Mitsubishi","Chevrolet"};
    public final String[] modelArr = {"1", "3","6","Fury","Hurricane","Tuareg","a45","208","CSVV","555","Seledka","sh45","klmN"};
    public final double[] motorVolumeArr ={1.0,1.2,1.3,1.4,1.6,1.8,2.0,2.4,3.0};
    public final String[] colorArr={"blue","white","black","pink","grey","yellow","metallic grey","red","green"};

    public List<Car> fill(String fileName){
        List<Car> carList= new ArrayList<Car>();
        try {
            FileWriter writer = new FileWriter(fileName);
            StringBuilder bld = new StringBuilder();
            String str;
            for(int i = 0; i<50;i++){
                Car car = createCar();
                car.setId(UUID.randomUUID());
                carList.add(car);
                str=("insert into car(id, brand, model, motorVolume, carNumber,price,color) values("+"'"+
                        car.getId()+"' ,'"
                            +car.getBrand()+"' ,'"
                            +car.getModel()+"',"
                            +car.getMotorVolume()+" ,"
                            +car.getCarNumber()+" ,"
                            +car.getPrice()+" ,'"
                            +car.getColor()+"');\n");
                bld.append(str);
            }
            writer.write(bld.toString());
            writer.close();
        }catch(IOException e){
            System.out.println("Error writing to file");
        }
        return carList;
    }

    private Car createCar(){
        return Car.builder()
                .carNumber(random.nextInt(1000000, 10000000))
                .model(modelArr[random.nextInt(modelArr.length)])
                .brand(brandArr[random.nextInt(brandArr.length)])
                .motorVolume(motorVolumeArr[random.nextInt(motorVolumeArr.length)])
                .color(colorArr[random.nextInt(colorArr.length)])
                .price(random.nextDouble(200.0, 600.0))
                .build();
    }
}
