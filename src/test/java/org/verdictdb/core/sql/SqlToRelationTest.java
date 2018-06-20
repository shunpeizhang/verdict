package org.verdictdb.core.sql;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.verdictdb.core.query.AbstractRelation;
import org.verdictdb.core.query.AliasReference;
import org.verdictdb.core.query.AliasedColumn;
import org.verdictdb.core.query.AsteriskColumn;
import org.verdictdb.core.query.BaseColumn;
import org.verdictdb.core.query.BaseTable;
import org.verdictdb.core.query.ColumnOp;
import org.verdictdb.core.query.SelectItem;
import org.verdictdb.core.query.SelectQueryOp;
import org.verdictdb.core.query.UnnamedColumn;
import org.verdictdb.exception.VerdictDbException;

public class SqlToRelationTest {

  @Test
  public void testSelectAllBaseTable() throws VerdictDbException {
    String actual = "select * from myschema.mytable as t";
    NonValidatingSQLParser sqlToRelation = new NonValidatingSQLParser();
    SelectQueryOp expected = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(
        new AsteriskColumn()
    ), Arrays.<AbstractRelation>asList(new BaseTable("myschema", "mytable", "t")));
    AbstractRelation sel = sqlToRelation.toRelation(actual);
    assertEquals(expected, sel);
  }

  @Test
  public void testSelectColumnsBaseTable() throws VerdictDbException {
    String actual = "select t.mycolumn1, t.mycolumn2 from myschema.mytable as t";
    NonValidatingSQLParser sqlToRelation = new NonValidatingSQLParser();
    AbstractRelation sel = sqlToRelation.toRelation(actual);
    SelectQueryOp expected = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(
        new BaseColumn("t", "mycolumn1"),
        new BaseColumn("t", "mycolumn2")
    ), Arrays.<AbstractRelation>asList(new BaseTable("myschema", "mytable", "t")));
    assertEquals(expected, sel);
  }

  @Test
  public void testSelectAvgBaseTable() throws VerdictDbException {
    String actual = "select avg(t.mycolumn1) from myschema.mytable as t";
    NonValidatingSQLParser sqlToRelation = new NonValidatingSQLParser();
    AbstractRelation sel = sqlToRelation.toRelation(actual);
    SelectQueryOp expected = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(
        new ColumnOp("avg", new BaseColumn("t", "mycolumn1"))
    ), Arrays.<AbstractRelation>asList(new BaseTable("myschema", "mytable", "t")));
    assertEquals(expected, sel);
  }

  @Test
  public void testSelectSumBaseTable() throws VerdictDbException {
    String actual = "select sum(t.mycolumn1) from myschema.mytable as t";
    NonValidatingSQLParser sqlToRelation = new NonValidatingSQLParser();
    AbstractRelation sel = sqlToRelation.toRelation(actual);
    SelectQueryOp expected = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(
        new ColumnOp("sum", new BaseColumn("t", "mycolumn1"))
    ), Arrays.<AbstractRelation>asList(new BaseTable("myschema", "mytable", "t")));
    assertEquals(expected, sel);
  }

  @Test
  public void testSelectCountBaseTable() throws VerdictDbException {
    String actual = "select count(*) from myschema.mytable as t";
    NonValidatingSQLParser sqlToRelation = new NonValidatingSQLParser();
    AbstractRelation sel = sqlToRelation.toRelation(actual);
    SelectQueryOp expected = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(
        new ColumnOp("count", new AsteriskColumn())
    ), Arrays.<AbstractRelation>asList(new BaseTable("myschema", "mytable", "t")));
    assertEquals(expected, sel);
  }

  @Test
  public void testSelectAggregatesBaseTable() throws VerdictDbException {
    String actual = "select avg(t.mycolumn1), sum(t.mycolumn1), count(*) from myschema.mytable as t";
    NonValidatingSQLParser sqlToRelation = new NonValidatingSQLParser();
    AbstractRelation sel = sqlToRelation.toRelation(actual);
    SelectQueryOp expected = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(
        new ColumnOp("avg", new BaseColumn("t", "mycolumn1")),
        new ColumnOp("sum", new BaseColumn("t", "mycolumn1")),
        new ColumnOp("count", new AsteriskColumn())
    ), Arrays.<AbstractRelation>asList(new BaseTable("myschema", "mytable", "t")));
    assertEquals(expected, sel);
  }

  @Test
  public void testSelectAddBaseTable() throws VerdictDbException {
    String actual = "select t.mycolumn1 + t.mycolumn2 from myschema.mytable as t";
    NonValidatingSQLParser sqlToRelation = new NonValidatingSQLParser();
    AbstractRelation sel = sqlToRelation.toRelation(actual);
    SelectQueryOp expected = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(
        new ColumnOp("add", Arrays.<UnnamedColumn>asList(
            new BaseColumn("t", "mycolumn1"),
            new BaseColumn("t", "mycolumn2")
        ))
    ), Arrays.<AbstractRelation>asList(new BaseTable("myschema", "mytable", "t")));
    assertEquals(expected, sel);
  }

  @Test
  public void testSelectSubtractBaseTable() throws VerdictDbException {
    String actual = "select t.mycolumn1 - t.mycolumn2 from myschema.mytable as t";
    NonValidatingSQLParser sqlToRelation = new NonValidatingSQLParser();
    AbstractRelation sel = sqlToRelation.toRelation(actual);
    SelectQueryOp expected = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(
        new ColumnOp("subtract", Arrays.<UnnamedColumn>asList(
            new BaseColumn("t", "mycolumn1"),
            new BaseColumn("t", "mycolumn2")
        ))
    ), Arrays.<AbstractRelation>asList(new BaseTable("myschema", "mytable", "t")));
    assertEquals(expected, sel);
  }

  @Test
  public void testSelectMultiplyBaseTable() throws VerdictDbException {
    String actual = "select t.mycolumn1 * t.mycolumn2 from myschema.mytable as t";
    NonValidatingSQLParser sqlToRelation = new NonValidatingSQLParser();
    AbstractRelation sel = sqlToRelation.toRelation(actual);
    SelectQueryOp expected = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(
        new ColumnOp("multiply", Arrays.<UnnamedColumn>asList(
            new BaseColumn("t", "mycolumn1"),
            new BaseColumn("t", "mycolumn2")
        ))
    ), Arrays.<AbstractRelation>asList(new BaseTable("myschema", "mytable", "t")));
    assertEquals(expected, sel);
  }

  @Test
  public void testSelectDivideBaseTable() throws VerdictDbException {
    String actual = "select t.mycolumn1 / t.mycolumn2 from myschema.mytable as t";
    NonValidatingSQLParser sqlToRelation = new NonValidatingSQLParser();
    AbstractRelation sel = sqlToRelation.toRelation(actual);
    SelectQueryOp expected = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(
        new ColumnOp("divide", Arrays.<UnnamedColumn>asList(
            new BaseColumn("t", "mycolumn1"),
            new BaseColumn("t", "mycolumn2")
        ))
    ), Arrays.<AbstractRelation>asList(new BaseTable("myschema", "mytable", "t")));
    assertEquals(expected, sel);
  }

  @Test
  public void testSelectAvgGroupbyBaseTable() throws VerdictDbException {
    String actual = "select t.mygroup, avg(t.mycolumn1) as myavg from myschema.mytable as t group by mygroup";
    NonValidatingSQLParser sqlToRelation = new NonValidatingSQLParser();
    AbstractRelation sel = sqlToRelation.toRelation(actual);
    SelectQueryOp expected = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(
        new BaseColumn("t", "mygroup"),
        new AliasedColumn(new ColumnOp("avg", new BaseColumn("t", "mycolumn1")), "myavg")
    ), Arrays.<AbstractRelation>asList(new BaseTable("myschema", "mytable", "t")));
    expected.addGroupby(new AliasReference("mygroup"));
    assertEquals(expected, sel);
  }

  @Test
  public void testSelectNestedGroupby() throws VerdictDbException {
    String actual = "select * from ("
        + "select t.mygroup, avg(t.mycolumn1) as myavg from myschema.mytable as t group by mygroup) as s "
        + "group by mygroup2";
    NonValidatingSQLParser sqlToRelation = new NonValidatingSQLParser();
    AbstractRelation sel = sqlToRelation.toRelation(actual);
    SelectQueryOp subquery = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(
        new BaseColumn("t", "mygroup"),
        new AliasedColumn(new ColumnOp("avg", new BaseColumn("t", "mycolumn1")), "myavg")
    ), Arrays.<AbstractRelation>asList(new BaseTable("myschema", "mytable", "t")));
    subquery.addGroupby(new AliasReference("mygroup"));
    subquery.setAliasName("s");
    SelectQueryOp expected = SelectQueryOp.getSelectQueryOp(Arrays.<SelectItem>asList(new AsteriskColumn()),
        Arrays.<AbstractRelation>asList(subquery));
    expected.addGroupby(new AliasReference("mygroup2"));
    assertEquals(expected, sel);
  }
}
