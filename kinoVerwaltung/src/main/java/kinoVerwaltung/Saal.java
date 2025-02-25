/**
 */
package kinoVerwaltung;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Saal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.Saal#getName <em>Name</em>}</li>
 *   <li>{@link kinoVerwaltung.Saal#getAnzahlReihen <em>Anzahl Reihen</em>}</li>
 *   <li>{@link kinoVerwaltung.Saal#getVorstellungen <em>Vorstellungen</em>}</li>
 *   <li>{@link kinoVerwaltung.Saal#getSitzreihen <em>Sitzreihen</em>}</li>
 *   <li>{@link kinoVerwaltung.Saal#isIstFreigegeben <em>Ist Freigegeben</em>}</li>
 * </ul>
 *
 * @see kinoVerwaltung.KinoVerwaltungPackage#getSaal()
 * @model
 * @generated
 */
public interface Saal extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSaal_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Saal#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Anzahl Reihen</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anzahl Reihen</em>' attribute.
	 * @see #setAnzahlReihen(int)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSaal_AnzahlReihen()
	 * @model
	 * @generated
	 */
	int getAnzahlReihen();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Saal#getAnzahlReihen <em>Anzahl Reihen</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Anzahl Reihen</em>' attribute.
	 * @see #getAnzahlReihen()
	 * @generated
	 */
	void setAnzahlReihen(int value);

	/**
	 * Returns the value of the '<em><b>Vorstellungen</b></em>' containment reference list.
	 * The list contents are of type {@link kinoVerwaltung.Vorstellung}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Ein Saal kann mehrere Vorstellungen haben, deshalb ist die Beziehung eine Containment-Referenz.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Vorstellungen</em>' containment reference list.
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSaal_Vorstellungen()
	 * @model containment="true"
	 * @generated
	 */
	EList<Vorstellung> getVorstellungen();

	/**
	 * Returns the value of the '<em><b>Sitzreihen</b></em>' containment reference list.
	 * The list contents are of type {@link kinoVerwaltung.Sitzreihe}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Jede Sitzreihe gehört genau zu einem Saal.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sitzreihen</em>' containment reference list.
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSaal_Sitzreihen()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Sitzreihe> getSitzreihen();

	/**
	 * Returns the value of the '<em><b>Ist Freigegeben</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ist Freigegeben</em>' attribute.
	 * @see #setIstFreigegeben(boolean)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSaal_IstFreigegeben()
	 * @model
	 * @generated
	 */
	boolean isIstFreigegeben();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Saal#isIstFreigegeben <em>Ist Freigegeben</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ist Freigegeben</em>' attribute.
	 * @see #isIstFreigegeben()
	 * @generated
	 */
	void setIstFreigegeben(boolean value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	void freigeben();

} // Saal
