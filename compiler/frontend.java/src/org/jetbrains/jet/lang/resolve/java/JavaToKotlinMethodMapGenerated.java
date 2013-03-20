/*
 * Copyright 2010-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.jet.lang.resolve.java;

import com.google.common.collect.ImmutableMultimap;

import static org.jetbrains.jet.lang.resolve.java.JavaToKotlinMethodMap.*;

/* This file is generated by org.jetbrains.jet.generators.jvm.GenerateJavaToKotlinMethodMap. DO NOT EDIT! */
@SuppressWarnings("unchecked")
class JavaToKotlinMethodMapGenerated {
    final ImmutableMultimap<String, JavaToKotlinMethodMap.ClassData> map;
    
    JavaToKotlinMethodMapGenerated() {
        ImmutableMultimap.Builder<String, JavaToKotlinMethodMap.ClassData> b = ImmutableMultimap.builder();
        
        put(b, "java.lang.String", "String",
            pair("java.lang.String int compareTo(java.lang.String)", "public open fun compareTo(that : jet.String) : jet.Int defined in jet.String"),            
            pair("java.lang.String boolean equals(java.lang.Object)", "public final fun equals(other : jet.Any?) : jet.Boolean defined in jet.String"),            
            pair("java.lang.String java.lang.String toString()", "public open fun toString() : jet.String defined in jet.String")            
        );
        
        put(b, "java.lang.CharSequence", "CharSequence",
            pair("java.lang.CharSequence java.lang.String toString()", "public abstract fun toString() : jet.String defined in jet.CharSequence")            
        );
        
        put(b, "java.lang.Throwable", "Throwable",
            pair("java.lang.Throwable java.lang.Throwable getCause()", "public final fun getCause() : jet.Throwable? defined in jet.Throwable"),            
            pair("java.lang.Throwable java.lang.String getMessage()", "public final fun getMessage() : jet.String? defined in jet.Throwable"),            
            pair("java.lang.Throwable void printStackTrace()", "public final fun printStackTrace() : jet.Unit defined in jet.Throwable")            
        );
        
        put(b, "java.lang.Comparable", "Comparable",
            pair("java.lang.Comparable int compareTo(T)", "public abstract fun compareTo(other : T) : jet.Int defined in jet.Comparable")            
        );
        
        put(b, "java.lang.Enum", "Enum",
            pair("java.lang.Enum java.lang.String name()", "public final fun name() : jet.String defined in jet.Enum"),            
            pair("java.lang.Enum int ordinal()", "public final fun ordinal() : jet.Int defined in jet.Enum")            
        );
        
        put(b, "java.lang.Iterable", "Iterable",
            pair("java.lang.Iterable java.util.Iterator<T> iterator()", "public abstract fun iterator() : jet.Iterator<T> defined in jet.Iterable")            
        );
        
        put(b, "java.lang.Iterable", "MutableIterable",
            pair("java.lang.Iterable java.util.Iterator<T> iterator()", "public abstract fun iterator() : jet.MutableIterator<T> defined in jet.MutableIterable")            
        );
        
        put(b, "java.util.Iterator", "Iterator",
            pair("java.util.Iterator boolean hasNext()", "public abstract fun hasNext() : jet.Boolean defined in jet.Iterator"),            
            pair("java.util.Iterator E next()", "public abstract fun next() : T defined in jet.Iterator")            
        );
        
        put(b, "java.util.Iterator", "MutableIterator",
            pair("java.util.Iterator boolean hasNext()", "public abstract fun hasNext() : jet.Boolean defined in jet.MutableIterator"),            
            pair("java.util.Iterator E next()", "public abstract fun next() : T defined in jet.MutableIterator"),            
            pair("java.util.Iterator void remove()", "public abstract fun remove() : jet.Unit defined in jet.MutableIterator")            
        );
        
        put(b, "java.util.Collection", "Collection",
            pair("java.util.Collection boolean contains(java.lang.Object)", "public abstract fun contains(o : jet.Any?) : jet.Boolean defined in jet.Collection"),            
            pair("java.util.Collection boolean containsAll(java.util.Collection<?>)", "public abstract fun containsAll(c : jet.Collection<jet.Any?>) : jet.Boolean defined in jet.Collection"),            
            pair("java.util.Collection boolean equals(java.lang.Object)", "public abstract fun equals(other : jet.Any?) : jet.Boolean defined in jet.Collection"),            
            pair("java.util.Collection int hashCode()", "public abstract fun hashCode() : jet.Int defined in jet.Collection"),            
            pair("java.util.Collection boolean isEmpty()", "public abstract fun isEmpty() : jet.Boolean defined in jet.Collection"),            
            pair("java.util.Collection java.util.Iterator<E> iterator()", "public abstract fun iterator() : jet.Iterator<E> defined in jet.Collection"),            
            pair("java.util.Collection int size()", "public abstract fun size() : jet.Int defined in jet.Collection"),            
            pair("java.util.Collection T[] toArray(T[])", "public abstract fun <T> toArray(a : jet.Array<out T>) : jet.Array<T> defined in jet.Collection"),            
            pair("java.util.Collection java.lang.Object[] toArray()", "public abstract fun toArray() : jet.Array<jet.Any?> defined in jet.Collection")            
        );
        
        put(b, "java.util.Collection", "MutableCollection",
            pair("java.util.Collection boolean add(E)", "public abstract fun add(e : E) : jet.Boolean defined in jet.MutableCollection"),            
            pair("java.util.Collection boolean addAll(java.util.Collection<? extends E>)", "public abstract fun addAll(c : jet.Collection<E>) : jet.Boolean defined in jet.MutableCollection"),            
            pair("java.util.Collection void clear()", "public abstract fun clear() : jet.Unit defined in jet.MutableCollection"),            
            pair("java.util.Collection boolean contains(java.lang.Object)", "public abstract fun contains(o : jet.Any?) : jet.Boolean defined in jet.MutableCollection"),            
            pair("java.util.Collection boolean containsAll(java.util.Collection<?>)", "public abstract fun containsAll(c : jet.Collection<jet.Any?>) : jet.Boolean defined in jet.MutableCollection"),            
            pair("java.util.Collection boolean equals(java.lang.Object)", "public abstract fun equals(other : jet.Any?) : jet.Boolean defined in jet.MutableCollection"),            
            pair("java.util.Collection int hashCode()", "public abstract fun hashCode() : jet.Int defined in jet.MutableCollection"),            
            pair("java.util.Collection boolean isEmpty()", "public abstract fun isEmpty() : jet.Boolean defined in jet.MutableCollection"),            
            pair("java.util.Collection java.util.Iterator<E> iterator()", "public abstract fun iterator() : jet.MutableIterator<E> defined in jet.MutableCollection"),            
            pair("java.util.Collection boolean remove(java.lang.Object)", "public abstract fun remove(o : jet.Any?) : jet.Boolean defined in jet.MutableCollection"),            
            pair("java.util.Collection boolean removeAll(java.util.Collection<?>)", "public abstract fun removeAll(c : jet.Collection<jet.Any?>) : jet.Boolean defined in jet.MutableCollection"),            
            pair("java.util.Collection boolean retainAll(java.util.Collection<?>)", "public abstract fun retainAll(c : jet.Collection<jet.Any?>) : jet.Boolean defined in jet.MutableCollection"),            
            pair("java.util.Collection int size()", "public abstract fun size() : jet.Int defined in jet.MutableCollection"),            
            pair("java.util.Collection T[] toArray(T[])", "public abstract fun <T> toArray(a : jet.Array<out T>) : jet.Array<T> defined in jet.MutableCollection"),            
            pair("java.util.Collection java.lang.Object[] toArray()", "public abstract fun toArray() : jet.Array<jet.Any?> defined in jet.MutableCollection")            
        );
        
        put(b, "java.util.List", "List",
            pair("java.util.List boolean contains(java.lang.Object)", "public abstract fun contains(o : jet.Any?) : jet.Boolean defined in jet.List"),            
            pair("java.util.List boolean containsAll(java.util.Collection<?>)", "public abstract fun containsAll(c : jet.Collection<jet.Any?>) : jet.Boolean defined in jet.List"),            
            pair("java.util.List boolean equals(java.lang.Object)", "public abstract fun equals(other : jet.Any?) : jet.Boolean defined in jet.List"),            
            pair("java.util.List E get(int)", "public abstract fun get(index : jet.Int) : E defined in jet.List"),            
            pair("java.util.List int hashCode()", "public abstract fun hashCode() : jet.Int defined in jet.List"),            
            pair("java.util.List int indexOf(java.lang.Object)", "public abstract fun indexOf(o : jet.Any?) : jet.Int defined in jet.List"),            
            pair("java.util.List boolean isEmpty()", "public abstract fun isEmpty() : jet.Boolean defined in jet.List"),            
            pair("java.util.List java.util.Iterator<E> iterator()", "public abstract fun iterator() : jet.Iterator<E> defined in jet.List"),            
            pair("java.util.List int lastIndexOf(java.lang.Object)", "public abstract fun lastIndexOf(o : jet.Any?) : jet.Int defined in jet.List"),            
            pair("java.util.List java.util.ListIterator<E> listIterator()", "public abstract fun listIterator() : jet.ListIterator<E> defined in jet.List"),            
            pair("java.util.List java.util.ListIterator<E> listIterator(int)", "public abstract fun listIterator(index : jet.Int) : jet.ListIterator<E> defined in jet.List"),            
            pair("java.util.List int size()", "public abstract fun size() : jet.Int defined in jet.List"),            
            pair("java.util.List java.util.List<E> subList(int, int)", "public abstract fun subList(fromIndex : jet.Int, toIndex : jet.Int) : jet.List<E> defined in jet.List"),            
            pair("java.util.List T[] toArray(T[])", "public abstract fun <T> toArray(a : jet.Array<out T>) : jet.Array<T> defined in jet.List"),            
            pair("java.util.List java.lang.Object[] toArray()", "public abstract fun toArray() : jet.Array<jet.Any?> defined in jet.List")            
        );
        
        put(b, "java.util.List", "MutableList",
            pair("java.util.List boolean add(E)", "public abstract fun add(e : E) : jet.Boolean defined in jet.MutableList"),            
            pair("java.util.List void add(int, E)", "public abstract fun add(index : jet.Int, element : E) : jet.Unit defined in jet.MutableList"),            
            pair("java.util.List boolean addAll(int, java.util.Collection<? extends E>)", "public abstract fun addAll(index : jet.Int, c : jet.Collection<E>) : jet.Boolean defined in jet.MutableList"),            
            pair("java.util.List boolean addAll(java.util.Collection<? extends E>)", "public abstract fun addAll(c : jet.Collection<E>) : jet.Boolean defined in jet.MutableList"),            
            pair("java.util.List void clear()", "public abstract fun clear() : jet.Unit defined in jet.MutableList"),            
            pair("java.util.List boolean contains(java.lang.Object)", "public abstract fun contains(o : jet.Any?) : jet.Boolean defined in jet.MutableList"),            
            pair("java.util.List boolean containsAll(java.util.Collection<?>)", "public abstract fun containsAll(c : jet.Collection<jet.Any?>) : jet.Boolean defined in jet.MutableList"),            
            pair("java.util.List boolean equals(java.lang.Object)", "public abstract fun equals(other : jet.Any?) : jet.Boolean defined in jet.MutableList"),            
            pair("java.util.List E get(int)", "public abstract fun get(index : jet.Int) : E defined in jet.MutableList"),            
            pair("java.util.List int hashCode()", "public abstract fun hashCode() : jet.Int defined in jet.MutableList"),            
            pair("java.util.List int indexOf(java.lang.Object)", "public abstract fun indexOf(o : jet.Any?) : jet.Int defined in jet.MutableList"),            
            pair("java.util.List boolean isEmpty()", "public abstract fun isEmpty() : jet.Boolean defined in jet.MutableList"),            
            pair("java.util.List java.util.Iterator<E> iterator()", "public abstract fun iterator() : jet.Iterator<E> defined in jet.MutableList"),            
            pair("java.util.List int lastIndexOf(java.lang.Object)", "public abstract fun lastIndexOf(o : jet.Any?) : jet.Int defined in jet.MutableList"),            
            pair("java.util.List java.util.ListIterator<E> listIterator()", "public abstract fun listIterator() : jet.MutableListIterator<E> defined in jet.MutableList"),            
            pair("java.util.List java.util.ListIterator<E> listIterator(int)", "public abstract fun listIterator(index : jet.Int) : jet.MutableListIterator<E> defined in jet.MutableList"),            
            pair("java.util.List E remove(int)", "public abstract fun remove(index : jet.Int) : E defined in jet.MutableList"),            
            pair("java.util.List boolean remove(java.lang.Object)", "public abstract fun remove(o : jet.Any?) : jet.Boolean defined in jet.MutableList"),            
            pair("java.util.List boolean removeAll(java.util.Collection<?>)", "public abstract fun removeAll(c : jet.Collection<jet.Any?>) : jet.Boolean defined in jet.MutableList"),            
            pair("java.util.List boolean retainAll(java.util.Collection<?>)", "public abstract fun retainAll(c : jet.Collection<jet.Any?>) : jet.Boolean defined in jet.MutableList"),            
            pair("java.util.List E set(int, E)", "public abstract fun set(index : jet.Int, element : E) : E defined in jet.MutableList"),            
            pair("java.util.List int size()", "public abstract fun size() : jet.Int defined in jet.MutableList"),            
            pair("java.util.List java.util.List<E> subList(int, int)", "public abstract fun subList(fromIndex : jet.Int, toIndex : jet.Int) : jet.MutableList<E> defined in jet.MutableList"),            
            pair("java.util.List T[] toArray(T[])", "public abstract fun <T> toArray(a : jet.Array<out T>) : jet.Array<T> defined in jet.MutableList"),            
            pair("java.util.List java.lang.Object[] toArray()", "public abstract fun toArray() : jet.Array<jet.Any?> defined in jet.MutableList")            
        );
        
        put(b, "java.util.Set", "Set",
            pair("java.util.Set boolean contains(java.lang.Object)", "public abstract fun contains(o : jet.Any?) : jet.Boolean defined in jet.Set"),            
            pair("java.util.Set boolean containsAll(java.util.Collection<?>)", "public abstract fun containsAll(c : jet.Collection<jet.Any?>) : jet.Boolean defined in jet.Set"),            
            pair("java.util.Set boolean equals(java.lang.Object)", "public abstract fun equals(other : jet.Any?) : jet.Boolean defined in jet.Set"),            
            pair("java.util.Set int hashCode()", "public abstract fun hashCode() : jet.Int defined in jet.Set"),            
            pair("java.util.Set boolean isEmpty()", "public abstract fun isEmpty() : jet.Boolean defined in jet.Set"),            
            pair("java.util.Set java.util.Iterator<E> iterator()", "public abstract fun iterator() : jet.Iterator<E> defined in jet.Set"),            
            pair("java.util.Set int size()", "public abstract fun size() : jet.Int defined in jet.Set"),            
            pair("java.util.Set T[] toArray(T[])", "public abstract fun <T> toArray(a : jet.Array<out T>) : jet.Array<T> defined in jet.Set"),            
            pair("java.util.Set java.lang.Object[] toArray()", "public abstract fun toArray() : jet.Array<jet.Any?> defined in jet.Set")            
        );
        
        put(b, "java.util.Set", "MutableSet",
            pair("java.util.Set boolean add(E)", "public abstract fun add(e : E) : jet.Boolean defined in jet.MutableSet"),            
            pair("java.util.Set boolean addAll(java.util.Collection<? extends E>)", "public abstract fun addAll(c : jet.Collection<E>) : jet.Boolean defined in jet.MutableSet"),            
            pair("java.util.Set void clear()", "public abstract fun clear() : jet.Unit defined in jet.MutableSet"),            
            pair("java.util.Set boolean contains(java.lang.Object)", "public abstract fun contains(o : jet.Any?) : jet.Boolean defined in jet.MutableSet"),            
            pair("java.util.Set boolean containsAll(java.util.Collection<?>)", "public abstract fun containsAll(c : jet.Collection<jet.Any?>) : jet.Boolean defined in jet.MutableSet"),            
            pair("java.util.Set boolean equals(java.lang.Object)", "public abstract fun equals(other : jet.Any?) : jet.Boolean defined in jet.MutableSet"),            
            pair("java.util.Set int hashCode()", "public abstract fun hashCode() : jet.Int defined in jet.MutableSet"),            
            pair("java.util.Set boolean isEmpty()", "public abstract fun isEmpty() : jet.Boolean defined in jet.MutableSet"),            
            pair("java.util.Set java.util.Iterator<E> iterator()", "public abstract fun iterator() : jet.MutableIterator<E> defined in jet.MutableSet"),            
            pair("java.util.Set boolean remove(java.lang.Object)", "public abstract fun remove(o : jet.Any?) : jet.Boolean defined in jet.MutableSet"),            
            pair("java.util.Set boolean removeAll(java.util.Collection<?>)", "public abstract fun removeAll(c : jet.Collection<jet.Any?>) : jet.Boolean defined in jet.MutableSet"),            
            pair("java.util.Set boolean retainAll(java.util.Collection<?>)", "public abstract fun retainAll(c : jet.Collection<jet.Any?>) : jet.Boolean defined in jet.MutableSet"),            
            pair("java.util.Set int size()", "public abstract fun size() : jet.Int defined in jet.MutableSet"),            
            pair("java.util.Set T[] toArray(T[])", "public abstract fun <T> toArray(a : jet.Array<out T>) : jet.Array<T> defined in jet.MutableSet"),            
            pair("java.util.Set java.lang.Object[] toArray()", "public abstract fun toArray() : jet.Array<jet.Any?> defined in jet.MutableSet")            
        );
        
        put(b, "java.util.Map", "Map",
            pair("java.util.Map boolean containsKey(java.lang.Object)", "public abstract fun containsKey(key : jet.Any?) : jet.Boolean defined in jet.Map"),            
            pair("java.util.Map boolean containsValue(java.lang.Object)", "public abstract fun containsValue(value : jet.Any?) : jet.Boolean defined in jet.Map"),            
            pair("java.util.Map java.util.Set<java.util.Map.Entry<K,V>> entrySet()", "public abstract fun entrySet() : jet.Set<jet.Map.Entry<K, V>> defined in jet.Map"),            
            pair("java.util.Map V get(java.lang.Object)", "public abstract fun get(key : jet.Any?) : V? defined in jet.Map"),            
            pair("java.util.Map boolean isEmpty()", "public abstract fun isEmpty() : jet.Boolean defined in jet.Map"),            
            pair("java.util.Map java.util.Set<K> keySet()", "public abstract fun keySet() : jet.Set<K> defined in jet.Map"),            
            pair("java.util.Map int size()", "public abstract fun size() : jet.Int defined in jet.Map"),            
            pair("java.util.Map java.util.Collection<V> values()", "public abstract fun values() : jet.Collection<V> defined in jet.Map")            
        );
        
        put(b, "java.util.Map", "MutableMap",
            pair("java.util.Map void clear()", "public abstract fun clear() : jet.Unit defined in jet.MutableMap"),            
            pair("java.util.Map boolean containsKey(java.lang.Object)", "public abstract fun containsKey(key : jet.Any?) : jet.Boolean defined in jet.MutableMap"),            
            pair("java.util.Map boolean containsValue(java.lang.Object)", "public abstract fun containsValue(value : jet.Any?) : jet.Boolean defined in jet.MutableMap"),            
            pair("java.util.Map java.util.Set<java.util.Map.Entry<K,V>> entrySet()", "public abstract fun entrySet() : jet.MutableSet<jet.MutableMap.MutableEntry<K, V>> defined in jet.MutableMap"),            
            pair("java.util.Map V get(java.lang.Object)", "public abstract fun get(key : jet.Any?) : V? defined in jet.MutableMap"),            
            pair("java.util.Map boolean isEmpty()", "public abstract fun isEmpty() : jet.Boolean defined in jet.MutableMap"),            
            pair("java.util.Map java.util.Set<K> keySet()", "public abstract fun keySet() : jet.MutableSet<K> defined in jet.MutableMap"),            
            pair("java.util.Map V put(K, V)", "public abstract fun put(key : K, value : V) : V? defined in jet.MutableMap"),            
            pair("java.util.Map void putAll(java.util.Map<? extends K,? extends V>)", "public abstract fun putAll(m : jet.Map<out K, V>) : jet.Unit defined in jet.MutableMap"),            
            pair("java.util.Map V remove(java.lang.Object)", "public abstract fun remove(key : jet.Any?) : V? defined in jet.MutableMap"),            
            pair("java.util.Map int size()", "public abstract fun size() : jet.Int defined in jet.MutableMap"),            
            pair("java.util.Map java.util.Collection<V> values()", "public abstract fun values() : jet.MutableCollection<V> defined in jet.MutableMap")            
        );
        
        put(b, "java.util.Map.Entry", "Map.Entry",
            pair("java.util.Map.Entry boolean equals(java.lang.Object)", "public abstract fun equals(other : jet.Any?) : jet.Boolean defined in jet.Map.Entry"),            
            pair("java.util.Map.Entry K getKey()", "public abstract fun getKey() : K defined in jet.Map.Entry"),            
            pair("java.util.Map.Entry V getValue()", "public abstract fun getValue() : V defined in jet.Map.Entry"),            
            pair("java.util.Map.Entry int hashCode()", "public abstract fun hashCode() : jet.Int defined in jet.Map.Entry")            
        );
        
        put(b, "java.util.Map.Entry", "MutableMap.MutableEntry",
            pair("java.util.Map.Entry boolean equals(java.lang.Object)", "public abstract fun equals(other : jet.Any?) : jet.Boolean defined in jet.MutableMap.MutableEntry"),            
            pair("java.util.Map.Entry K getKey()", "public abstract fun getKey() : K defined in jet.MutableMap.MutableEntry"),            
            pair("java.util.Map.Entry V getValue()", "public abstract fun getValue() : V defined in jet.MutableMap.MutableEntry"),            
            pair("java.util.Map.Entry int hashCode()", "public abstract fun hashCode() : jet.Int defined in jet.MutableMap.MutableEntry"),            
            pair("java.util.Map.Entry V setValue(V)", "public abstract fun setValue(value : V) : V defined in jet.MutableMap.MutableEntry")            
        );
        
        put(b, "java.util.ListIterator", "ListIterator",
            pair("java.util.ListIterator boolean hasNext()", "public abstract fun hasNext() : jet.Boolean defined in jet.ListIterator"),            
            pair("java.util.ListIterator boolean hasPrevious()", "public abstract fun hasPrevious() : jet.Boolean defined in jet.ListIterator"),            
            pair("java.util.ListIterator E next()", "public abstract fun next() : T defined in jet.ListIterator"),            
            pair("java.util.ListIterator int nextIndex()", "public abstract fun nextIndex() : jet.Int defined in jet.ListIterator"),            
            pair("java.util.ListIterator E previous()", "public abstract fun previous() : T defined in jet.ListIterator"),            
            pair("java.util.ListIterator int previousIndex()", "public abstract fun previousIndex() : jet.Int defined in jet.ListIterator")            
        );
        
        put(b, "java.util.ListIterator", "MutableListIterator",
            pair("java.util.ListIterator void add(E)", "public abstract fun add(e : T) : jet.Unit defined in jet.MutableListIterator"),            
            pair("java.util.ListIterator boolean hasNext()", "public abstract fun hasNext() : jet.Boolean defined in jet.MutableListIterator"),            
            pair("java.util.ListIterator boolean hasPrevious()", "public abstract fun hasPrevious() : jet.Boolean defined in jet.MutableListIterator"),            
            pair("java.util.ListIterator E next()", "public abstract fun next() : T defined in jet.MutableListIterator"),            
            pair("java.util.ListIterator int nextIndex()", "public abstract fun nextIndex() : jet.Int defined in jet.MutableListIterator"),            
            pair("java.util.ListIterator E previous()", "public abstract fun previous() : T defined in jet.MutableListIterator"),            
            pair("java.util.ListIterator int previousIndex()", "public abstract fun previousIndex() : jet.Int defined in jet.MutableListIterator"),            
            pair("java.util.ListIterator void remove()", "public abstract fun remove() : jet.Unit defined in jet.MutableListIterator"),            
            pair("java.util.ListIterator void set(E)", "public abstract fun set(e : T) : jet.Unit defined in jet.MutableListIterator")            
        );
        
        map = b.build();
    }
}
