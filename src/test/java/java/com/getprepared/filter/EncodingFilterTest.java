package java.com.getprepared.filter;

import com.getprepared.filter.EncodingFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.getprepared.constant.WebConstants.FILTERS_VARIABLES.ENCODING;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Created by koval on 31.01.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EncodingFilterTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterConfig config;

    @Mock
    private FilterChain chain;

    private Filter filter;

    @Before
    public void setUp() {
        filter = new EncodingFilter();
    }

    @Test
    public void requireNoInteractionsWithFilterConfig() throws Exception {
        filter.init(config);
        verify(config, never()).getInitParameter(anyString());
        verifyNoMoreInteractions(config);
    }

    @Test
    public void requireSetEncoding() throws Exception {
        filter.doFilter(request, response, chain);
        verifyNoMoreInteractions(response);
        verify(request, only()).setCharacterEncoding(ENCODING);
        verifyNoMoreInteractions(request);
    }

    @Test
    public void requireInteractionsDoFilter() throws Exception {
        filter.doFilter(request, response, chain);
        verify(chain, only()).doFilter(request, response);
        verifyNoMoreInteractions(request);
        verifyNoMoreInteractions(response);
        verifyNoMoreInteractions(chain);
    }
}