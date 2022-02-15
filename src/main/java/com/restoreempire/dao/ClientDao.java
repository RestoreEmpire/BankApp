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
    protected Map<String,Object> serialized(Client client){
        var map = new HashMap<String, Object>();
        if (client.getId() != 0)
            map.put("id", client.getId());
        map.put("client_number", client.getClientNumber());
        map.put("surname", client.getSurname());
        map.put("first_name", client.getFirstName());
        map.put("middle_name", client.getMiddlename());
        map.put("birthdate", client.getBirthDate());
        return map;

    }

    @Override
    public void create(Client client) {
        insert(tableName, serialized(client));
    }

    @Override
    public Client read(long id) {
        Client client = new Client();
        try (ResultSet rs = select(tableName, id)) {
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
    public void update(Client updated, Client updating) {
        dbUpdate(tableName, updated.getId(), serialized(updating));
    }

    @Override
    public void delete(Client client) {
        dbDelete(tableName, client.getId());
    }
}
