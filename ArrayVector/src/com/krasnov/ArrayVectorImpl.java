package com.krasnov;

public class ArrayVectorImpl implements ArrayVector {

    double[] arr;
    int length;
    public ArrayVectorImpl() {

    }

    @Override
    public void set(double... elements) {
        arr = new double[elements.length];
        length = elements.length;
        for (int i=0; i<elements.length; i++) {
            arr[i]=elements[i];
        }
    }

    @Override
    public double[] get() {
        return arr;
    }

    @Override
    public ArrayVector clone() {
        ArrayVectorImpl nw = new ArrayVectorImpl();
        nw.set(arr);
        return nw;
    }

    @Override
    public int getSize() {
        return length;
    }

    @Override
    public void set(int index, double value) {
        if (index<0)
            return;
        if (index>length-1) {
            double[] nwarr = new double[index];
            for (int i=0; i<length; i++) {
                nwarr[i] = arr[i];
            }
            arr = new double[index+1];
            for (int i=0; i<length; i++) {
                arr[i] = nwarr[i];
            }
            arr[index] = value;
            length = index+1;
        }
        else {
            arr[index] = value;
        }
    }

    @Override
    public double get(int index) throws ArrayIndexOutOfBoundsException {
        if (index > length-1) throw new ArrayIndexOutOfBoundsException();
        return arr[index];
    }

    @Override
    public double getMax() {
        double max = arr[0];
        for (int i=1; i<length; i++) {
            if (arr[i]>max)
                max = arr[i];
        }
        return max;
    }

    @Override
    public double getMin() {
        double min = arr[0];
        for (int i=1; i<length; i++) {
            if (arr[i]<min)
                min = arr[i];
        }
        return min;
    }

    @Override
    public void sortAscending() {
        for (int i=0; i<length; i++) {
            for (int j=0; j<length; j++) {
                if (arr[j]>arr[i]) {
                    double swap = arr[i];
                    arr[i]=arr[j];
                    arr[j]=swap;
                }
            }
        }
    }

    @Override
    public void mult(double factor) {
        for(int i =0; i<length; i++) {
            arr[i] *= factor;
        }
    }

    @Override
    public ArrayVector sum(ArrayVector anotherVector) {
        if (length>anotherVector.getSize()) {
            for (int i=0; i<anotherVector.getSize(); i++) {
                arr[i] +=anotherVector.get(i);
            }
        }
        else {
            for (int i=0; i<length; i++) {
                arr[i] +=anotherVector.get(i);
            }
        }
        return this;
    }

    @Override
    public double scalarMult(ArrayVector anotherVector) {
        double mult=0;
        if (length>anotherVector.getSize()) {
            for (int i=0; i<anotherVector.getSize(); i++) {
                mult += arr[i] * anotherVector.get(i);
            }
        }
        else {
            for (int i=0; i<length; i++) {
                mult += arr[i] * anotherVector.get(i);
            }
        }
        return mult;
    }

    @Override
    public double getNorm() {
        double sum = 0;
        for (double a:arr
             ) {
            sum+=a*a;
        }
        return Math.sqrt(sum);
    }
}
