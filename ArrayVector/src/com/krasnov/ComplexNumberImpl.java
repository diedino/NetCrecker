package com.krasnov;
import java.util.Arrays;
public class ComplexNumberImpl implements ComplexNumber {
    private double re, im;

    public ComplexNumberImpl() {
        re = 0;
        im = 0;
    }

    public ComplexNumberImpl(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public ComplexNumberImpl(String number) {
        String[] parsed = number.split("\\+");
        re = Double.parseDouble(parsed[0]);
        im = Double.parseDouble(parsed[1].substring(0, parsed[1].length() - 1));
    }

    @Override
    public double getRe() {
        return re;
    }

    @Override
    public double getIm() {
        return im;
    }

    @Override
    public boolean isReal() {
        return im == 0;
    }

    @Override
    public void set(double re, double im) {
        this.re = re;
        this.im = im;
    }

    @Override
    public void set(String value) throws NumberFormatException {

    }

    @Override
    public ComplexNumber copy() {
        ComplexNumberImpl impl = new ComplexNumberImpl();
        impl.set(re, im);
        return impl;
    }

    @Override
    public ComplexNumber clone() throws CloneNotSupportedException {
        ComplexNumberImpl cln = (ComplexNumberImpl) super.clone();
        cln.re = re;
        cln.im = im;
        return cln;
    }

    @Override
    public String toString() {
        if (im == 0 )
            return String.format("%s", re);
        else {
            if (re == 0) {
                return String.format("%si", im);
            }
        }
        if (im>0) return String.format("%s+%si", re, im);
        else return String.format("%s%si", re, im);
        //return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj==null) return false;
        if (getClass() != obj.getClass())
            return false;
        ComplexNumber impl = (ComplexNumber) obj;
        if (re == impl.getRe() && im == impl.getIm())
            return true;
        return false;
    }

    @Override
    public int compareTo(ComplexNumber other) {
        return Double.compare(re * re + im * im, other.getRe() * other.getRe() + other.getIm() * other.getIm());
    }

    @Override
    public void sort(ComplexNumber[] array) {
        Arrays.sort(array);
//        for (int i=0; i< array.length; i++) {
//            for (int j=0; j< array.length; j++) {
//                if (array[i].compareTo(array[j])>0) {
//                    ComplexNumber swap = array[i];
//                    array[i] = array[j];
//                    array[j] = swap;
//                }
//            }
//        }
    }

    @Override
    public ComplexNumber negate() {
        re *= -1;
        im *= -1;
        return this;
    }

    @Override
    public ComplexNumber add(ComplexNumber arg2) {
        re += arg2.getRe();
        im += arg2.getIm();
        return this;
    }

    @Override
    public ComplexNumber multiply(ComplexNumber arg2) {
        re = re * arg2.getRe() - im * arg2.getIm();
        im = re * arg2.getIm() + arg2.getRe() * arg2.getIm();
        return this;
    }
}
