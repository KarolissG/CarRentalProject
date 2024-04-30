package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HeavyRentalSystem extends JFrame {
    private JPanel imagePanel;
    private JButton selectButton;
    private JLabel selectedImageLabel; // Track the selected image label

    private String[] heavyImagePaths = {
        "heavy1.jpg",
        "heavy2.jpg",
        "heavy3.jpg",
        "heavy4.jpg",
        "heavy5.jpg",
        "heavy6.jpg",
        "heavy7.jpg",
        "heavy8.jpg",
    };

    private String[] heavyModels = {
        "Caterpillar",
        "Fork lift",
        "School bus Monster truck",
        "Tractor",
        "Digger",
        "Flat bed truck",
        "Shmol truck",
        "Big truck",
    };

    public HeavyRentalSystem() {
        setTitle("Heavy Rental System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500); // Adjusted size
        setLocationRelativeTo(null);

        imagePanel = new JPanel();
        imagePanel.setLayout(new GridLayout(4, 2, 10, 10)); // 4 rows, 2 columns, with gaps of 10 pixels

        for (int i = 0; i < heavyImagePaths.length; i++) {
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(heavyImagePaths[i]));
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

            JLabel nameLabel = new JLabel(heavyModels[i], SwingConstants.CENTER); // Label to display heavy model
            nameLabel.setForeground(Color.BLUE); // Set text color to blue

            JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Panel to hold the text label
            labelPanel.add(nameLabel);

            JPanel imagePanelWithText = new JPanel(new BorderLayout());
            imagePanelWithText.add(imageLabel, BorderLayout.CENTER);
            imagePanelWithText.add(labelPanel, BorderLayout.SOUTH);

            imagePanel.add(imagePanelWithText);
        }

        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

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
                    String selectedModel = heavyModels[index];
                    // Show message and redirect to InsuranceSelectionPage
                    JOptionPane.showMessageDialog(null, selectedModel + " has been selected", "Heavy Rental Selected", JOptionPane.INFORMATION_MESSAGE);
                    InsuranceSelectionPage insurancePage = new InsuranceSelectionPage();
                    insurancePage.setVisible(true);
                    dispose(); // Close the current window
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a heavy first.", "No Heavy Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(selectButton, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                HeavyRentalSystem heavyRentalSystem = new HeavyRentalSystem();
                heavyRentalSystem.setVisible(true);
            }
        });
    }
}
