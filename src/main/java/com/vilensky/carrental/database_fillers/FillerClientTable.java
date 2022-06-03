package com.vilensky.carrental.database_fillers;

import com.vilensky.carrental.entities.Client;

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FillerClientTable {
    public final SecureRandom random = new SecureRandom();
    public final String[] licenceArr={"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
    public final String[] phoneArr={"0","1","2","3","4","5","6","7","8","9"};
    public final String[] nameArr={"Ivan", "Moti","John","Matthew","Arnold","Alex","Anastasia","Rotem","Yael","Boris"};
    public final String[] surnameArr={"Ivanov","Cohen","Johnson","Ben Ari","Yom Tov","Petrov","Chu","Spiderman"};
    public List<Client> fill(String fileName){
        List<Client> clientList= new ArrayList<Client>();
        try{
            FileWriter writer =new FileWriter(fileName);
            StringBuilder bld = new StringBuilder();
            String str;
            for(int i=0;i<30;i++){
                Client client = createClient();
                client.setId(UUID.randomUUID());
                clientList.add(client);
                bld.append("insert into client (id, licenceNumber, name, phone, age, experience) values(");

                str=("'"+client.getId()+"' ,'"
                            +client.getLicenceNumber()+"' ,'"
                            +client.getName()+"' ,'"
                            +client.getPhone()+"' ,"
                            +client.getAge()+" ,"
                            +client.getExperience()+" );\n");
                bld.append(str);
            }
            writer.write(bld.toString());
            writer.close();
        }catch(IOException e){
            System.out.println("Error writing client table");
        }
        return clientList;
    }

    //generates sequence of 8 chars, for short lists it must be unique
    //chance of collision |licenceArr|^8
    private String generate(String[] arr){
        StringBuilder str = new StringBuilder();
        for(int i=0;i<8;i++){
            str.append(arr[random.nextInt(arr.length)]);
        }
        return str.toString();
    }

    private Client createClient(){
        return Client.builder()
                .licenceNumber(generate(licenceArr))
                .name(nameArr[random.nextInt(nameArr.length)]+" "+surnameArr[random.nextInt(surnameArr.length)])
                .experience(random.nextInt(40))
                .age(random.nextInt(18,80))
                .phone(generate(phoneArr))
                .build();
    }
}
