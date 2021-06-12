package rs.bg.ac.student.ivana.MavenCommon.domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 * Interfejs koji predstavlja domenski interfejs sa potrebnim metodama
 * @author Ivana
 * @version 0.1
 */
public interface DomainType extends Serializable{
    
    /**
     * Vraca naziv tabele.
     * @return Naziv table
     */
    String getTableName();

    String getColumnNamesForInsert();

    String getInsertValues();

    
    void setId(Long id);

    String getJoinCondition();
    
    String getUpdateString(DomainType d);
    
    List<DomainType> getRS(ResultSet rs);
    
    String getDeleteString();
            
}
