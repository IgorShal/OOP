package org.example;

import java.util.*;

/**
 * Класс деревьев
 */
public class Tree<T> implements Iterable<Tree> {
    public final T value;

    Tree<T> father;
    public ArrayList<Tree<T>> sons = new ArrayList<Tree<T>>();

    public Tree(T value) {
        this.value = value;
    }

    /**
     * Делаем метод итератор
     */
    @Override
    public Iterator<Tree> iterator() {
        TreeIterator iterator = new TreeIterator(this);
        //iterator.DFS(this);
        iterator.BFS();
        return (Iterator<Tree>) iterator;
    }

    /**
     * Добавляем детей к вершине
     */
    public Tree<T> addChild(T value) {
        Tree<T> son = new Tree<>(value);
        son.father = this;
        this.sons.add(son);
        return son;
    }

    /**
     * Добавляем дерево к дереву
     */
    public Tree<T> addChild(Tree<T> value) {
        value.father = this;
        this.sons.add(value);

        return value;
    }

    /**
     * Удаляем вершину из дерева
     */
    public void remove() {
        this.father.sons.remove(this);
    }

    /**
     * Сравниваем
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
        Iterator treeIterator1 = second.iterator();
        Iterator<Tree> treeIterator2 = this.iterator();

        while (treeIterator1.hasNext() && treeIterator2.hasNext()) {
            if (((Tree) treeIterator1.next()).value != ((Tree) treeIterator2.next()).value) {
                return false;
            }

        }
        if (treeIterator2.hasNext() || treeIterator1.hasNext()) {
            return false;
        }
        if (this.hashCode() == second.hashCode())
            return true;
        else
            return false;
    }

    /**
     * Хэшкод
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
     * Меин
     */
    public static void main(String[] args) {
        Tree<String> tree = new Tree<>("R1");
        var a = tree.addChild("A");
        Tree<String> tree2 = new Tree<>("R1");
        System.out.println(tree.equals(tree2));

    }
}

/**
 * Класс итератора
 */
class TreeIterator implements Iterator<Tree> {
    Tree start;
    int i;
    ArrayList<Tree> res;

    /**
     * Создаем итератор
     */
    public TreeIterator(Tree start) {
        this.start = start;

        i = 0;
        res = new ArrayList<Tree>();
    }

    /**
     * Делаем бфс
     */
    public void BFS() {
        res.clear();
        Queue<Tree> queue = new LinkedList<>();
        queue.add(start);
        res.add(start);
        while (!queue.isEmpty()) {
            Tree cur = queue.poll();
            for (int i = 0; i < cur.sons.size(); i++) {
                queue.add((Tree) cur.sons.get(i));
                res.add((Tree) cur.sons.get(i));
            }
        }
    }

    /**
     * Делаем дфс
     */
    public void DFS(Tree vert) {
        res.add(vert);
        for (int i = 0; i < vert.sons.size(); i++) {
            DFS((Tree) vert.sons.get(i));
        }
    }

    /**
     * Проверяем есть ли элемент
     */
    @Override
    public boolean hasNext() {
        if (i < res.size()) {
            return true;
        }

        return false;
    }

    /**
     * Получаем следующий
     */
    @Override
    public Tree next() throws NoSuchElementException {
        if (hasNext() == false) {
            i = 0;
            throw new NoSuchElementException("No more elements in result");
        }
        i += 1;
        return res.get(i - 1);

    }

}