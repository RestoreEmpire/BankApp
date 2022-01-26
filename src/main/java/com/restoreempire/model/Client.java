package com.restoreempire.model;

import java.util.ArrayList;

import com.restoreempire.exceptions.RowNotFoundInTableException;
import com.restoreempire.logging.Logger;
import com.restoreempire.logging.Logger.Status;
import com.restoreempire.processing.data.Parser;
import com.restoreempire.processing.data.generators.ClientIdGenerator;

public class Client extends Person implements Model<Client> {

    private String id;
    private Parser parser = new Parser("Client");

    public Client(){

    }
    
    public Client(String surname, String firstName, String middlename, String birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setRandId();
    }

    public Client(String id, String surname, String firstName, String middlename, String birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setId(id);
        
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
                getId(), 
                getSurname(), 
                getFirstName(), 
                getMiddlename(), 
                getBirthDate()
                );
            if (result < 0) throw new RowNotFoundInTableException("Row not found");
            return result;
        } catch (RuntimeException e){
            e.getMessage();
            return -1;
        }
    }


}
