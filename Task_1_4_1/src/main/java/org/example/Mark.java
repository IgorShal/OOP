package org.example;

/**
 * Енум для оценок.
 */
public enum Mark {

    MARKFIVE(5),
    MARKFOUR(4),
    MARKTHREE(3),
    MARKTWO(2),
    MARKONE(1),
    MARKPASSED("Passed"),
    MARKNOTPASSED("Not passed");
    private int intMark;
    private String strMark;

    /**
     * Конструктор для числовых оценок.
     *
     * @param mark оценка.
     */
    Mark(int mark) {
        if (mark <= 0 || mark > 5) {
            System.out.println("This mark isn't allowed");
            return;
        }
        this.intMark = mark;
        this.strMark = "";
    }

    /**
     * Конструктор для зачёта и незачёт.
     *
     * @param mark Оценка.
     */
    Mark(String mark) {
        if (mark.equals("Passed") || mark.equals("Not passed")) {
            this.strMark = mark;

        } else {
            System.out.println("This mark isn't allowed");
            return;
        }
    }

    /**
     * Геттер оценки.
     */
    public int getIntMark() {
        return this.intMark;
    }

    /**
     * Геттер оценки.
     */
    public String getStrMark() {
        return this.strMark;
    }
}
