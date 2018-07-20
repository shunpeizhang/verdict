package org.verdictdb.sqlwriter;

import org.verdictdb.core.querying.CreateSchemaQuery;
import org.verdictdb.core.sqlobject.CreateTableQuery;
import org.verdictdb.core.sqlobject.DropTableQuery;
import org.verdictdb.core.sqlobject.InsertValuesQuery;
import org.verdictdb.core.sqlobject.SelectQuery;
import org.verdictdb.core.sqlobject.SetOperationRelation;
import org.verdictdb.core.sqlobject.SqlConvertible;
import org.verdictdb.exception.VerdictDBException;
import org.verdictdb.exception.VerdictDBTypeException;
import org.verdictdb.exception.VerdictDBValueException;
import org.verdictdb.sqlsyntax.SqlSyntax;

public class QueryToSql {
  
  public static String convert(SqlSyntax syntax, SqlConvertible query) throws VerdictDBException {
    if (query == null) {
      throw new VerdictDBValueException("null value passed");
    }
    
    if (query instanceof SelectQuery) {
      SelectQueryToSql tosql = new SelectQueryToSql(syntax);
      return tosql.toSql((SelectQuery) query);
    }
    else if (query instanceof CreateSchemaQuery) {
      CreateSchemaToSql tosql = new CreateSchemaToSql(syntax);
      return tosql.toSql((CreateSchemaQuery) query);
    }
    else if (query instanceof CreateTableQuery) {
      CreateTableToSql tosql = new CreateTableToSql(syntax);
      return tosql.toSql((CreateTableQuery) query);
    }
    else if (query instanceof DropTableQuery) {
      DropTableToSql tosql = new DropTableToSql(syntax);
      return tosql.toSql((DropTableQuery) query);
    }
    else if (query instanceof InsertValuesQuery) {
      InsertQueryToSql tosql = new InsertQueryToSql(syntax);
      return tosql.toSql((InsertValuesQuery) query);
    }
    else if (query instanceof SetOperationRelation) {
      SetOperationToSql tosql = new SetOperationToSql(syntax);
      return tosql.toSql((SetOperationRelation) query);
    }
    else {
      throw new VerdictDBTypeException(query);
    }
  }

}