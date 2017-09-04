package com.shang.chapter3;

import java.math.BigInteger;
import java.util.*;

import net.jcip.annotations.*;

/**
 * OneValueCache
 * <p/>
 * Immutable holder for caching a number and its factors
 * 对数值机器因数分解结构进行缓存的不可变容器类
 *
 * @author Brian Goetz and Tim Peierls
 */
@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private final BigInteger[] lastFactors;

    public OneValueCache(BigInteger i,
                         BigInteger[] factors) {
        lastNumber = i;
        lastFactors = Arrays.copyOf(factors, factors.length);
    }

    public BigInteger[] getFactors(BigInteger i) {
        if (lastNumber == null || !lastNumber.equals(i))
            return null;
        else
            return Arrays.copyOf(lastFactors, lastFactors.length);
    }
}
