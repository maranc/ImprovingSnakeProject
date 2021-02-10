import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author xwolf
 */
public class MainMenu extends javax.swing.JFrame {

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelTitle = new javax.swing.JLabel();
        btnPlayerVsPlayer = new javax.swing.JButton();
        btnSingleplayer1 = new javax.swing.JButton();
        btnPlayerVsAI = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(240, 240, 20));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.green);

        labelTitle.setFont(new java.awt.Font("UD Digi Kyokasho N-B", 1, 48)); // NOI18N
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("Snake");

        btnPlayerVsPlayer.setFont(new java.awt.Font("UD Digi Kyokasho N-B", 0, 18)); // NOI18N
        btnPlayerVsPlayer.setText("Player vs Player");
        btnPlayerVsPlayer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayerVsPlayerActionPerformed(evt);
            }
        });

        btnSingleplayer1.setFont(new java.awt.Font("UD Digi Kyokasho N-B", 0, 18)); // NOI18N
        btnSingleplayer1.setText("Singleplayer");
        btnSingleplayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSingleplayer1ActionPerformed(evt);
            }
        });

        btnPlayerVsAI.setFont(new java.awt.Font("UD Digi Kyokasho N-B", 0, 18)); // NOI18N
        btnPlayerVsAI.setText("Player vs AI");
        btnPlayerVsAI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayerVsAIActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup().addGap(20, 20, 20).addComponent(btnSingleplayer1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPlayerVsPlayer)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnPlayerVsAI))
                        .addGroup(layout.createSequentialGroup().addGap(131, 131, 131).addComponent(labelTitle,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
                .createSequentialGroup()
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 72,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnPlayerVsPlayer).addComponent(btnPlayerVsAI).addComponent(btnSingleplayer1))
                .addContainerGap(32, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * @param evt
     */
    private void btnSingleplayer1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSingleplayer1ActionPerformed
        // TODO add your handling code here:
        // Creating the window with all its awesome snaky features
        Window f1 = new Window(0);

        // Setting up the window settings
        f1.setTitle("Snake");
        f1.setSize(300, 300);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Main.m.setVisible(false);

    }// GEN-LAST:event_btnSingleplayer1ActionPerformed
    public static void OpenScoreBoard(){
        ScoreboardWindow f2 = new ScoreboardWindow();
        f2.setTitle("Scoreboard");
        f2.setSize(300,300);
        f2.setVisible(true);
        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void btnPlayerVsPlayerActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnPlayerVsPlayerActionPerformed
        // TODO add your handling code here:
        // Creating the window with all its awesome snaky features
        Window f1 = new Window(2);

        // Setting up the window settings
        f1.setTitle("Snake");
        f1.setSize(300, 300);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Main.m.setVisible(false);

    }// GEN-LAST:event_btnPlayerVsPlayerActionPerformed

    private void btnPlayerVsAIActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnPlayerVsAIActionPerformed
        // TODO add your handling code here:
        // Creating the window with all its awesome snaky features
        Window f1 = new Window(1);

        // Setting up the window settings
        f1.setTitle("Snake");
        f1.setSize(300, 300);
        f1.setVisible(true);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Main.m.setVisible(false);

    }// GEN-LAST:event_btnPlayerVsAIActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPlayerVsAI;
    private javax.swing.JButton btnPlayerVsPlayer;
    private javax.swing.JButton btnSingleplayer1;
    private javax.swing.JLabel labelTitle;
    // End of variables declaration//GEN-END:variables
}
