/**
 */
package kinoVerwaltung;

import java.util.Date;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Buchung</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.Buchung#getBuchungsnummer <em>Buchungsnummer</em>}</li>
 *   <li>{@link kinoVerwaltung.Buchung#getDatum <em>Datum</em>}</li>
 *   <li>{@link kinoVerwaltung.Buchung#getPreis <em>Preis</em>}</li>
 *   <li>{@link kinoVerwaltung.Buchung#getSitzplaetze <em>Sitzplaetze</em>}</li>
 * </ul>
 *
 * @see kinoVerwaltung.KinoVerwaltungPackage#getBuchung()
 * @model
 * @generated
 */
public interface Buchung extends EObject {
	/**
	 * Returns the value of the '<em><b>Buchungsnummer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Buchungsnummer</em>' attribute.
	 * @see #setBuchungsnummer(String)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getBuchung_Buchungsnummer()
	 * @model
	 * @generated
	 */
	String getBuchungsnummer();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Buchung#getBuchungsnummer <em>Buchungsnummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Buchungsnummer</em>' attribute.
	 * @see #getBuchungsnummer()
	 * @generated
	 */
	void setBuchungsnummer(String value);

	/**
	 * Returns the value of the '<em><b>Datum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Datum</em>' attribute.
	 * @see #setDatum(Date)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getBuchung_Datum()
	 * @model
	 * @generated
	 */
	Date getDatum();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Buchung#getDatum <em>Datum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Datum</em>' attribute.
	 * @see #getDatum()
	 * @generated
	 */
	void setDatum(Date value);

	/**
	 * Returns the value of the '<em><b>Preis</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Preis</em>' attribute.
	 * @see #setPreis(double)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getBuchung_Preis()
	 * @model
	 * @generated
	 */
	double getPreis();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Buchung#getPreis <em>Preis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Preis</em>' attribute.
	 * @see #getPreis()
	 * @generated
	 */
	void setPreis(double value);

	/**
	 * Returns the value of the '<em><b>Sitzplaetze</b></em>' reference list.
	 * The list contents are of type {@link kinoVerwaltung.Sitzplatz}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Eine Buchung kann mehrere Sitzplätze umfassen, aber die Buchung "besitzt" die Sitzplätze nicht direkt.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sitzplaetze</em>' reference list.
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getBuchung_Sitzplaetze()
	 * @model
	 * @generated
	 */
	EList<Sitzplatz> getSitzplaetze();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Berechnet den Preis basierend auf der Sitzkategorie
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	double berechnePreis();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Gibt die Buchungsinformationen formatiert zurück.
	 * <!-- end-model-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	String getBuchungsdetails();

} // Buchung
