/*
 * This file is generated by jOOQ.
 */
package DB.Tables.tables;


import DB.Tables.FootballsystemDb;
import DB.Tables.Keys;
import DB.Tables.tables.records.SeasonsRecord;

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
public class Seasons extends TableImpl<SeasonsRecord> {

    private static final long serialVersionUID = 1106490356;

    /**
     * The reference instance of <code>footballsystem_db.seasons</code>
     */
    public static final Seasons SEASONS = new Seasons();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<SeasonsRecord> getRecordType() {
        return SeasonsRecord.class;
    }

    /**
     * The column <code>footballsystem_db.seasons.year</code>.
     */
    public final TableField<SeasonsRecord, String> YEAR = createField(DSL.name("year"), org.jooq.impl.SQLDataType.VARCHAR(4).nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>footballsystem_db.seasons.schedulingPolicy</code>.
     */
    public final TableField<SeasonsRecord, String> SCHEDULINGPOLICY = createField(DSL.name("schedulingPolicy"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>footballsystem_db.seasons.calculationPolicy</code>.
     */
    public final TableField<SeasonsRecord, String> CALCULATIONPOLICY = createField(DSL.name("calculationPolicy"), org.jooq.impl.SQLDataType.VARCHAR(50).nullable(false).defaultValue(org.jooq.impl.DSL.field("''", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>footballsystem_db.seasons.current_season</code>.
     */
    public final TableField<SeasonsRecord, Byte> CURRENT_SEASON = createField(DSL.name("current_season"), org.jooq.impl.SQLDataType.TINYINT.nullable(false).defaultValue(org.jooq.impl.DSL.field("0", org.jooq.impl.SQLDataType.TINYINT)), this, "");

    /**
     * Create a <code>footballsystem_db.seasons</code> table reference
     */
    public Seasons() {
        this(DSL.name("seasons"), null);
    }

    /**
     * Create an aliased <code>footballsystem_db.seasons</code> table reference
     */
    public Seasons(String alias) {
        this(DSL.name(alias), SEASONS);
    }

    /**
     * Create an aliased <code>footballsystem_db.seasons</code> table reference
     */
    public Seasons(Name alias) {
        this(alias, SEASONS);
    }

    private Seasons(Name alias, Table<SeasonsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Seasons(Name alias, Table<SeasonsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    public <O extends Record> Seasons(Table<O> child, ForeignKey<O, SeasonsRecord> key) {
        super(child, key, SEASONS);
    }

    @Override
    public Schema getSchema() {
        return FootballsystemDb.FOOTBALLSYSTEM_DB;
    }

    @Override
    public UniqueKey<SeasonsRecord> getPrimaryKey() {
        return Keys.KEY_SEASONS_PRIMARY;
    }

    @Override
    public List<UniqueKey<SeasonsRecord>> getKeys() {
        return Arrays.<UniqueKey<SeasonsRecord>>asList(Keys.KEY_SEASONS_PRIMARY);
    }

    @Override
    public List<ForeignKey<SeasonsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<SeasonsRecord, ?>>asList(Keys.FK__SCHEDULINGPOLICYS, Keys.FK_SEASONS_CALCULATIONPOLICYS);
    }

    public Schedulingpolicys schedulingpolicys() {
        return new Schedulingpolicys(this, Keys.FK__SCHEDULINGPOLICYS);
    }

    public Calculationpolicys calculationpolicys() {
        return new Calculationpolicys(this, Keys.FK_SEASONS_CALCULATIONPOLICYS);
    }

    @Override
    public Seasons as(String alias) {
        return new Seasons(DSL.name(alias), this);
    }

    @Override
    public Seasons as(Name alias) {
        return new Seasons(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Seasons rename(String name) {
        return new Seasons(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Seasons rename(Name name) {
        return new Seasons(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<String, String, String, Byte> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
