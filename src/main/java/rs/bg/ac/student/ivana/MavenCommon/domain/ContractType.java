package rs.bg.ac.student.ivana.MavenCommon.domain;

import java.io.Serializable;

/**
 * Enum koji predstavlja tip ugovora
 * @author Ivana
 *
 */
public enum ContractType implements Serializable{
	/**
	 * Ugovor u trajanju od jedne godine
	 */
    ONE_YEAR,
    /**
     * Ugovor u trajanju od dve godine
     */
    TWO_YEARS,
    /**
     * Ugovor u trajanju od pet godina
     */
    FIVE_YEARS,
    /**
     * Porodicni tip ugovora
     */
    FAMILY
}
