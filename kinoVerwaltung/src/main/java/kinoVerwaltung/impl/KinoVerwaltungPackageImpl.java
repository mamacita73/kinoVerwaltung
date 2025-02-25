/**
 */
package kinoVerwaltung.impl;

import kinoVerwaltung.Benutzer;
import kinoVerwaltung.Buchung;
import kinoVerwaltung.Kino;
import kinoVerwaltung.KinoVerwaltungFactory;
import kinoVerwaltung.KinoVerwaltungPackage;
import kinoVerwaltung.Reservierung;
import kinoVerwaltung.Saal;
import kinoVerwaltung.Sitzkategorie;
import kinoVerwaltung.Sitzplatz;
import kinoVerwaltung.Sitzreihe;
import kinoVerwaltung.Sitzstatus;
import kinoVerwaltung.Status;
import kinoVerwaltung.Vorstellung;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KinoVerwaltungPackageImpl extends EPackageImpl implements KinoVerwaltungPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass kinoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass saalEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sitzplatzEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass vorstellungEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass buchungEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass benutzerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass reservierungEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sitzreiheEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum sitzkategorieEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum statusEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum sitzstatusEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see kinoVerwaltung.KinoVerwaltungPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private KinoVerwaltungPackageImpl() {
		super(eNS_URI, KinoVerwaltungFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link KinoVerwaltungPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static KinoVerwaltungPackage init() {
		if (isInited)
			return (KinoVerwaltungPackage) EPackage.Registry.INSTANCE.getEPackage(KinoVerwaltungPackage.eNS_URI);

		// Obtain or create and register package
		Object registeredKinoVerwaltungPackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		KinoVerwaltungPackageImpl theKinoVerwaltungPackage = registeredKinoVerwaltungPackage instanceof KinoVerwaltungPackageImpl
				? (KinoVerwaltungPackageImpl) registeredKinoVerwaltungPackage
				: new KinoVerwaltungPackageImpl();

		isInited = true;

		// Create package meta-data objects
		theKinoVerwaltungPackage.createPackageContents();

		// Initialize created meta-data
		theKinoVerwaltungPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theKinoVerwaltungPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(KinoVerwaltungPackage.eNS_URI, theKinoVerwaltungPackage);
		return theKinoVerwaltungPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getKino() {
		return kinoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKino_Name() {
		return (EAttribute) kinoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getKino_Adresse() {
		return (EAttribute) kinoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getKino_Saele() {
		return (EReference) kinoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSaal() {
		return saalEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSaal_Name() {
		return (EAttribute) saalEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSaal_AnzahlReihen() {
		return (EAttribute) saalEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSaal_Vorstellungen() {
		return (EReference) saalEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSaal_Sitzreihen() {
		return (EReference) saalEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSaal_IstFreigegeben() {
		return (EAttribute) saalEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getSaal__Freigeben() {
		return saalEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSitzplatz() {
		return sitzplatzEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSitzplatz_Reihe() {
		return (EAttribute) sitzplatzEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSitzplatz_Nummer() {
		return (EAttribute) sitzplatzEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSitzplatz_Sitzkategorie() {
		return (EAttribute) sitzplatzEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSitzplatz_Sitzreihe() {
		return (EReference) sitzplatzEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSitzplatz_Sitzstatus() {
		return (EAttribute) sitzplatzEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getSitzplatz__IstVerfügbar() {
		return sitzplatzEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getSitzplatz__Reservieren() {
		return sitzplatzEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getSitzplatz__Buchen() {
		return sitzplatzEClass.getEOperations().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getVorstellung() {
		return vorstellungEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVorstellung_FilmTitle() {
		return (EAttribute) vorstellungEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVorstellung_Startzeit() {
		return (EAttribute) vorstellungEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getVorstellung_DauerMinuten() {
		return (EAttribute) vorstellungEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getVorstellung_Buchungen() {
		return (EReference) vorstellungEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getVorstellung__BerechneEinnahmen() {
		return vorstellungEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getVorstellung__GetFreiePlätze() {
		return vorstellungEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBuchung() {
		return buchungEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBuchung_Buchungsnummer() {
		return (EAttribute) buchungEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBuchung_Datum() {
		return (EAttribute) buchungEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBuchung_Preis() {
		return (EAttribute) buchungEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBuchung_Sitzplaetze() {
		return (EReference) buchungEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getBuchung__BerechnePreis() {
		return buchungEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getBuchung__GetBuchungsdetails() {
		return buchungEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getBenutzer() {
		return benutzerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBenutzer_Benutzername() {
		return (EAttribute) benutzerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBenutzer_Email() {
		return (EAttribute) benutzerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBenutzer_Passwort() {
		return (EAttribute) benutzerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getBenutzer_Rolle() {
		return (EAttribute) benutzerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBenutzer_Reservierungen() {
		return (EReference) benutzerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getBenutzer_Buchungen() {
		return (EReference) benutzerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getReservierung() {
		return reservierungEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReservierung_Reservierungsnummer() {
		return (EAttribute) reservierungEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReservierung_Datum() {
		return (EAttribute) reservierungEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getReservierung_Status() {
		return (EAttribute) reservierungEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReservierung_Sitzplaetze() {
		return (EReference) reservierungEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReservierung_Buchung() {
		return (EReference) reservierungEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getReservierung_Vorstellung() {
		return (EReference) reservierungEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getReservierung__WandelReservierungInBuchung() {
		return reservierungEClass.getEOperations().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EOperation getReservierung__StorniereReservierung() {
		return reservierungEClass.getEOperations().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EClass getSitzreihe() {
		return sitzreiheEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSitzreihe_Nummer() {
		return (EAttribute) sitzreiheEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EAttribute getSitzreihe_AnzahlPlaetze() {
		return (EAttribute) sitzreiheEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EReference getSitzreihe_Sitzplaetze() {
		return (EReference) sitzreiheEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getSitzkategorie() {
		return sitzkategorieEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getStatus() {
		return statusEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EEnum getSitzstatus() {
		return sitzstatusEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public KinoVerwaltungFactory getKinoVerwaltungFactory() {
		return (KinoVerwaltungFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		kinoEClass = createEClass(KINO);
		createEAttribute(kinoEClass, KINO__NAME);
		createEAttribute(kinoEClass, KINO__ADRESSE);
		createEReference(kinoEClass, KINO__SAELE);

		saalEClass = createEClass(SAAL);
		createEAttribute(saalEClass, SAAL__NAME);
		createEAttribute(saalEClass, SAAL__ANZAHL_REIHEN);
		createEReference(saalEClass, SAAL__VORSTELLUNGEN);
		createEReference(saalEClass, SAAL__SITZREIHEN);
		createEAttribute(saalEClass, SAAL__IST_FREIGEGEBEN);
		createEOperation(saalEClass, SAAL___FREIGEBEN);

		sitzplatzEClass = createEClass(SITZPLATZ);
		createEAttribute(sitzplatzEClass, SITZPLATZ__REIHE);
		createEAttribute(sitzplatzEClass, SITZPLATZ__NUMMER);
		createEAttribute(sitzplatzEClass, SITZPLATZ__SITZKATEGORIE);
		createEReference(sitzplatzEClass, SITZPLATZ__SITZREIHE);
		createEAttribute(sitzplatzEClass, SITZPLATZ__SITZSTATUS);
		createEOperation(sitzplatzEClass, SITZPLATZ___IST_VERFÜGBAR);
		createEOperation(sitzplatzEClass, SITZPLATZ___RESERVIEREN);
		createEOperation(sitzplatzEClass, SITZPLATZ___BUCHEN);

		vorstellungEClass = createEClass(VORSTELLUNG);
		createEAttribute(vorstellungEClass, VORSTELLUNG__FILM_TITLE);
		createEAttribute(vorstellungEClass, VORSTELLUNG__STARTZEIT);
		createEAttribute(vorstellungEClass, VORSTELLUNG__DAUER_MINUTEN);
		createEReference(vorstellungEClass, VORSTELLUNG__BUCHUNGEN);
		createEOperation(vorstellungEClass, VORSTELLUNG___BERECHNE_EINNAHMEN);
		createEOperation(vorstellungEClass, VORSTELLUNG___GET_FREIE_PLÄTZE);

		buchungEClass = createEClass(BUCHUNG);
		createEAttribute(buchungEClass, BUCHUNG__BUCHUNGSNUMMER);
		createEAttribute(buchungEClass, BUCHUNG__DATUM);
		createEAttribute(buchungEClass, BUCHUNG__PREIS);
		createEReference(buchungEClass, BUCHUNG__SITZPLAETZE);
		createEOperation(buchungEClass, BUCHUNG___BERECHNE_PREIS);
		createEOperation(buchungEClass, BUCHUNG___GET_BUCHUNGSDETAILS);

		benutzerEClass = createEClass(BENUTZER);
		createEAttribute(benutzerEClass, BENUTZER__BENUTZERNAME);
		createEAttribute(benutzerEClass, BENUTZER__EMAIL);
		createEAttribute(benutzerEClass, BENUTZER__PASSWORT);
		createEAttribute(benutzerEClass, BENUTZER__ROLLE);
		createEReference(benutzerEClass, BENUTZER__RESERVIERUNGEN);
		createEReference(benutzerEClass, BENUTZER__BUCHUNGEN);

		reservierungEClass = createEClass(RESERVIERUNG);
		createEAttribute(reservierungEClass, RESERVIERUNG__RESERVIERUNGSNUMMER);
		createEAttribute(reservierungEClass, RESERVIERUNG__DATUM);
		createEAttribute(reservierungEClass, RESERVIERUNG__STATUS);
		createEReference(reservierungEClass, RESERVIERUNG__SITZPLAETZE);
		createEReference(reservierungEClass, RESERVIERUNG__BUCHUNG);
		createEReference(reservierungEClass, RESERVIERUNG__VORSTELLUNG);
		createEOperation(reservierungEClass, RESERVIERUNG___WANDEL_RESERVIERUNG_IN_BUCHUNG);
		createEOperation(reservierungEClass, RESERVIERUNG___STORNIERE_RESERVIERUNG);

		sitzreiheEClass = createEClass(SITZREIHE);
		createEAttribute(sitzreiheEClass, SITZREIHE__NUMMER);
		createEAttribute(sitzreiheEClass, SITZREIHE__ANZAHL_PLAETZE);
		createEReference(sitzreiheEClass, SITZREIHE__SITZPLAETZE);

		// Create enums
		sitzkategorieEEnum = createEEnum(SITZKATEGORIE);
		statusEEnum = createEEnum(STATUS);
		sitzstatusEEnum = createEEnum(SITZSTATUS);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes, features, and operations; add parameters
		initEClass(kinoEClass, Kino.class, "Kino", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getKino_Name(), ecorePackage.getEString(), "name", null, 0, 1, Kino.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getKino_Adresse(), ecorePackage.getEString(), "adresse", null, 0, 1, Kino.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getKino_Saele(), this.getSaal(), null, "saele", null, 0, -1, Kino.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(saalEClass, Saal.class, "Saal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSaal_Name(), ecorePackage.getEString(), "name", null, 0, 1, Saal.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSaal_AnzahlReihen(), ecorePackage.getEInt(), "anzahlReihen", null, 0, 1, Saal.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSaal_Vorstellungen(), this.getVorstellung(), null, "vorstellungen", null, 0, -1, Saal.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSaal_Sitzreihen(), this.getSitzreihe(), null, "sitzreihen", null, 1, -1, Saal.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSaal_IstFreigegeben(), ecorePackage.getEBoolean(), "istFreigegeben", null, 0, 1, Saal.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getSaal__Freigeben(), null, "freigeben", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(sitzplatzEClass, Sitzplatz.class, "Sitzplatz", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSitzplatz_Reihe(), ecorePackage.getEInt(), "reihe", null, 0, 1, Sitzplatz.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSitzplatz_Nummer(), ecorePackage.getEInt(), "nummer", null, 0, 1, Sitzplatz.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSitzplatz_Sitzkategorie(), this.getSitzkategorie(), "sitzkategorie", null, 0, 1,
				Sitzplatz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getSitzplatz_Sitzreihe(), this.getSitzreihe(), null, "sitzreihe", null, 1, 1, Sitzplatz.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSitzplatz_Sitzstatus(), this.getSitzstatus(), "sitzstatus", "FREI", 0, 1, Sitzplatz.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getSitzplatz__IstVerfügbar(), ecorePackage.getEBoolean(), "istVerfügbar", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		initEOperation(getSitzplatz__Reservieren(), null, "reservieren", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getSitzplatz__Buchen(), null, "buchen", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(vorstellungEClass, Vorstellung.class, "Vorstellung", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVorstellung_FilmTitle(), ecorePackage.getEString(), "filmTitle", null, 0, 1,
				Vorstellung.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getVorstellung_Startzeit(), ecorePackage.getEDate(), "startzeit", null, 0, 1, Vorstellung.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVorstellung_DauerMinuten(), ecorePackage.getEInt(), "dauerMinuten", null, 0, 1,
				Vorstellung.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getVorstellung_Buchungen(), this.getBuchung(), null, "buchungen", null, 0, -1, Vorstellung.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getVorstellung__BerechneEinnahmen(), ecorePackage.getEDouble(), "berechneEinnahmen", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		initEOperation(getVorstellung__GetFreiePlätze(), null, "getFreiePlätze", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(buchungEClass, Buchung.class, "Buchung", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBuchung_Buchungsnummer(), ecorePackage.getEString(), "buchungsnummer", null, 0, 1,
				Buchung.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuchung_Datum(), ecorePackage.getEDate(), "datum", null, 0, 1, Buchung.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBuchung_Preis(), ecorePackage.getEDouble(), "preis", null, 0, 1, Buchung.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBuchung_Sitzplaetze(), this.getSitzplatz(), null, "sitzplaetze", null, 0, -1, Buchung.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getBuchung__BerechnePreis(), ecorePackage.getEDouble(), "berechnePreis", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		initEOperation(getBuchung__GetBuchungsdetails(), ecorePackage.getEString(), "getBuchungsdetails", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		initEClass(benutzerEClass, Benutzer.class, "Benutzer", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBenutzer_Benutzername(), ecorePackage.getEString(), "benutzername", null, 0, 1,
				Benutzer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenutzer_Email(), ecorePackage.getEString(), "email", null, 0, 1, Benutzer.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenutzer_Passwort(), ecorePackage.getEString(), "passwort", null, 0, 1, Benutzer.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBenutzer_Rolle(), ecorePackage.getEString(), "rolle", null, 0, 1, Benutzer.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBenutzer_Reservierungen(), this.getReservierung(), null, "reservierungen", null, 0, -1,
				Benutzer.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getBenutzer_Buchungen(), this.getBuchung(), null, "buchungen", null, 0, -1, Benutzer.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(reservierungEClass, Reservierung.class, "Reservierung", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getReservierung_Reservierungsnummer(), ecorePackage.getEString(), "reservierungsnummer", null, 0,
				1, Reservierung.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getReservierung_Datum(), ecorePackage.getEDate(), "datum", null, 0, 1, Reservierung.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getReservierung_Status(), this.getStatus(), "status", "OFFEN", 0, 1, Reservierung.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReservierung_Sitzplaetze(), this.getSitzplatz(), null, "sitzplaetze", null, 0, -1,
				Reservierung.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReservierung_Buchung(), this.getBuchung(), null, "buchung", null, 0, 1, Reservierung.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getReservierung_Vorstellung(), this.getVorstellung(), null, "vorstellung", null, 1, 1,
				Reservierung.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEOperation(getReservierung__WandelReservierungInBuchung(), this.getBuchung(), "wandelReservierungInBuchung",
				0, 1, IS_UNIQUE, IS_ORDERED);

		initEOperation(getReservierung__StorniereReservierung(), null, "storniereReservierung", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		initEClass(sitzreiheEClass, Sitzreihe.class, "Sitzreihe", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSitzreihe_Nummer(), ecorePackage.getEInt(), "nummer", null, 0, 1, Sitzreihe.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSitzreihe_AnzahlPlaetze(), ecorePackage.getEInt(), "anzahlPlaetze", null, 0, 1,
				Sitzreihe.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getSitzreihe_Sitzplaetze(), this.getSitzplatz(), null, "sitzplaetze", null, 1, -1,
				Sitzreihe.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(sitzkategorieEEnum, Sitzkategorie.class, "Sitzkategorie");
		addEEnumLiteral(sitzkategorieEEnum, Sitzkategorie.PARKETT);
		addEEnumLiteral(sitzkategorieEEnum, Sitzkategorie.LOGE);
		addEEnumLiteral(sitzkategorieEEnum, Sitzkategorie.LOGE_SERVICE);

		initEEnum(statusEEnum, Status.class, "Status");
		addEEnumLiteral(statusEEnum, Status.OFFEN);
		addEEnumLiteral(statusEEnum, Status.GEBUCHT);
		addEEnumLiteral(statusEEnum, Status.STORNIERT);

		initEEnum(sitzstatusEEnum, Sitzstatus.class, "Sitzstatus");
		addEEnumLiteral(sitzstatusEEnum, Sitzstatus.FREI);
		addEEnumLiteral(sitzstatusEEnum, Sitzstatus.RESERVIERT);
		addEEnumLiteral(sitzstatusEEnum, Sitzstatus.GEBUCHT);

		// Create resource
		createResource(eNS_URI);
	}

} //KinoVerwaltungPackageImpl
