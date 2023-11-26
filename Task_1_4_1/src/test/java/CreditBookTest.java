import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.example.CreditBook;
import org.example.Mark;
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
        creditBookForDiploma.addMark("Математика", Mark.MARKFOUR);
        creditBookForDiploma.addMark("Оси", Mark.MARKFIVE);
        creditBookForDiploma.addMark("ИИ", Mark.MARKFIVE);
        creditBookForDiploma.addMark("Модели", Mark.MARKPASSED);
        creditBookForDiploma.addMark("Физика", Mark.MARKFIVE);
        creditBookForDiploma.setQualWork(Mark.MARKFIVE);

        creditBookWithOneThree = new CreditBook("Цой", 2);
        creditBookWithOneThree.addMark("Информатика", Mark.MARKTHREE);
        creditBookWithOneThree.addMark("Английский", Mark.MARKFIVE);
        creditBookWithOneThree.addMark("Математика", Mark.MARKFOUR);
        creditBookWithOneThree.setQualWork(Mark.MARKFIVE);

        creditBookWithQualFour = new CreditBook("Власенко", 3);
        creditBookWithQualFour.addMark("Информатика", Mark.MARKFIVE);
        creditBookWithQualFour.addMark("ОСИ", Mark.MARKFIVE);
        creditBookWithQualFour.addMark("Литература", Mark.MARKFIVE);
        creditBookWithQualFour.addMark("Физика", Mark.MARKFOUR);
        creditBookWithQualFour.setQualWork(Mark.MARKFOUR);

        creditBookWithTwoSemesters = new CreditBook("Козлов", 4);
        creditBookWithTwoSemesters.addMark("История", Mark.MARKTHREE);
        creditBookWithTwoSemesters.addMark("Английский", Mark.MARKFIVE);
        creditBookWithTwoSemesters.addMark("ИИ", Mark.MARKFIVE);
        creditBookWithTwoSemesters.incSemesterNumber();
        creditBookWithTwoSemesters.addMark("Английский", Mark.MARKFIVE);
        creditBookWithTwoSemesters.addMark("ОСИ", Mark.MARKPASSED);
        creditBookWithTwoSemesters.addMark("ИИ", Mark.MARKFIVE);
        creditBookWithTwoSemesters.setQualWork(Mark.MARKFIVE);
    }

    /**
     * Тесты для метода addMark для двух разных зачетных книжек.
     */
    @Test
    public void testAddMark() throws Exception {
        creditBookForDiploma.addMark("История", Mark.MARKTHREE);
        assertEquals(Mark.MARKTHREE,
                creditBookForDiploma.getDiplomaMarkForLesson("История"));

        creditBookWithOneThree.addMark("История", Mark.MARKFIVE);
        assertEquals(Mark.MARKFIVE,
                creditBookWithOneThree.getDiplomaMarkForLesson("История"));
    }

    /**
     * Тест для проверки получения диплома, если оценка за квали не CreditBook.MARKFIVE.
     */
    @Test
    public void testNotPossibleDiplomaWhenMarkFour() throws Exception {
        creditBookForDiploma.setQualWork(Mark.MARKFOUR);
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

