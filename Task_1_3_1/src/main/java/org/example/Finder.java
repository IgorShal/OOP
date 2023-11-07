package org.example;


import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;


public class Finder {

    private String subString;
    private int[] prefixArr;
    private Reader reader;
    private int currentIndexInStr;
    private Charset encoding;

    public Finder(String filename, String subString, fileType filetype) throws IOException {
        this.encoding = StandardCharsets.UTF_8;
        byte[] bytes = subString.getBytes("windows-1251");
        this.subString = new String(bytes, StandardCharsets.UTF_8);
        this.prefixArr = new int[this.subString.length()];
        this.currentIndexInStr = 0;
        openFile(filename, filetype);
    }

    public ArrayList<Integer> find() throws IOException {
        this.prefixArr[0] = 0;
        int len = this.subString.length();
        for (int i = 1; i < len; i++) {
            int j = this.prefixArr[i - 1];
            while (j > 0 && this.subString.charAt(i) != this.subString.charAt(j)) {
                j = this.prefixArr[j - 1];
            }
            if (this.subString.charAt(i) == this.subString.charAt(j)) {
                j++;
            }
            this.prefixArr[i] = j;
        }
        int k = 0;
        int strIndex = 0;
        String currentString;
        ArrayList<Integer> result = new ArrayList<>();
        while (true) {
            try {
                currentString = new String(getElementFromFile());
            } catch (EOFException e) {
                return result;
            }

            for (int i = 0; i < currentString.length(); i++) {
                strIndex++;
                while ((k > 0) && currentString.charAt(i) != this.subString.charAt(k)) {
                    k = this.prefixArr[k - 1];

                }
                if (currentString.charAt(i) == this.subString.charAt(k)) {
                    k++;
                }
                if (k == len) {
                    k = 0;
                    result.add(strIndex - len);
                }
            }

        }


    }

    private void openFile(String filename, fileType filetype) throws IOException {

        File file = new File(filename);
        InputStream in;
        if (filetype == fileType.resourse) {
            in = getClass().getClassLoader().getResourceAsStream(filename);
        } else {
            in = new FileInputStream(file);
        }

        this.reader = new InputStreamReader(in, this.encoding);


    }

    private char[] getElementFromFile() throws IOException {
        char[] buffer = new char[100000];
        int count = this.reader.read(buffer);

        if (count == -1)
            throw new EOFException();
        char[] res = new char[count];
        java.lang.System.arraycopy(buffer, 0, res, 0, count);
        return res;
    }

    public enum fileType {

        file,
        resourse

    }
}