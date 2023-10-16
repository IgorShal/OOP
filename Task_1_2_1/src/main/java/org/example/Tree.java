package org.example;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Queue;


/**
 * Класс деревьев.
 */
public class Tree<T> implements Iterable<Tree> {
    public final T value;
    private boolean isIterationFlag;
    public IteratorType iteratorType;
    Tree<T> father;
    public ArrayList<Tree<T>> sons = new ArrayList<Tree<T>>();

    /**
     * Конструктор.
     */
    public Tree(T value, int iterator) {
        this.value = value;
        this.isIterationFlag = false;
        if (iterator == 0) {
            this.iteratorType = IteratorType.BFS;
        } else {
            this.iteratorType = IteratorType.DFS;
        }
    }

    /**
     * Делаем метод итератор.
     */
    @Override
    public Iterator<Tree> iterator() {
        TreeIterator iterator = new TreeIterator(this);
        if (this.iteratorType == IteratorType.DFS) {
            iterator.dfs(this);
        } else {
            iterator.bfs();
        }

        return iterator;
    }

    /**
     * Добавляем детей к вершине.
     */
    public Tree<T> addChild(T value) {
        if (this.isIterationFlag) {
            throw new ConcurrentModificationException("Changed tree during iteration");
        }
        Tree<T> son = new Tree<>(value, 0);
        son.father = this;
        this.sons.add(son);
        return son;
    }

    /**
     * Добавляем дерево к дереву.
     */
    public Tree<T> addChild(Tree<T> value) {
        value.father = this;
        this.sons.add(value);
        if (this.isIterationFlag) {
            throw new ConcurrentModificationException("Changed tree during iteration");
        }
        return value;
    }

    /**
     * Удаляем вершину из дерева.
     */
    public void remove() {
        if (this.isIterationFlag) {
            throw new ConcurrentModificationException("Changed tree during iteration");
        }
        this.father.sons.remove(this);
    }

    /**
     * Сравниваем деревья по значениям в вершинах.
     * Сравнение происходит без учёта перестановок сыновей.То есть деревья вида:
     * ..A           A
     * /  \    и  /   \
     * B   C     C     B
     * Не равны.
     */
    @Override
    public boolean equals(Object o) {
        if (Objects.isNull(o)) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Tree second = (Tree) o;
        if (this.sons.size() != second.sons.size()) {
            return false;
        }


        for (int i = 0; i < this.sons.size(); i++) {
            if (!this.sons.get(i).equals(second.sons.get(i))) {
                return false;
            }
        }
        if (this.hashCode() == second.hashCode() && this.value == second.value) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Хэшкод.
     */
    @Override
    public int hashCode() {
        int prime = 31;
        int hash = 0;
        for (Tree son : sons) {
            hash += Objects.hash(son.value);
        }
        hash += Objects.hash(value);
        return prime * hash;
    }

    /**
     * Меин.
     */
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("R1", 1);
        var a = tree.addChild("A");
        Tree<String> tree2 = new Tree<>("R1", 1);
        System.out.println(tree.equals(tree2));
    }

    /**
     * Класс итератора по дереву.
     */
    public class TreeIterator implements Iterator<Tree> {
        Tree start;
        int number;
        ArrayList<Tree> res;

        /**
         * Создаем итератор.
         */
        public TreeIterator(Tree start) {
            this.start = start;

            number = 0;
            res = new ArrayList<>();
        }

        /**
         * Делаем бфс.
         */
        public void bfs() {
            res.clear();
            Queue<Tree> queue = new LinkedList<>();
            queue.add(start);
            res.add(start);
            start.isIterationFlag = true;
            while (!queue.isEmpty()) {
                Tree cur = queue.poll();

                for (int i = 0; i < cur.sons.size(); i++) {
                    queue.add((Tree) cur.sons.get(i));
                    ((Tree) cur.sons.get(i)).isIterationFlag = true;
                    res.add((Tree) cur.sons.get(i));

                }
            }
        }

        /**
         * Делаем дфс.
         */
        public void dfs(Tree vert) {
            vert.isIterationFlag = true;
            res.add(vert);
            for (int i = 0; i < vert.sons.size(); i++) {
                dfs((Tree) vert.sons.get(i));
            }
        }

        /**
         * Проверяем есть ли элемент.
         */
        @Override
        public boolean hasNext() {

            if (number < res.size()) {
                return true;
            }
            changeIterationFlag(start);


            return false;
        }

        /**
         * Меняем обратно флаг итерирования в дереве.
         */
        private void changeIterationFlag(Tree start) {
            start.isIterationFlag = false;
            for (int i = 0; i < start.sons.size(); i++) {
                changeIterationFlag((Tree) start.sons.get(i));
            }
        }

        /**
         * Получаем следующий.
         */
        @Override
        public Tree next() throws NoSuchElementException {
            if (!hasNext()) {
                number = 0;

                throw new NoSuchElementException("No more elements in result");
            }
            number += 1;
            return res.get(number - 1);

        }

    }

    /**
     * Перечисление типов итератора.
     */
    public enum IteratorType {

        DFS,
        BFS

    }
}

