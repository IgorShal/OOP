package org.example;

/**
 * Класс полиномов.
 */
public class Polynomial {
    private final int[] coefs;
    private final int length;

    /**
     * Создание элемента класса полиномов.
     */
    public Polynomial(int[] arr) {
        coefs = arr;
        length = arr.length;
    }

    /**
     * Сумма двух полиномов.
     */
    public Polynomial plus(Polynomial p2) {
        int[] resultCoefs;
        if (length > p2.length) {
            resultCoefs = coefs.clone();
            for (int i = 0; i < p2.length; i++) {
                resultCoefs[length - i - 1] = p2.coefs[p2.length - i - 1] + coefs[length - i - 1];
            }
        } else {
            resultCoefs = p2.coefs.clone();
            for (int i = 0; i < length; i++) {
                resultCoefs[p2.length - i - 1] = p2.coefs[p2.length - i - 1] + coefs[length - i - 1];
            }
        }
        return new Polynomial(resultCoefs);
    }

    /**
     * Разность.
     */
    public Polynomial minus(Polynomial p2) {
        Polynomial P3 = new Polynomial(p2.coefs.clone());
        for (int i = 0; i < p2.length; i++) {
            P3.coefs[i] = -p2.coefs[i];
        }
        P3 = this.plus(P3);
        return P3;
    }

    /**
     * Значение в точке.
     */
    public double evaluate(double point) {
        double res = 0;
        for (int i = 0; i < length; i++) {
            res += ((double) coefs[length - i - 1] * Math.pow(point, i));
        }
        return res;
    }

    /**
     * Сравнение на равенство.
     */
    public boolean equal(Polynomial p2) {
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
     * Взятие i-ой производной.
     */
    public Polynomial differentiate(int k) {
        int[] newCoefs = coefs.clone();
        for (int i = 0; (i < k && i < length - 1); i++) {
            int curLength = length - i - 1;
            int[] arr = new int[curLength];

            for (int j = 0; j < curLength; j++) {
                arr[curLength - j - 1] = newCoefs[curLength - j - 1] * (j + 1);
            }
            newCoefs = arr.clone();
        }
        if (k >= length) {
            return new Polynomial(new int[]{0});
        }
        return new Polynomial(newCoefs);
    }

    /**
     * Перевод в строку.
     */
    public String toString() {
        String result = "";
        for (int i = length - 1; i >= 2; i--) {
            if (coefs[length - 1 - i] != 0 && (!result.isEmpty())) {
                if (coefs[length - 1 - i] > 0) {
                    result = result.concat(" + ");
                    if (coefs[length - 1 - i] != 1) {
                        result += (coefs[length - 1 - i] + "x^" + i);
                    } else {
                        result += ("x^" + i);
                    }

                } else {
                    result = result.concat(" + ");
                    if ((-coefs[length - 1 - i]) != 1) {
                        result += ((-coefs[length - 1 - i]) + "x^" + i);
                    } else {
                        result += ("x^" + i);
                    }

                }

            } else if (coefs[length - 1 - i] != 0) {
                if (coefs[length - 1 - i] != 1) {
                    result = result.concat(coefs[length - 1 - i] + "x^" + i);
                } else {
                    result = result.concat("x^" + i);
                }
            }
        }
        if ((length > 1) && (!result.isEmpty()) && coefs[length - 2] != 0) {
            if (coefs[length - 2] > 0) {
                result += " + ";
                if (coefs[length - 2] != 1) {
                    result += (coefs[length - 2] + "x");
                } else {
                    result += ("x");
                }
            } else {
                result += " - ";
                if ((-coefs[length - 2]) != 1) {
                    result += ((-coefs[length - 2]) + "x");
                } else {
                    result += ("x");
                }

            }
        } else if (length > 1 && coefs[length - 2] != 0) {
            if (coefs[length - 2] != 1) {
                result += (coefs[length - 2] + "x");
            } else {
                result += ("x");
            }
        }
        if ((coefs[length - 1] != 0) && (!result.isEmpty())) {
            if (coefs[length - 1] > 0) {
                result += " + ";
                result += coefs[length - 1];
            } else {
                result += " - ";
                result += (-coefs[length - 1]);
            }

        } else if (result.isEmpty()) {
            result += coefs[length - 1];
        }
        return result;
    }

    /**
     * Произведение многочленов.
     */
    public Polynomial times(Polynomial p2) {
        int len = length + p2.length - 1;
        Polynomial res = new Polynomial(new int[len]);
        for (int i = 0; i < length; i++) {
            int[] arr = new int[len];
            for (int j = 0; j < p2.length; j++) {
                arr[i + j] = p2.coefs[j] * coefs[i];
            }
            Polynomial cur = new Polynomial(arr);
            res = res.plus(cur);
        }
        return res;
    }

    /**
     * Main функция.
     */
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(new int[]{7, 6, 3, 4});
        Polynomial p2 = new Polynomial(new int[]{8, 2, 3});
        System.out.println(p1.plus(p2.differentiate(1)).toString());
        System.out.println(p1.times(p2).evaluate(2));
    }
}