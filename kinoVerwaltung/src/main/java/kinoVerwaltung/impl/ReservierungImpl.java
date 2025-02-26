/**
 */
package kinoVerwaltung.impl;

import java.lang.reflect.InvocationTargetException;

import java.util.Collection;
import java.util.Date;

import kinoVerwaltung.Buchung;
import kinoVerwaltung.KinoVerwaltungPackage;
import kinoVerwaltung.Reservierung;
import kinoVerwaltung.Sitzplatz;
import kinoVerwaltung.Status;
import kinoVerwaltung.Vorstellung;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reservierung</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link kinoVerwaltung.impl.ReservierungImpl#getReservierungsnummer <em>Reservierungsnummer</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.ReservierungImpl#getDatum <em>Datum</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.ReservierungImpl#getStatus <em>Status</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.ReservierungImpl#getSitzplaetze <em>Sitzplaetze</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.ReservierungImpl#getBuchung <em>Buchung</em>}</li>
 *   <li>{@link kinoVerwaltung.impl.ReservierungImpl#getVorstellung <em>Vorstellung</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ReservierungImpl extends MinimalEObjectImpl.Container implements Reservierung {
	/**
	 * The default value of the '{@link #getReservierungsnummer() <em>Reservierungsnummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReservierungsnummer()
	 * @generated
	 * @ordered
	 */
	protected static final String RESERVIERUNGSNUMMER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getReservierungsnummer() <em>Reservierungsnummer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReservierungsnummer()
	 * @generated
	 * @ordered
	 */
	protected String reservierungsnummer = RESERVIERUNGSNUMMER_EDEFAULT;

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
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final Status STATUS_EDEFAULT = Status.OFFEN;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected Status status = STATUS_EDEFAULT;

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
	 * The cached value of the '{@link #getBuchung() <em>Buchung</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuchung()
	 * @generated
	 * @ordered
	 */
	protected Buchung buchung;

	/**
	 * The cached value of the '{@link #getVorstellung() <em>Vorstellung</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVorstellung()
	 * @generated
	 * @ordered
	 */
	protected Vorstellung vorstellung;

	protected int ticketAnzahl;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
    public ReservierungImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return KinoVerwaltungPackage.Literals.RESERVIERUNG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getReservierungsnummer() {
		return reservierungsnummer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setReservierungsnummer(String newReservierungsnummer) {
		String oldReservierungsnummer = reservierungsnummer;
		reservierungsnummer = newReservierungsnummer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					KinoVerwaltungPackage.RESERVIERUNG__RESERVIERUNGSNUMMER, oldReservierungsnummer,
					reservierungsnummer));
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
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.RESERVIERUNG__DATUM, oldDatum,
					datum));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Status getStatus() {
		return status;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setStatus(Status newStatus) {
		Status oldStatus = status;
		status = newStatus == null ? STATUS_EDEFAULT : newStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.RESERVIERUNG__STATUS, oldStatus,
					status));
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
					KinoVerwaltungPackage.RESERVIERUNG__SITZPLAETZE);
		}
		return sitzplaetze;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Buchung getBuchung() {
		if (buchung != null && buchung.eIsProxy()) {
			InternalEObject oldBuchung = (InternalEObject) buchung;
			buchung = (Buchung) eResolveProxy(oldBuchung);
			if (buchung != oldBuchung) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							KinoVerwaltungPackage.RESERVIERUNG__BUCHUNG, oldBuchung, buchung));
			}
		}
		return buchung;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Buchung basicGetBuchung() {
		return buchung;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBuchung(Buchung newBuchung) {
		Buchung oldBuchung = buchung;
		buchung = newBuchung;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.RESERVIERUNG__BUCHUNG,
					oldBuchung, buchung));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Vorstellung getVorstellung() {
		if (vorstellung != null && vorstellung.eIsProxy()) {
			InternalEObject oldVorstellung = (InternalEObject) vorstellung;
			vorstellung = (Vorstellung) eResolveProxy(oldVorstellung);
			if (vorstellung != oldVorstellung) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							KinoVerwaltungPackage.RESERVIERUNG__VORSTELLUNG, oldVorstellung, vorstellung));
			}
		}
		return vorstellung;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Vorstellung basicGetVorstellung() {
		return vorstellung;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVorstellung(Vorstellung newVorstellung) {
		Vorstellung oldVorstellung = vorstellung;
		vorstellung = newVorstellung;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, KinoVerwaltungPackage.RESERVIERUNG__VORSTELLUNG,
					oldVorstellung, vorstellung));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Buchung wandelReservierungInBuchung() {
		BuchungImpl buchung = new BuchungImpl();

		buchung.setDatum(getDatum());
		buchung.setBuchungsnummer(getReservierungsnummer() + 1);

		return buchung;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void storniereReservierung() {
		setStatus(Status.STORNIERT);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case KinoVerwaltungPackage.RESERVIERUNG__RESERVIERUNGSNUMMER:
			return getReservierungsnummer();
		case KinoVerwaltungPackage.RESERVIERUNG__DATUM:
			return getDatum();
		case KinoVerwaltungPackage.RESERVIERUNG__STATUS:
			return getStatus();
		case KinoVerwaltungPackage.RESERVIERUNG__SITZPLAETZE:
			return getSitzplaetze();
		case KinoVerwaltungPackage.RESERVIERUNG__BUCHUNG:
			if (resolve)
				return getBuchung();
			return basicGetBuchung();
		case KinoVerwaltungPackage.RESERVIERUNG__VORSTELLUNG:
			if (resolve)
				return getVorstellung();
			return basicGetVorstellung();
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
		case KinoVerwaltungPackage.RESERVIERUNG__RESERVIERUNGSNUMMER:
			setReservierungsnummer((String) newValue);
			return;
		case KinoVerwaltungPackage.RESERVIERUNG__DATUM:
			setDatum((Date) newValue);
			return;
		case KinoVerwaltungPackage.RESERVIERUNG__STATUS:
			setStatus((Status) newValue);
			return;
		case KinoVerwaltungPackage.RESERVIERUNG__SITZPLAETZE:
			getSitzplaetze().clear();
			getSitzplaetze().addAll((Collection<? extends Sitzplatz>) newValue);
			return;
		case KinoVerwaltungPackage.RESERVIERUNG__BUCHUNG:
			setBuchung((Buchung) newValue);
			return;
		case KinoVerwaltungPackage.RESERVIERUNG__VORSTELLUNG:
			setVorstellung((Vorstellung) newValue);
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
		case KinoVerwaltungPackage.RESERVIERUNG__RESERVIERUNGSNUMMER:
			setReservierungsnummer(RESERVIERUNGSNUMMER_EDEFAULT);
			return;
		case KinoVerwaltungPackage.RESERVIERUNG__DATUM:
			setDatum(DATUM_EDEFAULT);
			return;
		case KinoVerwaltungPackage.RESERVIERUNG__STATUS:
			setStatus(STATUS_EDEFAULT);
			return;
		case KinoVerwaltungPackage.RESERVIERUNG__SITZPLAETZE:
			getSitzplaetze().clear();
			return;
		case KinoVerwaltungPackage.RESERVIERUNG__BUCHUNG:
			setBuchung((Buchung) null);
			return;
		case KinoVerwaltungPackage.RESERVIERUNG__VORSTELLUNG:
			setVorstellung((Vorstellung) null);
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
		case KinoVerwaltungPackage.RESERVIERUNG__RESERVIERUNGSNUMMER:
			return RESERVIERUNGSNUMMER_EDEFAULT == null ? reservierungsnummer != null
					: !RESERVIERUNGSNUMMER_EDEFAULT.equals(reservierungsnummer);
		case KinoVerwaltungPackage.RESERVIERUNG__DATUM:
			return DATUM_EDEFAULT == null ? datum != null : !DATUM_EDEFAULT.equals(datum);
		case KinoVerwaltungPackage.RESERVIERUNG__STATUS:
			return status != STATUS_EDEFAULT;
		case KinoVerwaltungPackage.RESERVIERUNG__SITZPLAETZE:
			return sitzplaetze != null && !sitzplaetze.isEmpty();
		case KinoVerwaltungPackage.RESERVIERUNG__BUCHUNG:
			return buchung != null;
		case KinoVerwaltungPackage.RESERVIERUNG__VORSTELLUNG:
			return vorstellung != null;
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
		case KinoVerwaltungPackage.RESERVIERUNG___WANDEL_RESERVIERUNG_IN_BUCHUNG:
			return wandelReservierungInBuchung();
		case KinoVerwaltungPackage.RESERVIERUNG___STORNIERE_RESERVIERUNG:
			storniereReservierung();
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
		result.append(" (reservierungsnummer: ");
		result.append(reservierungsnummer);
		result.append(", datum: ");
		result.append(datum);
		result.append(", status: ");
		result.append(status);
		result.append(')');
		return result.toString();
	}

	public int getTicketAnzahl() {
		return ticketAnzahl;
	}

	public void setTicketAnzahl(int ticketAnzahl) {
		this.ticketAnzahl = ticketAnzahl;
	}
} //ReservierungImpl
