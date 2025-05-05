package view;

import controller.SimulationController;
import model.MemorySimulation.SimulationResult;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;

public class MainFrame extends JFrame {
    private final SimulationController controller;
    private JFormattedTextField frameCountField;
    private JTextField referenceStringField;
    private JTextArea resultsArea;
    private JButton simulateButton;
    
    public MainFrame() {
        super("Simulador de Algoritmos de Substituição de Páginas");
        this.controller = new SimulationController();
        initializeUI();
    }
    
    private void initializeUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout(10, 10));
        
        // Painel de entrada
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Parâmetros da Simulação"));
        
        inputPanel.add(new JLabel("Sequência de Referência:"));
        referenceStringField = new JTextField("1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5");
        inputPanel.add(referenceStringField);
        
        inputPanel.add(new JLabel("Número de Molduras (Frames):"));
        frameCountField = new JFormattedTextField(NumberFormat.getIntegerInstance());
        frameCountField.setValue(3);
        inputPanel.add(frameCountField);
        
        simulateButton = new JButton("Executar Simulação");
        simulateButton.addActionListener(this::executeSimulation);
        inputPanel.add(new JLabel());
        inputPanel.add(simulateButton);
        
        // Área de resultados
        resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        resultsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        
        // Adiciona componentes ao frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        
        // Exemplo de resultados iniciais
        resultsArea.setText("Resultados da simulação serão exibidos aqui...\n\n" +
                           "Digite a sequência de referência (ex: 1, 2, 3, 4) e o número de molduras, " +
                           "então clique em 'Executar Simulação'.");
    }
    
    private void executeSimulation(ActionEvent e) {
        try {
            int frameCount = ((Number) frameCountField.getValue()).intValue();
            if (frameCount <= 0) {
                throw new IllegalArgumentException("O número de molduras deve ser positivo");
            }
            
            int[] referenceString = SimulationController.parseReferenceString(referenceStringField.getText());
            
            SimulationResult[] results = controller.simulate(referenceString, frameCount);
            
            StringBuilder sb = new StringBuilder();
            sb.append("Resultados da Simulação:\n");
            sb.append(String.format("Sequência de referência: %s\n", referenceStringField.getText()));
            sb.append(String.format("Número de molduras: %d\n\n", frameCount));
            
            for (SimulationResult result : results) {
                sb.append(String.format("%s:\n", result.getAlgorithmName()));
                sb.append(String.format("  - Faltas de página: %d\n", result.getPageFaults()));
                sb.append(String.format("  - Descrição: %s\n\n", result.getDescription()));
            }
            
            resultsArea.setText(sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro na entrada: " + ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}