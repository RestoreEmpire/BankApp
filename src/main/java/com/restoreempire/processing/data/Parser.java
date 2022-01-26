package com.restoreempire.processing.data;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

import com.restoreempire.exceptions.CSVParserException;
import com.restoreempire.exceptions.RowNotFoundInTableException;
import com.restoreempire.logging.Logger;

public class Parser {
    private String path = "\\db\\";
    private String dbName;
    private String filePath;
    private ArrayList<ArrayList<String>> table = null;
    
    public Parser(String dbName){
        setDbName(dbName);
        setFilePath();
        updateTable();
    }

    public Parser(String path, String dbName) {
        setPath(path);
        setDbName(dbName);
        setFilePath();
        updateTable();
    }

    public String getPath() {
        return path;
    }
    public String getDbName() {
        return dbName;
    }
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
    public void setPath(String path) {
        this.path = path;
    }

    public void setFilePath() {
        String absPath = new File("").getAbsolutePath();
        StringBuilder sb = new StringBuilder(absPath);
        sb.append(path);
        sb.append(dbName);
        sb.append(".csv");
        filePath = sb.toString();
        updateTable();
    }


    /**
     * Возвращает номер строки, если находит полностью совпадающую строку.
     * Если такой строки нет, возвращает -1.
     * @param strings поля обх
     */
    public int inTable(String... row) {
        for (int i = 1; i < table.size(); i++) {
            var tableRow = table.get(i);
            if (Arrays.compare(tableRow.toArray(new String[]{}), row) == 0)
                return i + 1;
        }
        return -1;
    }

    public void removeRow(int rowIndex) {
        table.remove(rowIndex - 1);
        rewrite();
    }

    public ArrayList<String> getRow(int rowIndex){
        return table.get(rowIndex + 1);
    }

    public ArrayList<String> getRowById (String id){
        try{
            for(int i = 1; i < table.size(); i++){
                ArrayList<String> row = table.get(i);
                if (row.get(0).equals(id))
                    return row;
            }
            throw new RowNotFoundInTableException("Row with id: " + id + " was not found" );
        } catch(Exception e){
            e.printStackTrace();
            return null;
        
        }
    }

    public void changeRow(int rowIndex, String... row){
        var listedRow = new ArrayList<String>();
        Collections.addAll(listedRow, row);
        table.set(rowIndex - 1, listedRow);
        rewrite();
    }

    public void updateTable(){
        var table = new ArrayList<ArrayList<String>>();
        try (Scanner scanner = new Scanner(new File(filePath), StandardCharsets.UTF_8)) {
            if(!scanner.hasNextLine()) 
                throw new CSVParserException("Table is empty");
            var titles = new ArrayList<String>();
            Collections.addAll(titles, scanner.nextLine().split(",\\s"));
            table.add(titles);
            while(scanner.hasNextLine()){
                ArrayList<String> elements = new ArrayList<String>();
                Collections.addAll(elements, scanner.nextLine().split(",\\s"));
                if(elements.size() > titles.size()) throw new CSVParserException("Number of elements not equals for number of titles");
                table.add(elements);
            }
            this.table = table;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            Logger.write(e.getMessage(), Logger.Status.ERROR);
        }
    }

    public void rewrite(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.size(); i++) {
            var row = table.get(i);
            for (int j = 0; j < row.size() - 1; j++) {
                sb.append(row.get(j) + ", ");
            }
            sb.append(row.get(row.size() - 1));
            if (i != table.size() - 1)
                sb.append("\n");
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8))) {
            bw.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            Logger.write(e.getMessage(), Logger.Status.ERROR);
        }
    }
    public void writeToEnd(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length - 1; i++) {
            sb.append(strings[i] + ", ");
        }
        sb.append(strings[strings.length - 1]);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, StandardCharsets.UTF_8, true))) {
            bw.newLine();
            bw.append(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
            Logger.write(e.getMessage(), Logger.Status.ERROR);
        }
    }

}
