import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class CheckoutPanel extends JPanel implements ActionListener {
    private DefaultListModel<String> listModel;
    private JList<String> list;
    private JScrollPane scrollPane;
    JComboBox<String> tipBox;
    JRadioButton cashButton;
    JRadioButton cardButton;
    JRadioButton eWalletButton;
    JRadioButton dineInButton;
    JRadioButton deliveryButton;

    ButtonGroup paymentGroup;
    ButtonGroup orderTypeGroup;
    JTextField cardNumberField;
    JTextField eWalletField;
    JLabel cardLabel;
    JLabel eWalletLabel;
    JPanel itemsPanel;
    double total,dev,tax;
    ArrayList <Meal> od;


    public CheckoutPanel(ArrayList <Meal> od,JButton payButton,JFrame fr1) {
        dev=0;
        tax=0;
        total=0.0;
        for (Meal meal:od)
            total+=meal.maelPrise;
        this.od=new ArrayList<>();
        this.od=od;
        listModel = new DefaultListModel<>();
        update(od);
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
        scrollPane.setBounds(10, 20, 470, 500);

        String[] tips = {"No Tip", "10%", "15%", "20%"};
        tipBox = new JComboBox<>(tips);
        tipBox.addActionListener(this);

        cashButton = new JRadioButton("Cash");
        cardButton = new JRadioButton("Credit Card");
        eWalletButton = new JRadioButton("E-Wallet");

        dineInButton = new JRadioButton("Dine In");
        deliveryButton = new JRadioButton("Delivery");


        paymentGroup = new ButtonGroup();
        paymentGroup.add(cashButton);
        paymentGroup.add(cardButton);
        paymentGroup.add(eWalletButton);

        orderTypeGroup = new ButtonGroup();
        orderTypeGroup.add(deliveryButton);
        orderTypeGroup.add(dineInButton);

        cardLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField(16);
        cardLabel.setVisible(false);
        cardNumberField.setVisible(false);

        eWalletLabel = new JLabel("E-Wallet ID:");
        eWalletField = new JTextField(16);
        eWalletLabel.setVisible(false);
        eWalletField.setVisible(false);

        JPanel paymentPanel = new JPanel(new GridLayout(1, 3));
        paymentPanel.add(cashButton);
        paymentPanel.add(cardButton);
        paymentPanel.add(eWalletButton);

        JPanel orderstypePanel = new JPanel(new GridLayout(1, 2));
        orderstypePanel.add(dineInButton);
        orderstypePanel.add(deliveryButton);


        dineInButton.addActionListener(this);
        deliveryButton.addActionListener(this);


        cashButton.addActionListener(e -> {
            cardLabel.setVisible(false);
            cardNumberField.setVisible(false);
            eWalletLabel.setVisible(false);
            eWalletField.setVisible(false);

            revalidate();
            repaint();
        });

        cardButton.addActionListener(e -> {
            cardLabel.setVisible(true);
            cardNumberField.setVisible(true);
            eWalletLabel.setVisible(false);
            eWalletField.setVisible(false);
            revalidate();
            repaint();
        });

        payButton.addActionListener(e -> {



            String res=new String();
            if (deliveryButton.isSelected())
                res="Delivery";
            else
                res="Dine in";
            Order order=new Order(res,"Done",od);




            String paymentMethod;
            String paymentInfo;
            if (cashButton.isSelected())
            {
                paymentMethod="Cash";
                paymentInfo="No Info";
            }
            else if (cardButton.isSelected()) {
                paymentMethod = "Credit Card";
                paymentInfo = cardNumberField.getText();
            }
            else if (eWalletButton.isSelected()) {
                paymentMethod = "E-Wallet";
                paymentInfo = eWalletField.getText();
            } else {
                paymentMethod = null;
                paymentInfo = null;
            }

            if (paymentMethod == null) {
                JOptionPane.showMessageDialog(this, "Please select a payment method.");
            } else if ((cardButton.isSelected() && paymentInfo.isEmpty()) || (eWalletButton.isSelected() && paymentInfo.isEmpty())) {
                JOptionPane.showMessageDialog(this, "Please enter payment information.");
            } else {
                WelcomeLabel.lastorder.add(order);

                fr1.dispose();
                WelcomeLabel.totalincoming+=total+tax+dev;
                JFrame waitFrame = new JFrame("Processing Payment...");
                waitFrame.setSize(300, 100);
                waitFrame.setLocationRelativeTo(this);
                JLabel waitLabel = new JLabel("Processing payment, please wait...", SwingConstants.CENTER);
                waitFrame.add(waitLabel);
                waitFrame.setVisible(true);

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        waitFrame.dispose();
                        JOptionPane.showMessageDialog(CheckoutPanel.this, "Thank you for your order!\nTotal amount: $" + String.format("%.2f", total) + "\nPayment Method: " + paymentMethod + "\nPayment Info: " + (paymentInfo != null ? paymentInfo : ""));
                    }
                }, 3000); // 3 seconds delay for processing simulation


            }

        });

        eWalletButton.addActionListener(e -> {
            cardLabel.setVisible(false);
            cardNumberField.setVisible(false);
            eWalletLabel.setVisible(true);
            eWalletField.setVisible(true);
            revalidate();
            repaint();
        });


        deliveryButton.addActionListener(e -> {
            dev= 5.0;
            update(od);
        });

        dineInButton.addActionListener(e -> {
            dev= 0;
            update(od);
        });


        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));
        JScrollPane itemsScrollPane = new JScrollPane(itemsPanel);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.gridx = 00;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(scrollPane, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(new JLabel("Tip:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(tipBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(paymentPanel, gbc);


        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(orderstypePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;

        add(cardLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;

        add(cardNumberField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(eWalletLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 4;
        add(eWalletField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        add(itemsScrollPane, gbc);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tipBox) {
            String tipSelected = (String) tipBox.getSelectedItem();
            double tipPercentage = 0;
            switch (tipSelected) {
                case "10%":
                    tipPercentage = 0.1;
                    break;
                case "15%":
                    tipPercentage = 0.15;
                    break;
                case "20%":
                    tipPercentage = 0.2;
                    break;
            }
            tax = total * tipPercentage;
            update(od);
        }
    }

    void update(ArrayList<Meal> lt) {
        listModel.clear();
        for (Meal s : lt) {
            listModel.addElement(s.mealName + " "+s.maelPrise);
        }
        listModel.addElement("total is:"+" "+String.valueOf(tax+total+dev));
    }

}