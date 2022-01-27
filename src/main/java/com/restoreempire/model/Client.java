package com.restoreempire.model;

import java.sql.ResultSet;
import java.util.HashMap;


import com.restoreempire.processing.data.generators.ClientIdGenerator;

public class Client extends Person<Client> {

    private int id;
    private String clientNumber;
    private final String tableName = "client";

    public Client(){

    }
    
    public Client(String surname, String firstName, String middlename, String birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setRandClientNumber();
    }

    public Client(int id, String surname, String firstName, String middlename, String birthDate, String clientNumber) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setId(id);
        setClientNumber(clientNumber);
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClientNumber(String clientNumber){
        this.clientNumber = clientNumber;
    }
    public void setRandClientNumber() {
        clientNumber = new ClientIdGenerator().generate();
    }

    public String getClientNumber() {
        return clientNumber;
    }

    @Override
    protected HashMap<String,Object> serialized(){
        var map = new HashMap<String, Object>();
        if (getId() != 0)
            map.put("id", String.valueOf(getId()));
        map.put("client_number", getClientNumber());
        map.put("surname", getSurname());
        map.put("first_name", getFirstName());
        map.put("middle_name", getMiddlename());
        map.put("birthdate", getBirthDate());
        return map;

    }

    @Override
    public void create() {
        insert(tableName, serialized());
    }

    @Override
    public void read(int id) {
        try (ResultSet rs = select(tableName, id)) {
            setId(rs.getInt("id"));
            setClientNumber(rs.getString("client_number"));
            setSurname(rs.getString("surname"));
            setFirstName(rs.getString("first_name"));
            setMiddlename(rs.getString("middle_name"));
            setBirthDate(rs.getDate("birthdate").toLocalDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Client client) {
        dbUpdate(tableName, getId(),client.serialized());
    }

    @Override
    public void delete() {
        dbDelete(tableName, getId());
    }
}

