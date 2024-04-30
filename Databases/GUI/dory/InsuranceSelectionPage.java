import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InsuranceSelectionPage extends JFrame {
    private JRadioButton noInsuranceRadioButton;
    private JRadioButton thirdPartyTheftRadioButton;
    private JRadioButton fullyComprehensiveRadioButton;
    private JButton selectButton;

    public InsuranceSelectionPage() {
        setTitle("Insurance Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600); // Adjusted size
        setLocationRelativeTo(null);

        // Load image
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("insurance.jpg"));
        // Resize image
        Image image = imageIcon.getImage().getScaledInstance(400, -1, Image.SCALE_SMOOTH);
        ImageIcon resizedImageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(resizedImageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create radio buttons
        noInsuranceRadioButton = new JRadioButton("No Insurance");
        thirdPartyTheftRadioButton = new JRadioButton("3rd Party & Theft");
        fullyComprehensiveRadioButton = new JRadioButton("Fully Comprehensive");

        // Group the radio buttons
        ButtonGroup group = new ButtonGroup();
        group.add(noInsuranceRadioButton);
        group.add(thirdPartyTheftRadioButton);
        group.add(fullyComprehensiveRadioButton);

        // Create select button
        selectButton = new JButton("Select");

        // Create disclaimer text
        JLabel disclaimerLabel = new JLabel("Disclaimer: When renting with no insurance, the person renting must have 3rd party insurance in their own name");
        disclaimerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create additional text
        JLabel additionalTextLabel = new JLabel("Heavy vehicles can be insured on a company if needed");
        additionalTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Set font for all components
        Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 16);
        imageLabel.setFont(font);
        noInsuranceRadioButton.setFont(font);
        thirdPartyTheftRadioButton.setFont(font);
        fullyComprehensiveRadioButton.setFont(font);
        selectButton.setFont(font);
        disclaimerLabel.setFont(font);
        additionalTextLabel.setFont(font);

        // Create panels for components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(imageLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(noInsuranceRadioButton);
        mainPanel.add(thirdPartyTheftRadioButton);
        mainPanel.add(fullyComprehensiveRadioButton);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(disclaimerLabel);
        mainPanel.add(additionalTextLabel);
        mainPanel.add(Box.createVerticalStrut(20));
        mainPanel.add(selectButton);

        // Add action listener for select button
        selectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedInsurance = "";
                if (noInsuranceRadioButton.isSelected()) {
                    selectedInsurance = "No Insurance";
                } else if (thirdPartyTheftRadioButton.isSelected()) {
                    selectedInsurance = "3rd Party & Theft";
                } else if (fullyComprehensiveRadioButton.isSelected()) {
                    selectedInsurance = "Fully Comprehensive";
                }
                JOptionPane.showMessageDialog(null, "You selected: " + selectedInsurance);
            }
        });

        // Add main panel to the frame
        getContentPane().add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                InsuranceSelectionPage insurancePage = new InsuranceSelectionPage();
                insurancePage.setVisible(true);
            }
        });
    }
}
