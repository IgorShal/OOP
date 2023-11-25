package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Класс зачетной книжки.
 * Давайте будем считать, что все предметы идут в диплом.
 * Также есть только оценки, зачетов и незачетов нет.
 */
public class CreditBook {
    private String studentName;
    private int semester;
    private ArrayList<String> lessonsList;
    private Mark qualWork;
    private HashMap<Integer, HashMap<String, Mark>> marks;

    /**
     * Конструктор.
     *
     * @param name     имя студента.
     * @param semester семестр.
     */
    public CreditBook(String name, int semester) throws Exception {
        if (semester < 1) {
            throw new Exception("Inappropriate number of semester");
        }
        this.semester = semester;
        this.studentName = name;
        this.lessonsList = new ArrayList<>();
        this.marks = new HashMap<>();
        this.marks.put(semester, new HashMap<>());
        this.qualWork = Mark.markNotPassed;
    }

    /**
     * Метод добавления оценки.
     *
     * @param lesson   Предмет.
     * @param mark     Оценка.
     * @param semester Семестр.
     * @throws Exception Эксепшн на случай некорректной оценки.
     */
    public void addMark(String lesson, Mark mark, int semester) throws Exception {

        if (!this.marks.containsKey(semester)) {
            this.marks.put(semester, new HashMap<>());
        }
        if (!this.marks.get(semester).containsKey(lesson)) {
            this.marks.get(semester).put(lesson, mark);
        }
        if (!this.lessonsList.contains(lesson)) {
            this.lessonsList.add(lesson);
        }

        this.marks.get(semester).put(lesson, mark);
    }

    /**
     * Тот же метод, но если мы не задаём семестр, то добавляем в текущий.
     *
     * @param lesson Предмет.
     * @param mark   Оценка.
     * @throws Exception Эксепшн на случай некорректной оценки.
     */
    public void addMark(String lesson, Mark mark) throws Exception {
        this.addMark(lesson, mark, this.semester);
    }

    /**
     * Увеличивает номер семестра.
     *
     * @return Номер нового семестра.
     */
    public int incSemesterNumber() {
        this.semester += 1;
        return this.semester;
    }

    /**
     * Сеттер для квалификационной работы.
     *
     * @param mark Оценка.
     * @throws Exception Валидность оценки.
     */
    public void setQualWork(Mark mark) throws Exception {
        this.qualWork = mark;
    }

    /**
     * Получаем дипломную оценку по предмету.
     *
     * @param lesson предмет.
     * @return возвращаем средний балл.
     * @throws Exception если предмета нет, возвращаем исключение.
     */
    public Mark getDiplomaMarkForLesson(String lesson) throws Exception {
        Mark mark = null;
        for (int i = 1; i <= this.semester; i++) {
            if (this.marks.containsKey(i)) {
                if (this.marks.get(i).containsKey(lesson)) {
                    mark = this.marks.get(i).get(lesson);
                }
            }
        }
        if (mark == null) {
            throw new Exception("No such lesson");
        }

        return mark;
    }

    /**
     * Проверяет возможность получения диплома.
     *
     * @return Возвращает тру или фолс.
     */
    public boolean isDiplomaPossible() throws Exception {
        if (this.qualWork.intMark != 5) {
            return false;
        }

        List<Mark> diplomaMarks = this.lessonsList.stream().filter(
                        x -> {
                            try {

                                return this.getDiplomaMarkForLesson(x).getIntMark() >= 4
                                        || this.getDiplomaMarkForLesson(x)
                                        .getStrMark().equals("Passed");
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        })
                .map(x -> {
                    try {
                        return getDiplomaMarkForLesson(x);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).collect(Collectors.toList());
        if (diplomaMarks.size() != this.lessonsList.size()) {
            return false;
        }

        return (double) Collections.frequency(diplomaMarks, Mark.markFive)
                / (diplomaMarks.size() - diplomaMarks.stream()
                .filter(x -> !x.strMark.isEmpty()).count()) >= 0.75;
    }

    /**
     * Получаем средний балл за всё время.
     *
     * @return дабл среднего балла.
     */
    public double getAverageForAllTime() {
        return this.marks.values().stream().map(x -> x.values().stream().map(Mark::getIntMark)
                        .filter(y -> y != 0).mapToInt(Integer::intValue).average().getAsDouble())
                .mapToDouble(num -> (double) num).average().getAsDouble();

    }

    /**
     * Проверяем возможность получения повышки.
     * Если хотя бы одна не 5,то нет.
     *
     * @return буллеан.
     */
    public boolean isIncreasedScholarship() {
        return this.marks.get(this.semester).values().stream()
                .allMatch(y -> y.intMark == 5 || y.strMark.equals("Passed"));
    }

    /**
     * Получаем квалификационную оценку.
     *
     * @return инт оценки.
     */
    public Mark getQualWork() {
        return this.qualWork;
    }

    /**
     * Енум для оценок.
     */
    public enum Mark {
        markFive(5),
        markFour(4),
        markThree(3),
        markTwo(2),
        markOne(1),
        markPassed("Passed"),
        markNotPassed("Not passed");
        private int intMark;
        private String strMark;

        /**
         * Конструктор для числовых оценок.
         *
         * @param mark оценка.
         */
        Mark(int mark) {
            this.intMark = mark;
            this.strMark = "";
        }

        /**
         * Конструктор для зачёта и незачёт.
         *
         * @param mark Оценка.
         */
        Mark(String mark) {
            this.strMark = mark;
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
}

