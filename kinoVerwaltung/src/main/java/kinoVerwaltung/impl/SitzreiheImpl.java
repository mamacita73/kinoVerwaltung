/**
 */
package kinoVerwaltung.impl;

import java.util.ArrayList;
import java.util.Collection;

import kinoVerwaltung.KinoVerwaltungPackage;
import kinoVerwaltung.Sitzplatz;
import kinoVerwaltung.Sitzreihe;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sitzreihe</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.impl.SitzreiheImpl#getNummer <em>Nummer</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.SitzreiheImpl#getAnzahlPlaetze <em>Anzahl Plaetze</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.SitzreiheImpl#getSitzplaetze <em>Sitzplaetze</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SitzreiheImpl extends MinimalEObjectImpl.Container implements Sitzreihe {
	/**
	 * The default value of the '{@link #getNummer() <em>Nummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNummer()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMMER_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNummer() <em>Nummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNummer()
	 * @generated
	 * @ordered
	 */
	protected int nummer = NUMMER_EDEFAULT;

	/**
	 * The default value of the '{@link #getAnzahlPlaetze() <em>Anzahl Plaetze</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnzahlPlaetze()
	 * @generated
	 * @ordered
	 */
	protected static final int ANZAHL_PLAETZE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAnzahlPlaetze() <em>Anzahl Plaetze</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnzahlPlaetze()
	 * @generated
	 * @ordered
	 */
	protected int anzahlPlaetze = ANZAHL_PLAETZE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSitzplaetze() <em>Sitzplaetze</em>}' containment reference list.
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
    public SitzreiheImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KinoVerwaltungPackage.Literals.SITZREIHE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getNummer() {
		return nummer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setNummer(int newNummer) {
		int oldNummer = nummer;
		nummer = newNummer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.SITZREIHE__NUMMER, oldNummer,
					nummer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getAnzahlPlaetze() {
		return anzahlPlaetze;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAnzahlPlaetze(int newAnzahlPlaetze) {
		int oldAnzahlPlaetze = anzahlPlaetze;
		anzahlPlaetze = newAnzahlPlaetze;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.SITZREIHE__ANZAHL_PLAETZE,
					oldAnzahlPlaetze, anzahlPlaetze));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Sitzplatz> getSitzplaetze() {
		if (sitzplaetze == null) {
			sitzplaetze = new EObjectContainmentEList<Sitzplatz>(Sitzplatz.class, this,
					KinoVerwaltungPackage.SITZREIHE__SITZPLAETZE);
		}
		return sitzplaetze;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case KinoVerwaltungPackage.SITZREIHE__SITZPLAETZE:
			return ((InternalEList<?>) getSitzplaetze()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case KinoVerwaltungPackage.SITZREIHE__NUMMER:
			return getNummer();
		case KinoVerwaltungPackage.SITZREIHE__ANZAHL_PLAETZE:
			return getAnzahlPlaetze();
		case KinoVerwaltungPackage.SITZREIHE__SITZPLAETZE:
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
		case KinoVerwaltungPackage.SITZREIHE__NUMMER:
			setNummer((Integer) newValue);
			return;
		case KinoVerwaltungPackage.SITZREIHE__ANZAHL_PLAETZE:
			setAnzahlPlaetze((Integer) newValue);
			return;
		case KinoVerwaltungPackage.SITZREIHE__SITZPLAETZE:
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
		case KinoVerwaltungPackage.SITZREIHE__NUMMER:
			setNummer(NUMMER_EDEFAULT);
			return;
		case KinoVerwaltungPackage.SITZREIHE__ANZAHL_PLAETZE:
			setAnzahlPlaetze(ANZAHL_PLAETZE_EDEFAULT);
			return;
		case KinoVerwaltungPackage.SITZREIHE__SITZPLAETZE:
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
		case KinoVerwaltungPackage.SITZREIHE__NUMMER:
			return nummer != NUMMER_EDEFAULT;
		case KinoVerwaltungPackage.SITZREIHE__ANZAHL_PLAETZE:
			return anzahlPlaetze != ANZAHL_PLAETZE_EDEFAULT;
		case KinoVerwaltungPackage.SITZREIHE__SITZPLAETZE:
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
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (nummer: ");
		result.append(nummer);
		result.append(", anzahlPlaetze: ");
		result.append(anzahlPlaetze);
		result.append(')');
		return result.toString();
	}


} //SitzreiheImpl
