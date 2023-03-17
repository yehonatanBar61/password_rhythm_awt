import java.awt.*;
import java.awt.event.*;
import java.util.Stack;


public class awt_login extends Frame implements KeyListener, ActionListener, Runnable, WindowListener, ItemListener {


    // initializing using constructor
    Label counterL, l;
    Label sec_pass_label, name_label, pass_label;
    TextField name, pass, sec_pass;
    CheckboxGroup cbg;
    Checkbox checkBox1,checkBox2;
    Button enter, reset, reset_pass;

    int first_stack_counter;
    int second_stack_counter;
    long first_time_first_box;
    long first_time_second_box;
    boolean first_flag = false;
    boolean second_first_flag;
    boolean login_flag = true;

    static Users users = new Users();
    User admin_user = new User("admin", "", null);

    String username;
    //stores the passwords time to check for correct rhythm
    Stack<Double> time_between_letters = new Stack<Double>();
    Stack<Double> time_between_letters_sec = new Stack<Double>();

    public void run() {

        users.add(admin_user);

        addWindowListener(this);
        first_stack_counter = 0;//counts the amount of letters in password 1
        second_stack_counter = 0;//counts the amount of letters in password 2

        cbg = new CheckboxGroup();
        checkBox1 = new Checkbox("register", cbg, false);
        checkBox1.setBounds(100,155, 70,50);
        checkBox2 = new Checkbox("login", cbg, true);
        checkBox2.setBounds(100,185, 70,50);
        checkBox1.addItemListener(this);
        checkBox2.addItemListener(this);




        //keylistener label
        l = new Label();
        l.setBounds (20, 50, 500, 20);

        counterL = new Label();
        counterL.setBounds(130,50,100,20);



        name_label = new Label("username", Label.CENTER);
        pass_label = new Label("password", Label.CENTER);

        name = new TextField();
        pass = new TextField();

        enter = new Button("enter");
        reset = new Button("reset");
        reset_pass = new Button("reset pass");

        name_label.setBounds(20,80,90,20);
        pass_label.setBounds(20,120,90,20);
        name.setBounds (120,80,90,20);
        pass.setBounds (120,120,90,20);
        enter.setBounds(20,250,40,20);
        reset.setBounds(70,250,40,20);
        reset_pass.setBounds(120,250,80,20);



        pass.addKeyListener(this);
        enter.addActionListener(this);
        reset.addActionListener(this);
        reset_pass.addActionListener(this);

        add(checkBox1);
        add(checkBox2);
        add(l);
        add(counterL);
        add(name);
        add(pass);
        add(reset_pass);
        add(name_label);
        add(pass_label);
        add(enter);
        add(reset);

        //System.out.println(users.get_usercount());

        setSize (300, 300);
        setLayout (null);
        setVisible (true);


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //recording and storing the password string and timeline
    public void keyPressed (KeyEvent e) {
        //using also time,gup_between_letters (stack), first_stack_counter from this.
        if(login_flag){
            long current_time;
            //the gup is from the first pressed key to the current pressed key
            double gup;

            first_stack_counter++;
            if(first_flag){
                current_time = System.nanoTime();
                gup = (current_time - first_time_first_box)/1000000000.0;
                time_between_letters.push(gup);
                System.out.println(time_between_letters);
                System.out.println(time_between_letters_sec);
            }
            else{
                first_flag = true;
                first_time_first_box = System.nanoTime();
            }
        }
        else {//register
            if(e.getSource() == pass){
                long current_time;
                double gup;

                first_stack_counter++;
                if(first_flag){
                    current_time = System.nanoTime();
                    gup = (current_time - first_time_first_box)/1000000000.0;
                    time_between_letters.push(gup);
                    System.out.print("pass 1 : ");
                    System.out.println(time_between_letters);
                    System.out.print("pass 2 : ");
                    System.out.println(time_between_letters_sec);
                    System.out.println();
                }
                else{
                    first_flag = true;
                    first_time_first_box = System.nanoTime();
                }
            }
            if(e.getSource() == sec_pass){
                long current_time;
                double gup;

                second_stack_counter++;
                if(second_first_flag){
                    current_time = System.nanoTime();
                    gup = (current_time - first_time_second_box)/1000000000.0;
                    time_between_letters_sec.push(gup);
                    System.out.print("pass 1 : ");
                    System.out.println(time_between_letters);
                    System.out.print("pass 2 : ");
                    System.out.println(time_between_letters_sec);
                    System.out.println();
                }
                else{
                    second_first_flag = true;
                    first_time_second_box = System.nanoTime();
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
    //for the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == reset_pass) {
            if (login_flag) {
                first_flag = false;
                second_first_flag = false;
                pass.setText("");
                algo.empty_stack(time_between_letters);
            } else {
                first_flag = false;
                second_first_flag = false;
                pass.setText("");
                sec_pass.setText("");
                algo.empty_stack(time_between_letters);
                algo.empty_stack(time_between_letters_sec);
            }
        }
        if (e.getSource() == enter && login_flag) {
            username = name.getText();
            if(pass.getText().equals("") && username.equals("admin")){
                Admin_page admin_page = new Admin_page(users);
                Thread th = new Thread(admin_page);
                th.start();
            }
            if (pass.getText().equals("")) {
                l.setText("please enter password");
            } else {
                if (!users.username_already_exists(username)) {
                    l.setText("username does not exist");
                    username = "";
                } else {
                    try {
                        if (users.get_by_username(username).is_the_password(pass.getText(), time_between_letters)) {
                            l.setText("");
                            l.setText("success login");
                            first_flag = false;
                            name.setText("");
                            pass.setText("");
                            username = "";
                            algo.empty_stack(time_between_letters);
                            first_stack_counter = 0;
                        } else {

                            l.setText("");
                            l.setText("the password or the user name is incorrect");
                            first_flag = false;
                            name.setText("");
                            pass.setText("");
                            username = "";
                            algo.empty_stack(time_between_letters);
                            first_stack_counter = 0;
                        }
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }
            }
        }
        if (e.getSource() == reset && login_flag) {
            first_flag = false;
            name.setText("");
            pass.setText("");
            username = "";
            algo.empty_stack(time_between_letters);
            first_stack_counter = 0;
        }
        if (e.getSource() == reset && !login_flag) {
            first_flag = false;
            second_first_flag = false;
            name.setText("");
            pass.setText("");
            sec_pass.setText("");
            username = "";
            algo.empty_stack(time_between_letters);
            algo.empty_stack(time_between_letters_sec);
            first_stack_counter = 0;
            second_stack_counter = 0;
        }
        if (e.getSource() == enter && !login_flag) {
            username = name.getText();
            if (users.username_already_exists(username)) {
                l.setText("");
                l.setText("username already exist");
            } else {
                if (pass.getText().equals("")) {
                    l.setText("please enter password");
                    username = "";
                }else {
                    if (pass.getText().equals(sec_pass.getText()) && algo.stack_compare(time_between_letters, time_between_letters_sec)) {
                        try {
                            time_between_letters = algo.get_average_Stack(time_between_letters, time_between_letters_sec);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        User u = new User(username, pass.getText(), time_between_letters);
                        System.out.println("after average: "+ time_between_letters.toString());
                        users.add(u);
                        l.setText("");
                        l.setText("success register");
                    } else {
                        first_flag = false;
                        second_first_flag = false;
                        l.setText("");
                        l.setText("the too passwords are different");
                        pass.setText("");
                        sec_pass.setText("");
                        algo.empty_stack(time_between_letters);
                        algo.empty_stack(time_between_letters_sec);
                        first_stack_counter = 0;
                        second_stack_counter = 0;
                    }
                }
            }

        }
    }


    @Override
    public void itemStateChanged(ItemEvent e) {
        algo.empty_stack(time_between_letters);
        algo.empty_stack(time_between_letters_sec);
        first_stack_counter = 0;
        second_stack_counter = 0;
        username = "";
        first_flag = false;
        second_first_flag = false;

        if(e.getItemSelectable() == checkBox2){
            login_flag = true;
            remove(sec_pass);
            remove(sec_pass_label);
            pass.setText("");
            enter.setBounds(20,250,40,20);
            reset.setBounds(70,250,40,20);
            reset_pass.setBounds(120,250,80,20);
            checkBox1.setBounds(100,155, 70,50);
            checkBox2.setBounds(100,185, 70,50);
            setSize (300, 300);
        }
        else {
            login_flag = false;
            pass.setText("");
            sec_pass_label = new Label("repeat password", Label.CENTER);
            sec_pass_label.setBounds(20,160,100,20);
            sec_pass = new TextField();
            sec_pass.setBounds(125,160,90,20);
            sec_pass.addKeyListener(this);
            enter.setBounds(20,300,40,20);
            reset.setBounds(70,300,40,20);
            reset_pass.setBounds(120,300,80,20);
            checkBox1.setBounds(100,200, 50,50);
            checkBox2.setBounds(100,240, 50,50);
            setSize (340, 340);
            add(sec_pass_label);
            add(sec_pass);
        }
    }


    @Override
    public void windowOpened(WindowEvent e) {

    }
    //to close window on x
    public void windowClosing(WindowEvent e) {
        dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }

}
