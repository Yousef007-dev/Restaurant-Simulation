import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Admin1 extends Maker implements ActionListener {

    private JButton delete ,add,back,edit,show;
    private JLabel mainlabel,backlabel;
    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> list;
    private JScrollPane scrollPane;

ArrayList<Order> od;
    Admin1(int h,int w,String name) {
        od=WelcomeLabel.lastorder;

        //frame options
        frame = new JFrame(name);
        frame.setSize(h, w);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setResizable(false);


        show =ButtonMaker(40, 530, 400, 50,"Show!",new Color(250,150,0));
        back = ButtonMaker(40, 600, 400, 50,"back!",Color.red);

        frame.add(show);
        frame.add(back);

        listModel = new DefaultListModel<>();
        update();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Tahoma", Font.BOLD, 25));
        list.setBackground(new Color(255, 240, 180));
        list.setForeground(Color.BLACK);
        list.setSelectionBackground(Color.GRAY);
        list.setSelectionForeground(Color.WHITE);

        scrollPane = new JScrollPane(list);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.getViewport().setBackground(new Color(255, 240, 180));
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        scrollPane.setBounds(10,20,470,500);


        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setBackground(new Color(6 * 255 / 7, 6 * 240 / 7, 6 * 180 / 7));

        frame.add(scrollPane);

        show.addActionListener(e -> {
            int ind = list.getSelectedIndex();
            if (ind == -1) {
                JOptionPane pane = new JOptionPane();
                JOptionPane.showMessageDialog(null,
                        "You must choose a meal from the menu",
                        "Wrong way",
                        JOptionPane.ERROR_MESSAGE);
            } else {
                new Admin2(frame,od.get(ind),500,700,"Seb's");
            }
        });


        back.addActionListener(e -> {
            frame.dispose();
        });

        frame.setVisible(true);
    }

    void update()
    {
        listModel.clear();
        int i=1;
        for (Order s:od ) {
            listModel.addElement("Order "+ Integer.toString(i++));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
