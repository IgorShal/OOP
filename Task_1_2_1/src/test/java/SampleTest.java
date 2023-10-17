import java.util.ConcurrentModificationException;
import java.util.Iterator;
import org.example.Tree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class SampleTest {
    private Tree<String> tree;

    @BeforeEach
    void setUp() {
        tree = new Tree<>("R1", 0);
    }

    @Test
    void addChildsSouldAddChildNode() {
        tree.addChild("A");
        tree.addChild("B");

        Assertions.assertEquals(2, tree.sons.size());
        Assertions.assertEquals("A", tree.sons.get(0).value);
        Assertions.assertEquals("B", tree.sons.get(1).value);
    }

    @Test
    void equalsTest() {

        var a = tree.addChild("A");
        Tree<String> tree2 = new Tree<>("R1", 1);
        Assertions.assertNotEquals(tree, tree2);

    }

    @Test
    void removeShouldRemoveNodeFromParentSons() {
        Tree<String> a = tree.addChild("A");
        tree.addChild("B");

        a.remove();
        Assertions.assertEquals(1, tree.sons.size());
        Assertions.assertEquals("B", tree.sons.get(0).value);
    }

    @Test
    void equalsShouldReturnTrueForEqualTrees() {
        Tree<String> tree2 = new Tree<>("R1", 0);
        tree.addChild("A");
        tree.addChild("B");

        tree2.addChild("A");
        tree2.addChild("B");

        Assertions.assertEquals(tree, tree2);
    }

    @Test
    void equalsShouldReturnFalseForDifferentTrees() {
        Tree<String> tree2 = new Tree<>("R2", 0);
        tree.addChild("A");
        tree.addChild("B");

        tree2.addChild("A");
        tree2.addChild("B");

        Assertions.assertFalse(tree.equals(tree2));
    }

    @Test
    void iteratorShouldReturnCorrectOrder() {
        tree.addChild("A").addChild("C");
        tree.addChild("B").addChild("D");

        Iterator<Tree<String>> iterator = tree.iterator();

        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("R1", iterator.next().value);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("A", iterator.next().value);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("B", iterator.next().value);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("C", iterator.next().value);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("D", iterator.next().value);
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    void iteratorShouldReturnCorrectOrderForDfs() {
        Tree<String> tree2 = new Tree<>("R1", 1);
        tree2.addChild("A").addChild("C");
        tree2.addChild("B").addChild("D");

        Iterator<Tree<String>> iterator = tree2.iterator();

        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("R1", iterator.next().value);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("A", iterator.next().value);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("C", iterator.next().value);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("B", iterator.next().value);
        Assertions.assertTrue(iterator.hasNext());
        Assertions.assertEquals("D", iterator.next().value);
        Assertions.assertFalse(iterator.hasNext());
    }

    @Test
    void exceptionOnAddingTest() {
        var a = tree.addChild("R2");
        var b = tree.addChild("R3");
        var c = b.addChild("R3");
        Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            for (Tree<String> node : tree) {
                tree.addChild("2");
            }
        });
    }

    @Test
    void exceptionOnRemoveTest() {
        var a = tree.addChild("R2");
        var b = tree.addChild("R3");
        var c = b.addChild("R3");
        Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            for (Tree<String> node : tree) {
                a.addChild("2");
            }
        });
    }
}