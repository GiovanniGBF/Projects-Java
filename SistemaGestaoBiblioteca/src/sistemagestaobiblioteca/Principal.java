/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sistemagestaobiblioteca;

import Cadastro.Aluno;
import Cadastro.Cadastro;
import Cadastro.Enviar;
import Cadastro.LIVROS;
import ConexaoDAO.Conexao;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Bobito
 */
public class Principal extends javax.swing.JFrame {

    private Object CampoTitulo;
    Connection conn = null;
    PreparedStatement stmt = null;
    private int Index;
    DefaultTableModel model;

    /**
     * Creates new form Principal
     */
    public void setarData() {
        Date data = new Date();
        DateFormat Formatador = DateFormat.getDateInstance(DateFormat.FULL);
        Data.setText(Formatador.format(data));
    }

    public Principal() throws ClassNotFoundException {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("Icon.png")).getImage());
        setTitle("SISTEMA DE GESTÃO DE BIBLIOTECA");
        setExtendedState(MAXIMIZED_BOTH);
        setarData();

        chamarTabela();
        FlatMacLightLaf.setup();
    }

    public List<LIVROS> Lista() throws ClassNotFoundException {
        List<LIVROS> li = new ArrayList();
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = conn.prepareStatement("SELECT * FROM livros");
            rs = stmt.executeQuery();

            while (rs.next()) {
                LIVROS l = new LIVROS();
                l.setId(rs.getInt("id"));
                l.setTitulo(rs.getString("Titulo"));
                l.setAutor(rs.getString("Autor"));
                l.setAnoPublico(rs.getDate("AnoPublico"));
                l.setEditora(rs.getString("Editora"));
                l.setIdioma(rs.getString("Idioma"));
                l.setDescrição(rs.getString("Descrição"));
                l.setCondição(rs.getString("Condição"));
                l.setDataEntrada(rs.getDate("DataEntrada"));
                l.setGenero(rs.getString("Genero"));
                l.setNumeroPagina(rs.getInt("NumeroPagina"));
                li.add(l);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Enviar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();

                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(null, e);
                }
            }

        }

        return li;

    }

