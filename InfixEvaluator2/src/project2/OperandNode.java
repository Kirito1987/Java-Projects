package project2;

public class OperandNode implements Node {

    private String value;


    OperandNode(String value) {
        this.value = value;
    }


    public String inOrderWalk() {
        return String.valueOf(value);
    }


    public String postOrderWalk() {
        return String.valueOf(value);
    }


    public void post() {}
}
