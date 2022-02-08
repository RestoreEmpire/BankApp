package com.restoreempire.model;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;


import com.restoreempire.processing.data.generators.ClientIdGenerator;

public class Client extends Person<Client> {

    private long id;
    private String clientNumber;
    private final static String tableName = "client";

    public Client(){

    }

    public Client(long id,  String clientNumber, String surname, String firstName, String middlename, String birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setId(id);
        setClientNumber(clientNumber);
        
    }
    public Client(long id,  String clientNumber, String surname, String firstName, String middlename, LocalDate birthDate) {
        setAllInfo(firstName, surname, middlename, birthDate);
        setId(id);
        setClientNumber(clientNumber);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
    public HashMap<String,Object> serialized(){
        var map = new HashMap<String, Object>();
        if (getId() != 0)
            map.put("id", getId());
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
    public void read(long id) {
        try (ResultSet rs = select(tableName, id)) {
            setId(rs.getLong("id"));
            setClientNumber(rs.getString("client_number"));
            setSurname(rs.getString("surname"));
            setFirstName(rs.getString("first_name"));
            setMiddlename(rs.getString("middle_name"));
            setBirthDate(rs.getDate("birthdate").toLocalDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ArrayList<Client> getAll() {
        ArrayList<Client> list = new ArrayList<>();
        try(ResultSet rs = selectAll(tableName)) {
            while(rs.next()){
                Client client = new Client(
                    rs.getLong("id"),
                    rs.getString("client_number"),
                    rs.getString("surname"),
                    rs.getString("first_name"),
                    rs.getString("middle_name"),
                    rs.getDate("birthDate").toLocalDate() 
                    );
                list.add(client);
            }
            return list;
        } catch (Exception e) {
            return null;
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

