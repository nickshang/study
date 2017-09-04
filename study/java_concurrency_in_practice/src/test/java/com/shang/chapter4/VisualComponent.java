package com.shang.chapter4;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.concurrent.*;

/**
 * VisualComponent
 * <p/>
 * Delegating thread safety to multiple underlying state variables
 * 将线程安全行委托给多个状态变量
 *
 * @author Brian Goetz and Tim Peierls
 */
public class VisualComponent {
    private final List<KeyListener> keyListeners
            = new CopyOnWriteArrayList<KeyListener>();
    private final List<MouseListener> mouseListeners
            = new CopyOnWriteArrayList<MouseListener>();

    public void addKeyListener(KeyListener listener) {
        keyListeners.add(listener);
    }

    public void addMouseListener(MouseListener listener) {
        mouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener) {
        keyListeners.remove(listener);
    }

    public void removeMouseListener(MouseListener listener) {
        mouseListeners.remove(listener);
    }
}
