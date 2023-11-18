package org.example;

public class Main {
    public static void main(String[] args) throws Exception {
        CreditBook book = new CreditBook("Igor Shalygin", 1);
        book.addMark("OS1", 5);
        book.addMark("OS2", 5);
        book.addMark("OS3", 5);
        book.addMark("OS4", 5);
        book.addMark("OS3", 5);
        book.addMark("OS6", 5);
        book.setQualWork(5);
        System.out.println(book.isDiplomaPossible());
        System.out.println(book.getAverageForAllTime());
        System.out.println(book.getAverageForAllTime());
        System.out.println(book.isIncreasedScholarship());
    }
}