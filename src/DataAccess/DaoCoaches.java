package DataAccess;

import DB.Tables.tables.records.CoachsRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static DB.Tables.Tables.COACHS;
import static DB.Tables.Tables.FANS;

public class DaoCoaches implements Dao<String> {
    @Override
    public List<String> get(List<String> keys) throws ParseException {
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);

        String Key= keys.get(0);
        List<String> result=new LinkedList<>();
        /** select retrieval row from table  **/
        CoachsRecord coachsRecord=create.selectFrom(COACHS)
                .where(COACHS.USERNAME.eq(Key)).fetchOne();
        /** key noy found in table  **/
        if (coachsRecord == null || coachsRecord.size()==0){
            //return null;
            throw new ParseException("key noy found in table",0);
        }

        for (int i = 0; i <coachsRecord.size() ; i++) {
            if(coachsRecord.get(i) instanceof String){
                result.add(coachsRecord.get(i).toString());
                //List<String> kk= result.add(coachsRecord.valuesRow());
            }
        }
//        result.add(coachsRecord.getUsername());
//        result.add(coachsRecord.getCoachteam());
//        result.add(coachsRecord.getRoleatteam());
        return result;

    }

    @Override
    public List<List<String>> getAll(String collName, String filter) {
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);

        if( collName==null && filter==null){
            /** return all rows in table **/
            //String sql="SELECT * FROM coachs";

            Result<Record> result=create.select().from(COACHS).fetch();

            /** iinitialize List<List<String>> **/
            List<List<String>> ans=new ArrayList<>(result.size());
            for(int i=0; i<result.size(); i++){
                List<String> temp = new LinkedList<>();
                ans.add(temp);
            }
            /** insert coll values to ans  **/
            int numOfCols=result.fields().length; //3 !
            for(int i=0;i< numOfCols;i++){
                List <String> currCol = (List<String>)result.getValues(i);
                for (int j = 0; j <result.size() ; j++) {
                    ans.get(j).add(currCol.get(j));

                }
            }
            return ans;

        }
        /** filter **/
        ResultSet rs=null;
        Result<Record> result=null;
        int numOfCols=0;
        String sql="SELECT * FROM coachs WHERE "+collName+"= '" + filter + "'"; //!!!!!!!!!!!!!!!!!!!!!!!!!
        List<List<String>> ans=null;
        try {
            rs=DBHandler.getConnection().createStatement().executeQuery(sql);
            result=DBHandler.getDSLConnect().fetch(rs);
            ResultSetMetaData rsmd=rs.getMetaData();
            numOfCols=rsmd.getColumnCount();
            /** iinitialize List<List<String>> **/
            ans=new ArrayList<>(result.size());
            for(int i=0; i<result.size(); i++){
                List<String> temp = new LinkedList<>();
                ans.add(temp);
            }
            /** insert coll values to ans  **/
            for(int i=0;i< numOfCols;i++){
                List <String> currCol = (List<String>)result.getValues(i);
                for (int j = 0; j <result.size() ; j++) {
                    ans.get(j).add(currCol.get(j));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ans;
    }

    @Override
    public void save(List<String> strings) throws SQLException { //add new row
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);
        /** check if key not already exist in DB  **/
        String key= strings.get(0);
        Result<Record> isExist = create.select().from(COACHS)
                .where(COACHS.USERNAME.eq(key)).fetch();

        if(isExist.size()== 0){ // key not exist
            /**add new row to DB **/
            try{
                create.insertInto(COACHS,
                        COACHS.USERNAME, COACHS.COACHTEAM, COACHS.ROLEATTEAM)
                        .values(strings.get(0), strings.get(1), strings.get(2)).execute();

                System.out.println("new row add"); // dell ! !

            }catch (DataAccessException e){
                e.printStackTrace();
                throw new SQLException("added failed, foreign key not exist.");

            }
        }
        else {
            throw new SQLException("added failed, key already exist.");
        }
    }

    @Override
    public void update(List<String> keys, List<String> string) {
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);

        String key= keys.get(0);
        /** select retrieval row from table  **/
        CoachsRecord coachsRecord=create.selectFrom(COACHS)
                .where(COACHS.USERNAME.eq(key)).fetchOne();

        /** check if key exist in DB  **/
        if(coachsRecord != null) {

            /**update row in  DB **/
            coachsRecord.setCoachteam(string.get(0));
            coachsRecord.setRoleatteam(string.get(1));
            try{
                coachsRecord.store();

                /** foreign key not exist  **/
            }catch (DataAccessException e){
                //e.printStackTrace();
                System.out.println("foreign key not exist. need to change it");
            }
        }
    }

    @Override
    public void delete(List<String> strings) {
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);
        String key= strings.get(0);

        create.delete(COACHS)
                .where(COACHS.USERNAME.eq(key))
                .execute();

    }
}
