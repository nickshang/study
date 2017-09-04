package com.shang.chapter3;

/**
 * UnsafeStates
 * <p/>
 * Allowing internal mutable state to escape
 * 使内部的可变状态逸出
 *
 * @author Brian Goetz and Tim Peierls
 */
class UnsafeStates {
    private String[] states = new String[]{
        "AK", "AL" /*...*/
    };

    public String[] getStates() {
        return states;
    }
}
