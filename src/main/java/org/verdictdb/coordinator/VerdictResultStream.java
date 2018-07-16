package org.verdictdb.coordinator;

import java.util.Iterator;

import org.verdictdb.connection.DbmsQueryResult;
import org.verdictdb.core.resulthandler.ExecutionResultReader;

public class VerdictResultStream implements Iterable<VerdictSingleResult>, Iterator<VerdictSingleResult> {
  
  ExecutionResultReader reader;
  
  ExecutionContext execContext;
  
  public VerdictResultStream(ExecutionResultReader reader, ExecutionContext execContext) {
    this.reader = reader;
    this.execContext = execContext;
  }

  @Override
  public boolean hasNext() {
    return reader.hasNext();
  }

  @Override
  public VerdictSingleResult next() {
    DbmsQueryResult internalResult = reader.next();
    VerdictSingleResult result = new VerdictSingleResult(internalResult);
    return result;
  }

  @Override
  public Iterator<VerdictSingleResult> iterator() {
    return this;
  }

  public void close() {
    
  }
  
}
