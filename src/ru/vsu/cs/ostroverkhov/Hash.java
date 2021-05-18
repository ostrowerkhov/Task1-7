package ru.vsu.cs.ostroverkhov;

import ru.vsu.cs.ostroverkhov.utils.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hash {
    public static final int DEFAULT_START_SIZE = 16;

    private class Element {
        int count = 0;
        String key;
        String[] values = new String[DEFAULT_START_SIZE];
        Element (String key, String value) {
            this.key = key;
            this.values[0] = value;
            count++;
        }
    }

    private Element[] elements;
    private int count = 0;

    public Hash(int startSize) {
        elements = new Element[startSize];
    }

    public Hash() {
        this(DEFAULT_START_SIZE);
    }

    public void addElement(String key, String value) throws Exception {
        if (key == null || value == null) {
            throw new Exception("Key or Value is null");
        }
        int position = -1;
        for (int i = 0; i < count; i++) {
            if(elements[i].key.equals(key)) {
                position = i;
            }
        }
        if (count == elements.length) {
            elements = Arrays.copyOf(elements, count * 2);
        }
        if(position == -1) {
            Element element = new Element(key, value);
            elements[count] = element;
            count++;
        } else {
            if (elements[position].count >= elements[position].values.length) {
                elements[position].values = Arrays.copyOf(elements[position].values, elements[position].count * 2);
            }
            elements[position].values[elements[position].count] = value;
            elements[position].count++;
        }
    }

    public void print() {
        for (int i = 0; i < count; i++) {
            System.out.print("Key " + elements[i].key + " - ");
            for(int j = 0; j < elements[i].count; j++) {
                String org = j == elements[i].count - 1 ? "." : ", ";
                System.out.print(elements[i].values[j] + org);
            }
            System.out.println();
        }
    }

    public void clear() {
        if (count > 0) {
            for (int i = 0; i < count; i++) {
                elements[i].key = null;
                for(String s : elements[i].values) {
                    s = null;
                }
                elements[i].count = 0;
            }
            count = 0;
        }
    }

    public String[] getKeys() {
        String[] keys = new String[count];
        int i = 0;
        for(Element e : elements) {
            keys[i] = e.key;
            i++;
        }
        return keys;
    }

    public String[] getValues(String key) throws Exception {
        boolean ch = false;
        int i = 0;
        int pos = -1;
        for (Element el : elements) {
            if(el.key.equals(key)) {
                ch = true;
                pos = i;
            }
            i++;
        }
        if(pos == -1) {
            throw new Exception("Key not found");
        }
        return elements[pos].values;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public String[][] convertHashToArray() {
        ArrayList<String[]> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            String[] pare = new String[2];
            for(int j = 0; j < elements[i].count; j++) {
                pare[0] = elements[i].key;
                pare[1] = elements[i].values[j];
            }
            list.add(pare);
        }
        String[][] arr = new String[list.size()][2];
        int i = 0;
        for(String[] s : list) {
            arr[i][0] = s[0];
            arr[i][1] = s[1];
            i++;
        }
        return arr;
    }

    public String[] findOnValue(String value) throws Exception {
        if (value == null) {
            throw new Exception("Value is null");
        }
        List<String> keysN = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            for(int j = 0; j < elements[i].count; j++) {
                if(elements[i].values[j].equals(value)) {
                    keysN.add(elements[i].key);
                    break;
                }
            }
        }
        String[] arrKeysN = new String[keysN.size()];
        for (int i = 0; i < keysN.size(); i++) {
            arrKeysN[i] = keysN.get(i);
        }
        return arrKeysN;
    }

    public void removeKey(String key) {
        for(int i = 0; i < count; i++) {
            if(elements[i].key.equals(key)) {
                count--;
                for(int j = i; j < count; j++) {
                    elements[j] = elements[j + 1];
                }
                elements[count] = null;
            }
        }
    }

    public void removeValue(String value) {
        int n = count;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < elements[i].count; j++) {
                if (elements[i].values[j].equals(value)) {
                    removeKey(elements[i].key);
                    i--;
                    n--;
                    break;
                }
            }
        }
    }
}