import org.example.CreditBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

/**
 * Тесты для класса CreditBook.
 */
public class CreditBookTest {
    private CreditBook creditBook1;
    private CreditBook creditBook2;
    private CreditBook creditBook3;
    private CreditBook creditBook4;

    /**
     * Настройка объектов CreditBook для каждого теста.
     */
    @BeforeEach
    public void setUp() throws Exception {
        creditBook1 = new CreditBook("Шалыгин", 1);
        creditBook1.addMark("Математика", 4);
        creditBook1.addMark("Оси", 5);
        creditBook1.addMark("ИИ", 5);
        creditBook1.addMark("Физика", 5);
        creditBook1.setQualWork(5);

        creditBook2 = new CreditBook("Цой", 2);
        creditBook2.addMark("Информатика", 3);
        creditBook2.addMark("Английский", 5);
        creditBook2.addMark("Математика", 4);
        creditBook2.setQualWork(4);

        creditBook3 = new CreditBook("Власенко", 3);
        creditBook3.addMark("Информатика", 5);
        creditBook3.addMark("Физика", 4);
        creditBook3.setQualWork(4);

        creditBook4 = new CreditBook("Козлов", 4);
        creditBook4.addMark("История", 3);
        creditBook4.addMark("Английский", 5);
        creditBook4.addMark("ИИ", 5);
        creditBook4.incSemesterNumber();
        creditBook4.addMark("Английский", 5);
        creditBook4.addMark("ИИ", 5);
        creditBook4.setQualWork(5);
    }

    /**
     * Тесты для метода addMark для двух разных зачетных книжек.
     */
    @Test
    public void testAddMark() throws Exception {
        creditBook1.addMark("История", 3);
        assertEquals(3, creditBook1.getDiplomaMarkForLesson("История"));

        creditBook2.addMark("История", 5);
        assertEquals(5, creditBook2.getDiplomaMarkForLesson("История"));
    }

    /**
     * Тест для проверки получения диплома, если оценка за квали не 5.
     */
    @Test
    public void testSetValidQualWork() throws Exception {
        creditBook1.setQualWork(4);

        assertFalse(creditBook1.isDiplomaPossible());
    }

    /**
     * Тест для метода getDiplomaMarkForLesson при отсутствии оценки.
     */
    @Test
    public void testGetDiplomaMarkForNonExistentLesson() throws Exception {
        assertThrowsExactly(Exception.class, () -> {
            creditBook1.getDiplomaMarkForLesson("Астрономия");
        });


    }

    /**
     * Тест для метода getAverageForLesson с пустой зачетной книжкой.
     */
    @Test
    public void testGetAverageForLessonWithEmptyCreditBook() throws Exception {
        CreditBook emptyCreditBook = new CreditBook("Сидоров", 3);
        assertThrowsExactly(Exception.class, () -> {
            emptyCreditBook.getAverageForLesson("Математика");
        });

    }

    /**
     * Тест для метода isDiplomaPossible для четырех разных зачетных книжек.
     */
    @Test
    public void testIsDiplomaPossibleForMultipleCreditBooks() throws Exception {
        assertTrue(creditBook1.isDiplomaPossible());
        assertFalse(creditBook2.isDiplomaPossible());
        assertFalse(creditBook3.isDiplomaPossible());
        assertFalse(creditBook4.isDiplomaPossible());
    }

    /**
     * Тест для метода isIncreasedScholarship для четырех разных зачетных книжек.
     */
    @Test
    public void testIsIncreasedScholarshipForMultipleCreditBooks() {
        assertFalse(creditBook1.isIncreasedScholarship());
        assertFalse(creditBook2.isIncreasedScholarship());
        assertFalse(creditBook3.isIncreasedScholarship());
        assertTrue(creditBook4.isIncreasedScholarship());
    }

    /**
     * Тест для метода getAverageForAllTime для четырех разных зачетных книжек.
     */
    @Test
    public void testAverageAllTimeAvg() {
        assertEquals(creditBook1.getAverageForAllTime(), 4.75, 0.01);
        assertEquals(creditBook2.getAverageForAllTime(), 4, 0.01);
        assertEquals(creditBook3.getAverageForAllTime(), 4.5, 0.01);
        assertEquals(creditBook4.getAverageForAllTime(), 4.6, 0.01);

    }
}

