/**
 */
package kinoVerwaltung.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;
import java.util.Date;

import kinoVerwaltung.Buchung;
import kinoVerwaltung.KinoVerwaltungPackage;
import kinoVerwaltung.Sitzplatz;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Buchung</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.impl.BuchungImpl#getBuchungsnummer <em>Buchungsnummer</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.BuchungImpl#getDatum <em>Datum</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.BuchungImpl#getPreis <em>Preis</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.BuchungImpl#getSitzplaetze <em>Sitzplaetze</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BuchungImpl extends MinimalEObjectImpl.Container implements Buchung {
	/**
	 * The default value of the '{@link #getBuchungsnummer() <em>Buchungsnummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuchungsnummer()
	 * @generated
	 * @ordered
	 */
	protected static final String BUCHUNGSNUMMER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBuchungsnummer() <em>Buchungsnummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuchungsnummer()
	 * @generated
	 * @ordered
	 */
	protected String buchungsnummer = BUCHUNGSNUMMER_EDEFAULT;

	/**
	 * The default value of the '{@link #getDatum() <em>Datum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatum()
	 * @generated
	 * @ordered
	 */
	protected static final Date DATUM_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDatum() <em>Datum</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDatum()
	 * @generated
	 * @ordered
	 */
	protected Date datum = DATUM_EDEFAULT;

	/**
	 * The default value of the '{@link #getPreis() <em>Preis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreis()
	 * @generated
	 * @ordered
	 */
	protected static final double PREIS_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getPreis() <em>Preis</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPreis()
	 * @generated
	 * @ordered
	 */
	protected double preis = PREIS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSitzplaetze() <em>Sitzplaetze</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSitzplaetze()
	 * @generated
	 * @ordered
	 */
	protected EList<Sitzplatz> sitzplaetze;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BuchungImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KinoVerwaltungPackage.Literals.BUCHUNG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBuchungsnummer() {
		return buchungsnummer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBuchungsnummer(String newBuchungsnummer) {
		String oldBuchungsnummer = buchungsnummer;
		buchungsnummer = newBuchungsnummer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.BUCHUNG__BUCHUNGSNUMMER,
					oldBuchungsnummer, buchungsnummer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Date getDatum() {
		return datum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDatum(Date newDatum) {
		Date oldDatum = datum;
		datum = newDatum;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.BUCHUNG__DATUM, oldDatum,
					datum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double getPreis() {
		return preis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPreis(double newPreis) {
		double oldPreis = preis;
		preis = newPreis;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.BUCHUNG__PREIS, oldPreis,
					preis));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Sitzplatz> getSitzplaetze() {
		if (sitzplaetze == null) {
			sitzplaetze = new EObjectResolvingEList<Sitzplatz>(Sitzplatz.class, this,
					KinoVerwaltungPackage.BUCHUNG__SITZPLAETZE);
		}
		return sitzplaetze;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public double berechnePreis() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBuchungsdetails() {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case KinoVerwaltungPackage.BUCHUNG__BUCHUNGSNUMMER:
			return getBuchungsnummer();
		case KinoVerwaltungPackage.BUCHUNG__DATUM:
			return getDatum();
		case KinoVerwaltungPackage.BUCHUNG__PREIS:
			return getPreis();
		case KinoVerwaltungPackage.BUCHUNG__SITZPLAETZE:
			return getSitzplaetze();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case KinoVerwaltungPackage.BUCHUNG__BUCHUNGSNUMMER:
			setBuchungsnummer((String) newValue);
			return;
		case KinoVerwaltungPackage.BUCHUNG__DATUM:
			setDatum((Date) newValue);
			return;
		case KinoVerwaltungPackage.BUCHUNG__PREIS:
			setPreis((Double) newValue);
			return;
		case KinoVerwaltungPackage.BUCHUNG__SITZPLAETZE:
			getSitzplaetze().clear();
			getSitzplaetze().addAll((Collection<? extends Sitzplatz>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case KinoVerwaltungPackage.BUCHUNG__BUCHUNGSNUMMER:
			setBuchungsnummer(BUCHUNGSNUMMER_EDEFAULT);
			return;
		case KinoVerwaltungPackage.BUCHUNG__DATUM:
			setDatum(DATUM_EDEFAULT);
			return;
		case KinoVerwaltungPackage.BUCHUNG__PREIS:
			setPreis(PREIS_EDEFAULT);
			return;
		case KinoVerwaltungPackage.BUCHUNG__SITZPLAETZE:
			getSitzplaetze().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case KinoVerwaltungPackage.BUCHUNG__BUCHUNGSNUMMER:
			return BUCHUNGSNUMMER_EDEFAULT == null ? buchungsnummer != null
					: !BUCHUNGSNUMMER_EDEFAULT.equals(buchungsnummer);
		case KinoVerwaltungPackage.BUCHUNG__DATUM:
			return DATUM_EDEFAULT == null ? datum != null : !DATUM_EDEFAULT.equals(datum);
		case KinoVerwaltungPackage.BUCHUNG__PREIS:
			return preis != PREIS_EDEFAULT;
		case KinoVerwaltungPackage.BUCHUNG__SITZPLAETZE:
			return sitzplaetze != null && !sitzplaetze.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eInvoke(int operationID, EList<?> arguments) throws InvocationTargetException {
		switch (operationID) {
		case KinoVerwaltungPackage.BUCHUNG___BERECHNE_PREIS:
			return berechnePreis();
		case KinoVerwaltungPackage.BUCHUNG___GET_BUCHUNGSDETAILS:
			return getBuchungsdetails();
		}
		return super.eInvoke(operationID, arguments);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (buchungsnummer: ");
		result.append(buchungsnummer);
		result.append(", datum: ");
		result.append(datum);
		result.append(", preis: ");
		result.append(preis);
		result.append(')');
		return result.toString();
	}

} //BuchungImpl
