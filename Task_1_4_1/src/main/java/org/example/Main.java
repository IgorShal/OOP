package org.example;

/**
 * Main.
 */
public class Main {
    /**
     * Main.
     */
    public static void main(String[] args) throws Exception {
        CreditBook book = new CreditBook("Igor Shalygin", 1);
        book.addMark("OS1", Mark.MARKFIVE);
        System.out.println(book.isDiplomaPossible());
    }
}