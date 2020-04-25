package userInreface;

import ai.AI;
import ai.GameStateEvaluator;
import ai.GameStateEvaluatorImpl;
import ai.MinMaxAI;
import game.*;

import javax.swing.*;
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

    private ParameterEditComponent fourInLineParameterEditor;
    private ParameterEditComponent threeInLineParameterEditor;
    private ParameterEditComponent twoInLineParameterEditor;

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
        titleLabel.setBorder(BorderFactory.createEmptyBorder(40, 0, 20, 0));

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(BACKGROUND_COLOR);

        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 50, 0));

        JPanel playerOneSettingPanel = new JPanel();
        playerOneSettingPanel.setBackground(BACKGROUND_COLOR);
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


        JPanel parametersPanel = new JPanel();
        parametersPanel.setLayout(new BoxLayout(parametersPanel, BoxLayout.Y_AXIS));
        parametersPanel.setBackground(BACKGROUND_COLOR);
        int parametersPanelSideMargin = 30;
        parametersPanel.setBorder(BorderFactory.createEmptyBorder(40, parametersPanelSideMargin, 0, parametersPanelSideMargin));

        fourInLineParameterEditor = new ParameterEditComponent("4-in-line weight", 1000);
        threeInLineParameterEditor = new ParameterEditComponent("3-in-line weight", 10);
        twoInLineParameterEditor = new ParameterEditComponent("2-in-line weight", 1);
        parametersPanel.add(fourInLineParameterEditor);
        parametersPanel.add(threeInLineParameterEditor);
        parametersPanel.add(twoInLineParameterEditor);

        centerPanel.add(playerOneSettingPanel);
        centerPanel.add(playerTwoSettingPanel);
        centerPanel.add(parametersPanel);


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

        lowerPanel.setBorder(BorderFactory.createEmptyBorder(0, 30, 30, 30));
        lowerPanel.add(startGameBtn);

        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(lowerPanel, BorderLayout.SOUTH);
    }

    private class PlayerComboBox extends JComboBox<String> implements ActionListener {

        public PlayerComboBox() {
            super();
            addItem("Human");
            addItem("MinMax 1");
            addItem("MinMax 2");
            addItem("MinMax 3");
            addItem("MinMax 4");
            addItem("MinMax 5");
            addItem("MinMax 6");
            setFont(new Font("Verdana", Font.PLAIN, 28));
            setPreferredSize(new Dimension(200, 50));
        }

    }

    private class TextLabel extends JLabel {

        public TextLabel(String string, int width) {
            super(string);
            setFont(new Font("Verdana", Font.PLAIN, 28));
            setForeground(new Color(10, 78, 169));
            setPreferredSize(new Dimension(width, 50));
            setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 0));
        }

        public TextLabel(String string) {
            this(string, 170);
        }
    }

    private class TextField extends JTextField {
        public TextField() {
            super(5);
            setFont(new Font("Verdana", Font.PLAIN, 28));
            setMaximumSize(new Dimension(10, 50));
        }
    }

    private class ParameterEditComponent extends JPanel {

        private JTextField parameterValueEdit;

        public ParameterEditComponent(String parameterName, int defaultValue) {
            super();
            setBackground(BACKGROUND_COLOR);
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            JLabel parameterLabel = new TextLabel(parameterName, 270);
            parameterValueEdit = new TextField();
            parameterValueEdit.setText(String.valueOf(defaultValue));
            parameterValueEdit.setHorizontalAlignment(SwingConstants.RIGHT);
            parameterValueEdit.setBorder(BorderFactory.createCompoundBorder(
                    parameterValueEdit.getBorder(),
                    BorderFactory.createEmptyBorder(5, 5, 5, 5)));
            add(parameterLabel);
            add(parameterValueEdit);
            add(Box.createHorizontalGlue());
        }

        public JTextField getParameterValueEdit() {
            return parameterValueEdit;
        }
    }


    private class StartBtmActionListener implements ActionListener {

        JPanel panel;

        public StartBtmActionListener(NewGamePanel newGamePanel) {
            this.panel = newGamePanel;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Player playerOne = getPlayerFromSettings(playerOneComboBox, Token.YELLOW);
            Player playerTwo = getPlayerFromSettings(playerTwoComboBox, Token.RED);
            GameStateEvaluator evaluator = getGameEvaluatorFromSettings();
            if (evaluator == null) {
                JOptionPane.showMessageDialog(panel, "Incorrect parameters given. Try again.", "Wrong input",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                ConnectFourGame game = new ConnectFourGame(playerOne, playerTwo, evaluator);
                parentGUI.setGame(game);
            }
        }
    }

    private GameStateEvaluator getGameEvaluatorFromSettings() {
        try {
            int fourInLineWeight = Integer.parseInt(fourInLineParameterEditor.getParameterValueEdit().getText());
            int threeInLineWeight = Integer.parseInt(threeInLineParameterEditor.getParameterValueEdit().getText());
            int twoInLineWeight = Integer.parseInt(twoInLineParameterEditor.getParameterValueEdit().getText());
            return new GameStateEvaluatorImpl(fourInLineWeight, threeInLineWeight, twoInLineWeight);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Player getPlayerFromSettings(PlayerComboBox playerComboBox, Token token) {
        Player newPlayer;
        String playerSetting = (String) playerComboBox.getSelectedItem();
        assert playerSetting != null;

        if (playerSetting.split(" ")[0].equals("MinMax")) {
            int maxDepth = Integer.parseInt(playerSetting.split(" ")[1]);
            newPlayer = new ComputerPlayer(token, PlayerType.AI);
            AI ai = new MinMaxAI(maxDepth);
            ((ComputerPlayer) newPlayer).setAi(ai);
        } else {
            switch (playerSetting) {
                case "Computer":
                    newPlayer = new ComputerPlayer(token, PlayerType.AI);
                    AI ai = new MinMaxAI();
                    ((ComputerPlayer) newPlayer).setAi(ai);
                    break;
                default:
                    newPlayer = new HumanPlayer(token, PlayerType.HUMAN);
                    break;
            }
        }
        return newPlayer;
    }

}
