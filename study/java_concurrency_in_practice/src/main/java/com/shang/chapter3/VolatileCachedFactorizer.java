package com.shang.chapter3;

import java.math.BigInteger;
import javax.servlet.*;

import net.jcip.annotations.*;

/**
 * VolatileCachedFactorizer
 * <p/>
 * Caching the last result using a volatile reference to an immutable holder object
 * 使用指向不可变容器对象的volatile类型引用以缓存最新的结果。
 *
 * @author Brian Goetz and Tim Peierls
 */
@ThreadSafe
public class VolatileCachedFactorizer extends GenericServlet implements Servlet {
    private volatile OneValueCache cache = new OneValueCache(null, null);

    public void service(ServletRequest req, ServletResponse resp) {
        BigInteger i = extractFromRequest(req);
        BigInteger[] factors = cache.getFactors(i);
        if (factors == null) {
            factors = factor(i);
            cache = new OneValueCache(i, factors);
        }
        encodeIntoResponse(resp, factors);
    }

    void encodeIntoResponse(ServletResponse resp, BigInteger[] factors) {
    }

    BigInteger extractFromRequest(ServletRequest req) {
        return new BigInteger("7");
    }

    BigInteger[] factor(BigInteger i) {
        // Doesn't really factor
        return new BigInteger[]{i};
    }
}

