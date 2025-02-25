/**
 */
package kinoVerwaltung.impl;

import kinoVerwaltung.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KinoVerwaltungFactoryImpl extends EFactoryImpl implements KinoVerwaltungFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static KinoVerwaltungFactory init() {
		try {
			KinoVerwaltungFactory theKinoVerwaltungFactory = (KinoVerwaltungFactory) EPackage.Registry.INSTANCE
					.getEFactory(KinoVerwaltungPackage.eNS_URI);
			if (theKinoVerwaltungFactory != null) {
				return theKinoVerwaltungFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new KinoVerwaltungFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KinoVerwaltungFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case KinoVerwaltungPackage.KINO:
			return createKino();
		case KinoVerwaltungPackage.SAAL:
			return createSaal();
		case KinoVerwaltungPackage.SITZPLATZ:
			return createSitzplatz();
		case KinoVerwaltungPackage.VORSTELLUNG:
			return createVorstellung();
		case KinoVerwaltungPackage.BUCHUNG:
			return createBuchung();
		case KinoVerwaltungPackage.BENUTZER:
			return createBenutzer();
		case KinoVerwaltungPackage.RESERVIERUNG:
			return createReservierung();
		case KinoVerwaltungPackage.SITZREIHE:
			return createSitzreihe();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case KinoVerwaltungPackage.SITZKATEGORIE:
			return createSitzkategorieFromString(eDataType, initialValue);
		case KinoVerwaltungPackage.STATUS:
			return createStatusFromString(eDataType, initialValue);
		case KinoVerwaltungPackage.SITZSTATUS:
			return createSitzstatusFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case KinoVerwaltungPackage.SITZKATEGORIE:
			return convertSitzkategorieToString(eDataType, instanceValue);
		case KinoVerwaltungPackage.STATUS:
			return convertStatusToString(eDataType, instanceValue);
		case KinoVerwaltungPackage.SITZSTATUS:
			return convertSitzstatusToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Kino createKino() {
		KinoImpl kino = new KinoImpl();
		return kino;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Saal createSaal() {
		SaalImpl saal = new SaalImpl();
		return saal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Sitzplatz createSitzplatz() {
		SitzplatzImpl sitzplatz = new SitzplatzImpl();
		return sitzplatz;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Vorstellung createVorstellung() {
		VorstellungImpl vorstellung = new VorstellungImpl();
		return vorstellung;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Buchung createBuchung() {
		BuchungImpl buchung = new BuchungImpl();
		return buchung;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Benutzer createBenutzer() {
		BenutzerImpl benutzer = new BenutzerImpl();
		return benutzer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Reservierung createReservierung() {
		ReservierungImpl reservierung = new ReservierungImpl();
		return reservierung;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Sitzreihe createSitzreihe() {
		SitzreiheImpl sitzreihe = new SitzreiheImpl();
		return sitzreihe;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sitzkategorie createSitzkategorieFromString(EDataType eDataType, String initialValue) {
		Sitzkategorie result = Sitzkategorie.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSitzkategorieToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Status createStatusFromString(EDataType eDataType, String initialValue) {
		Status result = Status.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertStatusToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sitzstatus createSitzstatusFromString(EDataType eDataType, String initialValue) {
		Sitzstatus result = Sitzstatus.get(initialValue);
		if (result == null)
			throw new IllegalArgumentException(
					"The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertSitzstatusToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public KinoVerwaltungPackage getKinoVerwaltungPackage() {
		return (KinoVerwaltungPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static KinoVerwaltungPackage getPackage() {
		return KinoVerwaltungPackage.eINSTANCE;
	}

} //KinoVerwaltungFactoryImpl
