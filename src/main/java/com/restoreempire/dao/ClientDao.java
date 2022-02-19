package com.restoreempire.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.restoreempire.model.Client;

/** DAO of Client model */
public class ClientDao extends BaseDao<Client> {

    /** Table name in database table */
    private final static String tableName = "client";

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public Map<String, Object> serialized(Client client){
        var map = new HashMap<String, Object>();
        var keys = getKeys();
        if (client.getId() != 0)
            map.put(keys[0], client.getId());
        map.put(keys[1], client.getClientNumber());
        map.put(keys[2], client.getSurname());
        map.put(keys[3], client.getFirstName());
        map.put(keys[4], client.getMiddlename());
        map.put(keys[5], client.getBirthDate());
        return map;

    }

    public String[] getKeys(){
        return new String[]{"id", "client_number", "surname", "first_name", "middle_name", "birthdate"};
    } 

    @Override
    public void create(Client client) {
        insert(client);
    }

    @Override
    public Client read(long id) {
        Client client = new Client();
        try (ResultSet rs = select(id)) {
            client.setId(rs.getLong("id"));
            client.setClientNumber(rs.getString("client_number"));
            client.setSurname(rs.getString("surname"));
            client.setFirstName(rs.getString("first_name"));
            client.setMiddlename(rs.getString("middle_name"));
            client.setBirthDate(rs.getDate("birthdate").toLocalDate());
            return client;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Client> getAll() {
        List<Client> list = new ArrayList<>();
        try(ResultSet rs = selectAll()) {
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
    public void update(long id, Client updating) {
        dbUpdate(id, updating);
    }

    @Override
    public void delete(Client client) {
        dbDelete(client.getId());
    }
}
