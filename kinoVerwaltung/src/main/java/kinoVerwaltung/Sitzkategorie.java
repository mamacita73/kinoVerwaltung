/**
 */
package kinoVerwaltung;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Sitzkategorie</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see kinoVerwaltung.KinoVerwaltungPackage#getSitzkategorie()
 * @model
 * @generated
 */
public enum Sitzkategorie implements Enumerator {
	/**
	 * The '<em><b>PARKETT</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARKETT_VALUE
	 * @generated
	 * @ordered
	 */
	PARKETT(0, "PARKETT", "PARKETT"),

	/**
	 * The '<em><b>LOGE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGE_VALUE
	 * @generated
	 * @ordered
	 */
	LOGE(1, "LOGE", "LOGE"),

	/**
	 * The '<em><b>LOGE SERVICE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGE_SERVICE_VALUE
	 * @generated
	 * @ordered
	 */
	LOGE_SERVICE(2, "LOGE_SERVICE", "LOGE_SERVICE");

	/**
	 * The '<em><b>PARKETT</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #PARKETT
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PARKETT_VALUE = 0;

	/**
	 * The '<em><b>LOGE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOGE_VALUE = 1;

	/**
	 * The '<em><b>LOGE SERVICE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOGE_SERVICE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LOGE_SERVICE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Sitzkategorie</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final Sitzkategorie[] VALUES_ARRAY = new Sitzkategorie[] { PARKETT, LOGE, LOGE_SERVICE, };

	/**
	 * A public read-only list of all the '<em><b>Sitzkategorie</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<Sitzkategorie> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Sitzkategorie</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Sitzkategorie get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Sitzkategorie result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Sitzkategorie</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Sitzkategorie getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			Sitzkategorie result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Sitzkategorie</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static Sitzkategorie get(int value) {
		switch (value) {
		case PARKETT_VALUE:
			return PARKETT;
		case LOGE_VALUE:
			return LOGE;
		case LOGE_SERVICE_VALUE:
			return LOGE_SERVICE;
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
	private Sitzkategorie(int value, String name, String literal) {
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

} //Sitzkategorie
