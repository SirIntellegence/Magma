/*
 * Magma Server
 * Copyright (C) 2019-2020.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.magmafoundation.magma.remapper;

import java.util.LinkedList;

/**
 * ClassLoaderContext
 *
 * @author Hexeption admin@hexeption.co.uk
 * @since 11/11/2019 - 07:48 am
 */
public class ClassLoaderContext {

    private static final ThreadLocal<LinkedList<ClassLoader>> THREAD_LOCAL = new ThreadLocal<>();

    public static void put(ClassLoader classLoader) {
        LinkedList<ClassLoader> stack = THREAD_LOCAL.get();
        if (stack == null) {
            stack = new LinkedList<>();
            THREAD_LOCAL.set(stack);
        }
        stack.push(classLoader);
    }

    public static ClassLoader remove() {
        LinkedList<ClassLoader> stack = THREAD_LOCAL.get();
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        return stack.remove();
    }

    public static ClassLoader pop() {
        LinkedList<ClassLoader> stack = THREAD_LOCAL.get();
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        return stack.pop();
    }

    public static ClassLoader peek() {
        LinkedList<ClassLoader> stack = THREAD_LOCAL.get();
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

}
