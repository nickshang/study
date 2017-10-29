package com.shang.disruptor.base;

//http://ifeve.com/disruptor-getting-started/

/**
 * 事件对象：Event来包含需要传递的数据
 */
public class LongEvent { 
    private long value;
    public long getValue() { 
        return value; 
    } 
 
    public void setValue(long value) { 
        this.value = value; 
    } 
} 