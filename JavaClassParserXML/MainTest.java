package JavaClassParserXML;

import org.junit.Before;
import org.junit.Test;
import java.sql.*;
import java.util.HashSet;
import static org.junit.Assert.*;


public class MainTest {
    private Main main ;

    @Before
    public void before(){
        main = new Main();
    }


    @Test
    public void testClearTables(){
        HashSet hs = new HashSet<>();
        main.readingFileAndFillingTables(hs);
        main.clearTables();

        int lineCountClient = 0 ;
        int lineCountTransaction = 0 ;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("..........", ".......", ".........");
            Statement statement = connection.createStatement();

            ResultSet resultSet1 = statement.executeQuery("SELECT inn FROM Client");
            while(resultSet1.next()) {
                lineCountClient = lineCountClient + 1 ;
            }

            ResultSet resultSet2 = statement.executeQuery("SELECT id FROM Transaction");
            while(resultSet2.next()) {
                lineCountTransaction = lineCountTransaction + 1 ;
            }


            resultSet2.close();
            resultSet1.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int expectedNumberOfLines = 0 ;
        assertEquals( expectedNumberOfLines, lineCountClient);
        assertEquals( expectedNumberOfLines, lineCountTransaction);
    }




    @Test
    public void testReadingFileAndFillingTables(){
        HashSet hs = new HashSet<>();
        main.clearTables();
        main.readingFileAndFillingTables(hs);


        int lineCountClient = 0 ;
        int lineCountTransaction = 0 ;

        try{
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("......................", "....", "......");
            Statement statement = connection.createStatement();

            ResultSet resultSet1 = statement.executeQuery("SELECT inn FROM Client");
            while(resultSet1.next()) {
                lineCountClient = lineCountClient + 1 ;
            }

            ResultSet resultSet2 = statement.executeQuery("SELECT id FROM Transaction");
            while(resultSet2.next()) {
                lineCountTransaction = lineCountTransaction + 1 ;
            }


            resultSet2.close();
            resultSet1.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        int expectedNumberOfLines = 0 ;
        assertTrue ( lineCountClient != expectedNumberOfLines);
        assertTrue ( lineCountTransaction != expectedNumberOfLines);
    }




}