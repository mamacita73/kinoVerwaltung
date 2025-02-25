/**
 */
package kinoVerwaltung;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Vorstellung</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.Vorstellung#getFilmTitle <em>Film Title</em>}</li>
 *   <li>{@link kinoVerwaltung.Vorstellung#getStartzeit <em>Startzeit</em>}</li>
 *   <li>{@link kinoVerwaltung.Vorstellung#getDauerMinuten <em>Dauer Minuten</em>}</li>
 *   <li>{@link kinoVerwaltung.Vorstellung#getBuchungen <em>Buchungen</em>}</li>
 * </ul>
 *
 * @see kinoVerwaltung.KinoVerwaltungPackage#getVorstellung()
 * @model
 * @generated
 */
public interface Vorstellung extends EObject {
	/**
	 * Returns the value of the '<em><b>Film Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Film Title</em>' attribute.
	 * @see #setFilmTitle(String)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getVorstellung_FilmTitle()
	 * @model
	 * @generated
	 */
	String getFilmTitle();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Vorstellung#getFilmTitle <em>Film Title</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Film Title</em>' attribute.
	 * @see #getFilmTitle()
	 * @generated
	 */
	void setFilmTitle(String value);

	/**
	 * Returns the value of the '<em><b>Startzeit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Startzeit</em>' attribute.
	 * @see #setStartzeit(Date)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getVorstellung_Startzeit()
	 * @model
	 * @generated
	 */
	Date getStartzeit();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Vorstellung#getStartzeit <em>Startzeit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Startzeit</em>' attribute.
	 * @see #getStartzeit()
	 * @generated
	 */
	void setStartzeit(Date value);

	/**
	 * Returns the value of the '<em><b>Dauer Minuten</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dauer Minuten</em>' attribute.
	 * @see #setDauerMinuten(int)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getVorstellung_DauerMinuten()
	 * @model
	 * @generated
	 */
	int getDauerMinuten();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Vorstellung#getDauerMinuten <em>Dauer Minuten</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dauer Minuten</em>' attribute.
	 * @see #getDauerMinuten()
	 * @generated
	 */
	void setDauerMinuten(int value);

	/**
	 * Returns the value of the '<em><b>Buchungen</b></em>' containment reference list.
	 * The list contents are of type {@link kinoVerwaltung.Buchung}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 	Eine Vorstellung kann mehrere Buchungen haben.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Buchungen</em>' containment reference list.
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getVorstellung_Buchungen()
	 * @model containment="true"
	 * @generated
	 */
	EList<Buchung> getBuchungen();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Summiert alle Buchung.preis für die Vorstellung.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	double berechneEinnahmen();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Gibt alle freien Sitzplätze dieser Vorstellung zurück.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	void getFreiePlätze();

} // Vorstellung
