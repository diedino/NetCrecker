package com.krasnov;

import java.util.*;

public class StringFilterImpl implements StringFilter {

    private interface Checker {
        boolean check(String s, String pattern);
    }

    private Set<String> set;

    public StringFilterImpl() {
        this.set = new HashSet<>();
    }
    @Override
    public void add(String s) {
        set.add(s == null? null : s.toLowerCase());
    }

    @Override
    public boolean remove(String s) {
        if (set.contains(s.toLowerCase())) {
            set.remove(s);
            return true;
        }
        return false;
    }

    @Override
    public void removeAll() {
        set.clear();
    }

    @Override
    public Collection<String> getCollection() {
        return set;
    }

    @Override
    public Iterator<String> getStringsContaining(String chars) {
        Checker checker = new Checker() {
            @Override
            public boolean check(String s, String pattern) {
                return s!=null && s.contains(pattern.toLowerCase());
            }
        };
        return filter(chars, checker);
    }

    @Override
    public Iterator<String> getStringsStartingWith(String begin) {
        Checker checker = new Checker() {
            @Override
            public boolean check(String s, String pattern) {
                return s!=null && s.startsWith(pattern);
            }
        };
        return filter(begin, checker);
    }

    @Override
    public Iterator<String> getStringsByNumberFormat(String format) {
        return null;
    }

    @Override
    public Iterator<String> getStringsByPattern(String pattern) {
        return null;
    }

    private Iterator<String> filter(String pattern, Checker checker){
        if (pattern == null || pattern.equals("")) {
            return set.iterator();
        }
        String template = pattern.toLowerCase();
        ArrayList<String> tmpArray = new ArrayList<>();
        for (String s : set) {
            if (checker.check(s, template)) {
                tmpArray.add(s);
            }
        }
        return tmpArray.iterator();
    }
}
