package nl.infosupport.javaminor.blok1.week2.tdd.tree;

public class Tree {

  private Node root;
  private int depth;

  public void add(int value) throws DuplicateValueException {
    if (root != null && root.isDuplicate(value)) {
      throw new DuplicateValueException("Value already exists inside tree.");
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

  public int max() {
    if (root == null) {
      return 0;
    }

    return root.max();
  }

  public int getDepth() {
    return depth;
  }

  public boolean contains(int value) {
    if (root == null) {
      return false;
    }

    return root.contains(value);
  }

  private class Node {

    private final int value;
    private int depth;

    private Node leftNode; // smaller than parent
    private Node rightNode; // larger than parent

    private Node(int value) {
      this.value = value;
    }

    private void add(int value) {
      if (leftNode == null && rightNode == null) {
        depth++;
      }

      if (value < this.value) {
        if (leftNode == null) {
          leftNode = new Node(value);
        } else {
          leftNode.add(value);
          depth++;
        }
      } else if (value > this.value) {
        if (rightNode == null) {
          rightNode = new Node(value);
        } else {
          rightNode.add(value);
          depth++;
        }
      }
    }

    private boolean isDuplicate(int value) {
      if (this.value == value) {
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

    private int max() {
      if (rightNode != null) {
        return rightNode.max();
      }

      return value;
    }

    private boolean contains(int value) {
      if (this.value == value) {
        return true;
      }
      if (value < this.value) {
        if (leftNode != null) {
          return leftNode.contains(value);
        }
      }
      if (value > this.value) {
        if (rightNode != null) {
          return rightNode.contains(value);
        }
      }

      return false;
    }

  }

}
