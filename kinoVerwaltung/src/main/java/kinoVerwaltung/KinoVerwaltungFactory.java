/**
 */
package kinoVerwaltung;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see kinoVerwaltung.KinoVerwaltungPackage
 * @generated
 */
public interface KinoVerwaltungFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	KinoVerwaltungFactory eINSTANCE = kinoVerwaltung.impl.KinoVerwaltungFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Kino</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Kino</em>'.
	 * @generated
	 */
	Kino createKino();

	/**
	 * Returns a new object of class '<em>Saal</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Saal</em>'.
	 * @generated
	 */
	Saal createSaal();

	/**
	 * Returns a new object of class '<em>Sitzplatz</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sitzplatz</em>'.
	 * @generated
	 */
	Sitzplatz createSitzplatz();

	/**
	 * Returns a new object of class '<em>Vorstellung</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Vorstellung</em>'.
	 * @generated
	 */
	Vorstellung createVorstellung();

	/**
	 * Returns a new object of class '<em>Buchung</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Buchung</em>'.
	 * @generated
	 */
	Buchung createBuchung();

	/**
	 * Returns a new object of class '<em>Benutzer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Benutzer</em>'.
	 * @generated
	 */
	Benutzer createBenutzer();

	/**
	 * Returns a new object of class '<em>Reservierung</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reservierung</em>'.
	 * @generated
	 */
	Reservierung createReservierung();

	/**
	 * Returns a new object of class '<em>Sitzreihe</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Sitzreihe</em>'.
	 * @generated
	 */
	Sitzreihe createSitzreihe();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	KinoVerwaltungPackage getKinoVerwaltungPackage();

} //KinoVerwaltungFactory
