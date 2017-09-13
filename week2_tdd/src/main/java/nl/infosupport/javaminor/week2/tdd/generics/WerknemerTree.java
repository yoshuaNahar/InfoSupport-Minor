package nl.infosupport.javaminor.week2.tdd.generics;

import nl.infosupport.javaminor.week2.tdd.tree.DuplicateValueException;

public class WerknemerTree<T extends Werknemer> {

  private Node root;
  private int depth;

  public void add(T value) throws DuplicateValueException {
    if (root != null && root.isDuplicate(value)) {
      throw new DuplicateValueException("Werknemer already exists inside tree.");
    }

    if (root == null) {
      root = new Node(value);
      depth++;
      return;
    }

    root.add(value);
    depth = 1 + root.getDepth();
  }

  public int count() {
    if (root == null) {
      return 0;
    }

    return root.count();
  }

  public T max() {
    if (root == null) {
      return null;
    }

    return root.max();
  }

  public int getDepth() {
    return depth;
  }

  public boolean contains(T value) {
    if (root == null) {
      return false;
    }

    return root.contains(value);
  }

  private class Node {

    private final T value;
    private int depth;

    private Node leftNode; // smaller than parent
    private Node rightNode; // larger than parent

    private Node(T value) {
      this.value = value;
    }

    private void add(T value) {
      if (leftNode == null && rightNode == null) {
        depth++;
      }

      if (this.value.compareTo(value) > 0) {
        if (leftNode == null) {
          leftNode = new Node(value);
        } else {
          leftNode.add(value);
          depth++;
        }
      } else if (this.value.compareTo(value) < 0) {
        if (rightNode == null) {
          rightNode = new Node(value);
        } else {
          rightNode.add(value);
          depth++;
        }
      }
    }

    private boolean isDuplicate(T value) {
      if (this.value.compareTo(value) == 0) {
        return true;
      }

      if (leftNode != null) {
        return leftNode.isDuplicate(value);
      }

      if (rightNode != null) {
        return rightNode.isDuplicate(value);
      }

      return false;
    }

    private int getDepth() {
      return depth;
    }

    private int count() {
      if (leftNode != null && rightNode != null) {
        return 1 + leftNode.count() + rightNode.count();
      } else if (leftNode != null) {
        return 1 + leftNode.count();
      } else if (rightNode != null) {
        return 1 + rightNode.count();
      }

      return 1;
    }

    private T max() {
      if (rightNode != null) {
        return rightNode.max();
      }

      return value;
    }

    private boolean contains(T value) {
      if (this.value.compareTo(value) == 0) {
        return true;
      }
      if (this.value.compareTo(value) > 0) {
        if (leftNode != null) {
          return leftNode.contains(value);
        }
      }
      if (this.value.compareTo(value) < 0) {
        if (rightNode != null) {
          return rightNode.contains(value);
        }
      }

      return false;
    }

  }

}
