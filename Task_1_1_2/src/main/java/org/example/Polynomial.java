package org.example;

/**
 * Класс полиномов.
 */
public class Polynomial {
    private final int[] coefs;
    private final int length;

    /**
     * Создание элемента класса полиномов
     */
    public Polynomial(int[] arr) {
        coefs = arr;
        length = arr.length;
    }

    /**
     * Сумма двух полиномов
     */
    public Polynomial plus(Polynomial P2) {
        int[] result_coefs;
        if (length > P2.length) {
            result_coefs = coefs.clone();
            for (int i = 0; i < P2.length; i++) {
                result_coefs[length - i - 1] = P2.coefs[P2.length - i - 1] + coefs[length - i - 1];
            }
        } else {
            result_coefs = P2.coefs.clone();
            for (int i = 0; i < length; i++) {
                result_coefs[P2.length - i - 1] = P2.coefs[P2.length - i - 1] + coefs[length - i - 1];
            }
        }
        return new Polynomial(result_coefs);
    }

    /**
     * Разность.
     */
    public Polynomial minus(Polynomial P2) {
        Polynomial P3 = new Polynomial(P2.coefs.clone());
        for (int i = 0; i < P2.length; i++) {
            P3.coefs[i] = -P2.coefs[i];
        }
        P3 = this.plus(P3);
        return P3;
    }

    /**
     * Значение в точке
     */
    public double evaluate(double point) {
        double res = 0;
        for (int i = 0; i < length; i++) {
            res += ((double) coefs[length - i - 1] * Math.pow(point, i));
        }
        return res;
    }

    /**
     * Сравнение на равенство
     */
    public boolean equal(Polynomial P2) {
        if (length != P2.length)
            return false;
        else {
            for (int i = 0; i < length; i++) {
                if (coefs[i] != P2.coefs[i])
                    return false;
            }
        }
        return true;
    }

    /**
     * Взятие i-ой производной
     */
    public Polynomial differentiate(int k) {
        int[] new_coefs = coefs.clone();
        for (int i = 0; (i < k && i < length - 1); i++) {
            int cur_length = length - i - 1;
            int[] arr = new int[cur_length];

            for (int j = 0; j < cur_length; j++) {
                arr[cur_length - j - 1] = new_coefs[cur_length - j - 1] * (j + 1);
            }
            new_coefs = arr.clone();
        }
        if (k >= length) {
            return new Polynomial(new int[]{0});
        }
        return new Polynomial(new_coefs);
    }

    /**
     * Перевод в строку
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
     * Произведение многочленов
     */
    public Polynomial times(Polynomial P2) {
        int len = length + P2.length - 1;
        Polynomial res = new Polynomial(new int[len]);
        for (int i = 0; i < length; i++) {
            int[] arr = new int[len];
            for (int j = 0; j < P2.length; j++) {
                arr[i + j] = P2.coefs[j] * coefs[i];
            }
            Polynomial cur = new Polynomial(arr);
            res = res.plus(cur);
        }
        return res;
    }

    /**
     * Main функция
     */
    public static void main(String[] args) {
        Polynomial p1 = new Polynomial(new int[]{7, 6, 3, 4});
        Polynomial p2 = new Polynomial(new int[]{8, 2, 3});
        System.out.println(p1.plus(p2.differentiate(1)).toString());
        System.out.println(p1.times(p2).evaluate(2));
    }
}