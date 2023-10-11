import org.example.Tree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals(2, tree.sons.size());
        assertEquals("A", tree.sons.get(0).value);
        assertEquals("B", tree.sons.get(1).value);
    }

    @Test
    void removeShouldRemoveNodeFromParentSons() {
        Tree<String> a = tree.addChild("A");
        tree.addChild("B");

        a.remove();
        assertEquals(1, tree.sons.size());
        assertEquals("B", tree.sons.get(0).value);
    }

    @Test
    void equalsShouldReturnTrueForEqualTrees() {
        Tree<String> tree2 = new Tree<>("R1");
        tree.addChild("A");
        tree.addChild("B");

        tree2.addChild("A");
        tree2.addChild("B");

        assertTrue(tree.equals(tree2));
    }

    @Test
    void equalsShouldReturnFalseForDifferentTrees() {
        Tree<String> tree2 = new Tree<>("R2");
        tree.addChild("A");
        tree.addChild("B");

        tree2.addChild("A");
        tree2.addChild("B");

        assertFalse(tree.equals(tree2));
    }

    @Test
    void iteratorShouldReturnCorrectOrder() {
        tree.addChild("A").addChild("C");
        tree.addChild("B").addChild("D");

        Iterator<Tree> iterator = tree.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("R1", iterator.next().value);
        assertTrue(iterator.hasNext());
        assertEquals("A", iterator.next().value);
        assertTrue(iterator.hasNext());
        assertEquals("B", iterator.next().value);
        assertTrue(iterator.hasNext());
        assertEquals("C", iterator.next().value);
        assertTrue(iterator.hasNext());
        assertEquals("D", iterator.next().value);
        assertFalse(iterator.hasNext());
    }
}
