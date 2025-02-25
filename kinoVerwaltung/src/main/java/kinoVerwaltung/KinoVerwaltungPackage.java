/**
 */
package kinoVerwaltung;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see kinoVerwaltung.KinoVerwaltungFactory
 * @model kind="package"
 * @generated
 */
public interface KinoVerwaltungPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "kinoVerwaltung";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.example.org/kinoVerwaltung";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "kinoVerwaltung";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	KinoVerwaltungPackage eINSTANCE = kinoVerwaltung.impl.KinoVerwaltungPackageImpl.init();

	/**
	 * The meta object id for the '{@link kinoVerwaltung.impl.KinoImpl <em>Kino</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kinoVerwaltung.impl.KinoImpl
	 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getKino()
	 * @generated
	 */
	int KINO = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KINO__NAME = 0;

	/**
	 * The feature id for the '<em><b>Adresse</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KINO__ADRESSE = 1;

	/**
	 * The feature id for the '<em><b>Saele</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KINO__SAELE = 2;

	/**
	 * The number of structural features of the '<em>Kino</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KINO_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Kino</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int KINO_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link kinoVerwaltung.impl.SaalImpl <em>Saal</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kinoVerwaltung.impl.SaalImpl
	 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getSaal()
	 * @generated
	 */
	int SAAL = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAAL__NAME = 0;

	/**
	 * The feature id for the '<em><b>Anzahl Reihen</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAAL__ANZAHL_REIHEN = 1;

	/**
	 * The feature id for the '<em><b>Vorstellungen</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAAL__VORSTELLUNGEN = 2;

	/**
	 * The feature id for the '<em><b>Sitzreihen</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAAL__SITZREIHEN = 3;

	/**
	 * The feature id for the '<em><b>Ist Freigegeben</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAAL__IST_FREIGEGEBEN = 4;

	/**
	 * The number of structural features of the '<em>Saal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAAL_FEATURE_COUNT = 5;

	/**
	 * The operation id for the '<em>Freigeben</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAAL___FREIGEBEN = 0;

	/**
	 * The number of operations of the '<em>Saal</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SAAL_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link kinoVerwaltung.impl.SitzplatzImpl <em>Sitzplatz</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kinoVerwaltung.impl.SitzplatzImpl
	 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getSitzplatz()
	 * @generated
	 */
	int SITZPLATZ = 2;

	/**
	 * The feature id for the '<em><b>Reihe</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZPLATZ__REIHE = 0;

	/**
	 * The feature id for the '<em><b>Nummer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZPLATZ__NUMMER = 1;

	/**
	 * The feature id for the '<em><b>Sitzkategorie</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZPLATZ__SITZKATEGORIE = 2;

	/**
	 * The feature id for the '<em><b>Sitzreihe</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZPLATZ__SITZREIHE = 3;

	/**
	 * The feature id for the '<em><b>Sitzstatus</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZPLATZ__SITZSTATUS = 4;

	/**
	 * The number of structural features of the '<em>Sitzplatz</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZPLATZ_FEATURE_COUNT = 5;

	/**
	 * The operation id for the '<em>Ist Verfügbar</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZPLATZ___IST_VERFÜGBAR = 0;

	/**
	 * The operation id for the '<em>Reservieren</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZPLATZ___RESERVIEREN = 1;

	/**
	 * The operation id for the '<em>Buchen</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZPLATZ___BUCHEN = 2;

	/**
	 * The number of operations of the '<em>Sitzplatz</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZPLATZ_OPERATION_COUNT = 3;

	/**
	 * The meta object id for the '{@link kinoVerwaltung.impl.VorstellungImpl <em>Vorstellung</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kinoVerwaltung.impl.VorstellungImpl
	 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getVorstellung()
	 * @generated
	 */
	int VORSTELLUNG = 3;

	/**
	 * The feature id for the '<em><b>Film Title</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSTELLUNG__FILM_TITLE = 0;

	/**
	 * The feature id for the '<em><b>Startzeit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSTELLUNG__STARTZEIT = 1;

	/**
	 * The feature id for the '<em><b>Dauer Minuten</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSTELLUNG__DAUER_MINUTEN = 2;

	/**
	 * The feature id for the '<em><b>Buchungen</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSTELLUNG__BUCHUNGEN = 3;

	/**
	 * The number of structural features of the '<em>Vorstellung</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSTELLUNG_FEATURE_COUNT = 4;

	/**
	 * The operation id for the '<em>Berechne Einnahmen</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSTELLUNG___BERECHNE_EINNAHMEN = 0;

	/**
	 * The operation id for the '<em>Get Freie Plätze</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSTELLUNG___GET_FREIE_PLÄTZE = 1;

	/**
	 * The number of operations of the '<em>Vorstellung</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VORSTELLUNG_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link kinoVerwaltung.impl.BuchungImpl <em>Buchung</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kinoVerwaltung.impl.BuchungImpl
	 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getBuchung()
	 * @generated
	 */
	int BUCHUNG = 4;

	/**
	 * The feature id for the '<em><b>Buchungsnummer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUCHUNG__BUCHUNGSNUMMER = 0;

	/**
	 * The feature id for the '<em><b>Datum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUCHUNG__DATUM = 1;

	/**
	 * The feature id for the '<em><b>Preis</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUCHUNG__PREIS = 2;

	/**
	 * The feature id for the '<em><b>Sitzplaetze</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUCHUNG__SITZPLAETZE = 3;

	/**
	 * The number of structural features of the '<em>Buchung</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUCHUNG_FEATURE_COUNT = 4;

	/**
	 * The operation id for the '<em>Berechne Preis</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUCHUNG___BERECHNE_PREIS = 0;

	/**
	 * The operation id for the '<em>Get Buchungsdetails</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUCHUNG___GET_BUCHUNGSDETAILS = 1;

	/**
	 * The number of operations of the '<em>Buchung</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BUCHUNG_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link kinoVerwaltung.impl.BenutzerImpl <em>Benutzer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kinoVerwaltung.impl.BenutzerImpl
	 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getBenutzer()
	 * @generated
	 */
	int BENUTZER = 5;

	/**
	 * The feature id for the '<em><b>Benutzername</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENUTZER__BENUTZERNAME = 0;

	/**
	 * The feature id for the '<em><b>Email</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENUTZER__EMAIL = 1;

	/**
	 * The feature id for the '<em><b>Passwort</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENUTZER__PASSWORT = 2;

	/**
	 * The feature id for the '<em><b>Rolle</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENUTZER__ROLLE = 3;

	/**
	 * The feature id for the '<em><b>Reservierungen</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENUTZER__RESERVIERUNGEN = 4;

	/**
	 * The feature id for the '<em><b>Buchungen</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENUTZER__BUCHUNGEN = 5;

	/**
	 * The number of structural features of the '<em>Benutzer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENUTZER_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Benutzer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BENUTZER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link kinoVerwaltung.impl.ReservierungImpl <em>Reservierung</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kinoVerwaltung.impl.ReservierungImpl
	 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getReservierung()
	 * @generated
	 */
	int RESERVIERUNG = 6;

	/**
	 * The feature id for the '<em><b>Reservierungsnummer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVIERUNG__RESERVIERUNGSNUMMER = 0;

	/**
	 * The feature id for the '<em><b>Datum</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVIERUNG__DATUM = 1;

	/**
	 * The feature id for the '<em><b>Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVIERUNG__STATUS = 2;

	/**
	 * The feature id for the '<em><b>Sitzplaetze</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVIERUNG__SITZPLAETZE = 3;

	/**
	 * The feature id for the '<em><b>Buchung</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVIERUNG__BUCHUNG = 4;

	/**
	 * The feature id for the '<em><b>Vorstellung</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVIERUNG__VORSTELLUNG = 5;

	/**
	 * The number of structural features of the '<em>Reservierung</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVIERUNG_FEATURE_COUNT = 6;

	/**
	 * The operation id for the '<em>Wandel Reservierung In Buchung</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVIERUNG___WANDEL_RESERVIERUNG_IN_BUCHUNG = 0;

	/**
	 * The operation id for the '<em>Storniere Reservierung</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVIERUNG___STORNIERE_RESERVIERUNG = 1;

	/**
	 * The number of operations of the '<em>Reservierung</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESERVIERUNG_OPERATION_COUNT = 2;

	/**
	 * The meta object id for the '{@link kinoVerwaltung.impl.SitzreiheImpl <em>Sitzreihe</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kinoVerwaltung.impl.SitzreiheImpl
	 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getSitzreihe()
	 * @generated
	 */
	int SITZREIHE = 7;

	/**
	 * The feature id for the '<em><b>Nummer</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZREIHE__NUMMER = 0;

	/**
	 * The feature id for the '<em><b>Anzahl Plaetze</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZREIHE__ANZAHL_PLAETZE = 1;

	/**
	 * The feature id for the '<em><b>Sitzplaetze</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZREIHE__SITZPLAETZE = 2;

	/**
	 * The number of structural features of the '<em>Sitzreihe</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZREIHE_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Sitzreihe</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITZREIHE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link kinoVerwaltung.Sitzkategorie <em>Sitzkategorie</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kinoVerwaltung.Sitzkategorie
	 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getSitzkategorie()
	 * @generated
	 */
	int SITZKATEGORIE = 8;

	/**
	 * The meta object id for the '{@link kinoVerwaltung.Status <em>Status</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kinoVerwaltung.Status
	 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getStatus()
	 * @generated
	 */
	int STATUS = 9;

	/**
	 * The meta object id for the '{@link kinoVerwaltung.Sitzstatus <em>Sitzstatus</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see kinoVerwaltung.Sitzstatus
	 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getSitzstatus()
	 * @generated
	 */
	int SITZSTATUS = 10;

	/**
	 * Returns the meta object for class '{@link kinoVerwaltung.Kino <em>Kino</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Kino</em>'.
	 * @see kinoVerwaltung.Kino
	 * @generated
	 */
	EClass getKino();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Kino#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see kinoVerwaltung.Kino#getName()
	 * @see #getKino()
	 * @generated
	 */
	EAttribute getKino_Name();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Kino#getAdresse <em>Adresse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Adresse</em>'.
	 * @see kinoVerwaltung.Kino#getAdresse()
	 * @see #getKino()
	 * @generated
	 */
	EAttribute getKino_Adresse();

	/**
	 * Returns the meta object for the containment reference list '{@link kinoVerwaltung.Kino#getSaele <em>Saele</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Saele</em>'.
	 * @see kinoVerwaltung.Kino#getSaele()
	 * @see #getKino()
	 * @generated
	 */
	EReference getKino_Saele();

	/**
	 * Returns the meta object for class '{@link kinoVerwaltung.Saal <em>Saal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Saal</em>'.
	 * @see kinoVerwaltung.Saal
	 * @generated
	 */
	EClass getSaal();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Saal#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see kinoVerwaltung.Saal#getName()
	 * @see #getSaal()
	 * @generated
	 */
	EAttribute getSaal_Name();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Saal#getAnzahlReihen <em>Anzahl Reihen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Anzahl Reihen</em>'.
	 * @see kinoVerwaltung.Saal#getAnzahlReihen()
	 * @see #getSaal()
	 * @generated
	 */
	EAttribute getSaal_AnzahlReihen();

	/**
	 * Returns the meta object for the containment reference list '{@link kinoVerwaltung.Saal#getVorstellungen <em>Vorstellungen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Vorstellungen</em>'.
	 * @see kinoVerwaltung.Saal#getVorstellungen()
	 * @see #getSaal()
	 * @generated
	 */
	EReference getSaal_Vorstellungen();

	/**
	 * Returns the meta object for the containment reference list '{@link kinoVerwaltung.Saal#getSitzreihen <em>Sitzreihen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sitzreihen</em>'.
	 * @see kinoVerwaltung.Saal#getSitzreihen()
	 * @see #getSaal()
	 * @generated
	 */
	EReference getSaal_Sitzreihen();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Saal#isIstFreigegeben <em>Ist Freigegeben</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ist Freigegeben</em>'.
	 * @see kinoVerwaltung.Saal#isIstFreigegeben()
	 * @see #getSaal()
	 * @generated
	 */
	EAttribute getSaal_IstFreigegeben();

	/**
	 * Returns the meta object for the '{@link kinoVerwaltung.Saal#freigeben() <em>Freigeben</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Freigeben</em>' operation.
	 * @see kinoVerwaltung.Saal#freigeben()
	 * @generated
	 */
	EOperation getSaal__Freigeben();

	/**
	 * Returns the meta object for class '{@link kinoVerwaltung.Sitzplatz <em>Sitzplatz</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sitzplatz</em>'.
	 * @see kinoVerwaltung.Sitzplatz
	 * @generated
	 */
	EClass getSitzplatz();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Sitzplatz#getReihe <em>Reihe</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reihe</em>'.
	 * @see kinoVerwaltung.Sitzplatz#getReihe()
	 * @see #getSitzplatz()
	 * @generated
	 */
	EAttribute getSitzplatz_Reihe();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Sitzplatz#getNummer <em>Nummer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nummer</em>'.
	 * @see kinoVerwaltung.Sitzplatz#getNummer()
	 * @see #getSitzplatz()
	 * @generated
	 */
	EAttribute getSitzplatz_Nummer();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Sitzplatz#getSitzkategorie <em>Sitzkategorie</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sitzkategorie</em>'.
	 * @see kinoVerwaltung.Sitzplatz#getSitzkategorie()
	 * @see #getSitzplatz()
	 * @generated
	 */
	EAttribute getSitzplatz_Sitzkategorie();

	/**
	 * Returns the meta object for the reference '{@link kinoVerwaltung.Sitzplatz#getSitzreihe <em>Sitzreihe</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Sitzreihe</em>'.
	 * @see kinoVerwaltung.Sitzplatz#getSitzreihe()
	 * @see #getSitzplatz()
	 * @generated
	 */
	EReference getSitzplatz_Sitzreihe();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Sitzplatz#getSitzstatus <em>Sitzstatus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Sitzstatus</em>'.
	 * @see kinoVerwaltung.Sitzplatz#getSitzstatus()
	 * @see #getSitzplatz()
	 * @generated
	 */
	EAttribute getSitzplatz_Sitzstatus();

	/**
	 * Returns the meta object for the '{@link kinoVerwaltung.Sitzplatz#istVerfügbar() <em>Ist Verfügbar</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Ist Verfügbar</em>' operation.
	 * @see kinoVerwaltung.Sitzplatz#istVerfügbar()
	 * @generated
	 */
	EOperation getSitzplatz__IstVerfügbar();

	/**
	 * Returns the meta object for the '{@link kinoVerwaltung.Sitzplatz#reservieren() <em>Reservieren</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Reservieren</em>' operation.
	 * @see kinoVerwaltung.Sitzplatz#reservieren()
	 * @generated
	 */
	EOperation getSitzplatz__Reservieren();

	/**
	 * Returns the meta object for the '{@link kinoVerwaltung.Sitzplatz#buchen() <em>Buchen</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Buchen</em>' operation.
	 * @see kinoVerwaltung.Sitzplatz#buchen()
	 * @generated
	 */
	EOperation getSitzplatz__Buchen();

	/**
	 * Returns the meta object for class '{@link kinoVerwaltung.Vorstellung <em>Vorstellung</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Vorstellung</em>'.
	 * @see kinoVerwaltung.Vorstellung
	 * @generated
	 */
	EClass getVorstellung();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Vorstellung#getFilmTitle <em>Film Title</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Film Title</em>'.
	 * @see kinoVerwaltung.Vorstellung#getFilmTitle()
	 * @see #getVorstellung()
	 * @generated
	 */
	EAttribute getVorstellung_FilmTitle();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Vorstellung#getStartzeit <em>Startzeit</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Startzeit</em>'.
	 * @see kinoVerwaltung.Vorstellung#getStartzeit()
	 * @see #getVorstellung()
	 * @generated
	 */
	EAttribute getVorstellung_Startzeit();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Vorstellung#getDauerMinuten <em>Dauer Minuten</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dauer Minuten</em>'.
	 * @see kinoVerwaltung.Vorstellung#getDauerMinuten()
	 * @see #getVorstellung()
	 * @generated
	 */
	EAttribute getVorstellung_DauerMinuten();

	/**
	 * Returns the meta object for the containment reference list '{@link kinoVerwaltung.Vorstellung#getBuchungen <em>Buchungen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Buchungen</em>'.
	 * @see kinoVerwaltung.Vorstellung#getBuchungen()
	 * @see #getVorstellung()
	 * @generated
	 */
	EReference getVorstellung_Buchungen();

	/**
	 * Returns the meta object for the '{@link kinoVerwaltung.Vorstellung#berechneEinnahmen() <em>Berechne Einnahmen</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Berechne Einnahmen</em>' operation.
	 * @see kinoVerwaltung.Vorstellung#berechneEinnahmen()
	 * @generated
	 */
	EOperation getVorstellung__BerechneEinnahmen();

	/**
	 * Returns the meta object for the '{@link kinoVerwaltung.Vorstellung#getFreiePlätze() <em>Get Freie Plätze</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Freie Plätze</em>' operation.
	 * @see kinoVerwaltung.Vorstellung#getFreiePlätze()
	 * @generated
	 */
	EOperation getVorstellung__GetFreiePlätze();

	/**
	 * Returns the meta object for class '{@link kinoVerwaltung.Buchung <em>Buchung</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Buchung</em>'.
	 * @see kinoVerwaltung.Buchung
	 * @generated
	 */
	EClass getBuchung();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Buchung#getBuchungsnummer <em>Buchungsnummer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Buchungsnummer</em>'.
	 * @see kinoVerwaltung.Buchung#getBuchungsnummer()
	 * @see #getBuchung()
	 * @generated
	 */
	EAttribute getBuchung_Buchungsnummer();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Buchung#getDatum <em>Datum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Datum</em>'.
	 * @see kinoVerwaltung.Buchung#getDatum()
	 * @see #getBuchung()
	 * @generated
	 */
	EAttribute getBuchung_Datum();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Buchung#getPreis <em>Preis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Preis</em>'.
	 * @see kinoVerwaltung.Buchung#getPreis()
	 * @see #getBuchung()
	 * @generated
	 */
	EAttribute getBuchung_Preis();

	/**
	 * Returns the meta object for the reference list '{@link kinoVerwaltung.Buchung#getSitzplaetze <em>Sitzplaetze</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sitzplaetze</em>'.
	 * @see kinoVerwaltung.Buchung#getSitzplaetze()
	 * @see #getBuchung()
	 * @generated
	 */
	EReference getBuchung_Sitzplaetze();

	/**
	 * Returns the meta object for the '{@link kinoVerwaltung.Buchung#berechnePreis() <em>Berechne Preis</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Berechne Preis</em>' operation.
	 * @see kinoVerwaltung.Buchung#berechnePreis()
	 * @generated
	 */
	EOperation getBuchung__BerechnePreis();

	/**
	 * Returns the meta object for the '{@link kinoVerwaltung.Buchung#getBuchungsdetails() <em>Get Buchungsdetails</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Buchungsdetails</em>' operation.
	 * @see kinoVerwaltung.Buchung#getBuchungsdetails()
	 * @generated
	 */
	EOperation getBuchung__GetBuchungsdetails();

	/**
	 * Returns the meta object for class '{@link kinoVerwaltung.Benutzer <em>Benutzer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Benutzer</em>'.
	 * @see kinoVerwaltung.Benutzer
	 * @generated
	 */
	EClass getBenutzer();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Benutzer#getBenutzername <em>Benutzername</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Benutzername</em>'.
	 * @see kinoVerwaltung.Benutzer#getBenutzername()
	 * @see #getBenutzer()
	 * @generated
	 */
	EAttribute getBenutzer_Benutzername();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Benutzer#getEmail <em>Email</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Email</em>'.
	 * @see kinoVerwaltung.Benutzer#getEmail()
	 * @see #getBenutzer()
	 * @generated
	 */
	EAttribute getBenutzer_Email();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Benutzer#getPasswort <em>Passwort</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Passwort</em>'.
	 * @see kinoVerwaltung.Benutzer#getPasswort()
	 * @see #getBenutzer()
	 * @generated
	 */
	EAttribute getBenutzer_Passwort();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Benutzer#getRolle <em>Rolle</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rolle</em>'.
	 * @see kinoVerwaltung.Benutzer#getRolle()
	 * @see #getBenutzer()
	 * @generated
	 */
	EAttribute getBenutzer_Rolle();

	/**
	 * Returns the meta object for the reference list '{@link kinoVerwaltung.Benutzer#getReservierungen <em>Reservierungen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Reservierungen</em>'.
	 * @see kinoVerwaltung.Benutzer#getReservierungen()
	 * @see #getBenutzer()
	 * @generated
	 */
	EReference getBenutzer_Reservierungen();

	/**
	 * Returns the meta object for the reference list '{@link kinoVerwaltung.Benutzer#getBuchungen <em>Buchungen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Buchungen</em>'.
	 * @see kinoVerwaltung.Benutzer#getBuchungen()
	 * @see #getBenutzer()
	 * @generated
	 */
	EReference getBenutzer_Buchungen();

	/**
	 * Returns the meta object for class '{@link kinoVerwaltung.Reservierung <em>Reservierung</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Reservierung</em>'.
	 * @see kinoVerwaltung.Reservierung
	 * @generated
	 */
	EClass getReservierung();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Reservierung#getReservierungsnummer <em>Reservierungsnummer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reservierungsnummer</em>'.
	 * @see kinoVerwaltung.Reservierung#getReservierungsnummer()
	 * @see #getReservierung()
	 * @generated
	 */
	EAttribute getReservierung_Reservierungsnummer();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Reservierung#getDatum <em>Datum</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Datum</em>'.
	 * @see kinoVerwaltung.Reservierung#getDatum()
	 * @see #getReservierung()
	 * @generated
	 */
	EAttribute getReservierung_Datum();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Reservierung#getStatus <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Status</em>'.
	 * @see kinoVerwaltung.Reservierung#getStatus()
	 * @see #getReservierung()
	 * @generated
	 */
	EAttribute getReservierung_Status();

	/**
	 * Returns the meta object for the reference list '{@link kinoVerwaltung.Reservierung#getSitzplaetze <em>Sitzplaetze</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sitzplaetze</em>'.
	 * @see kinoVerwaltung.Reservierung#getSitzplaetze()
	 * @see #getReservierung()
	 * @generated
	 */
	EReference getReservierung_Sitzplaetze();

	/**
	 * Returns the meta object for the reference '{@link kinoVerwaltung.Reservierung#getBuchung <em>Buchung</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Buchung</em>'.
	 * @see kinoVerwaltung.Reservierung#getBuchung()
	 * @see #getReservierung()
	 * @generated
	 */
	EReference getReservierung_Buchung();

	/**
	 * Returns the meta object for the reference '{@link kinoVerwaltung.Reservierung#getVorstellung <em>Vorstellung</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Vorstellung</em>'.
	 * @see kinoVerwaltung.Reservierung#getVorstellung()
	 * @see #getReservierung()
	 * @generated
	 */
	EReference getReservierung_Vorstellung();

	/**
	 * Returns the meta object for the '{@link kinoVerwaltung.Reservierung#wandelReservierungInBuchung() <em>Wandel Reservierung In Buchung</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Wandel Reservierung In Buchung</em>' operation.
	 * @see kinoVerwaltung.Reservierung#wandelReservierungInBuchung()
	 * @generated
	 */
	EOperation getReservierung__WandelReservierungInBuchung();

	/**
	 * Returns the meta object for the '{@link kinoVerwaltung.Reservierung#storniereReservierung() <em>Storniere Reservierung</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Storniere Reservierung</em>' operation.
	 * @see kinoVerwaltung.Reservierung#storniereReservierung()
	 * @generated
	 */
	EOperation getReservierung__StorniereReservierung();

	/**
	 * Returns the meta object for class '{@link kinoVerwaltung.Sitzreihe <em>Sitzreihe</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sitzreihe</em>'.
	 * @see kinoVerwaltung.Sitzreihe
	 * @generated
	 */
	EClass getSitzreihe();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Sitzreihe#getNummer <em>Nummer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nummer</em>'.
	 * @see kinoVerwaltung.Sitzreihe#getNummer()
	 * @see #getSitzreihe()
	 * @generated
	 */
	EAttribute getSitzreihe_Nummer();

	/**
	 * Returns the meta object for the attribute '{@link kinoVerwaltung.Sitzreihe#getAnzahlPlaetze <em>Anzahl Plaetze</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Anzahl Plaetze</em>'.
	 * @see kinoVerwaltung.Sitzreihe#getAnzahlPlaetze()
	 * @see #getSitzreihe()
	 * @generated
	 */
	EAttribute getSitzreihe_AnzahlPlaetze();

	/**
	 * Returns the meta object for the containment reference list '{@link kinoVerwaltung.Sitzreihe#getSitzplaetze <em>Sitzplaetze</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sitzplaetze</em>'.
	 * @see kinoVerwaltung.Sitzreihe#getSitzplaetze()
	 * @see #getSitzreihe()
	 * @generated
	 */
	EReference getSitzreihe_Sitzplaetze();

	/**
	 * Returns the meta object for enum '{@link kinoVerwaltung.Sitzkategorie <em>Sitzkategorie</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Sitzkategorie</em>'.
	 * @see kinoVerwaltung.Sitzkategorie
	 * @generated
	 */
	EEnum getSitzkategorie();

	/**
	 * Returns the meta object for enum '{@link kinoVerwaltung.Status <em>Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Status</em>'.
	 * @see kinoVerwaltung.Status
	 * @generated
	 */
	EEnum getStatus();

	/**
	 * Returns the meta object for enum '{@link kinoVerwaltung.Sitzstatus <em>Sitzstatus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Sitzstatus</em>'.
	 * @see kinoVerwaltung.Sitzstatus
	 * @generated
	 */
	EEnum getSitzstatus();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	KinoVerwaltungFactory getKinoVerwaltungFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link kinoVerwaltung.impl.KinoImpl <em>Kino</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kinoVerwaltung.impl.KinoImpl
		 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getKino()
		 * @generated
		 */
		EClass KINO = eINSTANCE.getKino();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KINO__NAME = eINSTANCE.getKino_Name();

		/**
		 * The meta object literal for the '<em><b>Adresse</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute KINO__ADRESSE = eINSTANCE.getKino_Adresse();

		/**
		 * The meta object literal for the '<em><b>Saele</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference KINO__SAELE = eINSTANCE.getKino_Saele();

		/**
		 * The meta object literal for the '{@link kinoVerwaltung.impl.SaalImpl <em>Saal</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kinoVerwaltung.impl.SaalImpl
		 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getSaal()
		 * @generated
		 */
		EClass SAAL = eINSTANCE.getSaal();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SAAL__NAME = eINSTANCE.getSaal_Name();

		/**
		 * The meta object literal for the '<em><b>Anzahl Reihen</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SAAL__ANZAHL_REIHEN = eINSTANCE.getSaal_AnzahlReihen();

		/**
		 * The meta object literal for the '<em><b>Vorstellungen</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SAAL__VORSTELLUNGEN = eINSTANCE.getSaal_Vorstellungen();

		/**
		 * The meta object literal for the '<em><b>Sitzreihen</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SAAL__SITZREIHEN = eINSTANCE.getSaal_Sitzreihen();

		/**
		 * The meta object literal for the '<em><b>Ist Freigegeben</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SAAL__IST_FREIGEGEBEN = eINSTANCE.getSaal_IstFreigegeben();

		/**
		 * The meta object literal for the '<em><b>Freigeben</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SAAL___FREIGEBEN = eINSTANCE.getSaal__Freigeben();

		/**
		 * The meta object literal for the '{@link kinoVerwaltung.impl.SitzplatzImpl <em>Sitzplatz</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kinoVerwaltung.impl.SitzplatzImpl
		 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getSitzplatz()
		 * @generated
		 */
		EClass SITZPLATZ = eINSTANCE.getSitzplatz();

		/**
		 * The meta object literal for the '<em><b>Reihe</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITZPLATZ__REIHE = eINSTANCE.getSitzplatz_Reihe();

		/**
		 * The meta object literal for the '<em><b>Nummer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITZPLATZ__NUMMER = eINSTANCE.getSitzplatz_Nummer();

		/**
		 * The meta object literal for the '<em><b>Sitzkategorie</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITZPLATZ__SITZKATEGORIE = eINSTANCE.getSitzplatz_Sitzkategorie();

		/**
		 * The meta object literal for the '<em><b>Sitzreihe</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITZPLATZ__SITZREIHE = eINSTANCE.getSitzplatz_Sitzreihe();

		/**
		 * The meta object literal for the '<em><b>Sitzstatus</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITZPLATZ__SITZSTATUS = eINSTANCE.getSitzplatz_Sitzstatus();

		/**
		 * The meta object literal for the '<em><b>Ist Verfügbar</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SITZPLATZ___IST_VERFÜGBAR = eINSTANCE.getSitzplatz__IstVerfügbar();

		/**
		 * The meta object literal for the '<em><b>Reservieren</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SITZPLATZ___RESERVIEREN = eINSTANCE.getSitzplatz__Reservieren();

		/**
		 * The meta object literal for the '<em><b>Buchen</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SITZPLATZ___BUCHEN = eINSTANCE.getSitzplatz__Buchen();

		/**
		 * The meta object literal for the '{@link kinoVerwaltung.impl.VorstellungImpl <em>Vorstellung</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kinoVerwaltung.impl.VorstellungImpl
		 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getVorstellung()
		 * @generated
		 */
		EClass VORSTELLUNG = eINSTANCE.getVorstellung();

		/**
		 * The meta object literal for the '<em><b>Film Title</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VORSTELLUNG__FILM_TITLE = eINSTANCE.getVorstellung_FilmTitle();

		/**
		 * The meta object literal for the '<em><b>Startzeit</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VORSTELLUNG__STARTZEIT = eINSTANCE.getVorstellung_Startzeit();

		/**
		 * The meta object literal for the '<em><b>Dauer Minuten</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute VORSTELLUNG__DAUER_MINUTEN = eINSTANCE.getVorstellung_DauerMinuten();

		/**
		 * The meta object literal for the '<em><b>Buchungen</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VORSTELLUNG__BUCHUNGEN = eINSTANCE.getVorstellung_Buchungen();

		/**
		 * The meta object literal for the '<em><b>Berechne Einnahmen</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation VORSTELLUNG___BERECHNE_EINNAHMEN = eINSTANCE.getVorstellung__BerechneEinnahmen();

		/**
		 * The meta object literal for the '<em><b>Get Freie Plätze</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation VORSTELLUNG___GET_FREIE_PLÄTZE = eINSTANCE.getVorstellung__GetFreiePlätze();

		/**
		 * The meta object literal for the '{@link kinoVerwaltung.impl.BuchungImpl <em>Buchung</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kinoVerwaltung.impl.BuchungImpl
		 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getBuchung()
		 * @generated
		 */
		EClass BUCHUNG = eINSTANCE.getBuchung();

		/**
		 * The meta object literal for the '<em><b>Buchungsnummer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUCHUNG__BUCHUNGSNUMMER = eINSTANCE.getBuchung_Buchungsnummer();

		/**
		 * The meta object literal for the '<em><b>Datum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUCHUNG__DATUM = eINSTANCE.getBuchung_Datum();

		/**
		 * The meta object literal for the '<em><b>Preis</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BUCHUNG__PREIS = eINSTANCE.getBuchung_Preis();

		/**
		 * The meta object literal for the '<em><b>Sitzplaetze</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BUCHUNG__SITZPLAETZE = eINSTANCE.getBuchung_Sitzplaetze();

		/**
		 * The meta object literal for the '<em><b>Berechne Preis</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation BUCHUNG___BERECHNE_PREIS = eINSTANCE.getBuchung__BerechnePreis();

		/**
		 * The meta object literal for the '<em><b>Get Buchungsdetails</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation BUCHUNG___GET_BUCHUNGSDETAILS = eINSTANCE.getBuchung__GetBuchungsdetails();

		/**
		 * The meta object literal for the '{@link kinoVerwaltung.impl.BenutzerImpl <em>Benutzer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kinoVerwaltung.impl.BenutzerImpl
		 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getBenutzer()
		 * @generated
		 */
		EClass BENUTZER = eINSTANCE.getBenutzer();

		/**
		 * The meta object literal for the '<em><b>Benutzername</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENUTZER__BENUTZERNAME = eINSTANCE.getBenutzer_Benutzername();

		/**
		 * The meta object literal for the '<em><b>Email</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENUTZER__EMAIL = eINSTANCE.getBenutzer_Email();

		/**
		 * The meta object literal for the '<em><b>Passwort</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENUTZER__PASSWORT = eINSTANCE.getBenutzer_Passwort();

		/**
		 * The meta object literal for the '<em><b>Rolle</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BENUTZER__ROLLE = eINSTANCE.getBenutzer_Rolle();

		/**
		 * The meta object literal for the '<em><b>Reservierungen</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BENUTZER__RESERVIERUNGEN = eINSTANCE.getBenutzer_Reservierungen();

		/**
		 * The meta object literal for the '<em><b>Buchungen</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BENUTZER__BUCHUNGEN = eINSTANCE.getBenutzer_Buchungen();

		/**
		 * The meta object literal for the '{@link kinoVerwaltung.impl.ReservierungImpl <em>Reservierung</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kinoVerwaltung.impl.ReservierungImpl
		 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getReservierung()
		 * @generated
		 */
		EClass RESERVIERUNG = eINSTANCE.getReservierung();

		/**
		 * The meta object literal for the '<em><b>Reservierungsnummer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESERVIERUNG__RESERVIERUNGSNUMMER = eINSTANCE.getReservierung_Reservierungsnummer();

		/**
		 * The meta object literal for the '<em><b>Datum</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESERVIERUNG__DATUM = eINSTANCE.getReservierung_Datum();

		/**
		 * The meta object literal for the '<em><b>Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESERVIERUNG__STATUS = eINSTANCE.getReservierung_Status();

		/**
		 * The meta object literal for the '<em><b>Sitzplaetze</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESERVIERUNG__SITZPLAETZE = eINSTANCE.getReservierung_Sitzplaetze();

		/**
		 * The meta object literal for the '<em><b>Buchung</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESERVIERUNG__BUCHUNG = eINSTANCE.getReservierung_Buchung();

		/**
		 * The meta object literal for the '<em><b>Vorstellung</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESERVIERUNG__VORSTELLUNG = eINSTANCE.getReservierung_Vorstellung();

		/**
		 * The meta object literal for the '<em><b>Wandel Reservierung In Buchung</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation RESERVIERUNG___WANDEL_RESERVIERUNG_IN_BUCHUNG = eINSTANCE
				.getReservierung__WandelReservierungInBuchung();

		/**
		 * The meta object literal for the '<em><b>Storniere Reservierung</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation RESERVIERUNG___STORNIERE_RESERVIERUNG = eINSTANCE.getReservierung__StorniereReservierung();

		/**
		 * The meta object literal for the '{@link kinoVerwaltung.impl.SitzreiheImpl <em>Sitzreihe</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kinoVerwaltung.impl.SitzreiheImpl
		 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getSitzreihe()
		 * @generated
		 */
		EClass SITZREIHE = eINSTANCE.getSitzreihe();

		/**
		 * The meta object literal for the '<em><b>Nummer</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITZREIHE__NUMMER = eINSTANCE.getSitzreihe_Nummer();

		/**
		 * The meta object literal for the '<em><b>Anzahl Plaetze</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SITZREIHE__ANZAHL_PLAETZE = eINSTANCE.getSitzreihe_AnzahlPlaetze();

		/**
		 * The meta object literal for the '<em><b>Sitzplaetze</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITZREIHE__SITZPLAETZE = eINSTANCE.getSitzreihe_Sitzplaetze();

		/**
		 * The meta object literal for the '{@link kinoVerwaltung.Sitzkategorie <em>Sitzkategorie</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kinoVerwaltung.Sitzkategorie
		 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getSitzkategorie()
		 * @generated
		 */
		EEnum SITZKATEGORIE = eINSTANCE.getSitzkategorie();

		/**
		 * The meta object literal for the '{@link kinoVerwaltung.Status <em>Status</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kinoVerwaltung.Status
		 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getStatus()
		 * @generated
		 */
		EEnum STATUS = eINSTANCE.getStatus();

		/**
		 * The meta object literal for the '{@link kinoVerwaltung.Sitzstatus <em>Sitzstatus</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see kinoVerwaltung.Sitzstatus
		 * @see kinoVerwaltung.impl.KinoVerwaltungPackageImpl#getSitzstatus()
		 * @generated
		 */
		EEnum SITZSTATUS = eINSTANCE.getSitzstatus();

	}

} //KinoVerwaltungPackage
