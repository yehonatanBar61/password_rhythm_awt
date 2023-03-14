import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class algoTest {

    @org.junit.jupiter.api.Test
    void empty_stack() {
        Stack<Double> st = new Stack<>();
        st.push(1.0);
        st.push(2.0);
        st.push(3.0);
        algo.empty_stack(st);
        assertEquals(0, st.size());
    }
}