import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Admin2 extends Maker {

    private JFrame frame;
    private JButton done;
    private DefaultListModel<String> listModel;
    private JList<String> list;
    private JScrollPane scrollPane;

    Admin2(JFrame fr1,Order od,int h,int w,String name)
    {
        frame = new JFrame(name);
        frame.setSize(h, w);
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        done =ButtonMaker(40, 600, 400, 50,"Done!",new Color(250,150,0));

        listModel = new DefaultListModel<>();
        update(od.list);
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
        scrollPane.setBounds(10,20,470,480);


        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setBackground(new Color(6 * 255 / 7, 6 * 240 / 7, 6 * 180 / 7));

        frame.add(scrollPane);

        JLabel label1=LabelMaker(10,520,230,50,"Order case:" + od.OrderCase,new Color(250,150,0));
        JLabel label2=LabelMaker(250,520,230,50,"Order Type:" + od.OrderType,new Color(250,150,0));

        frame.add(label1);
        frame.add(label2);




        done.addActionListener(e -> {
            fr1.setVisible(true);
            frame.dispose();
        });

        frame.add(done);

        frame.setVisible(true);
    }

    void update(List<Meal> lt)
    {
        listModel.clear();
        int i=1;
        for (Meal s:lt ) {
            listModel.addElement( Integer.toString(i++)+" "+s.mealName+" "+new String(String.valueOf(s.maelPrise))+"$");
        }
    }
}
