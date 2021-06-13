package rs.bg.ac.student.ivana.MavenCommon.domain;

import java.io.Serializable;
/**
 * Enum kao status u kom se nalazi Claim (zalba)
 * @author Ivana
 *
 */
public enum Status implements Serializable{
	/**
	 * Popunjena
	 */
    FILED,
    /**
     * U procesu
     */
    PENDING,
    /**
     * Prihvacena
     */
    ACCEPTED,
    /**
     * Odbijena
     */
    REJECTED
}
