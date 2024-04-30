package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CarRentalSystem extends JFrame {
    private JPanel imagePanel;
    private JButton selectButton;
    private JLabel selectedImageLabel; // Track the selected image label

    private String[] carImagePaths = {
        "car1.jpg",
        "car2.jpg",
        "car3.jpg",
        "car4.jpg",
        "car5.jpg",
        "car6.jpg",
        "car7.jpg",
        "car8.jpg",
        "car9.jpg",
        "car10.jpg",
        "car11.jpg",
        "car12.jpg",
        "car13.jpg"
    };

    private String[] carModels = {
        "BMW E36",
        "BMW E38",
        "BMW E39",
        "BMW E46",
        "BMW E60",
        "BMW E90",
        "BMW F10",
        "BMW F30",
        "BMW F82",
        "BMW E21",
        "BMW 28",
        "BMW E30",
        "BMW E34"
    };

    public CarRentalSystem() {
        setTitle("Car Rental System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 800); // Adjusted size to fit 7 x 2 grid
        setLocationRelativeTo(null);

        imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(7, 2, 10, 10)); // 7 rows, 2 columns, with gaps of 10 pixels

        for (int i = 0; i < carImagePaths.length; i++) {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(carImagePaths[i]));
            Image image = imageIcon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH); // Adjusted image size
            imageIcon = new ImageIcon(image);
            JLabel imageLabel = new JLabel(imageIcon);

            // Add mouse listener to each image label
            imageLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Deselect the previously selected image label
                    if (selectedImageLabel != null) {
                        selectedImageLabel.setBorder(null);
                    }
                    // Highlight the clicked image label
                    selectedImageLabel = (JLabel) e.getSource();
                    selectedImageLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
                }
            });

            JLabel nameLabel = new JLabel(carModels[i], SwingConstants.CENTER); // Label to display car model
            nameLabel.setForeground(Color.BLUE); // Set text color to blue

            JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel to hold the text label
            labelPanel.add(nameLabel);

            JPanel imagePanelWithText = new JPanel(new BorderLayout());
            imagePanelWithText.add(imageLabel, BorderLayout.CENTER);
            imagePanelWithText.add(labelPanel, BorderLayout.SOUTH);

            imagePanel.add(imagePanelWithText);
        }

        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action when "Select" button is clicked
                if (selectedImageLabel != null) {
                    int index = -1;
                    for (int i = 0; i < imagePanel.getComponentCount(); i++) {
                        Component comp = imagePanel.getComponent(i);
                        if (comp instanceof JPanel) {
                            JPanel panel = (JPanel) comp;
                            Component[] components = panel.getComponents();
                            for (Component c : components) {
                                if (c == selectedImageLabel) {
                                    index = i;
                                    break;
                                }
                            }
                            if (index != -1) {
                                break;
                            }
                        }
                    }
                    String selectedModel = carModels[index];
                    // Show message and redirect to InsuranceSelectionPage
                    JOptionPane.showMessageDialog(null, selectedModel + " has been selected", "Car Rental Selected", JOptionPane.INFORMATION_MESSAGE);
                    InsuranceSelectionPage insurancePage = new InsuranceSelectionPage();
                    insurancePage.setVisible(true);
                    dispose(); // Close the current window
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a car first.", "No Car Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(selectButton);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    
    /** 
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CarRentalSystem carRentalSystem = new CarRentalSystem();
                carRentalSystem.setVisible(true);
            }
        });
    }
}
