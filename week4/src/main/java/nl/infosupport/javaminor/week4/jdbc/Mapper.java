package nl.infosupport.javaminor.week4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface Mapper<T> {

  T map(ResultSet rs) throws SQLException ;

}