// Segundo tabela de pesquisar
    
    
    
    //tabela de pesquisa de aluno
     public List<Aluno> alunos() throws ClassNotFoundException {
        List<Aluno> li = new ArrayList();
        try {
            Connection conn = Conexao.getConnection();
            PreparedStatement stmt = null;
            ResultSet rs = null;
            stmt = conn.prepareStatement("SELECT * FROM aluno");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Aluno l = new Aluno();
                l.setId(rs.getInt("id"));
                l.setNome(rs.getString("Nome"));
                l.setApelido(rs.getString("Autor"));
                l.setNomeLvro(rs.getString("NomeLivro"));
                l.setSala(rs.getString("Sala"));
                l.setNumero(rs.getInt("Numero"));
                l.setTurma(rs.getInt("Turma"));
                l.setImagem((BufferedImage) rs.getBlob("Imagem"));
                li.add(l);
            }

        } catch (SQLException ex) {
            Logger.getLogger(Enviar.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();

                } catch (Exception e) {
                    JOptionPane.showConfirmDialog(null, e);
                }
            }

        }

        return li;

    }

    public void chamarTabela1() throws ClassNotFoundException {
        
        
        List<LIVROS> lista = Lista();
        model = (DefaultTableModel) Tabela1.getModel();
        model.setNumRows(0);
        model.setRowCount(0);
        for (LIVROS e : lista) {
            model.addRow(new Object[]{
                e.getId(),
                e.getTitulo(),
                e.getAutor(),
                e.getAnoPublico(),
                e.getEditora(),
                e.getIdioma(),
                e.getDescrição(),
                e.getCondição(),
                e.getDataEntrada(),
                e.getGenero(),
                e.getNumeroPagina()
                    
            });
        
        }
                    

    }

    //PESQUISAR NA TABELA
    public void Pesquisar(String nome) {

        model = (DefaultTableModel) Tabela1.getModel();
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<>(model);
        Tabela1.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(nome));
    }

    //FIM DE PESQUISAR NA TABELA
    // primeira tabela
    public void chamarTabela() throws ClassNotFoundException {
        Enviar a = new Enviar();
        List<LIVROS> lista = Lista();
        DefaultTableModel model = (DefaultTableModel) Tabela.getModel();
        model.setNumRows(0);
        for (LIVROS e : lista) {
            model.addRow(new Object[]{
                e.getId(),
                e.getTitulo(),
                e.getAutor(),
                e.getAnoPublico(),
                e.getEditora(),
                e.getIdioma(),
                e.getDescrição(),
                e.getCondição(),
                e.getDataEntrada(),
                e.getGenero(),
                e.getNumeroPagina()
            });
        }
    }

    
    
    
    
    //tabela de aluno
     /*public void chamarTabela2() {
                 Enviar a = new Enviar();
        List<Aluno> aluno=Lista1();
        
        DefaultTableModel model = (DefaultTableModel) Tabela2.getModel();
        model.setNumRows(0);
        for (Aluno e : aluno) {
            model.addRow(new Object[]{
                e.getId(),
                e.getNome(),
                e.getApelido(),
                e.getNomeLvro(),
                e.getSala(),
                e.getNumero(),
                e.getTurma(),
                e.getImagem(),
            });
        }
    }*/
    
    
   
    public void ApagarAT(LIVROS a) {

     
    try {
        conn = Conexao.getConnection();
        
        String sql = "DELETE FROM livros WHERE id=?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, a.getId());
        pst.executeUpdate();
        
        
       
    } catch (Exception e) {
        System.out.println("Erro ao apagar: " + e);
    }


    }
     
    
    
    
    
    public void Deletar() throws ClassNotFoundException {

        try {
            // Abrir conexão com o banco
            conn = Conexao.getConnection();
            int row = Tabela.getSelectedRow();
            String celula = Tabela.getModel().getValueAt(row, 0).toString();

            String sql = "DELETE FROM livros WHERE id=" + celula;
            stmt = conn.prepareStatement(sql);

            int r = JOptionPane.showConfirmDialog(this, "Quer Deletar esse registro!");
            if (r == 0) {
                stmt.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Selecione o registro que deseja apagar!");

        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    
    
    
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new RoundedPanel(900,new Color(248, 196, 113));
        jPanel1 = new javax.swing.JPanel();
        jPanel1 = new RoundedPanel(700,new Color(8, 196, 113));
        Principal = new javax.swing.JButton();
        CADASTRO = new javax.swing.JButton();
        EMPRESTAR = new javax.swing.JButton();
        EMPRESTAR1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Data = new javax.swing.JLabel();
        EMPRESTAR2 = new javax.swing.JButton();
        Ca = new javax.swing.JTabbedPane();
        a1 = new javax.swing.JPanel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel2 = new javax.swing.JLabel();
        ca = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        remov = new javax.swing.JButton();
        En = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        CampoImagem = new javax.swing.JLabel();
        Atualizar = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        Salvar = new javax.swing.JButton();
        Apagar = new javax.swing.JButton();
        CampoNome = new javax.swing.JTextField();
        CampoApelido = new javax.swing.JTextField();
        CampoNomeLivro = new javax.swing.JTextField();
        CampoSala = new javax.swing.JTextField();
        CampoTurma = new javax.swing.JFormattedTextField();
        CampoNumero = new javax.swing.JFormattedTextField();
        Mais1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        Tabela2 = new javax.swing.JTable();
        Pesquisa = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        in = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabela1 = new javax.swing.JTable();
        CampoPesquisa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Principal.setFont(new java.awt.Font("Baskerville Old Face", 1, 14)); // NOI18N
        Principal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/homeregular_106344.png"))); // NOI18N
        Principal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrincipalActionPerformed(evt);
            }
        });

        CADASTRO.setFont(new java.awt.Font("Baskerville Old Face", 1, 14)); // NOI18N
        CADASTRO.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/book_21469.png"))); // NOI18N
        CADASTRO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CADASTROActionPerformed(evt);
            }
        });

        EMPRESTAR.setFont(new java.awt.Font("Baskerville Old Face", 1, 14)); // NOI18N
        EMPRESTAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/arrow_right_22422.png"))); // NOI18N
        EMPRESTAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMPRESTARActionPerformed(evt);
            }
        });

        EMPRESTAR1.setFont(new java.awt.Font("Baskerville Old Face", 1, 14)); // NOI18N
        EMPRESTAR1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/informação.png"))); // NOI18N
        EMPRESTAR1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMPRESTAR1ActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/LOGO2.png"))); // NOI18N

        Data.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Data.setText("Data");

        EMPRESTAR2.setFont(new java.awt.Font("Baskerville Old Face", 1, 14)); // NOI18N
        EMPRESTAR2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/magnifier_search_zoom_find_bam_icon_197155.png"))); // NOI18N
        EMPRESTAR2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EMPRESTAR2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Principal, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(CADASTRO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EMPRESTAR2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EMPRESTAR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EMPRESTAR1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(Data)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(Principal)
                .addGap(15, 15, 15)
                .addComponent(CADASTRO)
                .addGap(15, 15, 15)
                .addComponent(EMPRESTAR2)
                .addGap(15, 15, 15)
                .addComponent(EMPRESTAR)
                .addGap(15, 15, 15)
                .addComponent(EMPRESTAR1)
                .addGap(40, 40, 40)
                .addComponent(Data)
                .addContainerGap(386, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 940));

        Ca.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                CaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        jDesktopPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/Design sem nome.gif"))); // NOI18N

        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 178, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 197, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout a1Layout = new javax.swing.GroupLayout(a1);
        a1.setLayout(a1Layout);
        a1Layout.setHorizontalGroup(
            a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        a1Layout.setVerticalGroup(
            a1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(a1Layout.createSequentialGroup()
                .addComponent(jDesktopPane1)
                .addGap(0, 0, 0))
        );

        Ca.addTab("TELA INICIAL", a1);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        Tabela.setBackground(new java.awt.Color(0, 204, 255));
        Tabela.setFont(new java.awt.Font("Bookman Old Style", 0, 12)); // NOI18N
        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Titulo", "Autor", "AnoPublico", "Editora", "Idioma", "Descrição", "Condição", "DataEntrada", "Genero", "NumeroPagina"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabela.setAlignmentX(10.0F);
        Tabela.setAlignmentY(10.0F);
        Tabela.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                TabelaAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        Tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(Tabela);

        jButton1.setBackground(new java.awt.Color(51, 204, 255));
        jButton1.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/Plus_36851.png"))); // NOI18N
        jButton1.setText("NOVO");
        jButton1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jButton1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Baskerville Old Face", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/LIVROS_GRANDE.png"))); // NOI18N
        jLabel3.setText("TABELA DE LIVROS CADASTRADOS");

        remov.setBackground(new java.awt.Color(0, 204, 255));
        remov.setFont(new java.awt.Font("Bodoni MT", 0, 14)); // NOI18N
        remov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/10_97263.png"))); // NOI18N
        remov.setText("REMOVER");
        remov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 945, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(668, 668, 668)
                        .addComponent(remov)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(287, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(remov)
                    .addComponent(jButton1))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(244, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout caLayout = new javax.swing.GroupLayout(ca);
        ca.setLayout(caLayout);
        caLayout.setHorizontalGroup(
            caLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(caLayout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 163, Short.MAX_VALUE))
        );
        caLayout.setVerticalGroup(
            caLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Ca.addTab("CADASTRO", ca);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Times New Roman", 0, 36)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 204, 255));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/LIVROS_GRANDE.png"))); // NOI18N
        jLabel19.setText("EMPRESTINO DE LIVROS");

        jPanel7.setBackground(new java.awt.Color(0, 204, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        CampoImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 255), 3));
        CampoImagem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CampoImagemMouseClicked(evt);
            }
        });

        Atualizar.setBackground(new java.awt.Color(0, 204, 255));
        Atualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/refresh_arrow_1546.png"))); // NOI18N

        jButton4.setBackground(new java.awt.Color(0, 204, 255));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/Printer_26276.png"))); // NOI18N

        Salvar.setBackground(new java.awt.Color(0, 204, 255));
        Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/Salvar.png"))); // NOI18N

        Apagar.setBackground(new java.awt.Color(0, 204, 255));
        Apagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/10_97263.png"))); // NOI18N

        CampoNome.setBorder(javax.swing.BorderFactory.createTitledBorder("NOME"));
        CampoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoNomeActionPerformed(evt);
            }
        });

        CampoApelido.setBorder(javax.swing.BorderFactory.createTitledBorder("APELIDO"));
        CampoApelido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoApelidoActionPerformed(evt);
            }
        });

        CampoNomeLivro.setBorder(javax.swing.BorderFactory.createTitledBorder("NOME DE LIVRO"));
        CampoNomeLivro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoNomeLivroActionPerformed(evt);
            }
        });

        CampoSala.setBorder(javax.swing.BorderFactory.createTitledBorder("SALA"));
        CampoSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoSalaActionPerformed(evt);
            }
        });

        CampoTurma.setBorder(javax.swing.BorderFactory.createTitledBorder("TURMA"));
        CampoTurma.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#########"))));

        CampoNumero.setBorder(javax.swing.BorderFactory.createTitledBorder("NUMERO"));
        CampoNumero.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#########"))));

        Mais1.setBackground(new java.awt.Color(0, 204, 255));
        Mais1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/Plus_36851.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(CampoImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(Mais1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62))
                    .addComponent(CampoApelido, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(CampoNomeLivro)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(CampoNome, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(Apagar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(Atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CampoSala, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoTurma, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoNumero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(42, 42, 42))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CampoNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CampoApelido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(CampoNomeLivro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CampoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Salvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Apagar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Atualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Mais1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(CampoImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Tabela2.setBackground(new java.awt.Color(0, 204, 255));
        Tabela2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "Apelido", "Nome de Livro", "Sala", "Turma", "Numero"
            }
        ));
        jScrollPane3.setViewportView(Tabela2);

        Pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PesquisaActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(0, 204, 255));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/magnifier_search_zoom_find_bam_icon_197155.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(Pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(243, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout EnLayout = new javax.swing.GroupLayout(En);
        En.setLayout(EnLayout);
        EnLayout.setHorizontalGroup(
            EnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(249, Short.MAX_VALUE))
        );
        EnLayout.setVerticalGroup(
            EnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EnLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        Ca.addTab("EMPRESTAR", En);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Baskerville Old Face", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/informação.png"))); // NOI18N
        jLabel5.setText("INFORMAÇÃO DO SISTEMA:");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/app_store_alt_macos_bigsur_icon_190386.png"))); // NOI18N
        jLabel6.setText("NOME: SISTEMA DE GESTÃO DE BIBLIOTECA.");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/ic_publish_128_28547.png"))); // NOI18N
        jLabel7.setText("DATA DE PUBLICAÇÃO:17/02/2026.");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/icon_developer_16019.png"))); // NOI18N
        jLabel8.setText("DESENVOLVEDOR: BUBACAR BOBITO DJALÓ.");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/customer_person_people_man_user_client_1629.png"))); // NOI18N
        jLabel9.setText("TIPO DE USUARIO:ADMINISTRATIVO.");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/interface_testing_design_functionality_accessibility_usability_icon_266336.png"))); // NOI18N
        jLabel10.setText("VERSÃO: 0.1.");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/file_type_homeassistant_icon_130543.png"))); // NOI18N
        jLabel11.setText("FUNCIONALIDADE DO SISTEMA:");

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setText("CADASTRO DE LIVROS;");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel13.setText("ATUALIZAÇÃO;");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel14.setText("PROCURAÇÃO;");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel15.setText("REMOÇÃO;");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel16.setText("SELECÇÃO;");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel17.setText("INSERÇÃO;");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel18.setText("REGISTROS DE EMPRESTIMOS;");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel15)
                            .addComponent(jLabel18)))
                    .addComponent(jLabel11))
                .addContainerGap(586, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addGap(3, 3, 3)
                .addComponent(jLabel8)
                .addGap(3, 3, 3)
                .addComponent(jLabel7)
                .addGap(3, 3, 3)
                .addComponent(jLabel10)
                .addGap(3, 3, 3)
                .addComponent(jLabel9)
                .addGap(3, 3, 3)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addGap(3, 3, 3)
                .addComponent(jLabel14)
                .addGap(3, 3, 3)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addGap(3, 3, 3)
                .addComponent(jLabel16)
                .addContainerGap(328, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout inLayout = new javax.swing.GroupLayout(in);
        in.setLayout(inLayout);
        inLayout.setHorizontalGroup(
            inLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inLayout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 159, Short.MAX_VALUE))
        );
        inLayout.setVerticalGroup(
            inLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Ca.addTab("INFORMAÇÃO", in);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPanel6FocusGained(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Tabela1.setBackground(new java.awt.Color(0, 204, 255));
        Tabela1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Titulo", "Autor", "AnoPublico", "Editora", "Idioma", "Descrição", "Condição", "DataEntrada", "Genero", "NumeroPaginal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabela1.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                Tabela1ComponentAdded(evt);
            }
        });
        Tabela1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                Tabela1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                Tabela1AncestorMoved(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                Tabela1AncestorRemoved(evt);
            }
        });
        Tabela1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Tabela1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabela1);

        jPanel6.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 990, 450));

        CampoPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CampoPesquisaActionPerformed(evt);
            }
        });
        CampoPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                CampoPesquisaKeyReleased(evt);
            }
        });
        jPanel6.add(CampoPesquisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 460, 30));

        jLabel4.setFont(new java.awt.Font("Baskerville Old Face", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 204, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/LIVROS_GRANDE.png"))); // NOI18N
        jLabel4.setText("TABELA DE CONSULTA DE LIVROS");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 720, -1));

        jButton2.setBackground(new java.awt.Color(0, 204, 255));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sistemagestaobiblioteca/magnifier_search_zoom_find_bam_icon_197155.png"))); // NOI18N
        jButton2.setText("PESQUISAR");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 140, 30));

        jPanel2.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1170, 905));

        Ca.addTab("PESQUISAR", jPanel2);

        getContentPane().add(Ca, new org.netbeans.lib.awtextra.AbsoluteConstraints(239, -5, 1430, 940));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void PrincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrincipalActionPerformed
        Ca.setSelectedIndex(0);        // TODO add your handling code here:
    }//GEN-LAST:event_PrincipalActionPerformed

    private void EMPRESTARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMPRESTARActionPerformed
        Ca.setSelectedIndex(2);   // TODO add your handling code here:
    }//GEN-LAST:event_EMPRESTARActionPerformed

    private void CADASTROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CADASTROActionPerformed
        try {
            chamarTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        Ca.setSelectedIndex(1);        // TODO add your handling code here:
    }//GEN-LAST:event_CADASTROActionPerformed

    private void EMPRESTAR1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMPRESTAR1ActionPerformed
        Ca.setSelectedIndex(3); // TODO add your handling code here:
    }//GEN-LAST:event_EMPRESTAR1ActionPerformed

    private void CaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_CaAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_CaAncestorAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            Cadastro a = new Cadastro(null, true);
            chamarTabela();
            a.setVisible(true);// TODO add your handling code here:
            chamarTabela();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void EMPRESTAR2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EMPRESTAR2ActionPerformed
        
        try {
            chamarTabela1();
            Ca.setSelectedIndex(4);
            
// TODO add your handling code here:
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_EMPRESTAR2ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jButton1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1AncestorAdded

    private void Tabela1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Tabela1AncestorAdded
  
        try {
            chamarTabela1();
            // TODO add your handling code here:
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Tabela1AncestorAdded

    private void TabelaAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_TabelaAncestorAdded
        try {
            Deletar();
            chamarTabela();  // TODO add your handling code here:
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TabelaAncestorAdded

    private void removActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removActionPerformed
        try {
            try {
                Deletar();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Selecione o registro que pretende apagar");
        }

    }//GEN-LAST:event_removActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowActivated

    private void Tabela1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Tabela1MouseClicked

        //SAIDA DE DADOS 
        int i = Tabela1.getSelectedRow();
        TableModel model = (DefaultTableModel)Tabela1.getModel();
        int id = (int)model.getValueAt(i, 0);
        String Titulo = model.getValueAt(i, 1).toString();
        String Autor = model.getValueAt(i, 2).toString();
        String et = model.getValueAt(i, 4).toString();
        Date pu = (Date) model.getValueAt(i, 3);
        Date p = (Date) model.getValueAt(i, 8);
        String idi = model.getValueAt(i, 5).toString();
        String des = model.getValueAt(i, 6).toString();
        String cod = model.getValueAt(i, 7).toString();
        String gen = model.getValueAt(i, 9).toString();
        int Nup =Integer.parseInt(model.getValueAt(i, 10).toString());
        Atualizar A = new Atualizar();
        A.setVisible(true);
        A.pack();
        A.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       
        A.CampoT.setText(Titulo);
        A.CampoA.setText(Autor);
        A.CampoDiscrição.setText(des);
        A.CampoEditora.setText(et);
        A.Publicar.setDate(pu);
        A.DataEntrada.setDate(p);
        A.CampoIdioma.setSelectedItem(idi);
        A.CampoCondição.setSelectedItem(cod);
        A.CampoGenero.setSelectedItem(gen);
        A.CampoPa.setValue( Nup);
        A.CampoId.setText(String .valueOf(id));
      
        
       
// TODO add your handling coA.CampoId.setInt(id);de here:
    }//GEN-LAST:event_Tabela1MouseClicked

    private void TabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaMouseClicked

        // TODO add your handling code here:
    }//GEN-LAST:event_TabelaMouseClicked

    private void CampoPesquisaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CampoPesquisaKeyReleased
        String Pes = CampoPesquisa.getText();
        Pesquisar(Pes);        // TODO add your handling code here:
    }//GEN-LAST:event_CampoPesquisaKeyReleased

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked
        JOptionPane.showMessageDialog(this, "Escreva o nome ou id ou...");        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2MouseClicked

    private void Tabela1AncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Tabela1AncestorMoved
        try {
            chamarTabela1();   // TODO add your handling code here:
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Tabela1AncestorMoved

    private void Tabela1ComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_Tabela1ComponentAdded
        try {
            chamarTabela1();        // TODO add your handling code here:
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Tabela1ComponentAdded

    private void Tabela1AncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_Tabela1AncestorRemoved
        try {
            chamarTabela1();    // TODO add your handling code here:
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_Tabela1AncestorRemoved

    private void jPanel6FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPanel6FocusGained
        try {
            chamarTabela1();
            // TODO add your handling code here:
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPanel6FocusGained

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
       
        try {
            chamarTabela1(); chamarTabela();
// TODO add your handling code here:
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowGainedFocus

    private void PesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PesquisaActionPerformed

    private void CampoSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoSalaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoSalaActionPerformed

    private void CampoNomeLivroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoNomeLivroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoNomeLivroActionPerformed

    private void CampoApelidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoApelidoActionPerformed
        // TODO add your handling code here:=null
    }//GEN-LAST:event_CampoApelidoActionPerformed

    private void CampoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoNomeActionPerformed

    private void CampoImagemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CampoImagemMouseClicked
        // TODO add your handling code here:


    
    }//GEN-LAST:event_CampoImagemMouseClicked

    private void CampoPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CampoPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CampoPesquisaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        FlatMacLightLaf.setup();
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Principal().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Apagar;
    private javax.swing.JButton Atualizar;
    private javax.swing.JButton CADASTRO;
    private javax.swing.JTabbedPane Ca;
    private javax.swing.JTextField CampoApelido;
    private javax.swing.JLabel CampoImagem;
    private javax.swing.JTextField CampoNome;
    private javax.swing.JTextField CampoNomeLivro;
    private javax.swing.JFormattedTextField CampoNumero;
    private javax.swing.JTextField CampoPesquisa;
    private javax.swing.JTextField CampoSala;
    private javax.swing.JFormattedTextField CampoTurma;
    private javax.swing.JLabel Data;
    private javax.swing.JButton EMPRESTAR;
    private javax.swing.JButton EMPRESTAR1;
    private javax.swing.JButton EMPRESTAR2;
    private javax.swing.JPanel En;
    private javax.swing.JButton Mais1;
    private javax.swing.JTextField Pesquisa;
    private javax.swing.JButton Principal;
    private javax.swing.JButton Salvar;
    private javax.swing.JTable Tabela;
    private javax.swing.JTable Tabela1;
    private javax.swing.JTable Tabela2;
    private javax.swing.JPanel a1;
    private javax.swing.JPanel ca;
    private javax.swing.JPanel in;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton remov;
    // End of variables declaration//GEN-END:variables

    private static class RoundedPanel extends JPanel {

        public RoundedPanel(int i, Color color) {
        }
    }
}
