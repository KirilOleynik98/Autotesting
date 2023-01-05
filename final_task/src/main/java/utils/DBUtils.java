package utils;

import aquality.selenium.core.logging.Logger;
import connection.AbstractConnection;
import models.TestTable;
import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class DBUtils {

    public static List<TestTable> performSqlRequest(String request) {
        String[][] dbTable = new String[0][];

        List<TestTable> resultTable = null;
        try {
            PreparedStatement stmt = AbstractConnection.getConnection().prepareStatement(request, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery();
            rs.last();
            int rowNumb = rs.getRow();
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnS = resultSetMetaData.getColumnCount();
            rs.beforeFirst();
            dbTable = new String[rowNumb][columnS];
            int i = 0;
            while (rs.next() && i < rowNumb && rowNumb < dbTable.length + 1) {
                for (int j = 0; j < columnS; j++) {
                    dbTable[i][j] = rs.getString(j + 1);
                }
                i++;
            }
            resultTable = (List<TestTable>) Arrays.asList(dbTable);
        } catch (SQLException e) {
            Logger.getInstance().error(e.toString());
            throw new RuntimeException(e);
        } finally {
            AbstractConnection.closeConnection();
            return resultTable;
        }
    }
}

