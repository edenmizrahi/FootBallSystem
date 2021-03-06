package DataAccess;

import DB.Tables.tables.records.FanMatchesFollowRecord;
import DB.Tables.tables.records.TeamOwnerRequestsRecord;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static DB.Tables.Tables.FAN_MATCHES_FOLLOW;
import static DB.Tables.Tables.TEAM_OWNER_REQUESTS;

/** table name: fan_matches_follow **/

/** key - 4 values**/

public class DaoFanMatchesFollow implements Dao<String>  {
    private DateTimeFormatter out = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private DateTimeFormatter in= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    @Override
    public List<String> get(List<String> keys) throws ParseException{
        /** check connection to DB  **/
        DBHandler.conectToDB();
        DSLContext create = DBHandler.getDSLConnect();
        // DateTimeFormatter out = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // DateTimeFormatter in= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String key0= keys.get(0);
        String key1Date=out.format(in.parse(keys.get(1)));
        LocalDateTime dateTime = LocalDateTime.parse(key1Date, out);
        String key2=keys.get(2);
        String key3=keys.get(3);

        List<String> result=new LinkedList<>();
        /** select retrieval row from table  **/
        FanMatchesFollowRecord fanMatchesFollowRecord=create.selectFrom(FAN_MATCHES_FOLLOW)
                .where(FAN_MATCHES_FOLLOW.USER_NAME.eq(key0))
                .and(FAN_MATCHES_FOLLOW.MATCH_DATE.ge(dateTime)
                .and(FAN_MATCHES_FOLLOW.HOME_TEAM.eq(key2))
                .and(FAN_MATCHES_FOLLOW.AWAY_TEAM.eq(key3)))
                .fetchOne();
        /** key not found in table  **/
        if (fanMatchesFollowRecord == null || fanMatchesFollowRecord.size()==0){
            //return null;
            throw new ParseException("key noy found in table",0);
        }

        for (int i = 0; i <fanMatchesFollowRecord.size() ; i++) {
            if(fanMatchesFollowRecord.get(i) instanceof LocalDateTime){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                result.add(((LocalDateTime) fanMatchesFollowRecord.get(i)).format(formatter));
                //dd-MM-yyyy HH:mm:ss
            }
            else{
                result.add(fanMatchesFollowRecord.get(i).toString());
            }
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
            Result<Record> result=create.select().from(FAN_MATCHES_FOLLOW).fetch();

            /** iinitialize List<List<String>> **/
            List<List<String>> ans=new ArrayList<>(result.size());
            for(int i=0; i<result.size(); i++){
                List<String> temp = new LinkedList<>();
                ans.add(temp);
            }
            /** insert coll values to ans  **/
            int numOfCols=result.fields().length; //4!
            for(int i=0;i< numOfCols;i++){
                List <?> currCol = result.getValues(i);
                for (int j = 0; j <result.size() ; j++) {
                    if(currCol.get(j) instanceof LocalDateTime){
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        ans.get(j).add(((LocalDateTime) currCol.get(j)).format(formatter));
                    }
                    else{
                        ans.get(j).add(currCol.get(j).toString());
                    }
                }
            }
            return ans;

        }
        /** filter **/
        if(collName.equals("match_date")){
            DateTimeFormatter out = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            DateTimeFormatter in= DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            filter=out.format(in.parse(filter));
            //outSDF.format(inSDF.parse(startDate))
        }
        ResultSet rs=null;
        Result<Record> result=null;
        int numOfCols=0;
        String sql="SELECT * FROM fan_matches_follow WHERE "+collName+"= '" + filter + "'"; //!!!!!!!!!!!!!!!!!!!!!!!!!
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
                List <?> currCol = result.getValues(i);
                for (int j = 0; j <result.size() ; j++) {
                    if( currCol.get(j) instanceof Timestamp ){
                        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        String formattedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(currCol.get(j));
                        ans.get(j).add((formattedDate));
                    }
                    else{
                        ans.get(j).add(currCol.get(j).toString());
                    }
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
        String key1Date=out.format(in.parse(strings.get(1)));
        LocalDateTime dateTime = LocalDateTime.parse(key1Date, out);
        String key2=strings.get(2);
        String key3=strings.get(3);

        Result<Record> isExist = create.select().from(FAN_MATCHES_FOLLOW)
                .where(FAN_MATCHES_FOLLOW.USER_NAME.eq(key0))
                        .and(FAN_MATCHES_FOLLOW. MATCH_DATE.ge(dateTime)
                        .and(FAN_MATCHES_FOLLOW.HOME_TEAM.eq(key2))
                        .and(FAN_MATCHES_FOLLOW.AWAY_TEAM.eq(key3)))
                .fetch();

        if(isExist.size()== 0){ // key not exist
            /**add new row to DB **/
            try{
                /** convert to date format of DB **/
                String matchDate=out.format(in.parse(strings.get(1)));
                LocalDateTime local=LocalDateTime.parse(matchDate,out);
                create.insertInto(FAN_MATCHES_FOLLOW,
                        FAN_MATCHES_FOLLOW.USER_NAME
                        ,FAN_MATCHES_FOLLOW.MATCH_DATE
                        ,FAN_MATCHES_FOLLOW.HOME_TEAM
                        ,FAN_MATCHES_FOLLOW.AWAY_TEAM)
                        .values(strings.get(0),LocalDateTime.parse(matchDate,out),
                                strings.get(2),strings.get(3))
                        .execute();

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

        String key0= keys.get(0);
        String key1Date=out.format(in.parse(keys.get(1)));
        LocalDateTime dateTime = LocalDateTime.parse(key1Date, out);
        String key2=keys.get(2);
        String key3=keys.get(3);

        /** select retrieval row from table  **/
        FanMatchesFollowRecord fanMatchesFollowRecord=create.selectFrom(FAN_MATCHES_FOLLOW)
                .where(FAN_MATCHES_FOLLOW.USER_NAME.eq(key0))
                .and(FAN_MATCHES_FOLLOW.MATCH_DATE.ge(dateTime))
                .and(FAN_MATCHES_FOLLOW.HOME_TEAM.eq(key2))
                .and(FAN_MATCHES_FOLLOW.AWAY_TEAM.eq(key3))
                .fetchOne();

        /** check if key exist in DB  **/
        if(fanMatchesFollowRecord != null) {
            /** convert to date format of DB **/
            String matchDate=out.format(in.parse(keys.get(1)));
            /**update row in  DB **/
            fanMatchesFollowRecord.setUserName(key0);;
            fanMatchesFollowRecord.setMatchDate(LocalDateTime.parse(matchDate,out));
            fanMatchesFollowRecord.setHomeTeam(key2);
            fanMatchesFollowRecord.setAwayTeam(key3);

            try{
                fanMatchesFollowRecord.store();

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
        String key0= strings.get(0);
        String key1Date=out.format(in.parse(strings.get(1)));
        LocalDateTime dateTime = LocalDateTime.parse(key1Date, out);
        String key2=strings.get(2);
        String key3=strings.get(3);

        create.delete(FAN_MATCHES_FOLLOW)
                .where(FAN_MATCHES_FOLLOW.USER_NAME.eq(key0))
                .and(FAN_MATCHES_FOLLOW.MATCH_DATE.ge(dateTime)
                .and(FAN_MATCHES_FOLLOW.HOME_TEAM.eq(key2))
                .and(FAN_MATCHES_FOLLOW.AWAY_TEAM.eq(key3)))
                .execute();

    }
}
