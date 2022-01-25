package model;

import java.util.ArrayList;

import logging.Logger;
import logging.Logger.Status;
import processing.data.Parser;
import processing.data.generators.ClientIdGenerator;

public class Client extends Person implements Model<Client> {

    private String id;
    private Parser parser = new Parser("Client");

    public Client(){

    }

    public Client(String id, String surname, String firstName, String middlename, String birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setId(id);
        
    }
    public Client(String firstName, String surname, String middlename, String birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setRandId();
    }

    public void setId(String id){
        this.id = id;
    }
    public void setRandId() {
        id = new ClientIdGenerator().generate();
    }

    public String getId() {
        return id;
    }

    @Override
    public void create() {
        parser.writeToEnd(
            getId(), 
            getSurname(), 
            getFirstName(), 
            getMiddlename(), 
            getBirthDate()
        );
        Logger.write("New client was created with name " + getFullName(), Status.OK); //change location
    }

    @Override
    public void delete() {
        parser.removeRow(search());
        
    }

    @Override
    public void read(String id) {
        ArrayList<String> row = parser.getRowById(id);
        setId(row.get(0));
        setSurname(row.get(1));
        setFirstName(row.get(2));
        setMiddlename(row.get(3));
        setBirthDate(row.get(4));
    }

    @Override
    public void update(Client client) {
        parser.changeRow(search(), 
        client.getId(), 
        client.getSurname(), 
        client.getFirstName(), 
        client.getMiddlename(), 
        client.getBirthDate()
        );
        
    }

    @Override
    public int search() {
        try { // TODO: засунуть в валидацию
            int result = parser.inTable(            
                String.valueOf(id), 
                getSurname(), 
                getFirstName(), 
                getMiddlename(), 
                getBirthDate()
                );
            if (result < 0) throw new Exception("Row not found");
            return result;
        } catch (Exception e){
            return 0;
        }
    }


}

