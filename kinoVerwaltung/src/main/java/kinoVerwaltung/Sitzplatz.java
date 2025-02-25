/**
 */
package kinoVerwaltung;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sitzplatz</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.Sitzplatz#getReihe <em>Reihe</em>}</li>
 *   <li>{@link kinoVerwaltung.Sitzplatz#getNummer <em>Nummer</em>}</li>
 *   <li>{@link kinoVerwaltung.Sitzplatz#getSitzkategorie <em>Sitzkategorie</em>}</li>
 *   <li>{@link kinoVerwaltung.Sitzplatz#getSitzreihe <em>Sitzreihe</em>}</li>
 *   <li>{@link kinoVerwaltung.Sitzplatz#getSitzstatus <em>Sitzstatus</em>}</li>
 * </ul>
 *
 * @see kinoVerwaltung.KinoVerwaltungPackage#getSitzplatz()
 * @model
 * @generated
 */
public interface Sitzplatz extends EObject {
	/**
	 * Returns the value of the '<em><b>Reihe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reihe</em>' attribute.
	 * @see #setReihe(int)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSitzplatz_Reihe()
	 * @model
	 * @generated
	 */
	int getReihe();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Sitzplatz#getReihe <em>Reihe</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reihe</em>' attribute.
	 * @see #getReihe()
	 * @generated
	 */
	void setReihe(int value);

	/**
	 * Returns the value of the '<em><b>Nummer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nummer</em>' attribute.
	 * @see #setNummer(int)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSitzplatz_Nummer()
	 * @model
	 * @generated
	 */
	int getNummer();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Sitzplatz#getNummer <em>Nummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nummer</em>' attribute.
	 * @see #getNummer()
	 * @generated
	 */
	void setNummer(int value);

	/**
	 * Returns the value of the '<em><b>Sitzkategorie</b></em>' attribute.
	 * The literals are from the enumeration {@link kinoVerwaltung.Sitzkategorie}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sitzkategorie</em>' attribute.
	 * @see kinoVerwaltung.Sitzkategorie
	 * @see #setSitzkategorie(Sitzkategorie)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSitzplatz_Sitzkategorie()
	 * @model
	 * @generated
	 */
	Sitzkategorie getSitzkategorie();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Sitzplatz#getSitzkategorie <em>Sitzkategorie</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sitzkategorie</em>' attribute.
	 * @see kinoVerwaltung.Sitzkategorie
	 * @see #getSitzkategorie()
	 * @generated
	 */
	void setSitzkategorie(Sitzkategorie value);

	/**
	 * Returns the value of the '<em><b>Sitzreihe</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Jeder Sitzplatz gehört zu genau einer Sitzreihe
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Sitzreihe</em>' reference.
	 * @see #setSitzreihe(Sitzreihe)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSitzplatz_Sitzreihe()
	 * @model required="true"
	 * @generated
	 */
	Sitzreihe getSitzreihe();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Sitzplatz#getSitzreihe <em>Sitzreihe</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sitzreihe</em>' reference.
	 * @see #getSitzreihe()
	 * @generated
	 */
	void setSitzreihe(Sitzreihe value);

	/**
	 * Returns the value of the '<em><b>Sitzstatus</b></em>' attribute.
	 * The default value is <code>"FREI"</code>.
	 * The literals are from the enumeration {@link kinoVerwaltung.Sitzstatus}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sitzstatus</em>' attribute.
	 * @see kinoVerwaltung.Sitzstatus
	 * @see #setSitzstatus(Sitzstatus)
	 * @see kinoVerwaltung.KinoVerwaltungPackage#getSitzplatz_Sitzstatus()
	 * @model default="FREI"
	 * @generated
	 */
	Sitzstatus getSitzstatus();

	/**
	 * Sets the value of the '{@link kinoVerwaltung.Sitzplatz#getSitzstatus <em>Sitzstatus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sitzstatus</em>' attribute.
	 * @see kinoVerwaltung.Sitzstatus
	 * @see #getSitzstatus()
	 * @generated
	 */
	void setSitzstatus(Sitzstatus value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Prüft, ob der Sitz frei ist (Sitzstatus = FREI).
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	boolean istVerfügbar();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Setzt Sitzstatus = RESERVIERT
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void reservieren();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  Setzt Sitzstatus = GEBUCHT.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated
	 */
	void buchen();

} // Sitzplatz
