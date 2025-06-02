import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ManagerFrame extends JFrame {




    static int fontSizeEditTheFood =16;
    ManagerFrame(){



        setSize(500,700);
        setTitle("Manager window");
        setLayout(null);
        setResizable(false);
        int frameWidth = getWidth();
        int frameHeight = getHeight();

        JLabel managerWelcomeLabel = new JLabel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Singular, for proper rendering
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw an oval
                g2d.setColor(new Color(250, 150, 0));
                g2d.fillOval(0, 0, getWidth(), getHeight());

                // Draw text
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Arial", Font.BOLD, 16));
                g2d.drawString("Welcome To The Manager Section", 50, 30);
            }
        };
        managerWelcomeLabel.setBounds(frameWidth / 8, frameHeight / 8, frameWidth / 2 + 100, 50); // Set bounds
        managerWelcomeLabel.setOpaque(false);

        JButton editTheFoodButton = new JButton() {

            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "Edit the food menu";

                g2d.setFont(new Font("Arial",Font.BOLD, fontSizeEditTheFood));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth  = fm.stringWidth(text);
                int textHeight = fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() + textHeight)/2;

                g2d.drawString(text,x,y-3);

            }

        };

        editTheFoodButton.setBounds(135,250,200,40);
        editTheFoodButton.setBorderPainted(false);
        editTheFoodButton.setContentAreaFilled(false);

        editTheFoodButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                editTheFoodButton.setBounds(55,250,360,40);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                editTheFoodButton.setBounds(135,250,200,40);
            }
        });
        // Not : you have to discuss here:
        editTheFoodButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditTheFoodFrame editTheFoodFrame = new EditTheFoodFrame();
                editTheFoodFrame.setVisible(true);

            }
        });




        JButton showCurrantOrdersButton = new JButton() {

            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "Show the currant orders";

                g2d.setFont(new Font("Arial",Font.BOLD, 16));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth  = fm.stringWidth(text);
                int textHeight = fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() + textHeight)/2;

                g2d.drawString(text,x,y-3);

            }

        };

        showCurrantOrdersButton.setBounds(135,310,200,40);
        showCurrantOrdersButton.setBorderPainted(false);
        showCurrantOrdersButton.setContentAreaFilled(false);

        showCurrantOrdersButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                showCurrantOrdersButton.setBounds(55,310,360,40);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                showCurrantOrdersButton.setBounds(135,310,200,40);
            }
        });
        showCurrantOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Admin1(500,700,"Seb's");
            }
        });


        JButton showTheReportButton = new JButton() {

            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "Show the Report";

                g2d.setFont(new Font("Arial",Font.BOLD, 16));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth  = fm.stringWidth(text);
                int textHeight = fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() + textHeight)/2;

                g2d.drawString(text,x,y-3);

            }

        };

        showTheReportButton.setBounds(135,370,200,40);
        showTheReportButton.setBorderPainted(false);
        showTheReportButton.setContentAreaFilled(false);

        showTheReportButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                showTheReportButton.setBounds(55,370,360,40);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                showTheReportButton.setBounds(135,370,200,40);
            }
        });

        showTheReportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportFrame reportFrame = new ReportFrame();
                reportFrame.setVisible(true);
            }
        });

        JButton addMealButton = new JButton() {

            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(250,150,0));
                g2d.fillRoundRect(0,0,getWidth(),getHeight(),100,100);

                g2d.setColor(Color.WHITE);
                String text = "Add new meal";

                g2d.setFont(new Font("Arial",Font.BOLD, 16));

                FontMetrics fm = g2d.getFontMetrics();

                int textWidth  = fm.stringWidth(text);
                int textHeight = fm.getHeight();

                int x = (getWidth() - textWidth)/2;
                int y = (getHeight() + textHeight)/2;

                g2d.drawString(text,x,y-3);

            }

        };

        addMealButton.setBounds(135,430,200,40);// edit the other bounds
        addMealButton.setBorderPainted(false);
        addMealButton.setContentAreaFilled(false);

        addMealButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                addMealButton.setBounds(55,430,360,40);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                addMealButton.setBounds(135,430,200,40);
            }
        });
        addMealButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AddMealFrame addMealFrame = new AddMealFrame();




            }
        });


        //add components

        add(managerWelcomeLabel);
        add(editTheFoodButton);
        add(showCurrantOrdersButton);
        add(addMealButton);
        add(showTheReportButton);
    }
}
/*

 */