/*
 * This file is generated by jOOQ.
 */
package DB.Tables.tables;


import DB.Tables.FootballsystemDb;
import DB.Tables.Keys;
import DB.Tables.tables.records.RefereesMatchesRecord;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RefereesMatches extends TableImpl<RefereesMatchesRecord> {

    private static final long serialVersionUID = 1250375451;

    /**
     * The reference instance of <code>footballsystem_db.referees_matches</code>
     */
    public static final RefereesMatches REFEREES_MATCHES = new RefereesMatches();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<RefereesMatchesRecord> getRecordType() {
        return RefereesMatchesRecord.class;
    }

    /**
     * The column <code>footballsystem_db.referees_matches.matche_date</code>.
     */
    public final TableField<RefereesMatchesRecord, LocalDateTime> MATCHE_DATE = createField(DSL.name("matche_date"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * The column <code>footballsystem_db.referees_matches.home_team</code>.
     */
    public final TableField<RefereesMatchesRecord, String> HOME_TEAM = createField(DSL.name("home_team"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>footballsystem_db.referees_matches.away_team</code>.
     */
    public final TableField<RefereesMatchesRecord, String> AWAY_TEAM = createField(DSL.name("away_team"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>footballsystem_db.referees_matches.referee</code>.
     */
    public final TableField<RefereesMatchesRecord, String> REFEREE = createField(DSL.name("referee"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * Create a <code>footballsystem_db.referees_matches</code> table reference
     */
    public RefereesMatches() {
        this(DSL.name("referees_matches"), null);
    }

    /**
     * Create an aliased <code>footballsystem_db.referees_matches</code> table reference
     */
    public RefereesMatches(String alias) {
        this(DSL.name(alias), REFEREES_MATCHES);
    }

    /**
     * Create an aliased <code>footballsystem_db.referees_matches</code> table reference
     */
    public RefereesMatches(Name alias) {
        this(alias, REFEREES_MATCHES);
    }

    private RefereesMatches(Name alias, Table<RefereesMatchesRecord> aliased) {
        this(alias, aliased, null);
    }

    private RefereesMatches(Name alias, Table<RefereesMatchesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> RefereesMatches(Table<O> child, ForeignKey<O, RefereesMatchesRecord> key) {
        super(child, key, REFEREES_MATCHES);
    }

    @Override
    public Schema getSchema() {
        return FootballsystemDb.FOOTBALLSYSTEM_DB;
    }

    @Override
    public UniqueKey<RefereesMatchesRecord> getPrimaryKey() {
        return Keys.KEY_REFEREES_MATCHES_PRIMARY;
    }

    @Override
    public List<UniqueKey<RefereesMatchesRecord>> getKeys() {
        return Arrays.<UniqueKey<RefereesMatchesRecord>>asList(Keys.KEY_REFEREES_MATCHES_PRIMARY);
    }

    @Override
    public List<ForeignKey<RefereesMatchesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<RefereesMatchesRecord, ?>>asList(Keys.FK_REFEREES_MATCHES_MATCHES, Keys.FK_REFEREES_MATCHES_FANS);
    }

    public Matches matches() {
        return new Matches(this, Keys.FK_REFEREES_MATCHES_MATCHES);
    }

    public Fans fans() {
        return new Fans(this, Keys.FK_REFEREES_MATCHES_FANS);
    }

    @Override
    public RefereesMatches as(String alias) {
        return new RefereesMatches(DSL.name(alias), this);
    }

    @Override
    public RefereesMatches as(Name alias) {
        return new RefereesMatches(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public RefereesMatches rename(String name) {
        return new RefereesMatches(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public RefereesMatches rename(Name name) {
        return new RefereesMatches(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<LocalDateTime, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
