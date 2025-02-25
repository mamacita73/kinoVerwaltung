/**
 */
package kinoVerwaltung;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Sitzstatus</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see kinoVerwaltung.KinoVerwaltungPackage#getSitzstatus()
 * @model
 * @generated
 */
public enum Sitzstatus implements Enumerator {
	/**
	 * The '<em><b>FREI</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FREI_VALUE
	 * @generated
	 * @ordered
	 */
	FREI(0, "FREI", "FREI"),

	/**
	 * The '<em><b>RESERVIERT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESERVIERT_VALUE
	 * @generated
	 * @ordered
	 */
	RESERVIERT(1, "RESERVIERT", "RESERVIERT"),

	/**
	 * The '<em><b>GEBUCHT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GEBUCHT_VALUE
	 * @generated
	 * @ordered
	 */
	GEBUCHT(2, "GEBUCHT", "GEBUCHT");

	/**
	 * The '<em><b>FREI</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #FREI
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int FREI_VALUE = 0;

	/**
	 * The '<em><b>RESERVIERT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #RESERVIERT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int RESERVIERT_VALUE = 1;

	/**
	 * The '<em><b>GEBUCHT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #GEBUCHT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int GEBUCHT_VALUE = 2;

	/**
	 * An array of all the '<em><b>Sitzstatus</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Sitzstatus[] VALUES_ARRAY = new Sitzstatus[] { FREI, RESERVIERT, GEBUCHT, };

	/**
	 * A public read-only list of all the '<em><b>Sitzstatus</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Sitzstatus> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Sitzstatus</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Sitzstatus get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Sitzstatus result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Sitzstatus</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Sitzstatus getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Sitzstatus result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Sitzstatus</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Sitzstatus get(int value) {
		switch (value) {
		case FREI_VALUE:
			return FREI;
		case RESERVIERT_VALUE:
			return RESERVIERT;
		case GEBUCHT_VALUE:
			return GEBUCHT;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private Sitzstatus(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
		return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}

} //Sitzstatus
