package com.getprepared.persistence.database;

import com.getprepared.core.util.ConnectionUtils;
import com.getprepared.core.util.DataSourceUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.sql.DataSource;

import java.sql.Connection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 06.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConnectionProviderTest {

    @Mock
    private ThreadLocal<ConnectionCounter> threadLocal;

    @InjectMocks
    private ConnectionProvider provider = new ConnectionProvider();

    @Ignore
    @Test // TODO
    public void requireBegin() throws Exception {
        when(threadLocal.get()).thenReturn(notNull(ConnectionCounter.class));
        provider.begin();
        verify(threadLocal.get()).increment();
        verifyNoMoreInteractions(threadLocal);
    }

    @Test
    @Ignore // TODO
    public void requireBeginWithValueInThreadLocal() throws Exception {
        doReturn(notNull(ConnectionCounter.class)).when(threadLocal.get());
        verify(threadLocal.get(), only()).increment();
        verifyNoMoreInteractions(threadLocal);
    }
//
//    @Test
//    public void rollback() throws Exception {
//    }
//
//    @Test
//    public void getConnection() throws Exception {
//    }
//
//    @Test
//    public void isTransactional() throws Exception {
//    }

}