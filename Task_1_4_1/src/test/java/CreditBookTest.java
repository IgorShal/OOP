import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.CreditBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Тесты для класса CreditBook.
 */
public class CreditBookTest {
    private CreditBook creditBookForDiploma;
    private CreditBook creditBookWithOneThree;
    private CreditBook creditBookWithQualFour;
    private CreditBook creditBookWithTwoSemesters;

    /**
     * Настройка объектов CreditBook для каждого теста.
     */
    @BeforeEach
    public void setUp() throws Exception {
        creditBookForDiploma = new CreditBook("Шалыгин", 1);
        creditBookForDiploma.addMark("Математика", CreditBook.Mark.markFour);
        creditBookForDiploma.addMark("Оси", CreditBook.Mark.markFive);
        creditBookForDiploma.addMark("ИИ", CreditBook.Mark.markFive);
        creditBookForDiploma.addMark("Модели", CreditBook.Mark.markPassed);
        creditBookForDiploma.addMark("Физика", CreditBook.Mark.markFive);
        creditBookForDiploma.setQualWork(CreditBook.Mark.markFive);

        creditBookWithOneThree = new CreditBook("Цой", 2);
        creditBookWithOneThree.addMark("Информатика", CreditBook.Mark.markThree);
        creditBookWithOneThree.addMark("Английский", CreditBook.Mark.markFive);
        creditBookWithOneThree.addMark("Математика", CreditBook.Mark.markFour);
        creditBookWithOneThree.setQualWork(CreditBook.Mark.markFive);

        creditBookWithQualFour = new CreditBook("Власенко", 3);
        creditBookWithQualFour.addMark("Информатика", CreditBook.Mark.markFive);
        creditBookWithQualFour.addMark("ОСИ", CreditBook.Mark.markFive);
        creditBookWithQualFour.addMark("Литература", CreditBook.Mark.markFive);
        creditBookWithQualFour.addMark("Физика", CreditBook.Mark.markFour);
        creditBookWithQualFour.setQualWork(CreditBook.Mark.markFour);

        creditBookWithTwoSemesters = new CreditBook("Козлов", 4);
        creditBookWithTwoSemesters.addMark("История", CreditBook.Mark.markThree);
        creditBookWithTwoSemesters.addMark("Английский", CreditBook.Mark.markFive);
        creditBookWithTwoSemesters.addMark("ИИ", CreditBook.Mark.markFive);
        creditBookWithTwoSemesters.incSemesterNumber();
        creditBookWithTwoSemesters.addMark("Английский", CreditBook.Mark.markFive);
        creditBookWithTwoSemesters.addMark("ОСИ", CreditBook.Mark.markPassed);
        creditBookWithTwoSemesters.addMark("ИИ", CreditBook.Mark.markFive);
        creditBookWithTwoSemesters.setQualWork(CreditBook.Mark.markFive);
    }

    /**
     * Тесты для метода addMark для двух разных зачетных книжек.
     */
    @Test
    public void testAddMark() throws Exception {
        creditBookForDiploma.addMark("История", CreditBook.Mark.markThree);
        assertEquals(CreditBook.Mark.markThree, creditBookForDiploma.getDiplomaMarkForLesson("История"));

        creditBookWithOneThree.addMark("История", CreditBook.Mark.markFive);
        assertEquals(CreditBook.Mark.markFive, creditBookWithOneThree.getDiplomaMarkForLesson("История"));
    }

    /**
     * Тест для проверки получения диплома, если оценка за квали не CreditBook.Mark.markFive.
     */
    @Test
    public void testNotPossibleDiplomaWhenMarkFour() throws Exception {
        creditBookForDiploma.setQualWork(CreditBook.Mark.markFour);
        assertFalse(creditBookForDiploma.isDiplomaPossible());
    }

    /**
     * Тест для метода getDiplomaMarkForLesson при отсутствии оценки.
     */
    @Test
    public void testGetExceptionForNotExistentLesson() throws Exception {
        assertThrowsExactly(Exception.class, () -> {
            creditBookForDiploma.getDiplomaMarkForLesson("Астрономия");
        });


    }

    /**
     * Тест для метода getAverageForAllTime для четырех разных зачетных книжек.
     */
    @Test
    public void testGetAverageForAllTimeForMultipleCreditBooks() throws Exception {
        assertEquals(creditBookForDiploma.getAverageForAllTime(), 4.75);
        assertEquals(creditBookWithOneThree.getAverageForAllTime(), 4.0);
        assertEquals(creditBookWithQualFour.getAverageForAllTime(), 4.75);
        assertEquals(creditBookWithTwoSemesters.getAverageForAllTime(), 4.66, 0.1);
    }

    /**
     * Тест для метода isDiplomaPossible для четырех разных зачетных книжек.
     */
    @Test
    public void testIsDiplomaPossibleForMultipleCreditBooks() throws Exception {
        assertTrue(creditBookForDiploma.isDiplomaPossible());
        assertFalse(creditBookWithOneThree.isDiplomaPossible());
        assertFalse(creditBookWithQualFour.isDiplomaPossible());
        assertFalse(creditBookWithTwoSemesters.isDiplomaPossible());
    }

    /**
     * Тест для метода isIncreasedScholarship для четырех разных зачетных книжек.
     */
    @Test
    public void testIsIncreasedScholarshipForMultipleCreditBooks() {
        assertFalse(creditBookForDiploma.isIncreasedScholarship());
        assertFalse(creditBookWithOneThree.isIncreasedScholarship());
        assertFalse(creditBookWithQualFour.isIncreasedScholarship());
        assertTrue(creditBookWithTwoSemesters.isIncreasedScholarship());
    }

}

