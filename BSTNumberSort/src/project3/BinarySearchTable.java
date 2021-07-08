package project3;

public class BinarySearchTable <Table extends Comparable<Table>> {

    // Variables
    private BSTnode root;
    private StringBuilder result = new StringBuilder();


    BinarySearchTable() {
        root = null;
        result.setLength(0);
    }


    void insertNode (Table value) {
        if (root == null) {
            root = new BSTnode(value);
            return;
        }
        insertNodeRecursive(value, root);
    }

    private void insertNodeRecursive(Table value, BSTnode node) {
        if (value.compareTo(node.value) <= 0) {
            if (node.left != null) {
                insertNodeRecursive(value, node.left);
            } else {
                node.left = new BSTnode(value);
            }
        } else if (value.compareTo(node.value) > 0) {
            if (node.right !=null) {
                insertNodeRecursive(value, node.right); 
            } else {
                node.right = new BSTnode(value);
            }
        }
    }


    String getAscending() {
        inorderTraversal(root);
        return result.toString();
    }


    String getDescending() {
        getAscending();

        String[] nums = result.toString().split(" ");
        result.setLength(0);
        int len = nums.length;

        for (int i = len - 1; i >= 0; i--) {
            result.append(nums[i]).append(" ");
        }
        return result.toString();
    }


    private void inorderTraversal(BSTnode root) {
        if (root.value != null) {
            if (root.getLeft() != null) inorderTraversal(root.getLeft());
            String res = (root.value).toString();
            result.append(res).append(" ");
            if (root.getRight() != null) inorderTraversal(root.getRight());
        }
    }


    class BSTnode {

        // Variables
        private Table value;
        private BSTnode left, right;

        BSTnode(Table value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }

        BSTnode getLeft() {
            return left;
        }

        BSTnode getRight() {
            return right;
        }
    }
}
