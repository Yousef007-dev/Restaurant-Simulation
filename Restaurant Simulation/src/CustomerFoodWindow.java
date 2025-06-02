import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class CustomerFoodWindow extends JFrame {
    double dailyincome;
    DefaultListModel listModel = new DefaultListModel();
    List<FoodPanelInTheList>  foodPanelList = new ArrayList<>();
    List<Meal> mealsAvailable = new ArrayList<>();
    InputStream input = null;

    public CustomerFoodWindow() {
        dailyincome=0;
        List <Meal> order=new ArrayList<>();


        try {
            input = new FileInputStream("Meals");
            ObjectInputStream ois = new ObjectInputStream(input);
            mealsAvailable = (List<Meal>) ois.readObject();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        for (Meal meal :
                mealsAvailable) {
            foodPanelList.add(new FoodPanelInTheList(meal));
        }
        listModel.addAll(foodPanelList);


        setSize(500, 700);
        setTitle("Food Editing Window");
        setResizable(false);



        // Create the JList with a custom renderer
        JList<FoodPanelInTheList> foodList = new JList<>(listModel);
        foodList.setCellRenderer(new ListCellRenderer<FoodPanelInTheList>() {
            @Override
            public Component getListCellRendererComponent(JList<? extends FoodPanelInTheList> list,
                                                          FoodPanelInTheList value,
                                                          int index,
                                                          boolean isSelected,
                                                          boolean cellHasFocus) {
                // Set selection background/foreground if needed
                if (isSelected) {
                    value.setBackground(new Color(200, 200, 255)); // Light blue background
                } else {
                    value.setBackground(Color.WHITE); // Default white background
                }

                value.setOpaque(true); // Ensure background color is shown
                return value; // Return the panel as the rendered component
            }
        });

        //forces the user to only chose one cell
        foodList.setSelectedIndex(0);
        foodList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        foodList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Ensure event fires only after user finishes selection
                    FoodPanelInTheList selectedItem = foodList.getSelectedValue();
                    System.out.println("Selected Item: " + selectedItem);
                    // Perform an action with the selected item
                    System.out.println("Item Selected" );
                }
            }
        });
        // Add the JList to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(foodList);
        scrollPane.setBounds(30, 10, 420, 400);


        JButton showButton =Maker.ButtonMaker(85,450,130,75,"Show!",new Color(53, 168, 7));
        JButton AddButton =Maker.ButtonMaker(250,450,130,75,"Add to cart!",new Color(53, 168, 7));

        showButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerMealInfo foodSelectedToEdit = new CustomerMealInfo(foodList.getSelectedValue().getMeal(),order);
                foodSelectedToEdit.setVisible(true);
            }
        });

        AddButton.addActionListener(e -> {
            order.add(foodList.getSelectedValue().meal);
        });
        JButton GoButton =Maker.ButtonMaker(85,550,300,75,"Go to cart",new Color(168, 7, 7));
        GoButton.addActionListener(e -> {

            SwingUtilities.invokeLater(() -> {
                JFrame frame = new JFrame("Restaurant Order System");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(500, 700);
                frame.setResizable(false);
                JButton payButton = Maker.ButtonMaker(165,540,150,75,"Pay Now",new Color(250,150,0));


                CheckoutPanel checkoutPanel = new CheckoutPanel((ArrayList<Meal>) order,payButton,frame);


                JPanel mainPanel = new JPanel();

                mainPanel.add(checkoutPanel, "Checkout");



                frame.add(payButton);
                frame.add(mainPanel);
                frame.setVisible(true);

                dispose();

            });
        });



        // Add components to the frame
        setLayout(null);
        add(scrollPane);
        add(showButton);
        add(GoButton);
        add(AddButton);
        revalidate();
        repaint();
    }
}
