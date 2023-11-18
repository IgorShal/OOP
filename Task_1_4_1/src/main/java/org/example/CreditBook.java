package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Класс зачетной книжки.
 * Давайте будем считать, что все предметы идут в диплом.
 * Также есть только оценки, зачетов и незачетов нет.
 */
public class CreditBook {
    private String studentName;
    private int semester;
    private ArrayList<String> lessonsList;
    private int qualWork;
    private HashMap<Integer, HashMap<String, ArrayList<Integer>>> marks;

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
        this.qualWork = 0;
    }

    /**
     * Метод добавления оценки.
     *
     * @param lesson   Предмет.
     * @param mark     Оценка.
     * @param semester Семестр.
     * @throws Exception Эксепшн на случай некорректной оценки.
     */
    public void addMark(String lesson, int mark, int semester) throws Exception {
        if (mark < 0 || mark > 5) {
            throw new Exception("Invalid mark number");
        }
        if (!this.marks.containsKey(semester)) {
            this.marks.put(semester, new HashMap<>());
        }
        if (!this.marks.get(semester).containsKey(lesson)) {
            this.marks.get(semester).put(lesson, new ArrayList<>());
        }
        if (!this.lessonsList.contains(lesson)){
            this.lessonsList.add(lesson);
        }

        this.marks.get(semester).get(lesson).add(mark);
    }

    /**
     * Тот же метод, но если мы не задаём семестр, то добавляем в текущий.
     *
     * @param lesson Предмет.
     * @param mark   Оценка.
     * @throws Exception Эксепшн на случай некорректной оценки.
     */
    public void addMark(String lesson, int mark) throws Exception {
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
    public void setQualWork(int mark) throws Exception {
        if (mark < 0 || mark > 5) {
            throw new Exception("Invalid mark number");
        }
        this.qualWork = mark;
    }

    /**
     * Получаем средний балл по предмету.
     *
     * @param lesson предмет.
     * @return возвращаем средний балл.
     * @throws Exception если предмета нет, возвращаем исключение.
     */
    public double getAverageForLesson(String lesson) throws Exception {
        double average = 0;
        for (int i = 1; i <= this.semester; i++) {
            if (this.marks.containsKey(i)) {
                if (this.marks.get(i).containsKey(lesson)) {
                    average = (double) this.marks.get(i).get(lesson)
                            .stream().reduce(0, Integer::sum)
                            / this.marks.get(i).get(lesson).size();
                }
            }
        }

        if (average == 0) {
            throw new Exception("No such lesson");
        }
        return average;
    }

    /**
     * Получаем дипломную оценку по предмету.
     *
     * @param lesson предмет.
     * @return возвращаем средний балл.
     * @throws Exception если предмета нет, возвращаем исключение.
     */
    public int getDiplomaMarkForLesson(String lesson) throws Exception {
        int mark = 0;
        for (int i = 1; i <= this.semester; i++) {
            if (this.marks.containsKey(i)) {
                if (this.marks.get(i).containsKey(lesson)) {
                    mark = this.marks.get(i).get(lesson)
                            .get(this.marks.get(i).get(lesson).size() - 1);
                }
            }
        }
        if (mark == 0) {
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
        if (this.qualWork != 5) {
            return false;
        }
        ArrayList<Integer> diplomaMarks = new ArrayList<>();
        for (String lesson : this.lessonsList) {
            if (this.getDiplomaMarkForLesson(lesson) <= 3){
                return false;
            }

            diplomaMarks.add(this.getDiplomaMarkForLesson(lesson));
        }
        if ((double) Collections.frequency(diplomaMarks, 5) / diplomaMarks.size() >= 0.75) {
            return true;
        }
        return false;
    }

    /**
     * Получаем средний балл за всё время.
     *
     * @return дабл среднего балла.
     */
    public double getAverageForAllTime() {
        int count = 0;
        int sum = 0;
        for (int i = 1; i <= this.semester; i++) {
            if (this.marks.containsKey(i)) {
                for (ArrayList<Integer> mark : this.marks.get(i).values()) {
                    sum += mark.stream().reduce(0, Integer::sum);
                    count += mark.size();
                }
            }
        }
        double res = (double) sum / count;
        return res;
    }

    /**
     * Проверяем возможность получения повышки.
     * Если хотя бы одна не 5,то нет.
     *
     * @return буллеан.
     */
    public boolean isIncreasedScholarship() {
        if (this.marks.containsKey(this.semester)) {
            for (ArrayList<Integer> mark : this.marks.get(this.semester).values()) {
                if (Collections.frequency(mark, 5) != mark.size()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Получаем квалификационную оценку.
     *
     * @return инт оценки.
     */
    public int getQualWork() {
        return this.qualWork;
    }
}

