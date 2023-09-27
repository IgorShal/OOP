package org.example;

import java.util.Arrays;
import java.util.Objects;

/**
 * Класс полиномов.
 */
public class Polynomial {
    private final double[] coefs;
    private final int length;

    /**
     * Создание элемента класса полиномов для даблов.
     */
    public Polynomial(double[] arr) {

        coefs = arr.clone();
        length = arr.length;
    }

    /**
     * Создание элемента класса полиномов для интов.
     */
    public Polynomial(int[] arr) {

        coefs = Arrays.stream(arr).asDoubleStream().toArray();
        length = arr.length;
    }

    /**
     * Сумма двух полиномов.
     */
    public Polynomial plus(Polynomial p2) {
        double[] resCoefs;
        if (length > p2.length) {
            resCoefs = coefs.clone();
            for (int i = 0; i < p2.length; i++) {
                resCoefs[length - i - 1] = p2.coefs[p2.length - i - 1] + coefs[length - i - 1];
            }
        } else {
            resCoefs = p2.coefs.clone();
            for (int i = 0; i < length; i++) {
                resCoefs[p2.length - i - 1] = p2.coefs[p2.length - i - 1] + coefs[length - i - 1];
            }
        }
        Polynomial res = new Polynomial(resCoefs);
        return res.reduce();
    }

    /**
     * Разность.
     */
    public Polynomial minus(Polynomial p2) {
        Polynomial p3 = new Polynomial(p2.coefs.clone());
        for (int i = 0; i < p2.length; i++) {
            p3.coefs[i] = -p2.coefs[i];
        }
        p3 = this.plus(p3);
        return p3.reduce();


    }

    /**
     * Значение в точке.
     */
    public double evaluate(double point) {
        double res = 0;
        for (int i = 0; i < length; i++) {
            res += (coefs[length - i - 1] * Math.pow(point, i));
        }
        return res;
    }

    /**
     * Сравнение на равенство.
     */
    @Override
    public boolean equals(Object o) {
        if (Objects.isNull(o)) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Polynomial p2 = (Polynomial) o;
        if (length != p2.length) {
            return false;
        } else {
            for (int i = 0; i < length; i++) {
                if (coefs[i] != p2.coefs[i]) {
                    return false;
                }

            }
        }
        return true;
    }

    /**
     * Получаем хеш код класса.
     */
    @Override
    public int hashCode() {
        int prime = 31;
        return prime * Objects.hash(Arrays.hashCode(coefs), length);
    }

    /**
     * Уменьшает размер полинома вида {0,0,....,0,x1,x2,...,xn} до {x1,x2,....,xn}
     */
    public Polynomial reduce() {

        int size = 0;
        double[] arr = new double[0];
        for (int i = 0; i < this.length; i++) {
            if (this.coefs[i] != 0 && size == 0) {
                size = this.length - i;
                arr = new double[size];
            }
            if (size != 0) {
                arr[i - (this.length - size)] = this.coefs[i];
            }
        }
        if (size == 0) {
            return new Polynomial(new double[]{0});
        } else {
            return new Polynomial(arr);
        }
    }

    /**
     * Взятие i-ой производной.Если и больше n,то 0.
     */
    public Polynomial differentiate(int k) {
        double[] newCoefs = coefs.clone();
        for (int i = 0; (i < k && i < length - 1); i++) {
            int curLength = length - i - 1;
            double[] arr = new double[curLength];

            for (int j = 0; j < curLength; j++) {
                arr[curLength - j - 1] = newCoefs[curLength - j - 1] * (j + 1);
            }
            newCoefs = arr.clone();
        }
        if (k >= length) {
            return new Polynomial(new double[]{0});
        }
        return new Polynomial(newCoefs);
    }

    /**
     * Перевод в строку.
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        for (int i = length - 1; i >= 2; i--) {
            if (Math.abs(coefs[length - 1 - i]) > 0.0000001 && (!(sb.length() == 0))) {
                if (coefs[length - 1 - i] > 0) {
                    sb.append(" + ");
                    if (Math.abs(coefs[length - 1 - i]) > 1.00000001) {
                        sb.append(coefs[length - 1 - i]);
                        sb.append("x^");
                        sb.append(i);
                    } else {
                        sb.append("x^");
                        sb.append(i);
                    }

                } else {
                    sb.append(" + ");
                    if (Math.abs(coefs[length - 1 - i]) > 1.00000001) {
                        sb.append((-coefs[length - 1 - i]));
                        sb.append("x^");
                        sb.append(i);
                    } else {
                        sb.append("x^");
                        sb.append(i);
                    }

                }

            } else if (Math.abs(coefs[length - 1 - i]) > 0.0000001) {
                if (Math.abs(coefs[length - 1 - i]) > 1.00000001) {
                    sb.append(coefs[length - 1 - i]);
                    sb.append("x^");
                    sb.append(i);
                } else {
                    sb.append("x^");
                    sb.append(i);
                }
            }
        }
        if ((length > 1) && (!(sb.length() == 0)) && Math.abs(coefs[length - 2]) > 0.0000001) {
            if (coefs[length - 2] > 0) {
                sb.append(" + ");
                if (Math.abs(coefs[length - 2]) > 1.00000001) {
                    sb.append(coefs[length - 2]);
                    sb.append("x");
                } else {
                    sb.append("x");
                }
            } else {
                sb.append(" - ");
                if (Math.abs(coefs[length - 2]) > 1.00000001) {
                    sb.append(-coefs[length - 2]);
                    sb.append("x");
                } else {
                    sb.append("x");
                }

            }
        } else if (length > 1 && Math.abs(coefs[length - 2]) > 0.0000001) {
            if (Math.abs(coefs[length - 2]) > 1.00000001) {
                sb.append(coefs[length - 2]);
                sb.append("x");
            } else {
                sb.append("x");
            }
        }
        if ((Math.abs(coefs[length - 1]) > 0.0000001) && (!(sb.length() == 0))) {
            if (coefs[length - 1] > 0) {
                sb.append(" + ");
                sb.append(coefs[length - 1]);
            } else {
                sb.append(" - ");
                sb.append(-coefs[length - 1]);
            }

        } else if ((sb.length() == 0)) {
            sb.append(coefs[length - 1]);
        }
        return sb.toString();
    }

    /**
     * Произведение многочленов.
     */
    public Polynomial times(Polynomial p2) {
        int len = length + p2.length - 1;
        Polynomial res = new Polynomial(new double[len]);
        for (int i = 0; i < length; i++) {
            double[] arr = new double[len];
            for (int j = 0; j < p2.length; j++) {
                arr[i + j] = p2.coefs[j] * coefs[i];
            }
            Polynomial cur = new Polynomial(arr);
            res = res.plus(cur);
        }
        res = res.reduce();
        return res;
    }

    /**
     * Main функция.
     */
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(new int[]{7, 6, 3, 4});
        System.out.println(p1.plus(p1.differentiate(1)).toString());
    }
}