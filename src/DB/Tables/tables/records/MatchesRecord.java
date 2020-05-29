/*
 * This file is generated by jOOQ.
 */
package DB.Tables.tables.records;


import DB.Tables.tables.Matches;

import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record3;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MatchesRecord extends UpdatableRecordImpl<MatchesRecord> implements Record8<LocalDateTime, String, String, Integer, Integer, String, String, Integer> {

    private static final long serialVersionUID = 276311167;

    /**
     * Setter for <code>footballsystem_db.matches.date</code>.
     */
    public void setDate(LocalDateTime value) {
        set(0, value);
    }

    /**
     * Getter for <code>footballsystem_db.matches.date</code>.
     */
    public LocalDateTime getDate() {
        return (LocalDateTime) get(0);
    }

    /**
     * Setter for <code>footballsystem_db.matches.home_team</code>.
     */
    public void setHomeTeam(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>footballsystem_db.matches.home_team</code>.
     */
    public String getHomeTeam() {
        return (String) get(1);
    }

    /**
     * Setter for <code>footballsystem_db.matches.away_team</code>.
     */
    public void setAwayTeam(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>footballsystem_db.matches.away_team</code>.
     */
    public String getAwayTeam() {
        return (String) get(2);
    }

    /**
     * Setter for <code>footballsystem_db.matches.home_score</code>.
     */
    public void setHomeScore(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>footballsystem_db.matches.home_score</code>.
     */
    public Integer getHomeScore() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>footballsystem_db.matches.away_score</code>.
     */
    public void setAwayScore(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>footballsystem_db.matches.away_score</code>.
     */
    public Integer getAwayScore() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>footballsystem_db.matches.field</code>.
     */
    public void setField(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>footballsystem_db.matches.field</code>.
     */
    public String getField() {
        return (String) get(5);
    }

    /**
     * Setter for <code>footballsystem_db.matches.main_referee</code>.
     */
    public void setMainReferee(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>footballsystem_db.matches.main_referee</code>.
     */
    public String getMainReferee() {
        return (String) get(6);
    }

    /**
     * Setter for <code>footballsystem_db.matches.Duration_time</code>.
     */
    public void setDurationTime(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>footballsystem_db.matches.Duration_time</code>.
     */
    public Integer getDurationTime() {
        return (Integer) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record3<LocalDateTime, String, String> key() {
        return (Record3) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<LocalDateTime, String, String, Integer, Integer, String, String, Integer> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<LocalDateTime, String, String, Integer, Integer, String, String, Integer> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<LocalDateTime> field1() {
        return Matches.MATCHES.DATE;
    }

    @Override
    public Field<String> field2() {
        return Matches.MATCHES.HOME_TEAM;
    }

    @Override
    public Field<String> field3() {
        return Matches.MATCHES.AWAY_TEAM;
    }

    @Override
    public Field<Integer> field4() {
        return Matches.MATCHES.HOME_SCORE;
    }

    @Override
    public Field<Integer> field5() {
        return Matches.MATCHES.AWAY_SCORE;
    }

    @Override
    public Field<String> field6() {
        return Matches.MATCHES.FIELD;
    }

    @Override
    public Field<String> field7() {
        return Matches.MATCHES.MAIN_REFEREE;
    }

    @Override
    public Field<Integer> field8() {
        return Matches.MATCHES.DURATION_TIME;
    }

    @Override
    public LocalDateTime component1() {
        return getDate();
    }

    @Override
    public String component2() {
        return getHomeTeam();
    }

    @Override
    public String component3() {
        return getAwayTeam();
    }

    @Override
    public Integer component4() {
        return getHomeScore();
    }

    @Override
    public Integer component5() {
        return getAwayScore();
    }

    @Override
    public String component6() {
        return getField();
    }

    @Override
    public String component7() {
        return getMainReferee();
    }

    @Override
    public Integer component8() {
        return getDurationTime();
    }

    @Override
    public LocalDateTime value1() {
        return getDate();
    }

    @Override
    public String value2() {
        return getHomeTeam();
    }

    @Override
    public String value3() {
        return getAwayTeam();
    }

    @Override
    public Integer value4() {
        return getHomeScore();
    }

    @Override
    public Integer value5() {
        return getAwayScore();
    }

    @Override
    public String value6() {
        return getField();
    }

    @Override
    public String value7() {
        return getMainReferee();
    }

    @Override
    public Integer value8() {
        return getDurationTime();
    }

    @Override
    public MatchesRecord value1(LocalDateTime value) {
        setDate(value);
        return this;
    }

    @Override
    public MatchesRecord value2(String value) {
        setHomeTeam(value);
        return this;
    }

    @Override
    public MatchesRecord value3(String value) {
        setAwayTeam(value);
        return this;
    }

    @Override
    public MatchesRecord value4(Integer value) {
        setHomeScore(value);
        return this;
    }

    @Override
    public MatchesRecord value5(Integer value) {
        setAwayScore(value);
        return this;
    }

    @Override
    public MatchesRecord value6(String value) {
        setField(value);
        return this;
    }

    @Override
    public MatchesRecord value7(String value) {
        setMainReferee(value);
        return this;
    }

    @Override
    public MatchesRecord value8(Integer value) {
        setDurationTime(value);
        return this;
    }

    @Override
    public MatchesRecord values(LocalDateTime value1, String value2, String value3, Integer value4, Integer value5, String value6, String value7, Integer value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MatchesRecord
     */
    public MatchesRecord() {
        super(Matches.MATCHES);
    }

    /**
     * Create a detached, initialised MatchesRecord
     */
    public MatchesRecord(LocalDateTime date, String homeTeam, String awayTeam, Integer homeScore, Integer awayScore, String field, String mainReferee, Integer durationTime) {
        super(Matches.MATCHES);

        set(0, date);
        set(1, homeTeam);
        set(2, awayTeam);
        set(3, homeScore);
        set(4, awayScore);
        set(5, field);
        set(6, mainReferee);
        set(7, durationTime);
    }
}