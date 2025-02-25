/**
 */
package kinoVerwaltung;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Benutzer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.Benutzer#getBenutzername <em>Benutzername</em>}</li>
 *   <li>{@link kinoVerwaltung.Benutzer#getEmail <em>Email</em>}</li>
 *   <li>{@link kinoVerwaltung.Benutzer#getPasswort <em>Passwort</em>}</li>
 *   <li>{@link kinoVerwaltung.Benutzer#getRolle <em>Rolle</em>}</li>
 *   <li>{@link kinoVerwaltung.Benutzer#getReservierungen <em>Reservierungen</em>}</li>
 *   <li>{@link kinoVerwaltung.Benutzer#getBuchungen <em>Buchungen</em>}</li>
 * </ul>
 *
 * @see kinoVerwaltung.KinoVerwaltungPackage#getBenutzer()
 * @model
 * @generated
 */
public interface Benutzer extends EObject {
	/**
	 * Returns the value of the '<em><b>Benutzername</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Benutzername</em>' attribute.
	 * @see #setBenutzername(String)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getBenutzer_Benutzername()
	 * @model
	 * @generated
	 */
	String getBenutzername();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Benutzer#getBenutzername <em>Benutzername</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Benutzername</em>' attribute.
	 * @see #getBenutzername()
	 * @generated
	 */
	void setBenutzername(String value);

	/**
	 * Returns the value of the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Email</em>' attribute.
	 * @see #setEmail(String)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getBenutzer_Email()
	 * @model
	 * @generated
	 */
	String getEmail();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Benutzer#getEmail <em>Email</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Email</em>' attribute.
	 * @see #getEmail()
	 * @generated
	 */
	void setEmail(String value);

	/**
	 * Returns the value of the '<em><b>Passwort</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Passwort</em>' attribute.
	 * @see #setPasswort(String)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getBenutzer_Passwort()
	 * @model
	 * @generated
	 */
	String getPasswort();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Benutzer#getPasswort <em>Passwort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Passwort</em>' attribute.
	 * @see #getPasswort()
	 * @generated
	 */
	void setPasswort(String value);

	/**
	 * Returns the value of the '<em><b>Rolle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Rolle</em>' attribute.
	 * @see #setRolle(String)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getBenutzer_Rolle()
	 * @model
	 * @generated
	 */
	String getRolle();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Benutzer#getRolle <em>Rolle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Rolle</em>' attribute.
	 * @see #getRolle()
	 * @generated
	 */
	void setRolle(String value);

	/**
	 * Returns the value of the '<em><b>Reservierungen</b></em>' reference list.
	 * The list contents are of type {@link kinoVerwaltung.Reservierung}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reservierungen</em>' reference list.
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getBenutzer_Reservierungen()
	 * @model
	 * @generated
	 */
	EList<Reservierung> getReservierungen();

	/**
	 * Returns the value of the '<em><b>Buchungen</b></em>' reference list.
	 * The list contents are of type {@link kinoVerwaltung.Buchung}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Buchungen</em>' reference list.
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getBenutzer_Buchungen()
	 * @model
	 * @generated
	 */
	EList<Buchung> getBuchungen();

} // Benutzer
