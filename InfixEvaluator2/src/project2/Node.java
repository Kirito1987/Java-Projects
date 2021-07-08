package project2;

import java.io.IOException;

public interface Node {
        String inOrderWalk();
        String postOrderWalk() throws IOException;
        void post() throws IOException;
}
