package javaricci.com.br;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class DataTelaApuracaoEntreDatas {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(DataTelaApuracaoEntreDatas::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Aplicativo Apura Dias da Semana");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        JLabel startDateLabel = new JLabel("Data Inicial (dd/MM/yyyy):");
        c.gridx = 0;
        c.gridy = 0;
        panel.add(startDateLabel, c);
        
        JFormattedTextField startDateField = new JFormattedTextField(new DateFormatter(new SimpleDateFormat("dd/MM/yyyy")));
        startDateField.setColumns(10);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(startDateField, c);
        
        JLabel endDateLabel = new JLabel("Data Final (dd/MM/yyyy):");
        c.gridx = 0;
        c.gridy = 1;
        panel.add(endDateLabel, c);
        
        JFormattedTextField endDateField = new JFormattedTextField(new DateFormatter(new SimpleDateFormat("dd/MM/yyyy")));
        endDateField.setColumns(10);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(endDateField, c);
        
        JLabel dayOfWeekLabel = new JLabel("Selecionar Dia da Semana:");
        c.gridx = 0;
        c.gridy = 2;
        panel.add(dayOfWeekLabel, c);
        
        String[] daysOfWeek = {"segunda-feira", "terça-feira", "quarta-feira", "quinta-feira", "sexta-feira", "sábado", "domingo"};
        JComboBox<String> dayOfWeekComboBox = new JComboBox<>(daysOfWeek);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(dayOfWeekComboBox, c);
        
        JButton submitButton = new JButton("Apurar");
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.insets = new Insets(10, 0, 0, 0);
        panel.add(submitButton, c);
        
        JTextArea resultTextArea = new JTextArea(10, 40);
        resultTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultTextArea);
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.insets = new Insets(10, 0, 0, 0);
        panel.add(scrollPane, c);

        JLabel countLabel = new JLabel("Qtd Dias:");
        c.gridx = 0;
        c.gridy = 5;
        panel.add(countLabel, c);

        JTextField countField = new JTextField(10);
        countField.setEditable(false);
        c.gridx = 1;
        c.gridy = 5;
        panel.add(countField, c);
        
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date startDate = sdf.parse(startDateField.getText());
                    Date endDate = sdf.parse(endDateField.getText());
                    String selectedDayOfWeek = (String) dayOfWeekComboBox.getSelectedItem();
                    
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(startDate);
                    
                    List<Date> datesInRange = new ArrayList<>();
                    while (!calendar.getTime().after(endDate)) {
                        datesInRange.add(calendar.getTime());
                        calendar.add(Calendar.DAY_OF_MONTH, 1);
                    }
                    
                    List<Date> filteredDates = datesInRange.stream()
                            .filter(date -> {
                                calendar.setTime(date);
                                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                                return dayOfWeek == getDayOfWeek(selectedDayOfWeek);
                            })
                            .collect(Collectors.toList());
                    
                    StringBuilder result = new StringBuilder();
                    for (Date date : filteredDates) {
                        result.append(sdf.format(date)).append(" - ").append(selectedDayOfWeek).append("\n");
                    }
                    
                    resultTextArea.setText(result.toString());
                    countField.setText(String.valueOf(filteredDates.size()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao processar datas: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }

            private int getDayOfWeek(String dayOfWeek) {
                switch (dayOfWeek) {
                    case "segunda-feira":
                        return Calendar.MONDAY;
                    case "terça-feira":
                        return Calendar.TUESDAY;
                    case "quarta-feira":
                        return Calendar.WEDNESDAY;
                    case "quinta-feira":
                        return Calendar.THURSDAY;
                    case "sexta-feira":
                        return Calendar.FRIDAY;
                    case "sábado":
                        return Calendar.SATURDAY;
                    case "domingo":
                        return Calendar.SUNDAY;
                    default:
                        throw new IllegalArgumentException("Dia da semana inválido: " + dayOfWeek);
                }
            }
        });
        
        frame.add(panel);
        frame.setVisible(true);
    }
}
