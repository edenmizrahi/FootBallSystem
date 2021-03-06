package DataAccess;

import DB.Tables.tables.records.RefereeSeasonLeagueRecord;
import DB.Tables.tables.records.TeamOwnerRequestsRecord;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static DB.Tables.Tables.REFEREE_SEASON_LEAGUE;
import static DB.Tables.Tables.TEAM_OWNER_REQUESTS;

/**
 * table name : referee_season_league
 * 3 keys!  - cant update
 * **/
public class DaoLeagueSeasonReferees implements Dao<String> {
    @Override
    public List<String> get(List<String> keys) throws ParseException{
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();
        String key1= keys.get(0);
        String key2= keys.get(1);
        String key3=keys.get(2);

        List<String> result=new LinkedList<>();
        /** select retrieval row from table  **/
        RefereeSeasonLeagueRecord refereeSeasonLeagueRecord =create.selectFrom(REFEREE_SEASON_LEAGUE)
                .where(REFEREE_SEASON_LEAGUE.REFEREE.eq(key1))
                .and(REFEREE_SEASON_LEAGUE.SEASON_YEAR.eq(key2))
                .and(REFEREE_SEASON_LEAGUE.LEAGUE_NAME.eq(key3))
                .fetchOne();
        /** key noy found in table  **/
        if (refereeSeasonLeagueRecord == null || refereeSeasonLeagueRecord.size()==0){
            //return null;
            throw new ParseException("key noy found in table",0);
        }

        for (int i = 0; i <refereeSeasonLeagueRecord.size() ; i++) {
            result.add(refereeSeasonLeagueRecord.get(i).toString());
        }
        return result;
    }

    @Override
    public List<List<String>> getAll(String collName, String filter) {
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();

        if( collName==null && filter==null){
            /** return all rows in table **/
            Result<Record> result=create.select().from(REFEREE_SEASON_LEAGUE).fetch();

            /** iinitialize List<List<String>> **/
            List<List<String>> ans=new ArrayList<>(result.size());
            for(int i=0; i<result.size(); i++){
                List<String> temp = new LinkedList<>();
                ans.add(temp);
            }
            /** insert coll values to ans  **/
            int numOfCols=result.fields().length; //3!
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
        String sql="SELECT * FROM referee_season_league WHERE "+collName+"= '" + filter + "'"; //!!!!!!!!!!!!!!!!!!!!!!!!!
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
    public void save(List<String> strings) throws SQLException {
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);
        /** check if key not already exist in DB  **/
        String key0= strings.get(0);
        String key1= strings.get(1);
        String key2= strings.get(2);
        Result<Record> isExist = create.select().from(REFEREE_SEASON_LEAGUE)
                .where(REFEREE_SEASON_LEAGUE.REFEREE.eq(key0))
                .and(REFEREE_SEASON_LEAGUE.SEASON_YEAR.eq(key1)
                .and(REFEREE_SEASON_LEAGUE.LEAGUE_NAME.eq(key2)))
                .fetch();

        if(isExist.size()== 0){ // key not exist
            /**add new row to DB **/
            try{
                create.insertInto(REFEREE_SEASON_LEAGUE,
                        REFEREE_SEASON_LEAGUE.REFEREE,REFEREE_SEASON_LEAGUE.SEASON_YEAR,REFEREE_SEASON_LEAGUE.LEAGUE_NAME)
                        .values(strings.get(0), strings.get(1),strings.get(2)).execute();

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
                /** cant set keys ! **/
    }

    @Override
    public void delete(List<String> strings) {
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();//DSL.using(connection, SQLDialect.MARIADB);
        String key0= strings.get(0);
        String key1= strings.get(1);
        String key2= strings.get(2);

        create.delete(REFEREE_SEASON_LEAGUE)
                .where(REFEREE_SEASON_LEAGUE.REFEREE.eq(key0))
                .and(REFEREE_SEASON_LEAGUE.SEASON_YEAR.eq(key1)
                .and(REFEREE_SEASON_LEAGUE.LEAGUE_NAME.eq(key2)))
                .execute();

    }
}
