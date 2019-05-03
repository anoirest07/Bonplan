/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Config.Connexion;
import Entite.Publicite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author user
 */
public class ServiceStatPublicite {
    Connection con ;

    public ServiceStatPublicite() {
        con = Connexion.getInstance().getCon();
    }
    public void ajouterStatPubicite() throws SQLException{
        String query ="insert into statpublicite (`id`,`id_publicite`,`date`) values (?,?,?)";
        PreparedStatement st =
            con.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
        st.setInt(1,1);
        st.setInt(2, 64);
        st.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
        st.executeUpdate();
            ResultSet result = st.getGeneratedKeys();
            result.next();
           System.out.println("Statpublicite est ajouter");
           
    }
    
    
    public boolean CheckStatu(Publicite p) throws SQLException{
        String sql ="select * from statpublicite where id = ? and id_publicite= ? ;";
         PreparedStatement st =
            con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        st.setInt(1,1);
        st.setInt(2, 64);
      //  st.setDate(3, java.sql.Date.valueOf(LocalDate.now()));
        ResultSet rs = st.executeQuery();
        
        if(rs.next()){
            return false ; // exist
        }
        
        
        return true ; // not exist
        
    }
//    public ObservableList<PieChart.Data> calculNbrClick(){
//         ArrayList<PieChart.Data> list = new ArrayList<PieChart.Data>();
//        
//    }
    
}
