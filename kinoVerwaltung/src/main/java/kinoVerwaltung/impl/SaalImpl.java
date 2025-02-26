/**
 */
package kinoVerwaltung.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.ArrayList;
import java.util.Collection;

import kinoVerwaltung.KinoVerwaltungPackage;
import kinoVerwaltung.Saal;
import kinoVerwaltung.Sitzreihe;
import kinoVerwaltung.Vorstellung;

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
 * An implementation of the model object '<em><b>Saal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.impl.SaalImpl#getName <em>Name</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.SaalImpl#getAnzahlReihen <em>Anzahl Reihen</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.SaalImpl#getVorstellungen <em>Vorstellungen</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.SaalImpl#getSitzreihen <em>Sitzreihen</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.SaalImpl#isIstFreigegeben <em>Ist Freigegeben</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SaalImpl extends MinimalEObjectImpl.Container implements Saal {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getAnzahlReihen() <em>Anzahl Reihen</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnzahlReihen()
	 * @generated
	 * @ordered
	 */
	protected static final int ANZAHL_REIHEN_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getAnzahlReihen() <em>Anzahl Reihen</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnzahlReihen()
	 * @generated
	 * @ordered
	 */
	protected int anzahlReihen = ANZAHL_REIHEN_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVorstellungen() <em>Vorstellungen</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVorstellungen()
	 * @generated
	 * @ordered
	 */
	protected EList<Vorstellung> vorstellungen;

	/**
	 * The cached value of the '{@link #getSitzreihen() <em>Sitzreihen</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSitzreihen()
	 * @generated
	 * @ordered
	 */
	protected EList<Sitzreihe> sitzreihen;

	/**
	 * The default value of the '{@link #isIstFreigegeben() <em>Ist Freigegeben</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIstFreigegeben()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IST_FREIGEGEBEN_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIstFreigegeben() <em>Ist Freigegeben</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIstFreigegeben()
	 * @generated
	 * @ordered
	 */
	protected boolean istFreigegeben = IST_FREIGEGEBEN_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
    public SaalImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KinoVerwaltungPackage.Literals.SAAL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.SAAL__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getAnzahlReihen() {
		return anzahlReihen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setAnzahlReihen(int newAnzahlReihen) {
		int oldAnzahlReihen = anzahlReihen;
		anzahlReihen = newAnzahlReihen;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.SAAL__ANZAHL_REIHEN,
					oldAnzahlReihen, anzahlReihen));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Vorstellung> getVorstellungen() {
		if (vorstellungen == null) {
			vorstellungen = new EObjectContainmentEList<Vorstellung>(Vorstellung.class, this,
					KinoVerwaltungPackage.SAAL__VORSTELLUNGEN);
		}
		return vorstellungen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Sitzreihe> getSitzreihen() {
		if (sitzreihen == null) {
			sitzreihen = new EObjectContainmentEList<Sitzreihe>(Sitzreihe.class, this,
					KinoVerwaltungPackage.SAAL__SITZREIHEN);
		}
		return sitzreihen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIstFreigegeben() {
		return istFreigegeben;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIstFreigegeben(boolean newIstFreigegeben) {
		boolean oldIstFreigegeben = istFreigegeben;
		istFreigegeben = newIstFreigegeben;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.SAAL__IST_FREIGEGEBEN,
					oldIstFreigegeben, istFreigegeben));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void freigeben() {
		setIstFreigegeben(true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case KinoVerwaltungPackage.SAAL__VORSTELLUNGEN:
			return ((InternalEList<?>) getVorstellungen()).basicRemove(otherEnd, msgs);
		case KinoVerwaltungPackage.SAAL__SITZREIHEN:
			return ((InternalEList<?>) getSitzreihen()).basicRemove(otherEnd, msgs);
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
		case KinoVerwaltungPackage.SAAL__NAME:
			return getName();
		case KinoVerwaltungPackage.SAAL__ANZAHL_REIHEN:
			return getAnzahlReihen();
		case KinoVerwaltungPackage.SAAL__VORSTELLUNGEN:
			return getVorstellungen();
		case KinoVerwaltungPackage.SAAL__SITZREIHEN:
			return getSitzreihen();
		case KinoVerwaltungPackage.SAAL__IST_FREIGEGEBEN:
			return isIstFreigegeben();
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
		case KinoVerwaltungPackage.SAAL__NAME:
			setName((String) newValue);
			return;
		case KinoVerwaltungPackage.SAAL__ANZAHL_REIHEN:
			setAnzahlReihen((Integer) newValue);
			return;
		case KinoVerwaltungPackage.SAAL__VORSTELLUNGEN:
			getVorstellungen().clear();
			getVorstellungen().addAll((Collection<? extends Vorstellung>) newValue);
			return;
		case KinoVerwaltungPackage.SAAL__SITZREIHEN:
			getSitzreihen().clear();
			getSitzreihen().addAll((Collection<? extends Sitzreihe>) newValue);
			return;
		case KinoVerwaltungPackage.SAAL__IST_FREIGEGEBEN:
			setIstFreigegeben((Boolean) newValue);
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
		case KinoVerwaltungPackage.SAAL__NAME:
			setName(NAME_EDEFAULT);
			return;
		case KinoVerwaltungPackage.SAAL__ANZAHL_REIHEN:
			setAnzahlReihen(ANZAHL_REIHEN_EDEFAULT);
			return;
		case KinoVerwaltungPackage.SAAL__VORSTELLUNGEN:
			getVorstellungen().clear();
			return;
		case KinoVerwaltungPackage.SAAL__SITZREIHEN:
			getSitzreihen().clear();
			return;
		case KinoVerwaltungPackage.SAAL__IST_FREIGEGEBEN:
			setIstFreigegeben(IST_FREIGEGEBEN_EDEFAULT);
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
		case KinoVerwaltungPackage.SAAL__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case KinoVerwaltungPackage.SAAL__ANZAHL_REIHEN:
			return anzahlReihen != ANZAHL_REIHEN_EDEFAULT;
		case KinoVerwaltungPackage.SAAL__VORSTELLUNGEN:
			return vorstellungen != null && !vorstellungen.isEmpty();
		case KinoVerwaltungPackage.SAAL__SITZREIHEN:
			return sitzreihen != null && !sitzreihen.isEmpty();
		case KinoVerwaltungPackage.SAAL__IST_FREIGEGEBEN:
			return istFreigegeben != IST_FREIGEGEBEN_EDEFAULT;
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
		case KinoVerwaltungPackage.SAAL___FREIGEBEN:
			freigeben();
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
		result.append(" (name: ");
		result.append(name);
		result.append(", anzahlReihen: ");
		result.append(anzahlReihen);
		result.append(", istFreigegeben: ");
		result.append(istFreigegeben);
		result.append(')');
		return result.toString();
	}



} //SaalImpl
