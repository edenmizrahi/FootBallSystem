/*
 * This file is generated by jOOQ.
 */
package DB.Tables.tables.records;


import DB.Tables.tables.Coachs;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CoachsRecord extends UpdatableRecordImpl<CoachsRecord> implements Record3<String, String, String> {

    private static final long serialVersionUID = 1520006575;

    /**
     * Setter for <code>footballsystem_db.coachs.userName</code>.
     */
    public void setUsername(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>footballsystem_db.coachs.userName</code>.
     */
    public String getUsername() {
        return (String) get(0);
    }

    /**
     * Setter for <code>footballsystem_db.coachs.coachTeam</code>.
     */
    public void setCoachteam(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>footballsystem_db.coachs.coachTeam</code>.
     */
    public String getCoachteam() {
        return (String) get(1);
    }

    /**
     * Setter for <code>footballsystem_db.coachs.roleAtTeam</code>.
     */
    public void setRoleatteam(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>footballsystem_db.coachs.roleAtTeam</code>.
     */
    public String getRoleatteam() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<String, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Coachs.COACHS.USERNAME;
    }

    @Override
    public Field<String> field2() {
        return Coachs.COACHS.COACHTEAM;
    }

    @Override
    public Field<String> field3() {
        return Coachs.COACHS.ROLEATTEAM;
    }

    @Override
    public String component1() {
        return getUsername();
    }

    @Override
    public String component2() {
        return getCoachteam();
    }

    @Override
    public String component3() {
        return getRoleatteam();
    }

    @Override
    public String value1() {
        return getUsername();
    }

    @Override
    public String value2() {
        return getCoachteam();
    }

    @Override
    public String value3() {
        return getRoleatteam();
    }

    @Override
    public CoachsRecord value1(String value) {
        setUsername(value);
        return this;
    }

    @Override
    public CoachsRecord value2(String value) {
        setCoachteam(value);
        return this;
    }

    @Override
    public CoachsRecord value3(String value) {
        setRoleatteam(value);
        return this;
    }

    @Override
    public CoachsRecord values(String value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CoachsRecord
     */
    public CoachsRecord() {
        super(Coachs.COACHS);
    }

    /**
     * Create a detached, initialised CoachsRecord
     */
    public CoachsRecord(String username, String coachteam, String roleatteam) {
        super(Coachs.COACHS);

        set(0, username);
        set(1, coachteam);
        set(2, roleatteam);
    }
}
