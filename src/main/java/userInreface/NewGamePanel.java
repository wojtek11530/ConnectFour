package userInreface;

import ai.*;
import game.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

public class NewGamePanel extends JPanel {


    private GUI parentGUI;

    private final Color BACKGROUND_COLOR = new java.awt.Color(240, 234, 220);

    private final PlayerComboBox playerOneComboBox;
    private final PlayerComboBox playerTwoComboBox;

    private final JPanel playerOneAiPanel;
    private final PlayerAiHeuristicComboBox playerOneAiHeuristicComboBox;
    private final LineEvalPlayerPanel lineEvalPlayerOnePanel;
    private final DepthEditComponent playerOneAiDepthEdit;

    private final JPanel playerTwoAiPanel;
    private final PlayerAiHeuristicComboBox playerTwoAiHeuristicComboBox;
    private final LineEvalPlayerPanel lineEvalPlayerTwoPanel;
    private final DepthEditComponent playerTwoAiDepthEdit;

    private URL smallYellowTokenUrl = getClass().getResource("/images/YellowTokenSmall.png");
    private ImageIcon smallYellowTokenImage = new ImageIcon(smallYellowTokenUrl);
    private URL smallRedToken = getClass().getResource("/images/RedTokenSmall.png");
    private ImageIcon smallRedTokenImage = new ImageIcon(smallRedToken);


    private URL newGameBtnImgUrl = getClass().getResource("/images/StartGameBtn.png");
    private ImageIcon newGameBtnImage = new ImageIcon(newGameBtnImgUrl);
    private URL focusNewGameBtnImgUrl = getClass().getResource("/images/FocusStartGameBtn.png");
    private ImageIcon focusNewGameBtnImage = new ImageIcon(focusNewGameBtnImgUrl);

