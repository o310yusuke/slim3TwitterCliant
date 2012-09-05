package jp.o310yusuke.dao;

import org.slim3.datastore.Datastore;

import com.google.appengine.api.datastore.Transaction;

public class TransactionUtil {

    private Transaction transaction;
    
    public void beginTransaction() {
        transaction = Datastore.beginTransaction();
    }
    
    public void commit() {
        transaction.commit();
    }
    
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
    
    public Transaction getTransaction() {
        return transaction;
    }
}
