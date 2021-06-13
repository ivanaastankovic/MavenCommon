package rs.bg.ac.student.ivana.MavenCommon.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 * Interfejs koji predstavlja domenski interfejs
 * @author Ivana
 * @version 0.1
 */
public interface DomainType extends Serializable{
    
    /**
     * Vraca naziv tabele.
     * @return String kao naziv table
     */
    String getTableName();
    /**
     * Vraca String kao naziv kolona za unos jedne instance u bazu
     * @return String kao naziv kolona 
     */
    String getColumnNamesForInsert();
    
    /**
     * Vraca String unetih vrednosti 
     * @return String unetih vrednosti
     */
    String getInsertValues();

    /**
     * Postavlja Id objekta domenske klase
     * @param id kao Long 
     */
    void setId(Long id);
    
    /**
     * Vraca String kao uslov za join tabela
     * @return String kao uslov za join tabela
     */
    String getJoinCondition();
    
    /**
     * Vraca String kao update upit za bazu za azuriranje objekta domenske klase 
     * @param d DomainType kao domenski objekat cije se vrednosti azuriraju
     * @return String kao upit za update domenskog objekata u bazi
     */
    String getUpdateString(DomainType d);
    
    /**
     * Vraca listu domenskih objekata dobijenu kao rezultat izvrsavanja upita nad bazom
     * @param rs ResultSet kao rezultat upita izvrsenog nad bazom podataka
     * @return List kao listu domenskih objekata 
     */
    List<DomainType> getRS(ResultSet rs);
    
    /**
     * Vraca String kao deo upita za brisanje elementa iz baze
     * @return String kao deo upita za bazu
     */
    String getDeleteString();
            
}
