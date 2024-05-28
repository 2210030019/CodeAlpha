package view;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import controller.HotelReservationController;
import models.Room;
import service.PaymentService;
import service.ReservationService;
import service.RoomService;
import service.UserService;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HotelReservationGUI extends JFrame {
    private HotelReservationController controller;
    private JComboBox<Room> roomComboBox;
    private JTextField userIdField;
    private JTextField checkInField;
    private JTextField checkOutField;
    private JTextField cardNumberField;
    private JTextField expiryDateField;
    private JTextField cvvField;

    public HotelReservationGUI(HotelReservationController controller) {
        this.controller = controller;
        setTitle("Hotel Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        createGUI();
    }

    private void createGUI() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Room Search Panel
        JPanel searchPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        searchPanel.add(new JLabel("Room Category:"));
        String[] categories = {"Single", "Double", "Suite"};
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);
        searchPanel.add(categoryComboBox);
        searchPanel.add(new JLabel("Check-in Date:"));
        checkInField = new JTextField();
        searchPanel.add(checkInField);
        searchPanel.add(new JLabel("Check-out Date:"));
        checkOutField = new JTextField();
        searchPanel.add(checkOutField);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> {
            String selectedCategory = (String) categoryComboBox.getSelectedItem();
            Date checkInDate = parseDate(checkInField.getText());
            Date checkOutDate = parseDate(checkOutField.getText());
            if (checkInDate != null && checkOutDate != null) {
                List<Room> availableRooms = controller.searchAvailableRooms(selectedCategory, checkInDate, checkOutDate);
                updateRoomComboBox(availableRooms);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter valid check-in and check-out dates.");
            }
        });
        searchPanel.add(searchButton);

        // Reservation Panel
        JPanel reservationPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        reservationPanel.add(new JLabel("Room:"));
        roomComboBox = new JComboBox<>();
        reservationPanel.add(roomComboBox);
        reservationPanel.add(new JLabel("User ID:"));
        userIdField = new JTextField();
        reservationPanel.add(userIdField);
        reservationPanel.add(new JLabel("Card Number:"));
        cardNumberField = new JTextField();
        reservationPanel.add(cardNumberField);
        reservationPanel.add(new JLabel("Expiry Date (MM/YY):"));
        expiryDateField = new JTextField();
        reservationPanel.add(expiryDateField);
        reservationPanel.add(new JLabel("CVV:"));
        cvvField = new JTextField();
        reservationPanel.add(cvvField);
        JButton reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(e -> {
            Room selectedRoom = (Room) roomComboBox.getSelectedItem();
            if (selectedRoom != null) {
                int selectedRoomId = selectedRoom.getRoomId();
                int userId = Integer.parseInt(userIdField.getText());
                Date checkInDate = parseDate(checkInField.getText());
                Date checkOutDate = parseDate(checkOutField.getText());
                String cardNumber = cardNumberField.getText();
                String expiryDate = expiryDateField.getText();
                String cvv = cvvField.getText();
                if (checkInDate != null && checkOutDate != null) {
                    boolean reservationSuccess = controller.makeReservation(selectedRoomId, userId, checkInDate, checkOutDate, 100.0, cardNumber, expiryDate, cvv);
                    if (reservationSuccess) {
                        JOptionPane.showMessageDialog(this, "Reservation successful!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Reservation failed.");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please enter valid check-in and check-out dates.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a room.");
            }
        });
        reservationPanel.add(reserveButton);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(reservationPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void updateRoomComboBox(List<Room> rooms) {
        roomComboBox.removeAllItems();
        for (Room room : rooms) {
            roomComboBox.addItem(room);
        }
    }

    private Date parseDate(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        RoomService roomService = new RoomService();
        ReservationService reservationService = new ReservationService();
        UserService userService = new UserService();
        PaymentService paymentService = new PaymentService();
        HotelReservationController controller = new HotelReservationController(roomService, reservationService, userService, paymentService);
        HotelReservationGUI gui = new HotelReservationGUI(controller);
        gui.setVisible(true);
    }
}