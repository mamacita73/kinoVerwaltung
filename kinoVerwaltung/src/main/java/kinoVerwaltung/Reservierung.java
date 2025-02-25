/**
 */
package kinoVerwaltung;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reservierung</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Eine Reservierung bezieht sich immer auf genau eine Filmvorstellung.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.Reservierung#getReservierungsnummer <em>Reservierungsnummer</em>}</li>
 *   <li>{@link kinoVerwaltung.Reservierung#getDatum <em>Datum</em>}</li>
 *   <li>{@link kinoVerwaltung.Reservierung#getStatus <em>Status</em>}</li>
 *   <li>{@link kinoVerwaltung.Reservierung#getSitzplaetze <em>Sitzplaetze</em>}</li>
 *   <li>{@link kinoVerwaltung.Reservierung#getBuchung <em>Buchung</em>}</li>
 *   <li>{@link kinoVerwaltung.Reservierung#getVorstellung <em>Vorstellung</em>}</li>
 * </ul>
 *
 * @see kinoVerwaltung.KinoVerwaltungPackage#getReservierung()
 * @model
 * @generated
 */
public interface Reservierung extends EObject {
	/**
	 * Returns the value of the '<em><b>Reservierungsnummer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reservierungsnummer</em>' attribute.
	 * @see #setReservierungsnummer(String)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getReservierung_Reservierungsnummer()
	 * @model
	 * @generated
	 */
	String getReservierungsnummer();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Reservierung#getReservierungsnummer <em>Reservierungsnummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reservierungsnummer</em>' attribute.
	 * @see #getReservierungsnummer()
	 * @generated
	 */
	void setReservierungsnummer(String value);

	/**
	 * Returns the value of the '<em><b>Datum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Datum</em>' attribute.
	 * @see #setDatum(Date)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getReservierung_Datum()
	 * @model
	 * @generated
	 */
	Date getDatum();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Reservierung#getDatum <em>Datum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Datum</em>' attribute.
	 * @see #getDatum()
	 * @generated
	 */
	void setDatum(Date value);

	/**
	 * Returns the value of the '<em><b>Status</b></em>' attribute.
	 * The default value is <code>"OFFEN"</code>.
	 * The literals are from the enumeration {@link kinoVerwaltung.Status}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Status</em>' attribute.
	 * @see kinoVerwaltung.Status
	 * @see #setStatus(Status)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getReservierung_Status()
	 * @model default="OFFEN"
	 * @generated
	 */
	Status getStatus();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Reservierung#getStatus <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Status</em>' attribute.
	 * @see kinoVerwaltung.Status
	 * @see #getStatus()
	 * @generated
	 */
	void setStatus(Status value);

	/**
	 * Returns the value of the '<em><b>Sitzplaetze</b></em>' reference list.
	 * The list contents are of type {@link kinoVerwaltung.Sitzplatz}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sitzplaetze</em>' reference list.
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getReservierung_Sitzplaetze()
	 * @model
	 * @generated
	 */
	EList<Sitzplatz> getSitzplaetze();

	/**
	 * Returns the value of the '<em><b>Buchung</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Eine Buchung kann, muss aber nicht aus einer Reservierung stammen
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Buchung</em>' reference.
	 * @see #setBuchung(Buchung)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getReservierung_Buchung()
	 * @model
	 * @generated
	 */
	Buchung getBuchung();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Reservierung#getBuchung <em>Buchung</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Buchung</em>' reference.
	 * @see #getBuchung()
	 * @generated
	 */
	void setBuchung(Buchung value);

	/**
	 * Returns the value of the '<em><b>Vorstellung</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Vorstellung</em>' reference.
	 * @see #setVorstellung(Vorstellung)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getReservierung_Vorstellung()
	 * @model required="true"
	 * @generated
	 */
	Vorstellung getVorstellung();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Reservierung#getVorstellung <em>Vorstellung</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Vorstellung</em>' reference.
	 * @see #getVorstellung()
	 * @generated
	 */
	void setVorstellung(Vorstellung value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  Erstellt eine Buchung aus einer Reservierung und entfernt diese.
	 *  Ändert status = GEBUCHT
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	Buchung wandelReservierungInBuchung();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Setzt Sitzstatus = FREI zurück und löscht die Reservierung.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void storniereReservierung();

} // Reservierung
