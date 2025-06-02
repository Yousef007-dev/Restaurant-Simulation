import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class EditTheFoodFrame extends JFrame {
    DefaultListModel listModel = new DefaultListModel();
    List<FoodPanelInTheList>  foodPanelList = new ArrayList<>();
    List<Meal> mealsAvailable = new ArrayList<>();
    InputStream input = null;

    public EditTheFoodFrame() {

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


        // Add the JList to a JScrollPane
        JScrollPane scrollPane = new JScrollPane(foodList);
        scrollPane.setBounds(30, 10, 420, 400);


        JButton editButton =ButtonMarker.ButtonMaker(85,450,300,75,"Edit",new Color(53, 168, 7));
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FoodSelectedToEdit foodSelectedToEdit = new FoodSelectedToEdit(mealsAvailable,foodList.getSelectedValue().getMeal());
                foodSelectedToEdit.setVisible(true);
                dispose();
            }
        });
        JButton deleteButton =ButtonMarker.ButtonMaker(85,550,300,75,"Back",new Color(168, 7, 7));
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();;
            }
        });


        // Add components to the frame
        setLayout(null);
        add(scrollPane);
        add(editButton);
        add(deleteButton);
        revalidate();
        repaint();
    }


}
