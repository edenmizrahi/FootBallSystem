/*
 * This file is generated by jOOQ.
 */
package DB.Tables.tables.records;


import DB.Tables.tables.FanMatchesFollow;

import java.time.LocalDate;

import org.jooq.Field;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FanMatchesFollowRecord extends UpdatableRecordImpl<FanMatchesFollowRecord> implements Record4<String, LocalDate, String, String> {

    private static final long serialVersionUID = -230557171;

    /**
     * Setter for <code>footballsystem_db.fan_matches_follow.user_name</code>.
     */
    public void setUserName(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>footballsystem_db.fan_matches_follow.user_name</code>.
     */
    public String getUserName() {
        return (String) get(0);
    }

    /**
     * Setter for <code>footballsystem_db.fan_matches_follow.match_date</code>.
     */
    public void setMatchDate(LocalDate value) {
        set(1, value);
    }

    /**
     * Getter for <code>footballsystem_db.fan_matches_follow.match_date</code>.
     */
    public LocalDate getMatchDate() {
        return (LocalDate) get(1);
    }

    /**
     * Setter for <code>footballsystem_db.fan_matches_follow.home_team</code>.
     */
    public void setHomeTeam(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>footballsystem_db.fan_matches_follow.home_team</code>.
     */
    public String getHomeTeam() {
        return (String) get(2);
    }

    /**
     * Setter for <code>footballsystem_db.fan_matches_follow.away_team</code>.
     */
    public void setAwayTeam(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>footballsystem_db.fan_matches_follow.away_team</code>.
     */
    public String getAwayTeam() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record4<String, LocalDate, String, String> key() {
        return (Record4) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<String, LocalDate, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<String, LocalDate, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return FanMatchesFollow.FAN_MATCHES_FOLLOW.USER_NAME;
    }

    @Override
    public Field<LocalDate> field2() {
        return FanMatchesFollow.FAN_MATCHES_FOLLOW.MATCH_DATE;
    }

    @Override
    public Field<String> field3() {
        return FanMatchesFollow.FAN_MATCHES_FOLLOW.HOME_TEAM;
    }

    @Override
    public Field<String> field4() {
        return FanMatchesFollow.FAN_MATCHES_FOLLOW.AWAY_TEAM;
    }

    @Override
    public String component1() {
        return getUserName();
    }

    @Override
    public LocalDate component2() {
        return getMatchDate();
    }

    @Override
    public String component3() {
        return getHomeTeam();
    }

    @Override
    public String component4() {
        return getAwayTeam();
    }

    @Override
    public String value1() {
        return getUserName();
    }

    @Override
    public LocalDate value2() {
        return getMatchDate();
    }

    @Override
    public String value3() {
        return getHomeTeam();
    }

    @Override
    public String value4() {
        return getAwayTeam();
    }

    @Override
    public FanMatchesFollowRecord value1(String value) {
        setUserName(value);
        return this;
    }

    @Override
    public FanMatchesFollowRecord value2(LocalDate value) {
        setMatchDate(value);
        return this;
    }

    @Override
    public FanMatchesFollowRecord value3(String value) {
        setHomeTeam(value);
        return this;
    }

    @Override
    public FanMatchesFollowRecord value4(String value) {
        setAwayTeam(value);
        return this;
    }

    @Override
    public FanMatchesFollowRecord values(String value1, LocalDate value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FanMatchesFollowRecord
     */
    public FanMatchesFollowRecord() {
        super(FanMatchesFollow.FAN_MATCHES_FOLLOW);
    }

    /**
     * Create a detached, initialised FanMatchesFollowRecord
     */
    public FanMatchesFollowRecord(String userName, LocalDate matchDate, String homeTeam, String awayTeam) {
        super(FanMatchesFollow.FAN_MATCHES_FOLLOW);

        set(0, userName);
        set(1, matchDate);
        set(2, homeTeam);
        set(3, awayTeam);
    }
}
