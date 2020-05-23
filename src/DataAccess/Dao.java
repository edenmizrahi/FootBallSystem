package DataAccess;
import java.sql.SQLException;
import java.util.*;

public interface Dao <T> {

    List<T> get(List<T> keys);

    List<List<T>> getAll(T collName, T filter);

    /** getAll (null ,null) -> get all rows in table
     *
     *  getAll (T collName ,null) -> get all rows in colName
     * **/

    void save(List<T> strings) throws SQLException;

    void update(T t, List<T> string);

    void delete(List<T> strings);
}

