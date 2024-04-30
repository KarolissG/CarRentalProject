package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class VehicleTypeSelection extends JFrame {
    private JLabel selectedLabel; // Track the selected label
    private JButton selectButton;

    public VehicleTypeSelection() {
        setTitle("Please choose vehicle type");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400); // Increased size of the window
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns, with gaps of 10 pixels

        ImageIcon bmwIcon = new ImageIcon(getClass().getResource("bmw.jpg"));
        ImageIcon otherIcon = new ImageIcon(getClass().getResource("other.jpg"));

        JLabel bmwLabel = new JLabel(bmwIcon, JLabel.CENTER);
        JLabel otherLabel = new JLabel(otherIcon, JLabel.CENTER);

        // Add mouse listener to each label
        MouseAdapter labelMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Deselect the previously selected label
                if (selectedLabel != null) {
                    selectedLabel.setBorder(null);
                }
                // Highlight the clicked label
                selectedLabel = (JLabel) e.getSource();
                selectedLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
            }
        };
        bmwLabel.addMouseListener(labelMouseListener);
        otherLabel.addMouseListener(labelMouseListener);

        JLabel bmwTextLabel = new JLabel("BMW", JLabel.CENTER);
        JLabel otherTextLabel = new JLabel("Other", JLabel.CENTER);

        mainPanel.add(bmwLabel);
        mainPanel.add(otherLabel);
        mainPanel.add(bmwTextLabel);
        mainPanel.add(otherTextLabel);

        selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action when "Select" button is clicked
                if (selectedLabel != null) {
                    String selectedOption = selectedLabel == bmwLabel ? "BMW" : "Other";
                    // Redirect based on selection
                    if (selectedOption.equals("BMW")) {
                        // Redirect to CarRentalSystem
                        CarRentalSystem carRentalSystem = new CarRentalSystem();
                        carRentalSystem.setVisible(true);
                        dispose(); // Close the current window
                    } else {
                        // Redirect to HeavyRentalSystem
                        HeavyRentalSystem heavyRentalSystem = new HeavyRentalSystem();
                        heavyRentalSystem.setVisible(true);
                        dispose(); // Close the current window
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please choose a vehicle type first.");
                }
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        getContentPane().add(selectButton, BorderLayout.SOUTH); // Add select button to the south position
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                VehicleTypeSelection vehicleTypeSelection = new VehicleTypeSelection();
                vehicleTypeSelection.setVisible(true);
            }
        });
    }

    public class java {
    }
}
