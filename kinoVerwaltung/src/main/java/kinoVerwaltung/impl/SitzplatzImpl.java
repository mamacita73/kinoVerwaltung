/**
 */
package kinoVerwaltung.impl;

import java.lang.reflect.InvocationTargetException;

import kinoVerwaltung.KinoVerwaltungPackage;
import kinoVerwaltung.Sitzkategorie;
import kinoVerwaltung.Sitzplatz;
import kinoVerwaltung.Sitzreihe;
import kinoVerwaltung.Sitzstatus;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sitzplatz</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.impl.SitzplatzImpl#getReihe <em>Reihe</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.SitzplatzImpl#getNummer <em>Nummer</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.SitzplatzImpl#getSitzkategorie <em>Sitzkategorie</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.SitzplatzImpl#getSitzreihe <em>Sitzreihe</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.SitzplatzImpl#getSitzstatus <em>Sitzstatus</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SitzplatzImpl extends MinimalEObjectImpl.Container implements Sitzplatz {
	/**
	 * The default value of the '{@link #getReihe() <em>Reihe</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReihe()
	 * @generated
	 * @ordered
	 */
	protected static final int REIHE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getReihe() <em>Reihe</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReihe()
	 * @generated
	 * @ordered
	 */
	protected int reihe = REIHE_EDEFAULT;

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
	 * The default value of the '{@link #getSitzkategorie() <em>Sitzkategorie</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSitzkategorie()
	 * @generated
	 * @ordered
	 */
	protected static final Sitzkategorie SITZKATEGORIE_EDEFAULT = Sitzkategorie.PARKETT;

	/**
	 * The cached value of the '{@link #getSitzkategorie() <em>Sitzkategorie</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSitzkategorie()
	 * @generated
	 * @ordered
	 */
	protected Sitzkategorie sitzkategorie = SITZKATEGORIE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getSitzreihe() <em>Sitzreihe</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSitzreihe()
	 * @generated
	 * @ordered
	 */
	protected Sitzreihe sitzreihe;

	/**
	 * The default value of the '{@link #getSitzstatus() <em>Sitzstatus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSitzstatus()
	 * @generated
	 * @ordered
	 */
	protected static final Sitzstatus SITZSTATUS_EDEFAULT = Sitzstatus.FREI;

	/**
	 * The cached value of the '{@link #getSitzstatus() <em>Sitzstatus</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSitzstatus()
	 * @generated
	 * @ordered
	 */
	protected Sitzstatus sitzstatus = SITZSTATUS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SitzplatzImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KinoVerwaltungPackage.Literals.SITZPLATZ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getReihe() {
		return reihe;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReihe(int newReihe) {
		int oldReihe = reihe;
		reihe = newReihe;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.SITZPLATZ__REIHE, oldReihe,
					reihe));
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
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.SITZPLATZ__NUMMER, oldNummer,
					nummer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Sitzkategorie getSitzkategorie() {
		return sitzkategorie;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSitzkategorie(Sitzkategorie newSitzkategorie) {
		Sitzkategorie oldSitzkategorie = sitzkategorie;
		sitzkategorie = newSitzkategorie == null ? SITZKATEGORIE_EDEFAULT : newSitzkategorie;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.SITZPLATZ__SITZKATEGORIE,
					oldSitzkategorie, sitzkategorie));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Sitzreihe getSitzreihe() {
		if (sitzreihe != null && sitzreihe.eIsProxy()) {
			InternalEObject oldSitzreihe = (InternalEObject) sitzreihe;
			sitzreihe = (Sitzreihe) eResolveProxy(oldSitzreihe);
			if (sitzreihe != oldSitzreihe) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							KinoVerwaltungPackage.SITZPLATZ__SITZREIHE, oldSitzreihe, sitzreihe));
			}
		}
		return sitzreihe;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Sitzreihe basicGetSitzreihe() {
		return sitzreihe;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSitzreihe(Sitzreihe newSitzreihe) {
		Sitzreihe oldSitzreihe = sitzreihe;
		sitzreihe = newSitzreihe;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.SITZPLATZ__SITZREIHE,
					oldSitzreihe, sitzreihe));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Sitzstatus getSitzstatus() {
		return sitzstatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSitzstatus(Sitzstatus newSitzstatus) {
		Sitzstatus oldSitzstatus = sitzstatus;
		sitzstatus = newSitzstatus == null ? SITZSTATUS_EDEFAULT : newSitzstatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.SITZPLATZ__SITZSTATUS,
					oldSitzstatus, sitzstatus));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean istVerfügbar() {
        return this.getSitzstatus() == Sitzstatus.FREI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void reservieren() {
		setSitzstatus(Sitzstatus.RESERVIERT);
		//Hier kann eine Art Timer implementiert werden, der nach einer gewissen Zeit den Sitzplatz wieder freigibt
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void buchen() {
		setSitzstatus(Sitzstatus.GEBUCHT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case KinoVerwaltungPackage.SITZPLATZ__REIHE:
			return getReihe();
		case KinoVerwaltungPackage.SITZPLATZ__NUMMER:
			return getNummer();
		case KinoVerwaltungPackage.SITZPLATZ__SITZKATEGORIE:
			return getSitzkategorie();
		case KinoVerwaltungPackage.SITZPLATZ__SITZREIHE:
			if (resolve)
				return getSitzreihe();
			return basicGetSitzreihe();
		case KinoVerwaltungPackage.SITZPLATZ__SITZSTATUS:
			return getSitzstatus();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case KinoVerwaltungPackage.SITZPLATZ__REIHE:
			setReihe((Integer) newValue);
			return;
		case KinoVerwaltungPackage.SITZPLATZ__NUMMER:
			setNummer((Integer) newValue);
			return;
		case KinoVerwaltungPackage.SITZPLATZ__SITZKATEGORIE:
			setSitzkategorie((Sitzkategorie) newValue);
			return;
		case KinoVerwaltungPackage.SITZPLATZ__SITZREIHE:
			setSitzreihe((Sitzreihe) newValue);
			return;
		case KinoVerwaltungPackage.SITZPLATZ__SITZSTATUS:
			setSitzstatus((Sitzstatus) newValue);
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
		case KinoVerwaltungPackage.SITZPLATZ__REIHE:
			setReihe(REIHE_EDEFAULT);
			return;
		case KinoVerwaltungPackage.SITZPLATZ__NUMMER:
			setNummer(NUMMER_EDEFAULT);
			return;
		case KinoVerwaltungPackage.SITZPLATZ__SITZKATEGORIE:
			setSitzkategorie(SITZKATEGORIE_EDEFAULT);
			return;
		case KinoVerwaltungPackage.SITZPLATZ__SITZREIHE:
			setSitzreihe((Sitzreihe) null);
			return;
		case KinoVerwaltungPackage.SITZPLATZ__SITZSTATUS:
			setSitzstatus(SITZSTATUS_EDEFAULT);
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
		case KinoVerwaltungPackage.SITZPLATZ__REIHE:
			return reihe != REIHE_EDEFAULT;
		case KinoVerwaltungPackage.SITZPLATZ__NUMMER:
			return nummer != NUMMER_EDEFAULT;
		case KinoVerwaltungPackage.SITZPLATZ__SITZKATEGORIE:
			return sitzkategorie != SITZKATEGORIE_EDEFAULT;
		case KinoVerwaltungPackage.SITZPLATZ__SITZREIHE:
			return sitzreihe != null;
		case KinoVerwaltungPackage.SITZPLATZ__SITZSTATUS:
			return sitzstatus != SITZSTATUS_EDEFAULT;
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
		case KinoVerwaltungPackage.SITZPLATZ___IST_VERFÜGBAR:
			return istVerfügbar();
		case KinoVerwaltungPackage.SITZPLATZ___RESERVIEREN:
			reservieren();
			return null;
		case KinoVerwaltungPackage.SITZPLATZ___BUCHEN:
			buchen();
			return null;
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
		result.append(" (reihe: ");
		result.append(reihe);
		result.append(", nummer: ");
		result.append(nummer);
		result.append(", sitzkategorie: ");
		result.append(sitzkategorie);
		result.append(", sitzstatus: ");
		result.append(sitzstatus);
		result.append(')');
		return result.toString();
	}

} //SitzplatzImpl
