/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import control.ControleCurso;
import entidades.Curso;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author fernando_paladini
 */
public class PrimeiraEntrada3 extends javax.swing.JFrame {

    /**
     * Creates new form PrimeiraEntrada3
     */
    public PrimeiraEntrada3() {
        initComponents();
        
        // Desativando botão próxima
        jLabel20.setEnabled(false);
        jLabel3.setVisible(false);
        
        // Cria uma coluna para a tabela
        Object colunas[] = { "Cursos" }; 
        
        // Cria um modelo e diz que ele tem uma coluna (e depois sobreescrevendo um método para não ser possível editar a table)
        DefaultTableModel modelo = new DefaultTableModel(colunas, 0) { 
            public boolean isCellEditable(int row, int col) {  
                return false;  
            } 
        } ;  
        
        // Seta o modelo na tabela, seta uma nova fonte e aumenta o tamanho das linhas.
        jTable1.setModel(modelo);  
        jTable1.setFont(new Font("Helvetica", Font.PLAIN, 18));
        jTable1.setRowHeight(jTable1.getRowHeight()+10);
        
        // Atualiza as disciplinas exibidas
        this.recarregarCursos();
        
        // "Listener", para "escutar" um duplo clique nas linhas dentro da tabela.
        jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    
                    // Pega a linha e a coluna do dado que foi selecionado pelo usuário. 
                    JTable target = (JTable)e.getSource();
                    int row = target.getSelectedRow();
                    int column = target.getSelectedColumn();
                    
                    // Pega o nome da disciplina nessa posição
                    String nomeCurso = (String) jTable1.getValueAt(row, column);
                    
                    // Instancia nova "view" chamada "editar". Em seguida exibe ela para o usuário centralizada.
                    EditarCurso editar = new EditarCurso(nomeCurso);
                    editar.setVisible(true);
                    editar.setLocationRelativeTo(null);
                    
                    // "Listener" para recarregar as disciplinas quando fechar a janela de "editar disciplinas". 
                    editar.addWindowListener(new WindowAdapter() {
                        public void windowClosed(WindowEvent evt) {
                            recarregarCursos();
                            jLabel3.setText("Curso atualizado!");
                            jLabel3.setVisible(true);
                        }
                    });    
                }
            }
        });
    }

    /**
     * Faz uma nova consulta no banco de dados, atualizando todas as disciplinas na lista de disciplinas.
     */
    private void recarregarCursos(){
        
        // Variável "modelo" é o "modelo" da jTable1
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        
        // Limpa todas as linhas do modelo (para não simplesmente adicionar os mesmos resultados já existentes na lista.
        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
        
        // Instancia um novo ControleDisciplina
        ControleCurso cc = new ControleCurso();
        
        // Faz a pesquisa no banco de dados, e armazena todas as disciplinas no ArrayList "consulta". 
        ArrayList<Curso> consulta = cc.consulta();
        
        // Esse é o "for each". Percorre todo o ArrayList "consulta", chamando o elemento atual de "temp".
        // É uma implementação mais rápida para um "for" normal. Basicamente percorre elemento por elemento do arraylist
        // e vai os chamando de "temp". 
        if (!(consulta.isEmpty())){
            
            // Habilita os campos de pesquisa e tabela (pois tem algum dado)
            jTable1.setEnabled(true);
            
            for (Curso temp : consulta){
                modelo.addRow(new String [] { temp.getNome() });
            }
            
            jLabel20.setEnabled(true);
        }
        
        if (modelo.getRowCount() == 0){   
            jTable1.setEnabled(false);
        }
        
    }
    
    /**
     * Faz uma nova consulta no banco de dados com os termos digitados, atualizando as disciplinas na lista de disciplinas.
     * Difere do método anterior pois leva em consideração os termos digitados pelo usuário.
     */
    private void recarregarCursos(ArrayList<Curso> consulta){
        
        // Variável "modelo" é o "modelo" da jTable1
        DefaultTableModel modelo = (DefaultTableModel) jTable1.getModel();
        
        // Limpa todas as linhas do modelo (para não simplesmente adicionar os mesmos resultados já existentes na lista.
        for (int i = modelo.getRowCount() - 1; i >= 0; i--) {
            modelo.removeRow(i);
        }
        
        // Instancia um novo ControleDisciplina
        ControleCurso cc = new ControleCurso();
        
        // Esse é o "for each". Percorre todo o ArrayList "consulta", chamando o elemento atual de "temp".
        // É uma implementação mais rápida para um "for" normal. Basicamente percorre elemento por elemento do arraylist
        // e vai os chamando de "temp". 
        for (Curso temp : consulta){
            modelo.addRow(new String [] { temp.getNome() });
        }
        
        // Se o número de linhas for maior do que 0, ativa o botão "Próxima"
        if (modelo.getRowCount() > 0){
             jLabel20.setEnabled(true);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Locus - Cursos");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logo5.fw.png"))); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/menu-inferior-3.fw.png"))); // NOI18N

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/próxima.fw.png"))); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cursos"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 0));
        jLabel3.setText("Curso adicionado!");

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/adicionar-geral.fw.png"))); // NOI18N
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jLabel7.setBounds(650, 0, 150, 40);
        jLayeredPane1.add(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jTextField1.setBounds(10, 0, 640, 40);
        jLayeredPane1.add(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel20))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 788, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 817, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel20)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        
        // Ir para "PrimeiraEntrada4".
        dispose();
        PrimeiraEntrada4 primeiraEntrada4 = new PrimeiraEntrada4();
        primeiraEntrada4.setVisible(true);
        primeiraEntrada4.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // Se existir algum texto no "jLabel3" (confirmação de que Disciplina foi inserida), vai limpar esse campo;
        if (jLabel3.getText() != null){
            jLabel3.setText(null); 
        } 
        
        // Se existir algum texto, atualiza a lista de disciplinas de acordo com os termos digitados
        String texto = jTextField1.getText(); 
        if (texto.length() != 0){
             // Instancia novo gerenciar disciplina
            ControleCurso cc = new ControleCurso();

            // Recarrega as disciplinas de acordo com o 
            this.recarregarCursos(cc.consultaComTermos(texto));      
        }else{
            // Se não, exibe todas as disciplinas
            this.recarregarCursos();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked

        // Instanciando controle e novo curso.
        ControleCurso cc = new ControleCurso();
        Curso curso = new Curso();
        
        // Pegando os dados
        curso.setNome(jTextField1.getText());
        
        // Adicionando no banco
        cc.adicionar(curso);
        
        // Instanciando um nova janela para cadastro de turmas
        CadastrarCurso cadastrarCurso = new CadastrarCurso(curso.getNome());
        cadastrarCurso.setVisible(true);
        cadastrarCurso.setLocationRelativeTo(null);

        cadastrarCurso.addWindowListener(new WindowAdapter(){
            public void windowClosed(WindowEvent evt){
                recarregarCursos();
                jLabel3.setText("Curso adicionado!");
                jLabel3.setVisible(true);
            }
        });

    }//GEN-LAST:event_jLabel7MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrimeiraEntrada3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrimeiraEntrada3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrimeiraEntrada3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrimeiraEntrada3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrimeiraEntrada3().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
