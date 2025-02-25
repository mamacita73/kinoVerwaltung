/**
 */
package kinoVerwaltung.impl;

import java.util.Collection;

import kinoVerwaltung.Benutzer;
import kinoVerwaltung.Buchung;
import kinoVerwaltung.KinoVerwaltungPackage;
import kinoVerwaltung.Reservierung;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Benutzer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.impl.BenutzerImpl#getBenutzername <em>Benutzername</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.BenutzerImpl#getEmail <em>Email</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.BenutzerImpl#getPasswort <em>Passwort</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.BenutzerImpl#getRolle <em>Rolle</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.BenutzerImpl#getReservierungen <em>Reservierungen</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.BenutzerImpl#getBuchungen <em>Buchungen</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BenutzerImpl extends MinimalEObjectImpl.Container implements Benutzer {
	/**
	 * The default value of the '{@link #getBenutzername() <em>Benutzername</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBenutzername()
	 * @generated
	 * @ordered
	 */
	protected static final String BENUTZERNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBenutzername() <em>Benutzername</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBenutzername()
	 * @generated
	 * @ordered
	 */
	protected String benutzername = BENUTZERNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEmail() <em>Email</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmail()
	 * @generated
	 * @ordered
	 */
	protected static final String EMAIL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEmail() <em>Email</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEmail()
	 * @generated
	 * @ordered
	 */
	protected String email = EMAIL_EDEFAULT;

	/**
	 * The default value of the '{@link #getPasswort() <em>Passwort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPasswort()
	 * @generated
	 * @ordered
	 */
	protected static final String PASSWORT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPasswort() <em>Passwort</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPasswort()
	 * @generated
	 * @ordered
	 */
	protected String passwort = PASSWORT_EDEFAULT;

	/**
	 * The default value of the '{@link #getRolle() <em>Rolle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRolle()
	 * @generated
	 * @ordered
	 */
	protected static final String ROLLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRolle() <em>Rolle</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRolle()
	 * @generated
	 * @ordered
	 */
	protected String rolle = ROLLE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getReservierungen() <em>Reservierungen</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReservierungen()
	 * @generated
	 * @ordered
	 */
	protected EList<Reservierung> reservierungen;

	/**
	 * The cached value of the '{@link #getBuchungen() <em>Buchungen</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuchungen()
	 * @generated
	 * @ordered
	 */
	protected EList<Buchung> buchungen;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected BenutzerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KinoVerwaltungPackage.Literals.BENUTZER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBenutzername() {
		return benutzername;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBenutzername(String newBenutzername) {
		String oldBenutzername = benutzername;
		benutzername = newBenutzername;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.BENUTZER__BENUTZERNAME,
					oldBenutzername, benutzername));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEmail() {
		return email;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEmail(String newEmail) {
		String oldEmail = email;
		email = newEmail;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.BENUTZER__EMAIL, oldEmail,
					email));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPasswort() {
		return passwort;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPasswort(String newPasswort) {
		String oldPasswort = passwort;
		passwort = newPasswort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.BENUTZER__PASSWORT, oldPasswort,
					passwort));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getRolle() {
		return rolle;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setRolle(String newRolle) {
		String oldRolle = rolle;
		rolle = newRolle;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.BENUTZER__ROLLE, oldRolle,
					rolle));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Reservierung> getReservierungen() {
		if (reservierungen == null) {
			reservierungen = new EObjectResolvingEList<Reservierung>(Reservierung.class, this,
					KinoVerwaltungPackage.BENUTZER__RESERVIERUNGEN);
		}
		return reservierungen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Buchung> getBuchungen() {
		if (buchungen == null) {
			buchungen = new EObjectResolvingEList<Buchung>(Buchung.class, this,
					KinoVerwaltungPackage.BENUTZER__BUCHUNGEN);
		}
		return buchungen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case KinoVerwaltungPackage.BENUTZER__BENUTZERNAME:
			return getBenutzername();
		case KinoVerwaltungPackage.BENUTZER__EMAIL:
			return getEmail();
		case KinoVerwaltungPackage.BENUTZER__PASSWORT:
			return getPasswort();
		case KinoVerwaltungPackage.BENUTZER__ROLLE:
			return getRolle();
		case KinoVerwaltungPackage.BENUTZER__RESERVIERUNGEN:
			return getReservierungen();
		case KinoVerwaltungPackage.BENUTZER__BUCHUNGEN:
			return getBuchungen();
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
		case KinoVerwaltungPackage.BENUTZER__BENUTZERNAME:
			setBenutzername((String) newValue);
			return;
		case KinoVerwaltungPackage.BENUTZER__EMAIL:
			setEmail((String) newValue);
			return;
		case KinoVerwaltungPackage.BENUTZER__PASSWORT:
			setPasswort((String) newValue);
			return;
		case KinoVerwaltungPackage.BENUTZER__ROLLE:
			setRolle((String) newValue);
			return;
		case KinoVerwaltungPackage.BENUTZER__RESERVIERUNGEN:
			getReservierungen().clear();
			getReservierungen().addAll((Collection<? extends Reservierung>) newValue);
			return;
		case KinoVerwaltungPackage.BENUTZER__BUCHUNGEN:
			getBuchungen().clear();
			getBuchungen().addAll((Collection<? extends Buchung>) newValue);
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
		case KinoVerwaltungPackage.BENUTZER__BENUTZERNAME:
			setBenutzername(BENUTZERNAME_EDEFAULT);
			return;
		case KinoVerwaltungPackage.BENUTZER__EMAIL:
			setEmail(EMAIL_EDEFAULT);
			return;
		case KinoVerwaltungPackage.BENUTZER__PASSWORT:
			setPasswort(PASSWORT_EDEFAULT);
			return;
		case KinoVerwaltungPackage.BENUTZER__ROLLE:
			setRolle(ROLLE_EDEFAULT);
			return;
		case KinoVerwaltungPackage.BENUTZER__RESERVIERUNGEN:
			getReservierungen().clear();
			return;
		case KinoVerwaltungPackage.BENUTZER__BUCHUNGEN:
			getBuchungen().clear();
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
		case KinoVerwaltungPackage.BENUTZER__BENUTZERNAME:
			return BENUTZERNAME_EDEFAULT == null ? benutzername != null : !BENUTZERNAME_EDEFAULT.equals(benutzername);
		case KinoVerwaltungPackage.BENUTZER__EMAIL:
			return EMAIL_EDEFAULT == null ? email != null : !EMAIL_EDEFAULT.equals(email);
		case KinoVerwaltungPackage.BENUTZER__PASSWORT:
			return PASSWORT_EDEFAULT == null ? passwort != null : !PASSWORT_EDEFAULT.equals(passwort);
		case KinoVerwaltungPackage.BENUTZER__ROLLE:
			return ROLLE_EDEFAULT == null ? rolle != null : !ROLLE_EDEFAULT.equals(rolle);
		case KinoVerwaltungPackage.BENUTZER__RESERVIERUNGEN:
			return reservierungen != null && !reservierungen.isEmpty();
		case KinoVerwaltungPackage.BENUTZER__BUCHUNGEN:
			return buchungen != null && !buchungen.isEmpty();
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
		result.append(" (benutzername: ");
		result.append(benutzername);
		result.append(", email: ");
		result.append(email);
		result.append(", passwort: ");
		result.append(passwort);
		result.append(", rolle: ");
		result.append(rolle);
		result.append(')');
		return result.toString();
	}

} //BenutzerImpl
