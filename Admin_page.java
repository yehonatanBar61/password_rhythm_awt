import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Admin_page extends awt_login implements ActionListener, Runnable, WindowListener, PropertyChangeListener {

    List l1;
    Frame f;
    Menu menu;
    MenuBar mb;
    TextArea area, area2;
    int users_size = 0;
    Users u;


    public Admin_page(Users u){
        this.u = u;
    }


    @Override
    public void run() {
        f = new Frame("admin page");
        mb=new MenuBar();
        menu=new Menu("Menu");
        MenuItem i1=new MenuItem("users");
        MenuItem i2=new MenuItem("time between letters");
        menu.add(i1);
        menu.add(i2);
        mb.add(menu);
        menu.addActionListener(this);

        first_page();

        f.setMenuBar(mb);
        f.setSize(400,400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public void remove_first_page(){
        if(area != null && l1 !=null){
            f.remove(area);
            f.remove(l1);
        }
    }

    public void remove_second_page(){
        if(area2 != null) {
            f.remove(area2);
        }
    }

    public void add_to_firstpage_list(User u){
        l1.add(u.user_name);
    }

    public void first_page(){
        remove_second_page();
        remove_first_page();
        users_size = u.users.size();
        l1 = new List(users_size);
        l1.addActionListener(this);
        // to make sure the components are the size of the win at start
        int x = f.getSize().width;
        int y = f.getSize().height;

        l1.setBounds(5,50,100,400);
        for (User user : u.users) {
            l1.add(user.user_name);
        }
        area = new TextArea("information");
        area.setBounds(105, 50, 290, 340);
        area.setEditable(false);
        //Components change as the center window slides
        f.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int x = f.getSize().width;
                int y = f.getSize().height;
                l1.setBounds(5, 50, 100, y);
                area.setBounds(105,50,x-110,y-58);
            }
        });

        f.add(area);
        f.add(l1);
    }

    public void second_page(){
        remove_first_page();
        remove_second_page();
        area2 = new TextArea();
        area2.setBounds(8, 50, 385, 340);
        area2.setEditable(false);
        //Components change as the center window slides
        f.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                int x = f.getSize().width;
                int y = f.getSize().height;
                area2.setBounds(8,50,x-15,y-58);
            }
        });
        int counter = 0;
        for (User user : u.users) {
            counter++;
            try{
                if(user.password.equals("")){
                    area2.append(counter + ") "+"empty password");
                }
                else
                    area2.append(counter + ") "+user.password);
            }catch (NullPointerException r){
                area2.append("no password\n");
            }
            area2.append("\n");
            area2.append("--------------------------------------");
            area2.append("\n");

        }

        f.add(area2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("users")){
            first_page();
        }
        if(e.getActionCommand().equals("time between letters")){
           second_page();
        }
        if(e.getSource() == l1){
            try {
                area.setText("");
                area.append("user name: ");
                area.append(users.get_by_username(e.getActionCommand()).user_name);
                area.append("\n");
                area.append("password: ");
                if(users.get_by_username(e.getActionCommand()).password.equals(""))
                    area.append("empty password");
                else
                    area.append(users.get_by_username(e.getActionCommand()).password);
                area.append("\n");
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }



    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
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
