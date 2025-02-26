/**
 */
package kinoVerwaltung;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import java.util.ArrayList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sitzreihe</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Eine Sitzreihe ist eine Gruppe von Sitzplatz-Objekten innerhalb eines Saal.
 * Jede Sitzreihe gehört genau zu einem Saal.
 * Jede Sitzreihe hat eine feste Anzahl an Plätzen.
 * Sitzplatz gehört zu genau einer Sitzreihe.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.Sitzreihe#getNummer <em>Nummer</em>}</li>
 *   <li>{@link kinoVerwaltung.Sitzreihe#getAnzahlPlaetze <em>Anzahl Plaetze</em>}</li>
 *   <li>{@link kinoVerwaltung.Sitzreihe#getSitzplaetze <em>Sitzplaetze</em>}</li>
 * </ul>
 *
 * @see kinoVerwaltung.KinoVerwaltungPackage#getSitzreihe()
 * @model
 * @generated
 */
public interface Sitzreihe extends EObject {
	/**
	 * Returns the value of the '<em><b>Nummer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nummer</em>' attribute.
	 * @see #setNummer(int)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSitzreihe_Nummer()
	 * @model
	 * @generated
	 */
	int getNummer();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Sitzreihe#getNummer <em>Nummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nummer</em>' attribute.
	 * @see #getNummer()
	 * @generated
	 */
	void setNummer(int value);

	/**
	 * Returns the value of the '<em><b>Anzahl Plaetze</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Anzahl Plaetze</em>' attribute.
	 * @see #setAnzahlPlaetze(int)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSitzreihe_AnzahlPlaetze()
	 * @model
	 * @generated
	 */
	int getAnzahlPlaetze();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Sitzreihe#getAnzahlPlaetze <em>Anzahl Plaetze</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Anzahl Plaetze</em>' attribute.
	 * @see #getAnzahlPlaetze()
	 * @generated
	 */
	void setAnzahlPlaetze(int value);

	/**
	 * Returns the value of the '<em><b>Sitzplaetze</b></em>' containment reference list.
	 * The list contents are of type {@link kinoVerwaltung.Sitzplatz}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sitzplaetze</em>' containment reference list.
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSitzreihe_Sitzplaetze()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Sitzplatz> getSitzplaetze();

} // Sitzreihe
