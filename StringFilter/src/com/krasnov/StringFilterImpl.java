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
        Checker checker = new Checker() {
            @Override
            public boolean check(String s, String pattern) {
                if (s == null || s.equals("") || s.length()!=pattern.length())
                    return false;
                for (int i = 0; i < s.length(); i++){
                    if (pattern.charAt(i) == '#') {
                        if (!Character.isDigit(s.charAt(i))) {
                            return false;
                        }
                    } else if (pattern.charAt(i) != s.charAt(i)) {
                        return false;
                    }
                }
                return true;
            }
        };
        return filter(format, checker);
    }

    @Override
    public Iterator<String> getStringsByPattern(String pattern) {
        Checker checker = new Checker() {
            @Override
            public boolean check(String s, String pattern) {
                if (s==null)
                    return false;
                String tmpPattern = pattern;
                String tmpString = s;
                int index;
                boolean first = false;
                while ((index = tmpPattern.indexOf("*")) != -1) {
                    if (index == 0) {
                        tmpPattern = tmpPattern.substring(1);
                        first = true;
                        continue;
                    }
                    int tmpIndex = tmpString
                            .indexOf(tmpPattern.substring(0, index));
                    if (tmpIndex == -1
                            || (!first && tmpIndex != 0)) {
                        return false;
                    }
                    tmpString = tmpString.substring(tmpIndex + index);
                    tmpPattern = tmpPattern.substring(index);
                    first = false;
                }
                return (first && new StringBuilder(tmpString).reverse().toString()
                        .startsWith(new StringBuilder(tmpPattern).reverse().toString()))
                        || tmpString.equals(tmpPattern);
            }
        };
        return filter(pattern, checker);
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
