import org.example.Tree;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



import org.junit.jupiter.api.Assertions;

class TreeTest {
    private Tree<String> tree;

    @BeforeEach
    void setUp() {
        tree = new Tree<>("R1");
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
    void removeShouldRemoveNodeFromParentSons() {
        Tree<String> a = tree.addChild("A");
        tree.addChild("B");

        a.remove();
        Assertions.assertEquals(1, tree.sons.size());
        Assertions.assertEquals("B", tree.sons.get(0).value);
    }

    @Test
    void equalsShouldReturnTrueForEqualTrees() {
        Tree<String> tree2 = new Tree<>("R1");
        tree.addChild("A");
        tree.addChild("B");

        tree2.addChild("A");
        tree2.addChild("B");

        Assertions.assertTrue(tree.equals(tree2));
    }

    @Test
    void equalsShouldReturnFalseForDifferentTrees() {
        Tree<String> tree2 = new Tree<>("R2");
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

        Iterator<Tree> iterator = tree.iterator();

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
}