    public NewGamePanel(int width, int height, GUI gui) {
        super();

        parentGUI = gui;
        setSize(width, height);
        setLayout(new BorderLayout());
        setBackground(BACKGROUND_COLOR);

        JLabel titleLabel = new JLabel();
        titleLabel.setText("CONNECT FOUR");
        titleLabel.setFont(new Font("Verdana", Font.PLAIN, 48));
        titleLabel.setForeground(new Color(10, 78, 169));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(BACKGROUND_COLOR);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        JPanel playerOneSettingPanel = new JPanel();
        playerOneSettingPanel.setBackground(BACKGROUND_COLOR);
        playerOneSettingPanel.setAlignmentX(CENTER_ALIGNMENT);
        JPanel yellowPLayerPanel = new JPanel();
        yellowPLayerPanel.setLayout(new BorderLayout());
        yellowPLayerPanel.setBackground(BACKGROUND_COLOR);

        JLabel imageYellowTokenLabel = new JLabel(smallYellowTokenImage);
        JLabel playerOneLabel = new TextLabel("Player 1:");
        playerOneComboBox = new PlayerComboBox();

        yellowPLayerPanel.add(imageYellowTokenLabel, BorderLayout.LINE_START);
        yellowPLayerPanel.add(playerOneLabel, BorderLayout.CENTER);

        playerOneSettingPanel.add(yellowPLayerPanel);
        playerOneSettingPanel.add(playerOneComboBox);

        JPanel playerTwoSettingPanel = new JPanel();
        playerTwoSettingPanel.setAlignmentX(CENTER_ALIGNMENT);
        playerTwoSettingPanel.setBackground(BACKGROUND_COLOR);
        JPanel redPLayerPanel = new JPanel();
        redPLayerPanel.setBackground(BACKGROUND_COLOR);
        redPLayerPanel.setLayout(new BorderLayout());

        JLabel imageRedTokenLabel = new JLabel(smallRedTokenImage);
        JLabel playerTwoLabel = new TextLabel("Player 2:");
        playerTwoComboBox = new PlayerComboBox();

        redPLayerPanel.add(imageRedTokenLabel, BorderLayout.LINE_START);
        redPLayerPanel.add(playerTwoLabel, BorderLayout.CENTER);

        playerTwoSettingPanel.add(redPLayerPanel);
        playerTwoSettingPanel.add(playerTwoComboBox);

        JPanel playersAiPanel = new JPanel();
        playersAiPanel.setLayout(new BorderLayout());
        playersAiPanel.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        playersAiPanel.setBackground(BACKGROUND_COLOR);

        playerOneAiPanel = new JPanel();
        playerOneAiPanel.setLayout(new BoxLayout(playerOneAiPanel, BoxLayout.Y_AXIS));
        playerOneAiPanel.setBackground(BACKGROUND_COLOR);
        playerOneAiPanel.setPreferredSize(new Dimension(220, 300));
        playerOneAiPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(10, 78, 169), 2, true),
                "Player One AI",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Verdana", Font.PLAIN, 24),
                new Color(10, 78, 169)));


        JPanel playerOneAiDepthPanel = new JPanel();
        playerOneAiDepthPanel.setBackground(BACKGROUND_COLOR);
        playerOneAiDepthPanel.setLayout(new BoxLayout(playerOneAiDepthPanel, BoxLayout.X_AXIS));
        JLabel depthOneLabel = new TextLabel("Depth: ", 160, 30, 20);
        playerOneAiDepthEdit = new DepthEditComponent(3);
        playerOneAiDepthPanel.add(depthOneLabel);
        playerOneAiDepthPanel.add(playerOneAiDepthEdit);
        playerOneAiDepthPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        playerOneAiHeuristicComboBox = new PlayerAiHeuristicComboBox();

        lineEvalPlayerOnePanel = new LineEvalPlayerPanel();

        playerOneAiPanel.add(playerOneAiDepthPanel);
        playerOneAiPanel.add(playerOneAiHeuristicComboBox);
        playerOneAiPanel.add(lineEvalPlayerOnePanel);

        JPanel dummyPanelOne = new JPanel();
        dummyPanelOne.setSize(new Dimension(220, 300));
        dummyPanelOne.setBackground(BACKGROUND_COLOR);

        playerOneComboBox.setPlayerAiPanel(playerOneAiPanel);
        playerOneComboBox.setDummyPanel(dummyPanelOne);
        playerOneComboBox.setLeft(true);
        playerOneComboBox.setParentPanel(playersAiPanel);

        playerTwoAiPanel = new JPanel();
        playerTwoAiPanel.setLayout(new BoxLayout(playerTwoAiPanel, BoxLayout.Y_AXIS));
        playerTwoAiPanel.setBackground(BACKGROUND_COLOR);
        playerTwoAiPanel.setPreferredSize(new Dimension(220, 300));
        playerTwoAiPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(10, 78, 169), 2, true),
                "Player Two AI",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Verdana", Font.PLAIN, 24),
                new Color(10, 78, 169)));


        JPanel playerTwoAiDepthPanel = new JPanel();
        playerTwoAiDepthPanel.setBackground(BACKGROUND_COLOR);
        playerTwoAiDepthPanel.setLayout(new BoxLayout(playerTwoAiDepthPanel, BoxLayout.X_AXIS));

        JLabel depthTwoLabel = new TextLabel("Depth: ", 160, 30, 20);
        playerTwoAiDepthEdit = new DepthEditComponent(3);
        playerTwoAiDepthPanel.add(depthTwoLabel);
        playerTwoAiDepthPanel.add(playerTwoAiDepthEdit);
        playerTwoAiDepthPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        playerTwoAiHeuristicComboBox = new PlayerAiHeuristicComboBox();

        lineEvalPlayerTwoPanel = new LineEvalPlayerPanel();
        playerTwoAiPanel.add(playerTwoAiDepthPanel);
        playerTwoAiPanel.add(playerTwoAiHeuristicComboBox);
        playerTwoAiPanel.add(lineEvalPlayerTwoPanel);

        JPanel dummyPanelTwo = new JPanel();
        dummyPanelTwo.setSize(new Dimension(220, 300));
        dummyPanelTwo.setBackground(BACKGROUND_COLOR);

        playerTwoComboBox.setPlayerAiPanel(playerTwoAiPanel);
        playerTwoComboBox.setDummyPanel(dummyPanelTwo);
        playerTwoComboBox.setLeft(false);
        playerTwoComboBox.setParentPanel(playersAiPanel);

//        playerOneAiPanel.setVisible(false);
//        playerTwoAiPanel.setVisible(false);
//        dummyPanelOne.setVisible(true);
//        dummyPanelTwo.setVisible(true);

//        playersAiPanel.add(playerOneAiPanel, BorderLayout.LINE_START);
//        playersAiPanel.add(playerTwoAiPanel, BorderLayout.LINE_END);

        playersAiPanel.add(dummyPanelOne, BorderLayout.LINE_START);
        playersAiPanel.add(dummyPanelTwo, BorderLayout.LINE_END);

