package org.drive.database.repository;

public interface MainRepository {

    long countTable(String table);

    boolean removeTableContent(String table);
}
