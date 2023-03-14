import java.util.EmptyStackException;
import java.util.Stack;

public class algo {

    static double epprox = 0.2;

    public static void empty_stack(Stack<Double> st){
        int size = st.size();
        for(int i = 0; i<size; i++){//because the stack doesn't count the first letter.
            st.pop();
        }
    }

    public static boolean is_epprox(double x, double y){//x and y are doubles representing seconds
        double max_gap = epprox; //in seconds
        return Math.abs(x - y) <= max_gap;
    }

    static boolean stack_compare(Stack<Double> first, Stack<Double> second){
        Stack<Double>  temp_first = (Stack<Double>) first.clone();
        Stack<Double>  temp_second = (Stack<Double>) second.clone();
        if(temp_first.size() != temp_second.size()){
            return false;
        }
        if(temp_first.size() == 0){
            return true;
        }

        while(temp_first.size() != 0){
            double first_pop = temp_first.pop();
            double second_pop = temp_second.pop();
            if(!is_epprox(first_pop,second_pop))
                return false;
        }
        return true;
    }



    public static Stack<Double> get_average_Stack(Stack<Double> st1, Stack<Double> st2) throws Exception{
        if(st1.size() != st2.size()){
            throw new IllegalArgumentException();
        }
//        if(st1.size() == 0){
//            throw new EmptyStackException();
//        }
        double p1, p2, average;
        Stack<Double> new_stack = new Stack<Double>();
        while(st1.size() != 0){
            p1 = st1.pop();
            p2 = st2.pop();
            average = (p1+p2)/2;
            new_stack.push(average);
        }
        new_stack = swipe(new_stack);
        return new_stack;
    }


    static Stack<Double> swipe(Stack<Double> st){
        int size = st.size();
        Stack<Double> new_st = new Stack<Double>();
        for(int i = 0; i< size; i++){
            new_st.push(st.pop());
        }
        return new_st;
    }

    public static double[] stack_to_arr(Stack<Double> st){
        st = swipe(st);
        double[] arr = new double[st.size()];
        for(int i = 0; i<arr.length; i++){
            arr[i] = st.pop();
        }
        return arr;
    }

    static boolean arr_compare(double[] arr1, double[] arr2){
        if(arr1.length != arr2.length){
            return false;
        }

        if(arr1.length == 0){
            return true;
        }

        for(int i = 0; i<arr1.length; i++){
            if(!is_epprox(arr1[i], arr2[i])){
                return false;
            }
        }
        return true;
    }

}