//        playersAiPanel.add(playerOneAiPanel);
//        playersAiPanel.add(dummyPanelOne);
////        playersAiPanel.add(Box.createHorizontalStrut(10));
//        playersAiPanel.add(playerTwoAiPanel);
//        playersAiPanel.add(dummyPanelTwo);

        centerPanel.add(playerOneSettingPanel);
        centerPanel.add(playerTwoSettingPanel);
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(playersAiPanel);

        JPanel lowerPanel = new JPanel();
        lowerPanel.setBackground(BACKGROUND_COLOR);
        JButton startGameBtn = new JButton();

        startGameBtn.setHorizontalAlignment(JLabel.CENTER);
        startGameBtn.setVerticalAlignment(JLabel.CENTER);
        startGameBtn.setIcon(newGameBtnImage);
        startGameBtn.setBorder(BorderFactory.createEmptyBorder());
        startGameBtn.setContentAreaFilled(false);

        startGameBtn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                JButton btn = (JButton) e.getComponent();
                btn.setIcon(newGameBtnImage);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JButton btn = (JButton) e.getComponent();
                btn.setIcon(focusNewGameBtnImage);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                JButton btn = (JButton) e.getComponent();
                btn.setIcon(newGameBtnImage);
            }
        });

        startGameBtn.addActionListener(new StartBtmActionListener(this));

        lowerPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        lowerPanel.add(startGameBtn);

        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);
    }

    private class PlayerComboBox extends JComboBox<String> implements ActionListener {

        private JPanel playerAiPanel;
        private JPanel dummyPanel;
        private boolean left;
        private JPanel parentPanel;

        public PlayerComboBox() {
            super();
            addItem("Human");
            addItem("MinMax");
            addItem("Alpha Beta");
            addItem("MinMax Middle Col Start");
            addItem("Alpha Beta Middle Col Start");
            setFont(new Font("Verdana", Font.PLAIN, 18));
            setPreferredSize(new Dimension(260, 50));
            addActionListener(this);
        }

        public void setPlayerAiPanel(JPanel playerAiPanel) {
            this.playerAiPanel = playerAiPanel;
        }

        public void setDummyPanel(JPanel dummyPanel) {
            this.dummyPanel = dummyPanel;
        }

        public void setLeft(boolean left) {
            this.left = left;
        }

        public void setParentPanel(JPanel parentPanel) {
            this.parentPanel = parentPanel;
        }

        public void actionPerformed(ActionEvent e) {
            JComboBox cb = (JComboBox) e.getSource();
            String playerKind = (String) cb.getSelectedItem();

            if (playerKind.equals("Human")) {
                parentPanel.remove(playerAiPanel);
                if (left) {
                    parentPanel.add(dummyPanel, BorderLayout.LINE_START);
                } else {
                    parentPanel.add(dummyPanel, BorderLayout.LINE_END);
                }
            } else {
                parentPanel.remove(dummyPanel);
                if (left) {
                    parentPanel.add(playerAiPanel, BorderLayout.LINE_START);
                } else {
                    parentPanel.add(playerAiPanel, BorderLayout.LINE_END);
                }
            }
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    parentPanel.revalidate();
                    parentPanel.repaint();
                }
            });

        }


    }

    private class PlayerAiHeuristicComboBox extends JComboBox<String> implements ActionListener {
        public PlayerAiHeuristicComboBox() {
            super();
            addItem("Line evaluator");
            setFont(new Font("Verdana", Font.PLAIN, 20));
            setBackground(BACKGROUND_COLOR);
            setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        }
    }

    private class TextLabel extends JLabel {
        public TextLabel(String string, int width, int height, int fontSize) {
            super(string);
            setFont(new Font("Verdana", Font.PLAIN, fontSize));
            setForeground(new Color(10, 78, 169));
            setPreferredSize(new Dimension(width, height));
            setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 0));
        }

        public TextLabel(String string) {
            this(string, 150, 50, 28);
        }
    }

    private class TextField extends JTextField {
        public TextField() {
            super(5);
            setFont(new Font("Verdana", Font.PLAIN, 28));
            setMaximumSize(new Dimension(10, 50));
        }

        public TextField(int columns, int fontSize) {
            super(columns);
            setFont(new Font("Verdana", Font.PLAIN, fontSize));
            setMaximumSize(new Dimension(10, 50));
        }
    }

    private class ParameterEditComponent extends JPanel {

        private JTextField parameterValueEdit;

        public ParameterEditComponent(String parameterName, int defaultValue) {
            super();
            setBackground(BACKGROUND_COLOR);
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            String text = "<html><p style=\"width: 70px\">" + parameterName + "</p></html>";
            JLabel parameterLabel = new TextLabel(text, 70, 75, 20);
            parameterLabel.setHorizontalAlignment(SwingConstants.LEFT);
            parameterValueEdit = new TextField(3, 22);
            parameterValueEdit.setText(String.valueOf(defaultValue));
            parameterValueEdit.setHorizontalAlignment(SwingConstants.RIGHT);
            parameterValueEdit.setBorder(BorderFactory.createCompoundBorder(
                    parameterValueEdit.getBorder(),
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));
            add(parameterLabel);
            add(parameterValueEdit);
        }

        public JTextField getParameterValueEdit() {
            return parameterValueEdit;
        }
    }

    private class DepthEditComponent extends JTextField {

        public DepthEditComponent(int defaultValue) {
            super(String.valueOf(defaultValue));
            setFont(new Font("Verdana", Font.PLAIN, 20));
            setHorizontalAlignment(SwingConstants.RIGHT);
            setBorder(BorderFactory.createCompoundBorder(
                    getBorder(),
                    BorderFactory.createEmptyBorder(2, 2, 2, 2)));

        }
    }


    private class LineEvalPlayerPanel extends JPanel {

        ParameterEditComponent fourInLinePlayerParameterEditor;
        ParameterEditComponent threeInLinePlayerParameterEditor;
        ParameterEditComponent twoInLinePlayerParameterEditor;

        public LineEvalPlayerPanel() {
            super();
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBackground(BACKGROUND_COLOR);
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            fourInLinePlayerParameterEditor = new ParameterEditComponent("4-in-line weight", 1000);
            threeInLinePlayerParameterEditor = new ParameterEditComponent("3-in-line weight", 10);
            twoInLinePlayerParameterEditor = new ParameterEditComponent("2-in-line weight", 1);
            add(fourInLinePlayerParameterEditor);
            add(threeInLinePlayerParameterEditor);
            add(twoInLinePlayerParameterEditor);
        }
    }


    private class StartBtmActionListener implements ActionListener {

        JPanel panel;

        public StartBtmActionListener(NewGamePanel newGamePanel) {
            this.panel = newGamePanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Player playerOne = getPlayerFromSettings(playerOneComboBox, Token.YELLOW, playerOneAiDepthEdit,
                    playerOneAiHeuristicComboBox, lineEvalPlayerOnePanel);
            Player playerTwo = getPlayerFromSettings(playerTwoComboBox, Token.RED, playerTwoAiDepthEdit,
                    playerTwoAiHeuristicComboBox, lineEvalPlayerTwoPanel);

            if (playerOne == null || playerTwo == null) {
                JOptionPane.showMessageDialog(panel, "Incorrect parameters given. Try again.", "Wrong input",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                ConnectFourGame game = new ConnectFourGame(playerOne, playerTwo);
                parentGUI.setGame(game);
            }
        }
    }


    private GameStateEvaluator getPlayerAiGameEvaluatorFromSettings(LineEvalPlayerPanel lineEvalPlayerPanel) {
        try {
            int fourInLineWeight = Integer.parseInt(lineEvalPlayerPanel.fourInLinePlayerParameterEditor.getParameterValueEdit().getText());
            int threeInLineWeight = Integer.parseInt(lineEvalPlayerPanel.threeInLinePlayerParameterEditor.getParameterValueEdit().getText());
            int twoInLineWeight = Integer.parseInt(lineEvalPlayerPanel.twoInLinePlayerParameterEditor.getParameterValueEdit().getText());
            return new GameStateEvaluatorImpl(fourInLineWeight, threeInLineWeight, twoInLineWeight);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Player getPlayerFromSettings(PlayerComboBox playerComboBox, Token token,
                                         DepthEditComponent playerAiDepthEdit,
                                         PlayerAiHeuristicComboBox playerAiHeuristicComboBox,
                                         LineEvalPlayerPanel lineEvalPlayerPanel) {
        Player newPlayer;
        String playerSetting = (String) playerComboBox.getSelectedItem();
        assert playerSetting != null;

        if (playerSetting.equals("Human")) {
            newPlayer = new HumanPlayer(token, PlayerType.HUMAN);
        } else {
            GameStateEvaluator evaluator = getPlayerAiGameEvaluatorFromSettings(lineEvalPlayerPanel);
            if (evaluator == null) {
                return null;
            }
            try {
                newPlayer = new ComputerPlayer(token, PlayerType.AI);
                int maxDepth = Integer.parseInt(playerAiDepthEdit.getText());
                AI ai = null;
                switch (playerSetting) {
                    case "MinMax":
                        ai = new MinMaxAI(maxDepth, evaluator);
                        break;
                    case "Alpha Beta":
                        ai = new AlphaBetaAI(maxDepth, evaluator);
                        break;
                    case "MinMax Middle Col Start":
                        ai = new MinMaxMiddleColStartAI(maxDepth, evaluator);
                        break;
                    default:
                        ai = new AlphaBetaMiddleColStartAI(maxDepth, evaluator);
                        break;
                }
                ((ComputerPlayer) newPlayer).setAi(ai);
            } catch (NumberFormatException e) {
                return null;
            }

        }
        return newPlayer;
    }



}
