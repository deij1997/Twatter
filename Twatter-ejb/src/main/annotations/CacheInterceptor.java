/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package annotations;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.HttpHeaders;

/**
 *
 * @author Jesse
 */
@Interceptor
@Cached
public class CacheInterceptor
{
    @Inject
    private HttpServletResponse request;
    
    private CacheInterceptor()
    {

    }

    @AroundInvoke
    public Object applyHats(InvocationContext ctx) throws Exception
    {
        Object ret = ctx.proceed();

        request.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=86400");
        
        return ret;
    }
}
