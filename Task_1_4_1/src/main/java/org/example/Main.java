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
        book.addMark("OS1", 5);
        book.setQualWork(5);
        System.out.println(book.isDiplomaPossible());
    }
}